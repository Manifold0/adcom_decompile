package com.ironsource.mediationsdk.model;

import java.util.ArrayList;
import java.util.Iterator;

public class RewardedVideoConfigurations {
    private static final int DEFAULT_RV_PLACEMENT_ID = 0;
    private String mBackFillProviderName;
    private Placement mDefaultRVPlacement;
    private String mPremiumProviderName;
    private int mRVAdaptersSmartLoadAmount;
    private int mRVAdaptersTimeOutInSeconds;
    private ApplicationEvents mRVEvents;
    private ArrayList<Placement> mRVPlacements;

    public RewardedVideoConfigurations() {
        this.mRVPlacements = new ArrayList();
        this.mRVEvents = new ApplicationEvents();
    }

    public RewardedVideoConfigurations(int adaptersSmartLoadAmount, int adaptersSmartLoadTimeout, ApplicationEvents events) {
        this.mRVPlacements = new ArrayList();
        this.mRVAdaptersSmartLoadAmount = adaptersSmartLoadAmount;
        this.mRVAdaptersTimeOutInSeconds = adaptersSmartLoadTimeout;
        this.mRVEvents = events;
    }

    public int getRewardedVideoAdaptersSmartLoadTimeout() {
        return this.mRVAdaptersTimeOutInSeconds;
    }

    public void addRewardedVideoPlacement(Placement placement) {
        if (placement != null) {
            this.mRVPlacements.add(placement);
            if (placement.getPlacementId() == 0) {
                this.mDefaultRVPlacement = placement;
            }
        }
    }

    public Placement getRewardedVideoPlacement(String placementName) {
        Iterator it = this.mRVPlacements.iterator();
        while (it.hasNext()) {
            Placement placement = (Placement) it.next();
            if (placement.getPlacementName().equals(placementName)) {
                return placement;
            }
        }
        return null;
    }

    public Placement getDefaultRewardedVideoPlacement() {
        return this.mDefaultRVPlacement;
    }

    public int getRewardedVideoAdaptersSmartLoadAmount() {
        return this.mRVAdaptersSmartLoadAmount;
    }

    public ApplicationEvents getRewardedVideoEventsConfigurations() {
        return this.mRVEvents;
    }

    public String getBackFillProviderName() {
        return this.mBackFillProviderName;
    }

    public void setBackFillProviderName(String backFillProviderName) {
        this.mBackFillProviderName = backFillProviderName;
    }

    public String getPremiumProviderName() {
        return this.mPremiumProviderName;
    }

    public void setPremiumProviderName(String premiumProviderName) {
        this.mPremiumProviderName = premiumProviderName;
    }
}
