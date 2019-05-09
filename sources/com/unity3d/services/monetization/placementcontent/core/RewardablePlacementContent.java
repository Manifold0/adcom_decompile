package com.unity3d.services.monetization.placementcontent.core;

import java.util.Map;

public class RewardablePlacementContent extends PlacementContent {
    private boolean isRewarded;
    private String rewardId;

    public RewardablePlacementContent(String placementId, Map<String, Object> params) {
        super(placementId, params);
        if (params.containsKey("rewarded")) {
            this.isRewarded = ((Boolean) params.get("rewarded")).booleanValue();
        }
        if (params.containsKey("rewardId")) {
            this.rewardId = (String) params.get("rewardId");
        }
    }

    public boolean isRewarded() {
        return this.isRewarded;
    }

    public String getRewardId() {
        return this.rewardId;
    }
}
