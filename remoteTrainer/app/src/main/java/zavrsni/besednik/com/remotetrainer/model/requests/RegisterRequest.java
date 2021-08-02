package zavrsni.besednik.com.remotetrainer.model.requests;

import java.sql.Date;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int userTypeId;

    public RegisterRequest(String firstName, String lastName, String email, String pwd, int userTypeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = pwd;
        this.userTypeId = userTypeId;
    }
}
