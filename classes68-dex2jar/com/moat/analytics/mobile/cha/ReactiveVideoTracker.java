// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import java.util.Map;
import android.app.Activity;
import android.view.View;

public interface ReactiveVideoTracker
{
    void changeTargetView(final View p0);
    
    void dispatchEvent(final MoatAdEvent p0);
    
    void removeListener();
    
    void removeVideoListener();
    
    @Deprecated
    void setActivity(final Activity p0);
    
    void setListener(final TrackerListener p0);
    
    void setPlayerVolume(final Double p0);
    
    void setVideoListener(final VideoTrackerListener p0);
    
    void stopTracking();
    
    boolean trackVideoAd(final Map<String, String> p0, final Integer p1, final View p2);
}
