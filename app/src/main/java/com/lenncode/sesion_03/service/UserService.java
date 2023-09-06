package com.lenncode.sesion_03.service;

import com.lenncode.sesion_03.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    public abstract Call<List<User>>listaUser();

}
