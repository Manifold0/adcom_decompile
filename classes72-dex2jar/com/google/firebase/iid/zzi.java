// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;

final class zzi
{
    private static final Executor zzad;
    
    static {
        zzad = zzk.zzaf;
    }
    
    static Executor zze() {
        return zzi.zzad;
    }
    
    static Executor zzf() {
        return new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), zzj.zzae);
    }
}
