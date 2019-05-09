// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.webkit.WebSettings;
import org.json.JSONObject;
import android.webkit.WebViewClient;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.Map;
import android.view.View;
import java.util.Iterator;
import java.util.LinkedList;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

class ad
{
    protected final a a;
    protected final ap b;
    protected bh c;
    private final String d;
    private final String e;
    private WeakReference f;
    private WeakReference g;
    private WebView h;
    private boolean i;
    private boolean j;
    private final LinkedList k;
    
    ad(final String d, final ap b, final a a) {
        this.d = d;
        this.b = b;
        this.a = a;
        this.e = String.format("_moatTracker%d", (int)(Math.random() * 1.0E8));
        this.k = new LinkedList();
        this.i = false;
        this.f = new WeakReference((T)a.c());
    }
    
    void a() {
        if (this.k.size() >= 200) {
            final LinkedList<String> list = new LinkedList<String>();
            for (int i = 0; i < 10; ++i) {
                list.addFirst(this.k.removeFirst());
            }
            for (int min = Math.min(Math.min(this.k.size() / 200, 10) + 200, this.k.size()), j = 0; j < min; ++j) {
                this.k.removeFirst();
            }
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.k.addFirst(iterator.next());
            }
        }
        int n = 0;
        while (!this.k.isEmpty() && n < 200) {
            int n2 = n + 1;
            final StringBuilder sb = new StringBuilder();
            int n3 = 1;
            while (true) {
                n = n2;
                if (this.k.isEmpty() || (n = n2) >= 200) {
                    break;
                }
                ++n2;
                final String s = this.k.getFirst();
                if (sb.length() + s.length() > 2000) {
                    n = n2;
                    break;
                }
                this.k.removeFirst();
                if (n3 != 0) {
                    n3 = 0;
                }
                else {
                    sb.append(",");
                }
                sb.append(s);
            }
            this.a(String.format("javascript:%s.dispatchMany([%s])", this.e, sb.toString()));
        }
        this.k.clear();
    }
    
    public void a(final View view) {
        this.g = new WeakReference((T)view);
        if (this.c != null) {
            this.c.a(view);
        }
    }
    
    public void a(final View view, final Map map, final Integer n, final Integer n2, final Integer n3) {
        this.g = new WeakReference((T)view);
        this.h = new WebView((Context)this.f.get());
        final WebSettings settings = this.h.getSettings();
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
        this.h.setWebViewClient((WebViewClient)new ae(this));
        this.h.loadData(String.format("<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", "mianahwvc", n, n2, "mianahwvc", System.currentTimeMillis(), this.e, this.d, new JSONObject(map).toString(), n3), "text/html", (String)null);
    }
    
    void a(final String s) {
        this.h.loadUrl(s);
    }
    
    public void a(final JSONObject jsonObject) {
        final String string = jsonObject.toString();
        if (this.i && this.h != null) {
            this.h.loadUrl(String.format("javascript:%s.dispatchEvent(%s);", this.e, string));
            return;
        }
        this.k.add(string);
    }
    
    public void b() {
        if (!this.j) {
            if (this.c != null) {
                this.c.d();
                this.c = null;
            }
            if (this.h != null) {
                this.h.loadUrl("about:blank");
                this.h.destroy();
                this.h = null;
            }
            this.j = true;
        }
    }
}
