// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.api;

import com.unity3d.services.core.misc.Utilities;
import org.json.JSONArray;
import java.util.Iterator;
import com.unity3d.services.ads.webplayer.WebPlayerError;
import com.unity3d.services.core.log.DeviceLog;
import java.util.Map;
import com.unity3d.services.banners.view.BannerView;
import android.view.View;
import com.unity3d.services.ads.adunit.IAdUnitViewHandler;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import org.json.JSONObject;

public class WebPlayer
{
    private static JSONObject _webPlayerEventSettings;
    private static JSONObject _webPlayerSettings;
    private static JSONObject _webSettings;
    
    static {
        WebPlayer._webSettings = null;
        WebPlayer._webPlayerSettings = null;
        WebPlayer._webPlayerEventSettings = null;
    }
    
    @WebViewExposed
    public static void clearSettings(final WebViewCallback webViewCallback) {
        WebPlayer._webSettings = null;
        WebPlayer._webPlayerSettings = null;
        WebPlayer._webPlayerEventSettings = null;
        webViewCallback.invoke(new Object[0]);
    }
    
    private static com.unity3d.services.ads.webplayer.WebPlayer getAdUnitWebPlayer() {
        if (AdUnit.getAdUnitActivity() != null) {
            final IAdUnitViewHandler viewHandler = AdUnit.getAdUnitActivity().getViewHandler("webplayer");
            if (viewHandler != null) {
                final View view = viewHandler.getView();
                if (view != null) {
                    return (com.unity3d.services.ads.webplayer.WebPlayer)view;
                }
            }
        }
        return null;
    }
    
    private static com.unity3d.services.ads.webplayer.WebPlayer getBannerWebPlayer() {
        if (BannerView.getInstance() == null) {
            return null;
        }
        return BannerView.getInstance().getWebPlayer();
    }
    
    @WebViewExposed
    public static void getErroredSettings(String s, final WebViewCallback webViewCallback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(s);
        if (webPlayer != null) {
            final Map<String, String> erroredSettings = webPlayer.getErroredSettings();
            s = (String)new JSONObject();
            try {
                for (final Map.Entry<String, String> entry : erroredSettings.entrySet()) {
                    ((JSONObject)s).put((String)entry.getKey(), (Object)entry.getValue());
                }
            }
            catch (Exception ex) {
                DeviceLog.exception("Error forming JSON object", ex);
            }
            webViewCallback.invoke(s);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }
    
    private static com.unity3d.services.ads.webplayer.WebPlayer getWebPlayer(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "webplayer": {
                return getAdUnitWebPlayer();
            }
            case "bannerplayer": {
                return getBannerWebPlayer();
            }
        }
    }
    
    public static JSONObject getWebPlayerEventSettings() {
        return WebPlayer._webPlayerEventSettings;
    }
    
    public static JSONObject getWebPlayerSettings() {
        return WebPlayer._webPlayerSettings;
    }
    
    public static JSONObject getWebSettings() {
        return WebPlayer._webSettings;
    }
    
    @WebViewExposed
    public static void sendEvent(final JSONArray jsonArray, final String s, final WebViewCallback webViewCallback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(s);
        if (webPlayer != null) {
            webPlayer.sendEvent(jsonArray);
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setData(final String s, final String s2, final String s3, final String s4, final WebViewCallback webViewCallback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(s4);
        if (webPlayer != null) {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webPlayer.loadData(s, s2, s3);
                }
            });
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setDataWithUrl(final String s, final String s2, final String s3, final String s4, final String s5, final WebViewCallback webViewCallback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(s5);
        if (webPlayer != null) {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webPlayer.loadDataWithBaseURL(s, s2, s3, s4, (String)null);
                }
            });
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }
    
    @WebViewExposed
    public static void setEventSettings(final JSONObject webPlayerEventSettings, final String s, final WebViewCallback webViewCallback) {
        if (s.equals("webplayer")) {
            WebPlayer._webPlayerEventSettings = webPlayerEventSettings;
        }
        else {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final com.unity3d.services.ads.webplayer.WebPlayer access$000 = getBannerWebPlayer();
                    if (access$000 != null) {
                        access$000.setEventSettings(webPlayerEventSettings);
                        return;
                    }
                    BannerView.setWebPlayerEventSettings(webPlayerEventSettings);
                }
            });
        }
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setSettings(final JSONObject webSettings, final JSONObject webPlayerSettings, final String s, final WebViewCallback webViewCallback) {
        switch (s) {
            case "bannerplayer": {
                Utilities.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final com.unity3d.services.ads.webplayer.WebPlayer access$000 = getBannerWebPlayer();
                        if (access$000 != null) {
                            access$000.setSettings(webSettings, webPlayerSettings);
                            return;
                        }
                        BannerView.setWebPlayerSettings(webSettings, webPlayerSettings);
                    }
                });
                break;
            }
            case "webplayer": {
                WebPlayer._webSettings = webSettings;
                WebPlayer._webPlayerSettings = webPlayerSettings;
                break;
            }
        }
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setUrl(final String s, final String s2, final WebViewCallback webViewCallback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(s2);
        if (webPlayer != null) {
            Utilities.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    webPlayer.loadUrl(s);
                }
            });
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }
}
