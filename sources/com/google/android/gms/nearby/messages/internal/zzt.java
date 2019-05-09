package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.internal.nearby.zza;
import com.google.android.gms.internal.nearby.zzc;

public final class zzt extends zza implements zzs {
    zzt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.messages.internal.INearbyMessagesService");
    }

    public final void zza(SubscribeRequest subscribeRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) subscribeRequest);
        transactOneway(3, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzbz zzbz) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzbz);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzcb zzcb) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzcb);
        transactOneway(8, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzce zzce) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzce);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzcg zzcg) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzcg);
        transactOneway(4, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzh zzh) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzh);
        transactOneway(7, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzj zzj) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzj);
        transactOneway(9, obtainAndWriteInterfaceToken);
    }
}
