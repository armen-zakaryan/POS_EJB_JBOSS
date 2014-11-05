package com.aua.businesslogic;

import java.io.Serializable;

/**
 * Created by Armen on 11-Oct-14.
 */
public class Product implements Serializable {
    private String UPS;
    private String name;
    private double price;
    private int availableNumber;

    public Product(){}
    public Product(String UPS, String name, double price, int availableNumber){
        this.UPS = UPS;
        this.name = name;
        this.price = price;
        this.availableNumber = availableNumber;
    }

    public Product(Product product){
        this.UPS = product.getUPS();
        this.name = product.getName();
        this.price = product.getPrice();
        this.availableNumber = product.getAvailableNumber();
    }

    public String toString(){
        return  this.UPS+"... "+ this.name+"  "+this.price+"$";
    }

    //Getters
    public String getUPS() {
        return UPS;
    }
    public int getAvailableNumber() {
        return availableNumber;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }



}
