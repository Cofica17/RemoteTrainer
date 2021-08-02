package zavrsni.besednik.com.remotetrainer.model.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zavrsni.besednik.com.remotetrainer.R;
import zavrsni.besednik.com.remotetrainer.model.models.Exercise;

public class ChooseExercisePreviewAdapter extends RecyclerView.Adapter<ChooseExercisePreviewAdapter.ExercisePreviewHolder> {

    Context context;
    List<Exercise> exercises;
    public Exercise selectedExercise;

    public ChooseExercisePreviewAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ChooseExercisePreviewAdapter.ExercisePreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.training_exercise_preview, parent, false);
        return new ChooseExercisePreviewAdapter.ExercisePreviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseExercisePreviewAdapter.ExercisePreviewHolder holder, int position) {
        Exercise exercise = exercises.get(position);
        holder.tvEPName.setText(exercise.getName());
        holder.tvEPDescription.setText(exercise.getDescription());

        holder.exercisePreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               selectedExercise = exercise;
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
        CardView exerciseCardView;

        public ExercisePreviewHolder(@NonNull View itemView) {
            super(itemView);
            tvEPName = itemView.findViewById(R.id.tvEPName);
            tvEPDescription = itemView.findViewById(R.id.tvEPDescription);
            exercisePreview = itemView.findViewById(R.id.exercisePreview);
            exerciseCardView = itemView.findViewById(R.id.exerciseCardView);
        }

        @SuppressLint("ResourceAsColor")
        public void setBlueBackground(){
            exerciseCardView.setCardBackgroundColor(R.color.light_blue);
        }

        @SuppressLint("ResourceAsColor")
        public void setNormalBackground(){
            exerciseCardView.setCardBackgroundColor(R.color.white);
        }
    }
}
