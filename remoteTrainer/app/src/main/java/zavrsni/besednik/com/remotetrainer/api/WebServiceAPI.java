package zavrsni.besednik.com.remotetrainer.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.Schedule;
import zavrsni.besednik.com.remotetrainer.model.models.Trainer;
import zavrsni.besednik.com.remotetrainer.model.models.TrainerProfile;
import zavrsni.besednik.com.remotetrainer.model.models.Training;
import zavrsni.besednik.com.remotetrainer.model.requests.AcceptUserRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.ExerciseRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.LoginRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.RegisterRequest;
import zavrsni.besednik.com.remotetrainer.model.models.User;
import zavrsni.besednik.com.remotetrainer.model.requests.ScheduleForUserRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.SendTrainerRequest;
import zavrsni.besednik.com.remotetrainer.model.requests.TrainingRequest;

public interface WebServiceAPI {

    @POST("api/login")
    Call<User> login(@Body LoginRequest loginRequest);
    @POST("api/register")
    Call<Void> register(@Body RegisterRequest registerRequest);
    @POST("api/getScheduleForUser")
    Call<Schedule> getScheduleForUser(@Body ScheduleForUserRequest scheduleForUserRequest);
    @POST("api/getTraining")
    Call<Training> getTraining(@Body int id);
    @POST("api/getExercise")
    Call<Exercise> getExercise(@Body int id);
    @GET("api/getTrainers")
    Call<List<Trainer>> getTrainers();
    @POST("api/getTrainerProfile")
    Call<TrainerProfile> getTrainerProfile(@Body int id);
    @POST("api/getUsersForTrainer")
    Call<List<User>> getUsersForTrainer(@Body int id);
    @POST("api/postExercise")
    Call<Void> newExercise(@Body ExerciseRequest exerciseRequest);
    @POST("api/getExercisesForTrainer")
    Call<List<Exercise>> getExercisesForTrainer(@Body int id);
    @POST("api/getTrainingsForTrainer")
    Call<List<Schedule.Training>> getTrainingsForTrainer(@Body int id);
    @POST("api/postTraining")
    Call<Void> newTraining(@Body TrainingRequest request);
    @POST("api/sendTrainerRequest")
    Call<Void> sendTrainerRequest(@Body SendTrainerRequest request);
    @POST("api/getUsersRequests")
    Call<List<User>> getUsersRequests(@Body int id);
    @POST("api/acceptUserRequest")
    Call<Void> acceptUserRequest(@Body AcceptUserRequest request);

}
