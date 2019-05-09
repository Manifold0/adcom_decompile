// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable$Creator;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzku extends zzej implements zzks
{
    zzku(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IAdManager");
    }
    
    public final void destroy() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(2, this.obtainAndWriteInterfaceToken());
    }
    
    public final String getAdUnitId() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(31, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final String getMediationAdapterClassName() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(18, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
    
    public final zzlo getVideoController() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(26, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzlo zzlo;
        if (strongBinder == null) {
            zzlo = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
            if (queryLocalInterface instanceof zzlo) {
                zzlo = (zzlo)queryLocalInterface;
            }
            else {
                zzlo = new zzlq(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzlo;
    }
    
    public final boolean isLoading() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(23, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final boolean isReady() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(3, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final void pause() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(5, this.obtainAndWriteInterfaceToken());
    }
    
    public final void resume() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(6, this.obtainAndWriteInterfaceToken());
    }
    
    public final void setImmersiveMode(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(34, obtainAndWriteInterfaceToken);
    }
    
    public final void setManualImpressionsEnabled(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(22, obtainAndWriteInterfaceToken);
    }
    
    public final void setUserId(final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(25, obtainAndWriteInterfaceToken);
    }
    
    public final void showInterstitial() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(9, this.obtainAndWriteInterfaceToken());
    }
    
    public final void stopLoading() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(10, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final zzaaw zzaaw) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzaaw);
        this.transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzabc zzabc, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzabc);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(15, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzahe zzahe) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzahe);
        this.transactAndReadExceptionReturnVoid(24, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzjn zzjn) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        this.transactAndReadExceptionReturnVoid(13, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzke zzke) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzke);
        this.transactAndReadExceptionReturnVoid(20, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzkh zzkh) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzkh);
        this.transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzkx zzkx) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzkx);
        this.transactAndReadExceptionReturnVoid(36, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzla zzla) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzla);
        this.transactAndReadExceptionReturnVoid(8, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzlg zzlg) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzlg);
        this.transactAndReadExceptionReturnVoid(21, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzlu zzlu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzlu);
        this.transactAndReadExceptionReturnVoid(30, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzmu zzmu) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzmu);
        this.transactAndReadExceptionReturnVoid(29, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzod zzod) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzod);
        this.transactAndReadExceptionReturnVoid(19, obtainAndWriteInterfaceToken);
    }
    
    public final boolean zzb(final zzjj zzjj) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        final Parcel transactAndReadException = this.transactAndReadException(4, obtainAndWriteInterfaceToken);
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final Bundle zzba() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(37, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = (Bundle)zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    public final IObjectWrapper zzbj() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(1, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final zzjn zzbk() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(12, this.obtainAndWriteInterfaceToken());
        final zzjn zzjn = (zzjn)zzel.zza(transactAndReadException, (Parcelable$Creator)com.google.android.gms.internal.ads.zzjn.CREATOR);
        transactAndReadException.recycle();
        return zzjn;
    }
    
    public final void zzbm() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(11, this.obtainAndWriteInterfaceToken());
    }
    
    public final zzla zzbw() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(32, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzla zzla;
        if (strongBinder == null) {
            zzla = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
            if (queryLocalInterface instanceof zzla) {
                zzla = (zzla)queryLocalInterface;
            }
            else {
                zzla = new zzlc(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzla;
    }
    
    public final zzkh zzbx() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(33, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzkh zzkh;
        if (strongBinder == null) {
            zzkh = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
            if (queryLocalInterface instanceof zzkh) {
                zzkh = (zzkh)queryLocalInterface;
            }
            else {
                zzkh = new zzkj(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzkh;
    }
    
    public final String zzck() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(35, this.obtainAndWriteInterfaceToken());
        final String string = transactAndReadException.readString();
        transactAndReadException.recycle();
        return string;
    }
}
