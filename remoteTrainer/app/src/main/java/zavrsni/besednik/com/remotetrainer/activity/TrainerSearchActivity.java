package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.models.Trainer;
import zavrsni.besednik.com.remotetrainer.model.models.Training;
import zavrsni.besednik.com.remotetrainer.model.views.ExercisePreviewAdapter;
import zavrsni.besednik.com.remotetrainer.model.views.TrainerPreviewAdapter;

public class TrainerSearchActivity extends BaseActivity {
    
    @BindView(R.id.rvTrainers)
    RecyclerView rvTrainers;

    TrainerPreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_search);
        ButterKnife.bind(this);
        onCreateDrawer("Pronađi trenera");

        adapter = new TrainerPreviewAdapter(this, null);
        rvTrainers.setAdapter(adapter);
        rvTrainers.setLayoutManager(new LinearLayoutManager(this));

        MutableLiveData<List<Trainer>> trainers = ApiRequester.getTrainers();
        trainers.observe(this, new Observer<List<Trainer>>() {
            @Override
            public void onChanged(List<Trainer> trainers) {
                if(trainers == null)
                {
                    return;
                }

                adapter = new TrainerPreviewAdapter(TrainerSearchActivity.this, trainers);
                rvTrainers.setAdapter(adapter);
            }
        });
    }
}