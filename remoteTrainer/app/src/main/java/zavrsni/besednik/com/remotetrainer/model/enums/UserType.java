package zavrsni.besednik.com.remotetrainer.model.enums;

public enum UserType {
    User (1),
    Trainer (2);

    public final int id;

    UserType(int type)
    {
        this.id = type;
    }
}
