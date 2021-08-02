package zavrsni.besednik.com.remotetrainer.model.models;

import java.util.ArrayList;
import java.util.List;

public class InfoHolder {
    public static User user = new User();
    public static Schedule schedule = new Schedule();

    public static final String TIME_OF_DAY_MORNING = "Morning";
    public static final String TIME_OF_DAY_MIDDAY = "Midday";
    public static final String TIME_OF_DAY_EVENING = "Evening";

    public static final long MILLISECONDS_IN_DAY = 86400000;

    public static List<User> myUsers = new ArrayList<User>();
}
