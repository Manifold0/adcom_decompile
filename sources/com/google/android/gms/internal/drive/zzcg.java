package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi.FileUploadPreferencesResult;

abstract class zzcg extends zzau<FileUploadPreferencesResult> {
    private final /* synthetic */ zzcb zzfi;

    public zzcg(zzcb zzcb, GoogleApiClient googleApiClient) {
        this.zzfi = zzcb;
        super(googleApiClient);
    }

    protected /* synthetic */ Result createFailedResult(Status status) {
        return new zzcf(this.zzfi, status, null, null);
    }
}
