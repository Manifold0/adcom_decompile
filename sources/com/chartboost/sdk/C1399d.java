package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import com.chartboost.sdk.C1397c.C1396c;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1388c;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1434c;
import com.chartboost.sdk.impl.C1434c.C1433a;
import com.chartboost.sdk.impl.C1447l;
import com.chartboost.sdk.impl.C1454s;
import com.chartboost.sdk.impl.aw;
import com.chartboost.sdk.impl.bc;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.chartboost.sdk.d */
public class C1399d {
    /* renamed from: a */
    final aw f2854a;
    /* renamed from: b */
    bc f2855b = null;
    /* renamed from: c */
    private final C1447l f2856c;
    /* renamed from: d */
    private final AtomicReference<C1390e> f2857d;
    /* renamed from: e */
    private final Handler f2858e;
    /* renamed from: f */
    private int f2859f = -1;

    public C1399d(aw awVar, C1447l c1447l, AtomicReference<C1390e> atomicReference, Handler handler) {
        this.f2854a = awVar;
        this.f2856c = c1447l;
        this.f2857d = atomicReference;
        this.f2858e = handler;
    }

    /* renamed from: a */
    void m3281a(C1388c c1388c) {
        if (c1388c.f2766l != 0) {
            m3279e(c1388c);
        }
    }

    /* renamed from: e */
    private void m3279e(C1388c c1388c) {
        int i = 1;
        if (this.f2855b == null || this.f2855b.m3482e() == c1388c) {
            Object obj = c1388c.f2766l != 2 ? 1 : null;
            c1388c.f2766l = 2;
            Context b = c1388c.f2761g.m3255b();
            CBImpressionError cBImpressionError = b == null ? CBImpressionError.NO_HOST_ACTIVITY : null;
            if (cBImpressionError == null) {
                cBImpressionError = c1388c.m3184j();
            }
            if (cBImpressionError != null) {
                CBLogging.m3099b("CBViewController", "Unable to create the view while trying th display the impression");
                c1388c.m3171a(cBImpressionError);
                return;
            }
            if (this.f2855b == null) {
                this.f2855b = (bc) C1405g.m3317a().m3318a(new bc(b, c1388c));
                b.addContentView(this.f2855b, new LayoutParams(-1, -1));
            }
            CBUtility.m3110a(b, c1388c.f2770p.f2730b, (C1390e) this.f2857d.get());
            if (C1454s.m3627a().m3630a(11) && this.f2859f == -1 && (c1388c.f2768n == 1 || c1388c.f2768n == 2)) {
                this.f2859f = b.getWindow().getDecorView().getSystemUiVisibility();
                Chartboost.setActivityAttrs(b);
            }
            this.f2855b.m3478a();
            CBLogging.m3104e("CBViewController", "Displaying the impression");
            c1388c.f2773s = this.f2855b;
            if (obj != null) {
                if (c1388c.f2770p.f2730b == 0) {
                    this.f2855b.m3480c().m3469a(this.f2854a, c1388c.f2770p);
                }
                if (c1388c.f2770p.f2730b == 1) {
                    i = 6;
                }
                Integer a = aw.m3462a(c1388c.f2770p.f2743o);
                if (a != null) {
                    i = a.intValue();
                }
                c1388c.m3187m();
                C1397c c1397c = c1388c.f2761g;
                c1397c.getClass();
                Runnable c1396c = new C1396c(c1397c, 12);
                c1396c.f2836d = c1388c;
                this.f2854a.m3465a(i, c1388c, c1396c, this);
                this.f2856c.m3597a();
                return;
            }
            return;
        }
        CBLogging.m3099b("CBViewController", "Impression already visible");
        c1388c.m3171a(CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
    }

    /* renamed from: b */
    public void m3284b(final C1388c c1388c) {
        CBLogging.m3104e("CBViewController", "Dismissing impression");
        final Activity b = c1388c.f2761g.m3255b();
        Runnable c13981 = new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C1399d f2853c;

            public void run() {
                int i = 1;
                c1388c.f2766l = 4;
                if (c1388c.f2770p.f2730b == 1) {
                    i = 6;
                }
                Integer a = aw.m3462a(c1388c.f2770p.f2743o);
                if (a != null) {
                    i = a.intValue();
                }
                C1397c c1397c = c1388c.f2761g;
                c1397c.getClass();
                Runnable c1396c = new C1396c(c1397c, 13);
                c1396c.f2836d = c1388c;
                c1396c.f2834b = b;
                this.f2853c.f2854a.m3464a(i, c1388c, c1396c);
            }
        };
        if (c1388c.f2774t) {
            c1388c.m3172a(c13981);
        } else {
            c13981.run();
        }
    }

    /* renamed from: a */
    void m3282a(C1388c c1388c, Activity activity) {
        C1397c c1397c = c1388c.f2761g;
        c1397c.getClass();
        Runnable c1396c = new C1396c(c1397c, 14);
        c1396c.f2836d = c1388c;
        this.f2858e.post(c1396c);
        c1388c.m3186l();
        CBUtility.m3115b(activity, c1388c.f2770p.f2730b, (C1390e) this.f2857d.get());
        if (this.f2859f == -1) {
            return;
        }
        if (c1388c.f2768n == 1 || c1388c.f2768n == 2) {
            activity.getWindow().getDecorView().setSystemUiVisibility(this.f2859f);
            this.f2859f = -1;
        }
    }

    /* renamed from: c */
    void m3285c(C1388c c1388c) {
        CBLogging.m3104e("CBViewController", "Removing impression silently");
        c1388c.m3183i();
        try {
            ((ViewGroup) this.f2855b.getParent()).removeView(this.f2855b);
        } catch (Exception e) {
            CBLogging.m3098a("CBViewController", "Exception removing impression silently", e);
            C1391a.m3206a(getClass(), "removeImpressionSilently", e);
        }
        this.f2855b = null;
    }

    /* renamed from: d */
    public void m3286d(C1388c c1388c) {
        CBLogging.m3104e("CBViewController", "Removing impression");
        c1388c.f2766l = 5;
        c1388c.m3182h();
        this.f2855b = null;
        this.f2856c.m3601b();
        Handler handler = this.f2858e;
        C1434c c1434c = c1388c.f2755a;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 3, c1388c.f2767m, null));
        if (c1388c.m3196v()) {
            handler = this.f2858e;
            c1434c = c1388c.f2755a;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 2, c1388c.f2767m, null));
        }
        m3283a(c1388c.f2761g);
    }

    /* renamed from: a */
    void m3283a(C1397c c1397c) {
        CBLogging.m3104e("CBViewController", "Attempting to close impression activity");
        Activity b = c1397c.m3255b();
        if (b != null && (b instanceof CBImpressionActivity)) {
            CBLogging.m3104e("CBViewController", "Closing impression activity");
            c1397c.m3266f();
            b.finish();
        }
    }

    /* renamed from: a */
    public bc m3280a() {
        return this.f2855b;
    }
}
