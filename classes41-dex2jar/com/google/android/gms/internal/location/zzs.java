// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;

final class zzs extends zzab
{
    private final /* synthetic */ LocationCallback zzcm;
    
    zzs(final zzq zzq, final GoogleApiClient googleApiClient, final LocationCallback zzcm) {
        this.zzcm = zzcm;
        super(googleApiClient);
    }
}
