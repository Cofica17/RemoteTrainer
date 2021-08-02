package zavrsni.besednik.com.remotetrainer.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.enums.RegisterResponse;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;
import zavrsni.besednik.com.remotetrainer.model.models.VideoHandler;
import zavrsni.besednik.com.remotetrainer.model.requests.ExerciseRequest;

public class CreateExerciseActivity extends BaseActivity {

    @BindView(R.id.etExerciseName)
    EditText etExerciseName;
    @BindView(R.id.etExerciseDescription)
    EditText etExerciseDescription;
    @BindView(R.id.vwVideoPreview)
    VideoView vwVideo;

    MediaController mediaController;

    int requestCode = 3;
    String videoPath = "https://videos1232.blob.core.windows.net/videos/";
    String videoFullPath;
    String videoName2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);
        ButterKnife.bind(this);

        onCreateDrawer("Kreiraj vježbu");
    }


    @OnClick(R.id.btnSaveExercise)
    public void saveExercise()
    {
        if(etExerciseName.getText().length() == 0 || etExerciseDescription.getText().length() == 0 || videoFullPath == null) {
            Utilities.createAndShowAlertDialog(this, "Fields missing.", "Please insert all fields and upload a video.");
            return;
        }

        String name = etExerciseName.getText() + "";
        String desc = etExerciseDescription.getText() + "";

        ExerciseRequest exerciseRequest = new ExerciseRequest(name, desc, videoName2, videoFullPath, InfoHolder.user.getId());
        MutableLiveData<RegisterResponse> code = ApiRequester.newExercise(exerciseRequest);
        code.observe(this, new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse registerResponse) {
                if (registerResponse == RegisterResponse.OK){

                    Utilities.createAndShowAlertDialog(CreateExerciseActivity.this, "Exercise", "Successfully create a new exercise!");
                }
                else if(registerResponse == RegisterResponse.EmailTaken){
                    Utilities.createAndShowAlertDialog(CreateExerciseActivity.this, "Exercise", "Something went wrong, try again!");
                }
                else{
                    Utilities.createAndShowAlertDialog(CreateExerciseActivity.this, "Exercise", "Something went wrong, try again!");
                }
            }
        });

    }


    @OnClick(R.id.btnUploadVideo)
    public void selectVideo()
    {
        Intent mediaChooser = new Intent(Intent.ACTION_GET_CONTENT);
        mediaChooser.setType("video/*");
        startActivityForResult(mediaChooser, requestCode);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == this.requestCode) {
                Uri videoUri = data.getData();
                vwVideo.setVideoURI(videoUri);
                vwVideo.start();
                mediaController = new MediaController(CreateExerciseActivity.this);
                mediaController.setMediaPlayer(vwVideo);
                vwVideo.setMediaController(mediaController);
                vwVideo.requestFocus();
                uploadVideo(videoUri);
            }
        }
    }


    private void uploadVideo(Uri uri){
        try {
            final InputStream videoStream = getContentResolver().openInputStream(uri);
            final int videoLength = videoStream.available();

            final Handler handler = new Handler();

            Thread th = new Thread(new Runnable() {
                public void run() {

                    try {

                        final String videoName = VideoHandler.UploadVideo(videoStream, videoLength);
                        videoFullPath = videoPath + videoName;
                        videoName2 = videoName;
                        handler.post(new Runnable() {

                            public void run() {
                                Toast.makeText(CreateExerciseActivity.this, "Video Uploaded Successfully. Name = " + videoName, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    catch(Exception ex) {
                        final String exceptionMessage = ex.getMessage();
                        handler.post(new Runnable() {
                            public void run() {
                                Toast.makeText(CreateExerciseActivity.this, exceptionMessage, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }});
            th.start();
        }
        catch(Exception ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}