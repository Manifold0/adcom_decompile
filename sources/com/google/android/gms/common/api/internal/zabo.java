package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.GoogleApiManager.zaa;
import java.util.Collections;

final class zabo implements Runnable {
    private final /* synthetic */ ConnectionResult zaiz;
    private final /* synthetic */ zac zajg;

    zabo(zac zac, ConnectionResult connectionResult) {
        this.zajg = zac;
        this.zaiz = connectionResult;
    }

    public final void run() {
        if (this.zaiz.isSuccess()) {
            this.zajg.zajf = true;
            if (this.zajg.zaio.requiresSignIn()) {
                this.zajg.zabr();
                return;
            }
            try {
                this.zajg.zaio.getRemoteService(null, Collections.emptySet());
                return;
            } catch (Throwable e) {
                Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                ((zaa) this.zajg.zaim.zaii.get(this.zajg.zafq)).onConnectionFailed(new ConnectionResult(10));
                return;
            }
        }
        ((zaa) this.zajg.zaim.zaii.get(this.zajg.zafq)).onConnectionFailed(this.zaiz);
    }
}
