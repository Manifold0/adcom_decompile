// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class InterstitialPlacement
{
    private PlacementAvailabilitySettings mPlacementAvailabilitySettings;
    private int mPlacementId;
    private String mPlacementName;
    
    public InterstitialPlacement(final int mPlacementId, final String mPlacementName, final PlacementAvailabilitySettings mPlacementAvailabilitySettings) {
        this.mPlacementId = mPlacementId;
        this.mPlacementName = mPlacementName;
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
    
    @Override
    public String toString() {
        return "placement name: " + this.mPlacementName;
    }
}
