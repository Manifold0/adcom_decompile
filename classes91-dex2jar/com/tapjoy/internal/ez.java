// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ez
{
    public et a;
    public volatile fi.a b;
    public int c;
    public volatile fi.a d;
    public volatile fi.a e;
    
    public final void a() {
        this.a(16);
        final fi.a d = this.d;
        if (d != null) {
            this.d = null;
            d.b().c();
        }
    }
    
    public final void a(final int n) {
        synchronized (this) {
            final fi.a b = this.b;
            if (b != null && this.c < n) {
                this.c |= n;
                b.a("state", (Object)this.c).b().c();
            }
        }
    }
}
