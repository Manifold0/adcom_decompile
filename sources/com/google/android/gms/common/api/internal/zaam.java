package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

final class zaam implements ConnectionProgressReportCallbacks {
    private final Api<?> mApi;
    private final boolean zaec;
    private final WeakReference<zaak> zagk;

    public zaam(zaak zaak, Api<?> api, boolean z) {
        this.zagk = new WeakReference(zaak);
        this.mApi = api;
        this.zaec = z;
    }

    public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
        boolean z = false;
        zaak zaak = (zaak) this.zagk.get();
        if (zaak != null) {
            if (Looper.myLooper() == zaak.zaft.zaee.getLooper()) {
                z = true;
            }
            Preconditions.checkState(z, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaak.zaeo.lock();
            try {
                if (zaak.zac(0)) {
                    if (!connectionResult.isSuccess()) {
                        zaak.zab(connectionResult, this.mApi, this.zaec);
                    }
                    if (zaak.zaao()) {
                        zaak.zaap();
                    }
                    zaak.zaeo.unlock();
                }
            } finally {
                zaak.zaeo.unlock();
            }
        }
    }
}
