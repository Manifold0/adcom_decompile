// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzbn extends zzbv
{
    private final /* synthetic */ ListenerHolder zzco;
    private final /* synthetic */ zzbw zzio;
    private final /* synthetic */ SubscribeOptions zzip;
    
    zzbn(final zzbi zzbi, final GoogleApiClient googleApiClient, final ListenerHolder zzco, final zzbw zzio, final SubscribeOptions zzip) {
        this.zzco = zzco;
        this.zzio = zzio;
        this.zzip = zzip;
        super(googleApiClient);
    }
}
