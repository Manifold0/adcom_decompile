package com.tapjoy;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONObject;

public class TJWebViewJSInterface {
    /* renamed from: a */
    WebView f6995a;
    /* renamed from: b */
    TJWebViewJSInterfaceListener f6996b;
    /* renamed from: c */
    private final ConcurrentLinkedQueue f6997c = new ConcurrentLinkedQueue();
    /* renamed from: d */
    private boolean f6998d;

    @TargetApi(19)
    /* renamed from: com.tapjoy.TJWebViewJSInterface$a */
    class C2816a extends AsyncTask {
        /* renamed from: a */
        WebView f6993a;
        /* renamed from: b */
        final /* synthetic */ TJWebViewJSInterface f6994b;

        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            return ((String[]) objArr)[0];
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            if (this.f6993a == null) {
                return;
            }
            if (!str.startsWith("javascript:") || VERSION.SDK_INT < 19) {
                try {
                    this.f6993a.loadUrl(str);
                    return;
                } catch (Exception e) {
                    TapjoyLog.m7127e("TJWebViewJSInterface", new TapjoyErrorMessage(ErrorType.INTERNAL_ERROR, "Exception in loadUrl. Device not supported. " + e.toString()));
                    return;
                }
            }
            try {
                this.f6993a.evaluateJavascript(str.replaceFirst("javascript:", ""), null);
            } catch (Exception e2) {
                TapjoyLog.m7127e("TJWebViewJSInterface", new TapjoyErrorMessage(ErrorType.INTERNAL_ERROR, "Exception in evaluateJavascript. Device not supported. " + e2.toString()));
            }
        }

        public C2816a(TJWebViewJSInterface tJWebViewJSInterface, WebView webView) {
            this.f6994b = tJWebViewJSInterface;
            this.f6993a = webView;
        }
    }

    public TJWebViewJSInterface(WebView w, TJWebViewJSInterfaceListener l) {
        this.f6995a = w;
        this.f6996b = l;
    }

    @JavascriptInterface
    public void dispatchMethod(String params) {
        TapjoyLog.m7126d("TJWebViewJSInterface", "dispatchMethod params: " + params);
        try {
            JSONObject jSONObject = new JSONObject(params);
            String string = jSONObject.getJSONObject("data").getString("method");
            TapjoyLog.m7126d("TJWebViewJSInterface", "method: " + string);
            if (this.f6996b != null) {
                this.f6996b.onDispatchMethod(string, jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callback(ArrayList result, String methodName, String callbackID) {
        try {
            callbackToJavaScript(new JSONArray(result), methodName, callbackID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callback(Map result, String methodName, String callbackID) {
        try {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(new JSONObject(result));
            callbackToJavaScript(jSONArray, methodName, callbackID);
        } catch (Exception e) {
            TapjoyLog.m7128e("TJWebViewJSInterface", "Exception in callback to JS: " + e.toString());
            e.printStackTrace();
        }
    }

    public void callbackToJavaScript(Object arguments, String methodName, String callbackID) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(String.ARGUMENTS, arguments);
            if (methodName != null && methodName.length() > 0) {
                jSONObject.put("method", methodName);
            }
            JSONObject jSONObject2 = new JSONObject();
            if (callbackID != null && callbackID.length() > 0) {
                jSONObject2.put(String.CALLBACK_ID, callbackID);
            }
            jSONObject2.put("data", jSONObject);
            String str = "javascript:if(window.AndroidWebViewJavascriptBridge) AndroidWebViewJavascriptBridge._handleMessageFromAndroid('" + jSONObject2 + "');";
            if (this.f6998d) {
                new C2816a(this, this.f6995a).execute(new String[]{str});
                return;
            }
            this.f6997c.add(str);
        } catch (Exception e) {
            TapjoyLog.m7128e("TJWebViewJSInterface", "Exception in callback to JS: " + e.toString());
            e.printStackTrace();
        }
    }

    public void flushMessageQueue() {
        if (!this.f6998d) {
            while (((String) this.f6997c.poll()) != null) {
                new C2816a(this, this.f6995a).execute(new String[]{r0});
            }
            this.f6998d = true;
        }
    }
}
