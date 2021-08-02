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
import zavrsni.besednik.com.remotetrainer.activity.ExercisePreviewActivity;
import zavrsni.besednik.com.remotetrainer.activity.TrainerProfileActivity;
import zavrsni.besednik.com.remotetrainer.model.models.Trainer;
import zavrsni.besednik.com.remotetrainer.model.models.Training;

public class TrainerPreviewAdapter extends RecyclerView.Adapter<TrainerPreviewAdapter.TrainerPreviewHolder> {

    Context context;
    List<Trainer> trainers;

    public TrainerPreviewAdapter(Context context, List<Trainer> trainers) {
        this.context = context;
        this.trainers = trainers;
    }

    @NonNull
    @Override
    public TrainerPreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_trainer_preview, parent, false);
        return new TrainerPreviewAdapter.TrainerPreviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerPreviewHolder holder, int position) {
        Trainer trainer = trainers.get(position);
        holder.tvTrainerName.setText(trainer.getFullName());
        holder.tvTrainerEmail.setText(trainer.getEmail());

        holder.trainerPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainerProfileActivity.class);
                intent.putExtra("trainerID", trainer.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(trainers == null) {
            return 0;
        }

        return trainers.size();
    }

    public class TrainerPreviewHolder extends RecyclerView.ViewHolder{

        ImageView imgTrainer;
        TextView tvTrainerName;
        TextView tvTrainerEmail;
        ConstraintLayout trainerPreview;

        public TrainerPreviewHolder(@NonNull View itemView) {
            super(itemView);
            imgTrainer = itemView.findViewById(R.id.imgTrainer);
            tvTrainerName = itemView.findViewById(R.id.tvTrainerName);
            tvTrainerEmail = itemView.findViewById(R.id.tvTrainerEmail);
            trainerPreview = itemView.findViewById(R.id.trainerPreview);
        }
    }
}
