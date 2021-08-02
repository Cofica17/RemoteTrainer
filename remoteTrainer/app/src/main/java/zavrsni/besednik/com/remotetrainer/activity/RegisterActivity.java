package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;
import zavrsni.besednik.com.remotetrainer.model.enums.RegisterResponse;
import zavrsni.besednik.com.remotetrainer.model.enums.UserType;
import zavrsni.besednik.com.remotetrainer.model.requests.RegisterRequest;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.tvUser)
    TextView tvUser;

    @BindView(R.id.tvTrainer)
    TextView tvTrainer;

    @BindView(R.id.ibBack)
    ImageButton ibBack;

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etSurname)
    EditText etSurname;
    @BindView(R.id.etEmailReg)
    EditText etEmail;
    @BindView(R.id.etPasswordReg)
    EditText etPassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;

    @BindView(R.id.tvCV)
    TextView tvCV;
    @BindView(R.id.ibCV)
    ImageButton ibCV;
    @BindView(R.id.tvDegree)
    TextView tvDegree;
    @BindView(R.id.ibDegree)
    ImageButton ibDegree;

    private UserType currentType = UserType.User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        toggleDocumentInfo(View.INVISIBLE);
    }

    @SuppressLint("ResourceAsColor")
    private void init() {
        ButterKnife.bind(this);
        tvUser.setTextColor(ContextCompat.getColor(this, R.color.colorOfIconsAndTextOnLoginScreen));
    }

    @OnClick(R.id.tvTrainer)
    public void switchToTrainerFragment()
    {
        if (tvTrainer.getCurrentTextColor() == ContextCompat.getColor(this, R.color.colorOfIconsAndTextOnLoginScreen))
        {
            return;
        }

        tvUser.setTextColor(ContextCompat.getColor(this, R.color.black));
        tvTrainer.setTextColor(ContextCompat.getColor(this, R.color.colorOfIconsAndTextOnLoginScreen));
        currentType = UserType.Trainer;
        toggleDocumentInfo(View.VISIBLE);
    }

    @OnClick(R.id.tvUser)
    public void switchToUserFragment()
    {
        if (tvUser.getCurrentTextColor() == ContextCompat.getColor(this, R.color.colorOfIconsAndTextOnLoginScreen))
        {
            return;
        }

        tvUser.setTextColor(ContextCompat.getColor(this, R.color.colorOfIconsAndTextOnLoginScreen));
        tvTrainer.setTextColor(ContextCompat.getColor(this, R.color.black));
        currentType = UserType.User;
        toggleDocumentInfo(View.INVISIBLE);
    }

    @OnClick(R.id.ibBack)
    public void buttonBackPressed()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnRegister)
    public void register()
    {
        if(etName.getText().length() == 0 || etPassword.getText().length() == 0 || etEmail.getText().length() == 0 || etSurname.getText().length() == 0 || etConfirmPassword.getText().length() == 0)
        {
            Utilities.createAndShowAlertDialog(this, getString(R.string.registrationFailed), getString(R.string.enterAllFields));
            return;
        }

        if(!etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))
        {
            Utilities.createAndShowAlertDialog(this, getString(R.string.registrationFailed), getString(R.string.passwordsDoNotMatch));
            return;
        }

        RegisterRequest registerRequest = new RegisterRequest(etName.getText().toString(), etSurname.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString(), currentType.id);
        MutableLiveData<RegisterResponse> code = ApiRequester.register(registerRequest);
        code.observe(this, new Observer<RegisterResponse>() {
            @Override
            public void onChanged(RegisterResponse registerResponse) {
                if (registerResponse == RegisterResponse.OK){

                    androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(RegisterActivity.this)
                            .setTitle(getString(R.string.regSuccesful))
                            .setMessage(getString(R.string.regSucMessage))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                else if(registerResponse == RegisterResponse.EmailTaken){
                    Utilities.createAndShowAlertDialog(RegisterActivity.this, getString(R.string.registrationFailed), getString(R.string.emailTaken));
                }
                else{
                    Utilities.createAndShowAlertDialog(RegisterActivity.this, getString(R.string.registrationFailed), getString(R.string.regFailedServer));
                }
            }
        });
    }


    private void toggleDocumentInfo(int visibility)
    {
        tvCV.setVisibility(visibility);
        tvDegree.setVisibility(visibility);
        ibCV.setVisibility(visibility);
        ibDegree.setVisibility(visibility);
    }
}
