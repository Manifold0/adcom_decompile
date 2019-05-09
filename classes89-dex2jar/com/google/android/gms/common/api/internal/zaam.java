// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.Preconditions;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import java.lang.ref.WeakReference;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;

final class zaam implements BaseGmsClient$ConnectionProgressReportCallbacks
{
    private final Api<?> mApi;
    private final boolean zaec;
    private final WeakReference<zaak> zagk;
    
    public zaam(final zaak zaak, final Api<?> mApi, final boolean zaec) {
        this.zagk = new WeakReference<zaak>(zaak);
        this.mApi = mApi;
        this.zaec = zaec;
    }
    
    public final void onReportServiceBinding(@NonNull final ConnectionResult connectionResult) {
        boolean b = false;
        final zaak zaak = this.zagk.get();
        if (zaak == null) {
            return;
        }
        if (Looper.myLooper() == zaak.zaft.zaee.getLooper()) {
            b = true;
        }
        Preconditions.checkState(b, (Object)"onReportServiceBinding must be called on the GoogleApiClient handler thread");
        zaak.zaeo.lock();
        try {
            if (!com.google.android.gms.common.api.internal.zaak.zaa(zaak, 0)) {
                return;
            }
            if (!connectionResult.isSuccess()) {
                zaak.zab(connectionResult, this.mApi, this.zaec);
            }
            if (zaak.zaao()) {
                zaak.zaap();
            }
        }
        finally {
            zaak.zaeo.unlock();
        }
    }
}
