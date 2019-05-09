// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util.concurrent;

import android.os.Process;

final class zza implements Runnable
{
    private final int priority;
    private final Runnable zzhu;
    
    public zza(final Runnable zzhu, final int priority) {
        this.zzhu = zzhu;
        this.priority = priority;
    }
    
    @Override
    public final void run() {
        Process.setThreadPriority(this.priority);
        this.zzhu.run();
    }
}
