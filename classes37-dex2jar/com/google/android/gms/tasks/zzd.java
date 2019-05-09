// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

final class zzd implements Runnable
{
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzc zzh;
    
    zzd(final zzc zzh, final Task zzg) {
        this.zzh = zzh;
        this.zzg = zzg;
    }
    
    @Override
    public final void run() {
        if (this.zzg.isCanceled()) {
            this.zzh.zzf.zza();
            return;
        }
        try {
            this.zzh.zzf.setResult(this.zzh.zze.then(this.zzg));
        }
        catch (RuntimeExecutionException exception) {
            if (exception.getCause() instanceof Exception) {
                this.zzh.zzf.setException((Exception)exception.getCause());
                return;
            }
            this.zzh.zzf.setException(exception);
        }
        catch (Exception exception2) {
            this.zzh.zzf.setException(exception2);
        }
    }
}
