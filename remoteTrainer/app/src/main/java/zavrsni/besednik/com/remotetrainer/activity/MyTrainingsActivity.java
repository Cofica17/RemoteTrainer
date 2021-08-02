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
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.Schedule;
import zavrsni.besednik.com.remotetrainer.model.models.Training;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;
import zavrsni.besednik.com.remotetrainer.model.requests.ScheduleForUserRequest;
import zavrsni.besednik.com.remotetrainer.model.views.TrainingPreviewAdapter;

public class MyTrainingsActivity extends BaseActivity {

    @BindView(R.id.rvMyTrainings)
    RecyclerView rvTrainings;

    TrainingPreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trainings);
        ButterKnife.bind(this);
        onCreateDrawer("Moji treninzi");

        adapter = new TrainingPreviewAdapter(MyTrainingsActivity.this, null);
        rvTrainings.setAdapter(adapter);
        rvTrainings.setLayoutManager(new LinearLayoutManager(MyTrainingsActivity.this));

        MutableLiveData<List<Schedule.Training>> trainings = ApiRequester.getTrainingsForTrainer(InfoHolder.user.getId());
        trainings.observe(this, new Observer<List<Schedule.Training>>() {
            @Override
            public void onChanged(List<Schedule.Training> trainings) {
                if(trainings == null)
                {
                    return;
                }
                setNewAdapterToRecyclerView(trainings);
            }
        });
    }

    public void setNewAdapterToRecyclerView(List<Schedule.Training> trainings)
    {
        adapter = new TrainingPreviewAdapter(MyTrainingsActivity.this, trainings);
        rvTrainings.setAdapter(adapter);
    }
}