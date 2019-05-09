package com.ironsource.mediationsdk.sdk;

import android.app.Activity;
import org.json.JSONObject;

public interface OfferwallAdapterApi {
    void getOfferwallCredits();

    void initOfferwall(Activity activity, String str, String str2, JSONObject jSONObject);

    boolean isOfferwallAvailable();

    void setInternalOfferwallListener(InternalOfferwallListener internalOfferwallListener);

    void showOfferwall(String str, JSONObject jSONObject);
}
