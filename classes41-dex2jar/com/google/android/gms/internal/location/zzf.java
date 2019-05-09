// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.app.PendingIntent;

final class zzf extends zzj
{
    private final /* synthetic */ long zzbw;
    private final /* synthetic */ PendingIntent zzbx;
    
    zzf(final zze zze, final GoogleApiClient googleApiClient, final long zzbw, final PendingIntent zzbx) {
        this.zzbw = zzbw;
        this.zzbx = zzbx;
        super(googleApiClient);
    }
}
