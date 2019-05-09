package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzdf extends zza implements zzdd {
    zzdf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IAdvertisingCallback");
    }

    public final void zza(zzej zzej) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzej);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzfb zzfb) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzfb);
        transactOneway(3, obtainAndWriteInterfaceToken);
    }
}
