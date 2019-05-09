// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import android.app.PendingIntent;
import android.content.Intent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface Messages
{
    @Deprecated
    PendingResult<Status> getPermissionStatus(final GoogleApiClient p0);
    
    void handleIntent(final Intent p0, final MessageListener p1);
    
    PendingResult<Status> publish(final GoogleApiClient p0, final Message p1);
    
    PendingResult<Status> publish(final GoogleApiClient p0, final Message p1, final PublishOptions p2);
    
    PendingResult<Status> registerStatusCallback(final GoogleApiClient p0, final StatusCallback p1);
    
    PendingResult<Status> subscribe(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> subscribe(final GoogleApiClient p0, final PendingIntent p1, final SubscribeOptions p2);
    
    PendingResult<Status> subscribe(final GoogleApiClient p0, final MessageListener p1);
    
    PendingResult<Status> subscribe(final GoogleApiClient p0, final MessageListener p1, final SubscribeOptions p2);
    
    PendingResult<Status> unpublish(final GoogleApiClient p0, final Message p1);
    
    PendingResult<Status> unregisterStatusCallback(final GoogleApiClient p0, final StatusCallback p1);
    
    PendingResult<Status> unsubscribe(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> unsubscribe(final GoogleApiClient p0, final MessageListener p1);
}
