package com.ironsource.sdk.handlers;

import android.app.Activity;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.controller.IronSourceWebView;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;

public class BackButtonHandler {
    public static BackButtonHandler mInstance;

    public static BackButtonHandler getInstance() {
        if (mInstance == null) {
            return new BackButtonHandler();
        }
        return mInstance;
    }

    public boolean handleBackButton(Activity activity) {
        switch (IronSourceSharedPrefHelper.getSupersonicPrefHelper().getBackButtonState()) {
            case Controller:
                try {
                    IronSourceWebView webViewController = IronSourceAdsPublisherAgent.getInstance(activity).getWebViewController();
                    if (webViewController != null) {
                        webViewController.nativeNavigationPressed("back");
                    }
                    return true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
            default:
                return false;
        }
    }
}
