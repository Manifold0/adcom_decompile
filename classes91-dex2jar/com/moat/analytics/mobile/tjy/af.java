// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.graphics.Rect;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import android.webkit.WebSettings;
import android.os.Build$VERSION;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

class af implements NativeDisplayTracker
{
    private WebView a;
    private bh b;
    private final String c;
    private final ap d;
    private boolean e;
    
    public af(final View view, final String c, final a a, final ap d) {
        if (d.b()) {
            Log.d("MoatNativeDispTracker", "Initializing.");
        }
        this.c = c;
        this.a = new WebView(view.getContext());
        final WebSettings settings = this.a.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setAllowFileAccess(false);
        settings.setDatabaseEnabled(false);
        settings.setDomStorageEnabled(false);
        settings.setGeolocationEnabled(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(false);
        settings.setSaveFormData(false);
        if (Build$VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        if (Build$VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(1);
        }
        this.d = d;
        this.b = new bi(view, this.a, true, a, d);
        this.e = false;
    }
    
    private static String a(final int n, final int n2, final String s, final String s2) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n    <meta charset=\"UTF-8\">\n    <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n<div id=\"mianahwvc\" style=\"width:" + n + "px;height:" + n2 + "px;\">\n    <script src=\"https://z.moatads.com/" + s + "/moatad.js#" + s2 + "\" type=\"text/javascript\"></script>\n</div>\n</body>\n</html>";
    }
    
    private static String a(final Map map) {
        final int n = 0;
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        int n2 = 0;
        int i;
        while (true) {
            i = n;
            if (n2 >= 8) {
                break;
            }
            final String string = "moatClientLevel" + n2;
            if (map.containsKey(string)) {
                linkedHashMap.put(string, map.get(string));
            }
            ++n2;
        }
        while (i < 8) {
            final String string2 = "moatClientSlicer" + i;
            if (map.containsKey(string2)) {
                linkedHashMap.put(string2, map.get(string2));
            }
            ++i;
        }
        for (final String s : map.keySet()) {
            if (!linkedHashMap.containsKey(s)) {
                linkedHashMap.put(s, map.get(s));
            }
        }
        final StringBuilder sb = new StringBuilder();
        for (final String s2 : linkedHashMap.keySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(s2).append('=').append(linkedHashMap.get(s2));
        }
        return String.valueOf(sb);
    }
    
    private void a() {
        if (!this.e) {
            this.a("Shutting down.");
            this.b.d();
            this.a.loadUrl("about:blank");
            this.a.destroy();
            this.a = null;
            this.b = null;
            this.e = true;
        }
    }
    
    private void a(final String s) {
        if (this.d.b()) {
            Log.d("MoatNativeDispTracker", String.format("id = %s, message = %s", this.hashCode(), s));
        }
    }
    
    @Override
    public void stopTracking() {
        this.a("Called stopTracking.");
        this.a();
    }
    
    @Override
    public boolean track(final Map map) {
        final boolean b = false;
        Label_0020: {
            if (map == null) {
                break Label_0020;
            }
        Label_0191_Outer:
            while (true) {
                boolean c = b;
                while (true) {
                    while (true) {
                        try {
                            if (map.isEmpty()) {
                                c = b;
                                this.a("adIdMap is null or empty. Shutting down.");
                                c = b;
                                this.a();
                                return false;
                            }
                            c = b;
                            final boolean b2 = c = this.b.c();
                            if (b2) {
                                c = b2;
                                final Rect e = this.b.e();
                                c = b2;
                                final int width = e.width();
                                c = b2;
                                final int height = e.height();
                                c = b2;
                                final String a = a(map);
                                c = b2;
                                this.a("Parsed ad ids = " + a);
                                c = b2;
                                final String a2 = a(width, height, this.c, a);
                                c = b2;
                                this.a.loadData(a2, "text/html", "utf-8");
                                c = b2;
                            }
                            final StringBuilder sb = new StringBuilder("Attempt to start tracking ad was ");
                            if (c) {
                                final String s = "";
                                this.a(sb.append(s).append("successful.").toString());
                                return c;
                            }
                        }
                        catch (Exception ex) {
                            com.moat.analytics.mobile.tjy.base.exception.a.a(ex);
                            continue Label_0191_Outer;
                        }
                        break;
                    }
                    final String s = "un";
                    continue;
                }
            }
        }
    }
}
