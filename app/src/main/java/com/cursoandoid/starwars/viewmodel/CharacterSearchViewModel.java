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

public class CharacterSearchViewModel extends DefaultSearchViewModel {
    /** View Model da Activity e Fragment */

    @Override
    public void callGetByNameCharacters(String search) {
        super.callGetByNameCharacters(search);
    }

    @Override
    public void callGetAllCharacters() {
        super.callGetAllCharacters();
    }

    public LiveData<List<Character>> getDataListDone() {
        return dataListCharacter;
    }

}
