// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.zzal;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.Preconditions;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationAvailability;
import android.os.RemoteException;
import android.location.Location;
import android.util.Log;
import javax.annotation.Nullable;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;

public final class zzaz extends zzk
{
    private final zzas zzde;
    
    public zzaz(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s) {
        this(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, s, ClientSettings.createDefault(context));
    }
    
    public zzaz(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s, @Nullable final ClientSettings clientSettings) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, s, clientSettings);
        this.zzde = new zzas(context, this.zzcb);
    }
    
    public final void disconnect() {
        synchronized (this.zzde) {
            Label_0030: {
                if (!this.isConnected()) {
                    break Label_0030;
                }
                try {
                    this.zzde.removeAllListeners();
                    this.zzde.zzb();
                    super.disconnect();
                }
                catch (Exception ex) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", (Throwable)ex);
                }
            }
        }
    }
    
    public final Location getLastLocation() throws RemoteException {
        return this.zzde.getLastLocation();
    }
    
    public final LocationAvailability zza() throws RemoteException {
        return this.zzde.zza();
    }
    
    public final void zza(final long n, final PendingIntent pendingIntent) throws RemoteException {
        this.checkConnected();
        Preconditions.checkNotNull((Object)pendingIntent);
        Preconditions.checkArgument(n >= 0L, (Object)"detectionIntervalMillis must be >= 0");
        ((zzao)this.getService()).zza(n, true, pendingIntent);
    }
    
    public final void zza(final PendingIntent pendingIntent, final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder) throws RemoteException {
        this.checkConnected();
        Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"ResultHolder not provided.");
        ((zzao)this.getService()).zza(pendingIntent, (IStatusCallback)new StatusCallback((BaseImplementation$ResultHolder)baseImplementation$ResultHolder));
    }
    
    public final void zza(final PendingIntent pendingIntent, final zzaj zzaj) throws RemoteException {
        this.zzde.zza(pendingIntent, zzaj);
    }
    
    public final void zza(final Location location) throws RemoteException {
        this.zzde.zza(location);
    }
    
    public final void zza(final ListenerHolder$ListenerKey<LocationListener> listenerHolder$ListenerKey, final zzaj zzaj) throws RemoteException {
        this.zzde.zza(listenerHolder$ListenerKey, zzaj);
    }
    
    public final void zza(final zzaj zzaj) throws RemoteException {
        this.zzde.zza(zzaj);
    }
    
    public final void zza(final zzbd zzbd, final ListenerHolder<LocationCallback> listenerHolder, final zzaj zzaj) throws RemoteException {
        synchronized (this.zzde) {
            this.zzde.zza(zzbd, listenerHolder, zzaj);
        }
    }
    
    public final void zza(final ActivityTransitionRequest activityTransitionRequest, final PendingIntent pendingIntent, final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder) throws RemoteException {
        this.checkConnected();
        Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"ResultHolder not provided.");
        ((zzao)this.getService()).zza(activityTransitionRequest, pendingIntent, (IStatusCallback)new StatusCallback((BaseImplementation$ResultHolder)baseImplementation$ResultHolder));
    }
    
    public final void zza(final GeofencingRequest geofencingRequest, final PendingIntent pendingIntent, final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder) throws RemoteException {
        this.checkConnected();
        Preconditions.checkNotNull((Object)geofencingRequest, (Object)"geofencingRequest can't be null.");
        Preconditions.checkNotNull((Object)pendingIntent, (Object)"PendingIntent must be specified.");
        Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"ResultHolder not provided.");
        ((zzao)this.getService()).zza(geofencingRequest, pendingIntent, new zzba(baseImplementation$ResultHolder));
    }
    
    public final void zza(final LocationRequest locationRequest, final PendingIntent pendingIntent, final zzaj zzaj) throws RemoteException {
        this.zzde.zza(locationRequest, pendingIntent, zzaj);
    }
    
    public final void zza(final LocationRequest locationRequest, final ListenerHolder<LocationListener> listenerHolder, final zzaj zzaj) throws RemoteException {
        synchronized (this.zzde) {
            this.zzde.zza(locationRequest, listenerHolder, zzaj);
        }
    }
    
    public final void zza(final LocationSettingsRequest locationSettingsRequest, final BaseImplementation$ResultHolder<LocationSettingsResult> baseImplementation$ResultHolder, @Nullable final String s) throws RemoteException {
        final boolean b = true;
        this.checkConnected();
        Preconditions.checkArgument(locationSettingsRequest != null, (Object)"locationSettingsRequest can't be null nor empty.");
        Preconditions.checkArgument(baseImplementation$ResultHolder != null && b, (Object)"listener can't be null.");
        ((zzao)this.getService()).zza(locationSettingsRequest, new zzbc(baseImplementation$ResultHolder), s);
    }
    
    public final void zza(final zzal zzal, final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder) throws RemoteException {
        this.checkConnected();
        Preconditions.checkNotNull((Object)zzal, (Object)"removeGeofencingRequest can't be null.");
        Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"ResultHolder not provided.");
        ((zzao)this.getService()).zza(zzal, new zzbb(baseImplementation$ResultHolder));
    }
    
    public final void zza(final boolean b) throws RemoteException {
        this.zzde.zza(b);
    }
    
    public final void zzb(final PendingIntent pendingIntent) throws RemoteException {
        this.checkConnected();
        Preconditions.checkNotNull((Object)pendingIntent);
        ((zzao)this.getService()).zzb(pendingIntent);
    }
    
    public final void zzb(final ListenerHolder$ListenerKey<LocationCallback> listenerHolder$ListenerKey, final zzaj zzaj) throws RemoteException {
        this.zzde.zzb(listenerHolder$ListenerKey, zzaj);
    }
}
