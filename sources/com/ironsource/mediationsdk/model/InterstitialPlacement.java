package com.ironsource.mediationsdk.model;

public class InterstitialPlacement {
    private PlacementAvailabilitySettings mPlacementAvailabilitySettings;
    private int mPlacementId;
    private String mPlacementName;

    public InterstitialPlacement(int placementId, String placementName, PlacementAvailabilitySettings placementAvailabilitySettings) {
        this.mPlacementId = placementId;
        this.mPlacementName = placementName;
        this.mPlacementAvailabilitySettings = placementAvailabilitySettings;
    }

    public int getPlacementId() {
        return this.mPlacementId;
    }

    public String getPlacementName() {
        return this.mPlacementName;
    }

    public String toString() {
        return "placement name: " + this.mPlacementName;
    }

    public PlacementAvailabilitySettings getPlacementAvailabilitySettings() {
        return this.mPlacementAvailabilitySettings;
    }
}
