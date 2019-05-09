// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.annotation.TargetApi;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.content.Context;
import java.util.concurrent.CountDownLatch;
import android.annotation.SuppressLint;
import android.webkit.WebView;

@SuppressLint({ "SetJavaScriptEnabled" })
public class TJEventOptimizer extends WebView
{
    private static String a;
    private static TJEventOptimizer b;
    private static CountDownLatch c;
    private Context d;
    private TJAdUnitJSBridge e;
    
    static {
        TJEventOptimizer.a = "TJEventOptimizer";
    }
    
    private TJEventOptimizer(final Context d) {
        super(d);
        this.d = d;
        this.e = new TJAdUnitJSBridge(this.d, this);
        this.getSettings().setJavaScriptEnabled(true);
        this.setWebViewClient((WebViewClient)new b((byte)0));
        this.setWebChromeClient((WebChromeClient)new a((byte)0));
        this.loadUrl(TapjoyConnectCore.getHostURL() + "events/proxy?" + TapjoyUtil.convertURLParams(TapjoyConnectCore.getGenericURLParams(), true));
    }
    
    public static TJEventOptimizer getInstance() {
        return TJEventOptimizer.b;
    }
    
    public static void init(final Context context) {
        TapjoyLog.d(TJEventOptimizer.a, "Initializing event optimizer");
        TJEventOptimizer.c = new CountDownLatch(1);
        TapjoyUtil.runOnMainThread(new Runnable() {
            @Override
            public final void run() {
                while (true) {
                    try {
                        TJEventOptimizer.b = new TJEventOptimizer(context, (byte)0);
                        TJEventOptimizer.c.countDown();
                    }
                    catch (Exception ex) {
                        TapjoyLog.w(TJEventOptimizer.a, ex.getMessage());
                        continue;
                    }
                    break;
                }
            }
        });
        TJEventOptimizer.c.await();
        if (TJEventOptimizer.b == null) {
            throw new RuntimeException("Failed to init TJEventOptimizer");
        }
    }
    
    final class a extends WebChromeClient
    {
        private a() {
        }
        
        @TargetApi(8)
        public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            TapjoyLog.d(TJEventOptimizer.a, "JS CONSOLE: " + consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
            return true;
        }
    }
    
    final class b extends WebViewClient
    {
        private b() {
        }
        
        public final void onPageFinished(final WebView webView, final String s) {
            TapjoyLog.d(TJEventOptimizer.a, "boostrap html loaded successfully");
        }
        
        public final void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            super.onReceivedError(webView, n, s, s2);
            TapjoyLog.e(TJEventOptimizer.a, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Error encountered when instantiating a WebViewClient"));
        }
    }
}
