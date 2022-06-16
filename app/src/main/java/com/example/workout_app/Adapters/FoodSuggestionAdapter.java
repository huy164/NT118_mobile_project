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
import com.example.workout_app.Models.Dish;
import com.example.workout_app.R;
import com.example.workout_app.Interface.RecyclerViewClickInterface;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FoodSuggestionAdapter extends RecyclerView.Adapter<FoodSuggestionAdapter.FoodSuggestionViewHolder> {
    private Context context;
    private List<Dish> suggestionList;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public FoodSuggestionAdapter(Context context, List<Dish> suggestionList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.context = context;
        this.suggestionList = suggestionList;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @Override
    public FoodSuggestionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_food_search, parent, false);
        return new FoodSuggestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull FoodSuggestionViewHolder holder, int position) {
        holder.getTitle().setText(suggestionList.get(position).getTitle());
        holder.getDescription().setText(suggestionList.get(position).getDescription());
        Glide.with(context)
                .load(suggestionList.get(position).getThumnailUrl())
                .into(holder.getImageView());
    }

    @Override
    public int getItemCount() {
        if(suggestionList != null){
            return suggestionList.size();
        }
        return 0;
    }

    public class FoodSuggestionViewHolder extends RecyclerView.ViewHolder{
        private final TextView Title;
        private final TextView Description;
        private final ImageView imageView;

        public FoodSuggestionViewHolder(View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.Title);
            Description = itemView.findViewById(R.id.Description);
            imageView = itemView.findViewById(R.id.FoodImage);

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
        public TextView getDescription() {return Description;}
        public ImageView getImageView() {return imageView;}
    }
}
