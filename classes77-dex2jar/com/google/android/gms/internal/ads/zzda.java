// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class zzda implements ThreadFactory
{
    private final ThreadFactory zzsk;
    
    zzda() {
        this.zzsk = Executors.defaultThreadFactory();
    }
    
    @Override
    public final Thread newThread(final Runnable runnable) {
        final Thread thread = this.zzsk.newThread(runnable);
        thread.setName(String.valueOf(thread.getName()).concat(":"));
        return thread;
    }
}
