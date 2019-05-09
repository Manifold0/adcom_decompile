package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzdl extends zza implements zzdj {
    zzdl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IConnectionLifecycleListener");
    }

    public final void zza(zzef zzef) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzef);
        transactOneway(5, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzeh zzeh) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzeh);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzen zzen) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzen);
        transactOneway(3, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzep zzep) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzep);
        transactOneway(4, obtainAndWriteInterfaceToken);
    }
}
