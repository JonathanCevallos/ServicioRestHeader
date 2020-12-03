package com.example.serviciorest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private  Button btnVolley;
    private  Button btnRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVolley = (Button) findViewById(R.id.btnVolley);
        btnRetrofit = (Button) findViewById(R.id.btnRetrofit);

        btnVolley.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),ListadoActivity.class);
                startActivity(intent);
            }
        });


        btnRetrofit.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),RetrofitActivity.class);
                startActivity(intent);
            }
        });
    }
}