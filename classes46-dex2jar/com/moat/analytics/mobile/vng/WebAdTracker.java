// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.app.Activity;

public interface WebAdTracker
{
    @Deprecated
    void setActivity(final Activity p0);
    
    void startTracking();
    
    void stopTracking();
}
