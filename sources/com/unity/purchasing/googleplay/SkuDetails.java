package com.unity.purchasing.googleplay;

import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {
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

    public SkuDetails(String jsonSkuDetails) throws JSONException {
        this("inapp", jsonSkuDetails);
    }

    public SkuDetails(String itemType, String jsonSkuDetails) throws JSONException {
        this.mItemType = itemType;
        this.mJson = jsonSkuDetails;
        JSONObject o = new JSONObject(this.mJson);
        this.mSku = o.optString("productId");
        this.mType = o.optString("type");
        this.mPrice = o.optString("price");
        this.mTitle = o.optString("title");
        this.mDescription = o.optString("description");
        this.priceInMicros = o.optLong("price_amount_micros");
        this.isoCurrencyCode = o.optString("price_currency_code");
        this.subscriptionPeriod = o.optString("subscriptionPeriod");
        this.freeTrialPeriod = o.optString("freeTrialPeriod");
        this.introductoryPrice = o.optString("introductoryPrice");
        this.introductoryPricePeriod = o.optString("introductoryPricePeriod");
        this.introductoryPriceCycles = o.optInt("IntroductoryPriceCycles");
        this.introductoryPriceInMicros = o.optLong("introductoryPriceAmountMicros");
    }

    public String getSku() {
        return this.mSku;
    }

    public String getType() {
        return this.mType;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public long getPriceInMicros() {
        return this.priceInMicros;
    }

    public String getISOCurrencyCode() {
        return this.isoCurrencyCode;
    }

    public String getSubscriptionPeriod() {
        return this.subscriptionPeriod;
    }

    public String getFreeTrialPeriod() {
        return this.freeTrialPeriod;
    }

    public String getIntroductoryPrice() {
        return this.introductoryPrice;
    }

    public String getIntroductoryPricePeriod() {
        return this.introductoryPricePeriod;
    }

    public int getIntroductoryPriceCycles() {
        return this.introductoryPriceCycles;
    }

    public long getIntroductoryPriceInMicros() {
        return this.introductoryPriceInMicros;
    }

    public String toString() {
        return "SkuDetails:" + this.mJson;
    }

    public String getOriginalJSON() {
        return this.mJson;
    }
}
