// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk;

import com.ironsource.sdk.data.SSAEnums;
import com.ironsource.sdk.agent.IronSourceAdsPublisherAgent;
import android.app.Activity;
import com.ironsource.sdk.agent.IronSourceAdsAdvertiserAgent;

public class SSAFactory
{
    public static SSAAdvertiser getAdvertiserInstance() {
        return IronSourceAdsAdvertiserAgent.getInstance();
    }
    
    public static SSAPublisher getPublisherInstance(final Activity activity) throws Exception {
        return IronSourceAdsPublisherAgent.getInstance(activity);
    }
    
    public static SSAPublisher getPublisherTestInstance(final Activity activity) throws Exception {
        return IronSourceAdsPublisherAgent.getInstance(activity, SSAEnums.DebugMode.MODE_2.getValue());
    }
    
    public static SSAPublisher getPublisherTestInstance(final Activity activity, final int n) throws Exception {
        return IronSourceAdsPublisherAgent.getInstance(activity, n);
    }
}
