// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.reward.RewardItem;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

@zzadh
public final class zzahj extends zzahf
{
    @Nullable
    private RewardedVideoAdListener zzhc;
    
    public zzahj(@Nullable final RewardedVideoAdListener zzhc) {
        this.zzhc = zzhc;
    }
    
    @Nullable
    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        return this.zzhc;
    }
    
    public final void onRewardedVideoAdClosed() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdClosed();
        }
    }
    
    public final void onRewardedVideoAdFailedToLoad(final int n) {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdFailedToLoad(n);
        }
    }
    
    public final void onRewardedVideoAdLeftApplication() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdLeftApplication();
        }
    }
    
    public final void onRewardedVideoAdLoaded() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdLoaded();
        }
    }
    
    public final void onRewardedVideoAdOpened() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoAdOpened();
        }
    }
    
    public final void onRewardedVideoCompleted() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoCompleted();
        }
    }
    
    public final void onRewardedVideoStarted() {
        if (this.zzhc != null) {
            this.zzhc.onRewardedVideoStarted();
        }
    }
    
    public final void setRewardedVideoAdListener(final RewardedVideoAdListener zzhc) {
        this.zzhc = zzhc;
    }
    
    public final void zza(final zzagu zzagu) {
        if (this.zzhc != null) {
            this.zzhc.onRewarded(new zzahh(zzagu));
        }
    }
}
