// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import android.webkit.WebView;
import android.view.ViewGroup;
import java.util.Map;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.annotation.UiThread;

public abstract class MoatFactory
{
    public static MoatFactory create() {
        try {
            return new k();
        }
        catch (Exception ex) {
            o.\u02ce(ex);
            return new NoOp.MoatFactory();
        }
    }
    
    @UiThread
    public abstract <T> T createCustomTracker(final l<T> p0);
    
    @UiThread
    public abstract NativeDisplayTracker createNativeDisplayTracker(@NonNull final View p0, @NonNull final Map<String, String> p1);
    
    @UiThread
    public abstract NativeVideoTracker createNativeVideoTracker(final String p0);
    
    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull final ViewGroup p0);
    
    @UiThread
    public abstract WebAdTracker createWebAdTracker(@NonNull final WebView p0);
}
