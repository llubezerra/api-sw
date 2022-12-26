package com.cursoandoid.starwars.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.SwapiObject;

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
    public void callGetAllStarships(Activity context) {
        super.callGetAllStarships(context);
    }

    public LiveData<List<SwapiObject>> getDataListDone() {
        return dataListStarship;
    }

}
