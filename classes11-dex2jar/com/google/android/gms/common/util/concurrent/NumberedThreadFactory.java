// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ThreadFactory;

@KeepForSdk
public class NumberedThreadFactory implements ThreadFactory
{
    private final int priority;
    private final ThreadFactory zzhr;
    private final String zzhs;
    private final AtomicInteger zzht;
    
    @KeepForSdk
    public NumberedThreadFactory(final String s) {
        this(s, 0);
    }
    
    private NumberedThreadFactory(final String s, final int n) {
        this.zzht = new AtomicInteger();
        this.zzhr = Executors.defaultThreadFactory();
        this.zzhs = Preconditions.checkNotNull(s, "Name must not be null");
        this.priority = 0;
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = this.zzhr.newThread(new zza(runnable, 0));
        final String zzhs = this.zzhs;
        thread.setName(new StringBuilder(String.valueOf(zzhs).length() + 13).append(zzhs).append("[").append(this.zzht.getAndIncrement()).append("]").toString());
        return thread;
    }
}
