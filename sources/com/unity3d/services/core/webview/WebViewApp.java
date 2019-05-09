package com.unity3d.services.core.webview;

import android.os.Build.VERSION;
import android.os.ConditionVariable;
import android.os.Looper;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.properties.SdkProperties;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import com.unity3d.services.core.webview.bridge.Invocation;
import com.unity3d.services.core.webview.bridge.NativeCallback;
import com.unity3d.services.core.webview.bridge.WebViewBridge;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

public class WebViewApp extends WebViewClient {
    private static final int INVOKE_JS_CHARS_LENGTH = 22;
    private static ConditionVariable _conditionVariable;
    private static WebViewApp _currentApp;
    private Configuration _configuration;
    private boolean _initialized;
    private HashMap<String, NativeCallback> _nativeCallbacks;
    private boolean _webAppLoaded;
    private WebView _webView;

    private class WebAppChromeClient extends WebChromeClient {
        private WebAppChromeClient() {
        }

        public void onConsoleMessage(String message, int lineNumber, String sourceID) {
            String sourceFile = sourceID;
            File tmp = null;
            try {
                tmp = new File(sourceID);
            } catch (Exception e) {
                DeviceLog.exception("Could not handle sourceId", e);
            }
            if (tmp != null) {
                sourceFile = tmp.getName();
            }
            if (VERSION.SDK_INT < 19) {
                DeviceLog.debug("JavaScript (sourceId=" + sourceFile + ", line=" + lineNumber + "): " + message);
            }
        }
    }

    private class WebAppClient extends WebViewClient {
        private WebAppClient() {
        }

        public void onPageFinished(WebView webview, String url) {
            super.onPageFinished(webview, url);
            DeviceLog.debug("onPageFinished url: " + url);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            DeviceLog.debug("Trying to load url: " + url);
            return false;
        }

        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (view != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + view.toString());
            }
            if (request != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + request.toString());
            }
            if (error != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + error.toString());
            }
        }
    }

    private WebViewApp(Configuration configuration) {
        this._webAppLoaded = false;
        this._initialized = false;
        setConfiguration(configuration);
        WebViewBridge.setClassTable(getConfiguration().getWebAppApiClassList());
        this._webView = new WebView(ClientProperties.getApplicationContext());
        this._webView.setWebViewClient(new WebAppClient());
        this._webView.setWebChromeClient(new WebAppChromeClient());
    }

    public WebViewApp() {
        this._webAppLoaded = false;
        this._initialized = false;
    }

    public void setWebAppLoaded(boolean loaded) {
        this._webAppLoaded = loaded;
    }

    public boolean isWebAppLoaded() {
        return this._webAppLoaded;
    }

    public void setWebAppInitialized(boolean initialized) {
        this._initialized = initialized;
        _conditionVariable.open();
    }

    public boolean isWebAppInitialized() {
        return this._initialized;
    }

    public WebView getWebView() {
        return this._webView;
    }

    public void setWebView(WebView webView) {
        this._webView = webView;
    }

    public Configuration getConfiguration() {
        return this._configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this._configuration = configuration;
    }

    private void invokeJavascriptMethod(String className, String methodName, JSONArray params) throws JSONException {
        String paramsString = params.toString();
        StringBuilder sb = new StringBuilder(((className.length() + 22) + methodName.length()) + paramsString.length());
        sb.append("javascript:window.");
        sb.append(className);
        sb.append(".");
        sb.append(methodName);
        sb.append("(");
        sb.append(paramsString);
        sb.append(");");
        String javaScriptString = sb.toString();
        DeviceLog.debug("Invoking javascript: " + javaScriptString);
        getWebView().invokeJavascript(javaScriptString);
    }

    public boolean sendEvent(Enum eventCategory, Enum eventId, Object... params) {
        if (isWebAppLoaded()) {
            JSONArray paramList = new JSONArray();
            paramList.put(eventCategory.name());
            paramList.put(eventId.name());
            for (Object o : params) {
                paramList.put(o);
            }
            try {
                invokeJavascriptMethod("nativebridge", "handleEvent", paramList);
                return true;
            } catch (Exception e) {
                DeviceLog.exception("Error while sending event to WebView", e);
                return false;
            }
        }
        DeviceLog.debug("sendEvent ignored because web app is not loaded");
        return false;
    }

    public boolean invokeMethod(String className, String methodName, Method callback, Object... params) {
        if (isWebAppLoaded()) {
            JSONArray paramList = new JSONArray();
            paramList.put(className);
            paramList.put(methodName);
            if (callback != null) {
                NativeCallback nativeCallback = new NativeCallback(callback);
                addCallback(nativeCallback);
                paramList.put(nativeCallback.getId());
            } else {
                paramList.put(null);
            }
            if (params != null) {
                for (Object o : params) {
                    paramList.put(o);
                }
            }
            try {
                invokeJavascriptMethod("nativebridge", "handleInvocation", paramList);
                return true;
            } catch (Exception e) {
                DeviceLog.exception("Error invoking javascript method", e);
                return false;
            }
        }
        DeviceLog.debug("invokeMethod ignored because web app is not loaded");
        return false;
    }

    public boolean invokeCallback(Invocation invocation) {
        if (isWebAppLoaded()) {
            JSONArray responseList = new JSONArray();
            ArrayList<ArrayList<Object>> responses = invocation.getResponses();
            if (!(responses == null || responses.isEmpty())) {
                Iterator it = responses.iterator();
                while (it.hasNext()) {
                    ArrayList<Object> response = (ArrayList) it.next();
                    CallbackStatus status = (CallbackStatus) response.get(0);
                    Enum error = (Enum) response.get(1);
                    Object[] params = (Object[]) response.get(2);
                    String callbackId = params[0];
                    params = Arrays.copyOfRange(params, 1, params.length);
                    ArrayList<Object> tmp = new ArrayList();
                    tmp.add(callbackId);
                    tmp.add(status.toString());
                    JSONArray paramArray = new JSONArray();
                    if (error != null) {
                        paramArray.put(error.name());
                    }
                    for (Object o : params) {
                        paramArray.put(o);
                    }
                    tmp.add(paramArray);
                    JSONArray paramList = new JSONArray();
                    Iterator it2 = tmp.iterator();
                    while (it2.hasNext()) {
                        paramList.put(it2.next());
                    }
                    responseList.put(paramList);
                }
            }
            try {
                invokeJavascriptMethod("nativebridge", "handleCallback", responseList);
            } catch (Exception e) {
                DeviceLog.exception("Error while invoking batch response for WebView", e);
            }
            return true;
        }
        DeviceLog.debug("invokeBatchCallback ignored because web app is not loaded");
        return false;
    }

    public void addCallback(NativeCallback callback) {
        if (this._nativeCallbacks == null) {
            this._nativeCallbacks = new HashMap();
        }
        synchronized (this._nativeCallbacks) {
            this._nativeCallbacks.put(callback.getId(), callback);
        }
    }

    public void removeCallback(NativeCallback callback) {
        if (this._nativeCallbacks != null) {
            synchronized (this._nativeCallbacks) {
                this._nativeCallbacks.remove(callback.getId());
            }
        }
    }

    public NativeCallback getCallback(String callbackId) {
        NativeCallback nativeCallback;
        synchronized (this._nativeCallbacks) {
            nativeCallback = (NativeCallback) this._nativeCallbacks.get(callbackId);
        }
        return nativeCallback;
    }

    public static WebViewApp getCurrentApp() {
        return _currentApp;
    }

    public static void setCurrentApp(WebViewApp app) {
        _currentApp = app;
    }

    public static boolean create(final Configuration configuration) throws IllegalThreadStateException {
        DeviceLog.entered();
        if (Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            throw new IllegalThreadStateException("Cannot call create() from main thread!");
        }
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                try {
                    WebViewApp webViewApp = new WebViewApp(configuration);
                    String queryString = "?platform=android";
                    try {
                        if (configuration.getWebViewUrl() != null) {
                            queryString = queryString + "&origin=" + URLEncoder.encode(configuration.getWebViewUrl(), "UTF-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                        DeviceLog.exception("Unsupported charset when encoding origin url", e);
                    }
                    try {
                        if (configuration.getWebViewVersion() != null) {
                            queryString = queryString + "&version=" + URLEncoder.encode(configuration.getWebViewVersion(), "UTF-8");
                        }
                    } catch (UnsupportedEncodingException e2) {
                        DeviceLog.exception("Unsupported charset when encoding webview version", e2);
                    }
                    webViewApp.getWebView().loadDataWithBaseURL("file://" + SdkProperties.getLocalWebViewFile() + queryString, configuration.getWebViewData(), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
                    WebViewApp.setCurrentApp(webViewApp);
                } catch (Exception e3) {
                    DeviceLog.error("Couldn't construct WebViewApp");
                    WebViewApp._conditionVariable.open();
                }
            }
        });
        _conditionVariable = new ConditionVariable();
        return _conditionVariable.block(60000) && getCurrentApp() != null;
    }
}
