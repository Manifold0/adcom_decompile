// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import android.view.WindowManager$BadTokenException;
import com.tapjoy.TJContentActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.os.SystemClock;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.view.Window$Callback;
import android.widget.FrameLayout$LayoutParams;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyErrorMessage;
import android.os.Looper;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;

public class fy extends gj
{
    private static final String h;
    private static fy i;
    final String a;
    final gu b;
    private final gc j;
    private boolean k;
    private boolean l;
    private long m;
    private Context n;
    private hr o;
    private Activity p;
    private gd q;
    private Handler r;
    private Runnable s;
    
    static {
        h = fy.class.getSimpleName();
    }
    
    public fy(final gc j, final String a, final gu b, final Context n) {
        this.j = j;
        this.a = a;
        this.b = b;
        this.n = n;
    }
    
    public static void a() {
        final fy i = fy.i;
        if (i != null) {
            final Runnable runnable = new Runnable() {
                @Override
                public final void run() {
                    fy.a(i);
                }
            };
            final Looper mainLooper = Looper.getMainLooper();
            int n;
            if (mainLooper != null && mainLooper.getThread() == Thread.currentThread()) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n == 0) {
                x.a().post((Runnable)runnable);
                return;
            }
            runnable.run();
        }
    }
    
    private void a(final Activity activity, final gd gd, final ez ez) {
        if (this.k) {
            TapjoyLog.e(fy.h, new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.INTEGRATION_ERROR, "Content is already displayed"));
        }
        else {
            this.k = true;
            this.l = true;
            fy.i = this;
            this.g = ez.a;
            this.o = new hr((Context)activity, this.b, (hr.a)new hr.a() {
                @Override
                public final void a() {
                    fy.a(fy.this);
                }
                
                @Override
                public final void a(final hc hc) {
                    if (fy.this.g instanceof ey) {
                        final ey ey = (ey)fy.this.g;
                        if (ey != null && ey.c != null) {
                            ey.c.a();
                        }
                    }
                    fy.this.j.a(fy.this.b.b, hc.k);
                    if (!ct.c(hc.h)) {
                        fy.this.e.a((Context)activity, hc.h, ct.b(hc.i));
                        fy.this.d = true;
                    }
                    else if (!ct.c(hc.g)) {
                        gj.a((Context)activity, hc.g);
                    }
                    gd.a(fy.this.a, null);
                    if (hc.j) {
                        fy.a(fy.this);
                    }
                }
            });
            final Window window = activity.getWindow();
            final hr o = this.o;
            final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(-1, -1, 17);
            final Window$Callback callback = window.getCallback();
            window.setCallback((Window$Callback)null);
            window.addContentView((View)o, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
            window.setCallback(callback);
            this.m = SystemClock.elapsedRealtime();
            this.j.a(this.b.b);
            ez.a();
            final et g = this.g;
            if (g != null) {
                g.b();
            }
            gd.c(this.a);
            if (this.b.c > 0.0f) {
                this.r = new Handler(Looper.getMainLooper());
                this.s = new Runnable() {
                    @Override
                    public final void run() {
                        fy.a(fy.this);
                    }
                };
                this.r.postDelayed(this.s, (long)(this.b.c * 1000.0f));
            }
        }
    }
    
    static /* synthetic */ void a(final fy fy) {
        if (fy.l) {
            fy.l = false;
            if (fy.r != null) {
                fy.r.removeCallbacks(fy.s);
                fy.s = null;
                fy.r = null;
            }
            if (fy.i == fy) {
                fy.i = null;
            }
            fy.j.a(fy.b.b, SystemClock.elapsedRealtime() - fy.m);
            if (!fy.d && fy.q != null) {
                fy.q.a(fy.a, fy.f, null);
                fy.q = null;
            }
            final ViewGroup viewGroup = (ViewGroup)fy.o.getParent();
            if (viewGroup != null) {
                viewGroup.removeView((View)fy.o);
            }
            fy.o = null;
            if (fy.p instanceof TJContentActivity) {
                fy.p.finish();
            }
            fy.p = null;
        }
    }
    
    @Override
    public final void a(final gd q, final ez ez) {
        this.q = q;
        this.p = fu.a();
        if (this.p != null && !this.p.isFinishing()) {
            try {
                this.a(this.p, q, ez);
                final String a = this.a;
                return;
            }
            catch (WindowManager$BadTokenException ex) {}
        }
        this.p = com.tapjoy.internal.c.a(this.n);
        if (this.p != null && !this.p.isFinishing()) {
            try {
                this.a(this.p, q, ez);
                final String a2 = this.a;
                return;
            }
            catch (WindowManager$BadTokenException ex2) {}
        }
        fz.b("Failed to show the content for \"{}\". No usable activity found.", this.a);
        q.a(this.a, this.f, null);
    }
    
    @Override
    public final void b() {
        final Iterator<hd> iterator = this.b.a.iterator();
        while (iterator.hasNext()) {
            for (final hc hc : iterator.next().c) {
                if (hc.l != null) {
                    hc.l.b();
                }
                if (hc.m != null) {
                    hc.m.b();
                }
            }
        }
    }
    
    @Override
    public final boolean c() {
        final Iterator<hd> iterator = (Iterator<hd>)this.b.a.iterator();
        int n = 1;
    Label_0109:
        while (iterator.hasNext()) {
            while (true) {
                for (final hc hc : iterator.next().c) {
                    if ((hc.l != null && !hc.l.a()) || (hc.m != null && !hc.m.a())) {
                        final int n2 = 0;
                        n = n2;
                        if (n2 == 0) {
                            n = 0;
                            break Label_0109;
                        }
                        continue Label_0109;
                    }
                }
                final int n2 = true ? 1 : 0;
                continue;
            }
        }
        return n != 0;
    }
}
