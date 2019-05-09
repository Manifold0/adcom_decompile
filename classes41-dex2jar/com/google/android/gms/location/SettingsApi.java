// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface SettingsApi
{
    PendingResult<LocationSettingsResult> checkLocationSettings(final GoogleApiClient p0, final LocationSettingsRequest p1);
}
