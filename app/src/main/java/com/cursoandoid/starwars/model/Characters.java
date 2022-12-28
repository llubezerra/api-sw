package com.cursoandoid.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Characters {

    @SerializedName("count")
    private Integer count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private String previous;
    @SerializedName("results")
    private ArrayList<Character> results;

    public Characters(Integer count, String next, String previous, ArrayList<Character> results) {
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

    public ArrayList<Character> getResults() {
        return results;
    }

}
