package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanq implements Runnable {
    private final zzaoj zzbnu;
    private final zzank zzcvl;
    private final zzanz zzcvm;

    zzanq(zzaoj zzaoj, zzank zzank, zzanz zzanz) {
        this.zzbnu = zzaoj;
        this.zzcvl = zzank;
        this.zzcvm = zzanz;
    }

    public final void run() {
        Throwable e;
        zzaoj zzaoj = this.zzbnu;
        try {
            zzaoj.set(this.zzcvl.apply(this.zzcvm.get()));
        } catch (CancellationException e2) {
            zzaoj.cancel(true);
        } catch (ExecutionException e3) {
            e = e3;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            zzaoj.setException(e);
        } catch (Throwable e4) {
            Thread.currentThread().interrupt();
            zzaoj.setException(e4);
        } catch (Throwable e42) {
            zzaoj.setException(e42);
        }
    }
}
