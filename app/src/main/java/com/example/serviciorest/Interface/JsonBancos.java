package com.example.serviciorest.Interface;

import com.example.serviciorest.Model.Bancos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface JsonBancos {
    @GET("Bancos")
    Call<List<Bancos>> getBancos();

}
