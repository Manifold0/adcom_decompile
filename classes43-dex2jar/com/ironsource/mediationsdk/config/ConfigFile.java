// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.config;

import java.util.Arrays;

public class ConfigFile
{
    private static ConfigFile mInstance;
    private String mPluginFrameworkVersion;
    private String mPluginType;
    private String mPluginVersion;
    private String[] mSupportedPlugins;
    
    public ConfigFile() {
        this.mSupportedPlugins = new String[] { "Unity", "AdobeAir", "Xamarin", "Corona", "AdMob", "MoPub" };
    }
    
    public static ConfigFile getConfigFile() {
        synchronized (ConfigFile.class) {
            if (ConfigFile.mInstance == null) {
                ConfigFile.mInstance = new ConfigFile();
            }
            return ConfigFile.mInstance;
        }
    }
    
    public String getPluginFrameworkVersion() {
        return this.mPluginFrameworkVersion;
    }
    
    public String getPluginType() {
        return this.mPluginType;
    }
    
    public String getPluginVersion() {
        return this.mPluginVersion;
    }
    
    public void setPluginData(final String mPluginType, final String mPluginVersion, final String mPluginFrameworkVersion) {
        if (mPluginType != null) {
            if (Arrays.asList(this.mSupportedPlugins).contains(mPluginType)) {
                this.mPluginType = mPluginType;
            }
            else {
                this.mPluginType = null;
            }
        }
        if (mPluginVersion != null) {
            this.mPluginVersion = mPluginVersion;
        }
        if (mPluginFrameworkVersion != null) {
            this.mPluginFrameworkVersion = mPluginFrameworkVersion;
        }
    }
}
