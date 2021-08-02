package zavrsni.besednik.com.remotetrainer.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Training {
    @SerializedName("IDTraining")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;
    @SerializedName("Exercises")
    private List<Exercise> exercises;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public Training(int id, String name, String description, List<Exercise> exercises) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.exercises = exercises;
    }
}
