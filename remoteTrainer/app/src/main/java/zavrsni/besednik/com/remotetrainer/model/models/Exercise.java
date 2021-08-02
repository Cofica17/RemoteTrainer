package zavrsni.besednik.com.remotetrainer.model.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Exercise implements Serializable {
    @SerializedName("IDExercise")
    private int id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;
    @SerializedName("Video")
    private Video video;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Video getVideo() {
        return video;
    }

    public class Video implements Serializable {
        @SerializedName("Name")
        private String name;
        @SerializedName("Path")
        private String path;

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }
    }
}
