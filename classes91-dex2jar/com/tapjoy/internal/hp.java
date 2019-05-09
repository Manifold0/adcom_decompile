// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.SystemClock;

public abstract class hp implements Runnable
{
    private final long a;
    
    public hp() {
        this.a = 300L;
    }
    
    public abstract boolean a();
    
    @Override
    public void run() {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long a = this.a;
        while (!this.a() && elapsedRealtime + a - SystemClock.elapsedRealtime() > 0L) {
            try {
                Thread.sleep(0L);
                continue;
            }
            catch (InterruptedException ex) {}
            break;
        }
    }
}
