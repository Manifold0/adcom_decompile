// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzcm extends zzcy
{
    private final /* synthetic */ String zzcv;
    private final /* synthetic */ byte[] zzde;
    
    zzcm(final zzca zzca, final GoogleApiClient googleApiClient, final String zzcv, final byte[] zzde) {
        this.zzcv = zzcv;
        this.zzde = zzde;
        super(googleApiClient, null);
    }
}
