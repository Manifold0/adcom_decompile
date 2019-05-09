// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Purchase
{
    boolean autoRenewing;
    String mDeveloperPayload;
    String mItemType;
    String mOrderId;
    String mOriginalJson;
    String mPackageName;
    int mPurchaseState;
    long mPurchaseTime;
    String mSignature;
    String mSku;
    String mToken;
    
    public Purchase(final String mItemType, final String mOriginalJson, final String mSignature) throws JSONException {
        this.mItemType = mItemType;
        this.mOriginalJson = mOriginalJson;
        final JSONObject jsonObject = new JSONObject(this.mOriginalJson);
        this.mOrderId = jsonObject.optString("orderId");
        this.mPackageName = jsonObject.optString("packageName");
        this.mSku = jsonObject.optString("productId");
        this.mPurchaseTime = jsonObject.optLong("purchaseTime");
        this.mPurchaseState = jsonObject.optInt("purchaseState");
        this.mDeveloperPayload = jsonObject.optString("developerPayload");
        this.mToken = jsonObject.optString("token", jsonObject.optString("purchaseToken"));
        this.mSignature = mSignature;
        this.autoRenewing = jsonObject.optBoolean("autoRenewing");
    }
    
    public boolean getAutoRenewing() {
        return this.autoRenewing;
    }
    
    public String getDeveloperPayload() {
        return this.mDeveloperPayload;
    }
    
    public String getItemType() {
        return this.mItemType;
    }
    
    public String getOrderIdOrPurchaseToken() {
        if (TextUtils.isEmpty((CharSequence)this.mOrderId)) {
            return this.mToken;
        }
        return this.mOrderId;
    }
    
    public String getOriginalJson() {
        return this.mOriginalJson;
    }
    
    public String getPackageName() {
        return this.mPackageName;
    }
    
    public int getPurchaseState() {
        return this.mPurchaseState;
    }
    
    public long getPurchaseTime() {
        return this.mPurchaseTime;
    }
    
    public String getSignature() {
        return this.mSignature;
    }
    
    public String getSku() {
        return this.mSku;
    }
    
    public String getToken() {
        return this.mToken;
    }
    
    @Override
    public String toString() {
        return "PurchaseInfo(type:" + this.mItemType + "):" + this.mOriginalJson;
    }
}
