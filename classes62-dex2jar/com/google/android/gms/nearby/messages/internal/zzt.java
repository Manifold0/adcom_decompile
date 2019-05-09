// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.nearby.zzc;
import android.os.IBinder;
import com.google.android.gms.internal.nearby.zza;

public final class zzt extends zza implements zzs
{
    zzt(final IBinder binder) {
        super(binder, "com.google.android.gms.nearby.messages.internal.INearbyMessagesService");
    }
    
    @Override
    public final void zza(final SubscribeRequest subscribeRequest) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)subscribeRequest);
        this.transactOneway(3, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzbz zzbz) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzbz);
        this.transactOneway(1, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzcb zzcb) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzcb);
        this.transactOneway(8, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzce zzce) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzce);
        this.transactOneway(2, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzcg zzcg) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzcg);
        this.transactOneway(4, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzh zzh) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzh);
        this.transactOneway(7, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzj zzj) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzj);
        this.transactOneway(9, obtainAndWriteInterfaceToken);
    }
}
