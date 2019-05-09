package com.google.android.gms.internal.drive;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@SuppressLint({"MissingRemoteException"})
final class zzat extends zzav {
    zzat(GoogleApiClient googleApiClient, Status status) {
        super(googleApiClient);
        setResult(status);
    }

    protected final /* bridge */ /* synthetic */ void doExecute(AnyClient anyClient) throws RemoteException {
    }
}
