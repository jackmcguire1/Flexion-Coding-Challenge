package FunFlowers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.util.HashMap;
import java.util.Map;

public class FunFlowerPurchase implements Purchase {

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @JsonProperty

    private boolean consumed;
    @JsonProperty("id")
    private String id;
    @JsonProperty("itemId")
    private String itemId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean getConsumed() {
        return consumed;
    }

    @Override
    public String getItemId() {
        return itemId;
    }
}
