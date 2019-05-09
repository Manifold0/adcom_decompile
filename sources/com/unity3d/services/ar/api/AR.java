package com.unity3d.services.ar.api;

import android.content.Context;
import com.unity3d.services.ads.adunit.AdUnitActivity;
import com.unity3d.services.ads.adunit.IAdUnitViewHandler;
import com.unity3d.services.ads.api.AdUnit;
import com.unity3d.services.ar.ARCheck;
import com.unity3d.services.ar.ARError;
import com.unity3d.services.ar.ARUtils;
import com.unity3d.services.ar.view.ARView;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AR {
    @WebViewExposed
    public static void isARSupported(WebViewCallback callback) {
        if (ARCheck.isFrameworkPresent()) {
            Context context = ClientProperties.getApplicationContext();
            if (context != null) {
                boolean isTransient;
                boolean isSupported;
                int result = ARUtils.isSupported(context);
                if ((result & 2) != 0) {
                    isTransient = true;
                } else {
                    isTransient = false;
                }
                if ((result & 1) != 0) {
                    isSupported = true;
                } else {
                    isSupported = false;
                }
                callback.invoke(Boolean.valueOf(isTransient), Boolean.valueOf(isSupported));
                return;
            }
            return;
        }
        callback.invoke(Boolean.valueOf(false), Boolean.valueOf(false));
    }

    @WebViewExposed
    public static void showCameraFeed(WebViewCallback callback) {
        if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
            return;
        }
        getARView().setShowCameraFrame(true);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void hideCameraFeed(WebViewCallback callback) {
        if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
            return;
        }
        getARView().setShowCameraFrame(false);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void restartSession(JSONObject properties, WebViewCallback callback) {
        if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
            return;
        }
        try {
            getARView().restartSession(properties);
            callback.invoke(new Object[0]);
        } catch (JSONException e) {
            callback.error(ARError.ARCONFIG_INVALID, new Object[0]);
        }
    }

    @WebViewExposed
    public static void setDepthFar(Double far, WebViewCallback callback) {
        if (far == null) {
            callback.error(ARError.INVALID_VALUE, new Object[0]);
        } else if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
        } else {
            getARView().setArFar(far.floatValue());
            callback.invoke(new Object[0]);
        }
    }

    @WebViewExposed
    public static void setDepthNear(Double near, WebViewCallback callback) {
        if (near == null) {
            callback.error(ARError.INVALID_VALUE, new Object[0]);
        } else if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
        } else {
            getARView().setArNear(near.floatValue());
            callback.invoke(new Object[0]);
        }
    }

    @WebViewExposed
    public static void addAnchor(String identifier, String matrix, WebViewCallback callback) {
        if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
            return;
        }
        getARView().addAnchor(identifier, matrix);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void removeAnchor(String identifier, WebViewCallback callback) {
        if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
            return;
        }
        getARView().removeAnchor(identifier);
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void advanceFrame(WebViewCallback callback) {
        if (AdUnit.getAdUnitActivity() == null || getARView() == null) {
            callback.error(ARError.ARVIEW_NULL, new Object[0]);
            return;
        }
        getARView().setDrawNextCameraFrame();
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getSupportedVideoFormats(String ignored, WebViewCallback callback) {
        callback.invoke(new JSONArray().toString());
    }

    @WebViewExposed
    public static void getAndroidConfigEnums(WebViewCallback callback) {
        if (ARCheck.isFrameworkPresent()) {
            callback.invoke(ARUtils.getConfigEnums());
            return;
        }
        callback.error(ARError.AR_NOT_SUPPORTED, new Object[0]);
    }

    private static ARView getARView() {
        AdUnitActivity activity = AdUnit.getAdUnitActivity();
        if (activity != null) {
            IAdUnitViewHandler handler = activity.getViewHandler("arview");
            if (handler != null) {
                return (ARView) handler.getView();
            }
        }
        return null;
    }
}
