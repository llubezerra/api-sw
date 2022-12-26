package com.cursoandoid.starwars.model;

import android.text.Spanned;

public class SwapiObject {

    private int bgRes;
    private int iconRes;
    private Spanned string1;
    private Spanned string2;
    private Spanned string3;

    public SwapiObject(int bgRes, int iconRes, Spanned string1, Spanned string2, Spanned string3) {
        this.bgRes = bgRes;
        this.iconRes = iconRes;
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
    }
    
    public int getBgRes() {
        return bgRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public Spanned getString1() {
        return string1;
    }

    public Spanned getString2() {
        return string2;
    }

    public Spanned getString3() {
        return string3;
    }
    
}
