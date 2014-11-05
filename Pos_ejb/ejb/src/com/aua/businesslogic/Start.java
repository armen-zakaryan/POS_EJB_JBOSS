package com.aua.businesslogic;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by Armen on 03-Nov-14.
 */

@Singleton
@Startup
public class Start {


    public static ProductList productList = null;
    public static UserList userList = null;
    public static Archive archive = null;

    /*
    public Start() {
        System.out.println("Singleton was called");
        this.userList = new UserList();
        this.productList = new ProductList();
        this.archive = new Archive();
    }
    */

    @PostConstruct
    public void setupTheatre(){
        this.userList = new UserList();
        this.productList = new ProductList();
        this.archive = new Archive();
    }
}