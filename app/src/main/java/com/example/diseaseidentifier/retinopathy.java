package com.example.diseaseidentifier;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.net.URI;

public class retinopathy extends AppCompatActivity {

    private ImageView retinaImage;
    private Button selectImage, predict;
    private int SELECT_IMAGE_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retinopathy);

        retinaImage = (ImageView) findViewById(R.id.select_retina_image);
        selectImage = (Button) findViewById(R.id.select_image_button);
        predict = (Button) findViewById(R.id.predict_button);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"title"),SELECT_IMAGE_CODE);
            }
        });

        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(retinopathy.this,Prediction.class);
                intent.putExtra("result","prediction");
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 0){

        }
        else if(requestCode == 1){
            System.out.println("RESULT CODE ______________________"+resultCode);
            Uri uri = data.getData();
            retinaImage.setImageURI(uri);
            selectImage.setVisibility(View.INVISIBLE);
            predict.setVisibility(View.VISIBLE);
        }
    }
}