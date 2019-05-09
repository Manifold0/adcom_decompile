package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface OfferwallApi {
    void getOfferwallCredits();

    void initOfferwall(Activity activity, String str, String str2);

    boolean isOfferwallAvailable();

    void setOfferwallListener(OfferwallListener offerwallListener);

    void showOfferwall();

    void showOfferwall(String str);
}
