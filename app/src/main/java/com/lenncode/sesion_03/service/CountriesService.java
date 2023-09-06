package com.lenncode.sesion_03.service;

import com.lenncode.sesion_03.entity.Countries;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountriesService {
    @GET("all")
    public abstract Call<List<Countries>>listCountries();

}
