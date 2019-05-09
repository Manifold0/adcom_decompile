// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.webview;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.os.Build$VERSION;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Arrays;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import java.util.ArrayList;
import com.unity3d.services.core.webview.bridge.Invocation;
import org.json.JSONException;
import org.json.JSONArray;
import com.unity3d.services.core.misc.Utilities;
import android.os.Looper;
import com.unity3d.services.core.log.DeviceLog;
import android.webkit.WebChromeClient;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewBridge;
import com.unity3d.services.core.webview.bridge.NativeCallback;
import java.util.HashMap;
import com.unity3d.services.core.configuration.Configuration;
import android.os.ConditionVariable;
import android.webkit.WebViewClient;

public class WebViewApp extends WebViewClient
{
    private static final int INVOKE_JS_CHARS_LENGTH = 22;
    private static ConditionVariable _conditionVariable;
    private static WebViewApp _currentApp;
    private Configuration _configuration;
    private boolean _initialized;
    private HashMap<String, NativeCallback> _nativeCallbacks;
    private boolean _webAppLoaded;
    private WebView _webView;
    
    public WebViewApp() {
        this._webAppLoaded = false;
        this._initialized = false;
    }
    
    private WebViewApp(final Configuration configuration) {
        this._webAppLoaded = false;
        this._initialized = false;
        this.setConfiguration(configuration);
        WebViewBridge.setClassTable(this.getConfiguration().getWebAppApiClassList());
        (this._webView = new WebView(ClientProperties.getApplicationContext())).setWebViewClient((WebViewClient)new WebAppClient());
        this._webView.setWebChromeClient((WebChromeClient)new WebAppChromeClient());
    }
    
    public static boolean create(final Configuration configuration) throws IllegalThreadStateException {
        DeviceLog.entered();
        if (Thread.currentThread().equals(Looper.getMainLooper().getThread())) {
            throw new IllegalThreadStateException("Cannot call create() from main thread!");
        }
        Utilities.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: dup            
                //     4: aload_0        
                //     5: getfield        com/unity3d/services/core/webview/WebViewApp$1.val$configuration:Lcom/unity3d/services/core/configuration/Configuration;
                //     8: aconst_null    
                //     9: invokespecial   com/unity3d/services/core/webview/WebViewApp.<init>:(Lcom/unity3d/services/core/configuration/Configuration;Lcom/unity3d/services/core/webview/WebViewApp$1;)V
                //    12: astore_3       
                //    13: ldc             "?platform=android"
                //    15: astore_2       
                //    16: aload_2        
                //    17: astore_1       
                //    18: aload_0        
                //    19: getfield        com/unity3d/services/core/webview/WebViewApp$1.val$configuration:Lcom/unity3d/services/core/configuration/Configuration;
                //    22: invokevirtual   com/unity3d/services/core/configuration/Configuration.getWebViewUrl:()Ljava/lang/String;
                //    25: ifnull          64
                //    28: new             Ljava/lang/StringBuilder;
                //    31: dup            
                //    32: invokespecial   java/lang/StringBuilder.<init>:()V
                //    35: ldc             "?platform=android"
                //    37: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    40: ldc             "&origin="
                //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    45: aload_0        
                //    46: getfield        com/unity3d/services/core/webview/WebViewApp$1.val$configuration:Lcom/unity3d/services/core/configuration/Configuration;
                //    49: invokevirtual   com/unity3d/services/core/configuration/Configuration.getWebViewUrl:()Ljava/lang/String;
                //    52: ldc             "UTF-8"
                //    54: invokestatic    java/net/URLEncoder.encode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
                //    57: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    60: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //    63: astore_1       
                //    64: aload_1        
                //    65: astore_2       
                //    66: aload_0        
                //    67: getfield        com/unity3d/services/core/webview/WebViewApp$1.val$configuration:Lcom/unity3d/services/core/configuration/Configuration;
                //    70: invokevirtual   com/unity3d/services/core/configuration/Configuration.getWebViewVersion:()Ljava/lang/String;
                //    73: ifnull          111
                //    76: new             Ljava/lang/StringBuilder;
                //    79: dup            
                //    80: invokespecial   java/lang/StringBuilder.<init>:()V
                //    83: aload_1        
                //    84: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    87: ldc             "&version="
                //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //    92: aload_0        
                //    93: getfield        com/unity3d/services/core/webview/WebViewApp$1.val$configuration:Lcom/unity3d/services/core/configuration/Configuration;
                //    96: invokevirtual   com/unity3d/services/core/configuration/Configuration.getWebViewVersion:()Ljava/lang/String;
                //    99: ldc             "UTF-8"
                //   101: invokestatic    java/net/URLEncoder.encode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
                //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   107: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   110: astore_2       
                //   111: aload_3        
                //   112: invokevirtual   com/unity3d/services/core/webview/WebViewApp.getWebView:()Lcom/unity3d/services/core/webview/WebView;
                //   115: new             Ljava/lang/StringBuilder;
                //   118: dup            
                //   119: invokespecial   java/lang/StringBuilder.<init>:()V
                //   122: ldc             "file://"
                //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   127: invokestatic    com/unity3d/services/core/properties/SdkProperties.getLocalWebViewFile:()Ljava/lang/String;
                //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   133: aload_2        
                //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   137: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   140: aload_0        
                //   141: getfield        com/unity3d/services/core/webview/WebViewApp$1.val$configuration:Lcom/unity3d/services/core/configuration/Configuration;
                //   144: invokevirtual   com/unity3d/services/core/configuration/Configuration.getWebViewData:()Ljava/lang/String;
                //   147: ldc             "text/html"
                //   149: ldc             "UTF-8"
                //   151: aconst_null    
                //   152: invokevirtual   com/unity3d/services/core/webview/WebView.loadDataWithBaseURL:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
                //   155: aload_3        
                //   156: invokestatic    com/unity3d/services/core/webview/WebViewApp.setCurrentApp:(Lcom/unity3d/services/core/webview/WebViewApp;)V
                //   159: return         
                //   160: astore_1       
                //   161: ldc             "Couldn't construct WebViewApp"
                //   163: invokestatic    com/unity3d/services/core/log/DeviceLog.error:(Ljava/lang/String;)V
                //   166: invokestatic    com/unity3d/services/core/webview/WebViewApp.access$300:()Landroid/os/ConditionVariable;
                //   169: invokevirtual   android/os/ConditionVariable.open:()V
                //   172: return         
                //   173: astore_1       
                //   174: ldc             "Unsupported charset when encoding origin url"
                //   176: aload_1        
                //   177: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
                //   180: aload_2        
                //   181: astore_1       
                //   182: goto            64
                //   185: astore_2       
                //   186: ldc             "Unsupported charset when encoding webview version"
                //   188: aload_2        
                //   189: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
                //   192: aload_1        
                //   193: astore_2       
                //   194: goto            111
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                  
                //  -----  -----  -----  -----  --------------------------------------
                //  0      13     160    173    Ljava/lang/Exception;
                //  18     64     173    185    Ljava/io/UnsupportedEncodingException;
                //  66     111    185    197    Ljava/io/UnsupportedEncodingException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 93, Size: 93
                //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
                //     at java.util.ArrayList.get(ArrayList.java:429)
                //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
                //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
                //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
        WebViewApp._conditionVariable = new ConditionVariable();
        return WebViewApp._conditionVariable.block(60000L) && getCurrentApp() != null;
    }
    
    public static WebViewApp getCurrentApp() {
        return WebViewApp._currentApp;
    }
    
    private void invokeJavascriptMethod(String string, final String s, final JSONArray jsonArray) throws JSONException {
        final String string2 = jsonArray.toString();
        final StringBuilder sb = new StringBuilder(string.length() + 22 + s.length() + string2.length());
        sb.append("javascript:window.");
        sb.append(string);
        sb.append(".");
        sb.append(s);
        sb.append("(");
        sb.append(string2);
        sb.append(");");
        string = sb.toString();
        DeviceLog.debug("Invoking javascript: " + string);
        this.getWebView().invokeJavascript(string);
    }
    
    public static void setCurrentApp(final WebViewApp currentApp) {
        WebViewApp._currentApp = currentApp;
    }
    
    public void addCallback(final NativeCallback nativeCallback) {
        if (this._nativeCallbacks == null) {
            this._nativeCallbacks = new HashMap<String, NativeCallback>();
        }
        synchronized (this._nativeCallbacks) {
            this._nativeCallbacks.put(nativeCallback.getId(), nativeCallback);
        }
    }
    
    public NativeCallback getCallback(final String s) {
        synchronized (this._nativeCallbacks) {
            return this._nativeCallbacks.get(s);
        }
    }
    
    public Configuration getConfiguration() {
        return this._configuration;
    }
    
    public WebView getWebView() {
        return this._webView;
    }
    
    public boolean invokeCallback(final Invocation invocation) {
        if (!this.isWebAppLoaded()) {
            DeviceLog.debug("invokeBatchCallback ignored because web app is not loaded");
            return false;
        }
        final JSONArray jsonArray = new JSONArray();
        final ArrayList<ArrayList<Object>> responses = invocation.getResponses();
        if (responses != null && !responses.isEmpty()) {
            for (final ArrayList<Object> list : responses) {
                final CallbackStatus callbackStatus = list.get(0);
                final CallbackStatus callbackStatus2 = list.get(1);
                final Object[] array = list.get(2);
                final String s = (String)array[0];
                final Object[] copyOfRange = Arrays.copyOfRange(array, 1, array.length);
                final ArrayList<String> list2 = new ArrayList<String>();
                list2.add(s);
                list2.add(callbackStatus.toString());
                final JSONArray jsonArray2 = new JSONArray();
                if (callbackStatus2 != null) {
                    jsonArray2.put((Object)callbackStatus2.name());
                }
                for (int length = copyOfRange.length, i = 0; i < length; ++i) {
                    jsonArray2.put(copyOfRange[i]);
                }
                list2.add((String)jsonArray2);
                final JSONArray jsonArray3 = new JSONArray();
                final Iterator<String> iterator2 = list2.iterator();
                while (iterator2.hasNext()) {
                    jsonArray3.put((Object)iterator2.next());
                }
                jsonArray.put((Object)jsonArray3);
            }
        }
        try {
            this.invokeJavascriptMethod("nativebridge", "handleCallback", jsonArray);
            return true;
        }
        catch (Exception ex) {
            DeviceLog.exception("Error while invoking batch response for WebView", ex);
            return true;
        }
    }
    
    public boolean invokeMethod(final String s, final String s2, final Method method, final Object... array) {
        if (!this.isWebAppLoaded()) {
            DeviceLog.debug("invokeMethod ignored because web app is not loaded");
            return false;
        }
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)s);
        jsonArray.put((Object)s2);
        if (method != null) {
            final NativeCallback nativeCallback = new NativeCallback(method);
            this.addCallback(nativeCallback);
            jsonArray.put((Object)nativeCallback.getId());
        }
        else {
            jsonArray.put((Object)null);
        }
        if (array != null) {
            for (int length = array.length, i = 0; i < length; ++i) {
                jsonArray.put(array[i]);
            }
        }
        try {
            this.invokeJavascriptMethod("nativebridge", "handleInvocation", jsonArray);
            return true;
        }
        catch (Exception ex) {
            DeviceLog.exception("Error invoking javascript method", ex);
            return false;
        }
    }
    
    public boolean isWebAppInitialized() {
        return this._initialized;
    }
    
    public boolean isWebAppLoaded() {
        return this._webAppLoaded;
    }
    
    public void removeCallback(final NativeCallback nativeCallback) {
        if (this._nativeCallbacks == null) {
            return;
        }
        synchronized (this._nativeCallbacks) {
            this._nativeCallbacks.remove(nativeCallback.getId());
        }
    }
    
    public boolean sendEvent(final Enum enum1, final Enum enum2, final Object... array) {
        if (!this.isWebAppLoaded()) {
            DeviceLog.debug("sendEvent ignored because web app is not loaded");
            return false;
        }
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)enum1.name());
        jsonArray.put((Object)enum2.name());
        for (int length = array.length, i = 0; i < length; ++i) {
            jsonArray.put(array[i]);
        }
        try {
            this.invokeJavascriptMethod("nativebridge", "handleEvent", jsonArray);
            return true;
        }
        catch (Exception ex) {
            DeviceLog.exception("Error while sending event to WebView", ex);
            return false;
        }
    }
    
    public void setConfiguration(final Configuration configuration) {
        this._configuration = configuration;
    }
    
    public void setWebAppInitialized(final boolean initialized) {
        this._initialized = initialized;
        WebViewApp._conditionVariable.open();
    }
    
    public void setWebAppLoaded(final boolean webAppLoaded) {
        this._webAppLoaded = webAppLoaded;
    }
    
    public void setWebView(final WebView webView) {
        this._webView = webView;
    }
    
    private class WebAppChromeClient extends WebChromeClient
    {
        public void onConsoleMessage(final String s, final int n, final String s2) {
            String name = s2;
            final File file = null;
            while (true) {
                try {
                    final File file2 = new File(s2);
                    if (file2 != null) {
                        name = file2.getName();
                    }
                    if (Build$VERSION.SDK_INT < 19) {
                        DeviceLog.debug("JavaScript (sourceId=" + name + ", line=" + n + "): " + s);
                    }
                }
                catch (Exception ex) {
                    DeviceLog.exception("Could not handle sourceId", ex);
                    final File file2 = file;
                    continue;
                }
                break;
            }
        }
    }
    
    private class WebAppClient extends WebViewClient
    {
        public void onPageFinished(final android.webkit.WebView webView, final String s) {
            super.onPageFinished(webView, s);
            DeviceLog.debug("onPageFinished url: " + s);
        }
        
        public void onReceivedError(final android.webkit.WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            if (webView != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + webView.toString());
            }
            if (webResourceRequest != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + webResourceRequest.toString());
            }
            if (webResourceError != null) {
                DeviceLog.error("WEBVIEW_ERROR: " + webResourceError.toString());
            }
        }
        
        public boolean shouldOverrideUrlLoading(final android.webkit.WebView webView, final String s) {
            DeviceLog.debug("Trying to load url: " + s);
            return false;
        }
    }
}
