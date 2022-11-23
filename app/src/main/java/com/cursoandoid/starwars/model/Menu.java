package com.cursoandoid.starwars.model;

import android.graphics.drawable.Drawable;


public class Menu {

    private Drawable image;
    private String text;

    public Menu(){

    }

    public Menu(Drawable image, String text) {
        this.image = image;
        this.text = text;
    }

    public Drawable getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setText(String text) {
        this.text = text;
    }
}
