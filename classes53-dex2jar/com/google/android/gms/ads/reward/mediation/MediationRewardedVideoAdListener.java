// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.reward.mediation;

import android.os.Bundle;
import com.google.android.gms.ads.reward.RewardItem;

public interface MediationRewardedVideoAdListener
{
    void onAdClicked(final MediationRewardedVideoAdAdapter p0);
    
    void onAdClosed(final MediationRewardedVideoAdAdapter p0);
    
    void onAdFailedToLoad(final MediationRewardedVideoAdAdapter p0, final int p1);
    
    void onAdLeftApplication(final MediationRewardedVideoAdAdapter p0);
    
    void onAdLoaded(final MediationRewardedVideoAdAdapter p0);
    
    void onAdOpened(final MediationRewardedVideoAdAdapter p0);
    
    void onInitializationFailed(final MediationRewardedVideoAdAdapter p0, final int p1);
    
    void onInitializationSucceeded(final MediationRewardedVideoAdAdapter p0);
    
    void onRewarded(final MediationRewardedVideoAdAdapter p0, final RewardItem p1);
    
    void onVideoCompleted(final MediationRewardedVideoAdAdapter p0);
    
    void onVideoStarted(final MediationRewardedVideoAdAdapter p0);
    
    void zzc(final Bundle p0);
}
