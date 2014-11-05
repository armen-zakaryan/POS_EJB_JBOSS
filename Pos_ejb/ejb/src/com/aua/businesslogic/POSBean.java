package com.aua.businesslogic;

import java.util.ArrayList;
import com.aua.business.POS;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class POSBean implements POS {
    private Map<String, ShoppingCart> activeUsersShoppingCarts = new HashMap<>();
    private Map<String, Sale> curentSalse = new HashMap<>();

    public POSBean() {}

    @Override
    public Map<String,Product> getProductList(){
        return Start.productList.getProductList();
    }
    @Override
    public String authenticate(String username, String password) {
        String authUser = Start.userList.authenticate(username, password);
        if(authUser != null){
            this.activeUsersShoppingCarts.put(username,new ShoppingCart(username));
        }
        return authUser;
    }
    @Override
    public boolean addItem(String userName, String ups, int amount) {
        ShoppingCart shopingCurt = this.activeUsersShoppingCarts.get(userName);
        if (shopingCurt != null){
            shopingCurt.addItem(ups,new CartItem(Start.productList.getProductList().get(ups),amount));
            return true;
        } else { return false; }
    }
    @Override
    public double getTotalCost(String userName){
        ShoppingCart shoppingCart = this.activeUsersShoppingCarts.get(userName);
        if(shoppingCart != null){
            return shoppingCart.getTotalCost();
        } else { return 0; }
    }
    @Override
    public double getSaleTotal(String userName){
        Sale sale = this.curentSalse.get(userName);
        if(sale != null){
            return sale.getSaleTotal();
        }  else { return 0; }
    }
    @Override
    public boolean removeItem(String userName, String ups, int amount){
        return this.activeUsersShoppingCarts.get(userName).removeItem(ups,amount);
    }
    @Override
    public ArrayList makeSale(String userName){
        Sale sale;
        sale = new Sale(this.activeUsersShoppingCarts.get(userName));
        this.curentSalse.put(userName,sale);
        this.activeUsersShoppingCarts.remove(userName);
        ArrayList<CartItem> notAddedItems = new ArrayList<>();
        return sale.verify();
    }
    @Override
    public boolean endSale(String username){
        Sale sale = this.curentSalse.get(username);
        if(sale != null){
            Start.archive.endSale(username, sale);
            return true;
        }
        return false;
    }

}