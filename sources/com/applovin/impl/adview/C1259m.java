package com.applovin.impl.adview;

import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;

/* renamed from: com.applovin.impl.adview.m */
class C1259m extends WebChromeClient {
    /* renamed from: a */
    private final AppLovinLogger f1896a;

    public C1259m(AppLovinSdk appLovinSdk) {
        this.f1896a = appLovinSdk.getLogger();
    }

    public void onConsoleMessage(String str, int i, String str2) {
        this.f1896a.mo4178w("AdWebView", "console.log[" + i + "] :" + str);
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        this.f1896a.mo4172d("AdWebView", consoleMessage.sourceId() + ": " + consoleMessage.lineNumber() + ": " + consoleMessage.message());
        return true;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        this.f1896a.mo4178w("AdWebView", "Alert attempted: " + str2);
        return true;
    }

    public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        this.f1896a.mo4178w("AdWebView", "JS onBeforeUnload attempted: " + str2);
        return true;
    }

    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        this.f1896a.mo4178w("AdWebView", "JS confirm attempted: " + str2);
        return true;
    }
}
