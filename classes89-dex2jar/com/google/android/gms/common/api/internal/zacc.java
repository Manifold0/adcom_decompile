// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import com.google.android.gms.common.util.concurrent.NumberedThreadFactory;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

public final class zacc
{
    private static final ExecutorService zahw;
    
    static {
        zahw = new ThreadPoolExecutor(0, 4, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), (ThreadFactory)new NumberedThreadFactory("GAC_Transform"));
    }
    
    public static ExecutorService zabb() {
        return zacc.zahw;
    }
}
