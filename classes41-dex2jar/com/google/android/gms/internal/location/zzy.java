// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import android.app.PendingIntent;

final class zzy extends zzab
{
    private final /* synthetic */ PendingIntent zzbx;
    private final /* synthetic */ LocationRequest zzck;
    
    zzy(final zzq zzq, final GoogleApiClient googleApiClient, final LocationRequest zzck, final PendingIntent zzbx) {
        this.zzck = zzck;
        this.zzbx = zzbx;
        super(googleApiClient);
    }
}
