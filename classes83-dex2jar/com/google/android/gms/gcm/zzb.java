// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.support.annotation.NonNull;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class zzb implements ThreadFactory
{
    private final AtomicInteger zzhzm;
    
    zzb(final GcmTaskService gcmTaskService) {
        this.zzhzm = new AtomicInteger(1);
    }
    
    @Override
    public final Thread newThread(@NonNull final Runnable runnable) {
        final Thread thread = new Thread(runnable, new StringBuilder(20).append("gcm-task#").append(this.zzhzm.getAndIncrement()).toString());
        thread.setPriority(4);
        return thread;
    }
}
