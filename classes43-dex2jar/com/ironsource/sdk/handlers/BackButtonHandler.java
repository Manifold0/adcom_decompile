// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.handlers;

import com.ironsource.sdk.controller.IronSourceWebView;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import android.app.Activity;

public class BackButtonHandler
{
    public static BackButtonHandler mInstance;
    
    public static BackButtonHandler getInstance() {
        if (BackButtonHandler.mInstance == null) {
            return new BackButtonHandler();
        }
        return BackButtonHandler.mInstance;
    }
    
    public boolean handleBackButton(final Activity activity) {
        switch (IronSourceSharedPrefHelper.getSupersonicPrefHelper().getBackButtonState()) {
            default: {
                return false;
            }
            case Controller: {
                try {
                    final IronSourceWebView webViewController = IronSourceAdsPublisherAgent.getInstance(activity).getWebViewController();
                    if (webViewController != null) {
                        webViewController.nativeNavigationPressed("back");
                    }
                    return true;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return false;
                }
                break;
            }
        }
    }
}
