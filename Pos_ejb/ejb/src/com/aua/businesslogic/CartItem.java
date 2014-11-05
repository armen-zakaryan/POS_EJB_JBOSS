package com.aua.businesslogic;

/**
 * Created by Armen on 12-Oct-14.
 */
public class CartItem extends Product {
    private int amount;
    private double sum;

    public CartItem(){}
    public CartItem(Product p, int amount) {
        super(p);
        this.amount = amount;
        this.sum = amount * this.getPrice();
    }

    public void changeAmount(int amount) {
        this.amount+= amount;
        this.sum = this.amount * this.getPrice();
    }

    public int getAmount() { return this.amount; }
    public double getSum(){
        return this.sum;
    }

}
