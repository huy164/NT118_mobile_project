package com.example.workout_app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workout_app.Adapters.ExSuggestionAdapter;
import com.example.workout_app.Interface.RecyclerViewClickInterface;
import com.example.workout_app.Models.Exercises;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ShowDetailFavoriteExercises extends AppCompatActivity implements RecyclerViewClickInterface {
    private RecyclerView rv_list_ex;
    private FirebaseFirestore db;
    private String currentID;
    private final String TAG = "detailfavorite";
    private ExSuggestionAdapter adapter;
    ArrayList<String> lsExID = new ArrayList<>();
    ArrayList<Exercises> originList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_show_detail_favorite_exercises);
        currentID = FirebaseAuth.getInstance().getUid();
        rv_list_ex = (RecyclerView)findViewById(R.id.rv_favorite_ex_list);
        getFavoriteEx();
        showExList();
    }

    private void getFavoriteEx(){
        db = FirebaseFirestore.getInstance();
        db.collection("user")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if(currentID.equals(document.getId())){
                                ArrayList<String> lstemp = (ArrayList<String>)document.get("FavoriteExercises");
                                if(lstemp.size() != 0){
                                    for(int i = lstemp.size() - 1; i >= 0; i--){
                                        lsExID.add(lstemp.get(i));
                                    }
                                }
                            }
                        }
                    } else {
                        Log.e("FIRE STORE", "Error getting documents: ", task.getException());
                    }
                });
    }



    private void showExList(){
        db = FirebaseFirestore.getInstance();
        db.collection("exercise")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            for(String i : lsExID){
                                if(i.equals(document.getId())){
                                    String Title = document.getString("exercise-name");
                                    //String Description = document.getString("description");
                                    String ImgUrl = document.getString("image");
                                    String Source = document.getString("url");
                                    Exercises temp = new Exercises(Title, ImgUrl, Source);
                                    temp.setId(i);
                                    originList.add(temp);
                                }
                            }
                        }

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        rv_list_ex.setLayoutManager(linearLayoutManager);

                        adapter = new ExSuggestionAdapter(ShowDetailFavoriteExercises.this, originList, this);
                        rv_list_ex.setAdapter(adapter);

                    } else {
                        Log.d(TAG, "getFoodData: ", task.getException());
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(originList.get(position).getUrl()));
        startActivity(openLinkIntent);
    }

    @Override
    public void onLongItemClick(int position) {
        String ExID = originList.get(position).getId();
        String UserID = FirebaseAuth.getInstance().getUid();

        DocumentReference docRef = db.collection("user").document(UserID);
        docRef.update("FavoriteExercises", FieldValue.arrayRemove(ExID));

        Toast.makeText(this, "Đã xóa khỏi danh sách yêu thích", Toast.LENGTH_SHORT).show();

        recreate();
    }
}
