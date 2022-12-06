package com.cursoandoid.starwars.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.GetDataService;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.model.Starships;
import com.cursoandoid.starwars.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarshipFragmentViewModel extends ViewModel {
    /** GET ALL STARSHIPS */

    private MutableLiveData<List<Starship>> dataListDone = new MutableLiveData<>();


    public void callGetAllStarships(){
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Starships> call = service.getAllStarships();
        // O método do callback
        call.enqueue(new Callback<Starships>() {
            @Override
            public void onResponse(Call<Starships> call, Response<Starships> response) {
                if(response.body() != null) {
                    dataListDone.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Starships> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListDone.setValue(null);
            }

        });
    }

    public LiveData<List<Starship>> getDataListDone() {
        return dataListDone;
    }

}
