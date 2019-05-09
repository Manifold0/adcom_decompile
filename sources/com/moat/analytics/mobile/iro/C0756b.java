package com.moat.analytics.mobile.iro;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.moat.analytics.mobile.iro.C0765f.C0764b;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.iro.b */
final class C0756b {
    /* renamed from: ˊ */
    final String f1129;
    /* renamed from: ˋ */
    C0765f f1130;
    /* renamed from: ˎ */
    private final int f1131;
    /* renamed from: ˏ */
    WebView f1132;
    /* renamed from: ॱ */
    private boolean f1133;

    /* renamed from: com.moat.analytics.mobile.iro.b$1 */
    class C07531 extends WebViewClient {
        /* renamed from: ॱ */
        private /* synthetic */ C0756b f1125;

        C07531(C0756b c0756b) {
            this.f1125 = c0756b;
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!this.f1125.f1133) {
                try {
                    this.f1125.f1133 = true;
                    this.f1125.f1130.m1289();
                    this.f1125.f1130.m1291(this.f1125.f1129);
                } catch (Exception e) {
                    C0785o.m1351(e);
                }
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.b$5 */
    class C07545 extends WebViewClient {
        /* renamed from: ˏ */
        private /* synthetic */ C0756b f1126;

        C07545(C0756b c0756b) {
            this.f1126 = c0756b;
        }

        public final void onPageFinished(WebView webView, String str) {
            if (!this.f1126.f1133) {
                try {
                    this.f1126.f1133 = true;
                    this.f1126.f1130.m1289();
                } catch (Exception e) {
                    C0785o.m1351(e);
                }
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.iro.b$a */
    enum C0755a {
        ;
        
        /* renamed from: ˎ */
        public static final int f1127 = 0;
        /* renamed from: ˏ */
        public static final int f1128 = 0;

        static {
            f1128 = 1;
            f1127 = 2;
            int[] iArr = new int[]{1, 2};
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    C0756b(Application application, int i) {
        this.f1131 = i;
        this.f1133 = false;
        this.f1129 = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
        this.f1132 = new WebView(application);
        WebSettings settings = this.f1132.getSettings();
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
        int i2 = C0764b.f1165;
        if (i == C0755a.f1127) {
            i2 = C0764b.f1164;
        }
        try {
            this.f1130 = new C0765f(this.f1132, i2);
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* renamed from: ˋ */
    final void m1237(String str) {
        if (this.f1131 == C0755a.f1128) {
            this.f1132.setWebViewClient(new C07545(this));
            this.f1132.loadData("<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>", AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING);
        }
    }

    /* renamed from: ˎ */
    final void m1238(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.f1131 == C0755a.f1127) {
            this.f1132.setWebViewClient(new C07531(this));
            JSONObject jSONObject = new JSONObject(map);
            WebView webView = this.f1132;
            String str2 = this.f1129;
            webView.loadData(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str2, str, jSONObject.toString(), num3}), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
        }
    }

    C0756b() {
    }

    /* renamed from: ˏ */
    static void m1234(int i, String str, Object obj, String str2) {
        if (!C0803t.m1393().f1300) {
            return;
        }
        if (obj == null) {
            Log.println(i, "Moat" + str, String.format("message = %s", new Object[]{str2}));
            return;
        }
        Log.println(i, "Moat" + str, String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}));
    }

    /* renamed from: ˏ */
    static void m1235(String str, Object obj, String str2) {
        if (C0803t.m1393().f1301) {
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

    /* renamed from: ˊ */
    static void m1230(String str, Object obj, String str2, Exception exception) {
        if (C0803t.m1393().f1300) {
            Log.e("Moat" + str, String.format("id = %s, message = %s", new Object[]{Integer.valueOf(obj.hashCode()), str2}), exception);
        }
    }

    /* renamed from: ˎ */
    static void m1232(String str, String str2) {
        if (!C0803t.m1393().f1300 && ((C0774j) MoatAnalytics.getInstance()).f1224) {
            int i = 2;
            if (str.equals("[ERROR] ")) {
                i = 6;
            }
            Log.println(i, "MoatAnalytics", str + str2);
        }
    }

    /* renamed from: ॱ */
    static String m1236(View view) {
        if (view != null) {
            return view.getClass().getSimpleName() + "@" + view.hashCode();
        }
        return "null";
    }
}
