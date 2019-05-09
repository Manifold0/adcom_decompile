// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;

public class Inventory
{
    Map<String, Purchase> mConsumablePurchaseHistoryMap;
    Set<String> mPurchaseHistoryOfSub;
    Map<String, Purchase> mPurchaseMap;
    public Map<String, SkuDetails> mSkuMap;
    
    Inventory() {
        this.mSkuMap = new HashMap<String, SkuDetails>();
        this.mPurchaseMap = new HashMap<String, Purchase>();
        this.mConsumablePurchaseHistoryMap = new HashMap<String, Purchase>();
        this.mPurchaseHistoryOfSub = new HashSet<String>();
    }
    
    void addPurchase(final Purchase purchase) {
        this.mPurchaseMap.put(purchase.getSku(), purchase);
    }
    
    void addPurchaseToConsumablePurchaseHistory(final String s, final Purchase purchase) {
        this.mConsumablePurchaseHistoryMap.put(s, purchase);
    }
    
    void addPurchaseToSubscriptionPurchaseHistory(final String s) {
        this.mPurchaseHistoryOfSub.add(s);
    }
    
    void addSkuDetails(final SkuDetails skuDetails) {
        this.mSkuMap.put(skuDetails.getSku(), skuDetails);
    }
    
    public void erasePurchase(final String s) {
        if (this.mPurchaseMap.containsKey(s)) {
            this.mPurchaseMap.remove(s);
        }
    }
    
    List<String> getAllOwnedSkus() {
        return new ArrayList<String>(this.mPurchaseMap.keySet());
    }
    
    List<String> getAllOwnedSkus(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        for (final Purchase purchase : this.mPurchaseMap.values()) {
            if (purchase.getItemType().equals(s)) {
                list.add(purchase.getSku());
            }
        }
        return list;
    }
    
    List<Purchase> getAllPurchases() {
        return new ArrayList<Purchase>(this.mPurchaseMap.values());
    }
    
    List<String> getAllSkus(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        for (final SkuDetails skuDetails : this.mSkuMap.values()) {
            if (skuDetails.getType().equals(s)) {
                list.add(skuDetails.getSku());
            }
        }
        return list;
    }
    
    public Purchase getHistoryPurchase(final String s) {
        return this.mConsumablePurchaseHistoryMap.get(s);
    }
    
    public Purchase getPurchase(final String s) {
        return this.mPurchaseMap.get(s);
    }
    
    public SkuDetails getSkuDetails(final String s) {
        return this.mSkuMap.get(s);
    }
    
    public List<String> getSubscriptionsWithHistory() {
        return new ArrayList<String>(this.mPurchaseHistoryOfSub);
    }
    
    public boolean hasConsumablePurchaseHistory(final String s) {
        return this.mConsumablePurchaseHistoryMap.containsKey(s);
    }
    
    public boolean hasDetails(final String s) {
        return this.mSkuMap.containsKey(s);
    }
    
    public boolean hasPurchase(final String s) {
        return this.mPurchaseMap.containsKey(s);
    }
    
    public boolean hasPurchaseHistory(final String s) {
        return this.mPurchaseHistoryOfSub.contains(s);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("skuDetails = ");
        sb.append("[");
        for (final String s : this.mSkuMap.keySet()) {
            sb.append(s);
            sb.append(" = ");
            sb.append(this.getSkuDetails(s));
            sb.append(", ");
        }
        sb.append("]");
        sb.append(", purchases = ");
        sb.append("[");
        for (final String s2 : this.mPurchaseMap.keySet()) {
            sb.append(s2);
            sb.append(" = ");
            sb.append(this.getSkuDetails(s2));
            sb.append(", ");
        }
        sb.append("]");
        return "{Inventory: " + sb.toString() + "}";
    }
}
