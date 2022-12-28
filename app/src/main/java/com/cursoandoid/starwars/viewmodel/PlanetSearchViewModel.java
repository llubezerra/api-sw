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

    @Override
    public void callGetPaginationPlanets(String url, Activity context) {
        super.callGetPaginationPlanets(url, context);
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
