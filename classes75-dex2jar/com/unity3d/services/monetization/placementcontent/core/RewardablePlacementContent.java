// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.placementcontent.core;

import java.util.Map;

public class RewardablePlacementContent extends PlacementContent
{
    private boolean isRewarded;
    private String rewardId;
    
    public RewardablePlacementContent(final String s, final Map<String, Object> map) {
        super(s, map);
        if (map.containsKey("rewarded")) {
            this.isRewarded = (boolean)map.get("rewarded");
        }
        if (map.containsKey("rewardId")) {
            this.rewardId = map.get("rewardId");
        }
    }
    
    public String getRewardId() {
        return this.rewardId;
    }
    
    public boolean isRewarded() {
        return this.isRewarded;
    }
}
