// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar.api;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import com.unity3d.services.core.properties.ClientProperties;
import org.json.JSONArray;
import com.unity3d.services.ar.ARUtils;
import com.unity3d.services.ar.ARCheck;
import com.unity3d.services.ads.adunit.IAdUnitViewHandler;
import com.unity3d.services.ads.adunit.AdUnitActivity;
import com.unity3d.services.ar.view.ARView;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.ar.ARError;
import com.unity3d.services.ads.api.AdUnit;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class AR
{
    @WebViewExposed
    public static void addAnchor(final String s, final String s2, final WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            getARView().addAnchor(s, s2);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void advanceFrame(final WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            getARView().setDrawNextCameraFrame();
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
    
    private static ARView getARView() {
        final AdUnitActivity adUnitActivity = AdUnit.getAdUnitActivity();
        if (adUnitActivity != null) {
            final IAdUnitViewHandler viewHandler = adUnitActivity.getViewHandler("arview");
            if (viewHandler != null) {
                return (ARView)viewHandler.getView();
            }
        }
        return null;
    }
    
    @WebViewExposed
    public static void getAndroidConfigEnums(final WebViewCallback webViewCallback) {
        if (!ARCheck.isFrameworkPresent()) {
            webViewCallback.error(ARError.AR_NOT_SUPPORTED, new Object[0]);
            return;
        }
        webViewCallback.invoke(ARUtils.getConfigEnums());
    }
    
    @WebViewExposed
    public static void getSupportedVideoFormats(final String s, final WebViewCallback webViewCallback) {
        webViewCallback.invoke(new JSONArray().toString());
    }
    
    @WebViewExposed
    public static void hideCameraFeed(final WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            getARView().setShowCameraFrame(false);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void isARSupported(final WebViewCallback webViewCallback) {
        if (!ARCheck.isFrameworkPresent()) {
            webViewCallback.invoke(false, false);
        }
        else {
            final Context applicationContext = ClientProperties.getApplicationContext();
            if (applicationContext != null) {
                final int supported = ARUtils.isSupported(applicationContext);
                webViewCallback.invoke((supported & 0x2) != 0x0, (supported & 0x1) != 0x0);
            }
        }
    }
    
    @WebViewExposed
    public static void removeAnchor(final String s, final WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            getARView().removeAnchor(s);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void restartSession(final JSONObject jsonObject, final WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            try {
                getARView().restartSession(jsonObject);
                webViewCallback.invoke(new Object[0]);
                return;
            }
            catch (JSONException ex) {
                webViewCallback.error(ARError.ARCONFIG_INVALID, new Object[0]);
                return;
            }
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setDepthFar(final Double n, final WebViewCallback webViewCallback) {
        if (n == null) {
            webViewCallback.error(ARError.INVALID_VALUE, new Object[0]);
            return;
        }
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            getARView().setArFar(n.floatValue());
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setDepthNear(final Double n, final WebViewCallback webViewCallback) {
        if (n == null) {
            webViewCallback.error(ARError.INVALID_VALUE, new Object[0]);
            return;
        }
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            getARView().setArNear(n.floatValue());
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void showCameraFeed(final WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() != null && getARView() != null) {
            getARView().setShowCameraFrame(true);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(ARError.ARVIEW_NULL, new Object[0]);
    }
}
