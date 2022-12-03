package com.cursoandoid.starwars.viewmodel;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_CHARACTERS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_ALL_PLANETS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_ALL_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_CHARACTERS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_PLANETS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_INFORMATION_API_EXTRAS;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.activity.CharacterSearchActivity;
import com.cursoandoid.starwars.activity.PlanetSearchActivity;
import com.cursoandoid.starwars.activity.RandomSearchActivity;
import com.cursoandoid.starwars.activity.SettingsActivity;
import com.cursoandoid.starwars.activity.StarshipSearchActivity;
import com.cursoandoid.starwars.model.Menu;

public class HomeViewModel extends ViewModel {

    //MutableLiveData<String> searchType = new MutableLiveData<>();
    private String searchType;
    private String origin;

    private Boolean shouldShowDialog = false;

//    private String listType(){
//        return listType;
//    }

    public Boolean getShouldShowDialog(Menu menu) {
        switch (menu.getText()) {
            case "Buscar ESPAÇONAVES":
            case "Buscar PERSONAGENS":
            case "Buscar PLANETAS":
                shouldShowDialog = true;
                break;
            case "Busca ALEATÓRIA":
            case "CONFIGURAÇÕES":
            case "REPRODUZIR SOM CLÁSSICO \nDO FILME":
                shouldShowDialog = false;
                break;
        }
        return shouldShowDialog;
    }

    public void apiCallType(Menu menu, AppCompatActivity context) {
        switch (menu.getText()) {
            case "Buscar ESPAÇONAVES":
                if (searchType == "all") {
                    origin = SEARCH_ALL_STARSHIPS_EXTRAS;
                } else if (searchType == "byName") {
                    origin = SEARCH_BY_NAME_STARSHIPS_EXTRAS;
                }
                openSearch(context);
                break;
            case "Buscar PLANETAS":
                if (searchType == "all") {
                    origin = SEARCH_ALL_PLANETS_EXTRAS;
                } else if (searchType == "byName") {
                    origin = SEARCH_BY_NAME_PLANETS_EXTRAS;
                }
                openSearch(context);
                break;
            case "Buscar PERSONAGENS":
                if (searchType == "all") {
                    origin = SEARCH_ALL_CHARACTERS_EXTRAS;
                } else if (searchType == "byName") {
                    origin = SEARCH_BY_NAME_CHARACTERS_EXTRAS;
                }
                openSearch(context);
                break;
            case "Busca ALEATÓRIA":
                Intent random = new Intent(context, RandomSearchActivity.class);
                context.startActivity(random);
                break;
            case "CONFIGURAÇÕES":
                Intent settings = new Intent(context, SettingsActivity.class);
                context.startActivity(settings);
                break;
            case "REPRODUZIR SOM CLÁSSICO \nDO FILME":
                Toast.makeText(context, "Sonzim", Toast.LENGTH_SHORT).show();
                break;
        }
        System.out.println("aqui: " + origin);
        System.out.println("menu: " + menu.getText());
    }

    //pegar a posição e retornar a tela
    public void openSearch(AppCompatActivity context) {

        switch (origin) {
            case SEARCH_ALL_STARSHIPS_EXTRAS:
            case SEARCH_BY_NAME_STARSHIPS_EXTRAS:
                Intent starships = new Intent(context, StarshipSearchActivity.class);
                starships.putExtra(SEARCH_INFORMATION_API_EXTRAS, origin);
                context.startActivity(starships);
                break;
            case SEARCH_ALL_PLANETS_EXTRAS:
            case SEARCH_BY_NAME_PLANETS_EXTRAS:
                Intent planets = new Intent(context, PlanetSearchActivity.class);
                planets.putExtra(SEARCH_INFORMATION_API_EXTRAS, origin);
                context.startActivity(planets);
                break;
            case SEARCH_ALL_CHARACTERS_EXTRAS:
            case SEARCH_BY_NAME_CHARACTERS_EXTRAS:
                Intent characters = new Intent(context, CharacterSearchActivity.class);
                characters.putExtra(SEARCH_INFORMATION_API_EXTRAS, origin);
                context.startActivity(characters);
                break;
        }
    }

//    public LiveData<String> getSearchType() {
//        return searchType;
//    }


    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
}
