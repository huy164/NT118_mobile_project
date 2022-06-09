package com.example.workout_app.Views.Fragnents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout_app.Adapters.DishAdapter;
import com.example.workout_app.Interface.RecyclerViewClickInterface;
import com.example.workout_app.Models.Dish;
import com.example.workout_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class DishFragment extends Fragment implements RecyclerViewClickInterface{

    private RecyclerView recyclerView;
    private TextView et_require_searching;
    private FirebaseFirestore db;
    private List<Dish> list = new ArrayList<>();
    private FirebaseAuth mAuth;


    public DishFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dish, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);

        //*************DO LATER**************
//        et_require_searching = view.findViewById(R.id.et_require_searching);
//
//        et_require_searching.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), SearchFoodActivity.class);
//                startActivity(intent);
//            }
//        });
        //************************************

        db = FirebaseFirestore.getInstance();
        db.collection("dish")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String Id = document.getId();
                            String Title = document.getString("title");
                            String Description = document.getString("description");
                            String ThumnailUrl = document.getString("thumnail_url");
                            String SourceUrl = document.getString("source_url");

                            list.add(new Dish(Id,Title ,Description, ThumnailUrl, SourceUrl));
                        }
                        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(view.getContext(), R.anim.anim_left_to_right);
                        recyclerView.setLayoutAnimation(layoutAnimationController);

                        DishAdapter foodAdapter = new DishAdapter(view.getContext(),list, (RecyclerViewClickInterface) this);
                        recyclerView.setAdapter(foodAdapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                        recyclerView.setLayoutManager(gridLayoutManager);


                    } else {
                        Log.e("FIRE STORE", "Error getting documents: ", task.getException());
                    }
                });
    }


    @Override
    public void onItemClick(int position) {
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getSourceUrl()));
        startActivity(openLinkIntent);
    }

    @Override
    public void onLongItemClick(int position) {
        String FoodID = list.get(position).getId();
        String UserID = FirebaseAuth.getInstance().getUid();
        DocumentReference docRef = db.collection("user").document(mAuth.getCurrentUser().getUid().toString());
        docRef.update("FavoriteFood", FieldValue.arrayUnion(FoodID));
        Toast.makeText(getContext(), "Đã thêm món ăn vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
    }
}