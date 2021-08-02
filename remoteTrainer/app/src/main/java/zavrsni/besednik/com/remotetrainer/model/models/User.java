package zavrsni.besednik.com.remotetrainer.model.models;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class User {

    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("Email")
    private String email;
    @SerializedName("Type")
    private int type;
    @SerializedName("IDUser")
    private int id;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getType() {
        return type;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
