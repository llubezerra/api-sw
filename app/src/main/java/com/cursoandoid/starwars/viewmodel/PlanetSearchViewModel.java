package com.cursoandoid.starwars.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.SwapiObject;

import java.util.List;

public class PlanetSearchViewModel extends DefaultSearchViewModel {
    /** View Model da Activity e Fragment */

    @Override
    public void callGetByNamePlanets(String search, Activity context) {
        super.callGetByNamePlanets(search, context);
    }

    @Override
    public void callGetAllPlanets(Activity context) {
        super.callGetAllPlanets(context);
    }

    public LiveData<List<SwapiObject>> getDataListDone() {
        return dataList;
    }

}
