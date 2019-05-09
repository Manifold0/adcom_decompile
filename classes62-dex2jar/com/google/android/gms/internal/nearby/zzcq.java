// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.connection.DiscoveryOptions;

final class zzcq extends zzcy
{
    private final /* synthetic */ String zzcn;
    private final /* synthetic */ DiscoveryOptions zzcr;
    private final /* synthetic */ ListenerHolder zzdg;
    
    zzcq(final zzca zzca, final GoogleApiClient googleApiClient, final String zzcn, final ListenerHolder zzdg, final DiscoveryOptions zzcr) {
        this.zzcn = zzcn;
        this.zzdg = zzdg;
        this.zzcr = zzcr;
        super(googleApiClient, null);
    }
}
