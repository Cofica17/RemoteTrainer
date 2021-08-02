package zavrsni.besednik.com.remotetrainer.model.requests;

public class ExerciseRequest {
    String Name;
    String Description;
    String VideoName;
    String VideoPath;
    int TrainerID;

    public ExerciseRequest(String name, String description, String videoName, String videoPath, int trainerID) {
        Name = name;
        Description = description;
        VideoName = videoName;
        VideoPath = videoPath;
        TrainerID = trainerID;
    }
}
