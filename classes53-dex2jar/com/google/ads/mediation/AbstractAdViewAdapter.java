// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation;

import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.internal.ads.zzjd;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import java.util.Map;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.mediation.NativeContentAdMapper;
import com.google.android.gms.ads.formats.NativeAdViewHolder;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.internal.ads.zzlo;
import android.view.View;
import android.location.Location;
import java.util.Iterator;
import java.util.Set;
import java.util.Date;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.ads.AdRequest;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import android.content.Context;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.mediation.zza;
import com.google.android.gms.ads.mediation.OnImmersiveModeUpdatedListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;

@zzadh
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, OnImmersiveModeUpdatedListener, com.google.android.gms.ads.mediation.zza, MediationRewardedVideoAdAdapter, zzatm
{
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdView zzgw;
    private InterstitialAd zzgx;
    private AdLoader zzgy;
    private Context zzgz;
    private InterstitialAd zzha;
    private MediationRewardedVideoAdListener zzhb;
    @VisibleForTesting
    private final RewardedVideoAdListener zzhc;
    
    public AbstractAdViewAdapter() {
        this.zzhc = new com.google.ads.mediation.zza(this);
    }
    
    private final AdRequest zza(final Context context, final MediationAdRequest mediationAdRequest, final Bundle bundle, final Bundle bundle2) {
        final AdRequest.Builder builder = new AdRequest.Builder();
        final Date birthday = mediationAdRequest.getBirthday();
        if (birthday != null) {
            builder.setBirthday(birthday);
        }
        final int gender = mediationAdRequest.getGender();
        if (gender != 0) {
            builder.setGender(gender);
        }
        final Set<String> keywords = mediationAdRequest.getKeywords();
        if (keywords != null) {
            final Iterator<String> iterator = keywords.iterator();
            while (iterator.hasNext()) {
                builder.addKeyword(iterator.next());
            }
        }
        final Location location = mediationAdRequest.getLocation();
        if (location != null) {
            builder.setLocation(location);
        }
        if (mediationAdRequest.isTesting()) {
            zzkb.zzif();
            builder.addTestDevice(zzamu.zzbc(context));
        }
        if (mediationAdRequest.taggedForChildDirectedTreatment() != -1) {
            builder.tagForChildDirectedTreatment(mediationAdRequest.taggedForChildDirectedTreatment() == 1);
        }
        builder.setIsDesignedForFamilies(mediationAdRequest.isDesignedForFamilies());
        builder.addNetworkExtrasBundle(AdMobAdapter.class, this.zza(bundle, bundle2));
        return builder.build();
    }
    
    public String getAdUnitId(final Bundle bundle) {
        return bundle.getString("pubid");
    }
    
    @Override
    public View getBannerView() {
        return (View)this.zzgw;
    }
    
    @Override
    public Bundle getInterstitialAdapterInfo() {
        return new MediationAdapter.zza().zzaj(1).zzvx();
    }
    
    @Override
    public zzlo getVideoController() {
        if (this.zzgw != null) {
            final VideoController videoController = this.zzgw.getVideoController();
            if (videoController != null) {
                return videoController.zzbc();
            }
        }
        return null;
    }
    
    @Override
    public void initialize(final Context context, final MediationAdRequest mediationAdRequest, final String s, final MediationRewardedVideoAdListener zzhb, final Bundle bundle, final Bundle bundle2) {
        this.zzgz = context.getApplicationContext();
        (this.zzhb = zzhb).onInitializationSucceeded(this);
    }
    
    @Override
    public boolean isInitialized() {
        return this.zzhb != null;
    }
    
    @Override
    public void loadAd(final MediationAdRequest mediationAdRequest, final Bundle bundle, final Bundle bundle2) {
        if (this.zzgz == null || this.zzhb == null) {
            zzane.e("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        (this.zzha = new InterstitialAd(this.zzgz)).zza(true);
        this.zzha.setAdUnitId(this.getAdUnitId(bundle));
        this.zzha.setRewardedVideoAdListener(this.zzhc);
        this.zzha.zza(new com.google.ads.mediation.zzb(this));
        this.zzha.loadAd(this.zza(this.zzgz, mediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void onDestroy() {
        if (this.zzgw != null) {
            this.zzgw.destroy();
            this.zzgw = null;
        }
        if (this.zzgx != null) {
            this.zzgx = null;
        }
        if (this.zzgy != null) {
            this.zzgy = null;
        }
        if (this.zzha != null) {
            this.zzha = null;
        }
    }
    
    @Override
    public void onImmersiveModeUpdated(final boolean b) {
        if (this.zzgx != null) {
            this.zzgx.setImmersiveMode(b);
        }
        if (this.zzha != null) {
            this.zzha.setImmersiveMode(b);
        }
    }
    
    @Override
    public void onPause() {
        if (this.zzgw != null) {
            this.zzgw.pause();
        }
    }
    
    @Override
    public void onResume() {
        if (this.zzgw != null) {
            this.zzgw.resume();
        }
    }
    
    @Override
    public void requestBannerAd(final Context context, final MediationBannerListener mediationBannerListener, final Bundle bundle, final AdSize adSize, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        (this.zzgw = new AdView(context)).setAdSize(new AdSize(adSize.getWidth(), adSize.getHeight()));
        this.zzgw.setAdUnitId(this.getAdUnitId(bundle));
        this.zzgw.setAdListener(new zzd(this, mediationBannerListener));
        this.zzgw.loadAd(this.zza(context, mediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void requestInterstitialAd(final Context context, final MediationInterstitialListener mediationInterstitialListener, final Bundle bundle, final MediationAdRequest mediationAdRequest, final Bundle bundle2) {
        (this.zzgx = new InterstitialAd(context)).setAdUnitId(this.getAdUnitId(bundle));
        this.zzgx.setAdListener(new zze(this, mediationInterstitialListener));
        this.zzgx.loadAd(this.zza(context, mediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void requestNativeAd(final Context context, final MediationNativeListener mediationNativeListener, final Bundle bundle, final NativeMediationAdRequest nativeMediationAdRequest, final Bundle bundle2) {
        final zzf zzf = new zzf(this, mediationNativeListener);
        final AdLoader.Builder withAdListener = new AdLoader.Builder(context, bundle.getString("pubid")).withAdListener(zzf);
        final NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            withAdListener.withNativeAdOptions(nativeAdOptions);
        }
        if (nativeMediationAdRequest.isUnifiedNativeAdRequested()) {
            withAdListener.forUnifiedNativeAd(zzf);
        }
        if (nativeMediationAdRequest.isAppInstallAdRequested()) {
            withAdListener.forAppInstallAd(zzf);
        }
        if (nativeMediationAdRequest.isContentAdRequested()) {
            withAdListener.forContentAd(zzf);
        }
        if (nativeMediationAdRequest.zzna()) {
            for (final String s : nativeMediationAdRequest.zznb().keySet()) {
                zzf zzf2;
                if (nativeMediationAdRequest.zznb().get(s)) {
                    zzf2 = zzf;
                }
                else {
                    zzf2 = null;
                }
                withAdListener.forCustomTemplateAd(s, zzf, zzf2);
            }
        }
        (this.zzgy = withAdListener.build()).loadAd(this.zza(context, nativeMediationAdRequest, bundle2, bundle));
    }
    
    @Override
    public void showInterstitial() {
        this.zzgx.show();
    }
    
    @Override
    public void showVideo() {
        this.zzha.show();
    }
    
    protected abstract Bundle zza(final Bundle p0, final Bundle p1);
    
    static final class zza extends NativeAppInstallAdMapper
    {
        private final NativeAppInstallAd zzhe;
        
        public zza(final NativeAppInstallAd zzhe) {
            this.zzhe = zzhe;
            this.setHeadline(zzhe.getHeadline().toString());
            this.setImages(zzhe.getImages());
            this.setBody(zzhe.getBody().toString());
            this.setIcon(zzhe.getIcon());
            this.setCallToAction(zzhe.getCallToAction().toString());
            if (zzhe.getStarRating() != null) {
                this.setStarRating(zzhe.getStarRating());
            }
            if (zzhe.getStore() != null) {
                this.setStore(zzhe.getStore().toString());
            }
            if (zzhe.getPrice() != null) {
                this.setPrice(zzhe.getPrice().toString());
            }
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
            this.zza(zzhe.getVideoController());
        }
        
        @Override
        public final void trackView(final View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView)view).setNativeAd(this.zzhe);
            }
            final NativeAdViewHolder nativeAdViewHolder = NativeAdViewHolder.zzvk.get(view);
            if (nativeAdViewHolder != null) {
                nativeAdViewHolder.setNativeAd(this.zzhe);
            }
        }
    }
    
    static final class zzb extends NativeContentAdMapper
    {
        private final NativeContentAd zzhf;
        
        public zzb(final NativeContentAd zzhf) {
            this.zzhf = zzhf;
            this.setHeadline(zzhf.getHeadline().toString());
            this.setImages(zzhf.getImages());
            this.setBody(zzhf.getBody().toString());
            if (zzhf.getLogo() != null) {
                this.setLogo(zzhf.getLogo());
            }
            this.setCallToAction(zzhf.getCallToAction().toString());
            this.setAdvertiser(zzhf.getAdvertiser().toString());
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
            this.zza(zzhf.getVideoController());
        }
        
        @Override
        public final void trackView(final View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView)view).setNativeAd(this.zzhf);
            }
            final NativeAdViewHolder nativeAdViewHolder = NativeAdViewHolder.zzvk.get(view);
            if (nativeAdViewHolder != null) {
                nativeAdViewHolder.setNativeAd(this.zzhf);
            }
        }
    }
    
    static final class zzc extends UnifiedNativeAdMapper
    {
        private final UnifiedNativeAd zzhg;
        
        public zzc(final UnifiedNativeAd zzhg) {
            this.zzhg = zzhg;
            this.setHeadline(zzhg.getHeadline());
            this.setImages(zzhg.getImages());
            this.setBody(zzhg.getBody());
            this.setIcon(zzhg.getIcon());
            this.setCallToAction(zzhg.getCallToAction());
            this.setAdvertiser(zzhg.getAdvertiser());
            this.setStarRating(zzhg.getStarRating());
            this.setStore(zzhg.getStore());
            this.setPrice(zzhg.getPrice());
            this.zzl(zzhg.zzbh());
            this.setOverrideImpressionRecording(true);
            this.setOverrideClickHandling(true);
            this.zza(zzhg.getVideoController());
        }
        
        @Override
        public final void trackViews(final View view, final Map<String, View> map, final Map<String, View> map2) {
            if (view instanceof UnifiedNativeAdView) {
                ((UnifiedNativeAdView)view).setNativeAd(this.zzhg);
            }
            else {
                final NativeAdViewHolder nativeAdViewHolder = NativeAdViewHolder.zzvk.get(view);
                if (nativeAdViewHolder != null) {
                    nativeAdViewHolder.setNativeAd(this.zzhg);
                }
            }
        }
    }
    
    @VisibleForTesting
    static final class zzd extends AdListener implements AppEventListener, zzjd
    {
        @VisibleForTesting
        private final AbstractAdViewAdapter zzhh;
        @VisibleForTesting
        private final MediationBannerListener zzhi;
        
        public zzd(final AbstractAdViewAdapter zzhh, final MediationBannerListener zzhi) {
            this.zzhh = zzhh;
            this.zzhi = zzhi;
        }
        
        @Override
        public final void onAdClicked() {
            this.zzhi.onAdClicked(this.zzhh);
        }
        
        @Override
        public final void onAdClosed() {
            this.zzhi.onAdClosed(this.zzhh);
        }
        
        @Override
        public final void onAdFailedToLoad(final int n) {
            this.zzhi.onAdFailedToLoad(this.zzhh, n);
        }
        
        @Override
        public final void onAdLeftApplication() {
            this.zzhi.onAdLeftApplication(this.zzhh);
        }
        
        @Override
        public final void onAdLoaded() {
            this.zzhi.onAdLoaded(this.zzhh);
        }
        
        @Override
        public final void onAdOpened() {
            this.zzhi.onAdOpened(this.zzhh);
        }
        
        @Override
        public final void onAppEvent(final String s, final String s2) {
            this.zzhi.zza(this.zzhh, s, s2);
        }
    }
    
    @VisibleForTesting
    static final class zze extends AdListener implements zzjd
    {
        @VisibleForTesting
        private final AbstractAdViewAdapter zzhh;
        @VisibleForTesting
        private final MediationInterstitialListener zzhj;
        
        public zze(final AbstractAdViewAdapter zzhh, final MediationInterstitialListener zzhj) {
            this.zzhh = zzhh;
            this.zzhj = zzhj;
        }
        
        @Override
        public final void onAdClicked() {
            this.zzhj.onAdClicked(this.zzhh);
        }
        
        @Override
        public final void onAdClosed() {
            this.zzhj.onAdClosed(this.zzhh);
        }
        
        @Override
        public final void onAdFailedToLoad(final int n) {
            this.zzhj.onAdFailedToLoad(this.zzhh, n);
        }
        
        @Override
        public final void onAdLeftApplication() {
            this.zzhj.onAdLeftApplication(this.zzhh);
        }
        
        @Override
        public final void onAdLoaded() {
            this.zzhj.onAdLoaded(this.zzhh);
        }
        
        @Override
        public final void onAdOpened() {
            this.zzhj.onAdOpened(this.zzhh);
        }
    }
    
    @VisibleForTesting
    static final class zzf extends AdListener implements OnAppInstallAdLoadedListener, OnContentAdLoadedListener, OnCustomClickListener, OnCustomTemplateAdLoadedListener, OnUnifiedNativeAdLoadedListener
    {
        @VisibleForTesting
        private final AbstractAdViewAdapter zzhh;
        @VisibleForTesting
        private final MediationNativeListener zzhk;
        
        public zzf(final AbstractAdViewAdapter zzhh, final MediationNativeListener zzhk) {
            this.zzhh = zzhh;
            this.zzhk = zzhk;
        }
        
        @Override
        public final void onAdClicked() {
            this.zzhk.onAdClicked(this.zzhh);
        }
        
        @Override
        public final void onAdClosed() {
            this.zzhk.onAdClosed(this.zzhh);
        }
        
        @Override
        public final void onAdFailedToLoad(final int n) {
            this.zzhk.onAdFailedToLoad(this.zzhh, n);
        }
        
        @Override
        public final void onAdImpression() {
            this.zzhk.onAdImpression(this.zzhh);
        }
        
        @Override
        public final void onAdLeftApplication() {
            this.zzhk.onAdLeftApplication(this.zzhh);
        }
        
        @Override
        public final void onAdLoaded() {
        }
        
        @Override
        public final void onAdOpened() {
            this.zzhk.onAdOpened(this.zzhh);
        }
        
        @Override
        public final void onAppInstallAdLoaded(final NativeAppInstallAd nativeAppInstallAd) {
            this.zzhk.onAdLoaded(this.zzhh, new zza(nativeAppInstallAd));
        }
        
        @Override
        public final void onContentAdLoaded(final NativeContentAd nativeContentAd) {
            this.zzhk.onAdLoaded(this.zzhh, new zzb(nativeContentAd));
        }
        
        @Override
        public final void onCustomClick(final NativeCustomTemplateAd nativeCustomTemplateAd, final String s) {
            this.zzhk.zza(this.zzhh, nativeCustomTemplateAd, s);
        }
        
        @Override
        public final void onCustomTemplateAdLoaded(final NativeCustomTemplateAd nativeCustomTemplateAd) {
            this.zzhk.zza(this.zzhh, nativeCustomTemplateAd);
        }
        
        @Override
        public final void onUnifiedNativeAdLoaded(final UnifiedNativeAd unifiedNativeAd) {
            this.zzhk.onAdLoaded(this.zzhh, new zzc(unifiedNativeAd));
        }
    }
}
