package zavrsni.besednik.com.remotetrainer.model.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.activity.ExercisePreviewActivity;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;
import zavrsni.besednik.com.remotetrainer.model.models.Training;

public class ExercisePreviewAdapter extends RecyclerView.Adapter<ExercisePreviewAdapter.ExercisePreviewHolder> {

    Context context;
    List<Exercise> exercises;

    public ExercisePreviewAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExercisePreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.training_exercise_preview, parent, false);
        return new ExercisePreviewAdapter.ExercisePreviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisePreviewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.tvEPName.setText(exercise.getName());
        holder.tvEPDescription.setText(exercise.getDescription());

        holder.exercisePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExercisePreviewActivity.class);
                intent.putExtra("exerciseID", exercise.getId());
                intent.putExtra("exerciseName", exercise.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(exercises == null) {
            return 0;
        }

        return exercises.size();
    }

    public class ExercisePreviewHolder extends RecyclerView.ViewHolder {
        TextView tvEPName;
        TextView tvEPDescription;
        ConstraintLayout exercisePreview;

        public ExercisePreviewHolder(@NonNull View itemView) {
            super(itemView);
            tvEPName = itemView.findViewById(R.id.tvEPName);
            tvEPDescription = itemView.findViewById(R.id.tvEPDescription);
            exercisePreview = itemView.findViewById(R.id.exercisePreview);
        }
    }
}
