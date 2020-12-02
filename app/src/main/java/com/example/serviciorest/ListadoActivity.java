package com.example.serviciorest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListadoActivity extends AppCompatActivity {

    private ListView lvDatos;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        lvDatos = (ListView) findViewById(R.id.lvDatos);
        mQueue = Volley.newRequestQueue(this);
        invocarServicio();
    }

    private void invocarServicio(){
        String DATA_URL = "https://api-uat.kushkipagos.com/transfer/v1/bankList";
        final ProgressDialog loading = ProgressDialog.show(this, "Por favor espere...", "Actualizando datos",false,false);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, DATA_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loading.dismiss();
                showListView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                headers.put("name", "public-merchant-id");
                headers.put("Authorization", "84e1d0de1fbf437e9779fd6a52a9ca18");
                return headers;
            }
        };
        mQueue.add(jsonObjectRequest);
    }

    private void showListView(JSONObject obj){
        try {
            List<String> contes = new ArrayList<>();
            JSONArray lista = obj.optJSONArray("contenidos");
            for(int i=0; i<lista.length();i++){
                JSONObject json_data = lista.getJSONObject(i);
                String conte = json_data.getString("code")+" "+ json_data.getString("name");
                contes.add(conte);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contes);
            lvDatos.setAdapter(adapter);

        } catch (Exception ex) {
            Toast.makeText(this,"Error al cargar Lista" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }finally {
        }

    }

}