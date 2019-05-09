package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface RewardedVideoSmashApi {
    void fetchRewardedVideo();

    void initRewardedVideo(Activity activity, String str, String str2);

    boolean isRewardedVideoAvailable();

    void setRewardedVideoManagerListener(RewardedVideoManagerListener rewardedVideoManagerListener);

    void showRewardedVideo();
}
