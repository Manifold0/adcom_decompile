// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface ActivityRecognitionApi
{
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    PendingResult<Status> removeActivityUpdates(final GoogleApiClient p0, final PendingIntent p1);
    
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    PendingResult<Status> requestActivityUpdates(final GoogleApiClient p0, final long p1, final PendingIntent p2);
    
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    PendingResult<Status> zza(final GoogleApiClient p0, final PendingIntent p1);
    
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    PendingResult<Status> zza(final GoogleApiClient p0, final ActivityTransitionRequest p1, final PendingIntent p2);
}
