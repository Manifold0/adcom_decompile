// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import java.util.ArrayList;
import java.util.Collection;
import com.ironsource.sdk.listeners.OnAdProductListener;
import android.text.TextUtils;
import com.ironsource.sdk.data.SSAEnums;
import java.util.LinkedHashMap;
import com.ironsource.sdk.data.DemandSource;
import java.util.Map;

public class DemandSourceManager
{
    private Map<String, DemandSource> mInterstitialDemandSourceMap;
    private Map<String, DemandSource> mRewardedVideoDemandSourceMap;
    
    public DemandSourceManager() {
        this.mRewardedVideoDemandSourceMap = new LinkedHashMap<String, DemandSource>();
        this.mInterstitialDemandSourceMap = new LinkedHashMap<String, DemandSource>();
    }
    
    private Map<String, DemandSource> getMapByProductType(final SSAEnums.ProductType productType) {
        if (productType.name().equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.name())) {
            return this.mRewardedVideoDemandSourceMap;
        }
        if (productType.name().equalsIgnoreCase(SSAEnums.ProductType.Interstitial.name())) {
            return this.mInterstitialDemandSourceMap;
        }
        return null;
    }
    
    private void put(final SSAEnums.ProductType productType, final String s, final DemandSource demandSource) {
        if (!TextUtils.isEmpty((CharSequence)s) && demandSource != null) {
            final Map<String, DemandSource> mapByProductType = this.getMapByProductType(productType);
            if (mapByProductType != null) {
                mapByProductType.put(s, demandSource);
            }
        }
    }
    
    public DemandSource createDemandSource(final SSAEnums.ProductType productType, final String s, final Map<String, String> map, final OnAdProductListener onAdProductListener) {
        final DemandSource demandSource = new DemandSource(s, map, onAdProductListener);
        this.put(productType, s, demandSource);
        return demandSource;
    }
    
    public DemandSource getDemandSourceByName(final SSAEnums.ProductType productType, final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final Map<String, DemandSource> mapByProductType = this.getMapByProductType(productType);
            if (mapByProductType != null) {
                return mapByProductType.get(s);
            }
        }
        return null;
    }
    
    public Collection<DemandSource> getDemandSources(final SSAEnums.ProductType productType) {
        final Map<String, DemandSource> mapByProductType = this.getMapByProductType(productType);
        if (mapByProductType != null) {
            return mapByProductType.values();
        }
        return new ArrayList<DemandSource>();
    }
}
