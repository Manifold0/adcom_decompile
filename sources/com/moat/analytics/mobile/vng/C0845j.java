package com.moat.analytics.mobile.vng;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.support.v4.content.LocalBroadcastManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.facebook.appevents.AppEventsConstants;
import com.moat.analytics.mobile.vng.C0862s.C0861a;
import com.moat.analytics.mobile.vng.C0879w.C0878d;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.model.Cookie;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.j */
class C0845j {
    /* renamed from: a */
    boolean f1409a = false;
    /* renamed from: b */
    private int f1410b = 0;
    /* renamed from: c */
    private boolean f1411c = false;
    /* renamed from: d */
    private boolean f1412d = false;
    /* renamed from: e */
    private final WeakReference<WebView> f1413e;
    /* renamed from: f */
    private final Map<C0822b, String> f1414f;
    /* renamed from: g */
    private final LinkedList<String> f1415g;
    /* renamed from: h */
    private final AtomicBoolean f1416h = new AtomicBoolean(false);
    /* renamed from: i */
    private final long f1417i;
    /* renamed from: j */
    private final C0861a f1418j;
    /* renamed from: k */
    private final List<String> f1419k;
    /* renamed from: l */
    private final C0844a f1420l;
    /* renamed from: m */
    private final BroadcastReceiver f1421m = new C08422(this);
    /* renamed from: n */
    private final BroadcastReceiver f1422n = new C08433(this);

    /* renamed from: com.moat.analytics.mobile.vng.j$1 */
    class C08411 implements ValueCallback<String> {
        /* renamed from: a */
        final /* synthetic */ C0845j f1402a;

        C08411(C0845j c0845j) {
            this.f1402a = c0845j;
        }

        /* renamed from: a */
        public void m1497a(String str) {
            if (str == null || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("false")) {
                C0858p.m1577a(3, "JavaScriptBridge", this.f1402a, "Received value is:" + (str == null ? "null" : "(String)" + str));
                if (this.f1402a.f1410b >= 50) {
                    this.f1402a.m1513f();
                }
                this.f1402a.f1410b = this.f1402a.f1410b + 1;
            } else if (str.equalsIgnoreCase("true")) {
                if (this.f1402a.f1410b != 0) {
                    C0858p.m1577a(3, "JavaScriptBridge", this.f1402a, "Javascript has found ad");
                    this.f1402a.m1509e();
                }
                this.f1402a.f1410b = 0;
            } else {
                C0858p.m1577a(3, "JavaScriptBridge", this.f1402a, "Received unusual value from Javascript:" + str);
            }
        }

        public /* synthetic */ void onReceiveValue(Object obj) {
            m1497a((String) obj);
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.j$2 */
    class C08422 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C0845j f1403a;

        C08422(C0845j c0845j) {
            this.f1403a = c0845j;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                this.f1403a.m1504c();
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
            if (System.currentTimeMillis() - this.f1403a.f1417i > TapjoyConstants.TIMER_INCREMENT) {
                this.f1403a.m1509e();
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.j$3 */
    class C08433 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ C0845j f1404a;

        C08433(C0845j c0845j) {
            this.f1404a = c0845j;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                this.f1404a.m1505d();
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    /* renamed from: com.moat.analytics.mobile.vng.j$a */
    enum C0844a {
        WEBVIEW,
        NATIVE_DISPLAY,
        NATIVE_VIDEO
    }

    C0845j(WebView webView, C0844a c0844a) {
        this.f1413e = new WeakReference(webView);
        this.f1420l = c0844a;
        this.f1418j = new C0861a();
        this.f1415g = new LinkedList();
        this.f1419k = new ArrayList();
        this.f1414f = new WeakHashMap();
        this.f1417i = System.currentTimeMillis();
        IntentFilter intentFilter = new IntentFilter("UPDATE_METADATA");
        IntentFilter intentFilter2 = new IntentFilter("UPDATE_VIEW_INFO");
        LocalBroadcastManager.getInstance(C0862s.m1590c()).registerReceiver(this.f1421m, intentFilter);
        LocalBroadcastManager.getInstance(C0862s.m1590c()).registerReceiver(this.f1422n, intentFilter2);
        try {
            C0858p.m1577a(3, "JavaScriptBridge", (Object) this, m1502b() ? "bridge installed" : "bridge not installed");
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: a */
    private boolean m1500a(WebView webView, String str) {
        if (webView == null) {
            C0858p.m1577a(6, "JavaScriptBridge", (Object) this, "WebView is null. Can't " + str);
            return false;
        } else if (webView.getSettings().getJavaScriptEnabled()) {
            return true;
        } else {
            C0858p.m1577a(6, "JavaScriptBridge", (Object) this, "JavaScript is not enabled in the given WebView. Can't " + str);
            return false;
        }
    }

    /* renamed from: b */
    private boolean m1502b() {
        if (m1517h() != null && !m1500a(m1517h(), "installBridge")) {
            return false;
        }
        this.f1409a = true;
        C0840i.m1486a().m1494a(C0862s.m1590c(), this);
        return true;
    }

    /* renamed from: c */
    private void m1504c() {
        try {
            if (C0879w.m1610a().f1478a != C0878d.OFF) {
                if (!this.f1412d) {
                    C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Ready for communication (setting environment variables).");
                    this.f1412d = true;
                }
                String format = String.format("javascript:(function(e,k){function l(){function f(a){var b=a.c,c=a.a,f=a.b;a=a.f;var d=[];if(c)b[c]&&d.push(b[c].fn[0]);else for(key in b)if(b[key])for(var g=0,e=b[key].fn.length;g<e;g++)d.push(b[key].fn[g]);g=0;for(e=d.length;g<e;g++){var h=d[g];if('function'===typeof h)try{f?h(f):h()}catch(k){}a&&delete b[c]}}function d(a,b,c){'function'===typeof a&&(b===kuea&&c[b]?c[b].fn.push(a):c[b]={ts:+new Date,fn:[a]})}kuea=+new Date;iymv={};briz=!1;ewat=+new Date;bnkr=[];bjmk={};dptk={};uqaj={};ryup={};yhgt={};csif={};this.g=function(a){this.namespace=a.namespace;this.version=a.version;this.appName=a.appName;this.deviceOS=a.deviceOS;this.isNative=a.isNative;this.versionHash=a.versionHash;this.aqzx=a.aqzx;this.appId=a.appId};this.nvsj=function(a){briz||(d(a,ewat,iymv),briz=!0)};this.bpsy=function(a,b){var c=b||kuea;c!==kuea&&bjmk[c]||d(a,c,bjmk)};this.qmrv=function(a,b){var c=b||kuea;c!==kuea&&uqaj[c]||d(a,c,uqaj)};this.lgpr=function(a,b){d(a,b||kuea,yhgt)};this.hgen=function(a,b){d(a,b||kuea,csif)};this.xrnk=function(a){delete yhgt[a||kuea]};this.vgft=function(a){return dptk[a||kuea]||!1};this.lkpu=function(a){return ryup[a||kuea]||!1};this.crts=function(a){var b={c:iymv,b:a,a:ewat};briz?f(b):bnkr.push(a)};this.mqjh=function(a){var b=a||kuea;dptk[b]=!0;var c={c:bjmk,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.egpw=function(a){var b=a||kuea;ryup[b]=!0;var c={c:uqaj,f:!0};b!==kuea&&(c.b=a,c.a=a);f(c)};this.sglu=function(a){var b={c:yhgt,b:a.event||a,f:!1};(a.adKey||kuea)!==kuea&&(b.a=a.adKey);f(b);return 0<Object.keys(yhgt).length};this.ucbx=function(a){f({a:a.adKey||kuea,c:csif,b:a.event,f:!1})}}'undefined'===typeof e.MoatMAK&&(e.MoatMAK=new l,e.MoatMAK.g(k),e.__zMoatInit__=!0)})(window,%s);", new Object[]{m1518i()});
                if (m1516g()) {
                    m1517h().loadUrl(format);
                }
            }
        } catch (Throwable e) {
            C0858p.m1578a("JavaScriptBridge", (Object) this, "Failed to initialize communication (did not set environment variables).", e);
        }
    }

    @TargetApi(19)
    /* renamed from: d */
    private void m1505d() {
        try {
            if (C0879w.m1610a().f1478a != C0878d.OFF) {
                if (this.f1413e == null || !m1516g() || (this.f1411c && m1517h().getUrl() == null)) {
                    if (this.f1413e == null) {
                        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "WebView ref became null, stopping tracking loop");
                    } else {
                        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "WebView became null" + (m1517h() == null ? "" : "based on null url") + ", stopping tracking loop");
                    }
                    m1513f();
                    return;
                }
                if (m1517h().getUrl() != null) {
                    this.f1411c = true;
                }
                for (Entry key : this.f1414f.entrySet()) {
                    C0822b c0822b = (C0822b) key.getKey();
                    if (c0822b == null || c0822b.m1452d() == null) {
                        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Tracker has no subject");
                        if (c0822b == null || !c0822b.f1346c) {
                            m1526c(c0822b);
                        }
                    }
                    if (c0822b.f1347d) {
                        m1514f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{c0822b.f1345b}));
                        String format = String.format("javascript: MoatMAK.sglu(%s)", new Object[]{c0822b.m1454f()});
                        if (VERSION.SDK_INT >= 19) {
                            m1517h().evaluateJavascript(format, new C08411(this));
                        } else {
                            m1517h().loadUrl(format);
                        }
                    }
                }
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
            m1513f();
        }
    }

    /* renamed from: d */
    private void m1506d(C0822b c0822b) {
        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Stopping view update loop");
        if (c0822b != null) {
            C0840i.m1486a().m1495a(c0822b);
        }
    }

    /* renamed from: d */
    private void m1508d(String str) {
        if (this.f1419k.size() >= 50) {
            this.f1419k.subList(0, 25).clear();
        }
        this.f1419k.add(str);
    }

    /* renamed from: e */
    private void m1509e() {
        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Stopping metadata reporting loop");
        C0840i.m1486a().m1496a(this);
        LocalBroadcastManager.getInstance(C0862s.m1590c()).unregisterReceiver(this.f1421m);
    }

    /* renamed from: e */
    private boolean m1511e(String str) {
        if (this.f1409a) {
            return true;
        }
        C0858p.m1577a(6, "JavaScriptBridge", (Object) this, "Bridge is not installed in the given WebView. Can't " + str);
        return false;
    }

    /* renamed from: f */
    private void m1513f() {
        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Cleaning up");
        m1509e();
        for (Entry key : this.f1414f.entrySet()) {
            m1506d((C0822b) key.getKey());
        }
        this.f1414f.clear();
        LocalBroadcastManager.getInstance(C0862s.m1590c()).unregisterReceiver(this.f1422n);
    }

    /* renamed from: f */
    private void m1514f(String str) {
        if (m1516g()) {
            m1517h().loadUrl(str);
        }
    }

    /* renamed from: g */
    private boolean m1516g() {
        return m1517h() != null;
    }

    /* renamed from: h */
    private WebView m1517h() {
        return (WebView) this.f1413e.get();
    }

    /* renamed from: i */
    private String m1518i() {
        try {
            Map hashMap = new HashMap();
            String str = "3f2ae9c1894282b5e0222f0d06bbf457191f816f";
            str = "VNG";
            str = "2.2.0";
            String a = this.f1418j.m1584a();
            String b = this.f1418j.m1585b();
            String num = Integer.toString(VERSION.SDK_INT);
            String b2 = C0862s.m1589b();
            Object obj = this.f1420l == C0844a.WEBVIEW ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "1";
            hashMap.put("versionHash", "3f2ae9c1894282b5e0222f0d06bbf457191f816f");
            hashMap.put("appName", a);
            hashMap.put("namespace", "VNG");
            hashMap.put("version", "2.2.0");
            hashMap.put("deviceOS", num);
            hashMap.put("isNative", obj);
            hashMap.put(Cookie.APP_ID, b);
            if (b2 != null) {
                hashMap.put("aqzx", b2);
            }
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            return "{}";
        }
    }

    /* renamed from: a */
    void m1519a() {
        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "webViewReady");
        this.f1416h.compareAndSet(false, true);
        m1509e();
        for (String f : this.f1419k) {
            m1514f(f);
        }
        this.f1419k.clear();
    }

    /* renamed from: a */
    void m1520a(C0822b c0822b) {
        if (c0822b != null) {
            C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "adding tracker" + c0822b.f1345b);
            this.f1414f.put(c0822b, "");
        }
    }

    /* renamed from: a */
    void m1521a(String str) {
        String format = String.format("javascript: MoatMAK.crts(%s)", new Object[]{str});
        if (this.f1416h.get()) {
            m1514f(format);
        } else {
            m1508d(format);
        }
    }

    /* renamed from: a */
    void m1522a(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        if (this.f1416h.get() && m1516g()) {
            m1514f(String.format("javascript:%s.dispatchEvent(%s);", new Object[]{str, jSONObject2}));
            return;
        }
        this.f1415g.add(jSONObject2);
    }

    /* renamed from: b */
    void m1523b(String str) {
        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "markUserInteractionEvent:" + str);
        String format = String.format("javascript: MoatMAK.ucbx(%s)", new Object[]{str});
        if (this.f1416h.get()) {
            m1514f(format);
        } else {
            m1508d(format);
        }
    }

    /* renamed from: b */
    boolean m1524b(C0822b c0822b) {
        try {
            if (!m1516g() || !m1500a(m1517h(), "startTracking") || !m1511e("startTracking")) {
                return false;
            }
            if (c0822b.m1452d() == null) {
                if (c0822b.f1346c) {
                    C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null at start");
                } else {
                    C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Tracker subject is null, won't start tracking");
                    return false;
                }
            }
            C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Starting tracking on tracker" + c0822b.f1345b);
            m1514f(String.format("javascript: MoatMAK.mqjh(\"%s\")", new Object[]{c0822b.f1345b}));
            C0840i.m1486a().m1493a(C0862s.m1590c(), c0822b);
            return true;
        } catch (Throwable e) {
            C0858p.m1578a("JavaScriptBridge", (Object) this, "Failed to initialize impression start.", e);
            return false;
        }
    }

    /* renamed from: c */
    void m1525c(String str) {
        int i;
        C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "flushDispatchQueue");
        this.f1416h.compareAndSet(false, true);
        if (this.f1415g.size() >= 200) {
            int i2;
            LinkedList linkedList = new LinkedList();
            for (i2 = 0; i2 < 10; i2++) {
                linkedList.addFirst((String) this.f1415g.removeFirst());
            }
            i2 = Math.min(Math.min(this.f1415g.size() / 200, 10) + 200, this.f1415g.size());
            for (i = 0; i < i2; i++) {
                this.f1415g.removeFirst();
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                this.f1415g.addFirst((String) it.next());
            }
        }
        i = 0;
        while (!this.f1415g.isEmpty() && i < 200) {
            i++;
            String str2 = "javascript:%s.dispatchMany([%s])";
            StringBuilder stringBuilder = new StringBuilder();
            boolean z = true;
            while (!this.f1415g.isEmpty() && i < 200) {
                int i3 = i + 1;
                String str3 = (String) this.f1415g.getFirst();
                if (stringBuilder.length() + str3.length() > 2000) {
                    i = i3;
                    break;
                }
                this.f1415g.removeFirst();
                if (z) {
                    z = false;
                } else {
                    stringBuilder.append(",");
                }
                stringBuilder.append(str3);
                i = i3;
            }
            m1514f(String.format(str2, new Object[]{str, stringBuilder.toString()}));
        }
        this.f1415g.clear();
    }

    /* renamed from: c */
    boolean m1526c(C0822b c0822b) {
        boolean z = true;
        if (m1516g() && m1500a(m1517h(), "stopTracking") && m1511e("stopTracking")) {
            try {
                C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "Ending tracking on tracker" + c0822b.f1345b);
                m1514f(String.format("javascript: MoatMAK.egpw(\"%s\")", new Object[]{c0822b.f1345b}));
            } catch (Throwable e) {
                C0858p.m1578a("JavaScriptBridge", (Object) this, "Failed to end impression.", e);
            }
        } else {
            z = false;
        }
        if (this.f1420l == C0844a.NATIVE_DISPLAY) {
            m1506d(c0822b);
        } else {
            m1513f();
        }
        this.f1414f.remove(c0822b);
        return z;
    }

    protected void finalize() {
        try {
            super.finalize();
            C0858p.m1577a(3, "JavaScriptBridge", (Object) this, "finalize");
            m1513f();
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }
}
