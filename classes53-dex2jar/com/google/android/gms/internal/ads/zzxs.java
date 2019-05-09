// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import android.os.IInterface;
import java.util.List;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Parcel;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.IBinder;

public final class zzxs extends zzej implements zzxq
{
    zzxs(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
    }
    
    public final void destroy() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(5, this.obtainAndWriteInterfaceToken());
    }
    
    public final Bundle getInterstitialAdapterInfo() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(18, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = (Bundle)zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    public final zzlo getVideoController() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(26, this.obtainAndWriteInterfaceToken());
        final zzlo zze = zzlp.zze(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zze;
    }
    
    public final IObjectWrapper getView() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(2, this.obtainAndWriteInterfaceToken());
        final IObjectWrapper interface1 = IObjectWrapper$Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return interface1;
    }
    
    public final boolean isInitialized() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(13, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final void pause() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(8, this.obtainAndWriteInterfaceToken());
    }
    
    public final void resume() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(9, this.obtainAndWriteInterfaceToken());
    }
    
    public final void setImmersiveMode(final boolean b) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, b);
        this.transactAndReadExceptionReturnVoid(25, obtainAndWriteInterfaceToken);
    }
    
    public final void showInterstitial() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(4, this.obtainAndWriteInterfaceToken());
    }
    
    public final void showVideo() throws RemoteException {
        this.transactAndReadExceptionReturnVoid(12, this.obtainAndWriteInterfaceToken());
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzaic zzaic, final List<String> list) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzaic);
        obtainAndWriteInterfaceToken.writeStringList((List)list);
        this.transactAndReadExceptionReturnVoid(23, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final zzaic zzaic, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzaic);
        obtainAndWriteInterfaceToken.writeString(s2);
        this.transactAndReadExceptionReturnVoid(10, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final zzxt zzxt) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxt);
        this.transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final String s2, final zzxt zzxt) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxt);
        this.transactAndReadExceptionReturnVoid(7, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjj zzjj, final String s, final String s2, final zzxt zzxt, final zzpl zzpl, final List<String> list) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxt);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzpl);
        obtainAndWriteInterfaceToken.writeStringList((List)list);
        this.transactAndReadExceptionReturnVoid(14, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjn zzjn, final zzjj zzjj, final String s, final zzxt zzxt) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxt);
        this.transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final IObjectWrapper objectWrapper, final zzjn zzjn, final zzjj zzjj, final String s, final String s2, final zzxt zzxt) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxt);
        this.transactAndReadExceptionReturnVoid(6, obtainAndWriteInterfaceToken);
    }
    
    public final void zza(final zzjj zzjj, final String s, final String s2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeString(s2);
        this.transactAndReadExceptionReturnVoid(20, obtainAndWriteInterfaceToken);
    }
    
    public final void zzc(final zzjj zzjj, final String s) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjj);
        obtainAndWriteInterfaceToken.writeString(s);
        this.transactAndReadExceptionReturnVoid(11, obtainAndWriteInterfaceToken);
    }
    
    public final void zzi(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        this.transactAndReadExceptionReturnVoid(21, obtainAndWriteInterfaceToken);
    }
    
    public final zzxz zzmo() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(15, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzxz zzxz;
        if (strongBinder == null) {
            zzxz = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeAppInstallAdMapper");
            if (queryLocalInterface instanceof zzxz) {
                zzxz = (zzxz)queryLocalInterface;
            }
            else {
                zzxz = new zzyb(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzxz;
    }
    
    public final zzyc zzmp() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(16, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzyc zzyc;
        if (strongBinder == null) {
            zzyc = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.INativeContentAdMapper");
            if (queryLocalInterface instanceof zzyc) {
                zzyc = (zzyc)queryLocalInterface;
            }
            else {
                zzyc = new zzye(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzyc;
    }
    
    public final Bundle zzmq() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(17, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = (Bundle)zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    public final Bundle zzmr() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(19, this.obtainAndWriteInterfaceToken());
        final Bundle bundle = (Bundle)zzel.zza(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }
    
    public final boolean zzms() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(22, this.obtainAndWriteInterfaceToken());
        final boolean zza = zzel.zza(transactAndReadException);
        transactAndReadException.recycle();
        return zza;
    }
    
    public final zzqs zzmt() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(24, this.obtainAndWriteInterfaceToken());
        final zzqs zzk = zzqt.zzk(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzk;
    }
    
    public final zzyf zzmu() throws RemoteException {
        final Parcel transactAndReadException = this.transactAndReadException(27, this.obtainAndWriteInterfaceToken());
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzyf zzyf;
        if (strongBinder == null) {
            zzyf = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IUnifiedNativeAdMapper");
            if (queryLocalInterface instanceof zzyf) {
                zzyf = (zzyf)queryLocalInterface;
            }
            else {
                zzyf = new zzyh(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzyf;
    }
}
