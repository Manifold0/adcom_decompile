// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import android.text.TextUtils;
import java.util.ArrayList;

public class ProviderOrder
{
    private ArrayList<String> mBannerProviderOrder;
    private String mISBackFillProvider;
    private String mISPremiumProvider;
    private ArrayList<String> mInterstitialProviderOrder;
    private String mRVBackFillProvider;
    private String mRVPremiumProvider;
    private ArrayList<String> mRewardedVideoProviderOrder;
    
    public ProviderOrder() {
        this.mRewardedVideoProviderOrder = new ArrayList<String>();
        this.mInterstitialProviderOrder = new ArrayList<String>();
        this.mBannerProviderOrder = new ArrayList<String>();
    }
    
    public void addBannerProvider(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.mBannerProviderOrder.add(s);
        }
    }
    
    public void addInterstitialProvider(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.mInterstitialProviderOrder.add(s);
        }
    }
    
    public void addRewardedVideoProvider(final String s) {
        if (!TextUtils.isEmpty((CharSequence)s)) {
            this.mRewardedVideoProviderOrder.add(s);
        }
    }
    
    public ArrayList<String> getBannerProviderOrder() {
        return this.mBannerProviderOrder;
    }
    
    public String getISBackFillProvider() {
        return this.mISBackFillProvider;
    }
    
    public String getISPremiumProvider() {
        return this.mISPremiumProvider;
    }
    
    public ArrayList<String> getInterstitialProviderOrder() {
        return this.mInterstitialProviderOrder;
    }
    
    public String getRVBackFillProvider() {
        return this.mRVBackFillProvider;
    }
    
    public String getRVPremiumProvider() {
        return this.mRVPremiumProvider;
    }
    
    public ArrayList<String> getRewardedVideoProviderOrder() {
        return this.mRewardedVideoProviderOrder;
    }
    
    public void setISBackFillProvider(final String misBackFillProvider) {
        this.mISBackFillProvider = misBackFillProvider;
    }
    
    public void setISPremiumProvider(final String misPremiumProvider) {
        this.mISPremiumProvider = misPremiumProvider;
    }
    
    public void setRVBackFillProvider(final String mrvBackFillProvider) {
        this.mRVBackFillProvider = mrvBackFillProvider;
    }
    
    public void setRVPremiumProvider(final String mrvPremiumProvider) {
        this.mRVPremiumProvider = mrvPremiumProvider;
    }
}
