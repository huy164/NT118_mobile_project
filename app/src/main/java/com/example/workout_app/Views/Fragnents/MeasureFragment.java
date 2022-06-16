package com.example.workout_app.Views.Fragnents;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.workout_app.Activity.CalculateBMI;
import com.example.workout_app.R;
import com.example.workout_app.Activity.HeartRateActivity;
import com.example.workout_app.pedometer.PedometerMainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeasureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeasureFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Button btnCountSteps;
    private Button btnCalHeartRate;
    private Button btnCalBMI;


    public MeasureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeasureFragment newInstance(String param1, String param2) {
        MeasureFragment fragment = new MeasureFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_measure, container, false);
        btnCountSteps =(Button)rootView.findViewById(R.id.btnCountStep);
        btnCountSteps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(v.getContext(), PedometerMainActivity.class));
            }
        });

        btnCalHeartRate =(Button)rootView.findViewById(R.id.btnCalHeartRate);
        btnCalHeartRate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(v.getContext(), HeartRateActivity.class));
            }
        });

        btnCalBMI =(Button)rootView.findViewById(R.id.btnCalBMI);
        btnCalBMI.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(v.getContext(), CalculateBMI.class));
            }
        });
        return rootView;
    }
}