package FunFlowers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import org.apache.http.client.utils.URIBuilder;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunFlowerIntegration implements Integration {
    private static String DEVELOPER_ID = "FunFlowers";
    private static String HOST = "sandbox.flexionmobile.com";
    private static String DEFAULT_PATH = "/javachallenge/rest/developer/" + DEVELOPER_ID + "/";


    public static void main(String[] args) throws Exception {
        FunFlowerIntegration funFlowIntegration = new FunFlowerIntegration();
        IntegrationTestRunner integrationTestRunner = new IntegrationTestRunner();
        integrationTestRunner.runTests(funFlowIntegration);
    }


    @Override
    public Purchase buy(String itemId) {

        FunFlowerPurchase purchase = new FunFlowerPurchase();
        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setHost(HOST);
            uriBuilder.setScheme("http");
            uriBuilder.setPath(DEFAULT_PATH + "buy/" + itemId);

            HttpClientComms httpClientComms = new HttpClientComms();
            String purchaseJson = httpClientComms.sendPost(uriBuilder.build());
            purchase = getPurchaseFromJson(purchaseJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchase;
    }

    @Override
    public List<Purchase> getPurchases() {
        List<Purchase> list = new ArrayList<>();
        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setHost(HOST);
            uriBuilder.setScheme("http");
            uriBuilder.setPath(DEFAULT_PATH + "all/");

            HttpClientComms httpClientComms = new HttpClientComms();
            String purchaseList = httpClientComms.sendGet(uriBuilder.build());

            Purchases purchases = getPurchases(purchaseList);
            list.addAll(purchases.getPurchases());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void consume( Purchase newPurchase) {
        try {
            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setHost(HOST);
            uriBuilder.setScheme("http");
            uriBuilder.setPath(DEFAULT_PATH + "consume/" + newPurchase.getId());

            HttpClientComms httpClientComms = new HttpClientComms();
            httpClientComms.sendPost(uriBuilder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FunFlowerPurchase getPurchaseFromJson(String purchaseJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FunFlowerPurchase purchase = mapper.readValue(purchaseJson, FunFlowerPurchase.class);
        return purchase;
    }

    public Purchases getPurchases(String purchaseList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(purchaseList, Purchases.class);
    }

}
