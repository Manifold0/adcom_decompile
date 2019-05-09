package com.moat.analytics.mobile.vng;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.AudienceNetworkActivity;
import com.moat.analytics.mobile.vng.C0845j.C0844a;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.g */
class C0834g {
    /* renamed from: a */
    WebView f1374a;
    /* renamed from: b */
    C0845j f1375b;
    /* renamed from: c */
    final String f1376c = String.format(Locale.ROOT, "_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    /* renamed from: d */
    private final C0833a f1377d;
    /* renamed from: e */
    private boolean f1378e = false;

    /* renamed from: com.moat.analytics.mobile.vng.g$1 */
    class C08311 extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ C0834g f1369a;

        C08311(C0834g c0834g) {
            this.f1369a = c0834g;
        }

        public void onPageFinished(WebView webView, String str) {
            if (!this.f1369a.f1378e) {
                try {
                    this.f1369a.f1378e = true;
                    this.f1369a.f1375b.m1519a();
                } catch (Exception e) {
                    C0849m.m1543a(e);
                }
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.g$2 */
    class C08322 extends WebViewClient {
        /* renamed from: a */
        final /* synthetic */ C0834g f1370a;

        C08322(C0834g c0834g) {
            this.f1370a = c0834g;
        }

        public void onPageFinished(WebView webView, String str) {
            if (!this.f1370a.f1378e) {
                try {
                    this.f1370a.f1378e = true;
                    this.f1370a.f1375b.m1519a();
                    this.f1370a.f1375b.m1525c(this.f1370a.f1376c);
                } catch (Exception e) {
                    C0849m.m1543a(e);
                }
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.g$a */
    enum C0833a {
        DISPLAY,
        f1372b
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    C0834g(Context context, C0833a c0833a) {
        this.f1377d = c0833a;
        this.f1374a = new WebView(context);
        WebSettings settings = this.f1374a.getSettings();
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
        C0844a c0844a = C0844a.NATIVE_DISPLAY;
        if (c0833a == C0833a.f1372b) {
            c0844a = C0844a.NATIVE_VIDEO;
        }
        this.f1375b = new C0845j(this.f1374a, c0844a);
    }

    /* renamed from: a */
    private static String m1472a(String str, String str2, Integer num, Integer num2, JSONObject jSONObject, Integer num3) {
        return String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), str, str2, jSONObject.toString(), num3});
    }

    /* renamed from: b */
    private static String m1475b(String str) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js\" type=\"text/javascript\"></script>\n" + "</body>\n" + "</html>";
    }

    /* renamed from: a */
    void m1476a() {
        this.f1375b = null;
        this.f1374a.destroy();
        this.f1374a = null;
    }

    /* renamed from: a */
    void m1477a(String str) {
        if (this.f1377d == C0833a.DISPLAY) {
            this.f1374a.setWebViewClient(new C08311(this));
            this.f1374a.loadData(C0834g.m1475b(str), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING);
        }
    }

    /* renamed from: a */
    void m1478a(String str, Map<String, String> map, Integer num, Integer num2, Integer num3) {
        if (this.f1377d == C0833a.f1372b) {
            this.f1374a.setWebViewClient(new C08322(this));
            this.f1374a.loadData(C0834g.m1472a(this.f1376c, str, num, num2, new JSONObject(map), num3), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
        }
    }
}
