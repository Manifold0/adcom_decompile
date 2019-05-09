// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.mediation.customevent;

import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.AdSize;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import android.content.Context;
import com.google.android.gms.internal.ads.zzane;
import android.view.View;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;

@KeepForSdkWithMembers
@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter
{
    @VisibleForTesting
    private CustomEventBanner zzdge;
    @VisibleForTesting
    private CustomEventInterstitial zzdgf;
    @VisibleForTesting
    private CustomEventNative zzdgg;
    private View zzhq;
    
    private final void zza(final View zzhq) {
        this.zzhq = zzhq;
    }
    
    private static <T> T zzi(final String s) {
        try {
            return (T)Class.forName(s).newInstance();
        }
        catch (Throwable t) {
            final String message = t.getMessage();
            zzane.zzdk(new StringBuilder(String.valueOf(s).length() + 46 + String.valueOf(message).length()).append("Could not instantiate custom event adapter: ").append(s).append(". ").append(message).toString());
            return null;
        }
    }
    
    @Override
    public final View getBannerView() {
        return this.zzhq;
    }
    
    @Override
    public final void onDestroy() {
        if (this.zzdge != null) {
            this.zzdge.onDestroy();
        }
        if (this.zzdgf != null) {
            this.zzdgf.onDestroy();
        }
        if (this.zzdgg != null) {
            this.zzdgg.onDestroy();
        }
    }
    
    @Override
    public final void onPause() {
        if (this.zzdge != null) {
            this.zzdge.onPause();
        }
        if (this.zzdgf != null) {
            this.zzdgf.onPause();
        }
        if (this.zzdgg != null) {
            this.zzdgg.onPause();
        }
    }
    
    @Override
    public final void onResume() {
        if (this.zzdge != null) {
            this.zzdge.onResume();
        }
        if (this.zzdgf != null) {
            this.zzdgf.onResume();
        }
        if (this.zzdgg != null) {
            this.zzdgg.onResume();
        }
    }
    
    @Override
    public final void requestBannerAd(final Context context, final MediationBannerListener mediationBannerListener, final Bundle bundle, final AdSize adSize, final MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzdge = zzi(bundle.getString("class_name"));
        if (this.zzdge == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
            return;
        }
        if (bundle2 == null) {
            bundle2 = null;
        }
        else {
            bundle2 = bundle2.getBundle(bundle.getString("class_name"));
        }
        this.zzdge.requestBannerAd(context, new zza(this, mediationBannerListener), bundle.getString("parameter"), adSize, mediationAdRequest, bundle2);
    }
    
    @Override
    public final void requestInterstitialAd(final Context context, final MediationInterstitialListener mediationInterstitialListener, final Bundle bundle, final MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzdgf = zzi(bundle.getString("class_name"));
        if (this.zzdgf == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
            return;
        }
        if (bundle2 == null) {
            bundle2 = null;
        }
        else {
            bundle2 = bundle2.getBundle(bundle.getString("class_name"));
        }
        this.zzdgf.requestInterstitialAd(context, new zzb(this, mediationInterstitialListener), bundle.getString("parameter"), mediationAdRequest, bundle2);
    }
    
    @Override
    public final void requestNativeAd(final Context context, final MediationNativeListener mediationNativeListener, final Bundle bundle, final NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.zzdgg = zzi(bundle.getString("class_name"));
        if (this.zzdgg == null) {
            mediationNativeListener.onAdFailedToLoad(this, 0);
            return;
        }
        if (bundle2 == null) {
            bundle2 = null;
        }
        else {
            bundle2 = bundle2.getBundle(bundle.getString("class_name"));
        }
        this.zzdgg.requestNativeAd(context, new zzc(this, mediationNativeListener), bundle.getString("parameter"), nativeMediationAdRequest, bundle2);
    }
    
    @Override
    public final void showInterstitial() {
        this.zzdgf.showInterstitial();
    }
    
    @VisibleForTesting
    static final class zza implements CustomEventBannerListener
    {
        private final CustomEventAdapter zzdgh;
        private final MediationBannerListener zzhi;
        
        public zza(final CustomEventAdapter zzdgh, final MediationBannerListener zzhi) {
            this.zzdgh = zzdgh;
            this.zzhi = zzhi;
        }
        
        @Override
        public final void onAdClicked() {
            zzane.zzck("Custom event adapter called onAdClicked.");
            this.zzhi.onAdClicked(this.zzdgh);
        }
        
        @Override
        public final void onAdClosed() {
            zzane.zzck("Custom event adapter called onAdClosed.");
            this.zzhi.onAdClosed(this.zzdgh);
        }
        
        @Override
        public final void onAdFailedToLoad(final int n) {
            zzane.zzck("Custom event adapter called onAdFailedToLoad.");
            this.zzhi.onAdFailedToLoad(this.zzdgh, n);
        }
        
        @Override
        public final void onAdLeftApplication() {
            zzane.zzck("Custom event adapter called onAdLeftApplication.");
            this.zzhi.onAdLeftApplication(this.zzdgh);
        }
        
        @Override
        public final void onAdLoaded(final View view) {
            zzane.zzck("Custom event adapter called onAdLoaded.");
            this.zzdgh.zza(view);
            this.zzhi.onAdLoaded(this.zzdgh);
        }
        
        @Override
        public final void onAdOpened() {
            zzane.zzck("Custom event adapter called onAdOpened.");
            this.zzhi.onAdOpened(this.zzdgh);
        }
    }
    
    @VisibleForTesting
    final class zzb implements CustomEventInterstitialListener
    {
        private final CustomEventAdapter zzdgh;
        private final MediationInterstitialListener zzhj;
        
        public zzb(final CustomEventAdapter zzdgh, final MediationInterstitialListener zzhj) {
            this.zzdgh = zzdgh;
            this.zzhj = zzhj;
        }
        
        @Override
        public final void onAdClicked() {
            zzane.zzck("Custom event adapter called onAdClicked.");
            this.zzhj.onAdClicked(this.zzdgh);
        }
        
        @Override
        public final void onAdClosed() {
            zzane.zzck("Custom event adapter called onAdClosed.");
            this.zzhj.onAdClosed(this.zzdgh);
        }
        
        @Override
        public final void onAdFailedToLoad(final int n) {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhj.onAdFailedToLoad(this.zzdgh, n);
        }
        
        @Override
        public final void onAdLeftApplication() {
            zzane.zzck("Custom event adapter called onAdLeftApplication.");
            this.zzhj.onAdLeftApplication(this.zzdgh);
        }
        
        @Override
        public final void onAdLoaded() {
            zzane.zzck("Custom event adapter called onReceivedAd.");
            this.zzhj.onAdLoaded(CustomEventAdapter.this);
        }
        
        @Override
        public final void onAdOpened() {
            zzane.zzck("Custom event adapter called onAdOpened.");
            this.zzhj.onAdOpened(this.zzdgh);
        }
    }
    
    @VisibleForTesting
    static final class zzc implements CustomEventNativeListener
    {
        private final CustomEventAdapter zzdgh;
        private final MediationNativeListener zzhk;
        
        public zzc(final CustomEventAdapter zzdgh, final MediationNativeListener zzhk) {
            this.zzdgh = zzdgh;
            this.zzhk = zzhk;
        }
        
        @Override
        public final void onAdClicked() {
            zzane.zzck("Custom event adapter called onAdClicked.");
            this.zzhk.onAdClicked(this.zzdgh);
        }
        
        @Override
        public final void onAdClosed() {
            zzane.zzck("Custom event adapter called onAdClosed.");
            this.zzhk.onAdClosed(this.zzdgh);
        }
        
        @Override
        public final void onAdFailedToLoad(final int n) {
            zzane.zzck("Custom event adapter called onAdFailedToLoad.");
            this.zzhk.onAdFailedToLoad(this.zzdgh, n);
        }
        
        @Override
        public final void onAdImpression() {
            zzane.zzck("Custom event adapter called onAdImpression.");
            this.zzhk.onAdImpression(this.zzdgh);
        }
        
        @Override
        public final void onAdLeftApplication() {
            zzane.zzck("Custom event adapter called onAdLeftApplication.");
            this.zzhk.onAdLeftApplication(this.zzdgh);
        }
        
        @Override
        public final void onAdLoaded(final NativeAdMapper nativeAdMapper) {
            zzane.zzck("Custom event adapter called onAdLoaded.");
            this.zzhk.onAdLoaded(this.zzdgh, nativeAdMapper);
        }
        
        @Override
        public final void onAdLoaded(final UnifiedNativeAdMapper unifiedNativeAdMapper) {
            zzane.zzck("Custom event adapter called onAdLoaded.");
            this.zzhk.onAdLoaded(this.zzdgh, unifiedNativeAdMapper);
        }
        
        @Override
        public final void onAdOpened() {
            zzane.zzck("Custom event adapter called onAdOpened.");
            this.zzhk.onAdOpened(this.zzdgh);
        }
    }
}
