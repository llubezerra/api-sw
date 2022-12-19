package com.cursoandoid.starwars.viewmodel;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.Starship;

import java.util.List;

public class StarshipSearchViewModel extends DefaultSearchViewModel {
    /** View Model da Activity e Fragment */

//    private StarshipSearchInterface starshipSearchInterface;
//
//    public StarshipSearchViewModel() {
//        starshipSearchInterface = BusinessModel.getInstance();
//    }

    @Override
    public void callGetByNameStarships(String search) {
        super.callGetByNameStarships(search);
    }

    @Override
    public void callGetAllStarships() {
        super.callGetAllStarships();
    }

    public LiveData<List<Starship>> getDataListDone() {
        return dataListStarship;
    }

}
