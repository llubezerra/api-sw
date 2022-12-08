package com.cursoandoid.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Starships {

    @SerializedName("count")
    private Integer count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private Object previus;
    @SerializedName("results")
    private ArrayList<Starship> results;

    public Starships(Integer count, String next, Object previus, ArrayList<Starship> results) {
        this.count = count;
        this.next = next;
        this.previus = previus;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public Object getPrevius() {
        return previus;
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
//                ", previus=" + previus +
//                ", results=" + results +
//                '}';
//    }
}
