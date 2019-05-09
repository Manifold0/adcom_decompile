// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.IInterface;
import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IBinder;

public final class zzzl extends zzej implements zzzj
{
    zzzl(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.mediation.client.rtb.IRtbAdapter");
    }
    
    public final zzlo getVideoController() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        final zzlo zze = zzlp.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }
    
    public final void showInterstitial() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(7, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final String s, final Bundle bundle, final zzzm zzzm) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzzm);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final byte[] array, final String s, final Bundle bundle, final IObjectWrapper objectWrapper, final zzzf zzzf, final zzxt zzxt, final zzjn zzjn) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeByteArray(array);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzzf);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxt);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        this.transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final byte[] array, final String s, final Bundle bundle, final IObjectWrapper objectWrapper, final zzzh zzzh, final zzxt zzxt) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeByteArray(array);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)bundle);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzzh);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxt);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
    
    public final zzzt zznc() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final zzzt zzzt = (zzzt)zzel.zza(transactAndReadException, (Parcelable$Creator)com.google.android.gms.internal.ads.zzzt.CREATOR);
        transactAndReadException.recycle();
        return zzzt;
    }
    
    public final zzzt zznd() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        final zzzt zzzt = (zzzt)zzel.zza(transactAndReadException, (Parcelable$Creator)com.google.android.gms.internal.ads.zzzt.CREATOR);
        transactAndReadException.recycle();
        return zzzt;
    }
}
