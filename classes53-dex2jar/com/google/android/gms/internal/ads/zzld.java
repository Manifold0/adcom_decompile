// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.IInterface;

public interface zzld extends IInterface
{
    zzkn createAdLoaderBuilder(final IObjectWrapper p0, final String p1, final zzxn p2, final int p3) throws RemoteException;
    
    zzaap createAdOverlay(final IObjectWrapper p0) throws RemoteException;
    
    zzks createBannerAdManager(final IObjectWrapper p0, final zzjn p1, final String p2, final zzxn p3, final int p4) throws RemoteException;
    
    zzaaz createInAppPurchaseManager(final IObjectWrapper p0) throws RemoteException;
    
    zzks createInterstitialAdManager(final IObjectWrapper p0, final zzjn p1, final String p2, final zzxn p3, final int p4) throws RemoteException;
    
    zzqa createNativeAdViewDelegate(final IObjectWrapper p0, final IObjectWrapper p1) throws RemoteException;
    
    zzqf createNativeAdViewHolderDelegate(final IObjectWrapper p0, final IObjectWrapper p1, final IObjectWrapper p2) throws RemoteException;
    
    zzagz createRewardedVideoAd(final IObjectWrapper p0, final zzxn p1, final int p2) throws RemoteException;
    
    zzks createSearchAdManager(final IObjectWrapper p0, final zzjn p1, final String p2, final int p3) throws RemoteException;
    
    zzlj getMobileAdsSettingsManager(final IObjectWrapper p0) throws RemoteException;
    
    zzlj getMobileAdsSettingsManagerWithClientJarVersion(final IObjectWrapper p0, final int p1) throws RemoteException;
}
