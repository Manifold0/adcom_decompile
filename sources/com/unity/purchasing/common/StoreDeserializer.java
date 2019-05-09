package com.unity.purchasing.common;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class StoreDeserializer implements INativeStore, IStore {
    public void RetrieveProducts(String json) {
        RetrieveProducts(DeserializeProducts(json));
    }

    public void Purchase(String productJSON, String developerPayload) {
        Purchase(GetProductDefinition(productJSON), developerPayload);
    }

    public void FinishTransaction(String productJSON, String transactionID) {
        FinishTransaction(GetProductDefinition(productJSON), transactionID);
    }

    public static List<ProductDefinition> DeserializeProducts(String json) {
        try {
            JSONArray array = new JSONArray(json);
            List<ProductDefinition> result = new ArrayList();
            for (int t = 0; t < array.length(); t++) {
                result.add(GetProductDefinition(array.getJSONObject(t)));
            }
            return result;
        } catch (JSONException j) {
            throw new RuntimeException(j);
        }
    }

    public static ProductDefinition GetProductDefinition(String json) {
        if (json == null) {
            return null;
        }
        try {
            return GetProductDefinition(new JSONObject(json));
        } catch (JSONException r) {
            throw new RuntimeException(r);
        }
    }

    private static ProductDefinition GetProductDefinition(JSONObject j) {
        try {
            return new ProductDefinition(j.getString("id"), j.getString("storeSpecificId"), ProductType.valueOf(j.getString("type")));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
