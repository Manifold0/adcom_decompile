package com.unity3d.services.core.api;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.request.IWebRequestListener;
import com.unity3d.services.core.request.WebRequest.RequestType;
import com.unity3d.services.core.request.WebRequestError;
import com.unity3d.services.core.request.WebRequestEvent;
import com.unity3d.services.core.request.WebRequestThread;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public class Request {
    @WebViewExposed
    public static void get(final String id, String url, JSONArray headers, Integer connectTimeout, Integer readTimeout, WebViewCallback callback) {
        if (headers != null && headers.length() == 0) {
            headers = null;
        }
        try {
            WebRequestThread.request(url, RequestType.GET, getHeadersMap(headers), null, connectTimeout, readTimeout, new IWebRequestListener() {
                public void onComplete(String url, String response, int responseCode, Map<String, List<String>> responseHeaders) {
                    try {
                        JSONArray headers = Request.getResponseHeadersMap(responseHeaders);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.COMPLETE, id, url, response, Integer.valueOf(responseCode), headers);
                    } catch (Exception e) {
                        DeviceLog.exception("Error parsing response headers", e);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, id, url, "Error parsing response headers");
                    }
                }

                public void onFailed(String url, String error) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, id, url, error);
                }
            });
            callback.invoke(id);
        } catch (Exception e) {
            DeviceLog.exception("Error mapping headers for the request", e);
            callback.error(WebRequestError.MAPPING_HEADERS_FAILED, id);
        }
    }

    @WebViewExposed
    public static void post(final String id, String url, String requestBody, JSONArray headers, Integer connectTimeout, Integer readTimeout, WebViewCallback callback) {
        if (requestBody != null && requestBody.length() == 0) {
            requestBody = null;
        }
        if (headers != null && headers.length() == 0) {
            headers = null;
        }
        try {
            WebRequestThread.request(url, RequestType.POST, getHeadersMap(headers), requestBody, connectTimeout, readTimeout, new IWebRequestListener() {
                public void onComplete(String url, String response, int responseCode, Map<String, List<String>> responseHeaders) {
                    try {
                        JSONArray headers = Request.getResponseHeadersMap(responseHeaders);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.COMPLETE, id, url, response, Integer.valueOf(responseCode), headers);
                    } catch (Exception e) {
                        DeviceLog.exception("Error parsing response headers", e);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, id, url, "Error parsing response headers");
                    }
                }

                public void onFailed(String url, String error) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, id, url, error);
                }
            });
            callback.invoke(id);
        } catch (Exception e) {
            DeviceLog.exception("Error mapping headers for the request", e);
            callback.error(WebRequestError.MAPPING_HEADERS_FAILED, id);
        }
    }

    @WebViewExposed
    public static void head(final String id, String url, JSONArray headers, Integer connectTimeout, Integer readTimeout, WebViewCallback callback) {
        if (headers != null && headers.length() == 0) {
            headers = null;
        }
        try {
            WebRequestThread.request(url, RequestType.HEAD, getHeadersMap(headers), connectTimeout, readTimeout, new IWebRequestListener() {
                public void onComplete(String url, String response, int responseCode, Map<String, List<String>> responseHeaders) {
                    try {
                        JSONArray headers = Request.getResponseHeadersMap(responseHeaders);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.COMPLETE, id, url, response, Integer.valueOf(responseCode), headers);
                    } catch (Exception e) {
                        DeviceLog.exception("Error parsing response headers", e);
                        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, id, url, "Error parsing response headers");
                    }
                }

                public void onFailed(String url, String error) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.REQUEST, WebRequestEvent.FAILED, id, url, error);
                }
            });
            callback.invoke(id);
        } catch (Exception e) {
            DeviceLog.exception("Error mapping headers for the request", e);
            callback.error(WebRequestError.MAPPING_HEADERS_FAILED, id);
        }
    }

    @WebViewExposed
    public static void setConcurrentRequestCount(Integer count, WebViewCallback callback) {
        WebRequestThread.setConcurrentRequestCount(count.intValue());
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setMaximumPoolSize(Integer count, WebViewCallback callback) {
        WebRequestThread.setMaximumPoolSize(count.intValue());
        callback.invoke(new Object[0]);
    }

    @WebViewExposed
    public static void setKeepAliveTime(Integer milliseconds, WebViewCallback callback) {
        WebRequestThread.setKeepAliveTime(milliseconds.longValue());
        callback.invoke(new Object[0]);
    }

    public static JSONArray getResponseHeadersMap(Map<String, List<String>> responseHeaders) {
        JSONArray retObj = new JSONArray();
        if (responseHeaders != null && responseHeaders.size() > 0) {
            for (String key : responseHeaders.keySet()) {
                JSONArray keyValueMap = null;
                for (String value : (List) responseHeaders.get(key)) {
                    keyValueMap = new JSONArray();
                    keyValueMap.put(key);
                    keyValueMap.put(value);
                }
                retObj.put(keyValueMap);
            }
        }
        return retObj;
    }

    public static HashMap<String, List<String>> getHeadersMap(JSONArray headers) throws JSONException {
        HashMap<String, List<String>> mappedHeaders = null;
        if (headers != null) {
            mappedHeaders = new HashMap();
            for (int idx = 0; idx < headers.length(); idx++) {
                JSONArray header = (JSONArray) headers.get(idx);
                List<String> valueList = (List) mappedHeaders.get(header.getString(0));
                if (valueList == null) {
                    valueList = new ArrayList();
                }
                valueList.add(header.getString(1));
                mappedHeaders.put(header.getString(0), valueList);
            }
        }
        return mappedHeaders;
    }
}
