// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import org.json.JSONObject;
import java.util.HashMap;
import android.annotation.TargetApi;
import java.util.Iterator;
import android.webkit.ValueCallback;
import android.os.Build$VERSION;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import java.util.WeakHashMap;
import java.util.ArrayList;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.LinkedList;
import java.util.Map;
import android.webkit.WebView;
import java.lang.ref.WeakReference;

class j
{
    boolean a;
    private int b;
    private boolean c;
    private boolean d;
    private final WeakReference<WebView> e;
    private final Map<b, String> f;
    private final LinkedList<String> g;
    private final AtomicBoolean h;
    private final long i;
    private final s.a j;
    private final List<String> k;
    private final a l;
    private final BroadcastReceiver m;
    private final BroadcastReceiver n;
    
    j(final WebView webView, final a l) {
        this.b = 0;
        this.c = false;
        this.a = false;
        this.d = false;
        this.h = new AtomicBoolean(false);
        this.m = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                while (true) {
                    try {
                        com.moat.analytics.mobile.vng.j.this.c();
                        if (System.currentTimeMillis() - com.moat.analytics.mobile.vng.j.this.i > 10000L) {
                            com.moat.analytics.mobile.vng.j.this.e();
                        }
                    }
                    catch (Exception ex) {
                        com.moat.analytics.mobile.vng.m.a(ex);
                        continue;
                    }
                    break;
                }
            }
        };
        this.n = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                try {
                    com.moat.analytics.mobile.vng.j.this.d();
                }
                catch (Exception ex) {
                    com.moat.analytics.mobile.vng.m.a(ex);
                }
            }
        };
        this.e = new WeakReference<WebView>(webView);
        this.l = l;
        this.j = new s.a();
        this.g = new LinkedList<String>();
        this.k = new ArrayList<String>();
        this.f = new WeakHashMap<b, String>();
        this.i = System.currentTimeMillis();
        final IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
        final IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
        LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.m, intentFilter);
        LocalBroadcastManager.getInstance(s.c()).registerReceiver(this.n, intentFilter2);
        try {
            String s;
            if (this.b()) {
                s = "bridge installed";
            }
            else {
                s = "bridge not installed";
            }
            p.a(3, "JavaScriptBridge", this, s);
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
    
    private boolean a(final WebView webView, final String s) {
        if (webView == null) {
            p.a(6, "JavaScriptBridge", this, "WebView is null. Can't " + s);
            return false;
        }
        if (!webView.getSettings().getJavaScriptEnabled()) {
            p.a(6, "JavaScriptBridge", this, "JavaScript is not enabled in the given WebView. Can't " + s);
            return false;
        }
        return true;
    }
    
    private boolean b() {
        if (this.h() != null && !this.a(this.h(), "installBridge")) {
            return false;
        }
        this.a = true;
        com.moat.analytics.mobile.vng.i.a().a(s.c(), this);
        return true;
    }
    
    private void c() {
        final w a = w.a();
        try {
            if (a.a == w.d.a) {
                return;
            }
            if (!this.d) {
                p.a(3, "JavaScriptBridge", this, "Ready for communication (setting environment variables).");
                this.d = true;
            }
            final String format = String.format("javascript:(function(e,k){function l(){function f(a){var b=a.c,c=a.a,f=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var h=d[g];if('function'===typeof h)try{f?h(f):h()}catch(k){}a&&delete b[c]}}function d(a,b,c){'function'===typeof a&&(b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]})}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};csif={};this.g=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId};this.nvsj=function(a){briz||(d(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea;c!==kuea&&bjmk[c]||d(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||d(a,c,uqaj)};this.lgpr=function(a,b){d(a,b||kuea,yhgt)};this.hgen=function(a,b){d(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={c:iymv,b:a,a:ewat};briz?f(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={c:bjmk,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.egpw=function(a){var b=a||kuea;ryup[b]=!0;var c={c:uqaj,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.sglu=function(a){var b={c:yhgt,b:a.event||a,f:!1};(a.adKey||kuea)!==kuea&&(b.a=a.adKey);f(b);return 0<Object.keys(yhgt).length};this.ucbx=function(a){f({a:a.adKey||kuea,c:csif,b:a.event,f:!1})}}'undefined'===typeof e.MoatMAK&&(e.MoatMAK=new l,e.MoatMAK.g(k),e.__zMoatInit__=!0)})(window,%s);", this.i());
            if (this.g()) {
                this.h().loadUrl(format);
            }
        }
        catch (Exception ex) {
            p.a("JavaScriptBridge", this, "Failed to initialize communication (did not set environment variables).", ex);
        }
    }
    
    @TargetApi(19)
    private void d() {
        while (true) {
            while (true) {
                try {
                    if (w.a().a == w.d.a) {
                        return;
                    }
                    if (this.e != null && this.g() && (!this.c || this.h().getUrl() != null)) {
                        break;
                    }
                    if (this.e == null) {
                        p.a(3, "JavaScriptBridge", this, "WebView ref became null, stopping tracking loop");
                        this.f();
                        return;
                    }
                }
                catch (Exception ex) {
                    com.moat.analytics.mobile.vng.m.a(ex);
                    this.f();
                    return;
                }
                final StringBuilder append = new StringBuilder().append("WebView became null");
                String s;
                if (this.h() == null) {
                    s = "";
                }
                else {
                    s = "based on null url";
                }
                p.a(3, "JavaScriptBridge", this, append.append(s).append(", stopping tracking loop").toString());
                continue;
            }
        }
        if (this.h().getUrl() != null) {
            this.c = true;
        }
        final Iterator<Map.Entry<b, String>> iterator = this.f.entrySet().iterator();
        while (iterator.hasNext()) {
            final b b = iterator.next().getKey();
            if (b == null || b.d() == null) {
                p.a(3, "JavaScriptBridge", this, "Tracker has no subject");
                if (b == null || !b.c) {
                    this.c(b);
                    continue;
                }
            }
            if (b.d) {
                this.f(String.format("javascript: MoatMAK.mqjh(\"%s\")", b.b));
                final String format = String.format("javascript: MoatMAK.sglu(%s)", b.f());
                if (Build$VERSION.SDK_INT >= 19) {
                    this.h().evaluateJavascript(format, (ValueCallback)new ValueCallback<String>() {
                        public void a(String string) {
                            if (string == null || string.equalsIgnoreCase("null") || string.equalsIgnoreCase("false")) {
                                final j a = com.moat.analytics.mobile.vng.j.this;
                                final StringBuilder append = new StringBuilder().append("Received value is:");
                                if (string == null) {
                                    string = "null";
                                }
                                else {
                                    string = "(String)" + string;
                                }
                                p.a(3, "JavaScriptBridge", a, append.append(string).toString());
                                if (com.moat.analytics.mobile.vng.j.this.b >= 50) {
                                    com.moat.analytics.mobile.vng.j.this.f();
                                }
                                com.moat.analytics.mobile.vng.j.this.b++;
                                return;
                            }
                            if (string.equalsIgnoreCase("true")) {
                                if (com.moat.analytics.mobile.vng.j.this.b != 0) {
                                    p.a(3, "JavaScriptBridge", com.moat.analytics.mobile.vng.j.this, "Javascript has found ad");
                                    com.moat.analytics.mobile.vng.j.this.e();
                                }
                                com.moat.analytics.mobile.vng.j.this.b = 0;
                                return;
                            }
                            p.a(3, "JavaScriptBridge", com.moat.analytics.mobile.vng.j.this, "Received unusual value from Javascript:" + string);
                        }
                    });
                }
                else {
                    this.h().loadUrl(format);
                }
            }
        }
    }
    
    private void d(final b b) {
        p.a(3, "JavaScriptBridge", this, "Stopping view update loop");
        if (b != null) {
            com.moat.analytics.mobile.vng.i.a().a(b);
        }
    }
    
    private void d(final String s) {
        if (this.k.size() >= 50) {
            this.k.subList(0, 25).clear();
        }
        this.k.add(s);
    }
    
    private void e() {
        p.a(3, "JavaScriptBridge", this, "Stopping metadata reporting loop");
        com.moat.analytics.mobile.vng.i.a().a(this);
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.m);
    }
    
    private boolean e(final String s) {
        if (!this.a) {
            p.a(6, "JavaScriptBridge", this, "Bridge is not installed in the given WebView. Can't " + s);
            return false;
        }
        return true;
    }
    
    private void f() {
        p.a(3, "JavaScriptBridge", this, "Cleaning up");
        this.e();
        final Iterator<Map.Entry<b, String>> iterator = this.f.entrySet().iterator();
        while (iterator.hasNext()) {
            this.d(iterator.next().getKey());
        }
        this.f.clear();
        LocalBroadcastManager.getInstance(s.c()).unregisterReceiver(this.n);
    }
    
    private void f(final String s) {
        if (this.g()) {
            this.h().loadUrl(s);
        }
    }
    
    private boolean g() {
        return this.h() != null;
    }
    
    private WebView h() {
        return this.e.get();
    }
    
    private String i() {
        try {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            final String a = this.j.a();
            final String b = this.j.b();
            final String string = Integer.toString(Build$VERSION.SDK_INT);
            final String b2 = s.b();
            String s;
            if (this.l == com.moat.analytics.mobile.vng.j.a.a) {
                s = "0";
            }
            else {
                s = "1";
            }
            hashMap.put("versionHash", "3f2ae9c1894282b5e0222f0d06bbf457191f816f");
            hashMap.put("appName", a);
            hashMap.put("namespace", "VNG");
            hashMap.put("version", "2.2.0");
            hashMap.put("deviceOS", string);
            hashMap.put("isNative", s);
            hashMap.put("appId", b);
            if (b2 != null) {
                hashMap.put("aqzx", b2);
            }
            return new JSONObject((Map)hashMap).toString();
        }
        catch (Exception ex) {
            return "{}";
        }
    }
    
    void a() {
        p.a(3, "JavaScriptBridge", this, "webViewReady");
        this.h.compareAndSet(false, true);
        this.e();
        final Iterator<String> iterator = this.k.iterator();
        while (iterator.hasNext()) {
            this.f(iterator.next());
        }
        this.k.clear();
    }
    
    void a(final b b) {
        if (b != null) {
            p.a(3, "JavaScriptBridge", this, "adding tracker" + b.b);
            this.f.put(b, "");
        }
    }
    
    void a(String format) {
        format = String.format("javascript: MoatMAK.crts(%s)", format);
        if (this.h.get()) {
            this.f(format);
            return;
        }
        this.d(format);
    }
    
    void a(final String s, final JSONObject jsonObject) {
        final String string = jsonObject.toString();
        if (this.h.get() && this.g()) {
            this.f(String.format("javascript:%s.dispatchEvent(%s);", s, string));
            return;
        }
        this.g.add(string);
    }
    
    void b(String format) {
        p.a(3, "JavaScriptBridge", this, "markUserInteractionEvent:" + format);
        format = String.format("javascript: MoatMAK.ucbx(%s)", format);
        if (this.h.get()) {
            this.f(format);
            return;
        }
        this.d(format);
    }
    
    boolean b(final b b) {
        boolean b2 = false;
        Label_0088: {
            try {
                if (!this.g()) {
                    return b2;
                }
                b2 = b2;
                if (!this.a(this.h(), "startTracking")) {
                    return b2;
                }
                if (!this.e("startTracking")) {
                    return false;
                }
                if (b.d() != null) {
                    break Label_0088;
                }
                if (!b.c) {
                    p.a(3, "JavaScriptBridge", this, "Tracker subject is null, won't start tracking");
                    return false;
                }
            }
            catch (Exception ex) {
                p.a("JavaScriptBridge", this, "Failed to initialize impression start.", ex);
                return false;
            }
            p.a(3, "JavaScriptBridge", this, "Tracker subject is null at start");
        }
        p.a(3, "JavaScriptBridge", this, "Starting tracking on tracker" + b.b);
        this.f(String.format("javascript: MoatMAK.mqjh(\"%s\")", b.b));
        com.moat.analytics.mobile.vng.i.a().a(s.c(), b);
        b2 = true;
        return b2;
    }
    
    void c(final String s) {
        p.a(3, "JavaScriptBridge", this, "flushDispatchQueue");
        this.h.compareAndSet(false, true);
        if (this.g.size() >= 200) {
            final LinkedList<String> list = new LinkedList<String>();
            for (int i = 0; i < 10; ++i) {
                list.addFirst(this.g.removeFirst());
            }
            for (int min = Math.min(Math.min(this.g.size() / 200, 10) + 200, this.g.size()), j = 0; j < min; ++j) {
                this.g.removeFirst();
            }
            final Iterator<Object> iterator = list.iterator();
            while (iterator.hasNext()) {
                this.g.addFirst(iterator.next());
            }
        }
        int n = 0;
        while (!this.g.isEmpty() && n < 200) {
            int n2 = n + 1;
            final StringBuilder sb = new StringBuilder();
            int n3 = 1;
            while (true) {
                n = n2;
                if (this.g.isEmpty() || (n = n2) >= 200) {
                    break;
                }
                ++n2;
                final String s2 = this.g.getFirst();
                if (sb.length() + s2.length() > 2000) {
                    n = n2;
                    break;
                }
                this.g.removeFirst();
                if (n3 != 0) {
                    n3 = 0;
                }
                else {
                    sb.append(",");
                }
                sb.append(s2);
            }
            this.f(String.format("javascript:%s.dispatchMany([%s])", s, sb.toString()));
        }
        this.g.clear();
    }
    
    boolean c(final b b) {
        boolean b2 = true;
    Label_0084:
        while (true) {
            Label_0126: {
                if (!this.g() || !this.a(this.h(), "stopTracking") || !this.e("stopTracking")) {
                    break Label_0126;
                }
                while (true) {
                    while (true) {
                        Label_0131: {
                            try {
                                p.a(3, "JavaScriptBridge", this, "Ending tracking on tracker" + b.b);
                                this.f(String.format("javascript: MoatMAK.egpw(\"%s\")", b.b));
                                if (this.l == com.moat.analytics.mobile.vng.j.a.b) {
                                    this.d(b);
                                    this.f.remove(b);
                                    return b2;
                                }
                                break Label_0131;
                            }
                            catch (Exception ex) {
                                p.a("JavaScriptBridge", this, "Failed to end impression.", ex);
                                continue Label_0084;
                            }
                            break;
                        }
                        this.f();
                        continue;
                    }
                }
            }
            b2 = false;
            continue Label_0084;
        }
    }
    
    @Override
    protected void finalize() {
        try {
            super.finalize();
            p.a(3, "JavaScriptBridge", this, "finalize");
            this.f();
        }
        catch (Exception ex) {
            com.moat.analytics.mobile.vng.m.a(ex);
        }
    }
    
    enum a
    {
        a, 
        b, 
        c;
    }
}
