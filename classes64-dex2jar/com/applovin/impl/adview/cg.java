// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class cg implements Runnable
{
    final /* synthetic */ cb a;
    
    cg(final cb a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        this.a.n.dismiss();
    }
}
