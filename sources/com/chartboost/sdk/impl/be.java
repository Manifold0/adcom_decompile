package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.Map;

public class be extends WebView {
    /* renamed from: a */
    private bd f3135a;
    /* renamed from: b */
    private boolean f3136b = false;

    public be(Context context) {
        super(context);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void setWebChromeClient(WebChromeClient client) {
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setAllowContentAccess(true);
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(true);
        }
        if (client instanceof bd) {
            this.f3135a = (bd) client;
        }
        super.setWebChromeClient(client);
    }

    public void loadData(String data, String mimeType, String encoding) {
        m3486a();
        super.loadData(data, mimeType, encoding);
    }

    public void loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl) {
        m3486a();
        super.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);
    }

    public void loadUrl(String url) {
        m3486a();
        super.loadUrl(url);
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        m3486a();
        super.loadUrl(url, additionalHttpHeaders);
    }

    /* renamed from: a */
    private void m3486a() {
        if (!this.f3136b) {
            this.f3136b = true;
        }
    }
}
