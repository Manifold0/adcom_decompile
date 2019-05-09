// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.agent;

import android.content.Context;
import com.ironsource.sdk.SSAAdvertiser;

public class IronSourceAdsAdvertiserAgent implements SSAAdvertiser
{
    private static final String TAG = "IronSourceAdsAdvertiserAgent";
    static IronSourceAdsAdvertiserAgent sInstance;
    
    private IronSourceAdsAdvertiserAgent() {
    }
    
    public static IronSourceAdsAdvertiserAgent getInstance() {
        synchronized (IronSourceAdsAdvertiserAgent.class) {
            if (IronSourceAdsAdvertiserAgent.sInstance == null) {
                IronSourceAdsAdvertiserAgent.sInstance = new IronSourceAdsAdvertiserAgent();
            }
            return IronSourceAdsAdvertiserAgent.sInstance;
        }
    }
    
    @Deprecated
    @Override
    public void reportAppStarted(final Context context) {
    }
}
