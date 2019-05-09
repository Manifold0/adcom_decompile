// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import android.location.Location;

final class zzu extends zzab
{
    private final /* synthetic */ Location zzco;
    
    zzu(final zzq zzq, final GoogleApiClient googleApiClient, final Location zzco) {
        this.zzco = zzco;
        super(googleApiClient);
    }
}
