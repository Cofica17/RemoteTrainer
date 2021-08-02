package zavrsni.besednik.com.remotetrainer.model.requests;

public class AcceptUserRequest {
    int userID;
    int trainerID;

    public AcceptUserRequest(int userID, int trainerID) {
        this.userID = userID;
        this.trainerID = trainerID;
    }
}
