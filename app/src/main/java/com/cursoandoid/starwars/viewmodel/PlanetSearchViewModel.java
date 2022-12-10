package com.cursoandoid.starwars.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.GetDataService;
import com.cursoandoid.starwars.model.Planet;
import com.cursoandoid.starwars.model.Planets;
import com.cursoandoid.starwars.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanetSearchViewModel extends ViewModel {
    /** View Model da Activity e Fragment */

    private final MutableLiveData<List<Planet>> dataListDone = new MutableLiveData<>();

    /** GET PLANETS BY NAME */
    public void callGetByNamePlanets(String search){
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Planets> call = service.getPlanetByName(search);
        //O método do callback
        call.enqueue(new Callback<Planets>() {
            @Override
            public void onResponse(Call<Planets> call, Response<Planets> response) {
                if(response.body() != null) {
                    dataListDone.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Planets> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListDone.setValue(null);
            }

        });
    }

    /** GET ALL PLANETS */
    public void callGetAllPlanets(){
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Planets> call = service.getAllPlanets();
        //O método do callback
        call.enqueue(new Callback<Planets>() {
            @Override
            public void onResponse(Call<Planets> call, Response<Planets> response) {
                if(response.body() != null) {
                    dataListDone.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Planets> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListDone.setValue(null);
            }

        });
    }

    public LiveData<List<Planet>> getDataListDone() {
        return dataListDone;
    }

}
