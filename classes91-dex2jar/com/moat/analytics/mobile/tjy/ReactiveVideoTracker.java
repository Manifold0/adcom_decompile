// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import java.util.Map;
import android.view.View;

public interface ReactiveVideoTracker
{
    void changeTargetView(final View p0);
    
    void dispatchEvent(final MoatAdEvent p0);
    
    void setDebug(final boolean p0);
    
    boolean trackVideoAd(final Map p0, final Integer p1, final View p2);
}
