package com.lenncode.sesion_03.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConeccitontRest {
    //METODO SINGLETOn
    private static final String URL_JSON_PLACE_HOLDER = "https://jsonplaceholder.typicode.com/";
    private static final String URL_JSON_REST_COUNTRIES = "https://restcountries.com/v2/";
   //Estara en memoria una vez creado
   private static Retrofit retrofitJsonPlace;
    private static Retrofit retrofitRestCountries;

    public static Retrofit getConecctionJsonPlace() {
        if (retrofitJsonPlace == null) {
            retrofitJsonPlace = new Retrofit.Builder().baseUrl(URL_JSON_PLACE_HOLDER)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitJsonPlace;
    }

    public static Retrofit getConecctionCountries() {
        if (retrofitRestCountries == null) {
            retrofitRestCountries = new Retrofit.Builder().baseUrl(URL_JSON_REST_COUNTRIES)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitRestCountries;
    }
}
