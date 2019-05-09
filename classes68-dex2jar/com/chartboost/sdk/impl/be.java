// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.os.Build$VERSION;
import android.webkit.WebChromeClient;
import java.util.Map;
import android.content.Context;
import android.webkit.WebView;

public class be extends WebView
{
    private bd a;
    private boolean b;
    
    public be(final Context context) {
        super(context);
        this.b = false;
    }
    
    private void a() {
        if (!this.b) {
            this.b = true;
        }
    }
    
    public void loadData(final String s, final String s2, final String s3) {
        this.a();
        super.loadData(s, s2, s3);
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.a();
        super.loadDataWithBaseURL(s, s2, s3, s4, s5);
    }
    
    public void loadUrl(final String s) {
        this.a();
        super.loadUrl(s);
    }
    
    public void loadUrl(final String s, final Map<String, String> map) {
        this.a();
        super.loadUrl(s, (Map)map);
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    public void setWebChromeClient(final WebChromeClient webChromeClient) {
        final WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(true);
        if (Build$VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        if (Build$VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        if (webChromeClient instanceof bd) {
            this.a = (bd)webChromeClient;
        }
        super.setWebChromeClient(webChromeClient);
    }
}
