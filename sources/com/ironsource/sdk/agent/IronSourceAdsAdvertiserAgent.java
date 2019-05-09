package com.ironsource.sdk.agent;

import android.content.Context;
import com.ironsource.sdk.SSAAdvertiser;

public class IronSourceAdsAdvertiserAgent implements SSAAdvertiser {
    private static final String TAG = "IronSourceAdsAdvertiserAgent";
    static IronSourceAdsAdvertiserAgent sInstance;

    private IronSourceAdsAdvertiserAgent() {
    }

    public static synchronized IronSourceAdsAdvertiserAgent getInstance() {
        IronSourceAdsAdvertiserAgent ironSourceAdsAdvertiserAgent;
        synchronized (IronSourceAdsAdvertiserAgent.class) {
            if (sInstance == null) {
                sInstance = new IronSourceAdsAdvertiserAgent();
            }
            ironSourceAdsAdvertiserAgent = sInstance;
        }
        return ironSourceAdsAdvertiserAgent;
    }

    @Deprecated
    public void reportAppStarted(Context context) {
    }
}
