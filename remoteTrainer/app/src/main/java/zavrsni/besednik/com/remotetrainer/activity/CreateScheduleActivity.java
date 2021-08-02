package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.User;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;

public class CreateScheduleActivity extends BaseActivity {

    @BindView(R.id.spUsers)
    Spinner spUsers;
    @BindView(R.id.etEndDate)
    EditText etEndDate;
    @BindView(R.id.etStartDate)
    EditText etStartDate;
    @BindView(R.id.tvCurrentDate)
    TextView tvCurrentDate;

    @BindView(R.id.tvMorningWorkout)
    TextView tvMorningWorkout;
    @BindView(R.id.imgMorningWorkout)
    ImageView ibMorningWorkout;

    @BindView(R.id.tvMiddayWorkout)
    TextView tvMiddayWorkout;
    @BindView(R.id.imgMiddayWorkout)
    ImageView ibMiddayWorkout;

    @BindView(R.id.tvEveningWorkout)
    TextView tvEveningWorkout;
    @BindView(R.id.imgEveningWorkout)
    ImageView ibEveningWorkout;

    User selectedUser;

    CharSequence currentDate;
    long currentDaysInMilliseconds;

    int REQ_CODE_MORNING = 123;
    int REQ_CODE_MIDDAY = 1234;
    int REQ_CODE_EVENING = 1235;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);
        ButterKnife.bind(this);
        onCreateDrawer(getString(R.string.createSchedulString));
        setSpinner();

        currentDate = Utilities.getCurrentDate("dd.MM.yyyy");
        Date d = new Date();
        currentDaysInMilliseconds = d.getTime();

        etStartDate.setText(currentDate);
        etEndDate.setText(currentDate);

        tvMorningWorkout.setVisibility(View.INVISIBLE);
        ibMorningWorkout.setVisibility(View.INVISIBLE);
        tvMiddayWorkout.setVisibility(View.INVISIBLE);
        ibMiddayWorkout.setVisibility(View.INVISIBLE);
        tvEveningWorkout.setVisibility(View.INVISIBLE);
        ibEveningWorkout.setVisibility(View.INVISIBLE);
    }

    private void setSpinner() {
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, InfoHolder.myUsers);
        spUsers.setAdapter(adapter);
        spUsers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUser = (User)parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @OnClick(R.id.cvMorning)
    public void onMorningTraining()
    {
        Intent intent = new Intent(this, ChooseExerciseActivity.class);
        startActivityForResult(intent, REQ_CODE_MORNING);
    }

    @OnClick(R.id.cvMidday)
    public void onMiddayTraining()
    {
        Intent intent = new Intent(this, ChooseExerciseActivity.class);
        startActivityForResult(intent, REQ_CODE_MIDDAY);
    }

    @OnClick(R.id.cvEvening)
    public void onEveningTraining()
    {
        Intent intent = new Intent(this, ChooseExerciseActivity.class);
        startActivityForResult(intent, REQ_CODE_EVENING);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
          if  (requestCode == REQ_CODE_MORNING){
                Exercise exercise = (Exercise) data.getSerializableExtra("exercise");
                tvMorningWorkout.setVisibility(View.VISIBLE);
                tvMorningWorkout.setText(exercise.getName());
                ibMorningWorkout.setVisibility(View.VISIBLE);
            }
          else if(requestCode == REQ_CODE_MIDDAY)
          {
              Exercise exercise = (Exercise) data.getSerializableExtra("exercise");
              tvMiddayWorkout.setVisibility(View.VISIBLE);
              tvMiddayWorkout.setText(exercise.getName());
              ibMiddayWorkout.setVisibility(View.VISIBLE);
          }
          else if(requestCode == REQ_CODE_EVENING)
          {
              Exercise exercise = (Exercise) data.getSerializableExtra("exercise");
              tvEveningWorkout.setVisibility(View.VISIBLE);
              tvEveningWorkout.setText(exercise.getName());
              ibEveningWorkout.setVisibility(View.VISIBLE);
          }
        }
    }


    @OnClick(R.id.ibPreviousDate)
    public void previousDate()
    {
        long currentDate = getCalculatedDateInMilis(tvCurrentDate.getText().toString(), "dd.MM.yyyy", -1);
        long startDate = getCalculatedDateInMilis(etStartDate.getText().toString(), "dd.MM.yyyy",0);
        
        if(currentDate >= startDate)
        {
            CharSequence showDate = Utilities.getCurrentDate("dd.MM.yyy", currentDate);
            tvCurrentDate.setText(showDate);
        }
    }


    @OnClick(R.id.ibNextDate)
    public void nextDate()
    {
        long currentDate = getCalculatedDateInMilis(tvCurrentDate.getText().toString(), "dd.MM.yyyy", 1);
        long endDate = getCalculatedDateInMilis(etEndDate.getText().toString(), "dd.MM.yyyy",0);

        if(currentDate <= endDate)
        {
            CharSequence showDate = Utilities.getCurrentDate("dd.MM.yyy", currentDate);
            tvCurrentDate.setText(showDate);
        }
    }


    @OnTextChanged(R.id.etStartDate)
    public void startDateChanged()
    {
        if(isValidDate(etStartDate.getText().toString()))
        {
            tvCurrentDate.setText(etStartDate.getText().toString());
            currentDate = etStartDate.getText().toString();
        }
    }

    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static String getCalculatedDate(String date,String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        if (!date.isEmpty()) {
            try {
                cal.setTime(s.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }

    public static long getCalculatedDateInMilis(String date,String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        if (!date.isEmpty()) {
            try {
                cal.setTime(s.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTimeInMillis();
    }
}