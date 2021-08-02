package zavrsni.besednik.com.remotetrainer.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;

public class BaseActivity extends AppCompatActivity {

    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    protected void onCreateDrawer(String headerText){
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);

        setTitle(headerText);
        setNavHeaderAttr();

        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(item -> {
            Intent intent = null;

            switch (item.getItemId())
            {
                case R.id.miTrainerSearch:
                    intent = new Intent(this, TrainerSearchActivity.class);
                    break;
                case R.id.miCreateTraining:
                    intent = new Intent(this, CreateTrainingActivity.class);
                    break;
                case R.id.miMyTrainings:
                    intent = new Intent(this, MyTrainingsActivity.class);
                    break;
                case R.id.miMyUsers:
                    intent = new Intent(this, MyUsersActivity.class);
                    break;
                case R.id.miCreateExercise:
                    intent = new Intent(this, CreateExerciseActivity.class);
                    break;
                case R.id.miMyExercises:
                    intent = new Intent(this, MyExercisesActivity.class);
                    break;
                case R.id.miUserRequests:
                    intent = new Intent(this, MyUsersRequestsActivity.class);
                    break;
                case R.id.miISchedule:
                    Toast.makeText(getApplicationContext(), "Schedule", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.miProfile:
                    Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.miSettings:
                    Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.miCreateSchedule:
                    intent = new Intent(this, CreateScheduleActivity.class);
                    break;
            }

            if(intent != null)
            {
                startActivity(intent);
            }

            return true;
        });
    }

    private void setNavHeaderAttr() {
        TextView tvFirstName = navigationView.getHeaderView(0).findViewById(R.id.tvUserFirstname);
        TextView tvLastName = navigationView.getHeaderView(0).findViewById(R.id.tvUserLastname);

        tvFirstName.setText(InfoHolder.user.getFirstName());
        tvLastName.setText(InfoHolder.user.getLastName());
    }

    public void setTitle(String title){
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.white));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}