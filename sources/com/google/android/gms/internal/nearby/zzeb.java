package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzeb extends zza implements zzdz {
    zzeb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.IResultListener");
    }

    public final void zzc(int i) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeInt(i);
        transactOneway(2, obtainAndWriteInterfaceToken);
    }
}
