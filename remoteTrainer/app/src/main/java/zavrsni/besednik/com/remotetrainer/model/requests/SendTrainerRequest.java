package zavrsni.besednik.com.remotetrainer.model.requests;

public class SendTrainerRequest {
    int userID;
    int trainerID;

    public SendTrainerRequest(int userID, int trainerID) {
        this.userID = userID;
        this.trainerID = trainerID;
    }
}
