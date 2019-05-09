// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationServices;

final class zzbl extends zza<LocationSettingsResult>
{
    private final /* synthetic */ LocationSettingsRequest zzdp;
    private final /* synthetic */ String zzdq;
    
    zzbl(final zzbk zzbk, final GoogleApiClient googleApiClient, final LocationSettingsRequest zzdp, final String s) {
        this.zzdp = zzdp;
        this.zzdq = null;
        super(googleApiClient);
    }
}
