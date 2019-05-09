// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.GeofencingRequest;
import android.app.PendingIntent;

final class zzag extends zzai
{
    private final /* synthetic */ PendingIntent zzbz;
    private final /* synthetic */ GeofencingRequest zzcs;
    
    zzag(final zzaf zzaf, final GoogleApiClient googleApiClient, final GeofencingRequest zzcs, final PendingIntent zzbz) {
        this.zzcs = zzcs;
        this.zzbz = zzbz;
        super(googleApiClient);
    }
}
