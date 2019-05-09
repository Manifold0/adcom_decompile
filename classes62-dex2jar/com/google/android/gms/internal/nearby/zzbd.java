// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.GoogleApi;
import java.util.List;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApi$Settings;
import android.app.Activity;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.nearby.connection.ConnectionsClient;

public final class zzbd extends ConnectionsClient
{
    private static final Api$AbstractClientBuilder<zzx, Api$ApiOptions$NoOptions> CLIENT_BUILDER;
    private static final Api$ClientKey<zzx> CLIENT_KEY;
    private static final Api<Api$ApiOptions$NoOptions> CONNECTIONS_API;
    private final zzk zzcd;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzbm();
        CONNECTIONS_API = new Api("Nearby.CONNECTIONS_API", (Api$AbstractClientBuilder)zzbd.CLIENT_BUILDER, (Api$ClientKey)zzbd.CLIENT_KEY);
    }
    
    public zzbd(final Activity activity) {
        super(activity, zzbd.CONNECTIONS_API, GoogleApi$Settings.DEFAULT_SETTINGS);
        this.zzcd = zzk.zza();
    }
    
    public zzbd(final Context context) {
        super(context, zzbd.CONNECTIONS_API, GoogleApi$Settings.DEFAULT_SETTINGS);
        this.zzcd = zzk.zza();
    }
    
    private final Task<Void> zza(final zzbw zzbw) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbv(this, zzbw));
    }
    
    private final Task<Void> zza(final zzbz zzbz) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbn(this, zzbz));
    }
    
    private final void zzb(final String s) {
        final ListenerHolder<String> zza = this.zzcd.zza((GoogleApi)this, s, "connection");
        this.zzcd.zza(this, new zzbt(this, zza), new zzbu(this, zza.getListenerKey()));
    }
    
    private final void zzc(final String s) {
        this.zzcd.zza(this, this.zzcd.zzb(this, s, "connection"));
    }
    
    @Override
    public final Task<Void> acceptConnection(final String s, final PayloadCallback payloadCallback) {
        return this.zza(new zzbf(s, this.registerListener((Object)payloadCallback, PayloadCallback.class.getName())));
    }
    
    @Override
    public final Task<Void> cancelPayload(final long n) {
        return this.zza(new zzbj(n));
    }
    
    @Override
    public final void disconnectFromEndpoint(final String s) {
        this.zza(new zzbk(s));
        this.zzc(s);
    }
    
    @Override
    public final Task<Void> rejectConnection(final String s) {
        return this.zza(new zzbg(s));
    }
    
    @Override
    public final Task<Void> requestConnection(final String s, final String s2, final ConnectionLifecycleCallback connectionLifecycleCallback) {
        final ListenerHolder registerListener = this.registerListener((Object)new zzbx(this, connectionLifecycleCallback), ConnectionLifecycleCallback.class.getName());
        this.zzb(s2);
        return (Task<Void>)this.zza(new zzbe(s, s2, registerListener)).addOnFailureListener((OnFailureListener)new zzbs(this, s2));
    }
    
    @Override
    public final Task<Void> sendPayload(final String s, final Payload payload) {
        return this.zza(new zzbh(s, payload));
    }
    
    @Override
    public final Task<Void> sendPayload(final List<String> list, final Payload payload) {
        return this.zza(new zzbi(list, payload));
    }
    
    @Override
    public final Task<Void> startAdvertising(final String s, final String s2, final ConnectionLifecycleCallback connectionLifecycleCallback, final AdvertisingOptions advertisingOptions) {
        final ListenerHolder registerListener = this.registerListener((Object)new zzbx(this, connectionLifecycleCallback), ConnectionLifecycleCallback.class.getName());
        final com.google.android.gms.common.api.internal.ListenerHolder<Object> zza = this.zzcd.zza(this, new Object(), "advertising");
        return this.zzcd.zza(this, new zzbo(this, zza, s, s2, registerListener, advertisingOptions), new zzbp(this, zza.getListenerKey()));
    }
    
    @Override
    public final Task<Void> startDiscovery(final String s, final EndpointDiscoveryCallback endpointDiscoveryCallback, final DiscoveryOptions discoveryOptions) {
        final com.google.android.gms.common.api.internal.ListenerHolder<EndpointDiscoveryCallback> zza = this.zzcd.zza(this, endpointDiscoveryCallback, "discovery");
        return this.zzcd.zza(this, new zzbq(this, zza, s, zza, discoveryOptions), new zzbr(this, zza.getListenerKey()));
    }
    
    @Override
    public final void stopAdvertising() {
        this.zzcd.zza(this, "advertising");
    }
    
    @Override
    public final void stopAllEndpoints() {
        this.stopAdvertising();
        this.stopDiscovery();
        this.zza(zzbl.zzcl);
        this.zzcd.zza(this, "connection");
    }
    
    @Override
    public final void stopDiscovery() {
        this.zzcd.zza(this, "discovery");
    }
}
