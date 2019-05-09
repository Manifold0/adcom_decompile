package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzk extends zzo<Status> {
    zzk(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
        zzg zzg = (zzg) anyClient;
        ((zzu) zzg.getService()).zzd(new zzl(this), zzg.zzg());
    }

    protected final /* synthetic */ Result createFailedResult(Status status) {
        return status;
    }
}
