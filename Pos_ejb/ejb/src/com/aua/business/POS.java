package com.aua.business;

import com.aua.businesslogic.Product;
import javax.ejb.Remote;
import java.util.ArrayList;
import java.util.Map;

@Remote
public interface POS {

    public Map<String,Product> getProductList();
    public String authenticate(String username, String password);
    public boolean addItem(String userName, String ups, int amount);
    public boolean removeItem(String userName, String ups, int amount);
    public double getTotalCost(String userName);
    public ArrayList makeSale(String userName);
    public double getSaleTotal(String userName);
    public boolean endSale(String username);
}