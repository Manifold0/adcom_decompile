// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzdl extends zza implements zzdj
{
    zzdl(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
    }
    
    @Override
    public final void zza(final zzef zzef) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzef);
        this.transactOneway(5, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzeh zzeh) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzeh);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzen zzen) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzen);
        this.transactOneway(3, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzep zzep) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzep);
        this.transactOneway(4, obtainAndWriteInterfaceToken);
    }
}
