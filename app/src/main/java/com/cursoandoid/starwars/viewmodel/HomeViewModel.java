package com.cursoandoid.starwars.viewmodel;

import static com.cursoandoid.starwars.Constants.SEARCH_ALL_CHARACTERS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_ALL_PLANETS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_ALL_RANDOM_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_ALL_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_CHARACTERS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_PLANETS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_RANDOM_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_BY_NAME_STARSHIPS_EXTRAS;
import static com.cursoandoid.starwars.Constants.SEARCH_INFORMATION_API_EXTRAS;
import static com.cursoandoid.starwars.Constants.TYPE_PREFERENCES;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.activity.CharacterSearchActivity;
import com.cursoandoid.starwars.activity.PlanetSearchActivity;
import com.cursoandoid.starwars.activity.RandomSearchActivity;
import com.cursoandoid.starwars.activity.SettingsActivity;
import com.cursoandoid.starwars.activity.StarshipSearchActivity;
import com.cursoandoid.starwars.model.Menu;

public class HomeViewModel extends ViewModel {

    private String searchType;
    private String origin;


    public Boolean getShouldShowDialog(Menu menu) {

        if(!(menu.getText().equals("CONFIGURAÇÕES")) && !(menu.getText().equals("REPRODUZIR SOM CLÁSSICO DO FILME"))){
            //colocar esses nomes em constantes pois não funciona o r.string
            return true;
        }
        return false;

    }

    public Boolean getShouldShowMusic(Menu menu) {
        return menu.getText().equals("REPRODUZIR SOM CLÁSSICO DO FILME");
    }

    //pegar a posição do Menu, o searchType e definir o put extras ou a tela
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
                if (searchType == "all") {
                    origin = SEARCH_ALL_RANDOM_EXTRAS;
                } else if (searchType == "byName") {
                    origin = SEARCH_BY_NAME_RANDOM_EXTRAS;
                }
                openSearch(context);
                break;
            case "CONFIGURAÇÕES":
                Intent settings = new Intent(context, SettingsActivity.class);
                context.startActivity(settings);
                break;
//            case "REPRODUZIR SOM CLÁSSICO \nDO FILME":
//                Toast.makeText(context, "Sonzim", Toast.LENGTH_SHORT).show();
//                break;
        }
        Log.d("LOG", "aqui: " + origin);
    }

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
            case SEARCH_ALL_RANDOM_EXTRAS:
            case SEARCH_BY_NAME_RANDOM_EXTRAS:
            Intent random = new Intent(context, RandomSearchActivity.class);
            random.putExtra(SEARCH_INFORMATION_API_EXTRAS, origin);
            context.startActivity(random);
                break;
        }
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public Boolean listType(AppCompatActivity context){
        //Recuperar dados salvos
        SharedPreferences preferences = context.getSharedPreferences(TYPE_PREFERENCES, 0);

        //Valida se temos o nome em preferências
        String menuType = preferences.getString("list_type", "");
        if(menuType.equals("list")){
            return true;

        }
        return false;
    }

}
