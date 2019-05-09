// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;

public class ActivityRecognitionClient extends GoogleApi<Api$ApiOptions$NoOptions>
{
    public ActivityRecognitionClient(@NonNull final Activity activity) {
        super(activity, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    public ActivityRecognitionClient(@NonNull final Context context) {
        super(context, (Api)LocationServices.API, (Api$ApiOptions)null, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> removeActivityTransitionUpdates(final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)ActivityRecognition.ActivityRecognitionApi.zza(this.asGoogleApiClient(), pendingIntent));
    }
    
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> removeActivityUpdates(final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(this.asGoogleApiClient(), pendingIntent));
    }
    
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> requestActivityTransitionUpdates(final ActivityTransitionRequest activityTransitionRequest, final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)ActivityRecognition.ActivityRecognitionApi.zza(this.asGoogleApiClient(), activityTransitionRequest, pendingIntent));
    }
    
    @RequiresPermission("com.google.android.gms.permission.ACTIVITY_RECOGNITION")
    public Task<Void> requestActivityUpdates(final long n, final PendingIntent pendingIntent) {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(this.asGoogleApiClient(), n, pendingIntent));
    }
}
