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
    public void callGetByNameStarships(String search, Activity context) {
        super.callGetByNameStarships(search, context);
    }

    @Override
    public void callGetAllStarships(Activity context) {
        super.callGetAllStarships(context);
    }

    public LiveData<List<SwapiObject>> getDataListDone() {
        return dataList;
    }

    @Override
    public void callGetPaginationStarships(String url, Activity context) {
        super.callGetPaginationStarships(url, context);
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
