package com.example.carmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name="car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="Brand")
    private String brand;

    @Column(name="Model")
    private String model;

    @Column(name="Year")
    private int year;

    @Column(name="InStock")
    private boolean inStock;

    public Car() {

    }

    public Car(String brand, String model, int year, boolean inStock) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.inStock=inStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getInStock(){
        return inStock;
    }

    public void setInStock(boolean inStock){
        this.inStock=inStock;
    }

    @Override
    public String toString(){
        return "Car [id = "+id+", brand = "+brand+", model = "+model+", year = "+year+", in stock = "+inStock+"]";
    }
}
