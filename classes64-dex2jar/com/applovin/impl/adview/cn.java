// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.content.Context;
import android.webkit.WebView;

public class cn extends WebView
{
    public cn(final co webViewClient, final Context context) {
        super(context);
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptEnabled(true);
        this.setWebViewClient((WebViewClient)webViewClient);
        this.setWebChromeClient(new WebChromeClient());
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.setScrollBarStyle(33554432);
    }
    
    public void a(final String s) {
        this.loadDataWithBaseURL("/", s, "text/html", (String)null, "");
    }
}
