package com.cursoandoid.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Starship {

    //Classe de modelo para consumir a API
    //starships string -- The URL root for Starships resources

    @SerializedName("name")
    private String name;
    @SerializedName("model")
    private String model;
    @SerializedName("starship_class")
    private String starship_class;
    @SerializedName("manufacturer")
    private String manufacturer;
    @SerializedName("cost_in_credits")
    private String cost_in_credits;
    @SerializedName("length")
    private String length;
    @SerializedName("crew")
    private String crew;
    @SerializedName("passengers")
    private String passengers;
    @SerializedName("max_atmosphering_speed")
    private String max_atmosphering_speed;
    @SerializedName("hyperdrive_rating")
    private String hyperdrive_rating;
    @SerializedName("MGLT")
    private String MGLT;
    @SerializedName("cargo_capacity")
    private String cargo_capacity;
    @SerializedName("consumables")
    private String consumables;
    @SerializedName("films")
    private ArrayList<String> films;
    @SerializedName("pilots")
    private ArrayList<Object> pilots;
    @SerializedName("url")
    private String url;
    @SerializedName("created")
    private String created;
    @SerializedName("edited")
    private String edited;

    /**Podemos usar a anotação @SerializedName para facilitar o processo de conversão e serialização,
    mas isso se torna desnecessário se os nomes das variáveis forem compatíveis e os tipos forem os mesmos.*/

    public Starship(String name, String model, String starship_class, String manufacturer, String cost_in_credits, String length, String crew, String passengers, String max_atmosphering_speed, String hyperdrive_rating, String MGLT, String cargo_capacity, String consumables, ArrayList<String> films, ArrayList<Object> pilots, String url, String created, String edited) {
        this.name = name;
        this.model = model;
        this.starship_class = starship_class;
        this.manufacturer = manufacturer;
        this.cost_in_credits = cost_in_credits;
        this.length = length;
        this.crew = crew;
        this.passengers = passengers;
        this.max_atmosphering_speed = max_atmosphering_speed;
        this.hyperdrive_rating = hyperdrive_rating;
        this.MGLT = MGLT;
        this.cargo_capacity = cargo_capacity;
        this.consumables = consumables;
        this.films = films;
        this.pilots = pilots;
        this.url = url;
        this.created = created;
        this.edited = edited;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getStarship_class() {
        return starship_class;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCost_in_credits() {
        return cost_in_credits;
    }

    public String getLength() {
        return length;
    }

    public String getCrew() {
        return crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public String getMax_atmosphering_speed() {
        return max_atmosphering_speed;
    }

    public String getHyperdrive_rating() {
        return hyperdrive_rating;
    }

    public String getMGLT() {
        return MGLT;
    }

    public String getCargo_capacity() {
        return cargo_capacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public ArrayList<String> getFilms() {
        return films;
    }

    public ArrayList<Object> getPilots() {
        return pilots;
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
