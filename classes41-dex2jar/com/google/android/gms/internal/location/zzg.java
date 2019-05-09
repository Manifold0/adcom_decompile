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

final class zzg extends zzj
{
    private final /* synthetic */ PendingIntent zzbx;
    
    zzg(final zze zze, final GoogleApiClient googleApiClient, final PendingIntent zzbx) {
        this.zzbx = zzbx;
        super(googleApiClient);
    }
}
