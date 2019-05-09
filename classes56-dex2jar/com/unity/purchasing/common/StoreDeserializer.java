// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import org.json.JSONArray;
import java.util.List;

public abstract class StoreDeserializer implements INativeStore, IStore
{
    public static List<ProductDefinition> DeserializeProducts(final String s) {
        ArrayList<ProductDefinition> list;
        try {
            final JSONArray jsonArray = new JSONArray(s);
            list = new ArrayList<ProductDefinition>();
            for (int i = 0; i < jsonArray.length(); ++i) {
                list.add(GetProductDefinition(jsonArray.getJSONObject(i)));
            }
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
        return list;
    }
    
    public static ProductDefinition GetProductDefinition(final String s) {
        if (s == null) {
            return null;
        }
        try {
            return GetProductDefinition(new JSONObject(s));
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    private static ProductDefinition GetProductDefinition(final JSONObject jsonObject) {
        try {
            return new ProductDefinition(jsonObject.getString("id"), jsonObject.getString("storeSpecificId"), ProductType.valueOf(jsonObject.getString("type")));
        }
        catch (JSONException ex) {
            throw new RuntimeException((Throwable)ex);
        }
    }
    
    @Override
    public void FinishTransaction(final String s, final String s2) {
        this.FinishTransaction(GetProductDefinition(s), s2);
    }
    
    @Override
    public void Purchase(final String s, final String s2) {
        this.Purchase(GetProductDefinition(s), s2);
    }
    
    @Override
    public void RetrieveProducts(final String s) {
        this.RetrieveProducts(DeserializeProducts(s));
    }
}
