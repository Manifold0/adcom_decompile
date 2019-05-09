// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import org.json.JSONObject;
import org.json.JSONException;

public class SkuDetails
{
    String freeTrialPeriod;
    String introductoryPrice;
    int introductoryPriceCycles;
    long introductoryPriceInMicros;
    String introductoryPricePeriod;
    String isoCurrencyCode;
    String mDescription;
    String mItemType;
    String mJson;
    String mPrice;
    String mSku;
    String mTitle;
    String mType;
    long priceInMicros;
    String subscriptionPeriod;
    
    public SkuDetails() {
    }
    
    public SkuDetails(final String s) throws JSONException {
        this("inapp", s);
    }
    
    public SkuDetails(final String mItemType, final String mJson) throws JSONException {
        this.mItemType = mItemType;
        this.mJson = mJson;
        final JSONObject jsonObject = new JSONObject(this.mJson);
        this.mSku = jsonObject.optString("productId");
        this.mType = jsonObject.optString("type");
        this.mPrice = jsonObject.optString("price");
        this.mTitle = jsonObject.optString("title");
        this.mDescription = jsonObject.optString("description");
        this.priceInMicros = jsonObject.optLong("price_amount_micros");
        this.isoCurrencyCode = jsonObject.optString("price_currency_code");
        this.subscriptionPeriod = jsonObject.optString("subscriptionPeriod");
        this.freeTrialPeriod = jsonObject.optString("freeTrialPeriod");
        this.introductoryPrice = jsonObject.optString("introductoryPrice");
        this.introductoryPricePeriod = jsonObject.optString("introductoryPricePeriod");
        this.introductoryPriceCycles = jsonObject.optInt("IntroductoryPriceCycles");
        this.introductoryPriceInMicros = jsonObject.optLong("introductoryPriceAmountMicros");
    }
    
    public String getDescription() {
        return this.mDescription;
    }
    
    public String getFreeTrialPeriod() {
        return this.freeTrialPeriod;
    }
    
    public String getISOCurrencyCode() {
        return this.isoCurrencyCode;
    }
    
    public String getIntroductoryPrice() {
        return this.introductoryPrice;
    }
    
    public int getIntroductoryPriceCycles() {
        return this.introductoryPriceCycles;
    }
    
    public long getIntroductoryPriceInMicros() {
        return this.introductoryPriceInMicros;
    }
    
    public String getIntroductoryPricePeriod() {
        return this.introductoryPricePeriod;
    }
    
    public String getOriginalJSON() {
        return this.mJson;
    }
    
    public String getPrice() {
        return this.mPrice;
    }
    
    public long getPriceInMicros() {
        return this.priceInMicros;
    }
    
    public String getSku() {
        return this.mSku;
    }
    
    public String getSubscriptionPeriod() {
        return this.subscriptionPeriod;
    }
    
    public String getTitle() {
        return this.mTitle;
    }
    
    public String getType() {
        return this.mType;
    }
    
    @Override
    public String toString() {
        return "SkuDetails:" + this.mJson;
    }
}
