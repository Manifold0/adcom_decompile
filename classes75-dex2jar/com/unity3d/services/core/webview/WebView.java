// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.webview;

import android.webkit.WebSettings;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.bridge.WebViewBridgeInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.unity3d.services.core.misc.ViewUtilities;
import android.graphics.drawable.ColorDrawable;
import android.webkit.WebSettings$RenderPriority;
import android.webkit.WebSettings$PluginState;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.content.Context;
import java.lang.reflect.Method;

public class WebView extends android.webkit.WebView
{
    private static Method _evaluateJavascript;
    
    static {
        WebView._evaluateJavascript = null;
    }
    
    public WebView(Context settings) {
        super(settings);
        settings = (Context)this.getSettings();
        if (Build$VERSION.SDK_INT >= 16) {
            ((WebSettings)settings).setAllowFileAccessFromFileURLs(true);
            ((WebSettings)settings).setAllowUniversalAccessFromFileURLs(true);
        }
        while (true) {
            if (Build$VERSION.SDK_INT < 19) {
                break Label_0060;
            }
            try {
                WebView._evaluateJavascript = android.webkit.WebView.class.getMethod("evaluateJavascript", String.class, ValueCallback.class);
                ((WebSettings)settings).setAppCacheEnabled(false);
                ((WebSettings)settings).setBlockNetworkImage(false);
                ((WebSettings)settings).setBlockNetworkLoads(false);
                ((WebSettings)settings).setBuiltInZoomControls(false);
                ((WebSettings)settings).setCacheMode(2);
                ((WebSettings)settings).setDatabaseEnabled(false);
                if (Build$VERSION.SDK_INT >= 11) {
                    ((WebSettings)settings).setDisplayZoomControls(false);
                }
                ((WebSettings)settings).setDomStorageEnabled(false);
                if (Build$VERSION.SDK_INT >= 11) {
                    ((WebSettings)settings).setEnableSmoothTransition(false);
                }
                ((WebSettings)settings).setGeolocationEnabled(false);
                ((WebSettings)settings).setJavaScriptCanOpenWindowsAutomatically(false);
                ((WebSettings)settings).setJavaScriptEnabled(true);
                ((WebSettings)settings).setLightTouchEnabled(false);
                ((WebSettings)settings).setLoadWithOverviewMode(false);
                ((WebSettings)settings).setLoadsImagesAutomatically(true);
                if (Build$VERSION.SDK_INT >= 17) {
                    ((WebSettings)settings).setMediaPlaybackRequiresUserGesture(false);
                }
                if (Build$VERSION.SDK_INT >= 21) {
                    ((WebSettings)settings).setMixedContentMode(1);
                }
                ((WebSettings)settings).setNeedInitialFocus(true);
                ((WebSettings)settings).setPluginState(WebSettings$PluginState.OFF);
                ((WebSettings)settings).setRenderPriority(WebSettings$RenderPriority.NORMAL);
                ((WebSettings)settings).setSaveFormData(false);
                ((WebSettings)settings).setSavePassword(false);
                ((WebSettings)settings).setSupportMultipleWindows(false);
                ((WebSettings)settings).setSupportZoom(false);
                ((WebSettings)settings).setUseWideViewPort(true);
                this.setHorizontalScrollBarEnabled(false);
                this.setVerticalScrollBarEnabled(false);
                this.setInitialScale(0);
                this.setBackgroundColor(0);
                ViewUtilities.setBackground((View)this, (Drawable)new ColorDrawable(0));
                this.setBackgroundResource(0);
                this.addJavascriptInterface((Object)new WebViewBridgeInterface(), "webviewbridge");
            }
            catch (NoSuchMethodException ex) {
                DeviceLog.exception("Method evaluateJavascript not found", ex);
                WebView._evaluateJavascript = null;
                continue;
            }
            break;
        }
    }
    
    public void invokeJavascript(final String s) {
        Utilities.runOnUiThread(new JavaScriptInvocation(s, this));
    }
    
    public void loadUrl(final String s) {
        DeviceLog.debug("Loading url: " + s);
        super.loadUrl(s);
    }
    
    private class JavaScriptInvocation implements Runnable
    {
        private String _jsString;
        private android.webkit.WebView _webView;
        
        public JavaScriptInvocation(final String jsString, final android.webkit.WebView webView) {
            this._jsString = null;
            this._webView = null;
            this._jsString = jsString;
            this._webView = webView;
        }
        
        @Override
        public void run() {
            if (this._jsString != null) {
                try {
                    if (Build$VERSION.SDK_INT >= 19) {
                        WebView._evaluateJavascript.invoke(this._webView, this._jsString, null);
                        return;
                    }
                    WebView.this.loadUrl(this._jsString);
                    return;
                }
                catch (Exception ex) {
                    DeviceLog.exception("Error while processing JavaScriptString", ex);
                    return;
                }
            }
            DeviceLog.error("Could not process JavaScript, the string is NULL");
        }
    }
}
