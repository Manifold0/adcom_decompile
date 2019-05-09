// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.TimerTask;

class bj extends TimerTask
{
    final /* synthetic */ bi a;
    
    bj(final bi a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        this.a.a.b.c.runOnUiThread(this.a.a.b.d);
    }
}
