// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.concurrent.atomic.AtomicReference;

class ek implements af<String>
{
    final /* synthetic */ AtomicReference a;
    final /* synthetic */ String b;
    final /* synthetic */ ej c;
    
    ek(final ej c, final AtomicReference a, final String b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void a(final int n) {
        this.c.e.e(this.c.c, "Failed to load resource from '" + this.b + "'");
    }
    
    @Override
    public void a(final String s, final int n) {
        this.a.set(s);
    }
}
