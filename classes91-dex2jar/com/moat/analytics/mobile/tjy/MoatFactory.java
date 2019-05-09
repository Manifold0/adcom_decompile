// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.webkit.WebView;
import android.view.ViewGroup;
import android.view.View;
import com.moat.analytics.mobile.tjy.base.exception.b;
import com.moat.analytics.mobile.tjy.base.exception.a;
import android.app.Activity;

public abstract class MoatFactory
{
    public static MoatFactory create(final Activity activity) {
        try {
            return new v(activity);
        }
        catch (b b) {
            a.a(b);
            return new ak();
        }
    }
    
    public abstract Object createCustomTracker(final ac p0);
    
    public abstract NativeDisplayTracker createNativeDisplayTracker(final View p0, final String p1);
    
    public abstract NativeVideoTracker createNativeVideoTracker(final String p0);
    
    public abstract WebAdTracker createWebAdTracker(final ViewGroup p0);
    
    public abstract WebAdTracker createWebAdTracker(final WebView p0);
    
    @Deprecated
    public abstract WebAdTracker createWebDisplayTracker(final ViewGroup p0);
    
    @Deprecated
    public abstract WebAdTracker createWebDisplayTracker(final WebView p0);
}
