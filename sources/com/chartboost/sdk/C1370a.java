package com.chartboost.sdk;

import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;

/* renamed from: com.chartboost.sdk.a */
public interface C1370a {
    void didCacheInPlay(String str);

    void didCacheInterstitial(String str);

    void didCacheRewardedVideo(String str);

    void didClickInterstitial(String str);

    void didClickRewardedVideo(String str);

    void didCloseInterstitial(String str);

    void didCloseRewardedVideo(String str);

    void didCompleteRewardedVideo(String str, int i);

    void didDismissInterstitial(String str);

    void didDismissRewardedVideo(String str);

    void didDisplayInterstitial(String str);

    void didDisplayRewardedVideo(String str);

    void didFailToLoadInPlay(String str, CBImpressionError cBImpressionError);

    void didFailToLoadInterstitial(String str, CBImpressionError cBImpressionError);

    void didFailToLoadMoreApps(String str, CBImpressionError cBImpressionError);

    void didFailToLoadRewardedVideo(String str, CBImpressionError cBImpressionError);

    void didFailToRecordClick(String str, CBClickError cBClickError);

    void didInitialize();

    boolean shouldDisplayInterstitial(String str);

    boolean shouldDisplayRewardedVideo(String str);

    boolean shouldRequestInterstitial(String str);

    void willDisplayInterstitial(String str);

    void willDisplayVideo(String str);
}
