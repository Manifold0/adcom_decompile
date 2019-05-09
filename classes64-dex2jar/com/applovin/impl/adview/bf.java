// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class bf implements Runnable
{
    final /* synthetic */ ak a;
    final /* synthetic */ az b;
    
    bf(final az b, final ak a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.a.equals(this.b.y)) {
            this.b.m();
        }
        else if (this.a.equals(this.b.A)) {
            this.b.o();
        }
    }
}
