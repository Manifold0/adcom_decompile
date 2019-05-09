// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import com.facebook.ads.AdError;
import android.view.View;
import com.facebook.ads.internal.adapters.f;
import java.util.Map;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.n;
import android.content.Context;

public class d extends c
{
    public d(final Context context, final com.facebook.ads.internal.b.a a) {
        super(context, a);
    }
    
    @Override
    protected void a() {
        ((n)this.f).e();
    }
    
    @Override
    protected void a(final AdAdapter adAdapter, final com.facebook.ads.internal.m.c c, final com.facebook.ads.internal.m.a a, final Map<String, Object> map) {
        ((f)adAdapter).a(this.b, new com.facebook.ads.a.a() {
            @Override
            public void a(final n f) {
                d.this.f = f;
                d.this.a = false;
                d.this.c.a(f);
            }
            
            @Override
            public void a(final n n, final View view) {
                d.this.c.a(view);
            }
            
            @Override
            public void a(final n n, final AdError adError) {
                d.this.c.a(new com.facebook.ads.internal.protocol.a(adError.getErrorCode(), adError.getErrorMessage()));
            }
            
            @Override
            public void b(final n n) {
                d.this.c.a();
            }
            
            @Override
            public void c(final n n) {
                d.this.c.b();
            }
            
            @Override
            public void d(final n n) {
                d.this.c.c();
            }
        }, map, this.g, this.h.d);
    }
    
    @Override
    protected void a(final String s) {
        final com.facebook.ads.internal.protocol.a a = com.facebook.ads.internal.b.e.a(this.b, 0);
        if (a != null) {
            this.a(a);
            return;
        }
        super.a(s);
    }
}
