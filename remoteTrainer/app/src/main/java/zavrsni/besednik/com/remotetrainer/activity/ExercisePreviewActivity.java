package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.Training;

public class ExercisePreviewActivity extends BaseActivity {

    @BindView(R.id.tvExerciseDescription)
    TextView tvExerciseDescription;
    @BindView(R.id.tvVideoName)
    TextView tvVideoName;
    @BindView(R.id.vwVideo)
    VideoView vwVideo;

    int exerciseID;
    MediaController mediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_preview);
        ButterKnife.bind(this);

        if(getIntent().hasExtra("exerciseID"))
        {
            exerciseID = getIntent().getIntExtra("exerciseID", -1);
        }

        if(exerciseID == -1)
        {
            return;
        }

        if(getIntent().hasExtra("exerciseName"))
        {
            onCreateDrawer(getIntent().getStringExtra("exerciseName"));
        }

        MutableLiveData<Exercise> exercise = ApiRequester.getExercise(exerciseID);
        exercise.observe(this, new Observer<Exercise>() {
            @Override
            public void onChanged(Exercise exercise) {
                if(exercise == null)
                {
                    return;
                }

                tvExerciseDescription.setText(exercise.getDescription());
                tvVideoName.setText(exercise.getVideo().getName());
                vwVideo.setVideoPath(exercise.getVideo().getPath());
                vwVideo.start();
                mediaController = new MediaController(ExercisePreviewActivity.this);
                mediaController.setMediaPlayer(vwVideo);
                vwVideo.setMediaController(mediaController);
                vwVideo.requestFocus();
            }
        });
    }
}