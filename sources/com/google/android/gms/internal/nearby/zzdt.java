package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzdt extends zza implements zzdr {
    zzdt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IDiscoveryListener");
    }

    public final void zza(zzer zzer) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzer);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzet zzet) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzet);
        transactOneway(3, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzfd zzfd) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzfd);
        transactOneway(4, obtainAndWriteInterfaceToken);
    }
}
