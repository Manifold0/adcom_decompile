// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.auth;

import android.os.RemoteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.IInterface;
import android.os.IBinder;

public final class zzaa extends zza implements zzz
{
    zzaa(final IBinder binder) {
        super(binder, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
    }
    
    @Override
    public final void zza(final zzx zzx, final zzab zzab) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzx);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzab);
        this.transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzx zzx, final zzad zzad) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzx);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzad);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzx zzx, final zzaf zzaf) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzx);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaf);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzx zzx, final zzah zzah) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzx);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzah);
        this.transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }
    
    @Override
    public final void zza(final zzx zzx, final zzv zzv) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface)zzx);
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable)zzv);
        this.transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
}
