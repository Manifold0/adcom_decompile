package com.moat.analytics.mobile.tjy;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import java.util.LinkedHashMap;
import java.util.Map;

class af implements NativeDisplayTracker {
    /* renamed from: a */
    private WebView f6623a;
    /* renamed from: b */
    private bh f6624b;
    /* renamed from: c */
    private final String f6625c;
    /* renamed from: d */
    private final ap f6626d;
    /* renamed from: e */
    private boolean f6627e;

    public af(View view, String str, C2742a c2742a, ap apVar) {
        if (apVar.mo6105b()) {
            Log.d("MoatNativeDispTracker", "Initializing.");
        }
        this.f6625c = str;
        this.f6623a = new WebView(view.getContext());
        WebSettings settings = this.f6623a.getSettings();
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
        this.f6626d = apVar;
        this.f6624b = new bi(view, this.f6623a, true, c2742a, apVar);
        this.f6627e = false;
    }

    /* renamed from: a */
    private static String m6806a(int i, int i2, String str, String str2) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n    <meta charset=\"UTF-8\">\n    <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n<div id=\"mianahwvc\" style=\"width:" + i + "px;height:" + i2 + "px;\">\n    <script src=\"https://z.moatads.com/" + str + "/moatad.js#" + str2 + "\" type=\"text/javascript\"></script>\n</div>\n</body>\n</html>";
    }

    /* renamed from: a */
    private static String m6807a(Map map) {
        int i = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < 8; i2++) {
            String str = "moatClientLevel" + i2;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        while (i < 8) {
            String str2 = "moatClientSlicer" + i;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
            i++;
        }
        for (String str3 : map.keySet()) {
            if (!linkedHashMap.containsKey(str3)) {
                linkedHashMap.put(str3, (String) map.get(str3));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str32 : linkedHashMap.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(RequestParameters.AMPERSAND);
            }
            stringBuilder.append(str32).append('=').append((String) linkedHashMap.get(str32));
        }
        return String.valueOf(stringBuilder);
    }

    /* renamed from: a */
    private void m6808a() {
        if (!this.f6627e) {
            m6809a("Shutting down.");
            this.f6624b.mo6122d();
            this.f6623a.loadUrl("about:blank");
            this.f6623a.destroy();
            this.f6623a = null;
            this.f6624b = null;
            this.f6627e = true;
        }
    }

    /* renamed from: a */
    private void m6809a(String str) {
        if (this.f6626d.mo6105b()) {
            Log.d("MoatNativeDispTracker", String.format("id = %s, message = %s", new Object[]{Integer.valueOf(hashCode()), str}));
        }
    }

    public void stopTracking() {
        m6809a("Called stopTracking.");
        m6808a();
    }

    public boolean track(Map map) {
        boolean z = false;
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    z = this.f6624b.mo6121c();
                    if (z) {
                        Rect e = this.f6624b.mo6123e();
                        int width = e.width();
                        int height = e.height();
                        String a = m6807a(map);
                        m6809a("Parsed ad ids = " + a);
                        this.f6623a.loadData(m6806a(width, height, this.f6625c, a), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING);
                    }
                    m6809a("Attempt to start tracking ad was " + (z ? "" : "un") + "successful.");
                    return z;
                }
            } catch (Exception e2) {
                C2747a.m6882a(e2);
            }
        }
        m6809a("adIdMap is null or empty. Shutting down.");
        m6808a();
        return z;
    }
}
