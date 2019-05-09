package com.chartboost.sdk.impl;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1377e.C1376a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1386a;
import com.chartboost.sdk.Model.C1387b;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

public class bd extends WebChromeClient {
    /* renamed from: a */
    private View f3127a;
    /* renamed from: b */
    private ViewGroup f3128b;
    /* renamed from: c */
    private boolean f3129c = false;
    /* renamed from: d */
    private FrameLayout f3130d;
    /* renamed from: e */
    private CustomViewCallback f3131e;
    /* renamed from: f */
    private C1427a f3132f;
    /* renamed from: g */
    private final bf f3133g;
    /* renamed from: h */
    private final Handler f3134h;

    /* renamed from: com.chartboost.sdk.impl.bd$a */
    public interface C1427a {
        /* renamed from: a */
        void m3484a(boolean z);
    }

    public bd(View view, ViewGroup viewGroup, View view2, be beVar, bf bfVar, Handler handler) {
        this.f3127a = view;
        this.f3128b = viewGroup;
        this.f3133g = bfVar;
        this.f3134h = handler;
    }

    public boolean onConsoleMessage(ConsoleMessage cm) {
        Log.d(bd.class.getSimpleName(), "Chartboost Webview:" + cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
        return true;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        try {
            JSONObject jSONObject = new JSONObject(message);
            result.confirm(m3485a(jSONObject.getJSONObject("eventArgs"), jSONObject.getString("eventType")));
        } catch (JSONException e) {
            CBLogging.m3099b("CBWebChromeClient", "Exception caught parsing the function name from js to native");
        }
        return true;
    }

    /* renamed from: a */
    public String m3485a(JSONObject jSONObject, String str) {
        int i = 0;
        int i2 = -1;
        switch (str.hashCode()) {
            case -2012425132:
                if (str.equals("getDefaultPosition")) {
                    i2 = 18;
                    break;
                }
                break;
            case -1757019252:
                if (str.equals("getCurrentPosition")) {
                    i2 = 17;
                    break;
                }
                break;
            case -1554056650:
                if (str.equals("currentVideoDuration")) {
                    i2 = 7;
                    break;
                }
                break;
            case -1263203643:
                if (str.equals("openUrl")) {
                    i2 = 14;
                    break;
                }
                break;
            case -1086137328:
                if (str.equals("videoCompleted")) {
                    i2 = 3;
                    break;
                }
                break;
            case -715147645:
                if (str.equals("getScreenSize")) {
                    i2 = 16;
                    break;
                }
                break;
            case -640720077:
                if (str.equals("videoPlaying")) {
                    i2 = 4;
                    break;
                }
                break;
            case 3529469:
                if (str.equals("show")) {
                    i2 = 9;
                    break;
                }
                break;
            case 94750088:
                if (str.equals("click")) {
                    i2 = 1;
                    break;
                }
                break;
            case 94756344:
                if (str.equals(String.CLOSE)) {
                    i2 = 2;
                    break;
                }
                break;
            case 95458899:
                if (str.equals("debug")) {
                    i2 = 12;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    i2 = 10;
                    break;
                }
                break;
            case 133423073:
                if (str.equals("setOrientationProperties")) {
                    i2 = 20;
                    break;
                }
                break;
            case 160987616:
                if (str.equals("getParameters")) {
                    i2 = 0;
                    break;
                }
                break;
            case 937504109:
                if (str.equals("getOrientationProperties")) {
                    i2 = 19;
                    break;
                }
                break;
            case 939594121:
                if (str.equals("videoPaused")) {
                    i2 = 5;
                    break;
                }
                break;
            case 1000390722:
                if (str.equals("videoReplay")) {
                    i2 = 6;
                    break;
                }
                break;
            case 1082777163:
                if (str.equals("totalVideoDuration")) {
                    i2 = 8;
                    break;
                }
                break;
            case 1124446108:
                if (str.equals("warning")) {
                    i2 = 11;
                    break;
                }
                break;
            case 1270488759:
                if (str.equals("tracking")) {
                    i2 = 13;
                    break;
                }
                break;
            case 1880941391:
                if (str.equals("getMaxSize")) {
                    i2 = 15;
                    break;
                }
                break;
        }
        switch (i2) {
            case 0:
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                if (this.f3133g.e != null) {
                    C1386a c1386a = this.f3133g.e.f2770p;
                    if (c1386a != null) {
                        JSONObject a = C1377e.m3130a(new C1376a[0]);
                        for (Entry entry : c1386a.f2732d.entrySet()) {
                            C1377e.m3131a(a, (String) entry.getKey(), entry.getValue());
                        }
                        for (Entry entry2 : c1386a.f2731c.entrySet()) {
                            C1387b c1387b = (C1387b) entry2.getValue();
                            C1377e.m3131a(a, (String) entry2.getKey(), c1387b.f2748a + "/" + c1387b.f2749b);
                        }
                        return a.toString();
                    }
                }
                return "{}";
            case 1:
                break;
            case 2:
                i = 1;
                break;
            case 3:
                i = 9;
                break;
            case 4:
                i = 11;
                break;
            case 5:
                i = 10;
                break;
            case 6:
                i = 12;
                break;
            case 7:
                i = 2;
                break;
            case 8:
                i = 7;
                break;
            case 9:
                i = 6;
                break;
            case 10:
                Log.d(be.class.getName(), "Javascript Error occured");
                i = 4;
                break;
            case 11:
                Log.d(be.class.getName(), "Javascript warning occurred");
                i = 13;
                break;
            case 12:
                i = 3;
                break;
            case 13:
                i = 8;
                break;
            case 14:
                i = 5;
                break;
            case 15:
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.f3133g.m3515s();
            case 16:
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.f3133g.m3516t();
            case 17:
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.f3133g.m3518v();
            case 18:
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.f3133g.m3517u();
            case 19:
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                return this.f3133g.m3512p();
            case 20:
                Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
                i = 14;
                break;
            default:
                Log.e("CBWebChromeClient", "JavaScript to native " + str + " callback not recognized.");
                return "Function name not recognized.";
        }
        Log.d("CBWebChromeClient", "JavaScript to native " + str + " callback triggered.");
        this.f3134h.post(new bg(this, this.f3133g, i, str, jSONObject));
        return "Native function successfully called.";
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            this.f3129c = true;
            this.f3130d = frameLayout;
            this.f3131e = callback;
            this.f3127a.setVisibility(4);
            this.f3128b.addView(this.f3130d, new LayoutParams(-1, -1));
            this.f3128b.setVisibility(0);
            if (this.f3132f != null) {
                this.f3132f.m3484a(true);
            }
        }
    }

    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        onShowCustomView(view, callback);
    }

    public void onHideCustomView() {
        if (this.f3129c) {
            this.f3128b.setVisibility(4);
            this.f3128b.removeView(this.f3130d);
            this.f3127a.setVisibility(0);
            if (!(this.f3131e == null || this.f3131e.getClass().getName().contains(".chromium."))) {
                this.f3131e.onCustomViewHidden();
            }
            this.f3129c = false;
            this.f3130d = null;
            this.f3131e = null;
            if (this.f3132f != null) {
                this.f3132f.m3484a(false);
            }
        }
    }
}
