package com.example.andaluciacampings;

public class Camping {

    String campingName, location, phone_number;
    int campingImage;

    public Camping(String campingName, String location, String phone_number, int campingImage){
        this.campingName = campingName;
        this.location = location;
        this.phone_number = phone_number;
        this.campingImage = campingImage;
    }

    public int getCampingImage() {
        return campingImage;
    }

    public String getCampingName() {
        return campingName;
    }

    public String getLocation() { return location; }

    public String getPhone_number() { return phone_number; }
}
