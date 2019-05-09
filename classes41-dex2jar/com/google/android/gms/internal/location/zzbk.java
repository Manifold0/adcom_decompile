// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.SettingsApi;

public final class zzbk implements SettingsApi
{
    @Override
    public final PendingResult<LocationSettingsResult> checkLocationSettings(final GoogleApiClient googleApiClient, final LocationSettingsRequest locationSettingsRequest) {
        return (PendingResult<LocationSettingsResult>)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzbl(this, googleApiClient, locationSettingsRequest, null));
    }
}
