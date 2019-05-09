// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.h;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.c;

class d implements com.chartboost.sdk.Model.d
{
    private final e a;
    private final f b;
    
    d(final e a, final f b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void a(final c c) {
        c.l = 1;
        if (this.a.f.f(this.b.b)) {
            this.a.e.a(c);
            if (this.a.f.a == 0 && c.n != 1 && c.p.b != 1) {
                this.c(c);
            }
            return;
        }
        final e a = this.a;
        a.getClass();
        this.a.a.execute(a.new a(7, this.b.b, this.b, null));
    }
    
    @Override
    public void a(final c d, final CBError.CBImpressionError cbImpressionError) {
        final com.chartboost.sdk.c e = this.a.e;
        e.getClass();
        final com.chartboost.sdk.c.c c = e.new c(11);
        c.d = d;
        h.b(c);
        final e a = this.a;
        a.getClass();
        this.a.a.execute(a.new a(6, this.b.b, this.b, cbImpressionError));
    }
    
    @Override
    public void b(final c c) {
        final e a = this.a;
        a.getClass();
        this.a.a.execute(a.new a(7, this.b.b, this.b, null));
    }
    
    @Override
    public void c(final c c) {
        c.r = true;
        final e a = this.a;
        a.getClass();
        this.a.a.execute(a.new a(5, this.b.b, this.b, null));
    }
}
