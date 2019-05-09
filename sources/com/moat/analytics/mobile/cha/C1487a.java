package com.moat.analytics.mobile.cha;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.moat.analytics.mobile.cha.C1507j.C1506e;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.cha.a */
final class C1487a {
    /* renamed from: ˊ */
    final String f3430;
    /* renamed from: ˋ */
    WebView f3431;
    /* renamed from: ˎ */
    private boolean f3432;
    /* renamed from: ˏ */
    C1507j f3433;
    /* renamed from: ॱ */
    private final int f3434;

    /* renamed from: com.moat.analytics.mobile.cha.a$2 */
    class C14842 extends WebViewClient {
        /* renamed from: ˋ */
        private /* synthetic */ C1487a f3426;

        C14842(C1487a c1487a) {
            this.f3426 = c1487a;
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!this.f3426.f3432) {
                try {
                    this.f3426.f3432 = true;
                    this.f3426.f3433.m3809();
                } catch (Exception e) {
                    C1518o.m3840(e);
                }
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.a$5 */
    class C14855 extends WebViewClient {
        /* renamed from: ˊ */
        private /* synthetic */ C1487a f3427;

        C14855(C1487a c1487a) {
            this.f3427 = c1487a;
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!this.f3427.f3432) {
                try {
                    this.f3427.f3432 = true;
                    this.f3427.f3433.m3809();
                    this.f3427.f3433.m3808(this.f3427.f3430);
                } catch (Exception e) {
                    C1518o.m3840(e);
                }
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.cha.a$d */
    enum C1486d {
        ;
        
        /* renamed from: ˋ */
        public static final int f3428 = 0;
        /* renamed from: ˏ */
        public static final int f3429 = 0;

        static {
            f3429 = 1;
            f3428 = 2;
            int[] iArr = new int[]{1, 2};
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    C1487a(Application application, int i) {
        this.f3434 = i;
        this.f3432 = false;
        this.f3430 = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
        this.f3431 = new WebView(application);
        WebSettings settings = this.f3431.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        int i2 = C1506e.f3512;
        if (i == C1486d.f3428) {
            i2 = C1506e.f3511;
        }
        try {
            this.f3433 = new C1507j(this.f3431, i2);
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* renamed from: ˊ */
    final void m3719(String str) {
        if (this.f3434 == C1486d.f3429) {
            this.f3431.setWebViewClient(new C14842(this));
            this.f3431.loadData("<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING);
        }
    }

    /* renamed from: ˊ */
    final void m3720(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.f3434 == C1486d.f3428) {
            this.f3431.setWebViewClient(new C14855(this));
            JSONObject jSONObject = new JSONObject(map);
            WebView webView = this.f3431;
            String str2 = this.f3430;
            webView.loadData(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str2, str, jSONObject.toString(), num3}), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
        }
    }

    C1487a() {
    }

    /* renamed from: ˏ */
    static void m3715(int i, String str, Object obj, String str2) {
        if (!C1536t.m3887().f3611) {
            return;
        }
        if (obj == null) {
            Log.println(i, "Moat" + str, String.format("message = %s", new Object[]{str2}));
            return;
        }
        Log.println(i, "Moat" + str, String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}));
    }

    /* renamed from: ˏ */
    static void m3716(String str, Object obj, String str2) {
        if (C1536t.m3887().f3608) {
            String str3;
            String str4 = "Moat" + str;
            String str5 = "id = %s, message = %s";
            Object[] objArr = new Object[2];
            if (obj == null) {
                str3 = "null";
            } else {
                str3 = Integer.valueOf(obj.hashCode());
            }
            objArr[0] = str3;
            objArr[1] = str2;
            Log.println(2, str4, String.format(str5, objArr));
        }
    }

    /* renamed from: ॱ */
    static void m3717(String str, Object obj, String str2, Exception exception) {
        if (C1536t.m3887().f3611) {
            Log.e("Moat" + str, String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), exception);
        }
    }

    /* renamed from: ˊ */
    static void m3712(String str, String str2) {
        if (!C1536t.m3887().f3611 && ((C1495f) MoatAnalytics.getInstance()).f3472) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            Log.println(i, "MoatAnalytics", str + str2);
        }
    }

    /* renamed from: ˏ */
    static String m3714(View view) {
        if (view != null) {
            return view.getClass().getSimpleName() + "@" + view.hashCode();
        }
        return "null";
    }
}
