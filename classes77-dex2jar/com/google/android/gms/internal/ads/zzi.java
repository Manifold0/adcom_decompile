// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class zzi implements zzaa
{
    private final Executor zzv;
    
    public zzi(final Handler handler) {
        this.zzv = new zzj(this, handler);
    }
    
    @Override
    public final void zza(final zzr<?> zzr, final zzae zzae) {
        zzr.zzb("post-error");
        this.zzv.execute(new zzk(this, zzr, zzx.zzc(zzae), null));
    }
    
    @Override
    public final void zza(final zzr<?> zzr, final zzx<?> zzx, final Runnable runnable) {
        zzr.zzk();
        zzr.zzb("post-response");
        this.zzv.execute(new zzk(this, zzr, zzx, runnable));
    }
    
    @Override
    public final void zzb(final zzr<?> zzr, final zzx<?> zzx) {
        this.zza(zzr, zzx, null);
    }
}
