package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager.BadTokenException;
import android.widget.FrameLayout;
import com.tapjoy.TJContentActivity;
import com.tapjoy.TapjoyErrorMessage;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.TapjoyLog;
import com.tapjoy.internal.hr.C2932a;
import java.util.Iterator;

public class fy extends gj {
    /* renamed from: h */
    private static final String f7809h = fy.class.getSimpleName();
    /* renamed from: i */
    private static fy f7810i;
    /* renamed from: a */
    final String f7811a;
    /* renamed from: b */
    final gu f7812b;
    /* renamed from: j */
    private final gc f7813j;
    /* renamed from: k */
    private boolean f7814k;
    /* renamed from: l */
    private boolean f7815l;
    /* renamed from: m */
    private long f7816m;
    /* renamed from: n */
    private Context f7817n;
    /* renamed from: o */
    private hr f7818o;
    /* renamed from: p */
    private Activity f7819p;
    /* renamed from: q */
    private gd f7820q;
    /* renamed from: r */
    private Handler f7821r;
    /* renamed from: s */
    private Runnable f7822s;

    /* renamed from: com.tapjoy.internal.fy$3 */
    class C29343 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ fy f7803a;

        C29343(fy fyVar) {
            this.f7803a = fyVar;
        }

        public final void run() {
            fy.m7806a(this.f7803a);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m7806a(fy fyVar) {
        if (fyVar.f7815l) {
            fyVar.f7815l = false;
            if (fyVar.f7821r != null) {
                fyVar.f7821r.removeCallbacks(fyVar.f7822s);
                fyVar.f7822s = null;
                fyVar.f7821r = null;
            }
            if (f7810i == fyVar) {
                f7810i = null;
            }
            fyVar.f7813j.m7842a(fyVar.f7812b.f7982b, SystemClock.elapsedRealtime() - fyVar.f7816m);
            if (!(fyVar.d || fyVar.f7820q == null)) {
                fyVar.f7820q.mo6138a(fyVar.f7811a, fyVar.f, null);
                fyVar.f7820q = null;
            }
            ViewGroup viewGroup = (ViewGroup) fyVar.f7818o.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(fyVar.f7818o);
            }
            fyVar.f7818o = null;
            if (fyVar.f7819p instanceof TJContentActivity) {
                fyVar.f7819p.finish();
            }
            fyVar.f7819p = null;
        }
    }

    /* renamed from: a */
    public static void m7804a() {
        final fy fyVar = f7810i;
        if (fyVar != null) {
            Object obj;
            Runnable c29311 = new Runnable() {
                public final void run() {
                    fy.m7806a(fyVar);
                }
            };
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper == null || mainLooper.getThread() != Thread.currentThread()) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj != null) {
                c29311.run();
            } else {
                C2998x.m8228a().post(c29311);
            }
        }
    }

    public fy(gc gcVar, String str, gu guVar, Context context) {
        this.f7813j = gcVar;
        this.f7811a = str;
        this.f7812b = guVar;
        this.f7817n = context;
    }

    /* renamed from: b */
    public final void mo6291b() {
        Iterator it = this.f7812b.f7981a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((hd) it.next()).f8060c.iterator();
            while (it2.hasNext()) {
                hc hcVar = (hc) it2.next();
                if (hcVar.f8055l != null) {
                    hcVar.f8055l.m8020b();
                }
                if (hcVar.f8056m != null) {
                    hcVar.f8056m.m8020b();
                }
            }
        }
    }

    /* renamed from: c */
    public final boolean mo6292c() {
        Iterator it = this.f7812b.f7981a.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Iterator it2 = ((hd) it.next()).f8060c.iterator();
            while (it2.hasNext()) {
                hc hcVar = (hc) it2.next();
                if ((hcVar.f8055l != null && !hcVar.f8055l.m8019a()) || (hcVar.f8056m != null && !hcVar.f8056m.m8019a())) {
                    z = false;
                    continue;
                    break;
                }
            }
            z = true;
            continue;
            if (!z) {
                return false;
            }
        }
        return z;
    }

    /* renamed from: a */
    public final void mo6290a(gd gdVar, ez ezVar) {
        this.f7820q = gdVar;
        this.f7819p = fu.m7788a();
        if (!(this.f7819p == null || this.f7819p.isFinishing())) {
            try {
                m7805a(this.f7819p, gdVar, ezVar);
                new Object[1][0] = this.f7811a;
                return;
            } catch (BadTokenException e) {
            }
        }
        this.f7819p = C2850c.m7313a(this.f7817n);
        if (!(this.f7819p == null || this.f7819p.isFinishing())) {
            try {
                m7805a(this.f7819p, gdVar, ezVar);
                new Object[1][0] = this.f7811a;
                return;
            } catch (BadTokenException e2) {
            }
        }
        fz.m7817b("Failed to show the content for \"{}\". No usable activity found.", this.f7811a);
        gdVar.mo6138a(this.f7811a, this.f, null);
    }

    /* renamed from: a */
    private void m7805a(final Activity activity, final gd gdVar, ez ezVar) {
        if (this.f7814k) {
            TapjoyLog.m7127e(f7809h, new TapjoyErrorMessage(ErrorType.INTEGRATION_ERROR, "Content is already displayed"));
            return;
        }
        this.f7814k = true;
        this.f7815l = true;
        f7810i = this;
        this.g = ezVar.f7707a;
        this.f7818o = new hr(activity, this.f7812b, new C2932a(this) {
            /* renamed from: c */
            final /* synthetic */ fy f7802c;

            /* renamed from: a */
            public final void mo6289a(hc hcVar) {
                if (this.f7802c.g instanceof ey) {
                    ey eyVar = (ey) this.f7802c.g;
                    if (!(eyVar == null || eyVar.f7706c == null)) {
                        eyVar.f7706c.m7661a();
                    }
                }
                this.f7802c.f7813j.m7843a(this.f7802c.f7812b.f7982b, hcVar.f8054k);
                if (!ct.m7339c(hcVar.f8051h)) {
                    this.f7802c.e.mo6163a(activity, hcVar.f8051h, ct.m7338b(hcVar.f8052i));
                    this.f7802c.d = true;
                } else if (!ct.m7339c(hcVar.f8050g)) {
                    gj.m7800a(activity, hcVar.f8050g);
                }
                gdVar.mo6137a(this.f7802c.f7811a, null);
                if (hcVar.f8053j) {
                    fy.m7806a(this.f7802c);
                }
            }

            /* renamed from: a */
            public final void mo6288a() {
                fy.m7806a(this.f7802c);
            }
        });
        Window window = activity.getWindow();
        View view = this.f7818o;
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        Callback callback = window.getCallback();
        window.setCallback(null);
        window.addContentView(view, layoutParams);
        window.setCallback(callback);
        this.f7816m = SystemClock.elapsedRealtime();
        this.f7813j.m7841a(this.f7812b.f7982b);
        ezVar.m7689a();
        et etVar = this.g;
        if (etVar != null) {
            etVar.m7664b();
        }
        gdVar.mo6140c(this.f7811a);
        if (this.f7812b.f7983c > 0.0f) {
            this.f7821r = new Handler(Looper.getMainLooper());
            this.f7822s = new C29343(this);
            this.f7821r.postDelayed(this.f7822s, (long) (this.f7812b.f7983c * 1000.0f));
        }
    }
}
