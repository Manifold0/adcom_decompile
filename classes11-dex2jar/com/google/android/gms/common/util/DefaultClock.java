// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.os.SystemClock;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DefaultClock implements Clock
{
    private static final DefaultClock zzgm;
    
    static {
        zzgm = new DefaultClock();
    }
    
    private DefaultClock() {
    }
    
    @KeepForSdk
    public static Clock getInstance() {
        return DefaultClock.zzgm;
    }
    
    @Override
    public long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }
    
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    @Override
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
    
    @Override
    public long nanoTime() {
        return System.nanoTime();
    }
}
