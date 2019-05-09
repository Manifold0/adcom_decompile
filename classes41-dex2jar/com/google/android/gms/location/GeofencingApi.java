// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import java.util.List;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface GeofencingApi
{
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    PendingResult<Status> addGeofences(final GoogleApiClient p0, final GeofencingRequest p1, final PendingIntent p2);
    
    @Deprecated
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    PendingResult<Status> addGeofences(final GoogleApiClient p0, final List<Geofence> p1, final PendingIntent p2);
    
    PendingResult<Status> removeGeofences(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> removeGeofences(final GoogleApiClient p0, final List<String> p1);
}
