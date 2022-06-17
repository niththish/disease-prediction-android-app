package com.example.diseaseidentifier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout diabetes, hepatitis, retinopathy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diabetes = (RelativeLayout) findViewById(R.id.view1);
        hepatitis = (RelativeLayout) findViewById(R.id.view2);
        retinopathy = (RelativeLayout) findViewById(R.id.view3);

        diabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diabetesIntent = new Intent(MainActivity.this, Diabetes.class);
                startActivity(diabetesIntent);
            }
        });

        hepatitis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hepatitisIntent = new Intent(MainActivity.this, hepatitis.class);
                startActivity(hepatitisIntent);
            }
        });

        retinopathy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent retinopathyIntent = new Intent(MainActivity.this,retinopathy.class);
                startActivity(retinopathyIntent);
            }
        });
    }
}