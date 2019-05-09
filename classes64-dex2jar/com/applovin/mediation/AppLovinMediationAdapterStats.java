// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.mediation;

public class AppLovinMediationAdapterStats
{
    private final String a;
    private final long b;
    
    public AppLovinMediationAdapterStats(final String a, final long b) {
        if (a == null) {
            throw new IllegalArgumentException("No adapter name specified");
        }
        this.a = a;
        this.b = b;
    }
    
    public String getAdapterName() {
        return this.a;
    }
    
    public long getLastAdLoadMillis() {
        return this.b;
    }
    
    @Override
    public String toString() {
        return "[Adapter Stats - <" + this.a + " : loaded in " + this.b + "milliseconds>]";
    }
}
