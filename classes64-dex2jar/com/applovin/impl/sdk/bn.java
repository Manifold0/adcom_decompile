// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class bn implements Runnable
{
    final /* synthetic */ bm a;
    
    bn(final bm a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.a.c != null) {
            this.a.c.dismiss();
        }
    }
}
