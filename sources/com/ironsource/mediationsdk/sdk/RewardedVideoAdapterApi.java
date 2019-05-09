package com.ironsource.mediationsdk.sdk;

import android.app.Activity;
import org.json.JSONObject;

public interface RewardedVideoAdapterApi {
    void addRewardedVideoListener(RewardedVideoSmashListener rewardedVideoSmashListener);

    void fetchRewardedVideo(JSONObject jSONObject);

    void initRewardedVideo(Activity activity, String str, String str2, JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener);

    boolean isRewardedVideoAvailable(JSONObject jSONObject);

    void removeRewardedVideoListener(RewardedVideoSmashListener rewardedVideoSmashListener);

    void showRewardedVideo(JSONObject jSONObject, RewardedVideoSmashListener rewardedVideoSmashListener);
}
