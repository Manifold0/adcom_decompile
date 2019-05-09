// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.d;

import java.io.Serializable;
import android.os.Bundle;
import com.facebook.ads.internal.w.b.r;

public class d implements r<Bundle>
{
    private c a;
    private final c b;
    private final b c;
    private boolean d;
    private boolean e;
    private boolean f;
    
    public d(final b c) {
        this.d = false;
        this.e = false;
        this.f = false;
        this.c = c;
        this.b = new c(c.b);
        this.a = new c(c.b);
    }
    
    public d(final b c, final Bundle bundle) {
        this.d = false;
        this.e = false;
        this.f = false;
        this.c = c;
        this.b = (c)bundle.getSerializable("testStats");
        this.a = (c)bundle.getSerializable("viewableStats");
        this.d = bundle.getBoolean("ended");
        this.e = bundle.getBoolean("passed");
        this.f = bundle.getBoolean("complete");
    }
    
    private void b() {
        this.f = true;
        this.d = true;
        c c;
        if (this.e) {
            c = this.a;
        }
        else {
            c = this.b;
        }
        this.c.a(this.f, this.e, c);
    }
    
    public void a() {
        if (!this.d) {
            this.a.b();
        }
    }
    
    public void a(double n, final double n2) {
        if (!this.d) {
            this.b.a(n, n2);
            this.a.a(n, n2);
            if (this.c.e) {
                n = this.a.c().h();
            }
            else {
                n = this.a.c().g();
            }
            if (this.c.c >= 0.0 && this.b.c().f() > this.c.c && n == 0.0) {
                this.b();
                return;
            }
            if (n >= this.c.d) {
                this.e = true;
                this.b();
            }
        }
    }
    
    @Override
    public Bundle g() {
        final Bundle bundle = new Bundle();
        bundle.putSerializable("viewableStats", (Serializable)this.a);
        bundle.putSerializable("testStats", (Serializable)this.b);
        bundle.putBoolean("ended", this.d);
        bundle.putBoolean("passed", this.e);
        bundle.putBoolean("complete", this.f);
        return bundle;
    }
}
