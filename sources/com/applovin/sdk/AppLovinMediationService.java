package com.applovin.sdk;

import com.applovin.mediation.AppLovinMediationAdapterInfo;
import com.applovin.mediation.AppLovinMediationAdapterStats;
import java.util.Collection;

public interface AppLovinMediationService {
    Collection<AppLovinMediationAdapterInfo> getAdapterInfo();

    AppLovinMediationAdapterStats getLastAdapterStats();
}
