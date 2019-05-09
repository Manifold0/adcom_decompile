// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzahb extends zzej implements zzagz
{
    zzahb(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
    }
    
    public final void destroy() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(8, this.obtainAndWriteInterfaceToken());
    }
    
    public final String getMediationAdapterClassName() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final boolean isLoaded() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(5, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final void pause() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(6, this.obtainAndWriteInterfaceToken());
    }
    
    public final void resume() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(7, this.obtainAndWriteInterfaceToken());
    }
    
    public final void setImmersiveMode(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(34, obtainAndWriteInterfaceToken);
    }
    
    public final void setUserId(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }
    
    public final void show() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final zzagx zzagx) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzagx);
        this.transactAndReadExceptionReturnVoid(16, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzahe zzahe) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzahe);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzahk zzahk) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzahk);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzkx zzkx) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzkx);
        this.transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }
    
    public final Bundle zzba() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(15, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = (Bundle)zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    public final void zzd(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(9, obtainAndWriteInterfaceToken);
    }
    
    public final void zze(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }
    
    public final void zzf(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }
}
