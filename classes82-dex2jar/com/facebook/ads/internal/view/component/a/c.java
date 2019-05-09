// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.component.a;

import java.util.Map;
import java.util.HashMap;
import com.facebook.ads.internal.adapters.b.l;
import android.view.View;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.view.component.a;
import com.facebook.ads.internal.view.component.j;
import android.widget.RelativeLayout;

public abstract class c extends RelativeLayout
{
    static final int a;
    static final int b;
    private final j c;
    private final a d;
    private final com.facebook.ads.internal.s.c e;
    
    static {
        a = (int)(16.0f * x.b);
        b = (int)(28.0f * x.b);
    }
    
    protected c(final e e, final h h, final boolean b) {
        super(e.a());
        this.e = e.b();
        x.a((View)(this.d = new a(e.a(), this.d(), this.e(), "com.facebook.ads.interstitial.clicked", h, e.b(), e.c(), e.e(), e.f())));
        x.a((View)(this.c = new j(this.getContext(), h, b, this.b(), this.c())));
    }
    
    public void a(final l l, final String s, final double n) {
        this.c.a(l.a().b(), l.a().c(), null, false, !this.a() && n > 0.0 && n < 1.0);
        this.d.a(l.b(), s, new HashMap<String, String>());
    }
    
    public abstract boolean a();
    
    protected boolean b() {
        return true;
    }
    
    protected boolean c() {
        return true;
    }
    
    protected boolean d() {
        return true;
    }
    
    protected boolean e() {
        return true;
    }
    
    public com.facebook.ads.internal.s.c getAdEventManager() {
        return this.e;
    }
    
    protected a getCtaButton() {
        return this.d;
    }
    
    public int getExactMediaHeightIfAvailable() {
        return 0;
    }
    
    public int getExactMediaWidthIfAvailable() {
        return 0;
    }
    
    protected j getTitleDescContainer() {
        return this.c;
    }
}
