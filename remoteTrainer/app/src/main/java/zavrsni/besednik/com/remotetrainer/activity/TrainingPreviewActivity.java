package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.Training;
import zavrsni.besednik.com.remotetrainer.model.views.ExercisePreviewAdapter;

public class TrainingPreviewActivity extends BaseActivity {

    @BindView(R.id.tvTrainingDescription)
    TextView tvTrainingDescription;
    @BindView(R.id.rvExercisesPreview)
    RecyclerView rvExercisesPreview;

    int trainingID;
    ExercisePreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_preview);
        ButterKnife.bind(this);

        if(getIntent().hasExtra("trainingID"))
        {
            trainingID = getIntent().getIntExtra("trainingID", -1);
        }

        if(trainingID == -1)
        {
            return;
        }

        if(getIntent().hasExtra("trainingName"))
        {
            onCreateDrawer(getIntent().getStringExtra("trainingName"));
        }

        adapter = new ExercisePreviewAdapter(this, null);
        rvExercisesPreview.setAdapter(adapter);
        rvExercisesPreview.setLayoutManager(new LinearLayoutManager(this));

        MutableLiveData<Training> training = ApiRequester.getTraining(trainingID);
        training.observe(this, new Observer<Training>() {
            @Override
            public void onChanged(Training training) {
                if(training == null)
                {
                    return;
                }

                tvTrainingDescription.setText(training.getDescription());
                List<Exercise> exercises = training.getExercises();
                adapter = new ExercisePreviewAdapter(TrainingPreviewActivity.this, exercises);
                rvExercisesPreview.setAdapter(adapter);
            }
        });

    }
}