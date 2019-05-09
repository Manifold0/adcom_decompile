// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;

final class zzct extends zzcy
{
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ ListenerHolder zzdh;
    
    zzct(final zzca zzca, final GoogleApiClient googleApiClient, final String zzcv, final ListenerHolder zzdh) {
        this.zzcv = zzcv;
        this.zzdh = zzdh;
        super(googleApiClient, null);
    }
}
