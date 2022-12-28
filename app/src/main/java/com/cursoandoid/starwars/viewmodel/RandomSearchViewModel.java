package com.cursoandoid.starwars.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.SwapiObject;

import java.util.List;

public class RandomSearchViewModel extends DefaultSearchViewModel{
    /** View Model da Activity e Fragment */

    @Override
    public void callGetByNameStarships(String search, Activity context) {
        super.callGetByNameStarships(search, context);
    }

    @Override
    public void callGetAllStarships(Activity context) {
        super.callGetAllStarships(context);
    }

    @Override
    public void callGetPaginationStarships(String url, Activity context) {
        super.callGetPaginationStarships(url, context);
    }

    @Override
    public void callGetByNamePlanets(String search, Activity context) {
        super.callGetByNamePlanets(search, context);
    }

    @Override
    public void callGetAllPlanets(Activity context) {
        super.callGetAllPlanets(context);
    }

    @Override
    public void callGetPaginationPlanets(String url, Activity context) {
        super.callGetPaginationPlanets(url, context);
    }

    @Override
    public void callGetByNameCharacters(String search, Activity context) {
        super.callGetByNameCharacters(search, context);
    }

    @Override
    public void callGetAllCharacters(Activity context) {
        super.callGetAllCharacters(context);
    }

    @Override
    public void callGetPaginationCharacters(String url, Activity context) {
        super.callGetPaginationCharacters(url, context);
    }

    public LiveData<List<SwapiObject>> getDataList() {
        return dataList;
    }

    @Override
    public Boolean enablePagination() {
        return super.enablePagination();
    }

    @Override
    public Boolean paginationNext() {
        return super.paginationNext();
    }

}
