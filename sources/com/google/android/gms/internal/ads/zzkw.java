package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzkw extends zzej implements zzkv {
    zzkw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.client.IAdManagerCreator");
    }

    public final IBinder zza(IObjectWrapper iObjectWrapper, zzjn zzjn, String str, zzxn zzxn, int i, int i2) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, zzjn);
        obtainAndWriteInterfaceToken.writeString(str);
        zzel.zza(obtainAndWriteInterfaceToken, zzxn);
        obtainAndWriteInterfaceToken.writeInt(12451000);
        obtainAndWriteInterfaceToken.writeInt(i2);
        obtainAndWriteInterfaceToken = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IBinder readStrongBinder = obtainAndWriteInterfaceToken.readStrongBinder();
        obtainAndWriteInterfaceToken.recycle();
        return readStrongBinder;
    }
}
