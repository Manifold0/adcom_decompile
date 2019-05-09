// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import org.json.JSONArray;
import org.json.JSONObject;
import android.net.Uri;
import java.util.EnumSet;
import java.util.Map;

public final class FetchedAppSettings
{
    private boolean IAPAutomaticLoggingEnabled;
    private boolean automaticLoggingEnabled;
    private boolean customTabsEnabled;
    private Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap;
    private FacebookRequestErrorClassification errorClassification;
    private String nuxContent;
    private boolean nuxEnabled;
    private String sdkUpdateMessage;
    private int sessionTimeoutInSeconds;
    private String smartLoginBookmarkIconURL;
    private String smartLoginMenuIconURL;
    private EnumSet<SmartLoginOption> smartLoginOptions;
    private boolean supportsImplicitLogging;
    
    public FetchedAppSettings(final boolean supportsImplicitLogging, final String nuxContent, final boolean nuxEnabled, final boolean customTabsEnabled, final int sessionTimeoutInSeconds, final EnumSet<SmartLoginOption> smartLoginOptions, final Map<String, Map<String, DialogFeatureConfig>> dialogConfigMap, final boolean automaticLoggingEnabled, final FacebookRequestErrorClassification errorClassification, final String smartLoginBookmarkIconURL, final String smartLoginMenuIconURL, final boolean iapAutomaticLoggingEnabled, final String sdkUpdateMessage) {
        this.supportsImplicitLogging = supportsImplicitLogging;
        this.nuxContent = nuxContent;
        this.nuxEnabled = nuxEnabled;
        this.customTabsEnabled = customTabsEnabled;
        this.dialogConfigMap = dialogConfigMap;
        this.errorClassification = errorClassification;
        this.sessionTimeoutInSeconds = sessionTimeoutInSeconds;
        this.automaticLoggingEnabled = automaticLoggingEnabled;
        this.smartLoginOptions = smartLoginOptions;
        this.smartLoginBookmarkIconURL = smartLoginBookmarkIconURL;
        this.smartLoginMenuIconURL = smartLoginMenuIconURL;
        this.IAPAutomaticLoggingEnabled = iapAutomaticLoggingEnabled;
        this.sdkUpdateMessage = sdkUpdateMessage;
    }
    
    public static DialogFeatureConfig getDialogFeatureConfig(final String s, final String s2, final String s3) {
        if (!Utility.isNullOrEmpty(s2) && !Utility.isNullOrEmpty(s3)) {
            final FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(s);
            if (appSettingsWithoutQuery != null) {
                final Map<String, DialogFeatureConfig> map = appSettingsWithoutQuery.getDialogConfigurations().get(s2);
                if (map != null) {
                    return map.get(s3);
                }
            }
        }
        return null;
    }
    
    public boolean getAutomaticLoggingEnabled() {
        return this.automaticLoggingEnabled;
    }
    
    public boolean getCustomTabsEnabled() {
        return this.customTabsEnabled;
    }
    
    public Map<String, Map<String, DialogFeatureConfig>> getDialogConfigurations() {
        return this.dialogConfigMap;
    }
    
    public FacebookRequestErrorClassification getErrorClassification() {
        return this.errorClassification;
    }
    
    public boolean getIAPAutomaticLoggingEnabled() {
        return this.IAPAutomaticLoggingEnabled;
    }
    
    public String getNuxContent() {
        return this.nuxContent;
    }
    
    public boolean getNuxEnabled() {
        return this.nuxEnabled;
    }
    
    public String getSdkUpdateMessage() {
        return this.sdkUpdateMessage;
    }
    
    public int getSessionTimeoutInSeconds() {
        return this.sessionTimeoutInSeconds;
    }
    
    public String getSmartLoginBookmarkIconURL() {
        return this.smartLoginBookmarkIconURL;
    }
    
    public String getSmartLoginMenuIconURL() {
        return this.smartLoginMenuIconURL;
    }
    
    public EnumSet<SmartLoginOption> getSmartLoginOptions() {
        return this.smartLoginOptions;
    }
    
    public boolean supportsImplicitLogging() {
        return this.supportsImplicitLogging;
    }
    
    public static class DialogFeatureConfig
    {
        private static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "\\|";
        private static final String DIALOG_CONFIG_NAME_KEY = "name";
        private static final String DIALOG_CONFIG_URL_KEY = "url";
        private static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
        private String dialogName;
        private Uri fallbackUrl;
        private String featureName;
        private int[] featureVersionSpec;
        
        private DialogFeatureConfig(final String dialogName, final String featureName, final Uri fallbackUrl, final int[] featureVersionSpec) {
            this.dialogName = dialogName;
            this.featureName = featureName;
            this.fallbackUrl = fallbackUrl;
            this.featureVersionSpec = featureVersionSpec;
        }
        
        public static DialogFeatureConfig parseDialogConfig(final JSONObject jsonObject) {
            final String optString = jsonObject.optString("name");
            if (!Utility.isNullOrEmpty(optString)) {
                final String[] split = optString.split("\\|");
                if (split.length == 2) {
                    final String s = split[0];
                    final String s2 = split[1];
                    if (!Utility.isNullOrEmpty(s) && !Utility.isNullOrEmpty(s2)) {
                        final String optString2 = jsonObject.optString("url");
                        Uri parse = null;
                        if (!Utility.isNullOrEmpty(optString2)) {
                            parse = Uri.parse(optString2);
                        }
                        return new DialogFeatureConfig(s, s2, parse, parseVersionSpec(jsonObject.optJSONArray("versions")));
                    }
                }
            }
            return null;
        }
        
        private static int[] parseVersionSpec(final JSONArray jsonArray) {
            int[] array = null;
            if (jsonArray != null) {
                final int length = jsonArray.length();
                final int[] array2 = new int[length];
                int n = 0;
            Label_0068_Outer:
                while (true) {
                    array = array2;
                    if (n < length) {
                        final int optInt = jsonArray.optInt(n, -1);
                        while (true) {
                            int int1;
                            if ((int1 = optInt) != -1) {
                                break Label_0068;
                            }
                            final String optString = jsonArray.optString(n);
                            int1 = optInt;
                            if (Utility.isNullOrEmpty(optString)) {
                                break Label_0068;
                            }
                            try {
                                int1 = Integer.parseInt(optString);
                                array2[n] = int1;
                                ++n;
                                continue Label_0068_Outer;
                            }
                            catch (NumberFormatException ex) {
                                Utility.logd("FacebookSDK", ex);
                                int1 = -1;
                                continue;
                            }
                            break;
                        }
                        break;
                    }
                    break;
                }
            }
            return array;
        }
        
        public String getDialogName() {
            return this.dialogName;
        }
        
        public Uri getFallbackUrl() {
            return this.fallbackUrl;
        }
        
        public String getFeatureName() {
            return this.featureName;
        }
        
        public int[] getVersionSpec() {
            return this.featureVersionSpec;
        }
    }
}
