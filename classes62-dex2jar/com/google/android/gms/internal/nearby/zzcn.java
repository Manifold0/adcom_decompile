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
import java.util.List;

final class zzcn extends zzcy
{
    private final /* synthetic */ List zzcw;
    private final /* synthetic */ byte[] zzde;
    
    zzcn(final zzca zzca, final GoogleApiClient googleApiClient, final List zzcw, final byte[] zzde) {
        this.zzcw = zzcw;
        this.zzde = zzde;
        super(googleApiClient, null);
    }
}
