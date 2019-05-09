// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class ch implements Runnable
{
    private final cf a;
    private final ck b;
    
    protected ch(final cf a, final ck b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public final void run() {
        try {
            final Object f = this.a.f();
            if (this.b != null && !(this.b instanceof cl)) {
                this.b.a(this.a, f);
            }
        }
        catch (Throwable t) {
            if (this.b != null && !(this.b instanceof cl)) {
                this.b.a(this.a);
            }
        }
    }
}
