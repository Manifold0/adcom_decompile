// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.webkit.JsResult;
import android.webkit.WebView;
import android.webkit.ConsoleMessage;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinLogger;
import android.webkit.WebChromeClient;

class m extends WebChromeClient
{
    private final AppLovinLogger a;
    
    public m(final AppLovinSdk appLovinSdk) {
        this.a = appLovinSdk.getLogger();
    }
    
    public void onConsoleMessage(final String s, final int n, final String s2) {
        this.a.w("AdWebView", "console.log[" + n + "] :" + s);
    }
    
    public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        this.a.d("AdWebView", consoleMessage.sourceId() + ": " + consoleMessage.lineNumber() + ": " + consoleMessage.message());
        return true;
    }
    
    public boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        this.a.w("AdWebView", "Alert attempted: " + s2);
        return true;
    }
    
    public boolean onJsBeforeUnload(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        this.a.w("AdWebView", "JS onBeforeUnload attempted: " + s2);
        return true;
    }
    
    public boolean onJsConfirm(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        this.a.w("AdWebView", "JS confirm attempted: " + s2);
        return true;
    }
}
