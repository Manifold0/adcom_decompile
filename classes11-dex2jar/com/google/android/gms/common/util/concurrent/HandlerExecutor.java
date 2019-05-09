// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util.concurrent;

import android.support.annotation.NonNull;
import com.google.android.gms.internal.common.zze;
import android.os.Looper;
import android.os.Handler;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.Executor;

@KeepForSdk
public class HandlerExecutor implements Executor
{
    private final Handler handler;
    
    @KeepForSdk
    public HandlerExecutor(final Looper looper) {
        this.handler = new zze(looper);
    }
    
    @Override
    public void execute(@NonNull final Runnable runnable) {
        this.handler.post(runnable);
    }
}
