package com.example.demospringraw.entity;

public class Car {

    public static final int CAR_NO_ID = -1;
    public static final int CAR_NO_BRANDID = -1;

    private int id = CAR_NO_ID;
    private int brandId = CAR_NO_BRANDID;
    private String model;
    private String color;

    public Car(){}

    public Car(int id, int brandId, String model, String color) {
        this.id = id;
        this.brandId = brandId;
        this.model = model;
        this.color = color;
    }

    public Car(int brandId, String model, String color) {
        this.brandId = brandId;
        this.model = model;
        this.color = color;
    }

    public int getId(){
        return this.id;
    }

    public int getBrandId(){
        return this.brandId;
    }

    public String getModel(){
        return this.model;
    }

    public String getColor(){
        return this.color;
    }

    public void setBrandId(int brandId){
        this.brandId = brandId;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
