package com.tapjoy.internal;

import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Base64;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.kongregate.p000o.p003a.C0578a;
import com.tapjoy.internal.dy.C2879a;
import java.io.File;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

public final class gc {
    /* renamed from: q */
    private static final gc f7845q;
    /* renamed from: r */
    private static gc f7846r;
    /* renamed from: w */
    private static Handler f7847w;
    /* renamed from: x */
    private static File f7848x;
    /* renamed from: a */
    public final gk f7849a = new gk(this);
    /* renamed from: b */
    public gl f7850b;
    /* renamed from: c */
    public boolean f7851c = false;
    /* renamed from: d */
    public String f7852d = null;
    /* renamed from: e */
    public Context f7853e;
    /* renamed from: f */
    public gf f7854f;
    /* renamed from: g */
    public gb f7855g;
    /* renamed from: h */
    public gp f7856h;
    /* renamed from: i */
    public ga f7857i;
    /* renamed from: j */
    public String f7858j;
    /* renamed from: k */
    public boolean f7859k;
    /* renamed from: l */
    public String f7860l;
    /* renamed from: m */
    public String f7861m;
    /* renamed from: n */
    public boolean f7862n = false;
    /* renamed from: o */
    public String f7863o;
    /* renamed from: p */
    public gd f7864p = gd.m7853a(null);
    /* renamed from: s */
    private boolean f7865s = false;
    /* renamed from: t */
    private boolean f7866t = false;
    /* renamed from: u */
    private String f7867u;
    /* renamed from: v */
    private String f7868v;

    static {
        gc gcVar = new gc();
        f7845q = gcVar;
        f7846r = gcVar;
    }

    /* renamed from: a */
    public static gc m7831a() {
        return f7846r;
    }

    private gc() {
    }

    /* renamed from: b */
    final synchronized void m7846b(Context context) {
        if (this.f7853e == null) {
            Context applicationContext = context.getApplicationContext();
            this.f7853e = applicationContext;
            fd.m7716a().m7718a(applicationContext);
            this.f7854f = gf.m7923a(applicationContext);
            File file = new File(m7836c(applicationContext), "events2");
            if (this.f7857i == null) {
                this.f7857i = new ga(file);
            }
            this.f7855g = new gb(this.f7854f, this.f7857i);
            this.f7856h = new gp(this.f7855g);
            this.f7850b = new gl(applicationContext);
            fi.m7744a(new fk(new File(m7836c(applicationContext), "usages"), this.f7855g));
            gw gwVar = gw.f7998a;
            gwVar.f7999b = applicationContext.getApplicationContext();
            gwVar.f8000c = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2E", 0);
            gwVar.f8001d = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2U", 0);
            gwVar.m8006a();
        }
    }

    /* renamed from: a */
    public final ee m7838a(boolean z) {
        if (z) {
            this.f7854f.m7926a();
        }
        return this.f7854f.m7932b();
    }

    /* renamed from: b */
    public final synchronized void m7845b() {
        if (this.f7859k) {
            ge.m7914b(this.f7853e).mo6316e(this.f7852d);
            m7840a(null);
        }
    }

    /* renamed from: a */
    public final synchronized void m7840a(String str) {
        if (this.f7859k) {
            if (str == null && this.f7863o != null) {
                str = this.f7863o;
            }
            this.f7863o = null;
            if (str != null) {
                fz.m7813a("GCM registration id of device {} updated for sender {}: {}", this.f7854f.m7932b().f7536d.f7516h, this.f7852d, str);
                new ho(r0, str).m7319a(new ck(this) {
                    /* renamed from: b */
                    final /* synthetic */ gc f7844b;

                    /* renamed from: a */
                    public final /* synthetic */ void mo6295a(cf cfVar, Object obj) {
                        C2948r b = ge.m7914b(this.f7844b.f7853e);
                        String str = str;
                        long currentTimeMillis = 0 != 0 ? System.currentTimeMillis() + 0 : 0;
                        if (str.equals(b.f7888b.mo6301b(b.f7887a))) {
                            b.f7888b.mo6304b(b.f7887a, true);
                            if (currentTimeMillis != 0) {
                                new Object[1][0] = new Timestamp(currentTimeMillis);
                            }
                            b.f7888b.mo6298a(b.f7887a, currentTimeMillis);
                            return;
                        }
                        new Object[1][0] = str;
                    }

                    /* renamed from: a */
                    public final void mo6294a(cf cfVar) {
                    }
                }, cf.f7280a);
            }
        } else if (str != null) {
            this.f7863o = str;
        }
    }

    /* renamed from: a */
    public static void m7834a(GLSurfaceView gLSurfaceView) {
        if (fz.m7814a((Object) gLSurfaceView, "setGLSurfaceView: The given GLSurfaceView was null")) {
            fu.m7789a(gLSurfaceView);
        }
    }

    /* renamed from: c */
    public final Set m7848c() {
        if (m7849c("getUserTags")) {
            return this.f7854f.m7938e();
        }
        return new HashSet();
    }

    /* renamed from: a */
    public final void m7844a(Set set) {
        if (m7849c("setUserTags")) {
            if (!(set == null || set.isEmpty())) {
                Set hashSet = new HashSet();
                for (String str : set) {
                    String str2;
                    if (str2 != null) {
                        str2 = str2.trim();
                        if (!str2.isEmpty() && str2.length() <= 200) {
                            hashSet.add(str2);
                            if (hashSet.size() >= 200) {
                                break;
                            }
                        }
                    }
                }
                set = hashSet;
            }
            gf gfVar = this.f7854f;
            synchronized (gfVar) {
                if (set != null) {
                    if (!set.isEmpty()) {
                        gfVar.f7894c.f7956z.m8220a(Base64.encodeToString(ej.f7592c.m7397b(new ej(new ArrayList(set))), 2));
                        gfVar.f7893b.f7594A.clear();
                        gfVar.f7893b.f7594A.addAll(set);
                    }
                }
                gfVar.f7894c.f7956z.m8200c();
                gfVar.f7893b.f7594A.clear();
            }
        }
    }

    /* renamed from: a */
    public final synchronized void m7839a(Context context, String str, String str2, String str3, String str4, String str5) {
        Object obj = 1;
        synchronized (this) {
            if (!this.f7859k) {
                boolean z;
                m7846b(context);
                if (this.f7853e != null) {
                    z = true;
                } else {
                    z = false;
                }
                if (fz.m7815a(z, "The given context was null")) {
                    Object obj2;
                    if (str4 != null && str4.length() == 24 && str4.matches("[0-9a-f]{24}")) {
                        obj2 = 1;
                    } else {
                        fz.m7817b("Invalid App ID: {}", str4);
                        obj2 = null;
                    }
                    if (obj2 != null) {
                        if (str5 != null && str5.length() == 20 && str5.matches("[0-9A-Za-z\\-_]{20}")) {
                            obj2 = 1;
                        } else {
                            fz.m7817b("Invalid App Key: {}", str5);
                            obj2 = null;
                        }
                        if (obj2 != null) {
                            this.f7860l = str;
                            this.f7861m = str2;
                            this.f7867u = str4;
                            this.f7868v = str5;
                            try {
                                URL url = new URL(str3);
                                ci cjVar = new cj("TapjoySDK" + " " + str2 + " (" + Build.MODEL + "; Android " + VERSION.RELEASE + "; " + Locale.getDefault() + ")", url);
                                cf.f7281b = cjVar;
                                cf.f7280a = Executors.newCachedThreadPool();
                                ga gaVar = this.f7857i;
                                gaVar.f7834b = cjVar;
                                gaVar.m7821a();
                                new Object[1][0] = str3;
                                this.f7859k = true;
                                gg ggVar = new gg(m7837d(this.f7853e));
                                if (ggVar.m7941b() == null) {
                                    obj = null;
                                }
                                if (obj == null && ggVar.m7940a()) {
                                    gb gbVar = this.f7855g;
                                    gbVar.m7824a(gbVar.m7823a(eb.APP, C0578a.f790c));
                                }
                                gf gfVar = this.f7854f;
                                if (!(ct.m7339c(str4) || str4.equals(gfVar.f7894c.f7930D.m8219a()))) {
                                    gfVar.f7894c.f7930D.m8220a(str4);
                                    gfVar.f7894c.m7973a(false);
                                }
                                m7845b();
                            } catch (Throwable e) {
                                throw new IllegalArgumentException(e);
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public final boolean m7847b(String str) {
        if ((this.f7859k || this.f7858j != null) && this.f7853e != null) {
            return true;
        }
        if (fz.f7823a) {
            fz.m7816b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    /* renamed from: c */
    public final boolean m7849c(String str) {
        if (this.f7853e != null) {
            return true;
        }
        if (fz.f7823a) {
            fz.m7816b(str + ": Should be called after initializing the SDK");
        }
        return false;
    }

    /* renamed from: d */
    public final boolean m7850d() {
        return this.f7856h != null && this.f7856h.f7968b.get();
    }

    /* renamed from: e */
    public final boolean m7851e() {
        boolean z;
        gp gpVar = this.f7856h;
        if (gpVar.f7969c != null) {
            gpVar.f7969c.cancel(false);
            gpVar.f7969c = null;
        }
        if (gpVar.f7968b.compareAndSet(false, true)) {
            fz.m7811a("New session started");
            gb gbVar = gpVar.f7967a;
            ef d = gbVar.f7838a.m7937d();
            gf gfVar = gbVar.f7838a;
            synchronized (gfVar) {
                int b = gfVar.f7894c.f7938h.m8210b() + 1;
                gfVar.f7894c.f7938h.m8208a(b);
                gfVar.f7893b.f7601h = Integer.valueOf(b);
            }
            C2879a a = gbVar.m7823a(eb.APP, "bootup");
            gbVar.f7840c = SystemClock.elapsedRealtime();
            if (d != null) {
                a.f7430s = d;
            }
            gbVar.m7824a(a);
            ev.f7702c.notifyObservers();
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            return false;
        }
        gk gkVar = this.f7849a;
        synchronized (gkVar) {
            gkVar.f7921b = null;
        }
        gw.f7998a.m8006a();
        return true;
    }

    /* renamed from: a */
    final void m7841a(Map map) {
        gb gbVar = this.f7855g;
        C2879a a = gbVar.m7823a(eb.CAMPAIGN, "impression");
        if (map != null) {
            a.f7429r = bm.m7204a((Object) map);
        }
        gbVar.m7824a(a);
    }

    /* renamed from: a */
    final void m7842a(Map map, long j) {
        gb gbVar = this.f7855g;
        C2879a a = gbVar.m7823a(eb.CAMPAIGN, ParametersKeys.VIEW);
        a.f7420i = Long.valueOf(j);
        if (map != null) {
            a.f7429r = bm.m7204a((Object) map);
        }
        gbVar.m7824a(a);
    }

    /* renamed from: a */
    final void m7843a(Map map, String str) {
        gb gbVar = this.f7855g;
        C2879a a = gbVar.m7823a(eb.CAMPAIGN, "click");
        Object linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.put("region", str);
        a.f7429r = bm.m7204a(linkedHashMap);
        gbVar.m7824a(a);
    }

    /* renamed from: a */
    public static synchronized void m7835a(Runnable runnable) {
        synchronized (gc.class) {
            if (f7847w == null) {
                f7847w = new Handler(Looper.getMainLooper());
            }
            f7847w.post(runnable);
        }
    }

    /* renamed from: c */
    public static synchronized File m7836c(Context context) {
        File file;
        synchronized (gc.class) {
            if (f7848x == null) {
                f7848x = context.getDir("fiverocks", 0);
            }
            file = f7848x;
        }
        return file;
    }

    /* renamed from: d */
    static File m7837d(Context context) {
        return new File(m7836c(context), C0578a.f790c);
    }

    /* renamed from: a */
    public static String m7833a(Context context, Intent intent) {
        String a = C2913f.m7691a(intent);
        if (a != null) {
            gc gcVar = f7846r;
            gcVar.m7846b(context);
            if (ct.m7339c(gcVar.f7854f.m7935c()) || intent.getBooleanExtra("fiverocks:force", false)) {
                gf gfVar = gcVar.f7854f;
                synchronized (gfVar) {
                    gfVar.f7894c.f7934d.m8220a(a);
                    gfVar.f7893b.f7597d = a;
                }
                if (a.length() > 0) {
                    gb gbVar = gcVar.f7855g;
                    gbVar.m7824a(gbVar.m7823a(eb.APP, "referrer"));
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    public static gc m7832a(Context context) {
        gc gcVar = f7846r;
        gcVar.m7846b(context);
        return gcVar;
    }
}
