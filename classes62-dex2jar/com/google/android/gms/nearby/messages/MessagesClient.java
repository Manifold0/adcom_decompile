// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import android.app.PendingIntent;
import com.google.android.gms.tasks.Task;
import android.support.annotation.NonNull;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.GoogleApi$Settings;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApi;

public abstract class MessagesClient extends GoogleApi<MessagesOptions>
{
    protected MessagesClient(final Activity activity, final Api<MessagesOptions> api, @Nullable final MessagesOptions messagesOptions, final GoogleApi$Settings googleApi$Settings) {
        super(activity, (Api)api, (Api$ApiOptions)messagesOptions, googleApi$Settings);
    }
    
    protected MessagesClient(final Context context, final Api<MessagesOptions> api, @Nullable final MessagesOptions messagesOptions, final GoogleApi$Settings googleApi$Settings) {
        super(context, (Api)api, (Api$ApiOptions)messagesOptions, googleApi$Settings);
    }
    
    public abstract void handleIntent(@NonNull final Intent p0, @NonNull final MessageListener p1);
    
    public abstract Task<Void> publish(@NonNull final Message p0);
    
    public abstract Task<Void> publish(@NonNull final Message p0, @NonNull final PublishOptions p1);
    
    public abstract Task<Void> registerStatusCallback(@NonNull final StatusCallback p0);
    
    public abstract Task<Void> subscribe(@NonNull final PendingIntent p0);
    
    public abstract Task<Void> subscribe(@NonNull final PendingIntent p0, @NonNull final SubscribeOptions p1);
    
    public abstract Task<Void> subscribe(@NonNull final MessageListener p0);
    
    public abstract Task<Void> subscribe(@NonNull final MessageListener p0, @NonNull final SubscribeOptions p1);
    
    public abstract Task<Void> unpublish(@NonNull final Message p0);
    
    public abstract Task<Void> unregisterStatusCallback(@NonNull final StatusCallback p0);
    
    public abstract Task<Void> unsubscribe(@NonNull final PendingIntent p0);
    
    public abstract Task<Void> unsubscribe(@NonNull final MessageListener p0);
}
