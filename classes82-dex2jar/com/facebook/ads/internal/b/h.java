// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.b;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.adapters.t;
import com.facebook.ads.internal.adapters.k;
import java.util.Map;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.adapters.s;
import android.content.Context;

public class h extends c
{
    public h(final Context context, final com.facebook.ads.internal.b.a a) {
        super(context, a);
    }
    
    @Override
    protected void a() {
        final s s = (s)this.f;
        s.a(this.h.g);
        s.b();
    }
    
    public void a(final RewardData rewardData) {
        if (this.f == null) {
            throw new IllegalStateException("no adapter ready to set reward on");
        }
        if (this.f.getPlacementType() != AdPlacementType.REWARDED_VIDEO) {
            throw new IllegalStateException("can only set on rewarded video ads");
        }
        ((s)this.f).a(rewardData);
    }
    
    @Override
    protected void a(final AdAdapter adAdapter, final com.facebook.ads.internal.m.c c, final com.facebook.ads.internal.m.a a, final Map<String, Object> map) {
        ((k)adAdapter).a(this.b, new t() {
            @Override
            public void a() {
                h.this.c.h();
            }
            
            @Override
            public void a(final s f) {
                h.this.f = f;
                h.this.c.a(f);
            }
            
            @Override
            public void a(final s s, final AdError adError) {
                h.this.c.a(new com.facebook.ads.internal.protocol.a(AdErrorType.INTERNAL_ERROR, null));
                h.this.a(s);
                h.this.i();
            }
            
            @Override
            public void b() {
                h.this.c.k();
            }
            
            @Override
            public void b(final s s) {
                h.this.c.a();
            }
            
            @Override
            public void c(final s s) {
                h.this.c.b();
            }
            
            @Override
            public void d(final s s) {
                h.this.c.g();
            }
            
            @Override
            public void e(final s s) {
                h.this.c.i();
            }
            
            @Override
            public void f(final s s) {
                h.this.c.j();
            }
        }, map, this.h.f, this.h.e);
    }
    
    @Nullable
    @Override
    com.facebook.ads.internal.protocol.a c() {
        if (!this.h.f || this.d()) {
            return null;
        }
        return new com.facebook.ads.internal.protocol.a(AdErrorType.CLEAR_TEXT_SUPPORT_NOT_ALLOWED, "");
    }
}
