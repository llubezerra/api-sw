package com.cursoandoid.starwars;

import com.cursoandoid.starwars.model.Characters;
import com.cursoandoid.starwars.model.Planets;
import com.cursoandoid.starwars.model.Starships;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GetDataService {

    /*Consumir os endpoints

        /starships/ -- get all the starship resources
        /starships/:id/ -- get a specific starship resource
        /starships/schema/ -- view the JSON schema for this resource*/

    @GET("starships/")
    Call<Starships> getAllStarships();

    @GET("starships/")
    Call<Starships> getStarshipByName(@Query("search") String search);

    @GET
    Call<Starships> getPageStarships(@Url String url);

    @GET("people/")
    Call<Characters> getAllCharacters();

    @GET("people/")
    Call<Characters> getCharacterByName(@Query("search") String search);

    @GET
    Call<Characters> getPageCharacters(@Url String url);

    @GET("planets/")
    Call<Planets> getAllPlanets();

    @GET("planets/")
    Call<Planets> getPlanetByName(@Query("search") String search);

    @GET
    Call<Planets> getPagePlanets(@Url String url);
}
