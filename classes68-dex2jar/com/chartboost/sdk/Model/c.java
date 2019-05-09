// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Model;

import com.chartboost.sdk.impl.ad;
import android.view.View;
import com.chartboost.sdk.i;
import com.chartboost.sdk.impl.u;
import com.chartboost.sdk.Libraries.CBLogging;
import java.util.Locale;
import com.chartboost.sdk.impl.bf;
import com.chartboost.sdk.impl.v;
import com.chartboost.sdk.impl.s;
import org.json.JSONObject;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.bc;
import android.content.SharedPreferences;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.d;
import com.chartboost.sdk.impl.ak;
import android.os.Handler;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.e;

public class c
{
    private boolean A;
    private Boolean B;
    private e C;
    private Runnable D;
    public final com.chartboost.sdk.impl.c a;
    public final f b;
    public final ah c;
    public final ap d;
    public final a e;
    public final Handler f;
    public final com.chartboost.sdk.c g;
    public final ak h;
    public final d i;
    public final al j;
    public final com.chartboost.sdk.Model.d k;
    public int l;
    public final String m;
    public int n;
    public final String o;
    public final com.chartboost.sdk.Model.a p;
    public final SharedPreferences q;
    public boolean r;
    public bc s;
    public boolean t;
    public boolean u;
    public boolean v;
    public aj w;
    public boolean x;
    public boolean y;
    public boolean z;
    
    public c(final com.chartboost.sdk.Model.a p15, final com.chartboost.sdk.Model.d k, final f b, final ah c, final ap d, final SharedPreferences q, final a e, final Handler f, final com.chartboost.sdk.c g, final ak h, final d i, final al j, final com.chartboost.sdk.impl.c a, final String m, final String o) {
        this.B = null;
        this.t = false;
        this.u = false;
        this.v = false;
        this.y = false;
        this.z = false;
        this.p = p15;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = 0;
        this.r = false;
        this.x = false;
        this.z = true;
        this.n = 3;
        this.m = m;
        this.o = o;
        this.A = true;
        this.q = q;
    }
    
    private boolean x() {
        return this.B != null;
    }
    
    private boolean y() {
        return this.B;
    }
    
    public void a(final CBError.CBImpressionError cbImpressionError) {
        this.k.a(this, cbImpressionError);
    }
    
    public void a(final Runnable d) {
        this.D = d;
    }
    
    void a(final String s, final JSONObject jsonObject) {
        final Handler f = this.f;
        final com.chartboost.sdk.impl.c a = this.a;
        a.getClass();
        f.post((Runnable)a.new a(1, this.m, null));
        if (this.b() && this.l == 2) {
            final d c = this.g.c();
            if (c != null) {
                c.b(this);
            }
        }
        int n;
        if (!s.a().a(s)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            final aj w = new aj("/api/click", this.d, this.e, 2, null);
            if (!this.p.f.isEmpty()) {
                w.a("ad_id", this.p.f);
            }
            if (!this.p.m.isEmpty()) {
                w.a("to", this.p.m);
            }
            if (!this.p.g.isEmpty()) {
                w.a("cgn", this.p.g);
            }
            if (!this.p.h.isEmpty()) {
                w.a("creative", this.p.h);
            }
            if (this.n == 1 || this.n == 2) {
                e e;
                if (this.p.b == 0 && this.k() != null) {
                    e = this.u();
                }
                else if (this.p.b == 1 && this.k() != null) {
                    e = this.u();
                }
                else {
                    e = null;
                }
                if (e != null) {
                    final float k = e.k();
                    final float j = e.j();
                    CBLogging.a(this.getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", j, k));
                    w.a("total_time", j / 1000.0f);
                    if (k <= 0.0f) {
                        w.a("playback_time", j / 1000.0f);
                    }
                    else {
                        w.a("playback_time", k / 1000.0f);
                    }
                }
            }
            if (jsonObject != null) {
                w.a("click_coordinates", jsonObject);
            }
            w.a("location", this.m);
            if (this.x()) {
                w.a("retarget_reinstall", this.y());
            }
            this.w = w;
            this.h.a(this, s, null);
        }
        else {
            this.h.a(this, false, s, CBError.CBClickError.URI_INVALID, null);
        }
        this.e.c(this.a.a(this.p.b), this.m, this.o());
    }
    
    public boolean a() {
        this.l = 0;
        if (this.p.b == 0) {
            switch (this.a.a) {
                case 0: {
                    if (this.p.p.equals("video")) {
                        this.n = 1;
                        this.C = new v(this, this.b, this.f, this.g);
                        this.A = false;
                        break;
                    }
                    this.n = 0;
                    this.C = new u(this, this.f, this.g);
                    break;
                }
                case 1: {
                    this.n = 2;
                    this.C = new v(this, this.b, this.f, this.g);
                    this.A = false;
                    break;
                }
            }
        }
        else {
            switch (this.a.a) {
                case 0: {
                    if (this.p.p.equals("video")) {
                        this.n = 1;
                        this.A = false;
                        break;
                    }
                    this.n = 0;
                    break;
                }
                case 1: {
                    this.n = 2;
                    this.A = false;
                    break;
                }
            }
            this.C = new bf(this, this.b, this.c, this.q, this.e, this.f, this.g, this.i);
        }
        return this.C.a(this.p.a);
    }
    
    public boolean a(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/chartboost/sdk/Model/c.l:I
        //     4: iconst_2       
        //     5: if_icmpne       15
        //     8: aload_0        
        //     9: getfield        com/chartboost/sdk/Model/c.t:Z
        //    12: ifeq            17
        //    15: iconst_0       
        //    16: ireturn        
        //    17: aload_0        
        //    18: getfield        com/chartboost/sdk/Model/c.p:Lcom/chartboost/sdk/Model/a;
        //    21: getfield        com/chartboost/sdk/Model/a.j:Ljava/lang/String;
        //    24: astore_3       
        //    25: aload_0        
        //    26: getfield        com/chartboost/sdk/Model/c.p:Lcom/chartboost/sdk/Model/a;
        //    29: getfield        com/chartboost/sdk/Model/a.i:Ljava/lang/String;
        //    32: astore          4
        //    34: aload           4
        //    36: invokevirtual   java/lang/String.isEmpty:()Z
        //    39: ifne            82
        //    42: aload_0        
        //    43: getfield        com/chartboost/sdk/Model/c.h:Lcom/chartboost/sdk/impl/ak;
        //    46: aload           4
        //    48: invokevirtual   com/chartboost/sdk/impl/ak.a:(Ljava/lang/String;)Z
        //    51: istore_2       
        //    52: iload_2        
        //    53: ifeq            75
        //    56: aload_0        
        //    57: getstatic       java/lang/Boolean.TRUE:Ljava/lang/Boolean;
        //    60: putfield        com/chartboost/sdk/Model/c.B:Ljava/lang/Boolean;
        //    63: aload           4
        //    65: astore_3       
        //    66: aload_0        
        //    67: getfield        com/chartboost/sdk/Model/c.x:Z
        //    70: ifeq            102
        //    73: iconst_0       
        //    74: ireturn        
        //    75: aload_0        
        //    76: getstatic       java/lang/Boolean.FALSE:Ljava/lang/Boolean;
        //    79: putfield        com/chartboost/sdk/Model/c.B:Ljava/lang/Boolean;
        //    82: goto            66
        //    85: astore          4
        //    87: aload_0        
        //    88: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    91: ldc_w           "onClick"
        //    94: aload           4
        //    96: invokestatic    com/chartboost/sdk/Tracking/a.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Exception;)V
        //    99: goto            66
        //   102: aload_0        
        //   103: iconst_1       
        //   104: putfield        com/chartboost/sdk/Model/c.x:Z
        //   107: aload_0        
        //   108: iconst_0       
        //   109: putfield        com/chartboost/sdk/Model/c.z:Z
        //   112: aload_0        
        //   113: aload_3        
        //   114: aload_1        
        //   115: invokevirtual   com/chartboost/sdk/Model/c.a:(Ljava/lang/String;Lorg/json/JSONObject;)V
        //   118: iconst_1       
        //   119: ireturn        
        //   120: astore          5
        //   122: aload           4
        //   124: astore_3       
        //   125: aload           5
        //   127: astore          4
        //   129: goto            87
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  42     52     85     87     Ljava/lang/Exception;
        //  56     63     120    132    Ljava/lang/Exception;
        //  75     82     85     87     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean b() {
        return this.A;
    }
    
    public void c() {
        this.z = true;
        this.g.b(this);
        this.k.b(this);
    }
    
    public void d() {
        this.k.a(this);
    }
    
    public void e() {
        this.u = true;
        this.A = true;
        if (this.a.a == 1 && com.chartboost.sdk.i.c != null) {
            com.chartboost.sdk.i.c.didCompleteRewardedVideo(this.m, this.p.k);
        }
        this.w();
    }
    
    public void f() {
        this.v = true;
    }
    
    public boolean g() {
        if (this.C != null) {
            this.C.b();
            if (this.C.e() != null) {
                return true;
            }
        }
        else {
            CBLogging.b("CBImpression", "reinitializing -- no view protocol exists!!");
        }
        CBLogging.e("CBImpression", "reinitializing -- view not yet created");
        return false;
    }
    
    public void h() {
        this.i();
        if (!this.r) {
            return;
        }
        if (this.C != null) {
            this.C.d();
        }
        this.C = null;
        CBLogging.e("CBImpression", "Destroying the view and view data");
    }
    
    public void i() {
        Label_0063: {
            if (this.s == null) {
                break Label_0063;
            }
            this.s.b();
            while (true) {
                try {
                    if (this.C != null && this.C.e() != null && this.C.e().getParent() != null) {
                        this.s.removeView((View)this.C.e());
                    }
                    this.s = null;
                    if (this.C != null) {
                        this.C.f();
                    }
                    CBLogging.e("CBImpression", "Destroying the view");
                }
                catch (Exception ex) {
                    CBLogging.a("CBImpression", "Exception raised while cleaning up views", ex);
                    com.chartboost.sdk.Tracking.a.a(this.getClass(), "cleanUpViews", ex);
                    continue;
                }
                break;
            }
        }
    }
    
    public CBError.CBImpressionError j() {
        try {
            if (this.C != null) {
                return this.C.c();
            }
        }
        catch (Exception ex) {
            com.chartboost.sdk.Tracking.a.a(this.getClass(), "tryCreatingView", ex);
        }
        return CBError.CBImpressionError.ERROR_CREATING_VIEW;
    }
    
    public e.a k() {
        if (this.C != null) {
            return this.C.e();
        }
        return null;
    }
    
    public void l() {
        if (this.C != null && this.C.e() != null) {
            this.C.e().setVisibility(8);
        }
    }
    
    public void m() {
        this.t = true;
    }
    
    public void n() {
        if (this.D != null) {
            this.D.run();
            this.D = null;
        }
        this.t = false;
    }
    
    public String o() {
        return this.p.f;
    }
    
    public void p() {
        this.k.c(this);
    }
    
    public boolean q() {
        return this.C != null && this.C.l();
    }
    
    public void r() {
        this.x = false;
        if (this.C != null && this.y) {
            this.y = false;
            this.C.m();
        }
    }
    
    public void s() {
        this.x = false;
    }
    
    public void t() {
        if (this.C != null && !this.y) {
            this.y = true;
            this.C.n();
        }
    }
    
    public e u() {
        return this.C;
    }
    
    public boolean v() {
        return this.z;
    }
    
    public void w() {
        e u = null;
        final aj aj = new aj("/api/video-complete", this.d, this.e, 2, null);
        aj.a("location", this.m);
        aj.a("reward", this.p.k);
        aj.a("currency-name", this.p.l);
        aj.a("ad_id", this.o());
        aj.a("force_close", false);
        if (!this.p.g.isEmpty()) {
            aj.a("cgn", this.p.g);
        }
        if (this.k() != null) {
            u = this.u();
        }
        if (u != null) {
            final float k = u.k();
            final float j = u.j();
            CBLogging.a(this.getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", j, k));
            aj.a("total_time", j / 1000.0f);
            if (k <= 0.0f) {
                aj.a("playback_time", j / 1000.0f);
            }
            else {
                aj.a("playback_time", k / 1000.0f);
            }
        }
        this.c.a((ad<Object>)aj);
        this.e.b(this.a.a(this.p.b), this.o());
    }
}
