// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.Looper;
import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import android.location.Location;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface FusedLocationProviderApi
{
    @Deprecated
    public static final String KEY_LOCATION_CHANGED = "com.google.android.location.LOCATION";
    public static final String KEY_MOCK_LOCATION = "mockLocation";
    
    PendingResult<Status> flushLocations(final GoogleApiClient p0);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    Location getLastLocation(final GoogleApiClient p0);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    LocationAvailability getLocationAvailability(final GoogleApiClient p0);
    
    PendingResult<Status> removeLocationUpdates(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> removeLocationUpdates(final GoogleApiClient p0, final LocationCallback p1);
    
    PendingResult<Status> removeLocationUpdates(final GoogleApiClient p0, final LocationListener p1);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    PendingResult<Status> requestLocationUpdates(final GoogleApiClient p0, final LocationRequest p1, final PendingIntent p2);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    PendingResult<Status> requestLocationUpdates(final GoogleApiClient p0, final LocationRequest p1, final LocationCallback p2, final Looper p3);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    PendingResult<Status> requestLocationUpdates(final GoogleApiClient p0, final LocationRequest p1, final LocationListener p2);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    PendingResult<Status> requestLocationUpdates(final GoogleApiClient p0, final LocationRequest p1, final LocationListener p2, final Looper p3);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    PendingResult<Status> setMockLocation(final GoogleApiClient p0, final Location p1);
    
    @RequiresPermission(anyOf = { "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION" })
    PendingResult<Status> setMockMode(final GoogleApiClient p0, final boolean p1);
}
