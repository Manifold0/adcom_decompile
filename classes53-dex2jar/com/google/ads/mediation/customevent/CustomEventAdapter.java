// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.AdRequest;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationServerParameters;
import android.app.Activity;
import com.google.ads.mediation.MediationBannerListener;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.common.util.VisibleForTesting;
import android.view.View;
import com.google.android.gms.common.annotation.KeepName;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.ads.mediation.MediationBannerAdapter;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters>
{
    private View zzhq;
    @VisibleForTesting
    private CustomEventBanner zzhr;
    @VisibleForTesting
    private CustomEventInterstitial zzhs;
    
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
    public final void destroy() {
        if (this.zzhr != null) {
            this.zzhr.destroy();
        }
        if (this.zzhs != null) {
            this.zzhs.destroy();
        }
    }
    
    @Override
    public final Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }
    
    @Override
    public final View getBannerView() {
        return this.zzhq;
    }
    
    @Override
    public final Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }
    
    @Override
    public final void requestBannerAd(final MediationBannerListener mediationBannerListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final AdSize adSize, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.zzhr = zzi(customEventServerParameters.className);
        if (this.zzhr == null) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object extra;
        if (customEventExtras == null) {
            extra = null;
        }
        else {
            extra = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.zzhr.requestBannerAd(new zza(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, extra);
    }
    
    @Override
    public final void requestInterstitialAd(final MediationInterstitialListener mediationInterstitialListener, final Activity activity, final CustomEventServerParameters customEventServerParameters, final MediationAdRequest mediationAdRequest, final CustomEventExtras customEventExtras) {
        this.zzhs = zzi(customEventServerParameters.className);
        if (this.zzhs == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.INTERNAL_ERROR);
            return;
        }
        Object extra;
        if (customEventExtras == null) {
            extra = null;
        }
        else {
            extra = customEventExtras.getExtra(customEventServerParameters.label);
        }
        this.zzhs.requestInterstitialAd(new zzb(this, mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, extra);
    }
    
    @Override
    public final void showInterstitial() {
        this.zzhs.showInterstitial();
    }
    
    @VisibleForTesting
    static final class zza implements CustomEventBannerListener
    {
        private final CustomEventAdapter zzht;
        private final MediationBannerListener zzhu;
        
        public zza(final CustomEventAdapter zzht, final MediationBannerListener zzhu) {
            this.zzht = zzht;
            this.zzhu = zzhu;
        }
        
        @Override
        public final void onClick() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onClick(this.zzht);
        }
        
        @Override
        public final void onDismissScreen() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onDismissScreen(this.zzht);
        }
        
        @Override
        public final void onFailedToReceiveAd() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onFailedToReceiveAd(this.zzht, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public final void onLeaveApplication() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onLeaveApplication(this.zzht);
        }
        
        @Override
        public final void onPresentScreen() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onPresentScreen(this.zzht);
        }
        
        @Override
        public final void onReceivedAd(final View view) {
            zzane.zzck("Custom event adapter called onReceivedAd.");
            this.zzht.zza(view);
            this.zzhu.onReceivedAd(this.zzht);
        }
    }
    
    @VisibleForTesting
    final class zzb implements CustomEventInterstitialListener
    {
        private final CustomEventAdapter zzht;
        private final MediationInterstitialListener zzhv;
        
        public zzb(final CustomEventAdapter zzht, final MediationInterstitialListener zzhv) {
            this.zzht = zzht;
            this.zzhv = zzhv;
        }
        
        @Override
        public final void onDismissScreen() {
            zzane.zzck("Custom event adapter called onDismissScreen.");
            this.zzhv.onDismissScreen(this.zzht);
        }
        
        @Override
        public final void onFailedToReceiveAd() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhv.onFailedToReceiveAd(this.zzht, AdRequest.ErrorCode.NO_FILL);
        }
        
        @Override
        public final void onLeaveApplication() {
            zzane.zzck("Custom event adapter called onLeaveApplication.");
            this.zzhv.onLeaveApplication(this.zzht);
        }
        
        @Override
        public final void onPresentScreen() {
            zzane.zzck("Custom event adapter called onPresentScreen.");
            this.zzhv.onPresentScreen(this.zzht);
        }
        
        @Override
        public final void onReceivedAd() {
            zzane.zzck("Custom event adapter called onReceivedAd.");
            this.zzhv.onReceivedAd(CustomEventAdapter.this);
        }
    }
}
