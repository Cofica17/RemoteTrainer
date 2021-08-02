package zavrsni.besednik.com.remotetrainer.model.requests;

import java.util.Date;

public class ScheduleForUserRequest {
    public ScheduleForUserRequest(int iDUser, String todayDate) {
        this.iDUser = iDUser;
        this.todayDate = todayDate;
    }

    private int iDUser;
    private String todayDate;
}
