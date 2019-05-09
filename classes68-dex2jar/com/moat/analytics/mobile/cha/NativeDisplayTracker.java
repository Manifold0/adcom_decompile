// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.support.annotation.UiThread;

@UiThread
public interface NativeDisplayTracker
{
    void removeListener();
    
    void reportUserInteractionEvent(final MoatUserInteractionType p0);
    
    @Deprecated
    void setActivity(final Activity p0);
    
    void setListener(final TrackerListener p0);
    
    void startTracking();
    
    void stopTracking();
    
    public enum MoatUserInteractionType
    {
        CLICK, 
        TOUCH;
    }
}
