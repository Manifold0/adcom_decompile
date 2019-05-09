// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.app.PendingIntent;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzbo extends zzbv
{
    private final /* synthetic */ zzbw zzio;
    private final /* synthetic */ SubscribeOptions zzip;
    private final /* synthetic */ PendingIntent zziq;
    
    zzbo(final zzbi zzbi, final GoogleApiClient googleApiClient, final PendingIntent zziq, final zzbw zzio, final SubscribeOptions zzip) {
        this.zziq = zziq;
        this.zzio = zzio;
        this.zzip = zzip;
        super(googleApiClient);
    }
}
