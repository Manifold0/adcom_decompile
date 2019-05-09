// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;

final class zzba extends zzav
{
    private final /* synthetic */ DriveId zzel;
    private final /* synthetic */ int zzem;
    
    zzba(final zzaw zzaw, final GoogleApiClient googleApiClient, final DriveId zzel, final int n) {
        this.zzel = zzel;
        this.zzem = 1;
        super(googleApiClient);
    }
}
