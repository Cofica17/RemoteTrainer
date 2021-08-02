package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.enums.RegisterResponse;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.Training;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;
import zavrsni.besednik.com.remotetrainer.model.requests.TrainingRequest;
import zavrsni.besednik.com.remotetrainer.model.views.ChooseExercisesPreviewAdapter;
import zavrsni.besednik.com.remotetrainer.model.views.ExercisePreviewAdapter;

public class CreateTrainingActivity extends BaseActivity {

    @BindView(R.id.rvChooseExercises)
    RecyclerView rvExercisesPreview;
    @BindView(R.id.etTrainingName)
    EditText etTrainingName;
    @BindView(R.id.etTrainingDesc)
    EditText etTrainingDesc;

    ChooseExercisesPreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_training);
        ButterKnife.bind(this);

        onCreateDrawer("Kreiraj trening");

        adapter = new ChooseExercisesPreviewAdapter(this, null);
        rvExercisesPreview.setAdapter(adapter);
        rvExercisesPreview.setLayoutManager(new LinearLayoutManager(this));

        MutableLiveData<Training> training = ApiRequester.getTraining(3);
        training.observe(this, new Observer<Training>() {
            @Override
            public void onChanged(Training training) {
                if(training == null)
                {
                    return;
                }

                List<Exercise> exercises = training.getExercises();
                adapter = new ChooseExercisesPreviewAdapter(CreateTrainingActivity.this, exercises);
                rvExercisesPreview.setAdapter(adapter);
            }
        });
    }

    @OnClick(R.id.btnCreateTraining)
    public void createTraining()
    {
        if(etTrainingName.getText().length() == 0 ||etTrainingDesc.getText().length() == 0 || adapter.selectedExercises.size() == 0)
        {
            Utilities.createAndShowAlertDialog(this, "Invalid", "Molimo upisite naziv treninga, opis treninga i odaberite vježbe");
        }

        TrainingRequest request = new TrainingRequest(etTrainingName.getText()+"", etTrainingDesc.getText()+"", InfoHolder.user.getId(), adapter.selectedExercises);
        MutableLiveData<RegisterResponse> code = ApiRequester.newTraining(request);
        code.observe(this, new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse registerResponse) {
                if (registerResponse == RegisterResponse.OK){

                    Utilities.createAndShowAlertDialog(CreateTrainingActivity.this, "Training", "Successfully create new training!");
                }
                else if(registerResponse == RegisterResponse.EmailTaken){
                    Utilities.createAndShowAlertDialog(CreateTrainingActivity.this, "Training", "Something went wrong, try again!");
                }
                else{
                    Utilities.createAndShowAlertDialog(CreateTrainingActivity.this, "Training", "Something went wrong, try again!");
                }
            }
        });

    }
}