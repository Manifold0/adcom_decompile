// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzv implements Runnable
{
    private final /* synthetic */ Callable val$callable;
    private final /* synthetic */ zzu zzad;
    
    zzv(final zzu zzad, final Callable val$callable) {
        this.zzad = zzad;
        this.val$callable = val$callable;
    }
    
    @Override
    public final void run() {
        try {
            this.zzad.setResult(this.val$callable.call());
        }
        catch (Exception exception) {
            this.zzad.setException(exception);
        }
    }
}
