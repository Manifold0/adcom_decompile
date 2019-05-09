package com.applovin.mediation;

import com.ironsource.sdk.constants.Constants.RequestParameters;

public class AppLovinMediationAdapterInfo {
    /* renamed from: a */
    private final String f2639a;
    /* renamed from: b */
    private final String f2640b;
    /* renamed from: c */
    private final String f2641c;
    /* renamed from: d */
    private final AppLovinMediationAdapterStatus f2642d;
    /* renamed from: e */
    private final AppLovinMediationAdapter f2643e;
    /* renamed from: f */
    private final AppLovinMediationAdapterConfig f2644f;

    public AppLovinMediationAdapterInfo(String str, String str2, String str3, AppLovinMediationAdapterStatus appLovinMediationAdapterStatus) {
        this(str, str2, str3, appLovinMediationAdapterStatus, null, null);
    }

    public AppLovinMediationAdapterInfo(String str, String str2, String str3, AppLovinMediationAdapterStatus appLovinMediationAdapterStatus, AppLovinMediationAdapter appLovinMediationAdapter, AppLovinMediationAdapterConfig appLovinMediationAdapterConfig) {
        if (str == null) {
            throw new IllegalArgumentException("No name specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No class name specified");
        } else if (appLovinMediationAdapterStatus == null) {
            throw new IllegalArgumentException("No status specified");
        } else {
            this.f2639a = str;
            this.f2640b = str2;
            this.f2641c = str3;
            this.f2642d = appLovinMediationAdapterStatus;
            this.f2643e = appLovinMediationAdapter;
            this.f2644f = appLovinMediationAdapterConfig;
        }
    }

    public AppLovinMediationAdapter getAdapter() {
        return this.f2643e;
    }

    public AppLovinMediationAdapterConfig getAdapterConfiguration() {
        return this.f2644f;
    }

    public String getClassName() {
        return this.f2640b;
    }

    public String getName() {
        return this.f2639a;
    }

    public AppLovinMediationAdapterStatus getStatus() {
        return this.f2642d;
    }

    public String getVersion() {
        return this.f2641c;
    }

    public String toString() {
        return "[Adapter Info - <" + this.f2639a + " : " + this.f2640b + "> v" + this.f2641c + " with configuration: " + this.f2644f + RequestParameters.RIGHT_BRACKETS;
    }
}
