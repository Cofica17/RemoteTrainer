package zavrsni.besednik.com.remotetrainer.model.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.activity.TrainingPreviewActivity;
import zavrsni.besednik.com.remotetrainer.model.models.InfoHolder;
import zavrsni.besednik.com.remotetrainer.model.models.Schedule;

public class TrainingPreviewAdapter extends RecyclerView.Adapter<TrainingPreviewAdapter.TrainingPreviewHolder> {

    Context context;
    List<Schedule.Training> trainings;

    public TrainingPreviewAdapter(Context context, List<Schedule.Training> trainings)
    {
        this.context = context;
        this.trainings = trainings;
    }

    @NonNull
    @Override
    public TrainingPreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sample_training_preview, parent, false);
        return new TrainingPreviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingPreviewHolder holder, int position) {
        Schedule.Training training = trainings.get(position);
        holder.tvTrainingName.setText(training.getName());

        if(training.getTimeOfDay() != null) {
            holder.tvTimeOfDay.setText(training.getTimeOfDay());
            switch (training.getTimeOfDay()) {
                case InfoHolder.TIME_OF_DAY_MORNING:
                    holder.image.setImageResource(R.drawable.ic_cardio);
                    break;
                case InfoHolder.TIME_OF_DAY_MIDDAY:
                    holder.image.setImageResource(R.drawable.ic_bodweight);
                    break;
                case InfoHolder.TIME_OF_DAY_EVENING:
                    holder.image.setImageResource(R.drawable.ic_bodweight);
                    break;
            }
        }
        else{
            holder.image.setImageResource(R.drawable.ic_rest);
        }

        holder.trainingPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingPreviewActivity.class);
                intent.putExtra("trainingID", training.getId());
                intent.putExtra("trainingName", training.getName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (trainings == null)
        {
            return 0;
        }
        return trainings.size();
    }

    public class TrainingPreviewHolder extends RecyclerView.ViewHolder {

        TextView tvTimeOfDay;
        TextView tvTrainingName;
        ImageView image;
        ConstraintLayout trainingPreview;

        public TrainingPreviewHolder(@NonNull View itemView) {
            super(itemView);
            tvTimeOfDay = itemView.findViewById(R.id.tvTimeOfDay);
            tvTrainingName = itemView.findViewById(R.id.tvWorkoutName);
            image = itemView.findViewById(R.id.imgTraining);
            trainingPreview = itemView.findViewById(R.id.trainingPreview);
        }
    }
}
