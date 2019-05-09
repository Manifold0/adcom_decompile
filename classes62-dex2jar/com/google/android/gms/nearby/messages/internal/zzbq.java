// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.app.PendingIntent;

final class zzbq extends zzbv
{
    private final /* synthetic */ PendingIntent zziq;
    
    zzbq(final zzbi zzbi, final GoogleApiClient googleApiClient, final PendingIntent zziq) {
        this.zziq = zziq;
        super(googleApiClient);
    }
}
