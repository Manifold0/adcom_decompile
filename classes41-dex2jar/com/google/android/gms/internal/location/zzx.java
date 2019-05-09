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
import android.os.Looper;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

final class zzx extends zzab
{
    private final /* synthetic */ LocationRequest zzck;
    private final /* synthetic */ LocationCallback zzcm;
    private final /* synthetic */ Looper zzcp;
    
    zzx(final zzq zzq, final GoogleApiClient googleApiClient, final LocationRequest zzck, final LocationCallback zzcm, final Looper zzcp) {
        this.zzck = zzck;
        this.zzcm = zzcm;
        this.zzcp = zzcp;
        super(googleApiClient);
    }
}
