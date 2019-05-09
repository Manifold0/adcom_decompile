// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.app.Activity;

public interface NativeDisplayTracker
{
    void reportUserInteractionEvent(final MoatUserInteractionType p0);
    
    @Deprecated
    void setActivity(final Activity p0);
    
    void startTracking();
    
    void stopTracking();
    
    public enum MoatUserInteractionType
    {
        CLICK, 
        TOUCH;
    }
}
