package com.example.diseaseidentifier;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class Diabetes extends AppCompatActivity {

    private String prediction_result;
    private Button predict_btn;
    private RequestQueue mQueue;
    private EditText glucose, insulin, blood_pressure, pregnancy, bmi, diabetic_pedigree, skin_thickness,age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diabetes);

        predict_btn = (Button) findViewById(R.id.predict_button);
        mQueue = Volley.newRequestQueue(this);

        glucose = (EditText) findViewById(R.id.glucose_level);
        insulin = (EditText) findViewById(R.id.insulin_level);
        blood_pressure = (EditText) findViewById(R.id.blood_pressure);
        pregnancy = (EditText) findViewById(R.id.pregnancy);
        bmi = (EditText) findViewById(R.id.bmi);
        diabetic_pedigree = (EditText) findViewById(R.id.pedigree_function);
        skin_thickness = (EditText) findViewById(R.id.skin_thickness);
        age = (EditText) findViewById(R.id.age);

        predict_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                JSONObject object = new JSONObject();
                try {
                    object.put("pregnancies",Integer.parseInt(pregnancy.getText().toString()));
                    object.put("glucose",glucose.getText().toString());
                    object.put("bloodpressure",blood_pressure.getText().toString());
                    object.put("skinthickness",skin_thickness.getText().toString());
                    object.put("insulin",insulin.getText().toString());
                    object.put("bmi",bmi.getText().toString());
                    object.put("diabetespedigreefunction",diabetic_pedigree.getText().toString());
                    object.put("age",age.getText().toString());
                }catch (Exception error){
                    error.printStackTrace();
                }
                jsonParse(object);
            }
        });
    }

    private void jsonParse(JSONObject object){
        String url = "http://192.168.1.7:5000/diabetes";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    prediction_result = response.get("result").toString();

                    if(prediction_result!=null && !prediction_result.equals("")){
                        if(prediction_result.equals("0")){
                            Intent intent = new Intent(Diabetes.this,Prediction.class);
                            intent.putExtra("result","non diabetic");
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(Diabetes.this,Prediction.class);
                            intent.putExtra("result","diabetic");
                            startActivity(intent);
                        }
                    }


                } catch (JSONException e) {
                    Toast.makeText(Diabetes.this,"Error getting result",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Diabetes.this,"Error making prediction",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }


}