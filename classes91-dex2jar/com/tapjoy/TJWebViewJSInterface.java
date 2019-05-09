// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.webkit.JavascriptInterface;
import org.json.JSONObject;
import java.util.Map;
import java.util.Collection;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import android.webkit.WebView;

public class TJWebViewJSInterface
{
    WebView a;
    TJWebViewJSInterfaceListener b;
    private final ConcurrentLinkedQueue c;
    private boolean d;
    
    public TJWebViewJSInterface(final WebView a, final TJWebViewJSInterfaceListener b) {
        this.c = new ConcurrentLinkedQueue();
        this.a = a;
        this.b = b;
    }
    
    public void callback(final ArrayList list, final String s, final String s2) {
        try {
            this.callbackToJavaScript(new JSONArray((Collection)list), s, s2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void callback(final Map map, final String s, final String s2) {
        try {
            final JSONArray jsonArray = new JSONArray();
            jsonArray.put((Object)new JSONObject(map));
            this.callbackToJavaScript(jsonArray, s, s2);
        }
        catch (Exception ex) {
            TapjoyLog.e("TJWebViewJSInterface", "Exception in callback to JS: " + ex.toString());
            ex.printStackTrace();
        }
    }
    
    public void callbackToJavaScript(final Object o, final String s, final String s2) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("arguments", o);
            if (s != null && s.length() > 0) {
                jsonObject.put("method", (Object)s);
            }
            final JSONObject jsonObject2 = new JSONObject();
            if (s2 != null && s2.length() > 0) {
                jsonObject2.put("callbackId", (Object)s2);
            }
            jsonObject2.put("data", (Object)jsonObject);
            final String string = "javascript:if(window.AndroidWebViewJavascriptBridge) AndroidWebViewJavascriptBridge._handleMessageFromAndroid('" + jsonObject2 + "');";
            if (!this.d) {
                this.c.add(string);
                return;
            }
            new a(this.a).execute((Object[])new String[] { string });
        }
        catch (Exception ex) {
            TapjoyLog.e("TJWebViewJSInterface", "Exception in callback to JS: " + ex.toString());
            ex.printStackTrace();
        }
    }
    
    @JavascriptInterface
    public void dispatchMethod(final String s) {
        TapjoyLog.d("TJWebViewJSInterface", "dispatchMethod params: " + s);
        try {
            final JSONObject jsonObject = new JSONObject(s);
            final String string = jsonObject.getJSONObject("data").getString("method");
            TapjoyLog.d("TJWebViewJSInterface", "method: " + string);
            if (this.b != null) {
                this.b.onDispatchMethod(string, jsonObject);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void flushMessageQueue() {
        if (!this.d) {
            while (true) {
                final String s = this.c.poll();
                if (s == null) {
                    break;
                }
                new a(this.a).execute((Object[])new String[] { s });
            }
            this.d = true;
        }
    }
    
    @TargetApi(19)
    final class a extends AsyncTask
    {
        WebView a;
        
        public a(final WebView a) {
            this.a = a;
        }
    }
}
