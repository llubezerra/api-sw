package com.cursoandoid.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Planet {

    @SerializedName("name")
    private String name;
    @SerializedName("diameter")
    private String diameter;
    @SerializedName("rotation_period")
    private String rotationPeriod;
    @SerializedName("orbital_period")
    private String orbitalPeriod;
    @SerializedName("gravity")
    private String gravity;
    @SerializedName("population")
    private String population;
    @SerializedName("climate")
    private String climate;
    @SerializedName("terrain")
    private String terrain;
    @SerializedName("surface_water")
    private String surfaceWater;
    @SerializedName("residents")
    private ArrayList<String> residents;
    @SerializedName("films")
    private ArrayList<String> films;
    @SerializedName("url")
    private String url;
    @SerializedName("created")
    private String created;
    @SerializedName("edited")
    private String edited;

    public Planet(String name, String diameter, String rotationPeriod, String orbitalPeriod, String gravity, String population, String climate, String terrain, String surfaceWater, ArrayList<String> residents, ArrayList<String> films, String url, String created, String edited) {
        this.name = name;
        this.diameter = diameter;
        this.rotationPeriod = rotationPeriod;
        this.orbitalPeriod = orbitalPeriod;
        this.gravity = gravity;
        this.population = population;
        this.climate = climate;
        this.terrain = terrain;
        this.surfaceWater = surfaceWater;
        this.residents = residents;
        this.films = films;
        this.url = url;
        this.created = created;
        this.edited = edited;
    }

    public String getName() {
        return name;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getGravity() {
        return gravity;
    }

    public String getPopulation() {
        return population;
    }

    public String getClimate() {
        return climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public ArrayList<String> getResidents() {
        return residents;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

}
