// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

public class i implements Runnable
{
    public final boolean a;
    private final h b;
    private final int c;
    private final int d;
    
    i(final h b, final boolean a, final int c, final int d) {
        this.b = b;
        this.a = a;
        this.c = c;
        this.d = d;
    }
    
    @Override
    public void run() {
        this.b.a(this.a, this.c, this.d);
    }
}
