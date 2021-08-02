package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.enums.UserType;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.User;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;
import zavrsni.besednik.com.remotetrainer.model.requests.LoginRequest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class  LoginActivity extends AppCompatActivity {

    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.etPasswordReg)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        etEmail.setText("trainer@mail.com");
        etPassword.setText("pwd123");
    }

    private void init() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvRegister)
    public void registerHere()
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnSignIn)
    public void login()
    {
        if(etEmail.getText().length() == 0 || etPassword.getText().length() == 0)
        {
            Utilities.createAndShowAlertDialog(this, getString(R.string.loginFailed), getString(R.string.enterAllFields));
            return;
        }

        LoginRequest loginRequest = new LoginRequest(etEmail.getText().toString(), etPassword.getText().toString());
        MutableLiveData<User> user = ApiRequester.login(loginRequest);
        user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null) {
                    Utilities.createAndShowAlertDialog(LoginActivity.this, getString(R.string.loginFailed), getString(R.string.wrongUsernameOrPwd));
                    return;
                }
                else
                {
                    InfoHolder.user = user;

                    Intent intent;

                    if(user.getType() == UserType.User.id) {
                        intent = new Intent(LoginActivity.this, LandingUserPage.class);
                    }
                    else{
                        intent = new Intent(LoginActivity.this, MyUsersActivity.class);
                    }

                    startActivity(intent);
                }
            }
        });
    }
}
