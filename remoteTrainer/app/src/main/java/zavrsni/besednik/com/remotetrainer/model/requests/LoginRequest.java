package zavrsni.besednik.com.remotetrainer.model.requests;

public class LoginRequest {
    private String email;
    private String pwd;

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public LoginRequest(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
