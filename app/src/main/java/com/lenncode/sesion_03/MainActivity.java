package com.lenncode.sesion_03;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.lenncode.sesion_03.entity.Countries;
import com.lenncode.sesion_03.entity.User;
import com.lenncode.sesion_03.service.CountriesService;
import com.lenncode.sesion_03.service.UserService;
import com.lenncode.sesion_03.util.ConeccitontRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Spinner spnUsuarios;
    Spinner spnCountries;

    ArrayAdapter<String> adapterUsuarios;
    ArrayAdapter<String> adapterCountries;
    ArrayList<String> listUsuarios = new ArrayList<String>();

    ArrayList<String> listCountries = new ArrayList<String>();



    UserService userService;
CountriesService countriesService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnUsuarios = findViewById(R.id.spnUsuarios);
        spnCountries = findViewById(R.id.spnPaises);
        adapterUsuarios = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listUsuarios);
        adapterCountries = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listCountries);
        spnUsuarios.setAdapter(adapterUsuarios);
        spnCountries.setAdapter(adapterCountries);
        userService = ConeccitontRest.getConecctionJsonPlace().create(UserService.class);
        countriesService = ConeccitontRest.getConecctionCountries().create(CountriesService.class);
        cargaUser();
        cargaCountries();

    }

    public void cargaUser() {
        Call<List<User>> call = userService.listaUser();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> listSalida = response.body();
                    for (User user : listSalida) {
                        listUsuarios.add(user.getName());
                    }
                    adapterUsuarios.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mensajeAlert(">>>Error>>" + t.getMessage());
            }
        });
    }
    public void cargaCountries() {
        Call<List<Countries>> call = countriesService.listCountries();
        call.enqueue(new Callback<List<Countries>>() {

            @Override
            public void onResponse(Call<List<Countries>> call, Response<List<Countries>> response) {
                if (response.isSuccessful()) {
                    List<Countries> listSalida = response.body();
                    for (Countries cnt : listSalida) {
                        listCountries.add(cnt.getName());
                    }
                    adapterCountries.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {

                mensajeAlert(">>>Error>>" + t.getMessage());
            }
        });
    }

    public void mensajeAlert(String msg) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }


    void mensajeToast(String mensaje) {
        Toast toast1 = Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG);
        toast1.show();
    }
}