package com.cursoandoid.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Characters {

    @SerializedName("count")
    private Integer count;
    @SerializedName("next")
    private String next;
    @SerializedName("previous")
    private Object previus;
    @SerializedName("results")
    private ArrayList<Character> results;

    public Characters(Integer count, String next, Object previus, ArrayList<Character> results) {
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

    public ArrayList<Character> getResults() {
        return results;
    }

}
