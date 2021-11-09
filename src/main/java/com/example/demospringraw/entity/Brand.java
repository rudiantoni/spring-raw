package com.example.demospringraw.entity;

public class Brand {

    private int id;
    private String description;

    public Brand() {}

    public Brand(int id, String description) {
        this.id = id;
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
