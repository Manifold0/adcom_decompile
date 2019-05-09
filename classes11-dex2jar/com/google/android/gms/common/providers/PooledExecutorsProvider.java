// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.providers;

import java.util.concurrent.ScheduledExecutorService;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class PooledExecutorsProvider
{
    private static PooledExecutorFactory zzey;
    
    private PooledExecutorsProvider() {
    }
    
    @KeepForSdk
    public static PooledExecutorFactory getInstance() {
        synchronized (PooledExecutorsProvider.class) {
            if (PooledExecutorsProvider.zzey == null) {
                PooledExecutorsProvider.zzey = (PooledExecutorFactory)new zza();
            }
            return PooledExecutorsProvider.zzey;
        }
    }
    
    public interface PooledExecutorFactory
    {
        @KeepForSdk
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }
}
