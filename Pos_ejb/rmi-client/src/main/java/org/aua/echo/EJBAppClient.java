package org.aua.echo;

import com.aua.business.POS;
import com.aua.businesslogic.POSBean;
import com.aua.clientutility.ClientUtility;

import javax.naming.Context;
import javax.naming.NamingException;

public class EJBAppClient {

    public static void main(String[] args) {
        POS bean = doLookup();
        System.out.println("*****  "+bean.authenticate("u.user","pass"));
    }


    private static POS doLookup() {
        Context context = null;
        POS bean = null;
        try {
            // 1. Obtaining Context
            context = ClientUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            bean = (POS) context.lookup("ejb:/ejb_ejb_exploded//" + POSBean.class.getSimpleName() + "!" + POS.class.getName());
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
    }

}