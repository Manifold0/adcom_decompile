// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class zzakj implements ThreadFactory
{
    private final AtomicInteger zzcnz;
    private final /* synthetic */ String zzcrl;
    
    zzakj(final String zzcrl) {
        this.zzcrl = zzcrl;
        this.zzcnz = new AtomicInteger(1);
    }
    
    @Override
    public final Thread newThread(final Runnable runnable) {
        final String zzcrl = this.zzcrl;
        return new Thread(runnable, new StringBuilder(String.valueOf(zzcrl).length() + 23).append("AdWorker(").append(zzcrl).append(") #").append(this.zzcnz.getAndIncrement()).toString());
    }
}
