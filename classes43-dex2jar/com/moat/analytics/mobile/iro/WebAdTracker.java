// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.support.annotation.UiThread;

@UiThread
public interface WebAdTracker
{
    void removeListener();
    
    @Deprecated
    void setActivity(final Activity p0);
    
    void setListener(final TrackerListener p0);
    
    void startTracking();
    
    void stopTracking();
}
