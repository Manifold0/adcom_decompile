package com.ironsource.sdk.listeners.internals;

import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import org.json.JSONObject;

public interface DSAdProductListener {
    void onAdProductClick(ProductType productType, String str);

    void onAdProductClose(ProductType productType, String str);

    void onAdProductEventNotificationReceived(ProductType productType, String str, String str2, JSONObject jSONObject);

    void onAdProductInitFailed(ProductType productType, String str, String str2);

    void onAdProductInitSuccess(ProductType productType, String str, AdUnitsReady adUnitsReady);

    void onAdProductOpen(ProductType productType, String str);
}
