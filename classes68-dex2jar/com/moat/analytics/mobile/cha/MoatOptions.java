// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

public class MoatOptions
{
    public boolean autoTrackGMAInterstitials;
    public boolean disableAdIdCollection;
    public boolean disableLocationServices;
    public boolean loggingEnabled;
    
    public MoatOptions() {
        this.disableAdIdCollection = false;
        this.autoTrackGMAInterstitials = false;
        this.disableLocationServices = false;
        this.loggingEnabled = false;
    }
}
