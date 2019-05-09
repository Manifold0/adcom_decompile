// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.webkit.WebView;
import android.view.ViewGroup;
import java.util.Map;
import android.view.View;

public abstract class MoatFactory
{
    public static MoatFactory create() {
        try {
            return new n();
        }
        catch (Exception ex) {
            m.a(ex);
            return new v.b();
        }
    }
    
    public abstract <T> T createCustomTracker(final MoatPlugin<T> p0);
    
    public abstract NativeDisplayTracker createNativeDisplayTracker(final View p0, final Map<String, String> p1);
    
    public abstract NativeVideoTracker createNativeVideoTracker(final String p0);
    
    public abstract WebAdTracker createWebAdTracker(final ViewGroup p0);
    
    public abstract WebAdTracker createWebAdTracker(final WebView p0);
}
