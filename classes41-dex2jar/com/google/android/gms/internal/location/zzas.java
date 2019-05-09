// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.internal.Preconditions;
import android.app.PendingIntent;
import com.google.android.gms.location.LocationAvailability;
import java.util.Iterator;
import android.os.IBinder;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import android.os.RemoteException;
import android.location.Location;
import com.google.android.gms.common.api.internal.ListenerHolder;
import java.util.HashMap;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import java.util.Map;
import android.content.ContentProviderClient;
import android.content.Context;

public final class zzas
{
    private final zzbj<zzao> zzcb;
    private final Context zzcu;
    private ContentProviderClient zzcv;
    private boolean zzcw;
    private final Map<ListenerHolder$ListenerKey<LocationListener>, zzax> zzcx;
    private final Map<ListenerHolder$ListenerKey<Object>, zzaw> zzcy;
    private final Map<ListenerHolder$ListenerKey<LocationCallback>, zzat> zzcz;
    
    public zzas(final Context zzcu, final zzbj<zzao> zzcb) {
        this.zzcv = null;
        this.zzcw = false;
        this.zzcx = new HashMap<ListenerHolder$ListenerKey<LocationListener>, zzax>();
        this.zzcy = new HashMap<ListenerHolder$ListenerKey<Object>, zzaw>();
        this.zzcz = new HashMap<ListenerHolder$ListenerKey<LocationCallback>, zzat>();
        this.zzcu = zzcu;
        this.zzcb = zzcb;
    }
    
    private final zzax zza(final ListenerHolder<LocationListener> listenerHolder) {
        synchronized (this.zzcx) {
            zzax zzax;
            if ((zzax = this.zzcx.get(listenerHolder.getListenerKey())) == null) {
                zzax = new zzax(listenerHolder);
            }
            this.zzcx.put((ListenerHolder$ListenerKey<LocationListener>)listenerHolder.getListenerKey(), zzax);
            return zzax;
        }
    }
    
    private final zzat zzb(final ListenerHolder<LocationCallback> listenerHolder) {
        synchronized (this.zzcz) {
            zzat zzat;
            if ((zzat = this.zzcz.get(listenerHolder.getListenerKey())) == null) {
                zzat = new zzat(listenerHolder);
            }
            this.zzcz.put((ListenerHolder$ListenerKey<LocationCallback>)listenerHolder.getListenerKey(), zzat);
            return zzat;
        }
    }
    
    public final Location getLastLocation() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zza(this.zzcu.getPackageName());
    }
    
    public final void removeAllListeners() throws RemoteException {
        synchronized (this.zzcx) {
            for (final zzax zzax : this.zzcx.values()) {
                if (zzax != null) {
                    this.zzcb.getService().zza(zzbf.zza(zzax, null));
                }
            }
        }
        this.zzcx.clear();
        // monitorexit(map)
        synchronized (this.zzcz) {
            for (final zzat zzat : this.zzcz.values()) {
                if (zzat != null) {
                    this.zzcb.getService().zza(zzbf.zza(zzat, null));
                }
            }
        }
        this.zzcz.clear();
        // monitorexit(map2)
        synchronized (this.zzcy) {
            for (final zzaw zzaw : this.zzcy.values()) {
                if (zzaw != null) {
                    this.zzcb.getService().zza(new zzo(2, null, zzaw.asBinder(), null));
                }
            }
        }
        this.zzcy.clear();
    }
    // monitorexit(map3)
    
    public final LocationAvailability zza() throws RemoteException {
        this.zzcb.checkConnected();
        return this.zzcb.getService().zzb(this.zzcu.getPackageName());
    }
    
    public final void zza(final PendingIntent pendingIntent, final zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        final zzao zzao = this.zzcb.getService();
        IBinder binder;
        if (zzaj != null) {
            binder = zzaj.asBinder();
        }
        else {
            binder = null;
        }
        zzao.zza(new zzbf(2, null, null, pendingIntent, null, binder));
    }
    
    public final void zza(final Location location) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(location);
    }
    
    public final void zza(final ListenerHolder$ListenerKey<LocationListener> listenerHolder$ListenerKey, final zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull((Object)listenerHolder$ListenerKey, (Object)"Invalid null listener key");
        synchronized (this.zzcx) {
            final zzax zzax = this.zzcx.remove(listenerHolder$ListenerKey);
            if (zzax != null) {
                zzax.release();
                this.zzcb.getService().zza(zzbf.zza(zzax, zzaj));
            }
        }
    }
    
    public final void zza(final zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(zzaj);
    }
    
    public final void zza(final zzbd zzbd, final ListenerHolder<LocationCallback> listenerHolder, final zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        final zzat zzb = this.zzb(listenerHolder);
        final zzao zzao = this.zzcb.getService();
        final IBinder binder = zzb.asBinder();
        IBinder binder2;
        if (zzaj != null) {
            binder2 = zzaj.asBinder();
        }
        else {
            binder2 = null;
        }
        zzao.zza(new zzbf(1, zzbd, null, null, binder, binder2));
    }
    
    public final void zza(final LocationRequest locationRequest, final PendingIntent pendingIntent, final zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        final zzao zzao = this.zzcb.getService();
        final zzbd zza = zzbd.zza(locationRequest);
        IBinder binder;
        if (zzaj != null) {
            binder = zzaj.asBinder();
        }
        else {
            binder = null;
        }
        zzao.zza(new zzbf(1, zza, null, pendingIntent, null, binder));
    }
    
    public final void zza(final LocationRequest locationRequest, final ListenerHolder<LocationListener> listenerHolder, final zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        final zzax zza = this.zza(listenerHolder);
        final zzao zzao = this.zzcb.getService();
        final zzbd zza2 = zzbd.zza(locationRequest);
        final IBinder binder = zza.asBinder();
        IBinder binder2;
        if (zzaj != null) {
            binder2 = zzaj.asBinder();
        }
        else {
            binder2 = null;
        }
        zzao.zza(new zzbf(1, zza2, binder, null, null, binder2));
    }
    
    public final void zza(final boolean zzcw) throws RemoteException {
        this.zzcb.checkConnected();
        this.zzcb.getService().zza(zzcw);
        this.zzcw = zzcw;
    }
    
    public final void zzb() throws RemoteException {
        if (this.zzcw) {
            this.zza(false);
        }
    }
    
    public final void zzb(final ListenerHolder$ListenerKey<LocationCallback> listenerHolder$ListenerKey, final zzaj zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull((Object)listenerHolder$ListenerKey, (Object)"Invalid null listener key");
        synchronized (this.zzcz) {
            final zzat zzat = this.zzcz.remove(listenerHolder$ListenerKey);
            if (zzat != null) {
                zzat.release();
                this.zzcb.getService().zza(zzbf.zza(zzat, zzaj));
            }
        }
    }
}
