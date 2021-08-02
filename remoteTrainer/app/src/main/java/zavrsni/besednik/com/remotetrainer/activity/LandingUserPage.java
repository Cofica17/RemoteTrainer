package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.Schedule;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;
import zavrsni.besednik.com.remotetrainer.model.requests.ScheduleForUserRequest;
import zavrsni.besednik.com.remotetrainer.model.views.TrainingPreviewAdapter;

public class LandingUserPage extends BaseActivity {

    RecyclerView rvTrainings;
    TrainingPreviewAdapter adapter;
    CharSequence currentDate;
    long currentDaysInMilliseconds;

    @BindView(R.id.tvDayDate)
    TextView tvDayDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_user_page);
        super.onCreateDrawer(getString(R.string.scheduleString));
        ButterKnife.bind(this);

        Date d = new Date();
        currentDaysInMilliseconds = d.getTime();

        rvTrainings = findViewById(R.id.rvTrainings);
        adapter = new TrainingPreviewAdapter(LandingUserPage.this, null);
        rvTrainings.setAdapter(adapter);
        rvTrainings.setLayoutManager(new LinearLayoutManager(LandingUserPage.this));

        currentDate = Utilities.getCurrentDate("MM-dd-yyyy");
        CharSequence showDate = Utilities.getCurrentDate("dd.MM.yyy", currentDaysInMilliseconds);
        tvDayDate.setText(showDate.toString());

        ScheduleForUserRequest request = new ScheduleForUserRequest(InfoHolder.user.getId(), currentDate.toString());
        MutableLiveData<Schedule> schedule = ApiRequester.getScheduleForUser(request);
        schedule.observe(this, new Observer<Schedule>() {
            @Override
            public void onChanged(Schedule schedule) {
                if(schedule == null)
                {
                    return;
                }
                InfoHolder.schedule = schedule;
                List<Schedule.Training> trainingsToday = schedule.getTrainingsForDate(currentDate.toString());
                setNewAdapterToRecyclerView(trainingsToday);
            }
        });
    }

    @OnClick(R.id.ibPrevious)
    public void previousDayRequested(View view)
    {
        currentDaysInMilliseconds -= InfoHolder.MILLISECONDS_IN_DAY;
        CharSequence newDate = Utilities.getCurrentDate("MM-dd-yyyy", currentDaysInMilliseconds);
        CharSequence showDate = Utilities.getCurrentDate("dd.MM.yyy", currentDaysInMilliseconds);
        List<Schedule.Training> trainingsToday = InfoHolder.schedule.getTrainingsForDate(newDate.toString());
        setNewAdapterToRecyclerView(trainingsToday);
        tvDayDate.setText(showDate.toString());
    }

    @OnClick(R.id.ibNext)
    public void nextDayRequested(View view)
    {
        currentDaysInMilliseconds += InfoHolder.MILLISECONDS_IN_DAY;
        CharSequence newDate = Utilities.getCurrentDate("MM-dd-yyyy", currentDaysInMilliseconds);
        CharSequence showDate = Utilities.getCurrentDate("dd.MM.yyy", currentDaysInMilliseconds);
        List<Schedule.Training> trainingsToday = InfoHolder.schedule.getTrainingsForDate(newDate.toString());
        setNewAdapterToRecyclerView(trainingsToday);
        tvDayDate.setText(showDate.toString());
    }

    public void setNewAdapterToRecyclerView(List<Schedule.Training> trainings)
    {
        adapter = new TrainingPreviewAdapter(LandingUserPage.this, trainings);
        rvTrainings.setAdapter(adapter);
    }
}