// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.os.Parcelable$Creator;
import com.google.android.gms.dynamic.IObjectWrapper$Stub;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;

public abstract class zzle extends zzek implements zzld
{
    public zzle() {
        super("com.google.android.gms.ads.internal.client.IClientApi");
    }
    
    public static zzld asInterface(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
        if (queryLocalInterface instanceof zzld) {
            return (zzld)queryLocalInterface;
        }
        return new zzlf(binder);
    }
    
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                final zzks bannerAdManager = this.createBannerAdManager(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), (zzjn)zzel.zza(parcel, (Parcelable$Creator)zzjn.CREATOR), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)bannerAdManager);
                break;
            }
            case 2: {
                final zzks interstitialAdManager = this.createInterstitialAdManager(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), (zzjn)zzel.zza(parcel, (Parcelable$Creator)zzjn.CREATOR), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)interstitialAdManager);
                break;
            }
            case 3: {
                final zzkn adLoaderBuilder = this.createAdLoaderBuilder(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), parcel.readString(), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)adLoaderBuilder);
                break;
            }
            case 4: {
                final zzlj mobileAdsSettingsManager = this.getMobileAdsSettingsManager(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)mobileAdsSettingsManager);
                break;
            }
            case 5: {
                final zzqa nativeAdViewDelegate = this.createNativeAdViewDelegate(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)nativeAdViewDelegate);
                break;
            }
            case 6: {
                final zzagz rewardedVideoAd = this.createRewardedVideoAd(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), zzxo.zzr(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)rewardedVideoAd);
                break;
            }
            case 7: {
                final zzaaz inAppPurchaseManager = this.createInAppPurchaseManager(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)inAppPurchaseManager);
                break;
            }
            case 8: {
                final zzaap adOverlay = this.createAdOverlay(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)adOverlay);
                break;
            }
            case 9: {
                final zzlj mobileAdsSettingsManagerWithClientJarVersion = this.getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)mobileAdsSettingsManagerWithClientJarVersion);
                break;
            }
            case 10: {
                final zzks searchAdManager = this.createSearchAdManager(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), (zzjn)zzel.zza(parcel, (Parcelable$Creator)zzjn.CREATOR), parcel.readString(), parcel.readInt());
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)searchAdManager);
                break;
            }
            case 11: {
                final zzqf nativeAdViewHolderDelegate = this.createNativeAdViewHolderDelegate(IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()), IObjectWrapper$Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzel.zza(parcel2, (IInterface)nativeAdViewHolderDelegate);
                break;
            }
        }
        return true;
    }
}
