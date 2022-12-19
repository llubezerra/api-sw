package com.cursoandoid.starwars.viewmodel;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.Planet;

import java.util.List;

public class PlanetSearchViewModel extends DefaultSearchViewModel {
    /** View Model da Activity e Fragment */

    @Override
    public void callGetByNamePlanets(String search) {
        super.callGetByNamePlanets(search);
    }

    @Override
    public void callGetAllPlanets() {
        super.callGetAllPlanets();
    }

    public LiveData<List<Planet>> getDataListDone() {
        return dataListPlanet;
    }

}
