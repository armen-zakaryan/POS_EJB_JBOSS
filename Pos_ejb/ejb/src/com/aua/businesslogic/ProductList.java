package com.aua.businesslogic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Armen on 11-Oct-14.
 */
public class ProductList {
    private Map<String, Product> productList = new HashMap<>();

    public ProductList(){
        load();
    }

    private void load(){
        this.productList.put( "0885909599639", new Product("0885909599639","iphone",1000,1 ) );
        this.productList.put( "0068700305245", new Product("0068700305245","milk",5,10 ) );
        this.productList.put( "0068721004004", new Product("0068721004004","bread",2,120 ) );
        this.productList.put( "0025444665189", new Product("0025444665189","eggs",1,10 ) );
    }

    public Map<String,Product> getProductList(){
        return this.productList;
    }

}
