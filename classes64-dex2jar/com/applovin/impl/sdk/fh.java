// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class fh implements Runnable
{
    final /* synthetic */ fd a;
    private final String b;
    private final dx c;
    private final fe d;
    
    fh(final fd a, final dx c, final fe d) {
        this.a = a;
        this.b = c.a();
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void run() {
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            ab.a();
            if (!this.a.b.e() || this.c.g) {
                this.a.c.i(this.b, "Task started execution...");
                this.c.run();
                final long n = System.currentTimeMillis() - currentTimeMillis;
                this.a.c.i(this.b, "Task executed successfully in " + n + "ms.");
                final aw a = this.a.b.a();
                a.a(this.b + "_count");
                a.a(this.b + "_time", n);
            }
            else {
                this.a.c.i(this.b, "Task re-scheduled...");
                this.a.a(this.c, this.d, 2000L);
            }
        }
        catch (Throwable t) {
            this.a.c.e(this.b, "Task failed execution in " + (System.currentTimeMillis() - currentTimeMillis) + "ms.", t);
        }
        finally {
            this.a.c.i("TaskManager", this.d + " queue finished task " + this.c.a() + " with queue size " + (this.a.a(this.d) - 1L));
        }
    }
}
