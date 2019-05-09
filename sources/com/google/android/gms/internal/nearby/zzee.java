package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzee extends zza implements zzec {
    zzee(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IStartAdvertisingResultListener");
    }

    public final void zza(zzez zzez) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzez);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }
}
