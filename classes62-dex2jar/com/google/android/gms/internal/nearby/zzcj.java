// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzcj extends zzcy
{
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ byte[] zzdb;
    private final /* synthetic */ ListenerHolder zzdc;
    private final /* synthetic */ ListenerHolder zzdd;
    
    zzcj(final zzca zzca, final GoogleApiClient googleApiClient, final String val$name, final String zzcv, final byte[] zzdb, final ListenerHolder zzdc, final ListenerHolder zzdd) {
        this.val$name = val$name;
        this.zzcv = zzcv;
        this.zzdb = zzdb;
        this.zzdc = zzdc;
        this.zzdd = zzdd;
        super(googleApiClient, null);
    }
}
