// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

final class zzj implements Executor
{
    private final /* synthetic */ Handler val$handler;
    
    zzj(final zzi zzi, final Handler val$handler) {
        this.val$handler = val$handler;
    }
    
    @Override
    public final void execute(final Runnable runnable) {
        this.val$handler.post(runnable);
    }
}
