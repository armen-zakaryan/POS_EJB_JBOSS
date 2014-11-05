package com.aua.businesslogic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Created by Armen on 11-Oct-14.
 */
public class UserList {
    private Map<String, User> userList = new HashMap<String, User>();

    public UserList(){
        load();
    }

    private void load(){
        userList.put("u.user", new User("u.user", "pass", "firstName", "lastName", new Address("Armenia;Erevan;someplace" ) , "093000000" ) );
        userList.put("z.armen", new User("z.armen","pass","Armen","Zakaryan", new Address("Armenia;Erevan;Komitas" ) , "093111111" ) );
        userList.put("t.artur", new User("t.artur","pass","Artur","Tigranyan", new Address("Armenia;Erevan;Opera" ) , "093222222" ) );
        userList.put("v.ruben", new User("v.ruben","pass","Ruben","Vardanyan", new Address("Armenia;Erevan;Marash" ) , "093333333" ) );
        userList.put("h.karen", new User("h.karen","pass","Karen","Hakobyan", new Address("Armenia;Erevan;Echmiadzin" ) , "093444444" ) );
    }

    public String authenticate(String username, String password){
        User user = userList.get(username);
        if(user != null){
            return user.isValidPassword(password);
        }
        else return null;
    }

    public Map<String,User> getUserList(){
        return this.userList;
    }
}
