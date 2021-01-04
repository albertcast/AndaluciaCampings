package com.example.andaluciacampings;

public class Camping {

    String campingName;
    int campingImage;

    public Camping(String campingName, int campingImage){
        this.campingName = campingName;
        this.campingImage = campingImage;
    }

    public int getCampingImage() {
        return campingImage;
    }

    public String getCampingName() {
        return campingName;
    }
}
