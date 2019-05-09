// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.VideoController;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;

@zzadh
public final class zzyl implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener
{
    private final zzxt zzbuu;
    private NativeAdMapper zzbuv;
    private UnifiedNativeAdMapper zzbuw;
    private NativeCustomTemplateAd zzbux;
    
    public zzyl(final zzxt zzbuu) {
        this.zzbuu = zzbuu;
    }
    
    private static void zza(final MediationNativeAdapter mediationNativeAdapter, @Nullable final UnifiedNativeAdMapper unifiedNativeAdMapper, @Nullable final NativeAdMapper nativeAdMapper) {
        if (!(mediationNativeAdapter instanceof AdMobAdapter)) {
            final VideoController videoController = new VideoController();
            videoController.zza(new zzyi());
            if (unifiedNativeAdMapper != null && unifiedNativeAdMapper.hasVideoContent()) {
                unifiedNativeAdMapper.zza(videoController);
            }
            if (nativeAdMapper != null && nativeAdMapper.hasVideoContent()) {
                nativeAdMapper.zza(videoController);
            }
        }
    }
    
    @Override
    public final void onAdClicked(final MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzbuu.onAdClicked();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdClicked(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzbuu.onAdClicked();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdClicked(final MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        final NativeAdMapper zzbuv = this.zzbuv;
        final UnifiedNativeAdMapper zzbuw = this.zzbuw;
        if (this.zzbux == null) {
            if (zzbuv == null && zzbuw == null) {
                zzane.zzd("#007 Could not call remote method.", null);
                return;
            }
            if (zzbuw != null && !zzbuw.getOverrideClickHandling()) {
                zzane.zzck("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
            if (zzbuv != null && !zzbuv.getOverrideClickHandling()) {
                zzane.zzck("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
        }
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzbuu.onAdClicked();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdClosed(final MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzbuu.onAdClosed();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdClosed(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzbuu.onAdClosed();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdClosed(final MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzbuu.onAdClosed();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdFailedToLoad(final MediationBannerAdapter mediationBannerAdapter, final int n) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error. ").append(n).toString());
        try {
            this.zzbuu.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdFailedToLoad(final MediationInterstitialAdapter mediationInterstitialAdapter, final int n) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(n).append(".").toString());
        try {
            this.zzbuu.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdFailedToLoad(final MediationNativeAdapter mediationNativeAdapter, final int n) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck(new StringBuilder(55).append("Adapter called onAdFailedToLoad with error ").append(n).append(".").toString());
        try {
            this.zzbuu.onAdFailedToLoad(n);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdImpression(final MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        final NativeAdMapper zzbuv = this.zzbuv;
        final UnifiedNativeAdMapper zzbuw = this.zzbuw;
        if (this.zzbux == null) {
            if (zzbuv == null && zzbuw == null) {
                zzane.zzd("#007 Could not call remote method.", null);
                return;
            }
            if (zzbuw != null && !zzbuw.getOverrideImpressionRecording()) {
                zzane.zzck("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
            if (zzbuv != null && !zzbuv.getOverrideImpressionRecording()) {
                zzane.zzck("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
        }
        zzane.zzck("Adapter called onAdImpression.");
        try {
            this.zzbuu.onAdImpression();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLeftApplication(final MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzbuu.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLeftApplication(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzbuu.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLeftApplication(final MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzbuu.onAdLeftApplication();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLoaded(final MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        try {
            this.zzbuu.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLoaded(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        try {
            this.zzbuu.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLoaded(final MediationNativeAdapter mediationNativeAdapter, final NativeAdMapper zzbuv) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        this.zzbuv = zzbuv;
        zza(mediationNativeAdapter, this.zzbuw = null, this.zzbuv);
        try {
            this.zzbuu.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdLoaded(final MediationNativeAdapter mediationNativeAdapter, final UnifiedNativeAdMapper zzbuw) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        this.zzbuw = zzbuw;
        this.zzbuv = null;
        zza(mediationNativeAdapter, this.zzbuw, this.zzbuv);
        try {
            this.zzbuu.onAdLoaded();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdOpened(final MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzbuu.onAdOpened();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdOpened(final MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzbuu.onAdOpened();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onAdOpened(final MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzbuu.onAdOpened();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void onVideoEnd(final MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onVideoEnd.");
        try {
            this.zzbuu.onVideoEnd();
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void zza(final MediationBannerAdapter mediationBannerAdapter, final String s, final String s2) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAppEvent.");
        try {
            this.zzbuu.onAppEvent(s, s2);
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    @Override
    public final void zza(final MediationNativeAdapter mediationNativeAdapter, final NativeCustomTemplateAd zzbux) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        final String value = String.valueOf(zzbux.getCustomTemplateId());
        Label_0048: {
            if (value.length() == 0) {
                break Label_0048;
            }
            String concat = "Adapter called onAdLoaded with template id ".concat(value);
            while (true) {
                zzane.zzck(concat);
                this.zzbux = zzbux;
                try {
                    this.zzbuu.onAdLoaded();
                    return;
                    concat = new String("Adapter called onAdLoaded with template id ");
                }
                catch (RemoteException ex) {
                    zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                }
            }
        }
    }
    
    @Override
    public final void zza(final MediationNativeAdapter mediationNativeAdapter, final NativeCustomTemplateAd nativeCustomTemplateAd, final String s) {
        if (nativeCustomTemplateAd instanceof zzqv) {
            try {
                this.zzbuu.zzb(((zzqv)nativeCustomTemplateAd).zzku(), s);
                return;
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
                return;
            }
        }
        zzane.zzdk("Unexpected native custom template ad type.");
    }
    
    public final NativeAdMapper zzmx() {
        return this.zzbuv;
    }
    
    public final UnifiedNativeAdMapper zzmy() {
        return this.zzbuw;
    }
    
    public final NativeCustomTemplateAd zzmz() {
        return this.zzbux;
    }
}
