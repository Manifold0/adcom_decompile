package com.ironsource.mediationsdk.model;

import java.util.ArrayList;
import java.util.Iterator;

public class BannerConfigurations {
    private static final int DEFAULT_BN_PLACEMENT_ID = 0;
    private int mBNAdaptersSmartLoadAmount;
    private long mBNAdaptersTimeOutInMilliseconds;
    private ApplicationEvents mBNEvents;
    private ArrayList<BannerPlacement> mBNPlacements;
    private int mBNRefreshInterval;
    private BannerPlacement mDefaultBNPlacement;

    public BannerConfigurations() {
        this.mBNEvents = new ApplicationEvents();
        this.mBNPlacements = new ArrayList();
    }

    public BannerConfigurations(int adaptersSmartLoadAmount, long adaptersSmartLoadTimeoutInMillis, ApplicationEvents events, int refreshInterval) {
        this.mBNPlacements = new ArrayList();
        this.mBNAdaptersSmartLoadAmount = adaptersSmartLoadAmount;
        this.mBNAdaptersTimeOutInMilliseconds = adaptersSmartLoadTimeoutInMillis;
        this.mBNEvents = events;
        this.mBNRefreshInterval = refreshInterval;
    }

    public int getBannerAdaptersSmartLoadAmount() {
        return this.mBNAdaptersSmartLoadAmount;
    }

    public long getBannerAdaptersSmartLoadTimeout() {
        return this.mBNAdaptersTimeOutInMilliseconds;
    }

    public ApplicationEvents getBannerEventsConfigurations() {
        return this.mBNEvents;
    }

    public void addBannerPlacement(BannerPlacement placement) {
        if (placement != null) {
            this.mBNPlacements.add(placement);
            if (placement.getPlacementId() == 0) {
                this.mDefaultBNPlacement = placement;
            }
        }
    }

    public BannerPlacement getBannerPlacement(String placementName) {
        Iterator it = this.mBNPlacements.iterator();
        while (it.hasNext()) {
            BannerPlacement placement = (BannerPlacement) it.next();
            if (placement.getPlacementName().equals(placementName)) {
                return placement;
            }
        }
        return null;
    }

    public BannerPlacement getDefaultBannerPlacement() {
        return this.mDefaultBNPlacement;
    }

    public int getBannerRefreshInterval() {
        return this.mBNRefreshInterval;
    }
}
