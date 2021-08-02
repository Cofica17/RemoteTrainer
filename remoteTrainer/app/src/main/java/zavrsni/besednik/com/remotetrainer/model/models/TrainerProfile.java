package zavrsni.besednik.com.remotetrainer.model.models;

import com.google.gson.annotations.SerializedName;

public class TrainerProfile {
    @SerializedName("FirstName")
    private String firstName;
    @SerializedName("LastName")
    private String lastName;
    @SerializedName("Email")
    private String email;
    @SerializedName("IDTrainer")
    private int id;
    @SerializedName("ProfileImage")
    private byte[] profileImage;
    @SerializedName("DegreeURL")
    private String degreeUrl;
    @SerializedName("CvURL")
    private String cvUrl;

    public byte[] getProfileImage() {
        return profileImage;
    }

    public String getDegreeUrl() {
        return degreeUrl;
    }

    public String getCvUrl() {
        return cvUrl;
    }

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
