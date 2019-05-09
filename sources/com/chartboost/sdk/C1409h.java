package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.webkit.WebView;
import com.chartboost.sdk.Chartboost.CBPIDataUseConsent;
import com.chartboost.sdk.Libraries.C1375d;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.C1382i;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1434c;
import com.chartboost.sdk.impl.C1440e;
import com.chartboost.sdk.impl.C1440e.C1439a;
import com.chartboost.sdk.impl.C1447l;
import com.chartboost.sdk.impl.C1448m;
import com.chartboost.sdk.impl.C1450o;
import com.chartboost.sdk.impl.C1454s;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.ai;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.aj.C1406a;
import com.chartboost.sdk.impl.ak;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.impl.ao;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.aw;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.h */
public class C1409h {
    /* renamed from: v */
    private static C1409h f2901v;
    /* renamed from: a */
    public final Executor f2902a;
    /* renamed from: b */
    final C1447l f2903b;
    /* renamed from: c */
    public final C1375d f2904c;
    /* renamed from: d */
    public final C1440e f2905d;
    /* renamed from: e */
    public final C1434c f2906e;
    /* renamed from: f */
    final C1440e f2907f;
    /* renamed from: g */
    final C1434c f2908g;
    /* renamed from: h */
    public final ah f2909h;
    /* renamed from: i */
    final C1448m f2910i;
    /* renamed from: j */
    public final ap f2911j;
    /* renamed from: k */
    final C1440e f2912k;
    /* renamed from: l */
    final C1434c f2913l;
    /* renamed from: m */
    public final AtomicReference<C1390e> f2914m;
    /* renamed from: n */
    final SharedPreferences f2915n;
    /* renamed from: o */
    public final C1391a f2916o;
    /* renamed from: p */
    public final Handler f2917p;
    /* renamed from: q */
    public final C1397c f2918q;
    /* renamed from: r */
    public final ak f2919r;
    /* renamed from: s */
    boolean f2920s = true;
    /* renamed from: t */
    boolean f2921t = false;
    /* renamed from: u */
    boolean f2922u = true;
    /* renamed from: w */
    private final C1454s f2923w;

    /* renamed from: com.chartboost.sdk.h$a */
    public class C1408a implements Runnable {
        /* renamed from: a */
        final int f2896a;
        /* renamed from: b */
        String f2897b = null;
        /* renamed from: c */
        boolean f2898c = false;
        /* renamed from: d */
        boolean f2899d = false;
        /* renamed from: e */
        final /* synthetic */ C1409h f2900e;

        C1408a(C1409h c1409h, int i) {
            this.f2900e = c1409h;
            this.f2896a = i;
        }

        public void run() {
            try {
                switch (this.f2896a) {
                    case 0:
                        this.f2900e.m3335d();
                        return;
                    case 1:
                        C1410i.f2943t = this.f2898c;
                        return;
                    case 2:
                        C1410i.f2945v = this.f2899d;
                        if (this.f2899d && C1409h.m3329f()) {
                            this.f2900e.f2910i.m3604a();
                            return;
                        } else {
                            this.f2900e.f2910i.m3607b();
                            return;
                        }
                    case 3:
                        ad ajVar = new aj("api/install", this.f2900e.f2911j, this.f2900e.f2916o, 2, null);
                        ajVar.f3010l = true;
                        this.f2900e.f2909h.m3371a(ajVar);
                        Executor executor = this.f2900e.f2902a;
                        C1440e c1440e = this.f2900e.f2905d;
                        c1440e.getClass();
                        executor.execute(new C1439a(c1440e, 0, null, null, null));
                        executor = this.f2900e.f2902a;
                        c1440e = this.f2900e.f2907f;
                        c1440e.getClass();
                        executor.execute(new C1439a(c1440e, 0, null, null, null));
                        executor = this.f2900e.f2902a;
                        c1440e = this.f2900e.f2912k;
                        c1440e.getClass();
                        executor.execute(new C1439a(c1440e, 0, null, null, null));
                        this.f2900e.f2902a.execute(new C1408a(this.f2900e, 4));
                        this.f2900e.f2922u = false;
                        return;
                    case 4:
                        this.f2900e.f2910i.m3604a();
                        return;
                    case 5:
                        if (C1410i.f2926c != null) {
                            C1410i.f2926c.didFailToLoadMoreApps(this.f2897b, CBImpressionError.END_POINT_DISABLED);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                C1391a.m3206a(C1408a.class, "run (" + this.f2896a + ")", e);
            }
            C1391a.m3206a(C1408a.class, "run (" + this.f2896a + ")", e);
        }
    }

    /* renamed from: a */
    public static C1409h m3324a() {
        return f2901v;
    }

    /* renamed from: a */
    static void m3327a(C1409h c1409h) {
        f2901v = c1409h;
    }

    C1409h(Activity activity, String str, String str2, C1454s c1454s, ScheduledExecutorService scheduledExecutorService, Handler handler, Executor executor) {
        JSONObject jSONObject;
        C1405g a = C1405g.m3317a();
        Context applicationContext = activity.getApplicationContext();
        this.f2904c = (C1375d) a.m3318a(new C1375d(applicationContext));
        ai aiVar = (ai) a.m3318a(new ai());
        C1382i c1382i = (C1382i) a.m3318a(new C1382i());
        this.f2909h = (ah) a.m3318a(new ah(scheduledExecutorService, (ao) a.m3318a(new ao()), aiVar, c1382i, handler, executor));
        SharedPreferences a2 = C1409h.m3323a(applicationContext);
        try {
            jSONObject = new JSONObject(a2.getString("config", "{}"));
        } catch (Exception e) {
            CBLogging.m3099b("Sdk", "Unable to process config");
            e.printStackTrace();
            jSONObject = new JSONObject();
        }
        AtomicReference atomicReference = new AtomicReference(null);
        if (!C1392b.m3237a(atomicReference, jSONObject, a2)) {
            atomicReference.set(new C1390e(new JSONObject()));
        }
        this.f2923w = c1454s;
        this.f2902a = scheduledExecutorService;
        this.f2914m = atomicReference;
        this.f2915n = a2;
        this.f2917p = handler;
        C1378f c1378f = new C1378f(c1454s, applicationContext, atomicReference);
        if (((C1390e) atomicReference.get()).f2817y) {
            C1409h.m3325a(applicationContext, null, a2);
        } else {
            C1410i.f2946w = "";
        }
        this.f2911j = (ap) a.m3318a(new ap(applicationContext, str, this.f2904c, aiVar, atomicReference, a2, c1382i));
        this.f2916o = (C1391a) a.m3318a(new C1391a(atomicReference));
        this.f2903b = (C1447l) a.m3318a(new C1447l(scheduledExecutorService, c1378f, this.f2909h, aiVar, atomicReference, c1382i, this.f2916o));
        C1399d c1399d = (C1399d) a.m3318a(new C1399d((aw) C1405g.m3317a().m3318a(new aw(handler)), this.f2903b, atomicReference, handler));
        this.f2919r = (ak) a.m3318a(new ak(scheduledExecutorService, this.f2909h, aiVar, handler));
        this.f2918q = (C1397c) a.m3318a(new C1397c(activity, aiVar, this, this.f2916o, handler, c1399d));
        al alVar = (al) a.m3318a(new al(c1378f));
        this.f2906e = C1434c.m3537c();
        this.f2908g = C1434c.m3535a();
        this.f2913l = C1434c.m3536b();
        this.f2905d = (C1440e) a.m3318a(new C1440e(this.f2906e, scheduledExecutorService, this.f2903b, c1378f, this.f2909h, aiVar, this.f2911j, atomicReference, a2, c1382i, this.f2916o, handler, this.f2918q, this.f2919r, c1399d, alVar));
        this.f2907f = (C1440e) a.m3318a(new C1440e(this.f2908g, scheduledExecutorService, this.f2903b, c1378f, this.f2909h, aiVar, this.f2911j, atomicReference, a2, c1382i, this.f2916o, handler, this.f2918q, this.f2919r, c1399d, alVar));
        this.f2912k = (C1440e) a.m3318a(new C1440e(this.f2913l, scheduledExecutorService, this.f2903b, c1378f, this.f2909h, aiVar, this.f2911j, atomicReference, a2, c1382i, this.f2916o, handler, this.f2918q, this.f2919r, c1399d, alVar));
        this.f2910i = (C1448m) a.m3318a(new C1448m(this.f2903b, c1378f, this.f2909h, this.f2911j, this.f2916o, atomicReference));
        C1410i.f2936m = applicationContext;
        C1410i.f2934k = str;
        C1410i.f2935l = str2;
        if (!a2.contains("cbLimitTrack") || a2.contains("cbGDPR")) {
            C1410i.f2947x = CBPIDataUseConsent.valueOf(a2.getInt("cbGDPR", C1410i.f2947x.getValue()));
        } else {
            C1410i.f2947x = a2.getBoolean("cbLimitTrack", false) ? CBPIDataUseConsent.NO_BEHAVIORAL : CBPIDataUseConsent.UNKNOWN;
        }
        aiVar.m3374a(C1410i.f2936m);
        C1450o.m3611a(activity.getApplication(), ((C1390e) atomicReference.get()).f2790J, !((C1390e) atomicReference.get()).f2791K, !((C1390e) atomicReference.get()).f2792L);
    }

    /* renamed from: a */
    private static SharedPreferences m3323a(Context context) {
        return context.getSharedPreferences("cbPrefs", 0);
    }

    /* renamed from: a */
    void m3332a(final Runnable runnable) {
        this.f2920s = true;
        ad ajVar = new aj("/api/config", this.f2911j, this.f2916o, 1, new C1406a(this) {
            /* renamed from: b */
            final /* synthetic */ C1409h f2895b;

            /* renamed from: a */
            public void mo4278a(aj ajVar, JSONObject jSONObject) {
                this.f2895b.f2920s = false;
                JSONObject a = C1377e.m3129a(jSONObject, ServerResponseWrapper.RESPONSE_FIELD);
                if (a != null && C1392b.m3237a(this.f2895b.f2914m, a, this.f2895b.f2915n)) {
                    this.f2895b.f2915n.edit().putString("config", a.toString()).apply();
                }
                if (runnable != null) {
                    runnable.run();
                }
                if (!this.f2895b.f2921t) {
                    C1370a c1370a = C1410i.f2926c;
                    if (c1370a != null) {
                        c1370a.didInitialize();
                    }
                    this.f2895b.f2921t = true;
                }
            }

            /* renamed from: a */
            public void mo4277a(aj ajVar, CBError cBError) {
                this.f2895b.f2920s = false;
                if (runnable != null) {
                    runnable.run();
                }
                if (!this.f2895b.f2921t) {
                    C1370a c1370a = C1410i.f2926c;
                    if (c1370a != null) {
                        c1370a.didInitialize();
                    }
                    this.f2895b.f2921t = true;
                }
            }
        });
        ajVar.f3010l = true;
        this.f2909h.m3371a(ajVar);
    }

    /* renamed from: a */
    void m3331a(Activity activity) {
        if (this.f2923w.m3630a(23)) {
            C1392b.m3236a((Context) activity);
        }
        if (!this.f2922u && !this.f2918q.m3265e()) {
            this.f2903b.m3602c();
        }
    }

    /* renamed from: b */
    void m3333b() {
        if (C1410i.f2936m == null) {
            CBLogging.m3099b("Sdk", "The context must be set through the Chartboost method onCreate() before calling startSession().");
        } else {
            m3330g();
        }
    }

    /* renamed from: c */
    void m3334c() {
        this.f2917p.postDelayed(new C1408a(this, 0), 500);
    }

    /* renamed from: g */
    private void m3330g() {
        this.f2916o.m3211a();
        if (!this.f2922u) {
            m3332a(new C1408a(this, 3));
        }
    }

    /* renamed from: d */
    void m3335d() {
        this.f2916o.m3222b();
    }

    /* renamed from: e */
    void m3336e() {
        if (!this.f2921t) {
            if (C1410i.f2926c != null) {
                C1410i.f2926c.didInitialize();
            }
            this.f2921t = true;
        }
    }

    /* renamed from: b */
    public static void m3328b(Runnable runnable) {
        C1454s a = C1454s.m3627a();
        if (a.m3635e()) {
            runnable.run();
        } else {
            a.f3322a.post(runnable);
        }
    }

    /* renamed from: f */
    static boolean m3329f() {
        C1409h a = C1409h.m3324a();
        if (a == null || !((C1390e) a.f2914m.get()).f2795c) {
            return true;
        }
        try {
            throw new Exception("Chartboost Integration Warning: your account has been disabled for this session. This app has no active publishing campaigns, please create a publishing campaign in the Chartboost dashboard and wait at least 30 minutes to re-enable. If you need assistance, please visit http://chartboo.st/publishing .");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static void m3325a(Context context, WebView webView, SharedPreferences sharedPreferences) {
        String str = C1410i.f2946w;
        if (webView == null) {
            try {
                if (sharedPreferences.contains("user_agent")) {
                    str = sharedPreferences.getString("user_agent", C1410i.f2946w);
                } else {
                    str = new WebView(context.getApplicationContext()).getSettings().getUserAgentString();
                }
            } catch (Exception e) {
            }
        } else {
            str = webView.getSettings().getUserAgentString();
        }
        C1410i.f2946w = str;
        sharedPreferences.edit().putString("user_agent", str).apply();
    }

    /* renamed from: a */
    static void m3326a(Context context, CBPIDataUseConsent cBPIDataUseConsent) {
        C1410i.f2947x = cBPIDataUseConsent;
        SharedPreferences a = C1409h.m3323a(context);
        if (a != null) {
            a.edit().putInt("cbGDPR", cBPIDataUseConsent.getValue()).apply();
        }
    }
}
