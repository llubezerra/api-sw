package com.cursoandoid.starwars.viewmodel;

import androidx.lifecycle.LiveData;

import com.cursoandoid.starwars.model.Character;

import java.util.List;

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
