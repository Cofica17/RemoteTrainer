package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.Schedule;
import zavrsni.besednik.com.remotetrainer.model.requests.ScheduleForUserRequest;
import zavrsni.besednik.com.remotetrainer.model.views.ExercisePreviewAdapter;
import zavrsni.besednik.com.remotetrainer.model.views.TrainingPreviewAdapter;

public class MyExercisesActivity extends BaseActivity {

    @BindView(R.id.rvMyExercises)
    RecyclerView rvExercises;

    ExercisePreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exercises);
        ButterKnife.bind(this);

        onCreateDrawer("Moje Vježbe");

        adapter = new ExercisePreviewAdapter(MyExercisesActivity.this, null);
        rvExercises.setAdapter(adapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(MyExercisesActivity.this));

        MutableLiveData<List<Exercise>> exercises = ApiRequester.getExercisesForTrainer(InfoHolder.user.getId());
        exercises.observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                if(exercises == null)
                {
                    return;
                }
                adapter = new ExercisePreviewAdapter(MyExercisesActivity.this, exercises);
                rvExercises.setAdapter(adapter);
            }
        });
    }
}