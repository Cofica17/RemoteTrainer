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
import zavrsni.besednik.com.remotetrainer.activity.TrainerProfileActivity;
import zavrsni.besednik.com.remotetrainer.model.models.Trainer;
import zavrsni.besednik.com.remotetrainer.model.models.User;

public class MyUsersPreviewAdapter extends RecyclerView.Adapter<MyUsersPreviewAdapter.MyUsersPreviewHolder> {

    Context context;
    List<User> myUsers;

    public MyUsersPreviewAdapter(Context context, List<User> myUsers) {
        this.context = context;
        this.myUsers = myUsers;
    }

    @NonNull
    @Override
    public MyUsersPreviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_users_preview, parent, false);
        return new MyUsersPreviewAdapter.MyUsersPreviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyUsersPreviewHolder holder, int position) {
       User user = myUsers.get(position);
        holder.tvUserName.setText(user.getFullName());
        holder.tvUserEmail.setText(user.getEmail());

        holder.myUsersPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainerProfileActivity.class);
                intent.putExtra("trainerID", user.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(myUsers == null) {
            return 0;
        }

        return myUsers.size();
    }

    public class MyUsersPreviewHolder extends RecyclerView.ViewHolder {

        ImageView imgUser;
        TextView tvUserName;
        TextView tvUserEmail;
        ConstraintLayout myUsersPreview;

        public MyUsersPreviewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.imgUser);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvUserEmail = itemView.findViewById(R.id.tvUserEmail);
            myUsersPreview = itemView.findViewById(R.id.usersPreview);
        }
    }
}
