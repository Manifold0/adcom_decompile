// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

final class zzf implements ServiceConnection
{
    private ComponentName mComponentName;
    private int mState;
    private IBinder zzcz;
    private final Set<ServiceConnection> zzdz;
    private boolean zzea;
    private final GmsClientSupervisor.zza zzeb;
    private final /* synthetic */ zze zzec;
    
    public zzf(final zze zzec, final GmsClientSupervisor.zza zzeb) {
        this.zzec = zzec;
        this.zzeb = zzeb;
        this.zzdz = new HashSet<ServiceConnection>();
        this.mState = 2;
    }
    
    public final IBinder getBinder() {
        return this.zzcz;
    }
    
    public final ComponentName getComponentName() {
        return this.mComponentName;
    }
    
    public final int getState() {
        return this.mState;
    }
    
    public final boolean isBound() {
        return this.zzea;
    }
    
    public final void onServiceConnected(final ComponentName mComponentName, final IBinder zzcz) {
        synchronized (this.zzec.zzdu) {
            this.zzec.mHandler.removeMessages(1, (Object)this.zzeb);
            this.zzcz = zzcz;
            this.mComponentName = mComponentName;
            final Iterator<ServiceConnection> iterator = this.zzdz.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceConnected(mComponentName, zzcz);
            }
        }
        this.mState = 1;
    }
    // monitorexit(hashMap)
    
    public final void onServiceDisconnected(final ComponentName mComponentName) {
        synchronized (this.zzec.zzdu) {
            this.zzec.mHandler.removeMessages(1, (Object)this.zzeb);
            this.zzcz = null;
            this.mComponentName = mComponentName;
            final Iterator<ServiceConnection> iterator = this.zzdz.iterator();
            while (iterator.hasNext()) {
                iterator.next().onServiceDisconnected(mComponentName);
            }
        }
        this.mState = 2;
    }
    // monitorexit(hashMap)
    
    public final void zza(final ServiceConnection serviceConnection, final String s) {
        this.zzec.zzdw;
        this.zzec.zzdv;
        this.zzeb.zzb(this.zzec.zzdv);
        this.zzdz.add(serviceConnection);
    }
    
    public final boolean zza(final ServiceConnection serviceConnection) {
        return this.zzdz.contains(serviceConnection);
    }
    
    public final void zzb(final ServiceConnection serviceConnection, final String s) {
        this.zzec.zzdw;
        this.zzec.zzdv;
        this.zzdz.remove(serviceConnection);
    }
    
    public final void zze(final String s) {
        this.mState = 3;
        this.zzea = this.zzec.zzdw.zza(this.zzec.zzdv, s, this.zzeb.zzb(this.zzec.zzdv), (ServiceConnection)this, this.zzeb.zzq());
        if (this.zzea) {
            this.zzec.mHandler.sendMessageDelayed(this.zzec.mHandler.obtainMessage(1, (Object)this.zzeb), this.zzec.zzdy);
            return;
        }
        this.mState = 2;
        try {
            this.zzec.zzdw.unbindService(this.zzec.zzdv, (ServiceConnection)this);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    public final void zzf(final String s) {
        this.zzec.mHandler.removeMessages(1, (Object)this.zzeb);
        this.zzec.zzdw.unbindService(this.zzec.zzdv, (ServiceConnection)this);
        this.zzea = false;
        this.mState = 2;
    }
    
    public final boolean zzr() {
        return this.zzdz.isEmpty();
    }
}
