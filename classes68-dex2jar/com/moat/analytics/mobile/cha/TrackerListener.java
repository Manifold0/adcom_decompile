// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

public interface TrackerListener
{
    void onTrackingFailedToStart(final String p0);
    
    void onTrackingStarted(final String p0);
    
    void onTrackingStopped(final String p0);
}
