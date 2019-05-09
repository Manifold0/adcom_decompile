package com.chartboost.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Libraries.C1383j;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1454s;
import com.chartboost.sdk.impl.ai;
import com.chartboost.sdk.impl.aq;
import com.chartboost.sdk.impl.bc;
import java.util.HashSet;

/* renamed from: com.chartboost.sdk.c */
public class C1397c {
    /* renamed from: a */
    final C1409h f2838a;
    /* renamed from: b */
    final Handler f2839b;
    /* renamed from: c */
    public final C1399d f2840c;
    /* renamed from: d */
    C1383j f2841d;
    /* renamed from: e */
    CBImpressionActivity f2842e = null;
    /* renamed from: f */
    C1388c f2843f = null;
    /* renamed from: g */
    Runnable f2844g;
    /* renamed from: h */
    final ActivityLifecycleCallbacks f2845h;
    /* renamed from: i */
    private final ai f2846i;
    /* renamed from: j */
    private final C1391a f2847j;
    /* renamed from: k */
    private boolean f2848k = false;
    /* renamed from: l */
    private final HashSet<Integer> f2849l = new HashSet();
    /* renamed from: m */
    private C1383j f2850m;

    @TargetApi(14)
    /* renamed from: com.chartboost.sdk.c$a */
    private class C1394a implements ActivityLifecycleCallbacks {
        /* renamed from: a */
        final /* synthetic */ C1397c f2828a;

        private C1394a(C1397c c1397c) {
            this.f2828a = c1397c;
        }

        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            aq.m3408a("CBUIManager.ActivityCallbackListener.onActivityCreated", (Object) activity);
            CBLogging.m3097a("CBUIManager", "######## onActivityCreated callback called");
            if (!(activity instanceof CBImpressionActivity)) {
                this.f2828a.m3256b(activity);
            }
        }

        public void onActivityStarted(Activity activity) {
            aq.m3408a("CBUIManager.ActivityCallbackListener.onActivityStarted", (Object) activity);
            if (activity instanceof CBImpressionActivity) {
                CBLogging.m3097a("CBUIManager", "######## onActivityStarted callback called from CBImpressionactivity");
                this.f2828a.m3264e(activity);
                return;
            }
            CBLogging.m3097a("CBUIManager", "######## onActivityStarted callback called from developer side");
            this.f2828a.m3263d(activity);
        }

        public void onActivityResumed(Activity activity) {
            aq.m3408a("CBUIManager.ActivityCallbackListener.onActivityResumed", (Object) activity);
            if (activity instanceof CBImpressionActivity) {
                CBLogging.m3097a("CBUIManager", "######## onActivityResumed callback called from CBImpressionactivity");
                this.f2828a.m3248a(activity);
                this.f2828a.m3270h();
                return;
            }
            CBLogging.m3097a("CBUIManager", "######## onActivityResumed callback called from developer side");
            this.f2828a.m3267f(activity);
        }

        public void onActivityPaused(Activity activity) {
            aq.m3408a("CBUIManager.ActivityCallbackListener.onActivityPaused", (Object) activity);
            if (activity instanceof CBImpressionActivity) {
                CBLogging.m3097a("CBUIManager", "######## onActivityPaused callback called from CBImpressionactivity");
                this.f2828a.m3248a(activity);
                this.f2828a.m3272i();
                return;
            }
            CBLogging.m3097a("CBUIManager", "######## onActivityPaused callback called from developer side");
            this.f2828a.m3268g(activity);
        }

        public void onActivityStopped(Activity activity) {
            aq.m3408a("CBUIManager.ActivityCallbackListener.onActivityStopped", (Object) activity);
            if (activity instanceof CBImpressionActivity) {
                CBLogging.m3097a("CBUIManager", "######## onActivityStopped callback called from CBImpressionactivity");
                this.f2828a.m3273i(activity);
                return;
            }
            CBLogging.m3097a("CBUIManager", "######## onActivityStopped callback called from developer side");
            this.f2828a.m3271h(activity);
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        public void onActivityDestroyed(Activity activity) {
            aq.m3408a("CBUIManager.ActivityCallbackListener.onActivityDestroyed", (Object) activity);
            if (activity instanceof CBImpressionActivity) {
                CBLogging.m3097a("CBUIManager", "######## onActivityDestroyed callback called from CBImpressionactivity");
                this.f2828a.m3276k(activity);
                return;
            }
            CBLogging.m3097a("CBUIManager", "######## onActivityDestroyed callback called from developer side");
            this.f2828a.m3274j(activity);
        }
    }

    /* renamed from: com.chartboost.sdk.c$b */
    class C1395b implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1397c f2829a;
        /* renamed from: b */
        private final int f2830b;
        /* renamed from: c */
        private final int f2831c;
        /* renamed from: d */
        private final int f2832d;

        /* renamed from: a */
        private C1370a m3241a() {
            return C1410i.f2926c;
        }

        C1395b(C1397c c1397c) {
            int i = -1;
            this.f2829a = c1397c;
            C1370a a = m3241a();
            this.f2830b = c1397c.f2842e == null ? -1 : c1397c.f2842e.hashCode();
            this.f2831c = c1397c.f2841d == null ? -1 : c1397c.f2841d.hashCode();
            if (a != null) {
                i = a.hashCode();
            }
            this.f2832d = i;
        }

        public void run() {
            aq.m3407a("ClearMemoryRunnable.run");
            C1370a a = m3241a();
            if (this.f2829a.f2841d != null && this.f2829a.f2841d.hashCode() == this.f2831c) {
                this.f2829a.f2841d = null;
                aq.m3407a("CBUIManager.clearHostActivityRef");
            }
            if (a != null && a.hashCode() == this.f2832d) {
                C1410i.f2926c = null;
                aq.m3407a("SdkSettings.clearDelegate");
            }
        }
    }

    /* renamed from: com.chartboost.sdk.c$c */
    public class C1396c implements Runnable {
        /* renamed from: a */
        public final int f2833a;
        /* renamed from: b */
        Activity f2834b = null;
        /* renamed from: c */
        boolean f2835c = false;
        /* renamed from: d */
        public C1388c f2836d = null;
        /* renamed from: e */
        final /* synthetic */ C1397c f2837e;

        public C1396c(C1397c c1397c, int i) {
            this.f2837e = c1397c;
            this.f2833a = i;
        }

        public void run() {
            try {
                switch (this.f2833a) {
                    case 0:
                        this.f2837e.m3261c(this.f2834b);
                        return;
                    case 1:
                        this.f2837e.f2839b.removeCallbacks(this.f2837e.f2844g);
                        if (!(this.f2837e.f2841d == null || this.f2837e.f2841d.m3160a(this.f2834b) || !this.f2837e.m3269g())) {
                            this.f2837e.m3257b(this.f2837e.f2841d);
                            this.f2837e.m3252a(this.f2837e.f2841d, false);
                        }
                        this.f2837e.m3250a(this.f2834b, true);
                        this.f2837e.f2841d = this.f2837e.m3248a(this.f2834b);
                        this.f2837e.f2838a.m3333b();
                        this.f2837e.f2838a.m3331a(this.f2834b);
                        this.f2837e.m3264e(this.f2834b);
                        return;
                    case 2:
                        if (this.f2837e.m3254a(this.f2837e.m3248a(this.f2834b))) {
                            this.f2837e.m3270h();
                            return;
                        } else if (CBUtility.m3113a(CBFramework.CBFrameworkUnity)) {
                            this.f2837e.f2838a.m3333b();
                            return;
                        } else {
                            return;
                        }
                    case 3:
                        if (this.f2837e.m3254a(this.f2837e.m3248a(this.f2834b))) {
                            this.f2837e.m3272i();
                            return;
                        }
                        return;
                    case 4:
                        C1383j a = this.f2837e.m3248a(this.f2834b);
                        if (this.f2837e.m3254a(a)) {
                            this.f2837e.m3257b(a);
                            return;
                        }
                        return;
                    case 5:
                        if (this.f2837e.f2841d == null || this.f2837e.f2841d.m3160a(this.f2834b)) {
                            this.f2837e.f2844g = new C1395b(this.f2837e);
                            this.f2837e.f2844g.run();
                        }
                        this.f2837e.m3276k(this.f2834b);
                        return;
                    case 6:
                        if (this.f2837e.f2842e == null) {
                            return;
                        }
                        if (this.f2835c) {
                            this.f2837e.f2842e.forwardTouchEvents(this.f2837e.m3247a());
                            return;
                        } else {
                            this.f2837e.f2842e.forwardTouchEvents(null);
                            return;
                        }
                    case 7:
                        this.f2837e.m3278l();
                        return;
                    case 9:
                        this.f2837e.m3249a(this.f2834b, this.f2836d);
                        return;
                    case 10:
                        if (this.f2836d.m3174a()) {
                            this.f2836d.m3195u().m3303b();
                            return;
                        }
                        return;
                    case 11:
                        C1399d c = this.f2837e.m3260c();
                        if (this.f2836d.f2766l == 2 && c != null) {
                            c.m3284b(this.f2836d);
                            return;
                        }
                        return;
                    case 12:
                        this.f2836d.m3188n();
                        return;
                    case 13:
                        this.f2837e.f2840c.m3282a(this.f2836d, this.f2834b);
                        return;
                    case 14:
                        this.f2837e.f2840c.m3286d(this.f2836d);
                        return;
                    default:
                        return;
                }
            } catch (Exception e) {
                C1391a.m3206a(C1396c.class, "run (" + this.f2833a + ")", e);
            }
            C1391a.m3206a(C1396c.class, "run (" + this.f2833a + ")", e);
        }
    }

    public C1397c(Activity activity, ai aiVar, C1409h c1409h, C1391a c1391a, Handler handler, C1399d c1399d) {
        this.f2846i = aiVar;
        this.f2838a = c1409h;
        this.f2847j = c1391a;
        this.f2839b = handler;
        this.f2840c = c1399d;
        this.f2841d = m3248a(activity);
        aq.m3408a("CBUIManager.assignHostActivityRef", this.f2841d);
        this.f2844g = new C1395b(this);
        if (C1454s.m3627a().m3630a(14)) {
            this.f2845h = new C1394a();
        } else {
            this.f2845h = null;
        }
    }

    /* renamed from: a */
    C1383j m3248a(Activity activity) {
        if (this.f2850m == null || this.f2850m.f2714a != activity.hashCode()) {
            this.f2850m = new C1383j(activity);
        }
        return this.f2850m;
    }

    /* renamed from: a */
    public Activity m3247a() {
        return this.f2841d != null ? (Activity) this.f2841d.get() : null;
    }

    /* renamed from: b */
    public Activity m3255b() {
        return this.f2842e;
    }

    /* renamed from: c */
    public C1399d m3260c() {
        if (m3255b() == null) {
            return null;
        }
        return this.f2840c;
    }

    /* renamed from: d */
    C1388c m3262d() {
        C1399d c = m3260c();
        bc a = c == null ? null : c.m3280a();
        if (a == null || !a.m3483f()) {
            return null;
        }
        return a.m3482e();
    }

    /* renamed from: e */
    public boolean m3265e() {
        return m3262d() != null;
    }

    /* renamed from: a */
    void m3251a(CBImpressionActivity cBImpressionActivity) {
        aq.m3408a("CBUIManager.setImpressionActivity", (Object) cBImpressionActivity);
        if (this.f2842e == null) {
            C1410i.f2936m = cBImpressionActivity.getApplicationContext();
            this.f2842e = cBImpressionActivity;
        }
        this.f2839b.removeCallbacks(this.f2844g);
    }

    /* renamed from: f */
    void m3266f() {
        aq.m3407a("CBUIManager.clearImpressionActivity");
        this.f2842e = null;
    }

    /* renamed from: a */
    private void m3242a(int i, boolean z) {
        if (z) {
            this.f2849l.add(Integer.valueOf(i));
        } else {
            this.f2849l.remove(Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    void m3252a(C1383j c1383j, boolean z) {
        if (c1383j != null) {
            m3242a(c1383j.f2714a, z);
        }
    }

    /* renamed from: a */
    void m3250a(Activity activity, boolean z) {
        if (activity != null) {
            m3242a(activity.hashCode(), z);
        }
    }

    /* renamed from: a */
    boolean m3254a(C1383j c1383j) {
        if (c1383j == null) {
            return false;
        }
        return this.f2849l.contains(Integer.valueOf(c1383j.f2714a));
    }

    /* renamed from: g */
    boolean m3269g() {
        return m3254a(this.f2841d);
    }

    /* renamed from: b */
    private void m3243b(C1383j c1383j, boolean z) {
    }

    /* renamed from: l */
    private boolean m3245l(Activity activity) {
        return this.f2842e == activity;
    }

    /* renamed from: c */
    private boolean m3244c(C1383j c1383j) {
        if (c1383j == null) {
            return this.f2842e == null;
        } else {
            return c1383j.m3160a(this.f2842e);
        }
    }

    /* renamed from: a */
    public void m3253a(C1388c c1388c) {
        aq.m3408a("CBUIManager.queueDisplayView", (Object) c1388c);
        if (m3265e()) {
            c1388c.m3171a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
        } else if (this.f2842e != null) {
            this.f2840c.m3281a(c1388c);
        } else if (m3269g()) {
            Activity a = m3247a();
            if (a == null) {
                CBLogging.m3099b("CBUIManager", "Failed to display impression as the host activity reference has been lost!");
                c1388c.m3171a(CBImpressionError.NO_HOST_ACTIVITY);
            } else if (this.f2843f == null || this.f2843f == c1388c) {
                this.f2843f = c1388c;
                if (C1410i.f2926c != null) {
                    if (c1388c.f2768n == 1 || c1388c.f2768n == 2) {
                        C1410i.f2926c.willDisplayVideo(c1388c.f2767m);
                    } else if (c1388c.f2768n == 0) {
                        C1410i.f2926c.willDisplayInterstitial(c1388c.f2767m);
                    }
                }
                if (C1410i.f2927d != null) {
                    Runnable c1396c = new C1396c(this, 9);
                    c1396c.f2834b = a;
                    c1396c.f2836d = c1388c;
                    this.f2839b.postDelayed(c1396c, (long) 1);
                    return;
                }
                m3249a(a, c1388c);
            } else {
                c1388c.m3171a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            }
        } else {
            c1388c.m3171a(CBImpressionError.NO_HOST_ACTIVITY);
        }
    }

    /* renamed from: a */
    public void m3249a(Activity activity, C1388c c1388c) {
        boolean z = false;
        Intent intent = new Intent(activity, CBImpressionActivity.class);
        boolean z2 = (activity.getWindow().getAttributes().flags & 1024) != 0;
        boolean z3;
        if ((activity.getWindow().getAttributes().flags & 2048) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        String str = "paramFullscreen";
        if (z2 && !r3) {
            z = true;
        }
        intent.putExtra(str, z);
        intent.putExtra("isChartboost", true);
        try {
            activity.startActivity(intent);
            this.f2848k = true;
        } catch (ActivityNotFoundException e) {
            CBLogging.m3099b("CBUIManager", "Please add CBImpressionActivity in AndroidManifest.xml following README.md instructions.");
            this.f2843f = null;
            c1388c.m3171a(CBImpressionError.ACTIVITY_MISSING_IN_MANIFEST);
        }
    }

    /* renamed from: b */
    void m3257b(C1383j c1383j) {
        aq.m3408a("CBUIManager.onStop", (Object) c1383j);
        if (!(c1383j.get() instanceof CBImpressionActivity)) {
            m3252a(c1383j, false);
        }
        this.f2838a.m3334c();
    }

    /* renamed from: b */
    void m3256b(Activity activity) {
        aq.m3408a("CBUIManager.onCreateCallback", (Object) activity);
        if (C1392b.m3238b() && C1392b.m3235a(activity)) {
            Runnable c1396c = new C1396c(this, 0);
            c1396c.f2834b = activity;
            C1409h.m3328b(c1396c);
        }
    }

    /* renamed from: c */
    void m3261c(Activity activity) {
        aq.m3408a("CBUIManager.onCreateImpl", (Object) activity);
        if (!(this.f2841d == null || this.f2841d.m3160a(activity) || !m3269g())) {
            m3257b(this.f2841d);
            m3252a(this.f2841d, false);
        }
        this.f2839b.removeCallbacks(this.f2844g);
        this.f2841d = m3248a(activity);
        aq.m3408a("CBUIManager.assignHostActivityRef", this.f2841d);
    }

    /* renamed from: d */
    void m3263d(Activity activity) {
        aq.m3408a("CBUIManager.onStartCallback", (Object) activity);
        if (C1392b.m3238b() && C1392b.m3235a(activity)) {
            Runnable c1396c = new C1396c(this, 1);
            c1396c.f2834b = activity;
            C1409h.m3328b(c1396c);
        }
    }

    /* renamed from: e */
    void m3264e(Activity activity) {
        boolean z;
        aq.m3408a("CBUIManager.onStartImpl", (Object) activity);
        C1410i.f2936m = activity.getApplicationContext();
        if (activity instanceof CBImpressionActivity) {
            m3251a((CBImpressionActivity) activity);
        } else {
            this.f2841d = m3248a(activity);
            aq.m3408a("CBUIManager.assignHostActivityRef", this.f2841d);
            m3252a(this.f2841d, true);
        }
        this.f2839b.removeCallbacks(this.f2844g);
        if (C1410i.f2927d == null || !C1410i.f2927d.doesWrapperUseCustomBackgroundingBehavior()) {
            z = false;
        } else {
            z = true;
        }
        if (activity == null) {
            return;
        }
        if (z || m3245l(activity)) {
            m3243b(m3248a(activity), true);
            if (activity instanceof CBImpressionActivity) {
                this.f2848k = false;
            }
            if (m3259b(activity, this.f2843f)) {
                this.f2843f = null;
            }
            C1388c d = m3262d();
            if (d != null) {
                d.m3193s();
            }
        }
    }

    /* renamed from: f */
    void m3267f(Activity activity) {
        aq.m3408a("CBUIManager.onResumeCallback", (Object) activity);
        if (C1392b.m3238b() && C1392b.m3235a(activity)) {
            this.f2838a.m3336e();
            Runnable c1396c = new C1396c(this, 2);
            c1396c.f2834b = activity;
            C1409h.m3328b(c1396c);
        }
    }

    /* renamed from: h */
    void m3270h() {
        aq.m3409a("CBUIManager.onResumeImpl", null);
        this.f2846i.m3376b(C1410i.f2936m);
        C1388c d = m3262d();
        if (CBUtility.m3113a(CBFramework.CBFrameworkUnity)) {
            this.f2838a.m3333b();
        }
        if (d != null) {
            d.m3192r();
        }
    }

    /* renamed from: g */
    void m3268g(Activity activity) {
        aq.m3408a("CBUIManager.onPauseCallback", (Object) activity);
        if (C1392b.m3238b() && C1392b.m3235a(activity)) {
            Runnable c1396c = new C1396c(this, 3);
            c1396c.f2834b = activity;
            C1409h.m3328b(c1396c);
        }
    }

    /* renamed from: i */
    void m3272i() {
        aq.m3409a("CBUIManager.onPauseImpl", null);
        C1388c d = m3262d();
        if (d != null) {
            d.m3194t();
        }
        this.f2846i.m3379c(C1410i.f2936m);
    }

    /* renamed from: h */
    void m3271h(Activity activity) {
        aq.m3408a("CBUIManager.onStopCallback", (Object) activity);
        if (C1392b.m3238b() && C1392b.m3235a(activity)) {
            Runnable c1396c = new C1396c(this, 4);
            c1396c.f2834b = activity;
            C1409h.m3328b(c1396c);
        }
    }

    /* renamed from: i */
    void m3273i(Activity activity) {
        C1383j a = m3248a(activity);
        aq.m3408a("CBUIManager.onStopImpl", (Object) a);
        C1388c d = m3262d();
        if (d != null && d.f2770p.f2730b == 0) {
            C1399d c = m3260c();
            if (m3244c(a) && c != null) {
                c.m3285c(d);
                this.f2843f = d;
                m3243b(a, false);
            }
            if (!(a.get() instanceof CBImpressionActivity)) {
                m3252a(a, false);
            }
        }
    }

    /* renamed from: j */
    boolean m3275j() {
        aq.m3407a("CBUIManager.onBackPressedCallback");
        if (!C1392b.m3238b()) {
            return false;
        }
        if (this.f2841d == null) {
            CBLogging.m3099b("CBUIManager", "The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
            return false;
        } else if (!this.f2848k) {
            return false;
        } else {
            this.f2848k = false;
            m3277k();
            return true;
        }
    }

    /* renamed from: k */
    boolean m3277k() {
        aq.m3407a("CBUIManager.onBackPressedImpl");
        return m3246m();
    }

    /* renamed from: m */
    private boolean m3246m() {
        aq.m3407a("CBUIManager.closeImpressionImpl");
        C1388c d = m3262d();
        if (d == null || d.f2766l != 2) {
            return false;
        }
        if (d.m3191q()) {
            return true;
        }
        C1409h.m3328b(new C1396c(this, 7));
        return true;
    }

    /* renamed from: j */
    void m3274j(Activity activity) {
        aq.m3408a("CBUIManager.onDestroyCallback", (Object) activity);
        if (C1392b.m3238b() && C1392b.m3235a(activity)) {
            Runnable c1396c = new C1396c(this, 5);
            c1396c.f2834b = activity;
            C1409h.m3328b(c1396c);
        }
    }

    /* renamed from: k */
    void m3276k(Activity activity) {
        aq.m3408a("CBUIManager.onDestroyImpl", (Object) activity);
        m3243b(m3248a(activity), false);
        C1388c d = m3262d();
        if (d == null && activity == this.f2842e && this.f2843f != null) {
            d = this.f2843f;
        }
        C1399d c = m3260c();
        if (!(c == null || d == null)) {
            c.m3286d(d);
        }
        this.f2843f = null;
    }

    /* renamed from: b */
    public void m3258b(C1388c c1388c) {
        C1399d c;
        if (c1388c.f2766l == 2) {
            c = m3260c();
            if (c != null) {
                c.m3284b(c1388c);
            }
        } else if (c1388c.f2770p.f2730b == 1 && c1388c.f2766l == 1) {
            c = m3260c();
            if (c != null) {
                c.m3286d(c1388c);
            }
        }
        if (c1388c.m3196v()) {
            this.f2847j.m3229d(c1388c.f2755a.m3538a(c1388c.f2770p.f2730b), c1388c.f2767m, c1388c.m3189o());
        } else {
            this.f2847j.m3231e(c1388c.f2755a.m3538a(c1388c.f2770p.f2730b), c1388c.f2767m, c1388c.m3189o());
        }
    }

    /* renamed from: l */
    boolean m3278l() {
        C1388c d = m3262d();
        if (d == null) {
            return false;
        }
        d.f2780z = true;
        m3258b(d);
        return true;
    }

    /* renamed from: b */
    boolean m3259b(Activity activity, C1388c c1388c) {
        if (c1388c != null) {
            switch (c1388c.f2766l) {
                case 1:
                case 3:
                    m3253a(c1388c);
                    break;
                case 2:
                    if (!c1388c.m3181g()) {
                        if (C1410i.f2927d == null || !C1410i.f2927d.doesWrapperUseCustomBackgroundingBehavior() || (activity instanceof CBImpressionActivity)) {
                            C1399d c = m3260c();
                            if (c != null) {
                                CBLogging.m3099b("CBUIManager", "Error onActivityStart " + c1388c.f2766l);
                                c.m3286d(c1388c);
                                break;
                            }
                        }
                        return false;
                    }
                    break;
            }
        }
        return true;
    }
}
