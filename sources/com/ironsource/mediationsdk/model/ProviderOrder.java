package com.ironsource.mediationsdk.model;

import android.text.TextUtils;
import java.util.ArrayList;

public class ProviderOrder {
    private ArrayList<String> mBannerProviderOrder = new ArrayList();
    private String mISBackFillProvider;
    private String mISPremiumProvider;
    private ArrayList<String> mInterstitialProviderOrder = new ArrayList();
    private String mRVBackFillProvider;
    private String mRVPremiumProvider;
    private ArrayList<String> mRewardedVideoProviderOrder = new ArrayList();

    public ArrayList<String> getRewardedVideoProviderOrder() {
        return this.mRewardedVideoProviderOrder;
    }

    public ArrayList<String> getInterstitialProviderOrder() {
        return this.mInterstitialProviderOrder;
    }

    public ArrayList<String> getBannerProviderOrder() {
        return this.mBannerProviderOrder;
    }

    public void addRewardedVideoProvider(String rewardedVideoProvider) {
        if (!TextUtils.isEmpty(rewardedVideoProvider)) {
            this.mRewardedVideoProviderOrder.add(rewardedVideoProvider);
        }
    }

    public void addInterstitialProvider(String interstitialProvider) {
        if (!TextUtils.isEmpty(interstitialProvider)) {
            this.mInterstitialProviderOrder.add(interstitialProvider);
        }
    }

    public void addBannerProvider(String bannerProvider) {
        if (!TextUtils.isEmpty(bannerProvider)) {
            this.mBannerProviderOrder.add(bannerProvider);
        }
    }

    public String getRVBackFillProvider() {
        return this.mRVBackFillProvider;
    }

    public void setRVBackFillProvider(String rvBackFillProvider) {
        this.mRVBackFillProvider = rvBackFillProvider;
    }

    public String getRVPremiumProvider() {
        return this.mRVPremiumProvider;
    }

    public void setRVPremiumProvider(String rvPremiumProvider) {
        this.mRVPremiumProvider = rvPremiumProvider;
    }

    public String getISBackFillProvider() {
        return this.mISBackFillProvider;
    }

    public void setISBackFillProvider(String isBackFillProvider) {
        this.mISBackFillProvider = isBackFillProvider;
    }

    public String getISPremiumProvider() {
        return this.mISPremiumProvider;
    }

    public void setISPremiumProvider(String isPremiumProvider) {
        this.mISPremiumProvider = isPremiumProvider;
    }
}
