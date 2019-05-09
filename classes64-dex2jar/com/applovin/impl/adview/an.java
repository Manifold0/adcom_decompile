// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class an implements Runnable
{
    final /* synthetic */ ap a;
    final /* synthetic */ int b;
    final /* synthetic */ am c;
    
    an(final am c, final ap a, final int b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        final ao b = this.a.c();
        if (b.b()) {
            if (this.c.d.get() == this.b) {
                while (true) {
                    try {
                        b.a();
                        this.c.a(this.a, this.b);
                        return;
                    }
                    catch (Throwable t) {
                        this.c.a.e("CountdownManager", "Encountered error on countdown step for: " + this.a.a(), t);
                        continue;
                    }
                    break;
                }
            }
            this.c.a.w("CountdownManager", "Killing duplicate countdown from previous generation: " + this.a.a());
            return;
        }
        this.c.a.d("CountdownManager", "Ending countdown for " + this.a.a());
    }
}
