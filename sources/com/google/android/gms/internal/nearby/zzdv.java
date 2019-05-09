package com.google.android.gms.internal.nearby;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.facebook.ads.AdError;

public final class zzdv extends zza implements zzdu {
    zzdv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.nearby.internal.connection.INearbyConnectionService");
    }

    public final void zza(zzcz zzcz) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzcz);
        transactAndReadExceptionReturnVoid(AdError.INTERSTITIAL_AD_TIMEOUT, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzfm zzfm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzfm);
        transactAndReadExceptionReturnVoid(2007, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzfq zzfq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzfq);
        transactAndReadExceptionReturnVoid(2005, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzfu zzfu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzfu);
        transactAndReadExceptionReturnVoid(AdError.REMOTE_ADS_SERVICE_ERROR, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzfy zzfy) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzfy);
        transactAndReadExceptionReturnVoid(2001, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgc zzgc) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgc);
        transactAndReadExceptionReturnVoid(AdError.INTERNAL_ERROR_2003, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgg zzgg) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgg);
        transactAndReadExceptionReturnVoid(2002, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgj zzgj) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgj);
        transactAndReadExceptionReturnVoid(2010, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzgm zzgm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzgm);
        transactAndReadExceptionReturnVoid(AdError.INTERNAL_ERROR_2004, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzm zzm) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzm);
        transactAndReadExceptionReturnVoid(AdError.INTERNAL_ERROR_2006, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzq zzq) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzq);
        transactAndReadExceptionReturnVoid(2012, obtainAndWriteInterfaceToken);
    }

    public final void zza(zzu zzu) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(obtainAndWriteInterfaceToken, (Parcelable) zzu);
        transactAndReadExceptionReturnVoid(2011, obtainAndWriteInterfaceToken);
    }
}
