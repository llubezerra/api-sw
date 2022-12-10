package com.cursoandoid.starwars;

public class BusinessModel {

    private static BusinessModel instance;

    public static BusinessModel getInstance() {
        if(instance == null) {
            instance = new BusinessModel();
        }
        return instance;
    }

}
