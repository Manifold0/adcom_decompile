package com.google.android.gms.internal.drive;

import android.content.IntentSender;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzep extends zza implements zzeo {
    zzep(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.drive.internal.IDriveService");
    }

    public final IntentSender zza(zzgg zzgg) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgg);
        Parcel transactAndReadException = transactAndReadException(10, obtainAndWriteInterfaceToken);
        IntentSender intentSender = (IntentSender) zzc.zza(transactAndReadException, IntentSender.CREATOR);
        transactAndReadException.recycle();
        return intentSender;
    }

    public final IntentSender zza(zzu zzu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzu);
        Parcel transactAndReadException = transactAndReadException(11, obtainAndWriteInterfaceToken);
        IntentSender intentSender = (IntentSender) zzc.zza(transactAndReadException, IntentSender.CREATOR);
        transactAndReadException.recycle();
        return intentSender;
    }

    public final zzec zza(zzgd zzgd, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgd);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        zzec zzec = (zzec) zzc.zza(transactAndReadException, zzec.CREATOR);
        transactAndReadException.recycle();
        return zzec;
    }

    public final void zza(zzab zzab, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzab);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(24, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzad zzad) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzad);
        transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzek zzek, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzek);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzex zzex, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzex);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgk zzgk, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgk);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgm zzgm, zzes zzes, String str, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgm);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzes);
        obtainAndWriteInterfaceToken.writeString(null);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgo zzgo, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgo);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(36, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgq zzgq, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgq);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(28, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgv zzgv, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgv);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(17, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgx zzgx, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgx);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(38, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgz zzgz, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgz);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzj zzj, zzes zzes, String str, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzj);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzes);
        obtainAndWriteInterfaceToken.writeString(null);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzm zzm, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzm);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(18, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzo zzo, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzo);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzr zzr, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzr);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzw zzw, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzw);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(5, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzy zzy, zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzy);
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }

    public final void zzb(zzeq zzeq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (IInterface) zzeq);
        transactAndReadExceptionReturnVoid(35, obtainAndWriteInterfaceToken);
    }
}
