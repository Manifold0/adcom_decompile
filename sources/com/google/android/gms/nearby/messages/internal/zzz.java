package com.google.android.gms.nearby.messages.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.nearby.zza;
import com.google.android.gms.internal.nearby.zzc;

public final class zzz extends zza implements zzx {
    zzz(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.messages.internal.IStatusCallback");
    }

    public final void onPermissionChanged(boolean z) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, z);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
