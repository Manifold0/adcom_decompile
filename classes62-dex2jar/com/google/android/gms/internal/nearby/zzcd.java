// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;
import com.google.android.gms.nearby.connection.Payload;

final class zzcd extends zzcy
{
    private final /* synthetic */ Payload zzbx;
    private final /* synthetic */ List zzcw;
    
    zzcd(final zzca zzca, final GoogleApiClient googleApiClient, final List zzcw, final Payload zzbx) {
        this.zzcw = zzcw;
        this.zzbx = zzbx;
        super(googleApiClient, null);
    }
}
