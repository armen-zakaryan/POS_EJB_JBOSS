import com.aua.business.POS;
import com.aua.businesslogic.POSBean;
import com.aua.businesslogic.Product;
import com.aua.clientutility.ClientUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

public class POSTest {

    private static POS bean;
    private String username = "u.user";

    @BeforeClass
    public static void start() throws Exception {
        try {
            bean = (POS)ClientUtility.getInitialContext().lookup("ejb:/ejb_ejb_exploded//" + POSBean.class.getSimpleName() + "!" + POS.class.getName());
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Test
    public void TestAuthenticate() throws Exception {
        String notHappyResponse = bean.authenticate(username,"wrongPass");

        Assert.assertNull(notHappyResponse);
        System.out.println("Please Try Again " + notHappyResponse);

        System.out.println("Authentication ...");
        String happyResponse = bean.authenticate(username,"pass");
        Assert.assertNotNull(happyResponse);
        Assert.assertEquals("Message and response must be equal", username, happyResponse);
        System.out.println("Welcome!!! " + happyResponse);

    }


    @Test
    public void getProductList() throws Exception {
        Map<String,Product> response = bean.getProductList();
        Assert.assertNotNull(response);
        System.out.println("*** Product List ***");
        for (String key: response.keySet()) {
            System.out.println(response.get(key).toString());
        }
    }

    @Before
    public void addItemInit() throws Exception {
        bean.authenticate(username,"pass");
    }
    @Test
    public void addItem() throws Exception {
        boolean res1 = bean.addItem(username, "0025444665189", 5);
        double total1 = bean.getTotalCost(username);
        boolean res2 = bean.addItem(username,"0068700305245",5);
        double total2 = bean.getTotalCost(username);
        Assert.assertTrue(res1);
        Assert.assertTrue(res2);
        Assert.assertTrue(total2 > total1);
    }

    @Before
    public void removeItemInit() throws Exception {
        bean.authenticate(username,"pass");
        bean.addItem(username, "0025444665189", 5);
        bean.addItem(username, "0068700305245", 4);
    }
    @Test
    public void removeItem() throws Exception {
        double total1 = bean.getTotalCost(username);
        boolean delResp = bean.removeItem(username, "0068700305245",5);
        Assert.assertTrue(delResp);
        double total2 = bean.getTotalCost(username);
        Assert.assertTrue(total2<total1);
    }

    @Before
    public void makeSaleInit() throws Exception {
        bean.authenticate(username,"pass");
        bean.addItem(username, "0025444665189", 5);
        bean.addItem(username, "0068700305245", 5);
    }
    @Test
    public void makeAndEndSale() throws Exception {
        ArrayList<Product> notAddedCurtItemsList = (ArrayList<Product>)bean.makeSale(username);
        Assert.assertTrue(notAddedCurtItemsList.size() == 0);
        double total = bean.getSaleTotal(username);
        System.out.println("Sale total is "+total);
        boolean archiveCreation = bean.endSale(username);
        Assert.assertTrue(archiveCreation);
    }
}