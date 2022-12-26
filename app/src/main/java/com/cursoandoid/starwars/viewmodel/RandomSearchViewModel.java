package com.cursoandoid.starwars.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.Character;
import com.cursoandoid.starwars.model.Planet;
import com.cursoandoid.starwars.model.SwapiObject;

import java.util.List;

public class RandomSearchViewModel extends DefaultSearchViewModel{

    @Override
    public void callGetByNameStarships(String search) {
        super.callGetByNameStarships(search);
    }

    @Override
    public void callGetAllStarships(Activity context) {
        super.callGetAllStarships(context);
    }

    @Override
    public void callGetByNamePlanets(String search) {
        super.callGetByNamePlanets(search);
    }

    @Override
    public void callGetAllPlanets() {
        super.callGetAllPlanets();
    }

    @Override
    public void callGetByNameCharacters(String search) {
        super.callGetByNameCharacters(search);
    }

    @Override
    public void callGetAllCharacters() {
        super.callGetAllCharacters();
    }

    public LiveData<List<SwapiObject>> getDataListStarship() {
        return dataListStarship;
    }
    public LiveData<List<Planet>> getDataListPlanet() {
        return dataListPlanet;
    }
    public LiveData<List<Character>> getDataListCharacter() {
        return dataListCharacter;
    }
}
