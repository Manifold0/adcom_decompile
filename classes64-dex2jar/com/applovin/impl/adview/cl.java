// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class cl implements ao
{
    final /* synthetic */ ck a;
    
    cl(final ck a) {
        this.a = a;
    }
    
    @Override
    public void a() {
        this.a.handleCountdownStep();
    }
    
    @Override
    public boolean b() {
        return this.a.shouldContinueFullLengthVideoCountdown();
    }
}
