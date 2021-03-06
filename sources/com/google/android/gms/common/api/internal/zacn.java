package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zacn implements Runnable {
    private final /* synthetic */ Result zakv;
    private final /* synthetic */ zacm zakw;

    zacn(zacm zacm, Result result) {
        this.zakw = zacm;
        this.zakv = result;
    }

    @WorkerThread
    public final void run() {
        GoogleApiClient googleApiClient;
        try {
            BasePendingResult.zadn.set(Boolean.valueOf(true));
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(0, this.zakw.zako.onSuccess(this.zakv)));
            BasePendingResult.zadn.set(Boolean.valueOf(false));
            zacm.zab(this.zakv);
            googleApiClient = (GoogleApiClient) this.zakw.zadq.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakw);
            }
        } catch (RuntimeException e) {
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(1, e));
            BasePendingResult.zadn.set(Boolean.valueOf(false));
            zacm.zab(this.zakv);
            googleApiClient = (GoogleApiClient) this.zakw.zadq.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakw);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            BasePendingResult.zadn.set(Boolean.valueOf(false));
            zacm.zab(this.zakv);
            googleApiClient = (GoogleApiClient) this.zakw.zadq.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakw);
            }
        }
    }
}
