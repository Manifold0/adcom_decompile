// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

final class zza implements RewardedVideoAdListener
{
    private final /* synthetic */ AbstractAdViewAdapter zzhd;
    
    zza(final AbstractAdViewAdapter zzhd) {
        this.zzhd = zzhd;
    }
    
    @Override
    public final void onRewarded(final RewardItem rewardItem) {
        this.zzhd.zzhb.onRewarded(this.zzhd, rewardItem);
    }
    
    @Override
    public final void onRewardedVideoAdClosed() {
        this.zzhd.zzhb.onAdClosed(this.zzhd);
        AbstractAdViewAdapter.zza(this.zzhd, null);
    }
    
    @Override
    public final void onRewardedVideoAdFailedToLoad(final int n) {
        this.zzhd.zzhb.onAdFailedToLoad(this.zzhd, n);
    }
    
    @Override
    public final void onRewardedVideoAdLeftApplication() {
        this.zzhd.zzhb.onAdLeftApplication(this.zzhd);
    }
    
    @Override
    public final void onRewardedVideoAdLoaded() {
        this.zzhd.zzhb.onAdLoaded(this.zzhd);
    }
    
    @Override
    public final void onRewardedVideoAdOpened() {
        this.zzhd.zzhb.onAdOpened(this.zzhd);
    }
    
    @Override
    public final void onRewardedVideoCompleted() {
        this.zzhd.zzhb.onVideoCompleted(this.zzhd);
    }
    
    @Override
    public final void onRewardedVideoStarted() {
        this.zzhd.zzhb.onVideoStarted(this.zzhd);
    }
}
