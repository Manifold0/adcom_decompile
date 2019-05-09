package com.unity.purchasing.googleplay;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Inventory {
    Map<String, Purchase> mConsumablePurchaseHistoryMap = new HashMap();
    Set<String> mPurchaseHistoryOfSub = new HashSet();
    Map<String, Purchase> mPurchaseMap = new HashMap();
    public Map<String, SkuDetails> mSkuMap = new HashMap();

    Inventory() {
    }

    public boolean hasPurchaseHistory(String productId) {
        return this.mPurchaseHistoryOfSub.contains(productId);
    }

    public List<String> getSubscriptionsWithHistory() {
        return new ArrayList(this.mPurchaseHistoryOfSub);
    }

    public SkuDetails getSkuDetails(String sku) {
        return (SkuDetails) this.mSkuMap.get(sku);
    }

    public Purchase getPurchase(String sku) {
        return (Purchase) this.mPurchaseMap.get(sku);
    }

    public Purchase getHistoryPurchase(String sku) {
        return (Purchase) this.mConsumablePurchaseHistoryMap.get(sku);
    }

    public boolean hasPurchase(String sku) {
        return this.mPurchaseMap.containsKey(sku);
    }

    public boolean hasConsumablePurchaseHistory(String sku) {
        return this.mConsumablePurchaseHistoryMap.containsKey(sku);
    }

    public boolean hasDetails(String sku) {
        return this.mSkuMap.containsKey(sku);
    }

    public void erasePurchase(String sku) {
        if (this.mPurchaseMap.containsKey(sku)) {
            this.mPurchaseMap.remove(sku);
        }
    }

    List<String> getAllOwnedSkus() {
        return new ArrayList(this.mPurchaseMap.keySet());
    }

    List<String> getAllSkus(String itemType) {
        List<String> result = new ArrayList();
        for (SkuDetails sku : this.mSkuMap.values()) {
            if (sku.getType().equals(itemType)) {
                result.add(sku.getSku());
            }
        }
        return result;
    }

    List<String> getAllOwnedSkus(String itemType) {
        List<String> result = new ArrayList();
        for (Purchase p : this.mPurchaseMap.values()) {
            if (p.getItemType().equals(itemType)) {
                result.add(p.getSku());
            }
        }
        return result;
    }

    List<Purchase> getAllPurchases() {
        return new ArrayList(this.mPurchaseMap.values());
    }

    void addSkuDetails(SkuDetails d) {
        this.mSkuMap.put(d.getSku(), d);
    }

    void addPurchase(Purchase p) {
        this.mPurchaseMap.put(p.getSku(), p);
    }

    void addPurchaseToSubscriptionPurchaseHistory(String sku) {
        this.mPurchaseHistoryOfSub.add(sku);
    }

    void addPurchaseToConsumablePurchaseHistory(String sku, Purchase p) {
        this.mConsumablePurchaseHistoryMap.put(sku, p);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("skuDetails = ");
        builder.append(RequestParameters.LEFT_BRACKETS);
        for (String key : this.mSkuMap.keySet()) {
            builder.append(key);
            builder.append(" = ");
            builder.append(getSkuDetails(key));
            builder.append(", ");
        }
        builder.append(RequestParameters.RIGHT_BRACKETS);
        builder.append(", purchases = ");
        builder.append(RequestParameters.LEFT_BRACKETS);
        for (String key2 : this.mPurchaseMap.keySet()) {
            builder.append(key2);
            builder.append(" = ");
            builder.append(getSkuDetails(key2));
            builder.append(", ");
        }
        builder.append(RequestParameters.RIGHT_BRACKETS);
        return "{Inventory: " + builder.toString() + "}";
    }
}
