package com.ironsource.sdk.controller;

import android.text.TextUtils;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.ironsource.sdk.listeners.OnAdProductListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DemandSourceManager {
    private Map<String, DemandSource> mInterstitialDemandSourceMap = new LinkedHashMap();
    private Map<String, DemandSource> mRewardedVideoDemandSourceMap = new LinkedHashMap();

    private Map<String, DemandSource> getMapByProductType(ProductType productType) {
        if (productType.name().equalsIgnoreCase(ProductType.RewardedVideo.name())) {
            return this.mRewardedVideoDemandSourceMap;
        }
        if (productType.name().equalsIgnoreCase(ProductType.Interstitial.name())) {
            return this.mInterstitialDemandSourceMap;
        }
        return null;
    }

    public Collection<DemandSource> getDemandSources(ProductType productType) {
        Map<String, DemandSource> productDemandMap = getMapByProductType(productType);
        if (productDemandMap != null) {
            return productDemandMap.values();
        }
        return new ArrayList();
    }

    public DemandSource getDemandSourceByName(ProductType productType, String demandSourceName) {
        if (!TextUtils.isEmpty(demandSourceName)) {
            Map<String, DemandSource> productDemandMap = getMapByProductType(productType);
            if (productDemandMap != null) {
                return (DemandSource) productDemandMap.get(demandSourceName);
            }
        }
        return null;
    }

    private void put(ProductType productType, String demandSourceName, DemandSource demandSource) {
        if (!TextUtils.isEmpty(demandSourceName) && demandSource != null) {
            Map<String, DemandSource> productDemandMap = getMapByProductType(productType);
            if (productDemandMap != null) {
                productDemandMap.put(demandSourceName, demandSource);
            }
        }
    }

    public DemandSource createDemandSource(ProductType type, String demandSourceName, Map<String, String> demandExtParam, OnAdProductListener listener) {
        DemandSource demandSource = new DemandSource(demandSourceName, demandExtParam, listener);
        put(type, demandSourceName, demandSource);
        return demandSource;
    }
}
