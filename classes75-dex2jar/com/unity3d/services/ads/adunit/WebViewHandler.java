// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import android.os.Bundle;
import android.view.View;
import com.unity3d.services.core.misc.ViewUtilities;
import com.unity3d.services.core.webview.WebViewApp;

public class WebViewHandler implements IAdUnitViewHandler
{
    @Override
    public boolean create(final AdUnitActivity adUnitActivity) {
        return true;
    }
    
    @Override
    public boolean destroy() {
        if (WebViewApp.getCurrentApp() != null && WebViewApp.getCurrentApp().getWebView() != null) {
            ViewUtilities.removeViewFromParent((View)WebViewApp.getCurrentApp().getWebView());
        }
        return true;
    }
    
    @Override
    public View getView() {
        if (WebViewApp.getCurrentApp() != null) {
            return (View)WebViewApp.getCurrentApp().getWebView();
        }
        return null;
    }
    
    @Override
    public void onCreate(final AdUnitActivity adUnitActivity, final Bundle bundle) {
    }
    
    @Override
    public void onDestroy(final AdUnitActivity adUnitActivity) {
        this.destroy();
    }
    
    @Override
    public void onPause(final AdUnitActivity adUnitActivity) {
    }
    
    @Override
    public void onResume(final AdUnitActivity adUnitActivity) {
    }
    
    @Override
    public void onStart(final AdUnitActivity adUnitActivity) {
    }
    
    @Override
    public void onStop(final AdUnitActivity adUnitActivity) {
    }
}
