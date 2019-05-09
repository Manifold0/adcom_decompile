// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class bw implements Runnable
{
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ bv c;
    
    bw(final bv c, final int a, final int b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.c.a.logger.e("InterActivity", "Video view error (" + this.a + "," + this.b + ").");
        this.c.a.handleMediaError();
    }
}
