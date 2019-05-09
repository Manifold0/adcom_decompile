// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import java.util.Iterator;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import com.unity3d.services.core.request.WebRequestError;
import com.unity3d.services.core.request.WebRequestThread;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.WebRequestEvent;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import java.util.List;
import java.util.Map;
import com.unity3d.services.core.request.IWebRequestListener;
import com.unity3d.services.core.request.WebRequest;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import org.json.JSONArray;

public class Request
{
    @WebViewExposed
    public static void get(final String s, final String s2, final JSONArray jsonArray, final Integer n, final Integer n2, final WebViewCallback webViewCallback) {
        JSONArray jsonArray2 = jsonArray;
        if (jsonArray != null) {
            jsonArray2 = jsonArray;
            if (jsonArray.length() == 0) {
                jsonArray2 = null;
            }
        }
        try {
            WebRequestThread.request(s2, WebRequest.RequestType.GET, getHeadersMap(jsonArray2), null, n, n2, new IWebRequestListener() {
                @Override
                public void onComplete(final String s, final String s2, final int n, final Map<String, List<String>> map) {
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.COMPLETE, s, s, s2, n, Request.getResponseHeadersMap(map));
                    }
                    catch (Exception ex) {
                        DeviceLog.exception("Error parsing response headers", ex);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, s, s, "Error parsing response headers");
                    }
                }
                
                @Override
                public void onFailed(final String s, final String s2) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, s, s, s2);
                }
            });
            webViewCallback.invoke(s);
        }
        catch (Exception ex) {
            DeviceLog.exception("Error mapping headers for the request", ex);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, s);
        }
    }
    
    public static HashMap<String, List<String>> getHeadersMap(final JSONArray jsonArray) throws JSONException {
        HashMap<String, List<String>> hashMap = null;
        if (jsonArray != null) {
            final HashMap<String, List<String>> hashMap2 = new HashMap<String, List<String>>();
            int n = 0;
            while (true) {
                hashMap = hashMap2;
                if (n >= jsonArray.length()) {
                    break;
                }
                final JSONArray jsonArray2 = (JSONArray)jsonArray.get(n);
                List<String> list;
                if ((list = hashMap2.get(jsonArray2.getString(0))) == null) {
                    list = new ArrayList<String>();
                }
                list.add(jsonArray2.getString(1));
                hashMap2.put(jsonArray2.getString(0), list);
                ++n;
            }
        }
        return hashMap;
    }
    
    public static JSONArray getResponseHeadersMap(final Map<String, List<String>> map) {
        final JSONArray jsonArray = new JSONArray();
        if (map != null && map.size() > 0) {
            for (final String s : map.keySet()) {
                JSONArray jsonArray2 = null;
                for (final String s2 : map.get(s)) {
                    jsonArray2 = new JSONArray();
                    jsonArray2.put((Object)s);
                    jsonArray2.put((Object)s2);
                }
                jsonArray.put((Object)jsonArray2);
            }
        }
        return jsonArray;
    }
    
    @WebViewExposed
    public static void head(final String s, final String s2, final JSONArray jsonArray, final Integer n, final Integer n2, final WebViewCallback webViewCallback) {
        JSONArray jsonArray2 = jsonArray;
        if (jsonArray != null) {
            jsonArray2 = jsonArray;
            if (jsonArray.length() == 0) {
                jsonArray2 = null;
            }
        }
        try {
            WebRequestThread.request(s2, WebRequest.RequestType.HEAD, getHeadersMap(jsonArray2), n, n2, new IWebRequestListener() {
                @Override
                public void onComplete(final String s, final String s2, final int n, final Map<String, List<String>> map) {
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.COMPLETE, s, s, s2, n, Request.getResponseHeadersMap(map));
                    }
                    catch (Exception ex) {
                        DeviceLog.exception("Error parsing response headers", ex);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, s, s, "Error parsing response headers");
                    }
                }
                
                @Override
                public void onFailed(final String s, final String s2) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, s, s, s2);
                }
            });
            webViewCallback.invoke(s);
        }
        catch (Exception ex) {
            DeviceLog.exception("Error mapping headers for the request", ex);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, s);
        }
    }
    
    @WebViewExposed
    public static void post(final String s, final String s2, final String s3, final JSONArray jsonArray, final Integer n, final Integer n2, final WebViewCallback webViewCallback) {
        String s4 = s3;
        if (s3 != null) {
            s4 = s3;
            if (s3.length() == 0) {
                s4 = null;
            }
        }
        JSONArray jsonArray2;
        if ((jsonArray2 = jsonArray) != null) {
            jsonArray2 = jsonArray;
            if (jsonArray.length() == 0) {
                jsonArray2 = null;
            }
        }
        try {
            WebRequestThread.request(s2, WebRequest.RequestType.POST, getHeadersMap(jsonArray2), s4, n, n2, new IWebRequestListener() {
                @Override
                public void onComplete(final String s, final String s2, final int n, final Map<String, List<String>> map) {
                    try {
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.COMPLETE, s, s, s2, n, Request.getResponseHeadersMap(map));
                    }
                    catch (Exception ex) {
                        DeviceLog.exception("Error parsing response headers", ex);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, s, s, "Error parsing response headers");
                    }
                }
                
                @Override
                public void onFailed(final String s, final String s2) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, s, s, s2);
                }
            });
            webViewCallback.invoke(s);
        }
        catch (Exception ex) {
            DeviceLog.exception("Error mapping headers for the request", ex);
            webViewCallback.error(WebRequestError.MAPPING_HEADERS_FAILED, s);
        }
    }
    
    @WebViewExposed
    public static void setConcurrentRequestCount(final Integer n, final WebViewCallback webViewCallback) {
        WebRequestThread.setConcurrentRequestCount(n);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setKeepAliveTime(final Integer n, final WebViewCallback webViewCallback) {
        WebRequestThread.setKeepAliveTime(n);
        webViewCallback.invoke(new Object[0]);
    }
    
    @WebViewExposed
    public static void setMaximumPoolSize(final Integer n, final WebViewCallback webViewCallback) {
        WebRequestThread.setMaximumPoolSize(n);
        webViewCallback.invoke(new Object[0]);
    }
}
