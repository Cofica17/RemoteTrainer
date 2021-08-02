package zavrsni.besednik.com.remotetrainer.api;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zavrsni.besednik.com.remotetrainer.model.enums.RegisterResponse;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.HttpCodes;
import zavrsni.besednik.com.remotetrainer.model.models.Schedule;
import zavrsni.besednik.com.remotetrainer.model.models.Trainer;
import zavrsni.besednik.com.remotetrainer.model.models.TrainerProfile;
import zavrsni.besednik.com.remotetrainer.model.models.Training;
import zavrsni.besednik.com.remotetrainer.model.models.User;
import zavrsni.besednik.com.remotetrainer.model.requests.AcceptUserRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.ExerciseRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.LoginRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.RegisterRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.ScheduleForUserRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.SendTrainerRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.TrainingRequest;

public class ApiRequester {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit == null)
        {
            retrofit =  new Retrofit.Builder()
                    .baseUrl("http://192.168.100.47:80/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static MutableLiveData<User> login(LoginRequest loginRequest){
        Log.d("TAG: ", getRetrofit().baseUrl()+"");
        MutableLiveData<User> user = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<User> call = webServiceAPI.login(loginRequest);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                user.setValue(null);
            }

        });

        return user;
    }

    public static MutableLiveData<RegisterResponse> register(RegisterRequest registerRequest){
        MutableLiveData<RegisterResponse> registerResponse = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Void> call = webServiceAPI.register(registerRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == HttpCodes.OK){
                    registerResponse.setValue(RegisterResponse.OK);
                }
                else if(response.code() == HttpCodes.CONFLICT){
                    registerResponse.setValue(RegisterResponse.EmailTaken);
                }
                else{
                    registerResponse.setValue(RegisterResponse.Error);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
               registerResponse.setValue(RegisterResponse.Error);
            }

        });

        return registerResponse;
    }

    public static MutableLiveData<Schedule> getScheduleForUser(ScheduleForUserRequest request){
        MutableLiveData<Schedule> schedule = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Schedule> call = webServiceAPI.getScheduleForUser(request);

        call.enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    schedule.setValue(response.body());
                }
                else
                {
                   schedule.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable t) {
                schedule.setValue(null);
            }

        });

        return schedule;
    }

    public static MutableLiveData<Training> getTraining(int id){
        MutableLiveData<Training> training = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Training> call = webServiceAPI.getTraining(id);

        call.enqueue(new Callback<Training>() {
            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    training.setValue(response.body());
                }
                else{
                    training.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Training> call, Throwable t) {
                training.setValue(null);
            }
        });

        return training;
    }

    public static MutableLiveData<Exercise> getExercise(int id){
        MutableLiveData<Exercise> exercise = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Exercise> call = webServiceAPI.getExercise(id);

        call.enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    exercise.setValue(response.body());
                }
                else{
                    exercise.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                exercise.setValue(null);
            }
        });

        return exercise;
    }

    public static MutableLiveData<List<Trainer>> getTrainers(){
        MutableLiveData<List<Trainer>> trainers = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<List<Trainer>> call = webServiceAPI.getTrainers();

        call.enqueue(new Callback<List<Trainer>>() {
            @Override
            public void onResponse(Call<List<Trainer>> call, Response<List<Trainer>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    trainers.setValue(response.body());
                }
                else{
                    trainers.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Trainer>> call, Throwable t) {
               trainers.setValue(null);
            }
        });

        return trainers;
    }

    public static MutableLiveData<TrainerProfile> getTrainerProfile(int id){
        MutableLiveData<TrainerProfile> trainerProfile = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<TrainerProfile> call = webServiceAPI.getTrainerProfile(id);

        call.enqueue(new Callback<TrainerProfile>() {
            @Override
            public void onResponse(Call<TrainerProfile> call, Response<TrainerProfile> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    trainerProfile.setValue(response.body());
                }
                else{
                    trainerProfile.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<TrainerProfile> call, Throwable t) {
                trainerProfile.setValue(null);
            }
        });

        return trainerProfile;
    }


    public static MutableLiveData<List<User>> getUsersForTrainer(int id){
        MutableLiveData<List<User>> users = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<List<User>> call = webServiceAPI.getUsersForTrainer(id);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    users.setValue(response.body());
                }
                else{
                    users.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                users.setValue(null);
            }
        });

        return users;
    }


    public static MutableLiveData<RegisterResponse> newExercise(ExerciseRequest exerciseRequest) {
        MutableLiveData<RegisterResponse> exerciseResponse = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Void> call = webServiceAPI.newExercise(exerciseRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == HttpCodes.OK) {
                    exerciseResponse.setValue(RegisterResponse.OK);
                } else if (response.code() == HttpCodes.CONFLICT) {
                    exerciseResponse.setValue(RegisterResponse.EmailTaken);
                } else {
                    exerciseResponse.setValue(RegisterResponse.Error);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                exerciseResponse.setValue(RegisterResponse.Error);
            }

        });

        return exerciseResponse;
    }

    public static MutableLiveData<List<Exercise>> getExercisesForTrainer(int trainerID){
        MutableLiveData<List<Exercise>> exercises = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<List<Exercise>> call = webServiceAPI.getExercisesForTrainer(trainerID);

        call.enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    exercises.setValue(response.body());
                }
                else{
                    exercises.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                exercises.setValue(null);
            }
        });

        return exercises;
    }

    public static MutableLiveData<List<Schedule.Training>> getTrainingsForTrainer(int trainerID){
        MutableLiveData<List<Schedule.Training>> trainings = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<List<Schedule.Training>> call = webServiceAPI.getTrainingsForTrainer(trainerID);

        call.enqueue(new Callback<List<Schedule.Training>>() {
            @Override
            public void onResponse(Call<List<Schedule.Training>> call, Response<List<Schedule.Training>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    trainings.setValue(response.body());
                }
                else{
                    trainings.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Schedule.Training>> call, Throwable t) {
                trainings.setValue(null);
            }
        });

        return trainings;
    }

    public static MutableLiveData<RegisterResponse> newTraining(TrainingRequest request) {
        MutableLiveData<RegisterResponse> trainingResponse = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Void> call = webServiceAPI.newTraining(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == HttpCodes.OK) {
                    trainingResponse.setValue(RegisterResponse.OK);
                } else if (response.code() == HttpCodes.CONFLICT) {
                    trainingResponse.setValue(RegisterResponse.EmailTaken);
                } else {
                    trainingResponse.setValue(RegisterResponse.Error);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                trainingResponse.setValue(RegisterResponse.Error);
            }

        });

        return trainingResponse;
    }

    public static MutableLiveData<RegisterResponse> sendTrainerRequest(SendTrainerRequest request) {
        MutableLiveData<RegisterResponse> reqResponse = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Void> call = webServiceAPI.sendTrainerRequest(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == HttpCodes.OK) {
                    reqResponse.setValue(RegisterResponse.OK);
                } else if (response.code() == HttpCodes.CONFLICT) {
                    reqResponse.setValue(RegisterResponse.EmailTaken);
                } else {
                    reqResponse.setValue(RegisterResponse.Error);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                reqResponse.setValue(RegisterResponse.Error);
            }

        });

        return reqResponse;
    }

    public static MutableLiveData<List<User>> getUsersRequests(int id){
        MutableLiveData<List<User>> users = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<List<User>> call = webServiceAPI.getUsersRequests(id);

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    users.setValue(response.body());
                }
                else{
                    users.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                users.setValue(null);
            }
        });

        return users;
    }

    public static MutableLiveData<RegisterResponse> acceptUserRequest(AcceptUserRequest request) {
        MutableLiveData<RegisterResponse> reqResponse = new MutableLiveData<>();
        WebServiceAPI webServiceAPI = getRetrofit().create(WebServiceAPI.class);
        Call<Void> call = webServiceAPI.acceptUserRequest(request);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == HttpCodes.OK) {
                    reqResponse.setValue(RegisterResponse.OK);
                } else if (response.code() == HttpCodes.CONFLICT) {
                    reqResponse.setValue(RegisterResponse.EmailTaken);
                } else {
                    reqResponse.setValue(RegisterResponse.Error);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                reqResponse.setValue(RegisterResponse.Error);
            }

        });

        return reqResponse;
    }

}
