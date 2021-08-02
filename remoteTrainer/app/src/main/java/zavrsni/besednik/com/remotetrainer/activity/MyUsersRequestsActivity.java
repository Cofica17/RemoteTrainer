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
import zavrsni.besednik.com.remotetrainer.model.models.User;
import zavrsni.besednik.com.remotetrainer.model.views.MyUsersPreviewAdapter;

public class MyUsersRequestsActivity extends BaseActivity {

    @BindView(R.id.rvMyUsersRequests)
    RecyclerView rvMyUsers;

    MyUsersPreviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_users_requests);
        ButterKnife.bind(this);
        onCreateDrawer("Moji zahtjevi za suradnju");

        adapter = new MyUsersPreviewAdapter(this, null);
        rvMyUsers.setAdapter(adapter);
        rvMyUsers.setLayoutManager(new LinearLayoutManager(this));

        MutableLiveData<List<User>> users = ApiRequester.getUsersRequests(InfoHolder.user.getId());
        users.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users == null)
                {
                    return;
                }

                adapter = new MyUsersPreviewAdapter(MyUsersRequestsActivity.this, users);
                rvMyUsers.setAdapter(adapter);
                InfoHolder.myUsers = users;
            }
        });
    }
}