package com.example.demospringraw.entity;

public class Brand {

    public static final int BRAND_NO_ID = -1;
    private int id = BRAND_NO_ID;
    private String description;

    public Brand () {}

    public Brand(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Brand(String description) {
        this.description = description;
    }

    public int getId(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
