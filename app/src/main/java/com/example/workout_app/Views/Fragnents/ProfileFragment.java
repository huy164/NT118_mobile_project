package com.example.workout_app.Views.Fragnents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.workout_app.R;
import com.example.workout_app.ShowDetailFavoriteFood;
import com.example.workout_app.Views.CalculateHeartRate;
import com.example.workout_app.Views.start_up_screens.Login;
import com.example.workout_app.Views.start_up_screens.UserRegistration;
import com.example.workout_app.pedometer.PedometerMainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends  Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnCalHeartRate;
    private  Button btncountsteps;
    private ImageView Logout;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView About, showfood, uEmail, uName, Report;
    private FirebaseFirestore mStore;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
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
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        btnCalHeartRate = (Button)rootView.findViewById(R.id.btnCalHeartRate);
        btncountsteps =(Button)rootView.findViewById(R.id.btncounting_steps);

        init(rootView);
//        animation();
        SignOut(rootView);

        btnCalHeartRate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                        startActivity(new Intent(v.getContext(), CalculateHeartRate.class));
            }
        });

        btncountsteps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(v.getContext(), PedometerMainActivity.class));
            }
        });



        /*Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                getActivity().startActivity(intent);
            }
        });*/

        showfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ShowDetailFavoriteFood.class);
                startActivity(intent);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0327403627"));
                startActivity(callIntent);
            }
        });

        return rootView;
    }


    private void init(View view) {
        Logout = view.findViewById(R.id.Logout);
        About = view.findViewById(R.id.About);
        showfood = view.findViewById(R.id.tv_favorite_food);
        uEmail = view.findViewById(R.id.UserGmail);
        uName = view.findViewById(R.id.UserName);
        Report = view.findViewById(R.id.Report);

        mStore = FirebaseFirestore.getInstance();

        DocumentReference docRef = mStore.collection("user").document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            uEmail.setText(documentSnapshot.getString("email"));
            uName.setText(documentSnapshot.getString("fullName"));
        }).addOnFailureListener(e -> Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    /*private void animation() {
        Logout.setTranslationX(300);
        showfood.setTranslationY(800);
//        Notify.setTranslationY(800);
        Report.setTranslationY(800);
        About.setTranslationY(800);


        Logout.setAlpha(0);
        showfood.setAlpha(0);
//        Notify.setAlpha(0);
        Report.setAlpha(0);
        About.setAlpha(0);

        Logout.animate().translationX(0).alpha(1).setDuration(600).setStartDelay(300).start();
        showfood.animate().translationY(0).alpha(1).setDuration(600).setStartDelay(300).start();
//        Notify.animate().translationY(0).alpha(1).setDuration(600).setStartDelay(300).start();
        Report.animate().translationY(0).alpha(1).setDuration(600).setStartDelay(400).start();
        About.animate().translationY(0).alpha(1).setDuration(600).setStartDelay(500).start();
    }*/

    private void SignOut(View view) {
        GoogleCreateRequest();
        mAuth = FirebaseAuth.getInstance();
        Logout.setOnClickListener(v -> {
            mAuth.signOut();
            mGoogleSignInClient.revokeAccess();
            mGoogleSignInClient.signOut();
            startActivity(new Intent(v.getContext(), Login.class));
        });
    }

    private void GoogleCreateRequest() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(Objects.requireNonNull(getActivity()).getApplicationContext(), gso);
    }


}