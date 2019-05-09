// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import java.util.Iterator;
import java.util.ArrayList;

public class InterstitialConfigurations
{
    private static final int DEFAULT_IS_PLACEMENT_ID = 0;
    private String mBackFillProviderName;
    private InterstitialPlacement mDefaultISPlacement;
    private int mISAdaptersSmartLoadAmount;
    private int mISAdaptersTimeOutInSeconds;
    private ApplicationEvents mISEvents;
    private ArrayList<InterstitialPlacement> mISPlacements;
    private String mPremiumProviderName;
    
    public InterstitialConfigurations() {
        this.mISPlacements = new ArrayList<InterstitialPlacement>();
        this.mISEvents = new ApplicationEvents();
    }
    
    public InterstitialConfigurations(final int misAdaptersSmartLoadAmount, final int misAdaptersTimeOutInSeconds, final ApplicationEvents misEvents) {
        this.mISPlacements = new ArrayList<InterstitialPlacement>();
        this.mISAdaptersSmartLoadAmount = misAdaptersSmartLoadAmount;
        this.mISAdaptersTimeOutInSeconds = misAdaptersTimeOutInSeconds;
        this.mISEvents = misEvents;
    }
    
    public void addInterstitialPlacement(final InterstitialPlacement mDefaultISPlacement) {
        if (mDefaultISPlacement != null) {
            this.mISPlacements.add(mDefaultISPlacement);
            if (mDefaultISPlacement.getPlacementId() == 0) {
                this.mDefaultISPlacement = mDefaultISPlacement;
            }
        }
    }
    
    public String getBackFillProviderName() {
        return this.mBackFillProviderName;
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
    
    public InterstitialPlacement getInterstitialPlacement(final String s) {
        for (final InterstitialPlacement interstitialPlacement : this.mISPlacements) {
            if (interstitialPlacement.getPlacementName().equals(s)) {
                return interstitialPlacement;
            }
        }
        return null;
    }
    
    public String getPremiumProviderName() {
        return this.mPremiumProviderName;
    }
    
    public void setBackFillProviderName(final String mBackFillProviderName) {
        this.mBackFillProviderName = mBackFillProviderName;
    }
    
    public void setPremiumProviderName(final String mPremiumProviderName) {
        this.mPremiumProviderName = mPremiumProviderName;
    }
}
