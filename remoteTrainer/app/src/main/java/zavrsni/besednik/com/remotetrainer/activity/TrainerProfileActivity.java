package zavrsni.besednik.com.remotetrainer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.api.ApiRequester;
import zavrsni.besednik.com.remotetrainer.model.enums.RegisterResponse;
import zavrsni.besednik.com.remotetrainer.model.enums.UserType;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.Trainer;
import zavrsni.besednik.com.remotetrainer.model.models.TrainerProfile;
import zavrsni.besednik.com.remotetrainer.model.models.Utilities;
import zavrsni.besednik.com.remotetrainer.model.requests.AcceptUserRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.SendTrainerRequest;
import zavrsni.besednik.com.remotetrainer.model.views.TrainerPreviewAdapter;

public class TrainerProfileActivity extends AppCompatActivity {

    @BindView(R.id.imgTrainerProfile)
    ImageView imgTrainerProfile;
    @BindView(R.id.tvTrainerFullName)
    TextView tvTrainerFullName;
    @BindView(R.id.tvTrainerEmailProfile)
    TextView tvTrainerEmail;
    @BindView(R.id.tvDegreeURL)
    TextView tvDegreeURL;
    @BindView(R.id.tvCVURL)
    TextView tvCvURL;
    @BindView(R.id.btnSend)
    Button btnSend;

    int trainerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_profile);
        ButterKnife.bind(this);

        if(getIntent().hasExtra("trainerID"))
        {
            trainerID = getIntent().getIntExtra("trainerID", -1);
        }

        if(trainerID == -1)
        {
            return;
        }


        if(InfoHolder.user.getType() == UserType.Trainer.id)
        {
            btnSend.setText("Prihvati zahtjev za suradnju.");
            tvCvURL.setVisibility(View.INVISIBLE);
            tvDegreeURL.setVisibility(View.INVISIBLE);
        }


        MutableLiveData<TrainerProfile> trainer = ApiRequester.getTrainerProfile(trainerID);
        trainer.observe(this, new Observer<TrainerProfile>() {
            @Override
            public void onChanged(TrainerProfile trainer) {
                if(trainer == null)
                {
                    return;
                }

                tvTrainerFullName.setText(trainer.getFullName());
                tvTrainerEmail.setText(trainer.getEmail());
                tvDegreeURL.setText(trainer.getDegreeUrl());
                tvCvURL.setText(trainer.getCvUrl());
            }
        });
    }

    @OnClick(R.id.btnSend)
    public void sendRequestToTrainer()
    {
        if(InfoHolder.user.getType() == UserType.User.id) {
            MutableLiveData<RegisterResponse> sendRequest = ApiRequester.sendTrainerRequest(new SendTrainerRequest(InfoHolder.user.getId(), trainerID));
            sendRequest.observe(this, new Observer<RegisterResponse>() {
                @Override
                public void onChanged(RegisterResponse registerResponse) {
                    if (registerResponse == RegisterResponse.OK) {

                        Utilities.createAndShowAlertDialog(TrainerProfileActivity.this, "Informacije", "Uspješno ste poslali zahtjev");
                    } else if (registerResponse == RegisterResponse.EmailTaken) {
                        Utilities.createAndShowAlertDialog(TrainerProfileActivity.this, "Informacije", "Nešto je pošlo po krivu. Pokušajte ponovno.");
                    } else {
                        Utilities.createAndShowAlertDialog(TrainerProfileActivity.this, "Informacije", "Nešto je pošlo po krivu. Pokušajte ponovno.");
                    }

                }
            });
        }
        else
        {
            MutableLiveData<RegisterResponse> acceptRequest = ApiRequester.acceptUserRequest(new AcceptUserRequest(trainerID, InfoHolder.user.getId()));
            acceptRequest.observe(this, new Observer<RegisterResponse>() {
                @Override
                public void onChanged(RegisterResponse registerResponse) {
                    if (registerResponse == RegisterResponse.OK) {

                        Utilities.createAndShowAlertDialog(TrainerProfileActivity.this, "Informacije", "Uspješno ste prihvatili zahtjev korisnika.");
                    } else if (registerResponse == RegisterResponse.EmailTaken) {
                        Utilities.createAndShowAlertDialog(TrainerProfileActivity.this, "Informacije", "Nešto je pošlo po krivu. Pokušajte ponovno.");
                    } else {
                        Utilities.createAndShowAlertDialog(TrainerProfileActivity.this, "Informacije", "Nešto je pošlo po krivu. Pokušajte ponovno.");
                    }

                }
            });
        }
    }
}