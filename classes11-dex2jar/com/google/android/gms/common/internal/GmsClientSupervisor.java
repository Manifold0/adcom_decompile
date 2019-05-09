// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.content.ServiceConnection;
import android.content.ComponentName;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class GmsClientSupervisor
{
    private static final Object zzdp;
    private static GmsClientSupervisor zzdq;
    
    static {
        zzdp = new Object();
    }
    
    @KeepForSdk
    public static GmsClientSupervisor getInstance(final Context context) {
        synchronized (GmsClientSupervisor.zzdp) {
            if (GmsClientSupervisor.zzdq == null) {
                GmsClientSupervisor.zzdq = new zze(context.getApplicationContext());
            }
            return GmsClientSupervisor.zzdq;
        }
    }
    
    @KeepForSdk
    public boolean bindService(final ComponentName componentName, final ServiceConnection serviceConnection, final String s) {
        return this.zza(new zza(componentName, 129), serviceConnection, s);
    }
    
    @KeepForSdk
    public boolean bindService(final String s, final ServiceConnection serviceConnection, final String s2) {
        return this.zza(new zza(s, 129), serviceConnection, s2);
    }
    
    @KeepForSdk
    public void unbindService(final ComponentName componentName, final ServiceConnection serviceConnection, final String s) {
        this.zzb(new zza(componentName, 129), serviceConnection, s);
    }
    
    @KeepForSdk
    public void unbindService(final String s, final ServiceConnection serviceConnection, final String s2) {
        this.zzb(new zza(s, 129), serviceConnection, s2);
    }
    
    public final void zza(final String s, final String s2, final int n, final ServiceConnection serviceConnection, final String s3) {
        this.zzb(new zza(s, s2, n), serviceConnection, s3);
    }
    
    protected abstract boolean zza(final zza p0, final ServiceConnection p1, final String p2);
    
    protected abstract void zzb(final zza p0, final ServiceConnection p1, final String p2);
    
    protected static final class zza
    {
        private final ComponentName mComponentName;
        private final String zzdr;
        private final String zzds;
        private final int zzdt;
        
        public zza(final ComponentName componentName, final int n) {
            this.zzdr = null;
            this.zzds = null;
            this.mComponentName = Preconditions.checkNotNull(componentName);
            this.zzdt = 129;
        }
        
        public zza(final String s, final int n) {
            this.zzdr = Preconditions.checkNotEmpty(s);
            this.zzds = "com.google.android.gms";
            this.mComponentName = null;
            this.zzdt = 129;
        }
        
        public zza(final String s, final String s2, final int zzdt) {
            this.zzdr = Preconditions.checkNotEmpty(s);
            this.zzds = Preconditions.checkNotEmpty(s2);
            this.mComponentName = null;
            this.zzdt = zzdt;
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (this != o) {
                if (!(o instanceof zza)) {
                    return false;
                }
                final zza zza = (zza)o;
                if (!Objects.equal(this.zzdr, zza.zzdr) || !Objects.equal(this.zzds, zza.zzds) || !Objects.equal(this.mComponentName, zza.mComponentName) || this.zzdt != zza.zzdt) {
                    return false;
                }
            }
            return true;
        }
        
        public final ComponentName getComponentName() {
            return this.mComponentName;
        }
        
        public final String getPackage() {
            return this.zzds;
        }
        
        @Override
        public final int hashCode() {
            return Objects.hashCode(this.zzdr, this.zzds, this.mComponentName, this.zzdt);
        }
        
        @Override
        public final String toString() {
            if (this.zzdr == null) {
                return this.mComponentName.flattenToString();
            }
            return this.zzdr;
        }
        
        public final Intent zzb(final Context context) {
            if (this.zzdr != null) {
                return new Intent(this.zzdr).setPackage(this.zzds);
            }
            return new Intent().setComponent(this.mComponentName);
        }
        
        public final int zzq() {
            return this.zzdt;
        }
    }
}
