// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import java.util.HashSet;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.ArrayList;

public class ProviderSettingsHolder
{
    private static ProviderSettingsHolder mInstance;
    private ArrayList<ProviderSettings> mProviderSettingsArrayList;
    
    private ProviderSettingsHolder() {
        this.mProviderSettingsArrayList = new ArrayList<ProviderSettings>();
    }
    
    public static ProviderSettingsHolder getProviderSettingsHolder() {
        synchronized (ProviderSettingsHolder.class) {
            if (ProviderSettingsHolder.mInstance == null) {
                ProviderSettingsHolder.mInstance = new ProviderSettingsHolder();
            }
            return ProviderSettingsHolder.mInstance;
        }
    }
    
    public void addProviderSettings(final ProviderSettings providerSettings) {
        if (providerSettings != null) {
            this.mProviderSettingsArrayList.add(providerSettings);
        }
    }
    
    public boolean containsProviderSettings(final String s) {
        final Iterator<ProviderSettings> iterator = this.mProviderSettingsArrayList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getProviderName().equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void fillSubProvidersDetails() {
        for (final ProviderSettings providerSettings : this.mProviderSettingsArrayList) {
            int n;
            if (providerSettings.isMultipleInstances() && !TextUtils.isEmpty((CharSequence)providerSettings.getProviderTypeForReflection())) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                final ProviderSettings providerSettings2 = this.getProviderSettings(providerSettings.getProviderTypeForReflection());
                providerSettings.setInterstitialSettings(IronSourceUtils.mergeJsons(providerSettings.getInterstitialSettings(), providerSettings2.getInterstitialSettings()));
                providerSettings.setRewardedVideoSettings(IronSourceUtils.mergeJsons(providerSettings.getRewardedVideoSettings(), providerSettings2.getRewardedVideoSettings()));
                providerSettings.setBannerSettings(IronSourceUtils.mergeJsons(providerSettings.getBannerSettings(), providerSettings2.getBannerSettings()));
            }
        }
    }
    
    public ProviderSettings getProviderSettings(final String s) {
        for (final ProviderSettings providerSettings : this.mProviderSettingsArrayList) {
            if (providerSettings.getProviderName().equals(s)) {
                return providerSettings;
            }
        }
        final ProviderSettings providerSettings2 = new ProviderSettings(s);
        this.addProviderSettings(providerSettings2);
        return providerSettings2;
    }
    
    public ArrayList<ProviderSettings> getProviderSettingsArrayList() {
        return this.mProviderSettingsArrayList;
    }
    
    public HashSet<String> getProviderSettingsByReflectionName(final String s, final String s2) {
        final HashSet<String> set = new HashSet<String>();
        try {
            for (final ProviderSettings providerSettings : this.mProviderSettingsArrayList) {
                if (providerSettings.getProviderTypeForReflection().equals(s)) {
                    if (providerSettings.getRewardedVideoSettings() != null && providerSettings.getRewardedVideoSettings().length() > 0 && !TextUtils.isEmpty((CharSequence)providerSettings.getRewardedVideoSettings().optString(s2))) {
                        set.add(providerSettings.getRewardedVideoSettings().optString(s2));
                    }
                    if (providerSettings.getInterstitialSettings() != null && providerSettings.getInterstitialSettings().length() > 0 && !TextUtils.isEmpty((CharSequence)providerSettings.getInterstitialSettings().optString(s2))) {
                        set.add(providerSettings.getInterstitialSettings().optString(s2));
                    }
                    if (providerSettings.getBannerSettings() == null || providerSettings.getBannerSettings().length() <= 0 || TextUtils.isEmpty((CharSequence)providerSettings.getBannerSettings().optString(s2))) {
                        continue;
                    }
                    set.add(providerSettings.getBannerSettings().optString(s2));
                }
            }
        }
        catch (Exception ex) {}
        return set;
    }
}
