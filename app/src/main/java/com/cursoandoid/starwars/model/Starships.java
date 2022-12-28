package com.cursoandoid.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Starships {

    @SerializedName("count")
    private Integer count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private ArrayList<Starship> results;

    public Starships(Integer count, String next, String previous, ArrayList<Starship> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public ArrayList<Starship> getResults() {
        return results;
    }

    /** Usado para ver meu retorno */
//    Log.d("LOG", " " + response.body()); --> colocar na chamada pra trazer o toString caso necessite
//    @Override
//    public String toString() {
//        return "Starships{" +
//                "count=" + count +
//                ", next='" + next + '\'' +
//                ", previous=" + previous +
//                ", results=" + results +
//                '}';
//    }
}
