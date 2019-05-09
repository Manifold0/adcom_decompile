package com.google.android.gms.internal.nearby;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final class zzbd extends ConnectionsClient {
    private static final AbstractClientBuilder<zzx, NoOptions> CLIENT_BUILDER = new zzbm();
    private static final ClientKey<zzx> CLIENT_KEY = new ClientKey();
    private static final Api<NoOptions> CONNECTIONS_API = new Api("Nearby.CONNECTIONS_API", CLIENT_BUILDER, CLIENT_KEY);
    private final zzk zzcd = zzk.zza();

    public zzbd(Activity activity) {
        super(activity, CONNECTIONS_API, Settings.DEFAULT_SETTINGS);
    }

    public zzbd(Context context) {
        super(context, CONNECTIONS_API, Settings.DEFAULT_SETTINGS);
    }

    private final Task<Void> zza(zzbw zzbw) {
        return doWrite(new zzbv(this, zzbw));
    }

    private final Task<Void> zza(zzbz zzbz) {
        return doWrite(new zzbn(this, zzbz));
    }

    private final void zzb(String str) {
        ListenerHolder zza = this.zzcd.zza((GoogleApi) this, str, "connection");
        this.zzcd.zza((GoogleApi) this, new zzbt(this, zza), new zzbu(this, zza.getListenerKey()));
    }

    private final void zzc(String str) {
        this.zzcd.zza((GoogleApi) this, this.zzcd.zzb(this, str, "connection"));
    }

    public final Task<Void> acceptConnection(String str, PayloadCallback payloadCallback) {
        return zza(new zzbf(str, registerListener(payloadCallback, PayloadCallback.class.getName())));
    }

    public final Task<Void> cancelPayload(long j) {
        return zza(new zzbj(j));
    }

    public final void disconnectFromEndpoint(String str) {
        zza(new zzbk(str));
        zzc(str);
    }

    public final Task<Void> rejectConnection(String str) {
        return zza(new zzbg(str));
    }

    public final Task<Void> requestConnection(String str, String str2, ConnectionLifecycleCallback connectionLifecycleCallback) {
        ListenerHolder registerListener = registerListener(new zzbx(this, connectionLifecycleCallback), ConnectionLifecycleCallback.class.getName());
        zzb(str2);
        return zza(new zzbe(str, str2, registerListener)).addOnFailureListener(new zzbs(this, str2));
    }

    public final Task<Void> sendPayload(String str, Payload payload) {
        return zza(new zzbh(str, payload));
    }

    public final Task<Void> sendPayload(List<String> list, Payload payload) {
        return zza(new zzbi(list, payload));
    }

    public final Task<Void> startAdvertising(String str, String str2, ConnectionLifecycleCallback connectionLifecycleCallback, AdvertisingOptions advertisingOptions) {
        ListenerHolder registerListener = registerListener(new zzbx(this, connectionLifecycleCallback), ConnectionLifecycleCallback.class.getName());
        ListenerHolder zza = this.zzcd.zza((GoogleApi) this, new Object(), "advertising");
        return this.zzcd.zza((GoogleApi) this, new zzbo(this, zza, str, str2, registerListener, advertisingOptions), new zzbp(this, zza.getListenerKey()));
    }

    public final Task<Void> startDiscovery(String str, EndpointDiscoveryCallback endpointDiscoveryCallback, DiscoveryOptions discoveryOptions) {
        ListenerHolder zza = this.zzcd.zza((GoogleApi) this, (Object) endpointDiscoveryCallback, "discovery");
        return this.zzcd.zza((GoogleApi) this, new zzbq(this, zza, str, zza, discoveryOptions), new zzbr(this, zza.getListenerKey()));
    }

    public final void stopAdvertising() {
        this.zzcd.zza((GoogleApi) this, "advertising");
    }

    public final void stopAllEndpoints() {
        stopAdvertising();
        stopDiscovery();
        zza(zzbl.zzcl);
        this.zzcd.zza((GoogleApi) this, "connection");
    }

    public final void stopDiscovery() {
        this.zzcd.zza((GoogleApi) this, "discovery");
    }
}
