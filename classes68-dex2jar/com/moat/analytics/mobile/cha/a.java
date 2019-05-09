// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import org.json.JSONObject;
import java.util.Map;
import android.webkit.WebViewClient;
import android.view.View;
import android.util.Log;
import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.Locale;
import android.app.Application;
import android.webkit.WebView;

final class a
{
    final String \u02ca;
    WebView \u02cb;
    private boolean \u02ce;
    j \u02cf;
    private final int \u0971;
    
    a() {
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    a(final Application application, final int \u0971) {
        this.\u0971 = \u0971;
        this.\u02ce = false;
        this.\u02ca = String.format(Locale.ROOT, "_moatTracker%d", (int)(Math.random() * 1.0E8));
        this.\u02cb = new WebView((Context)application);
        final WebSettings settings = this.\u02cb.getSettings();
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
        int n = j.e.\u02ce;
        if (\u0971 == d.\u02cb) {
            n = j.e.\u02cb;
        }
        try {
            this.\u02cf = new j(this.\u02cb, n);
        }
        catch (o o) {
            o.\u02ce(o);
        }
    }
    
    static void \u02ca(final String s, final String s2) {
        if (!t.\u02cf().\u02cf && ((f)MoatAnalytics.getInstance()).\u02ce) {
            int n = 2;
            if (s.equals("[ERROR] ")) {
                n = 6;
            }
            Log.println(n, "MoatAnalytics", s + s2);
        }
    }
    
    static String \u02cf(final View view) {
        if (view != null) {
            return view.getClass().getSimpleName() + "@" + view.hashCode();
        }
        return "null";
    }
    
    static void \u02cf(final int n, final String s, final Object o, final String s2) {
        if (t.\u02cf().\u02cf) {
            if (o != null) {
                Log.println(n, "Moat" + s, String.format("id = %s, message = %s", o.hashCode(), s2));
                return;
            }
            Log.println(n, "Moat" + s, String.format("message = %s", s2));
        }
    }
    
    static void \u02cf(String value, final Object o, final String s) {
        if (t.\u02cf().\u02cb) {
            final String string = "Moat" + (String)value;
            if (o == null) {
                value = "null";
            }
            else {
                value = o.hashCode();
            }
            Log.println(2, string, String.format("id = %s, message = %s", value, s));
        }
    }
    
    static void \u0971(final String s, final Object o, final String s2, final Exception ex) {
        if (t.\u02cf().\u02cf) {
            Log.e("Moat" + s, String.format("id = %s, message = %s", o.hashCode(), s2), (Throwable)ex);
        }
    }
    
    final void \u02ca(final String s) {
        if (this.\u0971 == d.\u02cf) {
            this.\u02cb.setWebViewClient((WebViewClient)new WebViewClient() {
                public final void onPageFinished(final WebView webView, final String s) {
                    if (a.this.\u02ce) {
                        return;
                    }
                    try {
                        a.this.\u02ce = true;
                        a.this.\u02cf.\u02cf();
                    }
                    catch (Exception ex) {
                        o.\u02ce(ex);
                    }
                }
            });
            this.\u02cb.loadData("<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + s + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>", "text/html", "utf-8");
        }
    }
    
    final void \u02ca(final String s, final Map<String, String> map, final Integer n, final Integer n2, final Integer n3) {
        if (this.\u0971 == d.\u02cb) {
            this.\u02cb.setWebViewClient((WebViewClient)new WebViewClient() {
                public final void onPageFinished(final WebView webView, final String s) {
                    if (a.this.\u02ce) {
                        return;
                    }
                    try {
                        a.this.\u02ce = true;
                        a.this.\u02cf.\u02cf();
                        a.this.\u02cf.\u02ce(a.this.\u02ca);
                    }
                    catch (Exception ex) {
                        o.\u02ce(ex);
                    }
                }
            });
            this.\u02cb.loadData(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", "mianahwvc", n, n2, "mianahwvc", System.currentTimeMillis(), this.\u02ca, s, new JSONObject((Map)map).toString(), n3), "text/html", (String)null);
        }
    }
    
    enum d
    {
        public static final int \u02cb;
        public static final int \u02cf;
        
        static {
            \u02cf = 1;
            \u02cb = 2;
        }
    }
}
