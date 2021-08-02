package zavrsni.besednik.com.remotetrainer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.views.ChooseExercisePreviewAdapter;
import zavrsni.besednik.com.remotetrainer.model.views.ExercisePreviewAdapter;

public class ChooseExerciseActivity extends BaseActivity {

    @BindView(R.id.rvMyExercises)
    RecyclerView rvExercises;

    ChooseExercisePreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_exercises);
        ButterKnife.bind(this);

        onCreateDrawer("Izaberite vježbu");

        adapter = new ChooseExercisePreviewAdapter(this, null);
        rvExercises.setAdapter(adapter);
        rvExercises.setLayoutManager(new LinearLayoutManager(this));

        MutableLiveData<List<Exercise>> exercises = ApiRequester.getExercisesForTrainer(InfoHolder.user.getId());
        exercises.observe(this, new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                if(exercises == null)
                {
                    return;
                }
                adapter = new ChooseExercisePreviewAdapter(ChooseExerciseActivity.this, exercises);
                rvExercises.setAdapter(adapter);
            }
        });

        rvExercises.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return adapter.selectedExercise != null;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if(adapter.selectedExercise != null) {
                    Intent intent = new Intent();
                    intent.putExtra("exercise", adapter.selectedExercise);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}