package zavrsni.besednik.com.remotetrainer.model.requests;

import java.util.List;

public class TrainingRequest {
    String name;
    String description;
    int TrainerID;

    public TrainingRequest(String name, String description, int trainerID, List<Integer> exercisesIDs) {
        this.name = name;
        this.description = description;
        TrainerID = trainerID;
        this.exercisesIDs = exercisesIDs;
    }

    List<Integer> exercisesIDs;
}
