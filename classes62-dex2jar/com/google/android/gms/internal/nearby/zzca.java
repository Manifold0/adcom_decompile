// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.AppMetadata;
import java.util.List;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.nearby.connection.Connections;

public final class zzca implements Connections
{
    public static final Api$AbstractClientBuilder<zzx, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    public static final Api$ClientKey<zzx> CLIENT_KEY;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzcb();
    }
    
    @Override
    public final PendingResult<Status> acceptConnection(final GoogleApiClient googleApiClient, final String s, final PayloadCallback payloadCallback) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzct(this, googleApiClient, s, googleApiClient.registerListener((Object)payloadCallback)));
    }
    
    @Deprecated
    @Override
    public final PendingResult<Status> acceptConnectionRequest(final GoogleApiClient googleApiClient, final String s, final byte[] array, final MessageListener messageListener) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzck(this, googleApiClient, s, array, googleApiClient.registerListener((Object)messageListener)));
    }
    
    @Override
    public final PendingResult<Status> cancelPayload(final GoogleApiClient googleApiClient, final long n) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzce(this, googleApiClient, n));
    }
    
    @Override
    public final void disconnectFromEndpoint(final GoogleApiClient googleApiClient, final String s) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcf(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<Status> rejectConnection(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcu(this, googleApiClient, s));
    }
    
    @Deprecated
    @Override
    public final PendingResult<Status> rejectConnectionRequest(final GoogleApiClient googleApiClient, final String s) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcl(this, googleApiClient, s));
    }
    
    @Override
    public final PendingResult<Status> requestConnection(final GoogleApiClient googleApiClient, @Nullable final String s, final String s2, final ConnectionLifecycleCallback connectionLifecycleCallback) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcs(this, googleApiClient, s, s2, googleApiClient.registerListener((Object)connectionLifecycleCallback)));
    }
    
    @Deprecated
    @Override
    public final PendingResult<Status> sendConnectionRequest(final GoogleApiClient googleApiClient, final String s, final String s2, final byte[] array, final ConnectionResponseCallback connectionResponseCallback, final MessageListener messageListener) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcj(this, googleApiClient, s, s2, array, googleApiClient.registerListener((Object)connectionResponseCallback), googleApiClient.registerListener((Object)messageListener)));
    }
    
    @Override
    public final PendingResult<Status> sendPayload(final GoogleApiClient googleApiClient, final String s, final Payload payload) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcc(this, googleApiClient, s, payload));
    }
    
    @Override
    public final PendingResult<Status> sendPayload(final GoogleApiClient googleApiClient, final List<String> list, final Payload payload) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcd(this, googleApiClient, list, payload));
    }
    
    @Deprecated
    @Override
    public final void sendReliableMessage(final GoogleApiClient googleApiClient, final String s, final byte[] array) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcm(this, googleApiClient, s, array));
    }
    
    @Deprecated
    @Override
    public final void sendReliableMessage(final GoogleApiClient googleApiClient, final List<String> list, final byte[] array) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcn(this, googleApiClient, list, array));
    }
    
    @Deprecated
    @Override
    public final void sendUnreliableMessage(final GoogleApiClient googleApiClient, final String s, final byte[] array) {
        this.sendPayload(googleApiClient, s, Payload.fromBytes(array));
    }
    
    @Deprecated
    @Override
    public final void sendUnreliableMessage(final GoogleApiClient googleApiClient, final List<String> list, final byte[] array) {
        this.sendPayload(googleApiClient, list, Payload.fromBytes(array));
    }
    
    @Deprecated
    @Override
    public final PendingResult<StartAdvertisingResult> startAdvertising(final GoogleApiClient googleApiClient, final String s, final AppMetadata appMetadata, final long n, final ConnectionRequestListener connectionRequestListener) {
        return (PendingResult<StartAdvertisingResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzch(this, googleApiClient, s, n, googleApiClient.registerListener((Object)connectionRequestListener)));
    }
    
    @Override
    public final PendingResult<StartAdvertisingResult> startAdvertising(final GoogleApiClient googleApiClient, final String s, final String s2, final ConnectionLifecycleCallback connectionLifecycleCallback, final AdvertisingOptions advertisingOptions) {
        return (PendingResult<StartAdvertisingResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzco(this, googleApiClient, s, s2, googleApiClient.registerListener((Object)connectionLifecycleCallback), advertisingOptions));
    }
    
    @Deprecated
    @Override
    public final PendingResult<Status> startDiscovery(final GoogleApiClient googleApiClient, final String s, final long n, final EndpointDiscoveryListener endpointDiscoveryListener) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzci(this, googleApiClient, s, n, googleApiClient.registerListener((Object)endpointDiscoveryListener)));
    }
    
    @Override
    public final PendingResult<Status> startDiscovery(final GoogleApiClient googleApiClient, final String s, final EndpointDiscoveryCallback endpointDiscoveryCallback, final DiscoveryOptions discoveryOptions) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcq(this, googleApiClient, s, googleApiClient.registerListener((Object)endpointDiscoveryCallback), discoveryOptions));
    }
    
    @Override
    public final void stopAdvertising(final GoogleApiClient googleApiClient) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcp(this, googleApiClient));
    }
    
    @Override
    public final void stopAllEndpoints(final GoogleApiClient googleApiClient) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcg(this, googleApiClient));
    }
    
    @Override
    public final void stopDiscovery(final GoogleApiClient googleApiClient) {
        googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzcr(this, googleApiClient));
    }
    
    @Deprecated
    @Override
    public final void stopDiscovery(final GoogleApiClient googleApiClient, final String s) {
        this.stopDiscovery(googleApiClient);
    }
}
