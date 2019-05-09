package com.unity3d.services.banners.api;

import android.view.ViewGroup;
import android.view.ViewParent;
import com.unity3d.services.banners.properties.BannerProperties;
import com.unity3d.services.banners.view.BannerEvent;
import com.unity3d.services.banners.view.BannerPosition;
import com.unity3d.services.banners.view.BannerView;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class Banner {

    /* renamed from: com.unity3d.services.banners.api.Banner$2 */
    static class C15992 implements Runnable {
        C15992() {
        }

        public synchronized void run() {
            BannerView view = BannerView.getInstance();
            if (view != null) {
                view.destroy();
                if (BannerProperties.getBannerParent() != null) {
                    ViewParent parent = BannerProperties.getBannerParent().getParent();
                    if (parent != null && (parent instanceof ViewGroup)) {
                        ((ViewGroup) parent).removeView(BannerProperties.getBannerParent());
                    }
                }
                BannerProperties.setBannerParent(null);
                WebViewApp app = WebViewApp.getCurrentApp();
                if (app != null) {
                    app.sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_DESTROYED, new Object[0]);
                }
            }
        }
    }

    @WebViewExposed
    public static void load(final JSONArray viewsArray, final String style, final Integer width, final Integer height, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public synchronized void run() {
                BannerView view = BannerView.getOrCreateInstance();
                view.setBannerDimensions(width.intValue(), height.intValue(), BannerPosition.fromString(style));
                view.setViews(Banner.getArrayFromJSONArray(viewsArray));
                WebViewApp app = WebViewApp.getCurrentApp();
                if (app != null) {
                    app.sendEvent(WebViewEventCategory.BANNER, BannerEvent.BANNER_LOADED, new Object[0]);
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void destroy(WebViewCallback callback) {
        Utilities.runOnUiThread(new C15992());
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setViewFrame(String viewName, Integer x, Integer y, Integer width, Integer height, WebViewCallback callback) {
        final String str = viewName;
        final Integer num = x;
        final Integer num2 = y;
        final Integer num3 = width;
        final Integer num4 = height;
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                BannerView view = BannerView.getInstance();
                if (view != null) {
                    view.setViewFrame(str, num, num2, num3, num4);
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setViews(final JSONArray views, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                BannerView view = BannerView.getInstance();
                if (view != null) {
                    view.setViews(Banner.getArrayFromJSONArray(views));
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setBannerFrame(final String style, final Integer width, final Integer height, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                BannerView view = BannerView.getInstance();
                if (view != null) {
                    view.setBannerDimensions(width.intValue(), height.intValue(), BannerPosition.fromString(style));
                    view.setLayoutParams(view.getLayoutParams());
                }
            }
        });
        callback.invoke(new Object[0]);
    }

    private static List<String> getArrayFromJSONArray(JSONArray arr) {
        String[] out = new String[arr.length()];
        for (int i = 0; i < out.length; i++) {
            try {
                out[i] = arr.getString(i);
            } catch (JSONException e) {
                DeviceLog.warning("Exception converting JSON Array to String Array: %s", e);
            }
        }
        return Arrays.asList(out);
    }
}
