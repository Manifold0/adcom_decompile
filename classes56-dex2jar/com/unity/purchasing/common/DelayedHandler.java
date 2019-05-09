// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import android.os.Looper;
import android.os.Handler;

public class DelayedHandler implements IDelayedExecutor
{
    private Handler handler;
    
    public DelayedHandler() {
        this.handler = new Handler(Looper.getMainLooper());
    }
    
    @Override
    public void ExecuteDelayed(final Runnable runnable, final long n) {
        this.handler.postDelayed(runnable, n);
    }
}
