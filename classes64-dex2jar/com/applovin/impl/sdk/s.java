// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class s implements Runnable
{
    final /* synthetic */ r a;
    
    s(final r a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        if (!this.a.a.isForegroundClickInvalidated()) {
            this.a.e.a(this.a.b, this.a.c, this.a.d, this.a.a);
        }
    }
}
