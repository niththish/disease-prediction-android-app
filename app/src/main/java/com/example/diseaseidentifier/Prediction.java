package com.example.diseaseidentifier;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Prediction extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);
        String prediction = getIntent().getExtras().getString("result");

        result = (TextView) findViewById(R.id.resultText);

        switch (prediction){
            case "diabetic" : {
                result.setText("The user is diabetic");
                result.setTextColor(Color.RED);
            }
            case "non diabetic" : {
                result.setText("The user is non-diabetic");
                result.setTextColor(Color.GREEN);
            }
            case "normal hepatitis" : {
                result.setText("The user has no hepatitis");
                result.setTextColor(Color.GREEN);
            }
            case "hepatitis" : {
                result.setText("The user has Hepatitis");
                result.setTextColor(Color.RED);
            }
            case "fibrosis" : {
                result.setText("The user has Fibrosis");
                result.setTextColor(Color.RED);
            }
            case "cirrhosis" : {
                result.setText("The user has Cirrhosis");
                result.setTextColor(Color.RED);
            }
            case "suspect" : {
                result.setText("The user data is under suspect");
                result.setTextColor(Color.RED);
            }
        }
    }
}