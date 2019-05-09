package com.google.android.gms.internal.nearby;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.nearby.Nearby;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.Connections.StartAdvertisingResult;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import java.io.IOException;
import java.util.Set;

public final class zzx extends GmsClient<zzdu> {
    private final long zzaz = ((long) hashCode());
    private final Set<zzak> zzba = new ArraySet();
    private final Set<zzav> zzbb = new ArraySet();
    private final Set<zzz> zzbc = new ArraySet();
    private zzff zzbd;

    public zzx(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 54, clientSettings, connectionCallbacks, onConnectionFailedListener);
    }

    private final void reset() {
        for (zzak shutdown : this.zzba) {
            shutdown.shutdown();
        }
        for (zzav shutdown2 : this.zzbb) {
            shutdown2.shutdown();
        }
        for (zzz shutdown3 : this.zzbc) {
            shutdown3.shutdown();
        }
        this.zzba.clear();
        this.zzbb.clear();
        this.zzbc.clear();
        if (this.zzbd != null) {
            this.zzbd.shutdown();
            this.zzbd = null;
        }
    }

    private static Status zza(int i) {
        return new Status(i, ConnectionsStatusCodes.getStatusCodeString(i));
    }

    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
        return queryLocalInterface instanceof zzdu ? (zzdu) queryLocalInterface : new zzdv(iBinder);
    }

    public final void disconnect() {
        if (isConnected()) {
            try {
                ((zzdu) getService()).zza(new zzv().zzd());
            } catch (Throwable e) {
                Log.w("NearbyConnectionsClient", "Failed to notify client disconnect.", e);
            }
        }
        reset();
        super.disconnect();
    }

    public final void disconnectFromEndpoint(String str) throws RemoteException {
        ((zzdu) getService()).zza(new zzdb().zzd(str).zzf());
    }

    protected final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        bundle.putLong("clientId", this.zzaz);
        return bundle;
    }

    public final int getMinApkVersion() {
        return 12451000;
    }

    protected final String getServiceDescriptor() {
        return "com.google.android.gms.nearby.internal.connection.INearbyConnectionService";
    }

    protected final String getStartServiceAction() {
        return "com.google.android.gms.nearby.connection.service.START";
    }

    protected final /* synthetic */ void onConnectedLocked(@NonNull IInterface iInterface) {
        super.onConnectedLocked((zzdu) iInterface);
        this.zzbd = new zzff();
    }

    public final void onConnectionSuspended(int i) {
        if (i == 1) {
            reset();
        }
        super.onConnectionSuspended(i);
    }

    public final boolean requiresGooglePlayServices() {
        return Nearby.zza(getContext());
    }

    public final void stopAdvertising() throws RemoteException {
        ((zzdu) getService()).zza(new zzgh().zzx());
    }

    public final void stopAllEndpoints() throws RemoteException {
        ((zzdu) getService()).zza(new zzgk().zzy());
    }

    public final void stopDiscovery() throws RemoteException {
        ((zzdu) getService()).zza(new zzgn().zzz());
    }

    public final void zza(ResultHolder<Status> resultHolder, long j) throws RemoteException {
        ((zzdu) getService()).zza(new zzs().zzb(new zzba(resultHolder)).zza(j).zzc());
    }

    public final void zza(ResultHolder<Status> resultHolder, String str) throws RemoteException {
        ((zzdu) getService()).zza(new zzfo().zzc(new zzba(resultHolder)).zzf(str).zzs());
    }

    public final void zza(ResultHolder<Status> resultHolder, String str, ListenerHolder<PayloadCallback> listenerHolder) throws RemoteException {
        zzdw zzav = new zzav(listenerHolder);
        this.zzbb.add(zzav);
        ((zzdu) getService()).zza(new zzo().zza(new zzba(resultHolder)).zza(str).zza(zzav).zzb());
    }

    public final void zza(ResultHolder<Status> resultHolder, String str, ListenerHolder<EndpointDiscoveryCallback> listenerHolder, DiscoveryOptions discoveryOptions) throws RemoteException {
        zzdr zzak = new zzak(listenerHolder);
        this.zzba.add(zzak);
        ((zzdu) getService()).zza(new zzge().zzf(new zzba(resultHolder)).zzk(str).zze(discoveryOptions).zza(zzak).zzw());
    }

    public final void zza(ResultHolder<Status> resultHolder, @Nullable String str, String str2, ListenerHolder<ConnectionLifecycleCallback> listenerHolder) throws RemoteException {
        zzdj zzz = new zzz(listenerHolder);
        this.zzbc.add(zzz);
        ((zzdu) getService()).zza(new zzfs().zzd(new zzba(resultHolder)).zzg(str).zzh(str2).zza(zzz).zzt());
    }

    public final void zza(ResultHolder<StartAdvertisingResult> resultHolder, String str, String str2, ListenerHolder<ConnectionLifecycleCallback> listenerHolder, AdvertisingOptions advertisingOptions) throws RemoteException {
        zzdj zzz = new zzz(listenerHolder);
        this.zzbc.add(zzz);
        ((zzdu) getService()).zza(new zzga().zza(new zzbc(resultHolder)).zzi(str).zzj(str2).zzg(advertisingOptions).zzb(zzz).zzv());
    }

    public final void zza(ResultHolder<Status> resultHolder, String[] strArr, Payload payload, boolean z) throws RemoteException {
        try {
            Pair zza = zzfl.zza(payload);
            ((zzdu) getService()).zza(new zzfw().zze(new zzba(resultHolder)).zza(strArr).zzb((zzfh) zza.first).zzu());
            if (zza.second != null) {
                Pair pair = (Pair) zza.second;
                this.zzbd.zza(payload.asStream().asInputStream(), new AutoCloseOutputStream((ParcelFileDescriptor) pair.first), new AutoCloseOutputStream((ParcelFileDescriptor) pair.second), payload.getId());
            }
        } catch (IOException e) {
            resultHolder.setResult(zza(ConnectionsStatusCodes.STATUS_PAYLOAD_IO_ERROR));
        }
    }
}
