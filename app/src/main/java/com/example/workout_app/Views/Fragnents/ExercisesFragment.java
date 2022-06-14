package com.example.workout_app.Views.Fragnents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_app.Adapters.ExercisesAdapter;
import com.example.workout_app.Interface.RecyclerViewClickInterface;
import com.example.workout_app.Models.Exercises;
import com.example.workout_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ExercisesFragment extends Fragment implements RecyclerViewClickInterface{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView recyclerView;
    private TextView et_require_searching;
    private FirebaseFirestore db;
    private List<Exercises> list = new ArrayList<>();
    private FirebaseAuth mAuth;

    public ExercisesFragment() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
  /*  public static ExercisesFragment newInstance(String param1, String param2) {
        ExercisesFragment fragment = new ExercisesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        db = FirebaseFirestore.getInstance();
        db.collection("exercise")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String Id = document.getId();
                            String Name = document.getString("exercise-name");
                            String Level = document.getString("level");
                            String Type = document.getString("exercise-type");
                            String Image = document.getString("image");
                            String Url = document.getString("url");

                            list.add(new Exercises(Id,Name,Level,Type,Image,Url));
                        }
                        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(view.getContext(), R.anim.anim_left_to_right);
                        recyclerView.setLayoutAnimation(layoutAnimationController);

                        ExercisesAdapter exercisesAdapter = new ExercisesAdapter(view.getContext(),list, (RecyclerViewClickInterface) this);
                        recyclerView.setAdapter(exercisesAdapter);

                        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                        recyclerView.setLayoutManager(gridLayoutManager);


                    } else {
                        Log.e("FIRE STORE", "Error getting documents: ", task.getException());
                    }
                });

    }



    @Override
    public void onItemClick(int position) {
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getUrl()));
        startActivity(openLinkIntent);
    }

    @Override
    public void onLongItemClick(int position) {
        String ExID = list.get(position).getId();
        String UserID = FirebaseAuth.getInstance().getUid();
        DocumentReference docRef = db.collection("user").document(mAuth.getCurrentUser().getUid().toString());
        docRef.update("FavoriteExercises", FieldValue.arrayUnion(ExID));
        Toast.makeText(getContext(), "Đã thêm bài tập vào danh sách yêu thích", Toast.LENGTH_SHORT).show();
    }
}