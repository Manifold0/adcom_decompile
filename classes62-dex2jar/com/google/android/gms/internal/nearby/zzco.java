// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.connection.AdvertisingOptions;

final class zzco extends zzcw
{
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ AdvertisingOptions zzcp;
    private final /* synthetic */ ListenerHolder zzdf;
    
    zzco(final zzca zzca, final GoogleApiClient googleApiClient, final String val$name, final String zzcn, final ListenerHolder zzdf, final AdvertisingOptions zzcp) {
        this.val$name = val$name;
        this.zzcn = zzcn;
        this.zzdf = zzdf;
        this.zzcp = zzcp;
        super(googleApiClient, null);
    }
}
