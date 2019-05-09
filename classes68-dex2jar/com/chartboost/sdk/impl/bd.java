// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.view.ViewGroup$LayoutParams;
import org.json.JSONException;
import com.chartboost.sdk.Libraries.CBLogging;
import android.webkit.JsPromptResult;
import android.webkit.WebView;
import android.webkit.ConsoleMessage;
import java.util.Iterator;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.b;
import java.util.Map;
import com.chartboost.sdk.Libraries.e;
import android.util.Log;
import org.json.JSONObject;
import android.os.Handler;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.view.ViewGroup;
import android.view.View;
import android.webkit.WebChromeClient;

public class bd extends WebChromeClient
{
    private View a;
    private ViewGroup b;
    private boolean c;
    private FrameLayout d;
    private WebChromeClient$CustomViewCallback e;
    private a f;
    private final bf g;
    private final Handler h;
    
    public bd(final View a, final ViewGroup b, final View view, final be be, final bf g, final Handler h) {
        this.a = a;
        this.b = b;
        this.c = false;
        this.g = g;
        this.h = h;
    }
    
    public String a(JSONObject a, final String s) {
        int n = 0;
        int n2 = -1;
        switch (s.hashCode()) {
            case 160987616: {
                if (s.equals("getParameters")) {
                    n2 = 0;
                    break;
                }
                break;
            }
            case 94750088: {
                if (s.equals("click")) {
                    n2 = 1;
                    break;
                }
                break;
            }
            case 94756344: {
                if (s.equals("close")) {
                    n2 = 2;
                    break;
                }
                break;
            }
            case -1086137328: {
                if (s.equals("videoCompleted")) {
                    n2 = 3;
                    break;
                }
                break;
            }
            case -640720077: {
                if (s.equals("videoPlaying")) {
                    n2 = 4;
                    break;
                }
                break;
            }
            case 939594121: {
                if (s.equals("videoPaused")) {
                    n2 = 5;
                    break;
                }
                break;
            }
            case 1000390722: {
                if (s.equals("videoReplay")) {
                    n2 = 6;
                    break;
                }
                break;
            }
            case -1554056650: {
                if (s.equals("currentVideoDuration")) {
                    n2 = 7;
                    break;
                }
                break;
            }
            case 1082777163: {
                if (s.equals("totalVideoDuration")) {
                    n2 = 8;
                    break;
                }
                break;
            }
            case 3529469: {
                if (s.equals("show")) {
                    n2 = 9;
                    break;
                }
                break;
            }
            case 96784904: {
                if (s.equals("error")) {
                    n2 = 10;
                    break;
                }
                break;
            }
            case 1124446108: {
                if (s.equals("warning")) {
                    n2 = 11;
                    break;
                }
                break;
            }
            case 95458899: {
                if (s.equals("debug")) {
                    n2 = 12;
                    break;
                }
                break;
            }
            case 1270488759: {
                if (s.equals("tracking")) {
                    n2 = 13;
                    break;
                }
                break;
            }
            case -1263203643: {
                if (s.equals("openUrl")) {
                    n2 = 14;
                    break;
                }
                break;
            }
            case 1880941391: {
                if (s.equals("getMaxSize")) {
                    n2 = 15;
                    break;
                }
                break;
            }
            case -715147645: {
                if (s.equals("getScreenSize")) {
                    n2 = 16;
                    break;
                }
                break;
            }
            case -1757019252: {
                if (s.equals("getCurrentPosition")) {
                    n2 = 17;
                    break;
                }
                break;
            }
            case -2012425132: {
                if (s.equals("getDefaultPosition")) {
                    n2 = 18;
                    break;
                }
                break;
            }
            case 937504109: {
                if (s.equals("getOrientationProperties")) {
                    n2 = 19;
                    break;
                }
                break;
            }
            case 133423073: {
                if (s.equals("setOrientationProperties")) {
                    n2 = 20;
                    break;
                }
                break;
            }
        }
        Label_0861: {
            switch (n2) {
                default: {
                    Log.e("CBWebChromeClient", "JavaScript to native " + s + " callback not recognized.");
                    return "Function name not recognized.";
                }
                case 0: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    if (this.g.e != null) {
                        final com.chartboost.sdk.Model.a p2 = this.g.e.p;
                        if (p2 != null) {
                            a = com.chartboost.sdk.Libraries.e.a(new e.a[0]);
                            for (final Map.Entry<String, String> entry : p2.d.entrySet()) {
                                com.chartboost.sdk.Libraries.e.a(a, entry.getKey(), entry.getValue());
                            }
                            for (final Map.Entry<String, b> entry2 : p2.c.entrySet()) {
                                final b b = entry2.getValue();
                                com.chartboost.sdk.Libraries.e.a(a, entry2.getKey(), b.a + "/" + b.b);
                            }
                            return a.toString();
                        }
                    }
                    return "{}";
                }
                case 2: {
                    n = 1;
                    break Label_0861;
                }
                case 20: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    n = 14;
                    break Label_0861;
                }
                case 14: {
                    n = 5;
                    break Label_0861;
                }
                case 13: {
                    n = 8;
                    break Label_0861;
                }
                case 12: {
                    n = 3;
                    break Label_0861;
                }
                case 11: {
                    Log.d(be.class.getName(), "Javascript warning occurred");
                    n = 13;
                    break Label_0861;
                }
                case 10: {
                    Log.d(be.class.getName(), "Javascript Error occured");
                    n = 4;
                    break Label_0861;
                }
                case 9: {
                    n = 6;
                    break Label_0861;
                }
                case 8: {
                    n = 7;
                    break Label_0861;
                }
                case 7: {
                    n = 2;
                    break Label_0861;
                }
                case 6: {
                    n = 12;
                    break Label_0861;
                }
                case 5: {
                    n = 10;
                    break Label_0861;
                }
                case 4: {
                    n = 11;
                    break Label_0861;
                }
                case 3: {
                    n = 9;
                }
                case 1: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    this.h.post((Runnable)new bg(this, this.g, n, s, a));
                    return "Native function successfully called.";
                }
                case 15: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    return this.g.s();
                }
                case 16: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    return this.g.t();
                }
                case 17: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    return this.g.v();
                }
                case 18: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    return this.g.u();
                }
                case 19: {
                    Log.d("CBWebChromeClient", "JavaScript to native " + s + " callback triggered.");
                    return this.g.p();
                }
            }
        }
    }
    
    public boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        Log.d(bd.class.getSimpleName(), "Chartboost Webview:" + consoleMessage.message() + " -- From line " + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
        return true;
    }
    
    public void onHideCustomView() {
        if (this.c) {
            this.b.setVisibility(4);
            this.b.removeView((View)this.d);
            this.a.setVisibility(0);
            if (this.e != null && !this.e.getClass().getName().contains(".chromium.")) {
                this.e.onCustomViewHidden();
            }
            this.c = false;
            this.d = null;
            this.e = null;
            if (this.f != null) {
                this.f.a(false);
            }
        }
    }
    
    public boolean onJsPrompt(final WebView webView, final String s, final String s2, final String s3, final JsPromptResult jsPromptResult) {
        try {
            final JSONObject jsonObject = new JSONObject(s2);
            jsPromptResult.confirm(this.a(jsonObject.getJSONObject("eventArgs"), jsonObject.getString("eventType")));
            return true;
        }
        catch (JSONException ex) {
            CBLogging.b("CBWebChromeClient", "Exception caught parsing the function name from js to native");
            return true;
        }
    }
    
    public void onShowCustomView(final View view, final int n, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        this.onShowCustomView(view, webChromeClient$CustomViewCallback);
    }
    
    public void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback e) {
        if (view instanceof FrameLayout) {
            final FrameLayout d = (FrameLayout)view;
            this.c = true;
            this.d = d;
            this.e = e;
            this.a.setVisibility(4);
            this.b.addView((View)this.d, new ViewGroup$LayoutParams(-1, -1));
            this.b.setVisibility(0);
            if (this.f != null) {
                this.f.a(true);
            }
        }
    }
    
    public interface a
    {
        void a(final boolean p0);
    }
}
