// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.mediation;

public class AppLovinMediationAdapterInfo
{
    private final String a;
    private final String b;
    private final String c;
    private final AppLovinMediationAdapterStatus d;
    private final AppLovinMediationAdapter e;
    private final AppLovinMediationAdapterConfig f;
    
    public AppLovinMediationAdapterInfo(final String s, final String s2, final String s3, final AppLovinMediationAdapterStatus appLovinMediationAdapterStatus) {
        this(s, s2, s3, appLovinMediationAdapterStatus, null, null);
    }
    
    public AppLovinMediationAdapterInfo(final String a, final String b, final String c, final AppLovinMediationAdapterStatus d, final AppLovinMediationAdapter e, final AppLovinMediationAdapterConfig f) {
        if (a == null) {
            throw new IllegalArgumentException("No name specified");
        }
        if (b == null) {
            throw new IllegalArgumentException("No class name specified");
        }
        if (d == null) {
            throw new IllegalArgumentException("No status specified");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }
    
    public AppLovinMediationAdapter getAdapter() {
        return this.e;
    }
    
    public AppLovinMediationAdapterConfig getAdapterConfiguration() {
        return this.f;
    }
    
    public String getClassName() {
        return this.b;
    }
    
    public String getName() {
        return this.a;
    }
    
    public AppLovinMediationAdapterStatus getStatus() {
        return this.d;
    }
    
    public String getVersion() {
        return this.c;
    }
    
    @Override
    public String toString() {
        return "[Adapter Info - <" + this.a + " : " + this.b + "> v" + this.c + " with configuration: " + this.f + "]";
    }
}
