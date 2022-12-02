package com.cursoandoid.starwars;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.cursoandoid.starwars.data.StarshipSearchInterface;
import com.cursoandoid.starwars.model.Starships;
import com.cursoandoid.starwars.network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessModel {

    private static BusinessModel instance;

    public static BusinessModel getInstance() {
        if(instance == null) {
            instance = new BusinessModel();
        }
        return instance;
    }

}
