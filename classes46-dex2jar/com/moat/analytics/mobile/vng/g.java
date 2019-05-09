// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import java.util.Map;
import android.webkit.WebViewClient;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.os.Build$VERSION;
import java.util.Locale;
import android.content.Context;
import android.webkit.WebView;

class g
{
    WebView a;
    j b;
    final String c;
    private final a d;
    private boolean e;
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    g(final Context context, final a d) {
        this.d = d;
        this.e = false;
        this.c = String.format(Locale.ROOT, "_moatTracker%d", (int)(Math.random() * 1.0E8));
        this.a = new WebView(context);
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
        j.a a = j.a.b;
        if (d == g.a.b) {
            a = j.a.c;
        }
        this.b = new j(this.a, a);
    }
    
    private static String a(final String s, final String s2, final Integer n, final Integer n2, final JSONObject jsonObject, final Integer n3) {
        return String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", "mianahwvc", n, n2, "mianahwvc", System.currentTimeMillis(), s, s2, jsonObject.toString(), n3);
    }
    
    private static String b(final String s) {
        return "<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + s + "/moatad.js\" type=\"text/javascript\"></script>\n" + "</body>\n" + "</html>";
    }
    
    void a() {
        this.b = null;
        this.a.destroy();
        this.a = null;
    }
    
    void a(final String s) {
        if (this.d == g.a.a) {
            this.a.setWebViewClient((WebViewClient)new WebViewClient() {
                public void onPageFinished(final WebView webView, final String s) {
                    if (g.this.e) {
                        return;
                    }
                    try {
                        g.this.e = true;
                        g.this.b.a();
                    }
                    catch (Exception ex) {
                        m.a(ex);
                    }
                }
            });
            this.a.loadData(b(s), "text/html", "utf-8");
        }
    }
    
    void a(final String s, final Map<String, String> map, final Integer n, final Integer n2, final Integer n3) {
        if (this.d == g.a.b) {
            this.a.setWebViewClient((WebViewClient)new WebViewClient() {
                public void onPageFinished(final WebView webView, final String s) {
                    if (g.this.e) {
                        return;
                    }
                    try {
                        g.this.e = true;
                        g.this.b.a();
                        g.this.b.c(g.this.c);
                    }
                    catch (Exception ex) {
                        m.a(ex);
                    }
                }
            });
            this.a.loadData(a(this.c, s, n, n2, new JSONObject((Map)map), n3), "text/html", (String)null);
        }
    }
    
    enum a
    {
        a, 
        b;
    }
}
