package com.cursoandoid.starwars.viewmodel;

import android.app.Activity;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.SwapiObject;

import java.util.List;

public class CharacterSearchViewModel extends DefaultSearchViewModel {
    /** View Model da Activity e Fragment */

    @Override
    public void callGetByNameCharacters(String search, Activity context) {
        super.callGetByNameCharacters(search, context);
    }

    @Override
    public void callGetAllCharacters(Activity context) {
        super.callGetAllCharacters(context);
    }

    public LiveData<List<SwapiObject>> getDataListDone() {
        return dataList;
    }

    @Override
    public void callGetPaginationCharacters(String url, Activity context) {
        super.callGetPaginationCharacters(url, context);
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
