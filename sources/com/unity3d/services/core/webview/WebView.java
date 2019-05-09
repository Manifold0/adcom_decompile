package com.unity3d.services.core.webview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.misc.ViewUtilities;
import com.unity3d.services.core.webview.bridge.WebViewBridgeInterface;
import java.lang.reflect.Method;

public class WebView extends android.webkit.WebView {
    private static Method _evaluateJavascript = null;

    private class JavaScriptInvocation implements Runnable {
        private String _jsString = null;
        private android.webkit.WebView _webView = null;

        public JavaScriptInvocation(String jsString, android.webkit.WebView webView) {
            this._jsString = jsString;
            this._webView = webView;
        }

        public void run() {
            if (this._jsString != null) {
                try {
                    if (VERSION.SDK_INT >= 19) {
                        WebView._evaluateJavascript.invoke(this._webView, new Object[]{this._jsString, null});
                        return;
                    }
                    WebView.this.loadUrl(this._jsString);
                    return;
                } catch (Exception e) {
                    DeviceLog.exception("Error while processing JavaScriptString", e);
                    return;
                }
            }
            DeviceLog.error("Could not process JavaScript, the string is NULL");
        }
    }

    public WebView(Context context) {
        super(context);
        WebSettings settings = getSettings();
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }
        if (VERSION.SDK_INT >= 19) {
            try {
                _evaluateJavascript = android.webkit.WebView.class.getMethod("evaluateJavascript", new Class[]{String.class, ValueCallback.class});
            } catch (NoSuchMethodException e) {
                DeviceLog.exception("Method evaluateJavascript not found", e);
                _evaluateJavascript = null;
            }
        }
        settings.setAppCacheEnabled(false);
        settings.setBlockNetworkImage(false);
        settings.setBlockNetworkLoads(false);
        settings.setBuiltInZoomControls(false);
        settings.setCacheMode(2);
        settings.setDatabaseEnabled(false);
        if (VERSION.SDK_INT >= 11) {
            settings.setDisplayZoomControls(false);
        }
        settings.setDomStorageEnabled(false);
        if (VERSION.SDK_INT >= 11) {
            settings.setEnableSmoothTransition(false);
        }
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setJavaScriptEnabled(true);
        settings.setLightTouchEnabled(false);
        settings.setLoadWithOverviewMode(false);
        settings.setLoadsImagesAutomatically(true);
        if (VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(false);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        settings.setNeedInitialFocus(true);
        settings.setPluginState(PluginState.OFF);
        settings.setRenderPriority(RenderPriority.NORMAL);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(false);
        settings.setSupportZoom(false);
        settings.setUseWideViewPort(true);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setInitialScale(0);
        setBackgroundColor(0);
        ViewUtilities.setBackground(this, new ColorDrawable(0));
        setBackgroundResource(0);
        addJavascriptInterface(new WebViewBridgeInterface(), "webviewbridge");
    }

    public void invokeJavascript(String data) {
        Utilities.runOnUiThread(new JavaScriptInvocation(data, this));
    }

    public void loadUrl(String url) {
        DeviceLog.debug("Loading url: " + url);
        super.loadUrl(url);
    }
}
