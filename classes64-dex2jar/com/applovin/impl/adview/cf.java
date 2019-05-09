// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class cf implements Runnable
{
    final /* synthetic */ int a;
    final /* synthetic */ cb b;
    
    cf(final cb b, final int a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.b.h != null) {
            this.b.h.failedToReceiveAd(this.a);
        }
    }
}
