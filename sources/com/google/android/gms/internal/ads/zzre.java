package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzre extends zzej implements zzrc {
    zzre(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.formats.client.IOnCustomClickListener");
    }

    public final void zzb(zzqs zzqs, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, zzqs);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
}
