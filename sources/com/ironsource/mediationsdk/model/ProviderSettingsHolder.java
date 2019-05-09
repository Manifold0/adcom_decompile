package com.ironsource.mediationsdk.model;

import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ProviderSettingsHolder {
    private static ProviderSettingsHolder mInstance;
    private ArrayList<ProviderSettings> mProviderSettingsArrayList = new ArrayList();

    public static synchronized ProviderSettingsHolder getProviderSettingsHolder() {
        ProviderSettingsHolder providerSettingsHolder;
        synchronized (ProviderSettingsHolder.class) {
            if (mInstance == null) {
                mInstance = new ProviderSettingsHolder();
            }
            providerSettingsHolder = mInstance;
        }
        return providerSettingsHolder;
    }

    private ProviderSettingsHolder() {
    }

    public void addProviderSettings(ProviderSettings providerSettings) {
        if (providerSettings != null) {
            this.mProviderSettingsArrayList.add(providerSettings);
        }
    }

    public ProviderSettings getProviderSettings(String providerName) {
        Iterator it = this.mProviderSettingsArrayList.iterator();
        while (it.hasNext()) {
            ProviderSettings providerSettings = (ProviderSettings) it.next();
            if (providerSettings.getProviderName().equals(providerName)) {
                return providerSettings;
            }
        }
        ProviderSettings ps = new ProviderSettings(providerName);
        addProviderSettings(ps);
        return ps;
    }

    public HashSet<String> getProviderSettingsByReflectionName(String providerNameForReflection, String fieldName) {
        HashSet<String> result = new HashSet();
        try {
            Iterator it = this.mProviderSettingsArrayList.iterator();
            while (it.hasNext()) {
                ProviderSettings providerSettings = (ProviderSettings) it.next();
                if (providerSettings.getProviderTypeForReflection().equals(providerNameForReflection)) {
                    if (!(providerSettings.getRewardedVideoSettings() == null || providerSettings.getRewardedVideoSettings().length() <= 0 || TextUtils.isEmpty(providerSettings.getRewardedVideoSettings().optString(fieldName)))) {
                        result.add(providerSettings.getRewardedVideoSettings().optString(fieldName));
                    }
                    if (!(providerSettings.getInterstitialSettings() == null || providerSettings.getInterstitialSettings().length() <= 0 || TextUtils.isEmpty(providerSettings.getInterstitialSettings().optString(fieldName)))) {
                        result.add(providerSettings.getInterstitialSettings().optString(fieldName));
                    }
                    if (!(providerSettings.getBannerSettings() == null || providerSettings.getBannerSettings().length() <= 0 || TextUtils.isEmpty(providerSettings.getBannerSettings().optString(fieldName)))) {
                        result.add(providerSettings.getBannerSettings().optString(fieldName));
                    }
                }
            }
        } catch (Exception e) {
        }
        return result;
    }

    public boolean containsProviderSettings(String providerName) {
        Iterator it = this.mProviderSettingsArrayList.iterator();
        while (it.hasNext()) {
            if (((ProviderSettings) it.next()).getProviderName().equals(providerName)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<ProviderSettings> getProviderSettingsArrayList() {
        return this.mProviderSettingsArrayList;
    }

    public void fillSubProvidersDetails() {
        Iterator it = this.mProviderSettingsArrayList.iterator();
        while (it.hasNext()) {
            ProviderSettings providerSettings = (ProviderSettings) it.next();
            boolean isSubProvider = providerSettings.isMultipleInstances() && !TextUtils.isEmpty(providerSettings.getProviderTypeForReflection());
            if (isSubProvider) {
                ProviderSettings commonProviderSettings = getProviderSettings(providerSettings.getProviderTypeForReflection());
                providerSettings.setInterstitialSettings(IronSourceUtils.mergeJsons(providerSettings.getInterstitialSettings(), commonProviderSettings.getInterstitialSettings()));
                providerSettings.setRewardedVideoSettings(IronSourceUtils.mergeJsons(providerSettings.getRewardedVideoSettings(), commonProviderSettings.getRewardedVideoSettings()));
                providerSettings.setBannerSettings(IronSourceUtils.mergeJsons(providerSettings.getBannerSettings(), commonProviderSettings.getBannerSettings()));
            }
        }
    }
}
