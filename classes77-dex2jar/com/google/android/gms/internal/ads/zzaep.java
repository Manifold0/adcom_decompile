// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable$Creator;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import android.os.Parcelable;
import android.os.IBinder;

public final class zzaep extends zzej implements zzaen
{
    zzaep(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.request.IAdRequestService");
    }
    
    public final void zza(final zzaef zzaef, final zzaeq zzaeq) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaef);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzaeq);
        this.transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzaey zzaey, final zzaet zzaet) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaey);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzaet);
        this.transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
    
    public final zzaej zzb(final zzaef zzaef) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaef);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final zzaej zzaej = (zzaej)zzel.zza(transactAndReadException, (Parcelable$Creator)com.google.android.gms.internal.ads.zzaej.CREATOR);
        transactAndReadException.recycle();
        return zzaej;
    }
    
    public final void zzb(final zzaey zzaey, final zzaet zzaet) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzaey);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzaet);
        this.transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }
}
