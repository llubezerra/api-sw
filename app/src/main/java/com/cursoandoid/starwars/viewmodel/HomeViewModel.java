package com.cursoandoid.starwars.viewmodel;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.activity.CharacterSearchActivity;
import com.cursoandoid.starwars.activity.PlanetSearchActivity;
import com.cursoandoid.starwars.activity.RandomSearchActivity;
import com.cursoandoid.starwars.activity.SettingsActivity;
import com.cursoandoid.starwars.activity.StarshipSearchActivity;
import com.cursoandoid.starwars.model.Menu;

public class HomeViewModel extends ViewModel {

//    private String listType(){
//        return listType;
//    }

    //pegar a posição e retornar a tela
    public void openSearch(Menu menu, AppCompatActivity context){

        switch (menu.getText()){
            case "Buscar ESPAÇONAVES":
                Intent starships = new Intent(context, StarshipSearchActivity.class);
                context.startActivity(starships);
                break;
            case "Buscar PLANETAS":
                Intent planets = new Intent(context, PlanetSearchActivity.class);
                context.startActivity(planets);
                break;
            case "Buscar PERSONAGENS":
                Intent characters = new Intent(context, CharacterSearchActivity.class);
                context.startActivity(characters);
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
    }

}
