// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import com.google.android.gms.common.api.Result;
import java.util.List;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public interface Connections
{
    @Deprecated
    public static final long DURATION_INDEFINITE = 0L;
    public static final int MAX_BYTES_DATA_SIZE = 32768;
    @Deprecated
    public static final int MAX_RELIABLE_MESSAGE_LEN = 4096;
    @Deprecated
    public static final int MAX_UNRELIABLE_MESSAGE_LEN = 1168;
    
    PendingResult<Status> acceptConnection(final GoogleApiClient p0, final String p1, final PayloadCallback p2);
    
    @Deprecated
    PendingResult<Status> acceptConnectionRequest(final GoogleApiClient p0, final String p1, final byte[] p2, final MessageListener p3);
    
    PendingResult<Status> cancelPayload(final GoogleApiClient p0, final long p1);
    
    void disconnectFromEndpoint(final GoogleApiClient p0, final String p1);
    
    PendingResult<Status> rejectConnection(final GoogleApiClient p0, final String p1);
    
    @Deprecated
    PendingResult<Status> rejectConnectionRequest(final GoogleApiClient p0, final String p1);
    
    PendingResult<Status> requestConnection(final GoogleApiClient p0, @Nullable final String p1, final String p2, final ConnectionLifecycleCallback p3);
    
    @Deprecated
    PendingResult<Status> sendConnectionRequest(final GoogleApiClient p0, final String p1, final String p2, final byte[] p3, final ConnectionResponseCallback p4, final MessageListener p5);
    
    PendingResult<Status> sendPayload(final GoogleApiClient p0, final String p1, final Payload p2);
    
    PendingResult<Status> sendPayload(final GoogleApiClient p0, final List<String> p1, final Payload p2);
    
    @Deprecated
    void sendReliableMessage(final GoogleApiClient p0, final String p1, final byte[] p2);
    
    @Deprecated
    void sendReliableMessage(final GoogleApiClient p0, final List<String> p1, final byte[] p2);
    
    @Deprecated
    void sendUnreliableMessage(final GoogleApiClient p0, final String p1, final byte[] p2);
    
    @Deprecated
    void sendUnreliableMessage(final GoogleApiClient p0, final List<String> p1, final byte[] p2);
    
    @Deprecated
    PendingResult<StartAdvertisingResult> startAdvertising(final GoogleApiClient p0, final String p1, final AppMetadata p2, final long p3, final ConnectionRequestListener p4);
    
    PendingResult<StartAdvertisingResult> startAdvertising(final GoogleApiClient p0, final String p1, final String p2, final ConnectionLifecycleCallback p3, final AdvertisingOptions p4);
    
    @Deprecated
    PendingResult<Status> startDiscovery(final GoogleApiClient p0, final String p1, final long p2, final EndpointDiscoveryListener p3);
    
    PendingResult<Status> startDiscovery(final GoogleApiClient p0, final String p1, final EndpointDiscoveryCallback p2, final DiscoveryOptions p3);
    
    void stopAdvertising(final GoogleApiClient p0);
    
    void stopAllEndpoints(final GoogleApiClient p0);
    
    void stopDiscovery(final GoogleApiClient p0);
    
    @Deprecated
    void stopDiscovery(final GoogleApiClient p0, final String p1);
    
    @Deprecated
    public static class ConnectionRequestListener
    {
        public void onConnectionRequest(final String s, final String s2, final byte[] array) {
        }
    }
    
    @Deprecated
    public interface ConnectionResponseCallback
    {
        void onConnectionResponse(final String p0, final Status p1, final byte[] p2);
    }
    
    @Deprecated
    public abstract static class EndpointDiscoveryListener
    {
        public void onEndpointFound(final String s, final String s2, final String s3) {
        }
        
        public abstract void onEndpointLost(final String p0);
    }
    
    @Deprecated
    public interface MessageListener
    {
        @Deprecated
        void onDisconnected(final String p0);
        
        @Deprecated
        void onMessageReceived(final String p0, final byte[] p1, final boolean p2);
    }
    
    public interface StartAdvertisingResult extends Result
    {
        String getLocalEndpointName();
    }
}
