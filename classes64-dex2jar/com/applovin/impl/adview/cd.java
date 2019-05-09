// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.content.Context;

class cd implements Runnable
{
    final /* synthetic */ Context a;
    final /* synthetic */ cb b;
    
    cd(final cb b, final Context a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        this.b.a(this.a);
    }
}
