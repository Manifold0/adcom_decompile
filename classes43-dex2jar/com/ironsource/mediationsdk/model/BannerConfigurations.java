// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import java.util.Iterator;
import java.util.ArrayList;

public class BannerConfigurations
{
    private static final int DEFAULT_BN_PLACEMENT_ID = 0;
    private int mBNAdaptersSmartLoadAmount;
    private long mBNAdaptersTimeOutInMilliseconds;
    private ApplicationEvents mBNEvents;
    private ArrayList<BannerPlacement> mBNPlacements;
    private int mBNRefreshInterval;
    private BannerPlacement mDefaultBNPlacement;
    
    public BannerConfigurations() {
        this.mBNEvents = new ApplicationEvents();
        this.mBNPlacements = new ArrayList<BannerPlacement>();
    }
    
    public BannerConfigurations(final int mbnAdaptersSmartLoadAmount, final long mbnAdaptersTimeOutInMilliseconds, final ApplicationEvents mbnEvents, final int mbnRefreshInterval) {
        this.mBNPlacements = new ArrayList<BannerPlacement>();
        this.mBNAdaptersSmartLoadAmount = mbnAdaptersSmartLoadAmount;
        this.mBNAdaptersTimeOutInMilliseconds = mbnAdaptersTimeOutInMilliseconds;
        this.mBNEvents = mbnEvents;
        this.mBNRefreshInterval = mbnRefreshInterval;
    }
    
    public void addBannerPlacement(final BannerPlacement mDefaultBNPlacement) {
        if (mDefaultBNPlacement != null) {
            this.mBNPlacements.add(mDefaultBNPlacement);
            if (mDefaultBNPlacement.getPlacementId() == 0) {
                this.mDefaultBNPlacement = mDefaultBNPlacement;
            }
        }
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
    
    public BannerPlacement getBannerPlacement(final String s) {
        for (final BannerPlacement bannerPlacement : this.mBNPlacements) {
            if (bannerPlacement.getPlacementName().equals(s)) {
                return bannerPlacement;
            }
        }
        return null;
    }
    
    public int getBannerRefreshInterval() {
        return this.mBNRefreshInterval;
    }
    
    public BannerPlacement getDefaultBannerPlacement() {
        return this.mDefaultBNPlacement;
    }
}
