package com.unity3d.services.ads.api;

import android.view.View;
import com.unity3d.services.ads.adunit.IAdUnitViewHandler;
import com.unity3d.services.ads.webplayer.WebPlayerError;
import com.unity3d.services.banners.view.BannerView;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebPlayer {
    private static JSONObject _webPlayerEventSettings = null;
    private static JSONObject _webPlayerSettings = null;
    private static JSONObject _webSettings = null;

    public static JSONObject getWebPlayerSettings() {
        return _webPlayerSettings;
    }

    public static JSONObject getWebSettings() {
        return _webSettings;
    }

    public static JSONObject getWebPlayerEventSettings() {
        return _webPlayerEventSettings;
    }

    @WebViewExposed
    public static void setUrl(final String url, String viewId, WebViewCallback callback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(viewId);
        if (webPlayer != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    webPlayer.loadUrl(url);
                }
            });
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setData(final String data, final String mimeType, final String encoding, String viewId, WebViewCallback callback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(viewId);
        if (webPlayer != null) {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    webPlayer.loadData(data, mimeType, encoding);
                }
            });
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setDataWithUrl(String baseUrl, String data, String mimeType, String encoding, String viewId, WebViewCallback callback) {
        final com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(viewId);
        if (webPlayer != null) {
            final String str = baseUrl;
            final String str2 = data;
            final String str3 = mimeType;
            final String str4 = encoding;
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    webPlayer.loadDataWithBaseURL(str, str2, str3, str4, null);
                }
            });
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setSettings(final JSONObject webSettings, final JSONObject webPlayerSettings, String viewId, WebViewCallback callback) {
        int i = -1;
        switch (viewId.hashCode()) {
            case -318269643:
                if (viewId.equals("webplayer")) {
                    i = 1;
                    break;
                }
                break;
            case 1497041165:
                if (viewId.equals("bannerplayer")) {
                    i = 0;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                Utilities.runOnUiThread(new Runnable() {
                    public void run() {
                        com.unity3d.services.ads.webplayer.WebPlayer webPlayer = WebPlayer.getBannerWebPlayer();
                        if (webPlayer != null) {
                            webPlayer.setSettings(webSettings, webPlayerSettings);
                        } else {
                            BannerView.setWebPlayerSettings(webSettings, webPlayerSettings);
                        }
                    }
                });
                break;
            case 1:
                _webSettings = webSettings;
                _webPlayerSettings = webPlayerSettings;
                break;
        }
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setEventSettings(final JSONObject settings, String viewId, WebViewCallback callback) {
        if (viewId.equals("webplayer")) {
            _webPlayerEventSettings = settings;
        } else {
            Utilities.runOnUiThread(new Runnable() {
                public void run() {
                    com.unity3d.services.ads.webplayer.WebPlayer webPlayer = WebPlayer.getBannerWebPlayer();
                    if (webPlayer != null) {
                        webPlayer.setEventSettings(settings);
                    } else {
                        BannerView.setWebPlayerEventSettings(settings);
                    }
                }
            });
        }
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void clearSettings(WebViewCallback callback) {
        _webSettings = null;
        _webPlayerSettings = null;
        _webPlayerEventSettings = null;
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void getErroredSettings(String viewId, WebViewCallback callback) {
        com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(viewId);
        if (webPlayer != null) {
            Map<String, String> errors = webPlayer.getErroredSettings();
            JSONObject retObj = new JSONObject();
            try {
                for (Entry pair : errors.entrySet()) {
                    retObj.put((String) pair.getKey(), pair.getValue());
                }
            } catch (Exception e) {
                DeviceLog.exception("Error forming JSON object", e);
            }
            callback.invoke(retObj);
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void sendEvent(JSONArray parameters, String viewId, WebViewCallback callback) {
        com.unity3d.services.ads.webplayer.WebPlayer webPlayer = getWebPlayer(viewId);
        if (webPlayer != null) {
            webPlayer.sendEvent(parameters);
            callback.invoke(new Object[0]);
            return;
        }
        callback.error(WebPlayerError.WEBPLAYER_NULL, new Object[0]);
    }

    private static com.unity3d.services.ads.webplayer.WebPlayer getWebPlayer(String viewId) {
        Object obj = -1;
        switch (viewId.hashCode()) {
            case -318269643:
                if (viewId.equals("webplayer")) {
                    obj = null;
                    break;
                }
                break;
            case 1497041165:
                if (viewId.equals("bannerplayer")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return getAdUnitWebPlayer();
            case 1:
                return getBannerWebPlayer();
            default:
                return null;
        }
    }

    private static com.unity3d.services.ads.webplayer.WebPlayer getAdUnitWebPlayer() {
        if (AdUnit.getAdUnitActivity() != null) {
            IAdUnitViewHandler viewHandler = AdUnit.getAdUnitActivity().getViewHandler("webplayer");
            if (viewHandler != null) {
                View view = viewHandler.getView();
                if (view != null) {
                    return (com.unity3d.services.ads.webplayer.WebPlayer) view;
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
}
