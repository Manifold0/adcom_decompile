// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk;

import android.os.Bundle;
import android.annotation.TargetApi;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.bc;
import android.content.ActivityNotFoundException;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Libraries.CBLogging;
import android.content.Context;
import android.content.Intent;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.impl.aq;
import android.app.Activity;
import java.util.HashSet;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.impl.ai;
import android.app.Application$ActivityLifecycleCallbacks;
import com.chartboost.sdk.Libraries.j;
import android.os.Handler;

public class c
{
    final h a;
    final Handler b;
    public final d c;
    j d;
    CBImpressionActivity e;
    c f;
    Runnable g;
    final Application$ActivityLifecycleCallbacks h;
    private final ai i;
    private final com.chartboost.sdk.Tracking.a j;
    private boolean k;
    private final HashSet<Integer> l;
    private j m;
    
    public c(final Activity activity, final ai i, final h a, final com.chartboost.sdk.Tracking.a j, final Handler b, final d c) {
        this.e = null;
        this.f = null;
        this.k = false;
        this.l = new HashSet<Integer>();
        this.i = i;
        this.a = a;
        this.j = j;
        this.b = b;
        this.c = c;
        aq.a("CBUIManager.assignHostActivityRef", this.d = this.a(activity));
        this.g = new b();
        if (s.a().a(14)) {
            this.h = (Application$ActivityLifecycleCallbacks)new a();
            return;
        }
        this.h = null;
    }
    
    private void a(final int n, final boolean b) {
        if (b) {
            this.l.add(n);
            return;
        }
        this.l.remove(n);
    }
    
    private void b(final j j, final boolean b) {
    }
    
    private boolean c(final j j) {
        if (j == null) {
            return this.e == null;
        }
        return j.a(this.e);
    }
    
    private boolean l(final Activity activity) {
        return this.e == activity;
    }
    
    private boolean m() {
        aq.a("CBUIManager.closeImpressionImpl");
        final c d = this.d();
        if (d == null || d.l != 2) {
            return false;
        }
        if (d.q()) {
            return true;
        }
        com.chartboost.sdk.h.b(new c(7));
        return true;
    }
    
    public Activity a() {
        if (this.d != null) {
            return this.d.get();
        }
        return null;
    }
    
    j a(final Activity activity) {
        if (this.m == null || this.m.a != activity.hashCode()) {
            this.m = new j(activity);
        }
        return this.m;
    }
    
    public void a(final Activity activity, final c c) {
        final boolean b = false;
        final Intent intent = new Intent((Context)activity, (Class)CBImpressionActivity.class);
        Label_0105: {
            if ((activity.getWindow().getAttributes().flags & 0x400) == 0x0) {
                break Label_0105;
            }
            int n = 1;
        Label_0054_Outer:
            while (true) {
                Label_0110: {
                    if ((activity.getWindow().getAttributes().flags & 0x800) == 0x0) {
                        break Label_0110;
                    }
                    int n2 = 1;
                    while (true) {
                        boolean b2 = b;
                        if (n != 0) {
                            b2 = b;
                            if (n2 == 0) {
                                b2 = true;
                            }
                        }
                        intent.putExtra("paramFullscreen", b2);
                        intent.putExtra("isChartboost", true);
                        try {
                            activity.startActivity(intent);
                            this.k = true;
                            return;
                            n = 0;
                            continue Label_0054_Outer;
                            n2 = 0;
                            continue;
                        }
                        catch (ActivityNotFoundException ex) {
                            CBLogging.b("CBUIManager", "Please add CBImpressionActivity in AndroidManifest.xml following README.md instructions.");
                            this.f = null;
                            c.a(CBError.CBImpressionError.ACTIVITY_MISSING_IN_MANIFEST);
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
    
    void a(final Activity activity, final boolean b) {
        if (activity == null) {
            return;
        }
        this.a(activity.hashCode(), b);
    }
    
    void a(final CBImpressionActivity e) {
        aq.a("CBUIManager.setImpressionActivity", e);
        if (this.e == null) {
            com.chartboost.sdk.i.m = e.getApplicationContext();
            this.e = e;
        }
        this.b.removeCallbacks(this.g);
    }
    
    void a(final j j, final boolean b) {
        if (j == null) {
            return;
        }
        this.a(j.a, b);
    }
    
    public void a(final c c) {
        aq.a("CBUIManager.queueDisplayView", c);
        if (this.e()) {
            c.a(CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            return;
        }
        if (this.e != null) {
            this.c.a(c);
            return;
        }
        if (!this.g()) {
            c.a(CBError.CBImpressionError.NO_HOST_ACTIVITY);
            return;
        }
        final Activity a = this.a();
        if (a == null) {
            CBLogging.b("CBUIManager", "Failed to display impression as the host activity reference has been lost!");
            c.a(CBError.CBImpressionError.NO_HOST_ACTIVITY);
            return;
        }
        if (this.f != null && this.f != c) {
            c.a(CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE);
            return;
        }
        this.f = c;
        if (com.chartboost.sdk.i.c != null) {
            if (c.n == 1 || c.n == 2) {
                com.chartboost.sdk.i.c.willDisplayVideo(c.m);
            }
            else if (c.n == 0) {
                com.chartboost.sdk.i.c.willDisplayInterstitial(c.m);
            }
        }
        if (com.chartboost.sdk.i.d != null) {
            final c c2 = new c(9);
            c2.b = a;
            c2.d = c;
            this.b.postDelayed((Runnable)c2, (long)1);
            return;
        }
        this.a(a, c);
    }
    
    boolean a(final j j) {
        return j != null && this.l.contains(j.a);
    }
    
    public Activity b() {
        return this.e;
    }
    
    void b(final Activity b) {
        aq.a("CBUIManager.onCreateCallback", b);
        if (!b.b() || !b.a(b)) {
            return;
        }
        final c c = new c(0);
        c.b = b;
        com.chartboost.sdk.h.b(c);
    }
    
    void b(final j j) {
        aq.a("CBUIManager.onStop", j);
        if (!(j.get() instanceof CBImpressionActivity)) {
            this.a(j, false);
        }
        this.a.c();
    }
    
    public void b(final c c) {
        if (c.l == 2) {
            final d c2 = this.c();
            if (c2 != null) {
                c2.b(c);
            }
        }
        else if (c.p.b == 1 && c.l == 1) {
            final d c3 = this.c();
            if (c3 != null) {
                c3.d(c);
            }
        }
        if (c.v()) {
            this.j.d(c.a.a(c.p.b), c.m, c.o());
            return;
        }
        this.j.e(c.a.a(c.p.b), c.m, c.o());
    }
    
    boolean b(final Activity activity, final c c) {
        if (c != null) {
            switch (c.l) {
                case 1:
                case 3: {
                    this.a(c);
                    break;
                }
                case 2: {
                    if (c.g()) {
                        break;
                    }
                    if (com.chartboost.sdk.i.d != null && com.chartboost.sdk.i.d.doesWrapperUseCustomBackgroundingBehavior() && !(activity instanceof CBImpressionActivity)) {
                        return false;
                    }
                    final d c2 = this.c();
                    if (c2 != null) {
                        CBLogging.b("CBUIManager", "Error onActivityStart " + c.l);
                        c2.d(c);
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    public d c() {
        if (this.b() == null) {
            return null;
        }
        return this.c;
    }
    
    void c(final Activity activity) {
        aq.a("CBUIManager.onCreateImpl", activity);
        if (this.d != null && !this.d.a(activity) && this.g()) {
            this.b(this.d);
            this.a(this.d, false);
        }
        this.b.removeCallbacks(this.g);
        aq.a("CBUIManager.assignHostActivityRef", this.d = this.a(activity));
    }
    
    c d() {
        final d c = this.c();
        bc a;
        if (c == null) {
            a = null;
        }
        else {
            a = c.a();
        }
        if (a == null || !a.f()) {
            return null;
        }
        return a.e();
    }
    
    void d(final Activity b) {
        aq.a("CBUIManager.onStartCallback", b);
        if (!b.b() || !b.a(b)) {
            return;
        }
        final c c = new c(1);
        c.b = b;
        com.chartboost.sdk.h.b(c);
    }
    
    void e(final Activity activity) {
        aq.a("CBUIManager.onStartImpl", activity);
        com.chartboost.sdk.i.m = activity.getApplicationContext();
        if (!(activity instanceof CBImpressionActivity)) {
            aq.a("CBUIManager.assignHostActivityRef", this.d = this.a(activity));
            this.a(this.d, true);
        }
        else {
            this.a((CBImpressionActivity)activity);
        }
        this.b.removeCallbacks(this.g);
        boolean b;
        if (com.chartboost.sdk.i.d != null && com.chartboost.sdk.i.d.doesWrapperUseCustomBackgroundingBehavior()) {
            b = true;
        }
        else {
            b = false;
        }
        if (activity != null && (b || this.l(activity))) {
            this.b(this.a(activity), true);
            if (activity instanceof CBImpressionActivity) {
                this.k = false;
            }
            if (this.b(activity, this.f)) {
                this.f = null;
            }
            final c d = this.d();
            if (d != null) {
                d.s();
            }
        }
    }
    
    public boolean e() {
        return this.d() != null;
    }
    
    void f() {
        aq.a("CBUIManager.clearImpressionActivity");
        this.e = null;
    }
    
    void f(final Activity b) {
        aq.a("CBUIManager.onResumeCallback", b);
        if (!b.b() || !b.a(b)) {
            return;
        }
        this.a.e();
        final c c = new c(2);
        c.b = b;
        com.chartboost.sdk.h.b(c);
    }
    
    void g(final Activity b) {
        aq.a("CBUIManager.onPauseCallback", b);
        if (!b.b() || !b.a(b)) {
            return;
        }
        final c c = new c(3);
        c.b = b;
        com.chartboost.sdk.h.b(c);
    }
    
    boolean g() {
        return this.a(this.d);
    }
    
    void h() {
        aq.a("CBUIManager.onResumeImpl", null);
        this.i.b(com.chartboost.sdk.i.m);
        final c d = this.d();
        if (CBUtility.a(Chartboost.CBFramework.CBFrameworkUnity)) {
            this.a.b();
        }
        if (d != null) {
            d.r();
        }
    }
    
    void h(final Activity b) {
        aq.a("CBUIManager.onStopCallback", b);
        if (!b.b() || !b.a(b)) {
            return;
        }
        final c c = new c(4);
        c.b = b;
        com.chartboost.sdk.h.b(c);
    }
    
    void i() {
        aq.a("CBUIManager.onPauseImpl", null);
        final c d = this.d();
        if (d != null) {
            d.t();
        }
        this.i.c(com.chartboost.sdk.i.m);
    }
    
    void i(final Activity activity) {
        final j a = this.a(activity);
        aq.a("CBUIManager.onStopImpl", a);
        final c d = this.d();
        if (d != null && d.p.b == 0) {
            final d c = this.c();
            if (this.c(a) && c != null) {
                c.c(d);
                this.f = d;
                this.b(a, false);
            }
            if (!(a.get() instanceof CBImpressionActivity)) {
                this.a(a, false);
            }
        }
    }
    
    void j(final Activity b) {
        aq.a("CBUIManager.onDestroyCallback", b);
        if (!b.b() || !b.a(b)) {
            return;
        }
        final c c = new c(5);
        c.b = b;
        com.chartboost.sdk.h.b(c);
    }
    
    boolean j() {
        aq.a("CBUIManager.onBackPressedCallback");
        if (com.chartboost.sdk.b.b()) {
            if (this.d == null) {
                CBLogging.b("CBUIManager", "The Chartboost methods onCreate(), onStart(), onStop(), and onDestroy() must be called in the corresponding methods of your activity in order for Chartboost to function properly.");
                return false;
            }
            if (this.k) {
                this.k = false;
                this.k();
                return true;
            }
        }
        return false;
    }
    
    void k(final Activity activity) {
        aq.a("CBUIManager.onDestroyImpl", activity);
        this.b(this.a(activity), false);
        c c2;
        final c c = c2 = this.d();
        if (c == null) {
            c2 = c;
            if (activity == this.e) {
                c2 = c;
                if (this.f != null) {
                    c2 = this.f;
                }
            }
        }
        final d c3 = this.c();
        if (c3 != null && c2 != null) {
            c3.d(c2);
        }
        this.f = null;
    }
    
    boolean k() {
        aq.a("CBUIManager.onBackPressedImpl");
        return this.m();
    }
    
    boolean l() {
        final c d = this.d();
        if (d == null) {
            return false;
        }
        d.z = true;
        this.b(d);
        return true;
    }
    
    @TargetApi(14)
    private class a implements Application$ActivityLifecycleCallbacks
    {
        public void onActivityCreated(final Activity activity, final Bundle bundle) {
            aq.a("CBUIManager.ActivityCallbackListener.onActivityCreated", activity);
            CBLogging.a("CBUIManager", "######## onActivityCreated callback called");
            if (!(activity instanceof CBImpressionActivity)) {
                com.chartboost.sdk.c.this.b(activity);
            }
        }
        
        public void onActivityDestroyed(final Activity activity) {
            aq.a("CBUIManager.ActivityCallbackListener.onActivityDestroyed", activity);
            if (!(activity instanceof CBImpressionActivity)) {
                CBLogging.a("CBUIManager", "######## onActivityDestroyed callback called from developer side");
                com.chartboost.sdk.c.this.j(activity);
                return;
            }
            CBLogging.a("CBUIManager", "######## onActivityDestroyed callback called from CBImpressionactivity");
            com.chartboost.sdk.c.this.k(activity);
        }
        
        public void onActivityPaused(final Activity activity) {
            aq.a("CBUIManager.ActivityCallbackListener.onActivityPaused", activity);
            if (!(activity instanceof CBImpressionActivity)) {
                CBLogging.a("CBUIManager", "######## onActivityPaused callback called from developer side");
                com.chartboost.sdk.c.this.g(activity);
                return;
            }
            CBLogging.a("CBUIManager", "######## onActivityPaused callback called from CBImpressionactivity");
            com.chartboost.sdk.c.this.a(activity);
            com.chartboost.sdk.c.this.i();
        }
        
        public void onActivityResumed(final Activity activity) {
            aq.a("CBUIManager.ActivityCallbackListener.onActivityResumed", activity);
            if (!(activity instanceof CBImpressionActivity)) {
                CBLogging.a("CBUIManager", "######## onActivityResumed callback called from developer side");
                com.chartboost.sdk.c.this.f(activity);
                return;
            }
            CBLogging.a("CBUIManager", "######## onActivityResumed callback called from CBImpressionactivity");
            com.chartboost.sdk.c.this.a(activity);
            com.chartboost.sdk.c.this.h();
        }
        
        public void onActivitySaveInstanceState(final Activity activity, final Bundle bundle) {
        }
        
        public void onActivityStarted(final Activity activity) {
            aq.a("CBUIManager.ActivityCallbackListener.onActivityStarted", activity);
            if (!(activity instanceof CBImpressionActivity)) {
                CBLogging.a("CBUIManager", "######## onActivityStarted callback called from developer side");
                com.chartboost.sdk.c.this.d(activity);
                return;
            }
            CBLogging.a("CBUIManager", "######## onActivityStarted callback called from CBImpressionactivity");
            com.chartboost.sdk.c.this.e(activity);
        }
        
        public void onActivityStopped(final Activity activity) {
            aq.a("CBUIManager.ActivityCallbackListener.onActivityStopped", activity);
            if (!(activity instanceof CBImpressionActivity)) {
                CBLogging.a("CBUIManager", "######## onActivityStopped callback called from developer side");
                com.chartboost.sdk.c.this.h(activity);
                return;
            }
            CBLogging.a("CBUIManager", "######## onActivityStopped callback called from CBImpressionactivity");
            com.chartboost.sdk.c.this.i(activity);
        }
    }
    
    class b implements Runnable
    {
        private final int b;
        private final int c;
        private final int d;
        
        b() {
            final int n = -1;
            final com.chartboost.sdk.a a2 = this.a();
            int hashCode;
            if (com.chartboost.sdk.c.this.e == null) {
                hashCode = -1;
            }
            else {
                hashCode = com.chartboost.sdk.c.this.e.hashCode();
            }
            this.b = hashCode;
            int hashCode2;
            if (com.chartboost.sdk.c.this.d == null) {
                hashCode2 = -1;
            }
            else {
                hashCode2 = com.chartboost.sdk.c.this.d.hashCode();
            }
            this.c = hashCode2;
            int hashCode3;
            if (a2 == null) {
                hashCode3 = n;
            }
            else {
                hashCode3 = a2.hashCode();
            }
            this.d = hashCode3;
        }
        
        private com.chartboost.sdk.a a() {
            return com.chartboost.sdk.i.c;
        }
        
        @Override
        public void run() {
            aq.a("ClearMemoryRunnable.run");
            final com.chartboost.sdk.a a = this.a();
            if (com.chartboost.sdk.c.this.d != null && com.chartboost.sdk.c.this.d.hashCode() == this.c) {
                com.chartboost.sdk.c.this.d = null;
                aq.a("CBUIManager.clearHostActivityRef");
            }
            if (a != null && a.hashCode() == this.d) {
                com.chartboost.sdk.i.c = null;
                aq.a("SdkSettings.clearDelegate");
            }
        }
    }
    
    public class c implements Runnable
    {
        public final int a;
        Activity b;
        boolean c;
        public com.chartboost.sdk.Model.c d;
        
        public c(final int a) {
            this.b = null;
            this.c = false;
            this.d = null;
            this.a = a;
        }
        
        @Override
        public void run() {
            Label_0611: {
                Label_0592: {
                    Label_0584: {
                        Label_0552: {
                            Label_0531: {
                                Label_0515: {
                                    Label_0506: {
                                        Label_0459: {
                                            Label_0390: {
                                                Label_0358: {
                                                    Label_0327: {
                                                        Label_0276: {
                                                            try {
                                                                switch (this.a) {
                                                                    case 0: {
                                                                        com.chartboost.sdk.c.this.c(this.b);
                                                                        return;
                                                                    }
                                                                    case 1: {
                                                                        break;
                                                                    }
                                                                    case 2: {
                                                                        break Label_0276;
                                                                    }
                                                                    case 3: {
                                                                        break Label_0327;
                                                                    }
                                                                    case 4: {
                                                                        break Label_0358;
                                                                    }
                                                                    case 5: {
                                                                        break Label_0390;
                                                                    }
                                                                    case 6: {
                                                                        break Label_0459;
                                                                    }
                                                                    case 7: {
                                                                        break Label_0506;
                                                                    }
                                                                    case 9: {
                                                                        break Label_0515;
                                                                    }
                                                                    case 10: {
                                                                        break Label_0531;
                                                                    }
                                                                    case 11: {
                                                                        break Label_0552;
                                                                    }
                                                                    case 12: {
                                                                        break Label_0584;
                                                                    }
                                                                    case 13: {
                                                                        break Label_0592;
                                                                    }
                                                                    case 14: {
                                                                        break Label_0611;
                                                                    }
                                                                    default: {
                                                                        return;
                                                                    }
                                                                }
                                                            }
                                                            catch (Exception ex) {
                                                                com.chartboost.sdk.Tracking.a.a(c.class, "run (" + this.a + ")", ex);
                                                                return;
                                                            }
                                                            com.chartboost.sdk.c.this.b.removeCallbacks(com.chartboost.sdk.c.this.g);
                                                            if (com.chartboost.sdk.c.this.d != null && !com.chartboost.sdk.c.this.d.a(this.b) && com.chartboost.sdk.c.this.g()) {
                                                                com.chartboost.sdk.c.this.b(com.chartboost.sdk.c.this.d);
                                                                com.chartboost.sdk.c.this.a(com.chartboost.sdk.c.this.d, false);
                                                            }
                                                            com.chartboost.sdk.c.this.a(this.b, true);
                                                            com.chartboost.sdk.c.this.d = com.chartboost.sdk.c.this.a(this.b);
                                                            com.chartboost.sdk.c.this.a.b();
                                                            com.chartboost.sdk.c.this.a.a(this.b);
                                                            com.chartboost.sdk.c.this.e(this.b);
                                                            return;
                                                        }
                                                        if (com.chartboost.sdk.c.this.a(com.chartboost.sdk.c.this.a(this.b))) {
                                                            com.chartboost.sdk.c.this.h();
                                                            return;
                                                        }
                                                        if (CBUtility.a(Chartboost.CBFramework.CBFrameworkUnity)) {
                                                            com.chartboost.sdk.c.this.a.b();
                                                            return;
                                                        }
                                                        return;
                                                    }
                                                    if (com.chartboost.sdk.c.this.a(com.chartboost.sdk.c.this.a(this.b))) {
                                                        com.chartboost.sdk.c.this.i();
                                                        return;
                                                    }
                                                    return;
                                                }
                                                final j a = com.chartboost.sdk.c.this.a(this.b);
                                                if (com.chartboost.sdk.c.this.a(a)) {
                                                    com.chartboost.sdk.c.this.b(a);
                                                    return;
                                                }
                                                return;
                                            }
                                            if (com.chartboost.sdk.c.this.d == null || com.chartboost.sdk.c.this.d.a(this.b)) {
                                                (com.chartboost.sdk.c.this.g = new b()).run();
                                            }
                                            com.chartboost.sdk.c.this.k(this.b);
                                            return;
                                        }
                                        if (com.chartboost.sdk.c.this.e == null) {
                                            return;
                                        }
                                        if (this.c) {
                                            com.chartboost.sdk.c.this.e.forwardTouchEvents(com.chartboost.sdk.c.this.a());
                                            return;
                                        }
                                        com.chartboost.sdk.c.this.e.forwardTouchEvents(null);
                                        return;
                                    }
                                    com.chartboost.sdk.c.this.l();
                                    return;
                                }
                                com.chartboost.sdk.c.this.a(this.b, this.d);
                                return;
                            }
                            if (this.d.a()) {
                                this.d.u().b();
                                return;
                            }
                            return;
                        }
                        final d c = com.chartboost.sdk.c.this.c();
                        if (this.d.l == 2 && c != null) {
                            c.b(this.d);
                            return;
                        }
                        return;
                    }
                    this.d.n();
                    return;
                }
                com.chartboost.sdk.c.this.c.a(this.d, this.b);
                return;
            }
            com.chartboost.sdk.c.this.c.d(this.d);
        }
    }
}
