// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import com.chartboost.sdk.Tracking.a;
import android.view.ViewGroup;
import android.app.Activity;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.Libraries.CBUtility;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.c;
import android.os.Handler;
import com.chartboost.sdk.Model.e;
import java.util.concurrent.atomic.AtomicReference;
import com.chartboost.sdk.impl.l;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.aw;

public class d
{
    final aw a;
    bc b;
    private final l c;
    private final AtomicReference<e> d;
    private final Handler e;
    private int f;
    
    public d(final aw a, final l c, final AtomicReference<e> d, final Handler e) {
        this.b = null;
        this.f = -1;
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    private void e(final c d) {
        final int n = 1;
        if (this.b != null && this.b.e() != d) {
            CBLogging.b("CBViewController", "Impression already visible");
            d.a(CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
        }
        else {
            final boolean b = d.l != 2;
            d.l = 2;
            final Activity b2 = d.g.b();
            Enum<CBError.CBImpressionError> no_HOST_ACTIVITY;
            if (b2 == null) {
                no_HOST_ACTIVITY = CBError.CBImpressionError.NO_HOST_ACTIVITY;
            }
            else {
                no_HOST_ACTIVITY = null;
            }
            Enum<CBError.CBImpressionError> j = no_HOST_ACTIVITY;
            if (no_HOST_ACTIVITY == null) {
                j = d.j();
            }
            if (j != null) {
                CBLogging.b("CBViewController", "Unable to create the view while trying th display the impression");
                d.a((CBError.CBImpressionError)j);
                return;
            }
            if (this.b == null) {
                b2.addContentView((View)(this.b = g.a().a(new bc((Context)b2, d))), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            }
            CBUtility.a(b2, d.p.b, this.d.get());
            if (s.a().a(11) && this.f == -1 && (d.n == 1 || d.n == 2)) {
                this.f = b2.getWindow().getDecorView().getSystemUiVisibility();
                Chartboost.setActivityAttrs(b2);
            }
            this.b.a();
            CBLogging.e("CBViewController", "Displaying the impression");
            d.s = this.b;
            if (b) {
                if (d.p.b == 0) {
                    this.b.c().a(this.a, d.p);
                }
                int intValue = n;
                if (d.p.b == 1) {
                    intValue = 6;
                }
                final Integer a = aw.a(d.p.o);
                if (a != null) {
                    intValue = a;
                }
                d.m();
                final com.chartboost.sdk.c g = d.g;
                g.getClass();
                final com.chartboost.sdk.c.c c = g.new c(12);
                c.d = d;
                this.a.a(intValue, d, c, this);
                this.c.a();
            }
        }
    }
    
    public bc a() {
        return this.b;
    }
    
    void a(final c c) {
        if (c.l != 0) {
            this.e(c);
        }
    }
    
    void a(final c d, final Activity activity) {
        final com.chartboost.sdk.c g = d.g;
        g.getClass();
        final com.chartboost.sdk.c.c c = g.new c(14);
        c.d = d;
        this.e.post((Runnable)c);
        d.l();
        CBUtility.b(activity, d.p.b, this.d.get());
        if (this.f != -1 && (d.n == 1 || d.n == 2)) {
            activity.getWindow().getDecorView().setSystemUiVisibility(this.f);
            this.f = -1;
        }
    }
    
    void a(final com.chartboost.sdk.c c) {
        CBLogging.e("CBViewController", "Attempting to close impression activity");
        final Activity b = c.b();
        if (b != null && b instanceof CBImpressionActivity) {
            CBLogging.e("CBViewController", "Closing impression activity");
            c.f();
            b.finish();
        }
    }
    
    public void b(final c c) {
        CBLogging.e("CBViewController", "Dismissing impression");
        final Runnable runnable = new Runnable() {
            final /* synthetic */ Activity b = c.g.b();
            
            @Override
            public void run() {
                int intValue = 1;
                c.l = 4;
                if (c.p.b == 1) {
                    intValue = 6;
                }
                final Integer a = aw.a(c.p.o);
                if (a != null) {
                    intValue = a;
                }
                final com.chartboost.sdk.c g = c.g;
                g.getClass();
                final com.chartboost.sdk.c.c c = g.new c(13);
                c.d = c;
                c.b = this.b;
                com.chartboost.sdk.d.this.a.a(intValue, c, c);
            }
        };
        if (c.t) {
            c.a(runnable);
            return;
        }
        runnable.run();
    }
    
    void c(final c c) {
        CBLogging.e("CBViewController", "Removing impression silently");
        c.i();
        while (true) {
            try {
                ((ViewGroup)this.b.getParent()).removeView((View)this.b);
                this.b = null;
            }
            catch (Exception ex) {
                CBLogging.a("CBViewController", "Exception removing impression silently", ex);
                com.chartboost.sdk.Tracking.a.a(this.getClass(), "removeImpressionSilently", ex);
                continue;
            }
            break;
        }
    }
    
    public void d(final c c) {
        CBLogging.e("CBViewController", "Removing impression");
        c.l = 5;
        c.h();
        this.b = null;
        this.c.b();
        final Handler e = this.e;
        final com.chartboost.sdk.impl.c a = c.a;
        a.getClass();
        e.post((Runnable)a.new a(3, c.m, null));
        if (c.v()) {
            final Handler e2 = this.e;
            final com.chartboost.sdk.impl.c a2 = c.a;
            a2.getClass();
            e2.post((Runnable)a2.new a(2, c.m, null));
        }
        this.a(c.g);
    }
}
