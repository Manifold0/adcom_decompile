// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import android.annotation.SuppressLint;

@SuppressLint({ "MissingRemoteException" })
final class zzat extends zzav
{
    zzat(final GoogleApiClient googleApiClient, final Status result) {
        super(googleApiClient);
        this.setResult((Result)result);
    }
}
