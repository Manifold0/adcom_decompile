package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.facebook.ads.AudienceNetworkActivity;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONObject;

class ad {
    /* renamed from: a */
    protected final C2742a f6611a;
    /* renamed from: b */
    protected final ap f6612b;
    /* renamed from: c */
    protected bh f6613c;
    /* renamed from: d */
    private final String f6614d;
    /* renamed from: e */
    private final String f6615e = String.format("_moatTracker%d", new Object[]{Integer.valueOf((int) (Math.random() * 1.0E8d))});
    /* renamed from: f */
    private WeakReference f6616f;
    /* renamed from: g */
    private WeakReference f6617g;
    /* renamed from: h */
    private WebView f6618h;
    /* renamed from: i */
    private boolean f6619i = false;
    /* renamed from: j */
    private boolean f6620j;
    /* renamed from: k */
    private final LinkedList f6621k = new LinkedList();

    ad(String str, ap apVar, C2742a c2742a) {
        this.f6614d = str;
        this.f6612b = apVar;
        this.f6611a = c2742a;
        this.f6616f = new WeakReference(c2742a.mo6127c());
    }

    /* renamed from: a */
    void m6800a() {
        int i;
        int i2;
        if (this.f6621k.size() >= 200) {
            LinkedList linkedList = new LinkedList();
            for (i = 0; i < 10; i++) {
                linkedList.addFirst((String) this.f6621k.removeFirst());
            }
            i = Math.min(Math.min(this.f6621k.size() / 200, 10) + 200, this.f6621k.size());
            for (i2 = 0; i2 < i; i2++) {
                this.f6621k.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.f6621k.addFirst((String) it.next());
            }
        }
        i2 = 0;
        while (!this.f6621k.isEmpty() && i2 < 200) {
            i2++;
            String str = "javascript:%s.dispatchMany([%s])";
            StringBuilder stringBuilder = new StringBuilder();
            i = 1;
            while (!this.f6621k.isEmpty() && i2 < 200) {
                int i3 = i2 + 1;
                String str2 = (String) this.f6621k.getFirst();
                if (stringBuilder.length() + str2.length() > 2000) {
                    i2 = i3;
                    break;
                }
                this.f6621k.removeFirst();
                if (i != 0) {
                    i = 0;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str2);
                i2 = i3;
            }
            m6803a(String.format(str, new Object[]{this.f6615e, stringBuilder.toString()}));
        }
        this.f6621k.clear();
    }

    /* renamed from: a */
    public void m6801a(View view) {
        this.f6617g = new WeakReference(view);
        if (this.f6613c != null) {
            this.f6613c.mo6119a(view);
        }
    }

    /* renamed from: a */
    public void m6802a(View view, Map map, Integer num, Integer num2, Integer num3) {
        this.f6617g = new WeakReference(view);
        this.f6618h = new WebView((Context) this.f6616f.get());
        WebSettings settings = this.f6618h.getSettings();
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
        this.f6618h.setWebViewClient(new ae(this));
        JSONObject jSONObject = new JSONObject(map);
        this.f6618h.loadData(String.format("<html><head></head><body><div id=\"%s\" style=\"width: %dpx; height: %dpx;\"></div><script>(function initMoatTracking(apiname, pcode, ids, duration) {var events = [];window[pcode + '_moatElToTrack'] = document.getElementById('%s');var moatapi = {'dropTime':%d,'adData': {'ids': ids, 'duration': duration, 'url': 'n/a'},'dispatchEvent': function(ev) {if (this.sendEvent) {if (events) { events.push(ev); ev = events; events = false; }this.sendEvent(ev);} else {events.push(ev);}},'dispatchMany': function(evs){for (var i=0, l=evs.length; i<l; i++) {this.dispatchEvent(evs[i]);}}};Object.defineProperty(window, apiname, {'value': moatapi});var s = document.createElement('script');s.src = 'https://z.moatads.com/' + pcode + '/moatvideo.js?' + apiname + '#' + apiname;document.body.appendChild(s);})('%s', '%s', %s, %s);</script></body></html>", new Object[]{"mianahwvc", num, num2, "mianahwvc", Long.valueOf(System.currentTimeMillis()), this.f6615e, this.f6614d, jSONObject.toString(), num3}), AudienceNetworkActivity.WEBVIEW_MIME_TYPE, null);
    }

    /* renamed from: a */
    void m6803a(String str) {
        this.f6618h.loadUrl(str);
    }

    /* renamed from: a */
    public void m6804a(JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (!this.f6619i || this.f6618h == null) {
            this.f6621k.add(jSONObject2);
            return;
        }
        this.f6618h.loadUrl(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{this.f6615e, jSONObject2}));
    }

    /* renamed from: b */
    public void m6805b() {
        if (!this.f6620j) {
            if (this.f6613c != null) {
                this.f6613c.mo6122d();
                this.f6613c = null;
            }
            if (this.f6618h != null) {
                this.f6618h.loadUrl("about:blank");
                this.f6618h.destroy();
                this.f6618h = null;
            }
            this.f6620j = true;
        }
    }
}
