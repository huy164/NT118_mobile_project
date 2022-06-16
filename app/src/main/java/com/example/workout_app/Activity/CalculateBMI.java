package com.example.workout_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.workout_app.R;
import com.example.workout_app.Views.start_up_screens.UserRegistration;

import java.text.DecimalFormat;

public class CalculateBMI extends AppCompatActivity implements View.OnClickListener {
    private Button btnCalBMI;
    private EditText inputHeight;
    private EditText inputWeight;
    private EditText inputAge;
    private TextView outputBMI;
    private TextView outputBMR;
    private TextView outputStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
        btnCalBMI = findViewById(R.id.btnCalBMI);
        btnCalBMI.setOnClickListener(this);
        inputHeight = findViewById(R.id.inputHeight);
        inputWeight = findViewById(R.id.inputWeight);
        inputWeight = findViewById(R.id.inputAge);
        outputBMI=findViewById(R.id.outputBMI);
        outputBMR=findViewById(R.id.outputBMR);
        outputStatus=findViewById(R.id.outputStatus);

    }

    protected double calculateBMI(double height, double weight) {
        height=height/100;
        double bmi = 1.0* weight / (height * height);
        return bmi;
    }
    protected double calculateBMR(double height, double weight,int age) {
        height=height/100;
        double bmi = 1.0* weight / (height * height);
        return bmi;
    }

    protected void ShowResult(){
        double height = Double.parseDouble(inputHeight.getText().toString());
        double weight = Double.parseDouble(inputWeight.getText().toString());
        double bmi=calculateBMI(height,weight);
        DecimalFormat df = new DecimalFormat("0.00");
        outputBMI.setText(df.format(bmi));
        String status="";
        if(bmi<18.5)  status="Underweight";
        if(bmi>=18.5&&bmi<25)  status="Normal";
        if(bmi>=25&&bmi<30)  status="Overweight";
        if(bmi>=30)  status="Obesity";
        outputStatus.setText(status);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCalBMI:
               ShowResult();
                break;
        }
    }
}