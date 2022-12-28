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
    Integer quantity;
    String next;

    protected final MutableLiveData<List<SwapiObject>> dataList = new MutableLiveData<>();

    /** GET STARSHIPS BY NAME */
    protected void callGetByNameStarships(String search, Activity context) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Starships> call = service.getStarshipByName(search);
        //O método do callback
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
                dataList.setValue(null);
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
                dataList.setValue(null);
            }

        });
    }

    /** GET STARSHIPS PAGINATION */
    protected void callGetPaginationStarships(String url, Activity context) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Starships> call = service.getPageStarships(url);
        //O método do callback
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
                dataList.setValue(null);
            }

        });
    }

    /** GET PLANETS BY NAME */
    protected void callGetByNamePlanets(String search, Activity context) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Planets> call = service.getPlanetByName(search);
        //O método do callback
        call.enqueue(new Callback<Planets>() {
            @Override
            public void onResponse(Call<Planets> call, Response<Planets> response) {
                if (response.body() != null) {
                    parsePlanetsToSwapiObject(response.body(), context);
                }
            }

            @Override
            public void onFailure(Call<Planets> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataList.setValue(null);
            }

        });
    }

    /** GET ALL PLANETS */
    protected void callGetAllPlanets(Activity context) {
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Planets> call = service.getAllPlanets();
        //O método do callback
        call.enqueue(new Callback<Planets>() {
            @Override
            public void onResponse(Call<Planets> call, Response<Planets> response) {
                if (response.body() != null) {
                    parsePlanetsToSwapiObject(response.body(), context);
                }
            }

            @Override
            public void onFailure(Call<Planets> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataList.setValue(null);
            }

        });
    }

    /** GET CHARACTERS BY NAME */
    protected void callGetByNameCharacters(String search, Activity context) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Characters> call = service.getCharacterByName(search);
        //O método do callback
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if (response.body() != null) {
                    parseCharactersToSwapiObject(response.body(), context);
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataList.setValue(null);
            }

        });
    }

    /** GET ALL CHARACTERS */
    protected void callGetAllCharacters(Activity context) {
        // Create handle for the RetrofitInstance interface
        // ENTRAR NA TELA ABRIR TUDO
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Characters> call = service.getAllCharacters();
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if (response.body() != null) {
                    parseCharactersToSwapiObject(response.body(), context);
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataList.setValue(null);
            }

        });
    }

    /** GET CHARACTERS PAGINATION */
    protected void callGetPaginationCharacters(String url, Activity context) {
        // Create handle for the RetrofitInstance interface
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        //receber texto da Activity -> searchText
        Call<Characters> call = service.getPageCharacters(url);
        //O método do callback
        call.enqueue(new Callback<Characters>() {
            @Override
            public void onResponse(Call<Characters> call, Response<Characters> response) {
                if (response.body() != null) {
                    parseCharactersToSwapiObject(response.body(), context);
                }
            }

            @Override
            public void onFailure(Call<Characters> call, Throwable t) {
                Log.d("LOG", "onFailure: " + t.getMessage());
                dataList.setValue(null);
            }

        });
    }

    private void parseStarshipsToSwapiObject(Starships body, Activity context) {
        ArrayList<Starship> result = body.getResults();
        quantity = body.getCount();
        next = body.getNext();

        for (Starship starship : result) {
            SwapiObject swapiModel = new SwapiObject(
                    // Change elements on view, wich is xml adapter_default_search
                    R.drawable.icon_circle_starship,
                    R.drawable.starship_2,
                    // 2 formatos no mesmo textView
                    HtmlCompat.fromHtml(context.getString(R.string.name, starship.getName()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.crew, starship.getCrew()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.passengers, starship.getPassengers()), HtmlCompat.FROM_HTML_MODE_COMPACT)
            );
            swapiObjects.add(swapiModel);

        }
        dataList.setValue(swapiObjects);
    }

    private void parsePlanetsToSwapiObject(Planets body, Activity context) {
        ArrayList<Planet> result = body.getResults();
        quantity = body.getCount();
        next = body.getNext();

        for (Planet planet : result) {
            SwapiObject swapiModel = new SwapiObject(
                    R.drawable.icon_circle_planet,
                    R.drawable.planet_2,
                    HtmlCompat.fromHtml(context.getString(R.string.name, planet.getName()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.population, planet.getPopulation()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.passengers, planet.getDiameter()), HtmlCompat.FROM_HTML_MODE_COMPACT)
            );
            swapiObjects.add(swapiModel);

        }
        dataList.setValue(swapiObjects);
    }

    private void parseCharactersToSwapiObject(Characters body, Activity context) {
        ArrayList<Character> result = body.getResults();
        quantity = body.getCount();
        next = body.getNext();

        for (Character character : result) {
            SwapiObject swapiModel = new SwapiObject(
                    R.drawable.icon_circle_person,
                    R.drawable.person,
                    HtmlCompat.fromHtml(context.getString(R.string.name, character.getName()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.height, character.getHeight()), HtmlCompat.FROM_HTML_MODE_LEGACY),
                    HtmlCompat.fromHtml(context.getString(R.string.eyes_color, character.getEyeColor()), HtmlCompat.FROM_HTML_MODE_COMPACT)
            );
            swapiObjects.add(swapiModel);

        }
        dataList.setValue(swapiObjects);
    }

    public Integer getQuantity() {
        return quantity;
    }

    protected Boolean enablePagination() {
        return quantity > 10;
    }

    protected Boolean paginationNext() {
        return (next != null);
    }

}
