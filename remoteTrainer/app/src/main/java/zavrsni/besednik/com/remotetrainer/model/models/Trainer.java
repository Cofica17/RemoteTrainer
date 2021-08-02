package zavrsni.besednik.com.remotetrainer.model.models;

import com.google.gson.annotations.SerializedName;

public class Trainer {

    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("Email")
    private String email;
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

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
