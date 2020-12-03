package com.example.serviciorest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.serviciorest.Interface.JsonBancos;
import com.example.serviciorest.Model.Bancos;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private TextView txtLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
       txtLista = (TextView) findViewById(R.id.txtLista);
       GETRetrofit();
    }

    private  void GETRetrofit(){
        final ProgressDialog loading = ProgressDialog.show(this, "Por favor espere...", "Actualizando datos",false,false);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/JonathanCevallos/JSON/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonBancos jsonBancos= retrofit.create(JsonBancos.class);
        Call<List<Bancos>> call = jsonBancos.getBancos();
        call.enqueue(new Callback<List<Bancos>>() {
            @Override
            public void onResponse(Call<List<Bancos>> call, retrofit2.Response<List<Bancos>> response) {
                loading.dismiss();
                if(!response.isSuccessful()){
                    txtLista.setText("Codigo"+response.code());
                    return;
                }
                List<Bancos> bancosList = response.body();
                for (Bancos bancos: bancosList){
                    String content = "";
                    content+= bancos.getId() + " ";
                    content+= bancos.getTitle()+ "\n\n";
                    txtLista.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Bancos>> call, Throwable t) {
                txtLista.setText(t.getMessage());
            }
        });
    }
}