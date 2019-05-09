package com.ironsource.mediationsdk.model;

import java.util.ArrayList;
import java.util.Iterator;

public class InterstitialConfigurations {
    private static final int DEFAULT_IS_PLACEMENT_ID = 0;
    private String mBackFillProviderName;
    private InterstitialPlacement mDefaultISPlacement;
    private int mISAdaptersSmartLoadAmount;
    private int mISAdaptersTimeOutInSeconds;
    private ApplicationEvents mISEvents;
    private ArrayList<InterstitialPlacement> mISPlacements;
    private String mPremiumProviderName;

    public InterstitialConfigurations() {
        this.mISPlacements = new ArrayList();
        this.mISEvents = new ApplicationEvents();
    }

    public InterstitialConfigurations(int adaptersSmartLoadAmount, int adaptersSmartLoadTimeout, ApplicationEvents events) {
        this.mISPlacements = new ArrayList();
        this.mISAdaptersSmartLoadAmount = adaptersSmartLoadAmount;
        this.mISAdaptersTimeOutInSeconds = adaptersSmartLoadTimeout;
        this.mISEvents = events;
    }

    public void addInterstitialPlacement(InterstitialPlacement placement) {
        if (placement != null) {
            this.mISPlacements.add(placement);
            if (placement.getPlacementId() == 0) {
                this.mDefaultISPlacement = placement;
            }
        }
    }

    public InterstitialPlacement getInterstitialPlacement(String placementName) {
        Iterator it = this.mISPlacements.iterator();
        while (it.hasNext()) {
            InterstitialPlacement placement = (InterstitialPlacement) it.next();
            if (placement.getPlacementName().equals(placementName)) {
                return placement;
            }
        }
        return null;
    }

    public InterstitialPlacement getDefaultInterstitialPlacement() {
        return this.mDefaultISPlacement;
    }

    public int getInterstitialAdaptersSmartLoadAmount() {
        return this.mISAdaptersSmartLoadAmount;
    }

    public int getInterstitialAdaptersSmartLoadTimeout() {
        return this.mISAdaptersTimeOutInSeconds;
    }

    public ApplicationEvents getInterstitialEventsConfigurations() {
        return this.mISEvents;
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
