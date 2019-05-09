package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.drive.Drive;

public abstract class zzau<R extends Result> extends ApiMethodImpl<R, zzaw> {
    public zzau(GoogleApiClient googleApiClient) {
        super(Drive.CLIENT_KEY, googleApiClient);
    }
}
