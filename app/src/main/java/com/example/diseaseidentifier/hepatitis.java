package com.example.diseaseidentifier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;


public class hepatitis extends AppCompatActivity {

    private String prediction_result;
    private EditText age, gender, alb, alp, alt, ast, bil, che, chol, crea, ggt,prot;
    private Button predict_btn;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hepatitis);

        predict_btn = (Button) findViewById(R.id.predict_button);
        mQueue = Volley.newRequestQueue(this);

        age = (EditText) findViewById(R.id.age);
        gender = (EditText) findViewById(R.id.gender);
        alb = (EditText) findViewById(R.id.alb);
        alp = (EditText) findViewById(R.id.alp);
        alt = (EditText) findViewById(R.id.alt);
        ast = (EditText) findViewById(R.id.ast);
        bil = (EditText) findViewById(R.id.bil);
        che = (EditText) findViewById(R.id.che);
        chol = (EditText) findViewById(R.id.chol);
        crea = (EditText) findViewById(R.id.crea);
        ggt = (EditText) findViewById(R.id.ggt);
        prot = (EditText) findViewById(R.id.prot);

        predict_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject object = new JSONObject();
                try {

                    object.put("age",age.getText().toString());
                    object.put("gender",gender.getText().toString().toLowerCase().charAt(0)=='m'?"0":"1");
                    object.put("alb",alb.getText().toString());
                    object.put("alp",alp.getText().toString());
                    object.put("alt",alt.getText().toString());
                    object.put("ast",ast.getText().toString());
                    object.put("bil",bil.getText().toString());
                    object.put("che",che.getText().toString());
                    object.put("chol",chol.getText().toString());
                    object.put("crea",crea.getText().toString());
                    object.put("ggt",ggt.getText().toString());
                    object.put("prot",prot.getText().toString());

                }catch (Exception error){
                    error.printStackTrace();
                }

                System.out.println(object);
                jsonParse(object);
            }
        });
    }

    private void jsonParse(JSONObject object){
        String url = "http://192.168.1.7:5000/hepatitis";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    prediction_result = response.get("result").toString();

                    if(prediction_result!=null && !prediction_result.equals("")){
                        if(prediction_result.equals("0")){
                            Intent intent = new Intent(hepatitis.this,Prediction.class);
                            intent.putExtra("result","normal hepatitis");
                            startActivity(intent);
                        }else if(prediction_result.equals("1")){
                            Intent intent = new Intent(hepatitis.this,Prediction.class);
                            intent.putExtra("result","hepatitis");
                            startActivity(intent);
                        }else if(prediction_result.equals("2")){
                            Intent intent = new Intent(hepatitis.this,Prediction.class);
                            intent.putExtra("result","fibrosis");
                            startActivity(intent);
                        }else if(prediction_result.equals("3")){
                            Intent intent = new Intent(hepatitis.this,Prediction.class);
                            intent.putExtra("result","cirrhosis");
                            startActivity(intent);
                        }else if(prediction_result.equals("4")){
                            Intent intent = new Intent(hepatitis.this,Prediction.class);
                            intent.putExtra("result","suspect");
                            startActivity(intent);
                        }
                    }

                } catch (JSONException e) {
                    Toast.makeText(hepatitis.this,"Error getting result",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(hepatitis.this,"Error making prediction",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }
}