package com.cursoandoid.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Character {

    @SerializedName("name")
    private String name;
    @SerializedName("birth_year")
    private String birthYear;
    @SerializedName("eye_color")
    private String eyeColor;
    @SerializedName("gender")
    private String gender;
    @SerializedName("hair_color")
    private String hairColor;
    @SerializedName("height")
    private String height;
    @SerializedName("mass")
    private String mass;
    @SerializedName("skin_color")
    private String skinColor;
    @SerializedName("homeworld")
    private String homeworld;
    @SerializedName("films")
    private ArrayList<String> films;
    @SerializedName("species")
    private ArrayList<String> species;
    @SerializedName("starships")
    private ArrayList<String> starships;
    @SerializedName("vehicles")
    private ArrayList<String> vehicles;
    @SerializedName("url")
    private String url;
    @SerializedName("created")
    private String created;
    @SerializedName("edited")
    private String edited;

    public Character(String name, String birthYear, String eyeColor, String gender, String hairColor, String height, String mass, String skinColor, String homeworld, ArrayList<String> films, ArrayList<String> species, ArrayList<String> starships, ArrayList<String> vehicles, String url, String created, String edited) {
        this.name = name;
        this.birthYear = birthYear;
        this.eyeColor = eyeColor;
        this.gender = gender;
        this.hairColor = hairColor;
        this.height = height;
        this.mass = mass;
        this.skinColor = skinColor;
        this.homeworld = homeworld;
        this.films = films;
        this.species = species;
        this.starships = starships;
        this.vehicles = vehicles;
        this.url = url;
        this.created = created;
        this.edited = edited;
    }

    public String getName() {
        return name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getGender() {
        return gender;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public ArrayList<String> getStarships() {
        return starships;
    }

    public ArrayList<String> getVehicles() {
        return vehicles;
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