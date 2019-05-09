package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzdy extends zza implements zzdw {
    zzdy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IPayloadListener");
    }

    public final void zza(zzev zzev) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzev);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzex zzex) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzex);
        transactOneway(3, obtainAndWriteInterfaceToken);
    }
}
