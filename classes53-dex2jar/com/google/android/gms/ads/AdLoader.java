// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.internal.ads.zzlg;
import android.support.annotation.NonNull;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzjf;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzsd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzsb;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzrz;
import com.google.android.gms.internal.ads.zzsa;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzry;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzrx;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxm;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzkn;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import android.support.annotation.RequiresPermission;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzlw;
import com.google.android.gms.internal.ads.zzkk;
import com.google.android.gms.internal.ads.zzjm;
import android.content.Context;

public class AdLoader
{
    private final Context mContext;
    private final zzjm zzuk;
    private final zzkk zzul;
    
    AdLoader(final Context context, final zzkk zzkk) {
        this(context, zzkk, zzjm.zzara);
    }
    
    private AdLoader(final Context mContext, final zzkk zzul, final zzjm zzuk) {
        this.mContext = mContext;
        this.zzul = zzul;
        this.zzuk = zzuk;
    }
    
    private final void zza(final zzlw zzlw) {
        try {
            this.zzul.zzd(zzjm.zza(this.mContext, zzlw));
        }
        catch (RemoteException ex) {
            zzane.zzb("Failed to load ad.", (Throwable)ex);
        }
    }
    
    @Deprecated
    public String getMediationAdapterClassName() {
        try {
            return this.zzul.zzck();
        }
        catch (RemoteException ex) {
            zzane.zzc("Failed to get the mediation adapter class name.", (Throwable)ex);
            return null;
        }
    }
    
    public boolean isLoading() {
        try {
            return this.zzul.isLoading();
        }
        catch (RemoteException ex) {
            zzane.zzc("Failed to check if ad is loading.", (Throwable)ex);
            return false;
        }
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(final AdRequest adRequest) {
        this.zza(adRequest.zzay());
    }
    
    public void loadAd(final PublisherAdRequest publisherAdRequest) {
        this.zza(publisherAdRequest.zzay());
    }
    
    @RequiresPermission("android.permission.INTERNET")
    public void loadAds(final AdRequest adRequest, final int n) {
        final zzlw zzay = adRequest.zzay();
        try {
            this.zzul.zza(zzjm.zza(this.mContext, zzay), n);
        }
        catch (RemoteException ex) {
            zzane.zzb("Failed to load ads.", (Throwable)ex);
        }
    }
    
    public static class Builder
    {
        private final Context mContext;
        private final zzkn zzum;
        
        private Builder(final Context mContext, final zzkn zzum) {
            this.mContext = mContext;
            this.zzum = zzum;
        }
        
        public Builder(final Context context, final String s) {
            this((Context)Preconditions.checkNotNull((Object)context, (Object)"context cannot be null"), zzkb.zzig().zzb(context, s, new zzxm()));
        }
        
        public AdLoader build() {
            try {
                return new AdLoader(this.mContext, this.zzum.zzdh());
            }
            catch (RemoteException ex) {
                zzane.zzb("Failed to build AdLoader.", (Throwable)ex);
                return null;
            }
        }
        
        public Builder forAppInstallAd(final NativeAppInstallAd.OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzum.zza(new zzrx(onAppInstallAdLoadedListener));
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to add app install ad listener", (Throwable)ex);
                return this;
            }
        }
        
        public Builder forContentAd(final NativeContentAd.OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzum.zza(new zzry(onContentAdLoadedListener));
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to add content ad listener", (Throwable)ex);
                return this;
            }
        }
        
        public Builder forCustomTemplateAd(final String s, final NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, final NativeCustomTemplateAd.OnCustomClickListener onCustomClickListener) {
            try {
                final zzkn zzum = this.zzum;
                final zzsa zzsa = new zzsa(onCustomTemplateAdLoadedListener);
                zzrc zzrc;
                if (onCustomClickListener == null) {
                    zzrc = null;
                }
                else {
                    zzrc = new zzrz(onCustomClickListener);
                }
                zzum.zza(s, zzsa, zzrc);
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to add custom template ad listener", (Throwable)ex);
                return this;
            }
        }
        
        public Builder forPublisherAdView(final OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener, final AdSize... array) {
            if (array == null || array.length <= 0) {
                throw new IllegalArgumentException("The supported ad sizes must contain at least one valid ad size.");
            }
            try {
                this.zzum.zza(new zzsb(onPublisherAdViewLoadedListener), new zzjn(this.mContext, array));
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to add publisher banner ad listener", (Throwable)ex);
                return this;
            }
        }
        
        public Builder forUnifiedNativeAd(final UnifiedNativeAd.OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
            try {
                this.zzum.zza(new zzsd(onUnifiedNativeAdLoadedListener));
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to add google native ad listener", (Throwable)ex);
                return this;
            }
        }
        
        public Builder withAdListener(final AdListener adListener) {
            try {
                this.zzum.zzb(new zzjf(adListener));
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to set AdListener.", (Throwable)ex);
                return this;
            }
        }
        
        public Builder withCorrelator(@NonNull final Correlator correlator) {
            Preconditions.checkNotNull((Object)correlator);
            try {
                this.zzum.zzb(correlator.zzuu);
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to set correlator.", (Throwable)ex);
                return this;
            }
        }
        
        public Builder withNativeAdOptions(final NativeAdOptions nativeAdOptions) {
            try {
                this.zzum.zza(new zzpl(nativeAdOptions));
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to specify native ad options", (Throwable)ex);
                return this;
            }
        }
        
        public Builder withPublisherAdViewOptions(final PublisherAdViewOptions publisherAdViewOptions) {
            try {
                this.zzum.zza(publisherAdViewOptions);
                return this;
            }
            catch (RemoteException ex) {
                zzane.zzc("Failed to specify DFP banner ad options", (Throwable)ex);
                return this;
            }
        }
    }
}
