// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.app.PendingIntent;
import com.google.android.gms.location.ActivityTransitionRequest;

final class zzh extends zzj
{
    private final /* synthetic */ ActivityTransitionRequest zzby;
    private final /* synthetic */ PendingIntent zzbz;
    
    zzh(final zze zze, final GoogleApiClient googleApiClient, final ActivityTransitionRequest zzby, final PendingIntent zzbz) {
        this.zzby = zzby;
        this.zzbz = zzbz;
        super(googleApiClient);
    }
}
