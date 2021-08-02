package zavrsni.besednik.com.remotetrainer.model.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Schedule {
    public Schedule(int id, String dateFrom, String dateTo, List<Training> trainings) {
        this.id = id;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.trainings = trainings;
    }

    public Schedule(){}

    @SerializedName("ID")
    private int id;
    @SerializedName("DateFrom")
    private String dateFrom;
    @SerializedName("DateTo")
    private String dateTo;
    @SerializedName("Trainings")
    private List<Training> trainings;

    public int getId() {
        return id;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public List<Training> getTrainingsForDate(String date){
        List<Training> trainingsForDate = new ArrayList<Training>();

        for (Training training:trainings) {
           if(training.getDayDate().equals(date))
           {
               trainingsForDate.add(training);
           }
        }

        return trainingsForDate;
    }

    public class Training{
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getTimeOfDay() {
            return timeOfDay;
        }

        public String getDayDate() {
            return dayDate;
        }

        @SerializedName("ID")
        private int id;
        @SerializedName("Name")
        private String name;
        @SerializedName("TimeOfDay")
        private String timeOfDay;
        @SerializedName("DayDate")
        private String dayDate;

    }
}
