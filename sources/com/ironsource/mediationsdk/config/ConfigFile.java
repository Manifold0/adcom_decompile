package com.ironsource.mediationsdk.config;

import java.util.Arrays;

public class ConfigFile {
    private static ConfigFile mInstance;
    private String mPluginFrameworkVersion;
    private String mPluginType;
    private String mPluginVersion;
    private String[] mSupportedPlugins = new String[]{"Unity", "AdobeAir", "Xamarin", "Corona", "AdMob", "MoPub"};

    public static synchronized ConfigFile getConfigFile() {
        ConfigFile configFile;
        synchronized (ConfigFile.class) {
            if (mInstance == null) {
                mInstance = new ConfigFile();
            }
            configFile = mInstance;
        }
        return configFile;
    }

    public void setPluginData(String pluginType, String pluginVersion, String pluginFrameworkVersion) {
        if (pluginType != null) {
            if (Arrays.asList(this.mSupportedPlugins).contains(pluginType)) {
                this.mPluginType = pluginType;
            } else {
                this.mPluginType = null;
            }
        }
        if (pluginVersion != null) {
            this.mPluginVersion = pluginVersion;
        }
        if (pluginFrameworkVersion != null) {
            this.mPluginFrameworkVersion = pluginFrameworkVersion;
        }
    }

    public String getPluginType() {
        return this.mPluginType;
    }

    public String getPluginVersion() {
        return this.mPluginVersion;
    }

    public String getPluginFrameworkVersion() {
        return this.mPluginFrameworkVersion;
    }
}
