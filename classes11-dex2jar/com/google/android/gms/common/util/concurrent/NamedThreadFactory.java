// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ThreadFactory;

@KeepForSdk
public class NamedThreadFactory implements ThreadFactory
{
    private final String name;
    private final int priority;
    private final ThreadFactory zzhr;
    
    @KeepForSdk
    public NamedThreadFactory(final String s) {
        this(s, 0);
    }
    
    private NamedThreadFactory(final String s, final int n) {
        this.zzhr = Executors.defaultThreadFactory();
        this.name = Preconditions.checkNotNull(s, "Name must not be null");
        this.priority = 0;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = this.zzhr.newThread(new zza(runnable, 0));
        thread.setName(this.name);
        return thread;
    }
}
