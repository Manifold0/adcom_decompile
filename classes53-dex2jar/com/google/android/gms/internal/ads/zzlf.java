// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Parcelable;
import android.os.RemoteException;
import android.os.Parcel;
import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IBinder;

public final class zzlf extends zzej implements zzld
{
    zzlf(final IBinder binder) {
        super(binder, "com.google.android.gms.ads.internal.client.IClientApi");
    }
    
    public final zzkn createAdLoaderBuilder(final IObjectWrapper objectWrapper, final String s, final zzxn zzxn, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxn);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(3, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzkn zzkn;
        if (strongBinder == null) {
            zzkn = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            if (queryLocalInterface instanceof zzkn) {
                zzkn = (zzkn)queryLocalInterface;
            }
            else {
                zzkn = new zzkp(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzkn;
    }
    
    public final zzaap createAdOverlay(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        final Parcel transactAndReadException = this.transactAndReadException(8, obtainAndWriteInterfaceToken);
        final zzaap zzu = zzaaq.zzu(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzu;
    }
    
    public final zzks createBannerAdManager(final IObjectWrapper objectWrapper, final zzjn zzjn, final String s, final zzxn zzxn, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxn);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(1, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzks zzks;
        if (strongBinder == null) {
            zzks = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzks) {
                zzks = (zzks)queryLocalInterface;
            }
            else {
                zzks = new zzku(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzks;
    }
    
    public final zzaaz createInAppPurchaseManager(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        final Parcel transactAndReadException = this.transactAndReadException(7, obtainAndWriteInterfaceToken);
        final zzaaz zzw = zzaba.zzw(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzw;
    }
    
    public final zzks createInterstitialAdManager(final IObjectWrapper objectWrapper, final zzjn zzjn, final String s, final zzxn zzxn, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        obtainAndWriteInterfaceToken.writeString(s);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxn);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(2, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzks zzks;
        if (strongBinder == null) {
            zzks = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzks) {
                zzks = (zzks)queryLocalInterface;
            }
            else {
                zzks = new zzku(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzks;
    }
    
    public final zzqa createNativeAdViewDelegate(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper2);
        final Parcel transactAndReadException = this.transactAndReadException(5, obtainAndWriteInterfaceToken);
        final zzqa zzi = zzqb.zzi(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzi;
    }
    
    public final zzqf createNativeAdViewHolderDelegate(final IObjectWrapper objectWrapper, final IObjectWrapper objectWrapper2, final IObjectWrapper objectWrapper3) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper2);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper3);
        final Parcel transactAndReadException = this.transactAndReadException(11, obtainAndWriteInterfaceToken);
        final zzqf zzj = zzqg.zzj(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzj;
    }
    
    public final zzagz createRewardedVideoAd(final IObjectWrapper objectWrapper, final zzxn zzxn, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)zzxn);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(6, obtainAndWriteInterfaceToken);
        final zzagz zzy = zzaha.zzy(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return zzy;
    }
    
    public final zzks createSearchAdManager(final IObjectWrapper objectWrapper, final zzjn zzjn, final String s, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        zzel.zza(obtainAndWriteInterfaceToken, (Parcelable)zzjn);
        obtainAndWriteInterfaceToken.writeString(s);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(10, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzks zzks;
        if (strongBinder == null) {
            zzks = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (queryLocalInterface instanceof zzks) {
                zzks = (zzks)queryLocalInterface;
            }
            else {
                zzks = new zzku(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzks;
    }
    
    public final zzlj getMobileAdsSettingsManager(final IObjectWrapper objectWrapper) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        final Parcel transactAndReadException = this.transactAndReadException(4, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzlj zzlj;
        if (strongBinder == null) {
            zzlj = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (queryLocalInterface instanceof zzlj) {
                zzlj = (zzlj)queryLocalInterface;
            }
            else {
                zzlj = new zzll(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzlj;
    }
    
    public final zzlj getMobileAdsSettingsManagerWithClientJarVersion(final IObjectWrapper objectWrapper, final int n) throws RemoteException {
        final Parcel obtainAndWriteInterfaceToken = this.obtainAndWriteInterfaceToken();
        zzel.zza(obtainAndWriteInterfaceToken, (IInterface)objectWrapper);
        obtainAndWriteInterfaceToken.writeInt(n);
        final Parcel transactAndReadException = this.transactAndReadException(9, obtainAndWriteInterfaceToken);
        final IBinder strongBinder = transactAndReadException.readStrongBinder();
        zzlj zzlj;
        if (strongBinder == null) {
            zzlj = null;
        }
        else {
            final IInterface queryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (queryLocalInterface instanceof zzlj) {
                zzlj = (zzlj)queryLocalInterface;
            }
            else {
                zzlj = new zzll(strongBinder);
            }
        }
        transactAndReadException.recycle();
        return zzlj;
    }
}
