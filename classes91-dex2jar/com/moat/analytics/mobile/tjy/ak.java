// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.webkit.WebView;
import android.view.ViewGroup;
import android.view.View;

public class ak extends MoatFactory
{
    @Override
    public Object createCustomTracker(final ac ac) {
        return null;
    }
    
    @Override
    public NativeDisplayTracker createNativeDisplayTracker(final View view, final String s) {
        return new al();
    }
    
    @Override
    public NativeVideoTracker createNativeVideoTracker(final String s) {
        return new am();
    }
    
    @Override
    public WebAdTracker createWebAdTracker(final ViewGroup viewGroup) {
        return new ao();
    }
    
    @Override
    public WebAdTracker createWebAdTracker(final WebView webView) {
        return new ao();
    }
    
    @Override
    public WebAdTracker createWebDisplayTracker(final ViewGroup viewGroup) {
        return new ao();
    }
    
    @Override
    public WebAdTracker createWebDisplayTracker(final WebView webView) {
        return new ao();
    }
}
