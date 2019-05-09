// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class Placement
{
    private PlacementAvailabilitySettings mPlacementAvailabilitySettings;
    private int mPlacementId;
    private String mPlacementName;
    private int mRewardAmount;
    private String mRewardName;
    
    public Placement(final int mPlacementId, final String mPlacementName, final String mRewardName, final int mRewardAmount, final PlacementAvailabilitySettings mPlacementAvailabilitySettings) {
        this.mPlacementId = mPlacementId;
        this.mPlacementName = mPlacementName;
        this.mRewardName = mRewardName;
        this.mRewardAmount = mRewardAmount;
        this.mPlacementAvailabilitySettings = mPlacementAvailabilitySettings;
    }
    
    public PlacementAvailabilitySettings getPlacementAvailabilitySettings() {
        return this.mPlacementAvailabilitySettings;
    }
    
    public int getPlacementId() {
        return this.mPlacementId;
    }
    
    public String getPlacementName() {
        return this.mPlacementName;
    }
    
    public int getRewardAmount() {
        return this.mRewardAmount;
    }
    
    public String getRewardName() {
        return this.mRewardName;
    }
    
    @Override
    public String toString() {
        return "placement name: " + this.mPlacementName + ", reward name: " + this.mRewardName + " , amount:" + this.mRewardAmount;
    }
}
