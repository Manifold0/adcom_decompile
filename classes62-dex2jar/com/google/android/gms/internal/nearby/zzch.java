// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.Strategy;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzch extends zzcw
{
    private final /* synthetic */ String val$name;
    private final /* synthetic */ long zzcy;
    private final /* synthetic */ ListenerHolder zzcz;
    
    zzch(final zzca zzca, final GoogleApiClient googleApiClient, final String val$name, final long zzcy, final ListenerHolder zzcz) {
        this.val$name = val$name;
        this.zzcy = zzcy;
        this.zzcz = zzcz;
        super(googleApiClient, null);
    }
}
