// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import java.util.List;
import com.google.android.gms.tasks.Task;
import android.support.annotation.NonNull;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.GoogleApi$Settings;
import com.google.android.gms.common.api.Api;
import android.app.Activity;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;

public abstract class ConnectionsClient extends GoogleApi<Api$ApiOptions$NoOptions>
{
    public static int MAX_BYTES_DATA_SIZE;
    
    static {
        ConnectionsClient.MAX_BYTES_DATA_SIZE = 32768;
    }
    
    protected ConnectionsClient(final Activity activity, final Api<Api$ApiOptions$NoOptions> api, final GoogleApi$Settings googleApi$Settings) {
        super(activity, (Api)api, (Api$ApiOptions)null, googleApi$Settings);
    }
    
    protected ConnectionsClient(final Context context, final Api<Api$ApiOptions$NoOptions> api, final GoogleApi$Settings googleApi$Settings) {
        super(context, (Api)api, (Api$ApiOptions)null, googleApi$Settings);
    }
    
    public abstract Task<Void> acceptConnection(@NonNull final String p0, @NonNull final PayloadCallback p1);
    
    public abstract Task<Void> cancelPayload(final long p0);
    
    public abstract void disconnectFromEndpoint(@NonNull final String p0);
    
    public abstract Task<Void> rejectConnection(@NonNull final String p0);
    
    public abstract Task<Void> requestConnection(@NonNull final String p0, @NonNull final String p1, @NonNull final ConnectionLifecycleCallback p2);
    
    public abstract Task<Void> sendPayload(@NonNull final String p0, @NonNull final Payload p1);
    
    public abstract Task<Void> sendPayload(@NonNull final List<String> p0, @NonNull final Payload p1);
    
    public abstract Task<Void> startAdvertising(@NonNull final String p0, @NonNull final String p1, @NonNull final ConnectionLifecycleCallback p2, @NonNull final AdvertisingOptions p3);
    
    public abstract Task<Void> startDiscovery(@NonNull final String p0, @NonNull final EndpointDiscoveryCallback p1, @NonNull final DiscoveryOptions p2);
    
    public abstract void stopAdvertising();
    
    public abstract void stopAllEndpoints();
    
    public abstract void stopDiscovery();
}
