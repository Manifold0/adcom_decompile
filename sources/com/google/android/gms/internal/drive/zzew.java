package com.google.android.gms.internal.drive;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzew extends zza implements zzeu {
    zzew(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.drive.internal.IEventReleaseCallback");
    }

    public final void zza(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
