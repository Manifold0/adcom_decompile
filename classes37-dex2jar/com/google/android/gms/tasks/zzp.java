// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

final class zzp implements Runnable
{
    private final /* synthetic */ Task zzg;
    private final /* synthetic */ zzo zzs;
    
    zzp(final zzo zzs, final Task zzg) {
        this.zzs = zzs;
        this.zzg = zzg;
    }
    
    @Override
    public final void run() {
        Task<Object> then;
        try {
            then = this.zzs.zzr.then(this.zzg.getResult());
            if (then == null) {
                this.zzs.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
        }
        catch (RuntimeExecutionException ex) {
            if (ex.getCause() instanceof Exception) {
                this.zzs.onFailure((Exception)ex.getCause());
                return;
            }
            this.zzs.onFailure(ex);
            return;
        }
        catch (CancellationException ex3) {
            this.zzs.onCanceled();
            return;
        }
        catch (Exception ex2) {
            this.zzs.onFailure(ex2);
            return;
        }
        then.addOnSuccessListener(TaskExecutors.zzw, this.zzs);
        then.addOnFailureListener(TaskExecutors.zzw, this.zzs);
        then.addOnCanceledListener(TaskExecutors.zzw, this.zzs);
    }
}
