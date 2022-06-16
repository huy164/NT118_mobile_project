package com.example.workout_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.workout_app.Interface.RecyclerViewClickInterface;
import com.example.workout_app.Models.Exercises;
import com.example.workout_app.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ExSuggestionAdapter extends RecyclerView.Adapter<ExSuggestionAdapter.ExSuggestionViewHolder> {
    private Context context;
    private List<Exercises> suggestionList;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public ExSuggestionAdapter(Context context, List<Exercises> suggestionList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.context = context;
        this.suggestionList = suggestionList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @Override
    public ExSuggestionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_exs_search, parent, false);
        return new ExSuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExSuggestionViewHolder holder, int position) {
        holder.getTitle().setText(suggestionList.get(position).getName());
        //holder.getDescription().setText(suggestionList.get(position).getName());
        Glide.with(context)
                .load(suggestionList.get(position).getImage())
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        if(suggestionList != null){
            return suggestionList.size();
        }
        return 0;
    }

    public class ExSuggestionViewHolder extends RecyclerView.ViewHolder{
        private final TextView Title;
        //private final TextView Description;
        private final ImageView imageView;

        public ExSuggestionViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Name);
            //Description = itemView.findViewById(R.id.Description);
            imageView = itemView.findViewById(R.id.Ex_Image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewClickInterface.onItemClick(getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    recyclerViewClickInterface.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });
        }

        public TextView getTitle() {return Title;}
        //public TextView getDescription() {return Description;}
        public ImageView getImageView() {return imageView;}
    }
}
