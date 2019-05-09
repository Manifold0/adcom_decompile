// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.Response;

public class LocationSettingsResponse extends Response<LocationSettingsResult>
{
    public LocationSettingsStates getLocationSettingsStates() {
        return ((LocationSettingsResult)this.getResult()).getLocationSettingsStates();
    }
}
