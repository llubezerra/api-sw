package com.cursoandoid.starwars.viewmodel;

import android.app.Activity;
import android.util.Log;

import androidx.core.text.HtmlCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cursoandoid.starwars.GetDataService;
import com.cursoandoid.starwars.R;
import com.cursoandoid.starwars.model.Character;
import com.cursoandoid.starwars.model.Characters;
import com.cursoandoid.starwars.model.Planet;
import com.cursoandoid.starwars.model.Planets;
import com.cursoandoid.starwars.model.Starship;
import com.cursoandoid.starwars.model.Starships;
import com.cursoandoid.starwars.model.SwapiObject;
import com.cursoandoid.starwars.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultSearchViewModel extends ViewModel {

    ArrayList<SwapiObject> swapiObjects = new ArrayList<>();

    protected final MutableLiveData<List<SwapiObject>> dataListStarship = new MutableLiveData<>();
    protected final MutableLiveData<List<Planet>> dataListPlanet = new MutableLiveData<>();
    protected final MutableLiveData<List<Character>> dataListCharacter = new MutableLiveData<>();

    /**
     * GET STARSHIPS BY NAME
     */
    protected void callGetByNameStarships(String search) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Starships> call = service.getStarshipByName(search);
        //O método do callback
        call.enqueue(new Callback<Starships>() {
            @Override
            public void onResponse(Call<Starships> call, Response<Starships> response) {
                if (response.body() != null) {
                    dataListStarship.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Starships> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListStarship.setValue(null);
            }

        });
    }

    /** GET ALL STARSHIPS */
    protected void callGetAllStarships(Activity context) {
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Starships> call = service.getAllStarships();
        // O método do callback
        call.enqueue(new Callback<Starships>() {
            @Override
            public void onResponse(Call<Starships> call, Response<Starships> response) {
                if (response.body() != null) {
                    parseStarshipsToSwapiObject(response.body(), context);
                }
            }

            @Override
            public void onFailure(Call<Starships> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListStarship.setValue(null);
            }

        });
    }

    /** GET PLANETS BY NAME */
    protected void callGetByNamePlanets(String search) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Planets> call = service.getPlanetByName(search);
        //O método do callback
        call.enqueue(new Callback<Planets>() {
            @Override
            public void onResponse(Call<Planets> call, Response<Planets> response) {
                if (response.body() != null) {
                    dataListPlanet.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Planets> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListPlanet.setValue(null);
            }

        });
    }

    /** GET ALL PLANETS */
    protected void callGetAllPlanets() {
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Planets> call = service.getAllPlanets();
        //O método do callback
        call.enqueue(new Callback<Planets>() {
            @Override
            public void onResponse(Call<Planets> call, Response<Planets> response) {
                if (response.body() != null) {
                    dataListPlanet.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Planets> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListPlanet.setValue(null);
            }

        });
    }

    /** GET CHARACTERS BY NAME */
    protected void callGetByNameCharacters(String search) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Characters> call = service.getCharacterByName(search);
        //O método do callback
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if (response.body() != null) {
                    dataListCharacter.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListCharacter.setValue(null);
            }

        });
    }

    /**
     * GET ALL CHARACTERS
     */
    protected void callGetAllCharacters() {
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Characters> call = service.getAllCharacters();
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if (response.body() != null) {
                    dataListCharacter.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataListCharacter.setValue(null);
            }

        });
    }

    private void parseStarshipsToSwapiObject(Starships body, Activity context) {
        ArrayList<Starship> result = body.getResults();

        for (Starship starship : result) {
            SwapiObject swapiModel = new SwapiObject(
                    R.drawable.icon_circle_starship,
                    R.drawable.starship_2,
                    HtmlCompat.fromHtml(context.getString(R.string.name, starship.getName()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.crew, starship.getCrew()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.passengers, starship.getPassengers()), HtmlCompat.FROM_HTML_MODE_LEGACY)
            );
            swapiObjects.add(swapiModel);

        }
        dataListStarship.setValue(swapiObjects);
    }

}
