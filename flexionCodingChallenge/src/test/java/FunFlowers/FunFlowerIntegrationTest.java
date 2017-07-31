package FunFlowers;

import com.flexionmobile.codingchallenge.integration.Purchase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FunFlowerIntegrationTest {
    FunFlowerIntegration funFlowerIntegration;
    @Before
    public void setup(){
        funFlowerIntegration = new FunFlowerIntegration();
    }

    @Test
    public void getPurchaseFromJson() throws Exception {
        String purchaseJson = "{\n" +
                "\t\"consumed\": \"false\",\n" +
                "\t\"id\": \"37536e7a-4e95-4862-89b5-c16e60b1f7d4\",\n" +
                "\t\"itemId\": \"item1\"\n" +
                "}";

        FunFlowerPurchase funFlowerPurchase = funFlowerIntegration.getPurchaseFromJson(purchaseJson);

        assertEquals("37536e7a-4e95-4862-89b5-c16e60b1f7d4", funFlowerPurchase.getId());
        assertEquals("item1", funFlowerPurchase.getItemId());
        assertEquals(false, funFlowerPurchase.getConsumed());
    }

    @Test
    public void getPurchasesFromJson() throws Exception{
        String purchaseJson = "{\n" +
                "\t\"purchases\": [{\n" +
                "\t\t\t\"consumed\": \"false\",\n" +
                "\t\t\t\"id\": \"d408199f-ae57-44a5-80a3-1ab7aeab9a20\",\n" +
                "\t\t\t\"itemId\": \"item1\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"consumed\": \"false\",\n" +
                "\t\t\t\"id\": \"6a78bdac-23b5-4a92-94ec-bd037c8c2ad9\",\n" +
                "\t\t\t\"itemId\": \"item2\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        Purchases funFlowerPurchases = funFlowerIntegration.getPurchases(purchaseJson);

        assertEquals(2, funFlowerPurchases.getPurchases().size());
    }

}