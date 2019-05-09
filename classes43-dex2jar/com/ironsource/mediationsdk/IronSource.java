// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import com.ironsource.mediationsdk.sdk.SegmentListener;
import java.util.Map;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.ironsource.mediationsdk.sdk.RewardedInterstitialListener;
import com.ironsource.mediationsdk.sdk.OfferwallListener;
import com.ironsource.mediationsdk.logger.LogListener;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyRewardedVideoListener;
import com.ironsource.mediationsdk.sdk.ISDemandOnlyInterstitialListener;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.model.InterstitialPlacement;
import android.content.Context;
import android.app.Activity;

public abstract class IronSource
{
    public static void clearRewardedVideoServerParameters() {
        IronSourceObject.getInstance().clearRewardedVideoServerParameters();
    }
    
    public static IronSourceBannerLayout createBanner(final Activity activity, final ISBannerSize isBannerSize) {
        return IronSourceObject.getInstance().createBanner(activity, isBannerSize);
    }
    
    public static void destroyBanner(final IronSourceBannerLayout ironSourceBannerLayout) {
        IronSourceObject.getInstance().destroyBanner(ironSourceBannerLayout);
    }
    
    public static String getAdvertiserId(final Context context) {
        return IronSourceObject.getInstance().getAdvertiserId(context);
    }
    
    public static InterstitialPlacement getInterstitialPlacementInfo(final String s) {
        return IronSourceObject.getInstance().getInterstitialPlacementInfo(s);
    }
    
    public static void getOfferwallCredits() {
        IronSourceObject.getInstance().getOfferwallCredits();
    }
    
    public static Placement getRewardedVideoPlacementInfo(final String s) {
        return IronSourceObject.getInstance().getRewardedVideoPlacementInfo(s);
    }
    
    public static void init(final Activity activity, final String s) {
        init(activity, s, (AD_UNIT[])null);
    }
    
    public static void init(final Activity activity, final String s, final AD_UNIT... array) {
        IronSourceObject.getInstance().init(activity, s, false, array);
    }
    
    public static void initISDemandOnly(final Activity activity, final String s, final AD_UNIT... array) {
        IronSourceObject.getInstance().initISDemandOnly(activity, s, array);
    }
    
    public static boolean isBannerPlacementCapped(final String s) {
        return IronSourceObject.getInstance().isBannerPlacementCapped(s);
    }
    
    public static boolean isISDemandOnlyInterstitialReady(final String s) {
        return IronSourceObject.getInstance().isISDemandOnlyInterstitialReady(s);
    }
    
    public static boolean isISDemandOnlyRewardedVideoAvailable(final String s) {
        return IronSourceObject.getInstance().isISDemandOnlyRewardedVideoAvailable(s);
    }
    
    public static boolean isInterstitialPlacementCapped(final String s) {
        return IronSourceObject.getInstance().isInterstitialPlacementCapped(s);
    }
    
    public static boolean isInterstitialReady() {
        return IronSourceObject.getInstance().isInterstitialReady();
    }
    
    public static boolean isOfferwallAvailable() {
        return IronSourceObject.getInstance().isOfferwallAvailable();
    }
    
    public static boolean isRewardedVideoAvailable() {
        return IronSourceObject.getInstance().isRewardedVideoAvailable();
    }
    
    public static boolean isRewardedVideoPlacementCapped(final String s) {
        return IronSourceObject.getInstance().isRewardedVideoPlacementCapped(s);
    }
    
    public static void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout) {
        IronSourceObject.getInstance().loadBanner(ironSourceBannerLayout);
    }
    
    public static void loadBanner(final IronSourceBannerLayout ironSourceBannerLayout, final String s) {
        IronSourceObject.getInstance().loadBanner(ironSourceBannerLayout, s);
    }
    
    public static void loadISDemandOnlyInterstitial(final String s) {
        IronSourceObject.getInstance().loadISDemandOnlyInterstitial(s);
    }
    
    public static void loadInterstitial() {
        IronSourceObject.getInstance().loadInterstitial();
    }
    
    public static void onPause(final Activity activity) {
        IronSourceObject.getInstance().onPause(activity);
    }
    
    public static void onResume(final Activity activity) {
        IronSourceObject.getInstance().onResume(activity);
    }
    
    public static void removeInterstitialListener() {
        IronSourceObject.getInstance().removeInterstitialListener();
    }
    
    public static void removeOfferwallListener() {
        IronSourceObject.getInstance().removeOfferwallListener();
    }
    
    public static void removeRewardedVideoListener() {
        IronSourceObject.getInstance().removeRewardedVideoListener();
    }
    
    public static void setAdaptersDebug(final boolean adaptersDebug) {
        IronSourceObject.getInstance().setAdaptersDebug(adaptersDebug);
    }
    
    public static void setAge(final int age) {
        synchronized (IronSource.class) {
            IronSourceObject.getInstance().setAge(age);
        }
    }
    
    public static void setConsent(final boolean consent) {
        IronSourceObject.getInstance().setConsent(consent);
    }
    
    public static boolean setDynamicUserId(final String dynamicUserId) {
        return IronSourceObject.getInstance().setDynamicUserId(dynamicUserId);
    }
    
    public static void setGender(final String gender) {
        synchronized (IronSource.class) {
            IronSourceObject.getInstance().setGender(gender);
        }
    }
    
    public static void setISDemandOnlyInterstitialListener(final ISDemandOnlyInterstitialListener isDemandOnlyInterstitialListener) {
        IronSourceObject.getInstance().setISDemandOnlyInterstitialListener(isDemandOnlyInterstitialListener);
    }
    
    public static void setISDemandOnlyRewardedVideoListener(final ISDemandOnlyRewardedVideoListener isDemandOnlyRewardedVideoListener) {
        IronSourceObject.getInstance().setISDemandOnlyRewardedVideoListener(isDemandOnlyRewardedVideoListener);
    }
    
    public static void setInterstitialListener(final InterstitialListener interstitialListener) {
        IronSourceObject.getInstance().setInterstitialListener(interstitialListener);
    }
    
    public static void setLogListener(final LogListener logListener) {
        IronSourceObject.getInstance().setLogListener(logListener);
    }
    
    public static void setMediationSegment(final String mediationSegment) {
        IronSourceObject.getInstance().setMediationSegment(mediationSegment);
    }
    
    public static void setMediationType(final String mediationType) {
        IronSourceObject.getInstance().setMediationType(mediationType);
    }
    
    public static void setOfferwallListener(final OfferwallListener offerwallListener) {
        IronSourceObject.getInstance().setOfferwallListener(offerwallListener);
    }
    
    public static void setRewardedInterstitialListener(final RewardedInterstitialListener rewardedInterstitialListener) {
        IronSourceObject.getInstance().setRewardedInterstitialListener(rewardedInterstitialListener);
    }
    
    public static void setRewardedVideoListener(final RewardedVideoListener rewardedVideoListener) {
        IronSourceObject.getInstance().setRewardedVideoListener(rewardedVideoListener);
    }
    
    public static void setRewardedVideoServerParameters(final Map<String, String> rewardedVideoServerParameters) {
        IronSourceObject.getInstance().setRewardedVideoServerParameters(rewardedVideoServerParameters);
    }
    
    public static void setSegment(final IronSourceSegment segment) {
        IronSourceObject.getInstance().setSegment(segment);
    }
    
    public static void setSegmentListener(final SegmentListener segmentListener) {
        IronSourceObject.getInstance().setSegmentListener(segmentListener);
    }
    
    public static void setUserId(final String ironSourceUserId) {
        IronSourceObject.getInstance().setIronSourceUserId(ironSourceUserId);
    }
    
    public static void shouldTrackNetworkState(final Context context, final boolean b) {
        IronSourceObject.getInstance().shouldTrackNetworkState(context, b);
    }
    
    public static void showISDemandOnlyInterstitial(final String s) {
        IronSourceObject.getInstance().showISDemandOnlyInterstitial(s);
    }
    
    public static void showISDemandOnlyInterstitial(final String s, final String s2) {
        IronSourceObject.getInstance().showISDemandOnlyInterstitial(s, s2);
    }
    
    public static void showISDemandOnlyRewardedVideo(final String s) {
        IronSourceObject.getInstance().showISDemandOnlyRewardedVideo(s);
    }
    
    public static void showISDemandOnlyRewardedVideo(final String s, final String s2) {
        IronSourceObject.getInstance().showISDemandOnlyRewardedVideo(s, s2);
    }
    
    public static void showInterstitial() {
        IronSourceObject.getInstance().showInterstitial();
    }
    
    public static void showInterstitial(final String s) {
        IronSourceObject.getInstance().showInterstitial(s);
    }
    
    public static void showOfferwall() {
        IronSourceObject.getInstance().showOfferwall();
    }
    
    public static void showOfferwall(final String s) {
        IronSourceObject.getInstance().showOfferwall(s);
    }
    
    public static void showRewardedVideo() {
        IronSourceObject.getInstance().showRewardedVideo();
    }
    
    public static void showRewardedVideo(final String s) {
        IronSourceObject.getInstance().showRewardedVideo(s);
    }
    
    public enum AD_UNIT
    {
        BANNER("banner"), 
        INTERSTITIAL("interstitial"), 
        OFFERWALL("offerwall"), 
        REWARDED_VIDEO("rewardedVideo");
        
        private String mValue;
        
        private AD_UNIT(final String mValue) {
            this.mValue = mValue;
        }
        
        @Override
        public String toString() {
            return this.mValue;
        }
    }
}
