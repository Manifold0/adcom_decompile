// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.aj;
import android.content.Context;
import android.webkit.WebView;
import com.chartboost.sdk.Libraries.CBLogging;
import android.app.Application;
import com.chartboost.sdk.impl.o;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.Libraries.f;
import org.json.JSONObject;
import com.chartboost.sdk.impl.ao;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.impl.ai;
import java.util.concurrent.ScheduledExecutorService;
import android.app.Activity;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.impl.ak;
import android.os.Handler;
import com.chartboost.sdk.Tracking.a;
import android.content.SharedPreferences;
import java.util.concurrent.atomic.AtomicReference;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.m;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.c;
import com.chartboost.sdk.impl.e;
import com.chartboost.sdk.Libraries.d;
import com.chartboost.sdk.impl.l;
import java.util.concurrent.Executor;

public class h
{
    private static h v;
    public final Executor a;
    final l b;
    public final d c;
    public final e d;
    public final c e;
    final e f;
    final c g;
    public final ah h;
    final m i;
    public final ap j;
    final e k;
    final c l;
    public final AtomicReference<com.chartboost.sdk.Model.e> m;
    final SharedPreferences n;
    public final com.chartboost.sdk.Tracking.a o;
    public final Handler p;
    public final com.chartboost.sdk.c q;
    public final ak r;
    boolean s;
    boolean t;
    boolean u;
    private final s w;
    
    h(Activity application, String s, final String l, s w, final ScheduledExecutorService a, final Handler p7, final Executor executor) {
        g a2;
        Context applicationContext;
        ai ai;
        i i;
        SharedPreferences a3;
        JSONObject jsonObject;
        AtomicReference<com.chartboost.sdk.Model.e> m;
        com.chartboost.sdk.d d;
        al al;
        boolean j;
        boolean b;
        boolean b2;
        Label_0261_Outer:Label_0787_Outer:Label_0791_Outer:Label_0834_Outer:Label_0851_Outer:
        while (true) {
            this.s = true;
            this.t = false;
            this.u = true;
            a2 = com.chartboost.sdk.g.a();
            applicationContext = application.getApplicationContext();
            this.c = a2.a(new d(applicationContext));
            ai = a2.a(new ai());
            i = a2.a(new i());
            this.h = a2.a(new ah(a, a2.a(new ao()), ai, i, p7, executor));
            a3 = a(applicationContext);
            while (true) {
            Label_0939:
                while (true) {
                Label_0933:
                    while (true) {
                        Label_0908: {
                            while (true) {
                            Label_0901:
                                while (true) {
                                    while (true) {
                                        try {
                                            jsonObject = new JSONObject(a3.getString("config", "{}"));
                                            m = new AtomicReference<com.chartboost.sdk.Model.e>(null);
                                            if (!com.chartboost.sdk.b.a(m, jsonObject, a3)) {
                                                m.set(new com.chartboost.sdk.Model.e(new JSONObject()));
                                            }
                                            this.w = w;
                                            this.a = a;
                                            this.m = m;
                                            this.n = a3;
                                            this.p = p7;
                                            w = (s)new f(w, applicationContext, m);
                                            if (!m.get().y) {
                                                com.chartboost.sdk.i.w = "";
                                                this.j = a2.a(new ap(applicationContext, s, this.c, ai, m, a3, i));
                                                this.o = a2.a(new com.chartboost.sdk.Tracking.a(m));
                                                this.b = a2.a(new l(a, (f)w, this.h, ai, m, i, this.o));
                                                d = a2.a(new com.chartboost.sdk.d(com.chartboost.sdk.g.a().a(new aw(p7)), this.b, m, p7));
                                                this.r = a2.a(new ak(a, this.h, ai, p7));
                                                this.q = a2.a(new com.chartboost.sdk.c(application, ai, this, this.o, p7, d));
                                                al = a2.a(new al((f)w));
                                                this.e = com.chartboost.sdk.impl.c.c();
                                                this.g = com.chartboost.sdk.impl.c.a();
                                                this.l = com.chartboost.sdk.impl.c.b();
                                                this.d = a2.a(new e(this.e, a, this.b, (f)w, this.h, ai, this.j, m, a3, i, this.o, p7, this.q, this.r, d, al));
                                                this.f = a2.a(new e(this.g, a, this.b, (f)w, this.h, ai, this.j, m, a3, i, this.o, p7, this.q, this.r, d, al));
                                                this.k = a2.a(new e(this.l, a, this.b, (f)w, this.h, ai, this.j, m, a3, i, this.o, p7, this.q, this.r, d, al));
                                                this.i = a2.a(new m(this.b, (f)w, this.h, this.j, this.o, m));
                                                com.chartboost.sdk.i.m = applicationContext;
                                                com.chartboost.sdk.i.k = s;
                                                com.chartboost.sdk.i.l = l;
                                                if (!a3.contains("cbLimitTrack") || a3.contains("cbGDPR")) {
                                                    break Label_0908;
                                                }
                                                if (!a3.getBoolean("cbLimitTrack", false)) {
                                                    break Label_0901;
                                                }
                                                s = (String)Chartboost.CBPIDataUseConsent.NO_BEHAVIORAL;
                                                com.chartboost.sdk.i.x = (Chartboost.CBPIDataUseConsent)s;
                                                ai.a(com.chartboost.sdk.i.m);
                                                application = (Activity)application.getApplication();
                                                j = m.get().J;
                                                if (m.get().K) {
                                                    break Label_0933;
                                                }
                                                b = true;
                                                if (!m.get().L) {
                                                    b2 = true;
                                                    com.chartboost.sdk.impl.o.a((Application)application, j, b, b2);
                                                    return;
                                                }
                                                break Label_0939;
                                            }
                                        }
                                        catch (Exception ex) {
                                            CBLogging.b("Sdk", "Unable to process config");
                                            ex.printStackTrace();
                                            jsonObject = new JSONObject();
                                            continue Label_0261_Outer;
                                        }
                                        break;
                                    }
                                    a(applicationContext, null, a3);
                                    continue Label_0787_Outer;
                                }
                                s = (String)Chartboost.CBPIDataUseConsent.UNKNOWN;
                                continue Label_0791_Outer;
                            }
                        }
                        com.chartboost.sdk.i.x = Chartboost.CBPIDataUseConsent.valueOf(a3.getInt("cbGDPR", com.chartboost.sdk.i.x.getValue()));
                        continue Label_0834_Outer;
                    }
                    b = false;
                    continue Label_0851_Outer;
                }
                b2 = false;
                continue;
            }
        }
    }
    
    private static SharedPreferences a(final Context context) {
        return context.getSharedPreferences("cbPrefs", 0);
    }
    
    public static h a() {
        return h.v;
    }
    
    public static void a(final Context context, final WebView webView, final SharedPreferences sharedPreferences) {
        final String w = com.chartboost.sdk.i.w;
        Label_0079: {
            if (webView != null) {
                break Label_0079;
            }
            while (true) {
                while (true) {
                    try {
                        String w2;
                        if (!sharedPreferences.contains("user_agent")) {
                            w2 = new WebView(context.getApplicationContext()).getSettings().getUserAgentString();
                        }
                        else {
                            w2 = sharedPreferences.getString("user_agent", com.chartboost.sdk.i.w);
                        }
                        com.chartboost.sdk.i.w = w2;
                        sharedPreferences.edit().putString("user_agent", w2).apply();
                        return;
                        w2 = webView.getSettings().getUserAgentString();
                        continue;
                    }
                    catch (Exception ex) {
                        final String w2 = w;
                        continue;
                    }
                    continue;
                }
            }
        }
    }
    
    static void a(final Context context, final Chartboost.CBPIDataUseConsent x) {
        com.chartboost.sdk.i.x = x;
        final SharedPreferences a = a(context);
        if (a != null) {
            a.edit().putInt("cbGDPR", x.getValue()).apply();
        }
    }
    
    static void a(final h v) {
        h.v = v;
    }
    
    public static void b(final Runnable runnable) {
        final s a = s.a();
        if (!a.e()) {
            a.a.post(runnable);
            return;
        }
        runnable.run();
    }
    
    static boolean f() {
        final h a = a();
        if (a != null && a.m.get().c) {
            try {
                throw new Exception("Chartboost Integration Warning: your account has been disabled for this session. This app has no active publishing campaigns, please create a publishing campaign in the Chartboost dashboard and wait at least 30 minutes to re-enable. If you need assistance, please visit http://chartboo.st/publishing .");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    private void g() {
        this.o.a();
        if (!this.u) {
            this.a(new a(3));
        }
    }
    
    void a(final Activity activity) {
        if (this.w.a(23)) {
            com.chartboost.sdk.b.a((Context)activity);
        }
        if (!this.u && !this.q.e()) {
            this.b.c();
        }
    }
    
    void a(final Runnable runnable) {
        this.s = true;
        final aj aj = new aj("/api/config", this.j, this.o, 1, (aj.a)new aj.a() {
            @Override
            public void a(final aj aj, final CBError cbError) {
                h.this.s = false;
                if (runnable != null) {
                    runnable.run();
                }
                if (!h.this.t) {
                    final a c = i.c;
                    if (c != null) {
                        c.didInitialize();
                    }
                    h.this.t = true;
                }
            }
            
            @Override
            public void a(final aj aj, final JSONObject jsonObject) {
                h.this.s = false;
                final JSONObject a = com.chartboost.sdk.Libraries.e.a(jsonObject, "response");
                if (a != null && com.chartboost.sdk.b.a(h.this.m, a, h.this.n)) {
                    h.this.n.edit().putString("config", a.toString()).apply();
                }
                if (runnable != null) {
                    runnable.run();
                }
                if (!h.this.t) {
                    final a c = i.c;
                    if (c != null) {
                        c.didInitialize();
                    }
                    h.this.t = true;
                }
            }
        });
        aj.l = true;
        this.h.a((ad<Object>)aj);
    }
    
    void b() {
        if (com.chartboost.sdk.i.m == null) {
            CBLogging.b("Sdk", "The context must be set through the Chartboost method onCreate() before calling startSession().");
            return;
        }
        this.g();
    }
    
    void c() {
        this.p.postDelayed((Runnable)new a(0), 500L);
    }
    
    void d() {
        this.o.b();
    }
    
    void e() {
        if (!this.t) {
            if (com.chartboost.sdk.i.c != null) {
                com.chartboost.sdk.i.c.didInitialize();
            }
            this.t = true;
        }
    }
    
    public class a implements Runnable
    {
        final int a;
        String b;
        boolean c;
        boolean d;
        
        a(final int a) {
            this.b = null;
            this.c = false;
            this.d = false;
            this.a = a;
        }
        
        @Override
        public void run() {
            Label_0340: {
                Label_0329: {
                    Label_0137: {
                        Label_0095: {
                            try {
                                switch (this.a) {
                                    case 0: {
                                        com.chartboost.sdk.h.this.d();
                                        return;
                                    }
                                    case 1: {
                                        break;
                                    }
                                    case 2: {
                                        break Label_0095;
                                    }
                                    case 3: {
                                        break Label_0137;
                                    }
                                    case 4: {
                                        break Label_0329;
                                    }
                                    case 5: {
                                        break Label_0340;
                                    }
                                    default: {
                                        return;
                                    }
                                }
                            }
                            catch (Exception ex) {
                                com.chartboost.sdk.Tracking.a.a(a.class, "run (" + this.a + ")", ex);
                                return;
                            }
                            com.chartboost.sdk.i.t = this.c;
                            return;
                        }
                        com.chartboost.sdk.i.v = this.d;
                        if (this.d && com.chartboost.sdk.h.f()) {
                            com.chartboost.sdk.h.this.i.a();
                            return;
                        }
                        com.chartboost.sdk.h.this.i.b();
                        return;
                    }
                    final aj aj = new aj("api/install", com.chartboost.sdk.h.this.j, com.chartboost.sdk.h.this.o, 2, null);
                    aj.l = true;
                    com.chartboost.sdk.h.this.h.a((ad<Object>)aj);
                    final Executor a = com.chartboost.sdk.h.this.a;
                    final e d = com.chartboost.sdk.h.this.d;
                    d.getClass();
                    a.execute(d.new a(0, null, null, null));
                    final Executor a2 = com.chartboost.sdk.h.this.a;
                    final e f = com.chartboost.sdk.h.this.f;
                    f.getClass();
                    a2.execute(f.new a(0, null, null, null));
                    final Executor a3 = com.chartboost.sdk.h.this.a;
                    final e k = com.chartboost.sdk.h.this.k;
                    k.getClass();
                    a3.execute(k.new a(0, null, null, null));
                    com.chartboost.sdk.h.this.a.execute(new a(4));
                    com.chartboost.sdk.h.this.u = false;
                    return;
                }
                com.chartboost.sdk.h.this.i.a();
                return;
            }
            if (com.chartboost.sdk.i.c != null) {
                com.chartboost.sdk.i.c.didFailToLoadMoreApps(this.b, CBError.CBImpressionError.END_POINT_DISABLED);
            }
        }
    }
}
