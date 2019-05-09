// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class bt implements Runnable
{
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ bs c;
    
    bt(final bs c, final int a, final int b) {
        this.c = c;
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void run() {
        this.c.a.a.logger.e("InterActivity", "Media player error (" + this.a + "," + this.b + ").");
        this.c.a.a.handleMediaError();
    }
}
