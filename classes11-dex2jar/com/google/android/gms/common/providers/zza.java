// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.providers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class zza implements PooledExecutorFactory
{
    @Override
    public final ScheduledExecutorService newSingleThreadScheduledExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
