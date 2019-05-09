// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import com.applovin.mediation.AppLovinMediationAdapterStats;
import com.applovin.mediation.AppLovinMediationAdapterInfo;
import java.util.Collection;

public interface AppLovinMediationService
{
    Collection<AppLovinMediationAdapterInfo> getAdapterInfo();
    
    AppLovinMediationAdapterStats getLastAdapterStats();
}
