package com.example.tarea_3.api;

import com.example.tarea_3.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    public abstract Call<List<User>> listaUsuario();

}
