// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.common.zzc;
import android.os.Bundle;
import android.os.IBinder;
import com.google.android.gms.internal.common.zza;

public final class zzl extends zza implements IGmsCallbacks
{
    zzl(final IBinder binder) {
        super(binder, "com.google.android.gms.common.internal.IGmsCallbacks");
    }
    
    @Override
    public final void onPostInitComplete(final int n, final IBinder binder, final Bundle bundle) throws RemoteException {
        final Parcel zza = this.zza();
        zza.writeInt(n);
        zza.writeStrongBinder(binder);
        zzc.zza(zza, (Parcelable)bundle);
        this.zzb(1, zza);
    }
    
    @Override
    public final void zza(final int n, final Bundle bundle) throws RemoteException {
        final Parcel zza = this.zza();
        zza.writeInt(n);
        zzc.zza(zza, (Parcelable)bundle);
        this.zzb(2, zza);
    }
    
    @Override
    public final void zza(final int n, final IBinder binder, final zzb zzb) throws RemoteException {
        final Parcel zza = this.zza();
        zza.writeInt(n);
        zza.writeStrongBinder(binder);
        zzc.zza(zza, (Parcelable)zzb);
        this.zzb(3, zza);
    }
}
