// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.configuration;

import com.unity3d.services.core.request.NetworkIOException;
import org.json.JSONException;
import java.io.IOException;
import org.json.JSONObject;
import java.util.List;
import com.unity3d.services.core.request.WebRequest;
import com.unity3d.services.core.log.DeviceLog;
import java.net.MalformedURLException;
import java.util.HashMap;
import com.unity3d.services.core.properties.SdkProperties;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;

public class Configuration
{
    private String[] _moduleConfigurationList;
    private Map<String, IModuleConfiguration> _moduleConfigurations;
    private String _url;
    private Class[] _webAppApiClassList;
    private String _webViewData;
    private String _webViewHash;
    private String _webViewUrl;
    private String _webViewVersion;
    
    public Configuration() {
        this._moduleConfigurationList = new String[] { "com.unity3d.services.core.configuration.CoreModuleConfiguration", "com.unity3d.services.ads.configuration.AdsModuleConfiguration", "com.unity3d.services.monetization.core.configuration.MonetizationModuleConfiguration", "com.unity3d.services.purchasing.core.configuration.PurchasingModuleConfiguration", "com.unity3d.services.analytics.core.configuration.AnalyticsModuleConfiguration", "com.unity3d.services.ar.configuration.ARModuleConfiguration", "com.unity3d.services.banners.configuration.BannersModuleConfiguration" };
    }
    
    public Configuration(final String url) {
        this._moduleConfigurationList = new String[] { "com.unity3d.services.core.configuration.CoreModuleConfiguration", "com.unity3d.services.ads.configuration.AdsModuleConfiguration", "com.unity3d.services.monetization.core.configuration.MonetizationModuleConfiguration", "com.unity3d.services.purchasing.core.configuration.PurchasingModuleConfiguration", "com.unity3d.services.analytics.core.configuration.AnalyticsModuleConfiguration", "com.unity3d.services.ar.configuration.ARModuleConfiguration", "com.unity3d.services.banners.configuration.BannersModuleConfiguration" };
        this._url = url;
    }
    
    private void createWebAppApiClassList() {
        final ArrayList list = new ArrayList();
        final String[] moduleConfigurationList = this.getModuleConfigurationList();
        for (int length = moduleConfigurationList.length, i = 0; i < length; ++i) {
            final IModuleConfiguration moduleConfiguration = this.getModuleConfiguration(moduleConfigurationList[i]);
            if (moduleConfiguration != null && moduleConfiguration.getWebAppApiClassList() != null) {
                list.addAll(Arrays.asList((Class[])moduleConfiguration.getWebAppApiClassList()));
            }
        }
        this._webAppApiClassList = (Class[])list.toArray(new Class[list.size()]);
    }
    
    protected String buildQueryString() {
        return "?ts=" + System.currentTimeMillis() + "&sdkVersion=" + SdkProperties.getVersionCode() + "&sdkVersionName=" + SdkProperties.getVersionName();
    }
    
    public String getConfigUrl() {
        return this._url;
    }
    
    public IModuleConfiguration getModuleConfiguration(final String s) {
        IModuleConfiguration moduleConfiguration = null;
        if (this._moduleConfigurations != null && this._moduleConfigurations.containsKey(s)) {
            moduleConfiguration = this._moduleConfigurations.get(s);
        }
        else {
            try {
                final IModuleConfiguration moduleConfiguration2 = (IModuleConfiguration)Class.forName(s).newInstance();
                if (moduleConfiguration2 != null) {
                    if (this._moduleConfigurations == null) {
                        (this._moduleConfigurations = new HashMap<String, IModuleConfiguration>()).put(s, moduleConfiguration2);
                    }
                    return moduleConfiguration2;
                }
            }
            catch (Exception ex) {
                return null;
            }
        }
        return moduleConfiguration;
    }
    
    public String[] getModuleConfigurationList() {
        return this._moduleConfigurationList;
    }
    
    public Class[] getWebAppApiClassList() {
        if (this._webAppApiClassList == null) {
            this.createWebAppApiClassList();
        }
        return this._webAppApiClassList;
    }
    
    public String getWebViewData() {
        return this._webViewData;
    }
    
    public String getWebViewHash() {
        return this._webViewHash;
    }
    
    public String getWebViewUrl() {
        return this._webViewUrl;
    }
    
    public String getWebViewVersion() {
        return this._webViewVersion;
    }
    
    protected void makeRequest() throws IOException, JSONException, IllegalStateException, NetworkIOException {
        if (this._url == null) {
            throw new MalformedURLException("Base URL is null");
        }
        final String string = this._url + this.buildQueryString();
        DeviceLog.debug("Requesting configuration with: " + string);
        final JSONObject jsonObject = new JSONObject(new WebRequest(string, "GET", null).makeRequest());
        this._webViewUrl = jsonObject.getString("url");
        if (!jsonObject.isNull("hash")) {
            this._webViewHash = jsonObject.getString("hash");
        }
        if (jsonObject.has("version")) {
            this._webViewVersion = jsonObject.getString("version");
        }
        if (this._webViewUrl == null || this._webViewUrl.isEmpty()) {
            throw new MalformedURLException("Invalid data. Web view URL is null or empty");
        }
    }
    
    public void setConfigUrl(final String url) {
        this._url = url;
    }
    
    public void setWebViewData(final String webViewData) {
        this._webViewData = webViewData;
    }
    
    public void setWebViewHash(final String webViewHash) {
        this._webViewHash = webViewHash;
    }
    
    public void setWebViewUrl(final String webViewUrl) {
        this._webViewUrl = webViewUrl;
    }
}
