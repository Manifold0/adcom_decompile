// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.listener;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.ConsoleMessage;

public interface MraidViewListener
{
    boolean onClose();
    
    boolean onConsoleMessage(final ConsoleMessage p0);
    
    boolean onEventFired();
    
    void onPageFinished(final WebView p0, final String p1);
    
    void onPageStarted(final WebView p0, final String p1, final Bitmap p2);
    
    boolean onReady();
    
    void onReceivedError(final WebView p0, final int p1, final String p2, final String p3);
    
    boolean onResize();
    
    boolean onResizeClose();
    
    boolean shouldOverrideUrlLoading(final WebView p0, final String p1);
}
