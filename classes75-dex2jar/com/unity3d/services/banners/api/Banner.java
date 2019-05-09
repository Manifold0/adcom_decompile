// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners.api;

import com.unity3d.services.banners.view.BannerPosition;
import java.util.Arrays;
import org.json.JSONException;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.misc.Utilities;
import android.view.ViewParent;
import com.unity3d.services.banners.view.BannerEvent;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import android.view.View;
import android.view.ViewGroup;
import com.unity3d.services.banners.properties.BannerProperties;
import com.unity3d.services.banners.view.BannerView;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import java.util.List;
import org.json.JSONArray;

public class Banner
{
    @WebViewExposed
    public static void destroy(final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    final BannerView instance = BannerView.getInstance();
                    if (instance != null) {
                        instance.destroy();
                        if (BannerProperties.getBannerParent() != null) {
                            final ViewParent parent = BannerProperties.getBannerParent().getParent();
                            if (parent != null && parent instanceof ViewGroup) {
                                ((ViewGroup)parent).removeView(BannerProperties.getBannerParent());
                            }
                        }
                        BannerProperties.setBannerParent(null);
                        final WebViewApp currentApp = WebViewApp.getCurrentApp();
                        if (currentApp != null) {
                            currentApp.sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_DESTROYED, new Object[0]);
                        }
                    }
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    private static List<String> getArrayFromJSONArray(final JSONArray jsonArray) {
        final String[] array = new String[jsonArray.length()];
        int i = 0;
    Label_0024_Outer:
        while (i < array.length) {
            while (true) {
                try {
                    array[i] = jsonArray.getString(i);
                    ++i;
                    continue Label_0024_Outer;
                }
                catch (JSONException ex) {
                    DeviceLog.warning("Exception converting JSON Array to String Array: %s", ex);
                    continue;
                }
                break;
            }
            break;
        }
        return Arrays.asList(array);
    }
    
    @WebViewExposed
    public static void load(final JSONArray jsonArray, final String s, final Integer n, final Integer n2, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    final BannerView orCreateInstance = BannerView.getOrCreateInstance();
                    orCreateInstance.setBannerDimensions(n, n2, BannerPosition.fromString(s));
                    orCreateInstance.setViews(getArrayFromJSONArray(jsonArray));
                    final WebViewApp currentApp = WebViewApp.getCurrentApp();
                    if (currentApp != null) {
                        currentApp.sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_LOADED, new Object[0]);
                    }
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setBannerFrame(final String s, final Integer n, final Integer n2, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final BannerView instance = BannerView.getInstance();
                if (instance != null) {
                    instance.setBannerDimensions(n, n2, BannerPosition.fromString(s));
                    instance.setLayoutParams(instance.getLayoutParams());
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setViewFrame(final String s, final Integer n, final Integer n2, final Integer n3, final Integer n4, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final BannerView instance = BannerView.getInstance();
                if (instance != null) {
                    instance.setViewFrame(s, n, n2, n3, n4);
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setViews(final JSONArray jsonArray, final WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final BannerView instance = BannerView.getInstance();
                if (instance != null) {
                    instance.setViews(getArrayFromJSONArray(jsonArray));
                }
            }
        });
        webViewCallback.invoke(new Object[0]);
    }
}
