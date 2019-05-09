// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.webplayer;

import android.webkit.WebSettings;
import android.view.KeyEvent;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.ClientCertRequest;
import android.webkit.WebChromeClient$FileChooserParams;
import android.net.Uri;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.graphics.Bitmap;
import android.webkit.PermissionRequest;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.GeolocationPermissions$Callback;
import android.os.Message;
import android.webkit.ConsoleMessage;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import android.annotation.TargetApi;
import com.unity3d.services.core.misc.Utilities;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.HashMap;
import com.unity3d.services.core.log.DeviceLog;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.unity3d.services.core.misc.ViewUtilities;
import android.graphics.drawable.ColorDrawable;
import android.webkit.WebSettings$RenderPriority;
import android.webkit.WebSettings$PluginState;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.content.Context;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.Map;
import android.webkit.WebView;

public class WebPlayer extends WebView
{
    private Map<String, String> _erroredSettings;
    private Method _evaluateJavascript;
    private JSONObject _eventSettings;
    private String viewId;
    
    public WebPlayer(Context settings, final String viewId, final JSONObject jsonObject, final JSONObject jsonObject2) {
        super(settings);
        this._evaluateJavascript = null;
        this.viewId = viewId;
        settings = (Context)this.getSettings();
        if (Build$VERSION.SDK_INT >= 16) {
            ((WebSettings)settings).setAllowFileAccessFromFileURLs(false);
            ((WebSettings)settings).setAllowUniversalAccessFromFileURLs(false);
        }
        while (true) {
            if (Build$VERSION.SDK_INT < 19) {
                break Label_0071;
            }
            try {
                this._evaluateJavascript = WebView.class.getMethod("evaluateJavascript", String.class, ValueCallback.class);
                ((WebSettings)settings).setAppCacheEnabled(false);
                ((WebSettings)settings).setCacheMode(2);
                ((WebSettings)settings).setDatabaseEnabled(false);
                ((WebSettings)settings).setDomStorageEnabled(false);
                ((WebSettings)settings).setGeolocationEnabled(false);
                ((WebSettings)settings).setJavaScriptEnabled(true);
                ((WebSettings)settings).setLoadsImagesAutomatically(true);
                ((WebSettings)settings).setPluginState(WebSettings$PluginState.OFF);
                ((WebSettings)settings).setRenderPriority(WebSettings$RenderPriority.NORMAL);
                ((WebSettings)settings).setSaveFormData(false);
                ((WebSettings)settings).setSavePassword(false);
                this.setHorizontalScrollBarEnabled(false);
                this.setVerticalScrollBarEnabled(false);
                this.setInitialScale(0);
                this.setBackgroundColor(0);
                ViewUtilities.setBackground((View)this, (Drawable)new ColorDrawable(0));
                this.setBackgroundResource(0);
                this.setSettings(jsonObject, jsonObject2);
                this.setWebViewClient((WebViewClient)new WebPlayerClient());
                this.setWebChromeClient((WebChromeClient)new WebPlayerChromeClient());
                this.setDownloadListener((DownloadListener)new WebPlayerDownloadListener());
                this.addJavascriptInterface((Object)new WebPlayerBridgeInterface(viewId), "webplayerbridge");
            }
            catch (NoSuchMethodException ex) {
                DeviceLog.exception("Method evaluateJavascript not found", ex);
                this._evaluateJavascript = null;
                continue;
            }
            break;
        }
    }
    
    private void addErroredSetting(final String s, final String s2) {
        if (this._erroredSettings == null) {
            this._erroredSettings = new HashMap<String, String>();
        }
        this._erroredSettings.put(s, s2);
    }
    
    private <T> T getReturnValue(final String s, final Class<T> clazz, final T t) {
        T cast = t;
        try {
            if (this._eventSettings != null) {
                cast = t;
                if (this._eventSettings.has(s)) {
                    cast = t;
                    if (this._eventSettings.getJSONObject(s).has("returnValue")) {
                        cast = clazz.cast(this._eventSettings.getJSONObject(s).get("returnValue"));
                    }
                }
            }
            return cast;
        }
        catch (Exception ex) {
            DeviceLog.exception("Error getting default return value", ex);
            return t;
        }
    }
    
    private Class<?>[] getTypes(final JSONArray jsonArray) throws JSONException, ClassNotFoundException {
        Class<?>[] array;
        if (jsonArray == null) {
            array = null;
        }
        else {
            final Class[] array2 = array = (Class<?>[])new Class[jsonArray.length()];
            if (jsonArray != null) {
                int n = 0;
                while (true) {
                    array = (Class<?>[])array2;
                    if (n >= jsonArray.length()) {
                        break;
                    }
                    if (jsonArray.get(n) instanceof JSONObject) {
                        final Class<?> forName = Class.forName(((JSONObject)jsonArray.get(n)).getString("className"));
                        if (forName != null) {
                            array2[n] = forName;
                        }
                    }
                    else {
                        array2[n] = this.getPrimitiveClass(jsonArray.get(n).getClass());
                    }
                    ++n;
                }
            }
        }
        return array;
    }
    
    private Object[] getValues(final JSONArray jsonArray) throws JSONException, ClassNotFoundException, NoSuchMethodException {
        Object[] array;
        if (jsonArray == null) {
            array = null;
        }
        else {
            final Object[] array2 = new Object[jsonArray.length()];
            final Object[] array3 = new Object[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); ++i) {
                if (jsonArray.get(i) instanceof JSONObject) {
                    final JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                    final Object value = jsonObject.get("value");
                    final String string = jsonObject.getString("type");
                    String string2 = null;
                    if (jsonObject.has("className")) {
                        string2 = jsonObject.getString("className");
                    }
                    if (string2 != null && string.equals("Enum")) {
                        final Class<?> forName = Class.forName(string2);
                        if (forName != null) {
                            array3[i] = Enum.valueOf(forName, (String)value);
                        }
                    }
                }
                else {
                    array3[i] = jsonArray.get(i);
                }
            }
            array = array2;
            if (jsonArray != null) {
                System.arraycopy(array3, 0, array2, 0, jsonArray.length());
                return array2;
            }
        }
        return array;
    }
    
    private boolean hasReturnValue(final String s) {
        try {
            if (this._eventSettings != null && this._eventSettings.has(s) && this._eventSettings.getJSONObject(s).has("returnValue")) {
                return true;
            }
        }
        catch (Exception ex) {
            DeviceLog.exception("Error getting default return value", ex);
        }
        return false;
    }
    
    private Object setTargetSettings(final Object o, final JSONObject jsonObject) {
        if (jsonObject != null) {
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s = keys.next();
                try {
                    final JSONArray jsonArray = jsonObject.getJSONArray(s);
                    o.getClass().getMethod(s, this.getTypes(jsonArray)).invoke(o, this.getValues(jsonArray));
                }
                catch (Exception ex) {
                    this.addErroredSetting(s, ex.getMessage());
                    DeviceLog.exception("Setting errored", ex);
                }
            }
        }
        return o;
    }
    
    private boolean shouldCallSuper(final String s) {
        try {
            if (this._eventSettings != null && this._eventSettings.has(s) && this._eventSettings.getJSONObject(s).has("callSuper")) {
                return this._eventSettings.getJSONObject(s).getBoolean("callSuper");
            }
        }
        catch (Exception ex) {
            DeviceLog.exception("Error getting super call status", ex);
        }
        return true;
    }
    
    private boolean shouldSendEvent(final String s) {
        try {
            if (this._eventSettings != null && this._eventSettings.has(s) && this._eventSettings.getJSONObject(s).has("sendEvent")) {
                return this._eventSettings.getJSONObject(s).getBoolean("sendEvent");
            }
        }
        catch (Exception ex) {
            DeviceLog.exception("Error getting send event status", ex);
        }
        return false;
    }
    
    public Map<String, String> getErroredSettings() {
        return this._erroredSettings;
    }
    
    public Class<?> getPrimitiveClass(Class<?> type) {
        final String name = type.getName();
        if (name.equals("java.lang.Byte")) {
            type = Byte.TYPE;
        }
        else {
            if (name.equals("java.lang.Short")) {
                return Short.TYPE;
            }
            if (name.equals("java.lang.Integer")) {
                return Integer.TYPE;
            }
            if (name.equals("java.lang.Long")) {
                return Long.TYPE;
            }
            if (name.equals("java.lang.Character")) {
                return Character.TYPE;
            }
            if (name.equals("java.lang.Float")) {
                return Float.TYPE;
            }
            if (name.equals("java.lang.Double")) {
                return Double.TYPE;
            }
            if (name.equals("java.lang.Boolean")) {
                return Boolean.TYPE;
            }
            if (name.equals("java.lang.Void")) {
                return Void.TYPE;
            }
        }
        return type;
    }
    
    public void invokeJavascript(final String s) {
        Utilities.runOnUiThread(new JavaScriptInvocation(s, this));
    }
    
    public void sendEvent(final JSONArray jsonArray) {
        final StringBuilder sb = new StringBuilder();
        sb.append("javascript:window.nativebridge.receiveEvent(");
        sb.append(jsonArray.toString());
        sb.append(")");
        this.invokeJavascript(sb.toString());
    }
    
    public void setEventSettings(final JSONObject eventSettings) {
        this._eventSettings = eventSettings;
    }
    
    public void setSettings(final JSONObject jsonObject, final JSONObject jsonObject2) {
        if (this._erroredSettings != null) {
            this._erroredSettings.clear();
        }
        this.setTargetSettings(this.getSettings(), jsonObject);
        this.setTargetSettings(this, jsonObject2);
    }
    
    private class JavaScriptInvocation implements Runnable
    {
        private String _jsString;
        private WebView _webView;
        
        public JavaScriptInvocation(final String jsString, final WebView webView) {
            this._jsString = null;
            this._webView = null;
            this._jsString = jsString;
            this._webView = webView;
        }
        
        @Override
        public void run() {
            if (this._jsString != null) {
                try {
                    if (Build$VERSION.SDK_INT >= 19) {
                        WebPlayer.this._evaluateJavascript.invoke(this._webView, this._jsString, null);
                        return;
                    }
                    WebPlayer.this.loadUrl(this._jsString);
                    return;
                }
                catch (Exception ex) {
                    DeviceLog.exception("Error while processing JavaScriptString", ex);
                    return;
                }
            }
            DeviceLog.error("Could not process JavaScript, the string is NULL");
        }
    }
    
    @TargetApi(21)
    private class WebPlayerChromeClient extends WebChromeClient
    {
        public void onCloseWindow(final WebView webView) {
            if (WebPlayer.this.shouldCallSuper("onCloseWindow")) {
                super.onCloseWindow(webView);
            }
            if (WebPlayer.this.shouldSendEvent("onCloseWindow")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.CLOSE_WINDOW, WebPlayer.this.viewId);
            }
        }
        
        public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("onConsoleMessage")) {
                b = super.onConsoleMessage(consoleMessage);
            }
            if (WebPlayer.this.shouldSendEvent("onConsoleMessage")) {
                String message = "";
                if (consoleMessage != null) {
                    message = consoleMessage.message();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.CONSOLE_MESSAGE, message, WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("onConsoleMessage")) {
                b = (Boolean)WebPlayer.this.getReturnValue("onConsoleMessage", Boolean.class, true);
            }
            return b;
        }
        
        public boolean onCreateWindow(final WebView webView, final boolean b, final boolean b2, final Message message) {
            Boolean b3 = false;
            if (WebPlayer.this.shouldCallSuper("onCreateWindow")) {
                b3 = super.onCreateWindow(webView, b, b2, message);
            }
            if (WebPlayer.this.shouldSendEvent("onCreateWindow")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.CREATE_WINDOW, b, b2, message, WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("onCreateWindow")) {
                b3 = (Boolean)WebPlayer.this.getReturnValue("onCreateWindow", Boolean.class, false);
            }
            return b3;
        }
        
        public void onGeolocationPermissionsShowPrompt(final String s, final GeolocationPermissions$Callback geolocationPermissions$Callback) {
            if (WebPlayer.this.shouldCallSuper("onGeolocationPermissionsShowPrompt")) {
                super.onGeolocationPermissionsShowPrompt(s, geolocationPermissions$Callback);
            }
            if (WebPlayer.this.shouldSendEvent("onGeolocationPermissionsShowPrompt")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.GEOLOCATION_PERMISSIONS_SHOW, s, WebPlayer.this.viewId);
            }
        }
        
        public void onHideCustomView() {
            if (WebPlayer.this.shouldCallSuper("onHideCustomView")) {
                super.onHideCustomView();
            }
            if (WebPlayer.this.shouldSendEvent("onHideCustomView")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.HIDE_CUSTOM_VIEW, WebPlayer.this.viewId);
            }
        }
        
        public boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("onJsAlert")) {
                b = super.onJsAlert(webView, s, s2, jsResult);
            }
            if (WebPlayer.this.shouldSendEvent("onJsAlert")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.JS_ALERT, s, s2, jsResult, WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("onJsAlert")) {
                b = (Boolean)WebPlayer.this.getReturnValue("onJsAlert", Boolean.class, true);
            }
            return b;
        }
        
        public boolean onJsConfirm(final WebView webView, final String s, final String s2, final JsResult jsResult) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("onJsConfirm")) {
                b = super.onJsConfirm(webView, s, s2, jsResult);
            }
            if (WebPlayer.this.shouldSendEvent("onJsConfirm")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.JS_CONFIRM, s, s2, WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("onJsConfirm")) {
                b = (Boolean)WebPlayer.this.getReturnValue("onJsConfirm", Boolean.class, true);
            }
            return b;
        }
        
        public boolean onJsPrompt(final WebView webView, final String s, final String s2, final String s3, final JsPromptResult jsPromptResult) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("onJsPrompt")) {
                b = super.onJsPrompt(webView, s, s2, s3, jsPromptResult);
            }
            if (WebPlayer.this.shouldSendEvent("onJsPrompt")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.JS_PROMPT, s, s2, s3, WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("onJsPrompt")) {
                b = (Boolean)WebPlayer.this.getReturnValue("onJsPrompt", Boolean.class, true);
            }
            return b;
        }
        
        public void onPermissionRequest(final PermissionRequest permissionRequest) {
            if (WebPlayer.this.shouldCallSuper("onPermissionRequest")) {
                super.onPermissionRequest(permissionRequest);
            }
            if (WebPlayer.this.shouldSendEvent("onPermissionRequest")) {
                String string = "";
                if (permissionRequest != null) {
                    string = string;
                    if (permissionRequest.getOrigin() != null) {
                        string = permissionRequest.getOrigin().toString();
                    }
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PERMISSION_REQUEST, string, WebPlayer.this.viewId);
            }
        }
        
        public void onProgressChanged(final WebView webView, final int n) {
            if (WebPlayer.this.shouldCallSuper("onProgressChanged")) {
                super.onProgressChanged(webView, n);
            }
            if (WebPlayer.this.shouldSendEvent("onProgressChanged")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PROGRESS_CHANGED, n, WebPlayer.this.viewId);
            }
        }
        
        public void onReceivedIcon(final WebView webView, final Bitmap bitmap) {
            if (WebPlayer.this.shouldCallSuper("onReceivedIcon")) {
                super.onReceivedIcon(webView, bitmap);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedIcon")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.RECEIVED_ICON, WebPlayer.this.viewId);
            }
        }
        
        public void onReceivedTitle(final WebView webView, final String s) {
            if (WebPlayer.this.shouldCallSuper("onReceivedTitle")) {
                super.onReceivedTitle(webView, s);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedTitle")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.RECEIVED_TITLE, s, WebPlayer.this.viewId);
            }
        }
        
        public void onReceivedTouchIconUrl(final WebView webView, final String s, final boolean b) {
            if (WebPlayer.this.shouldCallSuper("onReceivedTouchIconUrl")) {
                super.onReceivedTouchIconUrl(webView, s, b);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedTouchIconUrl")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.RECEIVED_TOUCH_ICON_URL, s, b, WebPlayer.this.viewId);
            }
        }
        
        public void onRequestFocus(final WebView webView) {
            if (WebPlayer.this.shouldCallSuper("onRequestFocus")) {
                super.onRequestFocus(webView);
            }
            if (WebPlayer.this.shouldSendEvent("onRequestFocus")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.REQUEST_FOCUS, WebPlayer.this.viewId);
            }
        }
        
        public void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
            if (WebPlayer.this.shouldCallSuper("onShowCustomView")) {
                super.onShowCustomView(view, webChromeClient$CustomViewCallback);
            }
            if (WebPlayer.this.shouldSendEvent("onShowCustomView")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOW_CUSTOM_VIEW, WebPlayer.this.viewId);
            }
        }
        
        public boolean onShowFileChooser(final WebView webView, final ValueCallback<Uri[]> valueCallback, final WebChromeClient$FileChooserParams webChromeClient$FileChooserParams) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("onShowFileChooser")) {
                b = super.onShowFileChooser(webView, (ValueCallback)valueCallback, webChromeClient$FileChooserParams);
            }
            if (WebPlayer.this.shouldSendEvent("onShowFileChooser")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOW_FILE_CHOOSER, WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("onShowFileChooser")) {
                final Boolean b2 = b = (Boolean)WebPlayer.this.getReturnValue("onShowFileChooser", Boolean.class, (boolean)(1 != 0));
                if (b2) {
                    valueCallback.onReceiveValue((Object)null);
                    b = b2;
                }
            }
            return b;
        }
    }
    
    private class WebPlayerClient extends WebViewClient
    {
        public void onFormResubmission(final WebView webView, final Message message, final Message message2) {
            if (WebPlayer.this.shouldCallSuper("onFormResubmission")) {
                super.onFormResubmission(webView, message, message2);
            }
            if (WebPlayer.this.shouldSendEvent("onFormResubmission")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.FORM_RESUBMISSION, WebPlayer.this.viewId);
            }
        }
        
        public void onLoadResource(final WebView webView, final String s) {
            if (WebPlayer.this.shouldCallSuper("onLoadResource")) {
                super.onLoadResource(webView, s);
            }
            if (WebPlayer.this.shouldSendEvent("onLoadResource")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.LOAD_RESOUCE, s, WebPlayer.this.viewId);
            }
        }
        
        public void onPageCommitVisible(final WebView webView, final String s) {
            if (WebPlayer.this.shouldCallSuper("onPageCommitVisible")) {
                super.onPageCommitVisible(webView, s);
            }
            if (WebPlayer.this.shouldSendEvent("onPageCommitVisible")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PAGE_COMMIT_VISIBLE, s, WebPlayer.this.viewId);
            }
        }
        
        public void onPageFinished(final WebView webView, final String s) {
            if (WebPlayer.this.shouldCallSuper("onPageFinished")) {
                super.onPageFinished(webView, s);
            }
            if (WebPlayer.this.shouldSendEvent("onPageFinished")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PAGE_FINISHED, s, WebPlayer.this.viewId);
            }
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            if (WebPlayer.this.shouldCallSuper("onPageStarted")) {
                super.onPageStarted(webView, s, bitmap);
            }
            if (WebPlayer.this.shouldSendEvent("onPageStarted")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.PAGE_STARTED, s, WebPlayer.this.viewId);
            }
        }
        
        @TargetApi(21)
        public void onReceivedClientCertRequest(final WebView webView, final ClientCertRequest clientCertRequest) {
            if (WebPlayer.this.shouldCallSuper("onReceivedClientCertRequest")) {
                super.onReceivedClientCertRequest(webView, clientCertRequest);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedClientCertRequest")) {
                String host = "";
                int port = -1;
                if (clientCertRequest != null) {
                    host = clientCertRequest.getHost();
                    port = clientCertRequest.getPort();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.CLIENT_CERT_REQUEST, host, port, WebPlayer.this.viewId);
            }
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            if (WebPlayer.this.shouldCallSuper("onReceivedError")) {
                super.onReceivedError(webView, n, s, s2);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedError")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.ERROR, s2, s, WebPlayer.this.viewId);
            }
        }
        
        @TargetApi(25)
        public void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
            if (WebPlayer.this.shouldCallSuper("onReceivedError")) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedError")) {
                String string = "";
                if (webResourceError != null) {
                    string = string;
                    if (webResourceError.getDescription() != null) {
                        string = webResourceError.getDescription().toString();
                    }
                }
                String string2 = "";
                if (webResourceRequest != null) {
                    string2 = string2;
                    if (webResourceRequest.getUrl() != null) {
                        string2 = webResourceRequest.getUrl().toString();
                    }
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.ERROR, string2, string, WebPlayer.this.viewId);
            }
        }
        
        public void onReceivedHttpAuthRequest(final WebView webView, final HttpAuthHandler httpAuthHandler, final String s, final String s2) {
            if (WebPlayer.this.shouldCallSuper("onReceivedHttpAuthRequest")) {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, s, s2);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedHttpAuthRequest")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.HTTP_AUTH_REQUEST, s, s2, WebPlayer.this.viewId);
            }
        }
        
        @TargetApi(21)
        public void onReceivedHttpError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceResponse webResourceResponse) {
            if (WebPlayer.this.shouldCallSuper("onReceivedHttpError")) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedHttpError")) {
                String string = "";
                if (webResourceRequest != null) {
                    string = string;
                    if (webResourceRequest.getUrl() != null) {
                        string = webResourceRequest.getUrl().toString();
                    }
                }
                int statusCode = -1;
                String reasonPhrase = "";
                if (webResourceResponse != null) {
                    statusCode = webResourceResponse.getStatusCode();
                    reasonPhrase = webResourceResponse.getReasonPhrase();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.HTTP_ERROR, string, reasonPhrase, statusCode, WebPlayer.this.viewId);
            }
        }
        
        public void onReceivedLoginRequest(final WebView webView, final String s, final String s2, final String s3) {
            if (WebPlayer.this.shouldCallSuper("onReceivedLoginRequest")) {
                super.onReceivedLoginRequest(webView, s, s2, s3);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedLoginRequest")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.LOGIN_REQUEST, s, s2, s3, WebPlayer.this.viewId);
            }
        }
        
        @TargetApi(14)
        public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
            if (WebPlayer.this.shouldCallSuper("onReceivedSslError")) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
            if (WebPlayer.this.shouldSendEvent("onReceivedSslError")) {
                String url = "";
                if (sslError != null) {
                    url = sslError.getUrl();
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SSL_ERROR, url, WebPlayer.this.viewId);
            }
        }
        
        public void onScaleChanged(final WebView webView, final float n, final float n2) {
            if (WebPlayer.this.shouldCallSuper("onScaleChanged")) {
                super.onScaleChanged(webView, n, n2);
            }
            if (WebPlayer.this.shouldSendEvent("onScaleChanged")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SCALE_CHANGED, n, n2, WebPlayer.this.viewId);
            }
        }
        
        public void onUnhandledKeyEvent(final WebView webView, final KeyEvent keyEvent) {
            if (WebPlayer.this.shouldCallSuper("onUnhandledKeyEvent")) {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }
            if (WebPlayer.this.shouldSendEvent("onUnhandledKeyEvent")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.UNHANDLED_KEY_EVENT, keyEvent.getKeyCode(), keyEvent.getAction(), WebPlayer.this.viewId);
            }
        }
        
        @TargetApi(21)
        public WebResourceResponse shouldInterceptRequest(final WebView webView, final WebResourceRequest webResourceRequest) {
            WebResourceResponse shouldInterceptRequest = null;
            if (WebPlayer.this.shouldCallSuper("shouldInterceptRequest")) {
                shouldInterceptRequest = super.shouldInterceptRequest(webView, webResourceRequest);
            }
            if (WebPlayer.this.shouldSendEvent("shouldInterceptRequest")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOULD_INTERCEPT_REQUEST, webResourceRequest.getUrl().toString(), WebPlayer.this.viewId);
            }
            return shouldInterceptRequest;
        }
        
        public boolean shouldOverrideKeyEvent(final WebView webView, final KeyEvent keyEvent) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("shouldOverrideKeyEvent")) {
                b = super.shouldOverrideKeyEvent(webView, keyEvent);
            }
            if (WebPlayer.this.shouldSendEvent("shouldOverrideKeyEvent")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOULD_OVERRIDE_KEY_EVENT, keyEvent.getKeyCode(), keyEvent.getAction(), WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("shouldOverrideKeyEvent")) {
                b = (Boolean)WebPlayer.this.getReturnValue("shouldOverrideKeyEvent", Boolean.class, true);
            }
            return b;
        }
        
        @TargetApi(21)
        public boolean shouldOverrideUrlLoading(final WebView webView, final WebResourceRequest webResourceRequest) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("shouldOverrideUrlLoading")) {
                b = super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
            if (WebPlayer.this.shouldSendEvent("shouldOverrideUrlLoading")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOULD_OVERRIDE_URL_LOADING, webResourceRequest.getUrl().toString(), webResourceRequest.getMethod(), WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("shouldOverrideUrlLoading")) {
                b = (Boolean)WebPlayer.this.getReturnValue("shouldOverrideUrlLoading", Boolean.class, true);
            }
            return b;
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
            Boolean b = false;
            if (WebPlayer.this.shouldCallSuper("shouldOverrideUrlLoading")) {
                b = super.shouldOverrideUrlLoading(webView, s);
            }
            if (WebPlayer.this.shouldSendEvent("shouldOverrideUrlLoading")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.SHOULD_OVERRIDE_URL_LOADING, s, WebPlayer.this.viewId);
            }
            if (WebPlayer.this.hasReturnValue("shouldOverrideUrlLoading")) {
                b = (Boolean)WebPlayer.this.getReturnValue("shouldOverrideUrlLoading", Boolean.class, true);
            }
            return b;
        }
    }
    
    private class WebPlayerDownloadListener implements DownloadListener
    {
        public void onDownloadStart(final String s, final String s2, final String s3, final String s4, final long n) {
            if (WebPlayer.this.shouldSendEvent("onDownloadStart")) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.WEBPLAYER, WebPlayerEvent.DOWNLOAD_START, s, s2, s3, s4, n, WebPlayer.this.viewId);
            }
        }
    }
}
