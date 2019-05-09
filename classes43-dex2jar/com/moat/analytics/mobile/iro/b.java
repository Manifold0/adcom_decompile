// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

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

final class b
{
    final String \u02ca;
    f \u02cb;
    private final int \u02ce;
    WebView \u02cf;
    private boolean \u0971;
    
    b() {
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    b(final Application application, final int \u02ce) {
        this.\u02ce = \u02ce;
        this.\u0971 = false;
        this.\u02ca = String.format(Locale.ROOT, "_moatTracker%d", (int)(Math.random() * 1.0E8));
        this.\u02cf = new WebView((Context)application);
        final WebSettings settings = this.\u02cf.getSettings();
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
        int n = f.b.\u02cf;
        if (\u02ce == a.\u02ce) {
            n = f.b.\u02cb;
        }
        try {
            this.\u02cb = new f(this.\u02cf, n);
        }
        catch (o o) {
            o.\u0971(o);
        }
    }
    
    static void \u02ca(final String s, final Object o, final String s2, final Exception ex) {
        if (t.\u02cb().\u02ce) {
            Log.e("Moat" + s, String.format("id = %s, message = %s", o.hashCode(), s2), (Throwable)ex);
        }
    }
    
    static void \u02ce(final String s, final String s2) {
        if (!t.\u02cb().\u02ce && ((j)MoatAnalytics.getInstance()).\u0971) {
            int n = 2;
            if (s.equals("[ERROR] ")) {
                n = 6;
            }
            Log.println(n, "MoatAnalytics", s + s2);
        }
    }
    
    static void \u02cf(final int n, final String s, final Object o, final String s2) {
        if (t.\u02cb().\u02ce) {
            if (o != null) {
                Log.println(n, "Moat" + s, String.format("id = %s, message = %s", o.hashCode(), s2));
                return;
            }
            Log.println(n, "Moat" + s, String.format("message = %s", s2));
        }
    }
    
    static void \u02cf(String value, final Object o, final String s) {
        if (t.\u02cb().\u02cf) {
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
    
    static String \u0971(final View view) {
        if (view != null) {
            return view.getClass().getSimpleName() + "@" + view.hashCode();
        }
        return "null";
    }
    
    final void \u02cb(final String s) {
        if (this.\u02ce == a.\u02cf) {
            this.\u02cf.setWebViewClient((WebViewClient)new WebViewClient() {
                public final void onPageFinished(final WebView webView, final String s) {
                    if (b.this.\u0971) {
                        return;
                    }
                    try {
                        b.this.\u0971 = true;
                        b.this.\u02cb.\u0971();
                    }
                    catch (Exception ex) {
                        o.\u0971(ex);
                    }
                }
            });
            this.\u02cf.loadData("<!DOCTYPE html>\n<html>\n<head lang=\"en\">\n   <meta charset=\"UTF-8\">\n   <title></title>\n</head>\n<body style=\"margin:0;padding:0;\">\n    <script src=\"https://z.moatads.com/" + s + "/moatad.js\" type=\"text/javascript\"></script>\n</body>\n</html>", "text/html", "utf-8");
        }
    }
    
    final void \u02ce(final String s, final Map<String, String> map, final Integer n, final Integer n2, final Integer n3) {
        if (this.\u02ce == a.\u02ce) {
            this.\u02cf.setWebViewClient((WebViewClient)new WebViewClient() {
                public final void onPageFinished(final WebView webView, final String s) {
                    if (b.this.\u0971) {
                        return;
                    }
                    try {
                        b.this.\u0971 = true;
                        b.this.\u02cb.\u0971();
                        b.this.\u02cb.\u0971(b.this.\u02ca);
                    }
                    catch (Exception ex) {
                        o.\u0971(ex);
                    }
                }
            });
            this.\u02cf.loadData(String.format(Locale.ROOT, "<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", "mianahwvc", n, n2, "mianahwvc", System.currentTimeMillis(), this.\u02ca, s, new JSONObject((Map)map).toString(), n3), "text/html", (String)null);
        }
    }
    
    enum a
    {
        public static final int \u02ce;
        public static final int \u02cf;
        
        static {
            \u02cf = 1;
            \u02ce = 2;
        }
    }
}
