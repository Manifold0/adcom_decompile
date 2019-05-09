package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzzl extends zzej implements zzzj {
    zzzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }

    public final zzlo getVideoController() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken());
        zzlo zze = zzlp.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }

    public final void showInterstitial() throws RemoteException {
        transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken());
    }

    public final void zza(IObjectWrapper iObjectWrapper, String str, Bundle bundle, zzzm zzzm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        obtainAndWriteInterfaceToken.writeString(str);
        zzel.zza(obtainAndWriteInterfaceToken, bundle);
        zzel.zza(obtainAndWriteInterfaceToken, zzzm);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzf zzzf, zzxt zzxt, zzjn zzjn) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        obtainAndWriteInterfaceToken.writeString(str);
        zzel.zza(obtainAndWriteInterfaceToken, bundle);
        zzel.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, zzzf);
        zzel.zza(obtainAndWriteInterfaceToken, zzxt);
        zzel.zza(obtainAndWriteInterfaceToken, zzjn);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    public final void zza(byte[] bArr, String str, Bundle bundle, IObjectWrapper iObjectWrapper, zzzh zzzh, zzxt zzxt) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeByteArray(bArr);
        obtainAndWriteInterfaceToken.writeString(str);
        zzel.zza(obtainAndWriteInterfaceToken, bundle);
        zzel.zza(obtainAndWriteInterfaceToken, iObjectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, zzzh);
        zzel.zza(obtainAndWriteInterfaceToken, zzxt);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    public final zzzt zznc() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken());
        zzzt zzzt = (zzzt) zzel.zza(transactAndReadException, zzzt.CREATOR);
        transactAndReadException.recycle();
        return zzzt;
    }

    public final zzzt zznd() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        zzzt zzzt = (zzzt) zzel.zza(transactAndReadException, zzzt.CREATOR);
        transactAndReadException.recycle();
        return zzzt;
    }
}
