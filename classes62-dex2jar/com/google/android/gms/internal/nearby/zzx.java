// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import java.io.IOException;
import java.io.OutputStream;
import android.os.ParcelFileDescriptor$AutoCloseOutputStream;
import android.os.ParcelFileDescriptor;
import android.util.Pair;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import android.support.annotation.Nullable;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.PayloadCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.nearby.Nearby;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.android.gms.common.api.Status;
import java.util.Iterator;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import java.util.Set;
import com.google.android.gms.common.internal.GmsClient;

public final class zzx extends GmsClient<zzdu>
{
    private final long zzaz;
    private final Set<zzak> zzba;
    private final Set<zzav> zzbb;
    private final Set<zzz> zzbc;
    private zzff zzbd;
    
    public zzx(final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 54, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzba = (Set<zzak>)new ArraySet();
        this.zzbb = (Set<zzav>)new ArraySet();
        this.zzbc = (Set<zzz>)new ArraySet();
        this.zzaz = this.hashCode();
    }
    
    private final void reset() {
        final Iterator<zzak> iterator = this.zzba.iterator();
        while (iterator.hasNext()) {
            iterator.next().shutdown();
        }
        final Iterator<zzav> iterator2 = this.zzbb.iterator();
        while (iterator2.hasNext()) {
            iterator2.next().shutdown();
        }
        final Iterator<zzz> iterator3 = this.zzbc.iterator();
        while (iterator3.hasNext()) {
            iterator3.next().shutdown();
        }
        this.zzba.clear();
        this.zzbb.clear();
        this.zzbc.clear();
        if (this.zzbd != null) {
            this.zzbd.shutdown();
            this.zzbd = null;
        }
    }
    
    private static Status zza(final int n) {
        return new Status(n, ConnectionsStatusCodes.getStatusCodeString(n));
    }
    
    public final void disconnect() {
        while (true) {
            if (!this.isConnected()) {
                break Label_0029;
            }
            try {
                ((zzdu)this.getService()).zza(new zzv().zzd());
                this.reset();
                super.disconnect();
            }
            catch (RemoteException ex) {
                Log.w("NearbyConnectionsClient", "Failed to notify client disconnect.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public final void disconnectFromEndpoint(final String s) throws RemoteException {
        ((zzdu)this.getService()).zza(new zzdb().zzd(s).zzf());
    }
    
    protected final Bundle getGetServiceRequestExtraArgs() {
        final Bundle bundle = new Bundle();
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
    
    public final void onConnectionSuspended(final int n) {
        if (n == 1) {
            this.reset();
        }
        super.onConnectionSuspended(n);
    }
    
    public final boolean requiresGooglePlayServices() {
        return Nearby.zza(this.getContext());
    }
    
    public final void stopAdvertising() throws RemoteException {
        ((zzdu)this.getService()).zza(new zzgh().zzx());
    }
    
    public final void stopAllEndpoints() throws RemoteException {
        ((zzdu)this.getService()).zza(new zzgk().zzy());
    }
    
    public final void stopDiscovery() throws RemoteException {
        ((zzdu)this.getService()).zza(new zzgn().zzz());
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder, final long n) throws RemoteException {
        ((zzdu)this.getService()).zza(new zzs().zzb(new zzba(baseImplementation$ResultHolder)).zza(n).zzc());
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder, final String s) throws RemoteException {
        ((zzdu)this.getService()).zza(new zzfo().zzc(new zzba(baseImplementation$ResultHolder)).zzf(s).zzs());
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder, final String s, final ListenerHolder<PayloadCallback> listenerHolder) throws RemoteException {
        final zzav zzav = new zzav(listenerHolder);
        this.zzbb.add(zzav);
        ((zzdu)this.getService()).zza(new zzo().zza(new zzba(baseImplementation$ResultHolder)).zza(s).zza(zzav).zzb());
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder, final String s, final ListenerHolder<EndpointDiscoveryCallback> listenerHolder, final DiscoveryOptions discoveryOptions) throws RemoteException {
        final zzak zzak = new zzak(listenerHolder);
        this.zzba.add(zzak);
        ((zzdu)this.getService()).zza(new zzge().zzf(new zzba(baseImplementation$ResultHolder)).zzk(s).zze(discoveryOptions).zza(zzak).zzw());
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder, @Nullable final String s, final String s2, final ListenerHolder<ConnectionLifecycleCallback> listenerHolder) throws RemoteException {
        final zzz zzz = new zzz(listenerHolder);
        this.zzbc.add(zzz);
        ((zzdu)this.getService()).zza(new zzfs().zzd(new zzba(baseImplementation$ResultHolder)).zzg(s).zzh(s2).zza(zzz).zzt());
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Connections.StartAdvertisingResult> baseImplementation$ResultHolder, final String s, final String s2, final ListenerHolder<ConnectionLifecycleCallback> listenerHolder, final AdvertisingOptions advertisingOptions) throws RemoteException {
        final zzz zzz = new zzz(listenerHolder);
        this.zzbc.add(zzz);
        ((zzdu)this.getService()).zza(new zzga().zza(new zzbc(baseImplementation$ResultHolder)).zzi(s).zzj(s2).zzg(advertisingOptions).zzb(zzz).zzv());
    }
    
    public final void zza(BaseImplementation$ResultHolder<Status> pair, final String[] array, final Payload payload, final boolean b) throws RemoteException {
        try {
            final Pair<zzfh, Pair<ParcelFileDescriptor, ParcelFileDescriptor>> zza = zzfl.zza(payload);
            ((zzdu)this.getService()).zza(new zzfw().zze(new zzba((BaseImplementation$ResultHolder<Status>)pair)).zza(array).zzb((zzfh)zza.first).zzu());
            if (zza.second != null) {
                pair = (Pair)zza.second;
                this.zzbd.zza(payload.asStream().asInputStream(), (OutputStream)new ParcelFileDescriptor$AutoCloseOutputStream((ParcelFileDescriptor)pair.first), (OutputStream)new ParcelFileDescriptor$AutoCloseOutputStream((ParcelFileDescriptor)pair.second), payload.getId());
            }
        }
        catch (IOException ex) {
            ((BaseImplementation$ResultHolder)pair).setResult((Object)zza(8013));
        }
    }
}
