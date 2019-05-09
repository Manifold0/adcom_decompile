// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

public interface RewardedVideoAdListener extends AdListener
{
    void onLoggingImpression(final Ad p0);
    
    void onRewardedVideoClosed();
    
    void onRewardedVideoCompleted();
}
