// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

final class zzf implements Runnable
{
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zze zzi;
    
    zzf(final zze zzi, final Task zzg) {
        this.zzi = zzi;
        this.zzg = zzg;
    }
    
    @Override
    public final void run() {
        Task task;
        try {
            task = this.zzi.zze.then(this.zzg);
            if (task == null) {
                this.zzi.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
        }
        catch (RuntimeExecutionException exception) {
            if (exception.getCause() instanceof Exception) {
                this.zzi.zzf.setException((Exception)exception.getCause());
                return;
            }
            this.zzi.zzf.setException(exception);
            return;
        }
        catch (Exception exception2) {
            this.zzi.zzf.setException(exception2);
            return;
        }
        task.addOnSuccessListener(TaskExecutors.zzw, this.zzi);
        task.addOnFailureListener(TaskExecutors.zzw, this.zzi);
        task.addOnCanceledListener(TaskExecutors.zzw, this.zzi);
    }
}
