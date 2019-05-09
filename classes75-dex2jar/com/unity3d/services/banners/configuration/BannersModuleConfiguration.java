// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners.configuration;

import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.banners.api.BannerListener;
import com.unity3d.services.banners.api.Banner;
import com.unity3d.services.core.configuration.IModuleConfiguration;

public class BannersModuleConfiguration implements IModuleConfiguration
{
    private static final Class[] WEB_APP_API_CLASS_LIST;
    
    static {
        WEB_APP_API_CLASS_LIST = new Class[] { Banner.class, BannerListener.class };
    }
    
    @Override
    public Class[] getWebAppApiClassList() {
        return BannersModuleConfiguration.WEB_APP_API_CLASS_LIST;
    }
    
    @Override
    public boolean initCompleteState(final Configuration configuration) {
        return true;
    }
    
    @Override
    public boolean initErrorState(final Configuration configuration, final String s, final String s2) {
        return true;
    }
    
    @Override
    public boolean initModuleState(final Configuration configuration) {
        return true;
    }
    
    @Override
    public boolean resetState(final Configuration configuration) {
        return true;
    }
}
