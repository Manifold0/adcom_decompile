// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

final class zzr extends zzab
{
    private final /* synthetic */ LocationRequest zzck;
    private final /* synthetic */ LocationListener zzcl;
    
    zzr(final zzq zzq, final GoogleApiClient googleApiClient, final LocationRequest zzck, final LocationListener zzcl) {
        this.zzck = zzck;
        this.zzcl = zzcl;
        super(googleApiClient);
    }
}
