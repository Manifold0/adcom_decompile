// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import java.util.Iterator;
import java.util.ArrayList;

public class RewardedVideoConfigurations
{
    private static final int DEFAULT_RV_PLACEMENT_ID = 0;
    private String mBackFillProviderName;
    private Placement mDefaultRVPlacement;
    private String mPremiumProviderName;
    private int mRVAdaptersSmartLoadAmount;
    private int mRVAdaptersTimeOutInSeconds;
    private ApplicationEvents mRVEvents;
    private ArrayList<Placement> mRVPlacements;
    
    public RewardedVideoConfigurations() {
        this.mRVPlacements = new ArrayList<Placement>();
        this.mRVEvents = new ApplicationEvents();
    }
    
    public RewardedVideoConfigurations(final int mrvAdaptersSmartLoadAmount, final int mrvAdaptersTimeOutInSeconds, final ApplicationEvents mrvEvents) {
        this.mRVPlacements = new ArrayList<Placement>();
        this.mRVAdaptersSmartLoadAmount = mrvAdaptersSmartLoadAmount;
        this.mRVAdaptersTimeOutInSeconds = mrvAdaptersTimeOutInSeconds;
        this.mRVEvents = mrvEvents;
    }
    
    public void addRewardedVideoPlacement(final Placement mDefaultRVPlacement) {
        if (mDefaultRVPlacement != null) {
            this.mRVPlacements.add(mDefaultRVPlacement);
            if (mDefaultRVPlacement.getPlacementId() == 0) {
                this.mDefaultRVPlacement = mDefaultRVPlacement;
            }
        }
    }
    
    public String getBackFillProviderName() {
        return this.mBackFillProviderName;
    }
    
    public Placement getDefaultRewardedVideoPlacement() {
        return this.mDefaultRVPlacement;
    }
    
    public String getPremiumProviderName() {
        return this.mPremiumProviderName;
    }
    
    public int getRewardedVideoAdaptersSmartLoadAmount() {
        return this.mRVAdaptersSmartLoadAmount;
    }
    
    public int getRewardedVideoAdaptersSmartLoadTimeout() {
        return this.mRVAdaptersTimeOutInSeconds;
    }
    
    public ApplicationEvents getRewardedVideoEventsConfigurations() {
        return this.mRVEvents;
    }
    
    public Placement getRewardedVideoPlacement(final String s) {
        for (final Placement placement : this.mRVPlacements) {
            if (placement.getPlacementName().equals(s)) {
                return placement;
            }
        }
        return null;
    }
    
    public void setBackFillProviderName(final String mBackFillProviderName) {
        this.mBackFillProviderName = mBackFillProviderName;
    }
    
    public void setPremiumProviderName(final String mPremiumProviderName) {
        this.mPremiumProviderName = mPremiumProviderName;
    }
}
