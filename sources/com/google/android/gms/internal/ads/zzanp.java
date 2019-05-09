package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanp implements Runnable {
    private final zzanl zzcvj;
    private final zzanz zzcvk;

    zzanp(zzanl zzanl, zzanz zzanz) {
        this.zzcvj = zzanl;
        this.zzcvk = zzanz;
    }

    public final void run() {
        Throwable cause;
        zzanl zzanl = this.zzcvj;
        try {
            zzanl.zzh(this.zzcvk.get());
            return;
        } catch (ExecutionException e) {
            cause = e.getCause();
        } catch (InterruptedException e2) {
            cause = e2;
            Thread.currentThread().interrupt();
        } catch (Exception e3) {
            cause = e3;
        }
        zzanl.zzb(cause);
    }
}
