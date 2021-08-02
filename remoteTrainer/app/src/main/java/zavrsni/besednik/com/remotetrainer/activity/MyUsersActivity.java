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
import zavrsni.besednik.com.remotetrainer.model.models.Trainer;
import zavrsni.besednik.com.remotetrainer.model.models.User;
import zavrsni.besednik.com.remotetrainer.model.views.MyUsersPreviewAdapter;
import zavrsni.besednik.com.remotetrainer.model.views.TrainerPreviewAdapter;

public class MyUsersActivity extends BaseActivity {

    @BindView(R.id.rvMyUsers)
    RecyclerView rvMyUsers;

    MyUsersPreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_users);
        ButterKnife.bind(this);

        onCreateDrawer("Moji korisnici");

        adapter = new MyUsersPreviewAdapter(this, null);
        rvMyUsers.setAdapter(adapter);
        rvMyUsers.setLayoutManager(new LinearLayoutManager(this));

        MutableLiveData<List<User>> users = ApiRequester.getUsersForTrainer(InfoHolder.user.getId());
        users.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users == null)
                {
                    return;
                }

                adapter = new MyUsersPreviewAdapter(MyUsersActivity.this, users);
                rvMyUsers.setAdapter(adapter);
                InfoHolder.myUsers = users;
            }
        });
    }
}