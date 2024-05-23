package org.example.entity;

public class Car {
    private Integer id;
    private String name;
    private String brand;
    private String model;
    private double pricePerDay;
    private boolean rented;

    public Car(Integer id, String name, String brand, String model, double pricePerDay) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.rented = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}
