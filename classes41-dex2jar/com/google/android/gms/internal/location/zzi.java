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

final class zzi extends zzj
{
    private final /* synthetic */ PendingIntent zzbz;
    
    zzi(final zze zze, final GoogleApiClient googleApiClient, final PendingIntent zzbz) {
        this.zzbz = zzbz;
        super(googleApiClient);
    }
}
