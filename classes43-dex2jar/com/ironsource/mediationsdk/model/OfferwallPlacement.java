// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class OfferwallPlacement
{
    private int mPlacementId;
    private String mPlacementName;
    
    public OfferwallPlacement(final int mPlacementId, final String mPlacementName) {
        this.mPlacementId = mPlacementId;
        this.mPlacementName = mPlacementName;
    }
    
    public int getPlacementId() {
        return this.mPlacementId;
    }
    
    public String getPlacementName() {
        return this.mPlacementName;
    }
    
    @Override
    public String toString() {
        return "placement name: " + this.mPlacementName + ", placement id: " + this.mPlacementId;
    }
}
