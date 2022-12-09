package com.cursoandoid.starwars.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.GetDataService;
import com.cursoandoid.starwars.model.Character;
import com.cursoandoid.starwars.model.Characters;
import com.cursoandoid.starwars.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterSearchViewModel extends ViewModel {
    /** View Model da Activity e Fragment */

    private final MutableLiveData<List<Character>> dataListDone = new MutableLiveData<>();

    /** GET CHARACTERS BY NAME */
    public void callGetByNameCharacters(String search){
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Characters> call = service.getCharacterByName(search);
        //O método do callback
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if(response.body() != null) {
                    dataListDone.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListDone.setValue(null);
            }

        });
    }

    /** GET ALL CHARACTERS */
    public void callGetAllCharacters(){
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Characters> call = service.getAllCharacters();
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if(response.body() != null) {
                    dataListDone.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListDone.setValue(null);
            }

        });
    }

    public LiveData<List<Character>> getDataListDone() {
        return dataListDone;
    }

}
