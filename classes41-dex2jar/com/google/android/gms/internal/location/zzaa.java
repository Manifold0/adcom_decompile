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

final class zzaa extends zzab
{
    private final /* synthetic */ PendingIntent zzbx;
    
    zzaa(final zzq zzq, final GoogleApiClient googleApiClient, final PendingIntent zzbx) {
        this.zzbx = zzbx;
        super(googleApiClient);
    }
}
