package com.example.workout_app.Views.Fragnents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.workout_app.R;
import com.example.workout_app.ShowDetailFavoriteExercises;
import com.example.workout_app.ShowDetailFavoriteFood;
import com.example.workout_app.Views.start_up_screens.Login;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RouteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteFragment extends  Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    private Button btnCalHeartRate;
    private ImageView Logout;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView About, showfood, uEmail, uName,uPhone ,showEx1,showEx2,showEx3,Report;
    private FirebaseFirestore mStore;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RouteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteFragment newInstance(String param1, String param2) {
        RouteFragment fragment = new RouteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_route, container, false);
//        btnCalHeartRate = (Button)rootView.findViewById(R.id.btnCalHeartRate);
//        btncountsteps =(Button)rootView.findViewById(R.id.btncounting_steps);
        showEx1 = rootView.findViewById(R.id.tv_route1);
        showEx2 = rootView.findViewById(R.id.tv_route2);
        showEx3 = rootView.findViewById(R.id.tv_route3);

//        init(rootView);

        showEx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowDetailFavoriteExercises.class);
                startActivity(intent);
            }
        });
        showEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowDetailFavoriteExercises.class);
                startActivity(intent);
            }
        });

        showEx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowDetailFavoriteExercises.class);
                startActivity(intent);
            }
        });



        return rootView;
    }


    /*private void init(View view) {
        *//*Logout = view.findViewById(R.id.Logout);
        About = view.findViewById(R.id.About);*//*
        *//*showfood = view.findViewById(R.id.tv_favorite_food);*//*
        showEx1 = view.findViewById(R.id.tv_route1);
        showEx2 = view.findViewById(R.id.tv_route2);
        showEx3 = view.findViewById(R.id.tv_route3);
//        uEmail = view.findViewById(R.id.email);
//        uName = view.findViewById(R.id.fullname);
//        uPhone = view.findViewById(R.id.phoneNumber);
        //Report = view.findViewById(R.id.Report);
//        mStore = FirebaseFirestore.getInstance();


        *//*DocumentReference docRef = mStore.collection("user").document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            uEmail.setText(documentSnapshot.getString("email"));
            uName.setText(documentSnapshot.getString("fullName"));
            uPhone.setText(documentSnapshot.getString("phoneNumber"));
        }).addOnFailureListener(e -> Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show());*//*
    }*/





}