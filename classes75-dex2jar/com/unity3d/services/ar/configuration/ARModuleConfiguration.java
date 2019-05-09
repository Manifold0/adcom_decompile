// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ar.configuration;

import com.unity3d.services.ar.ARUtils;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.ar.ARCheck;
import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.ar.api.AR;
import com.unity3d.services.ar.view.ARViewHandler;
import java.util.HashMap;
import java.util.Map;
import com.unity3d.services.ads.configuration.IAdsModuleConfiguration;

public class ARModuleConfiguration implements IAdsModuleConfiguration
{
    @Override
    public Map<String, Class> getAdUnitViewHandlers() {
        final HashMap<String, Class<ARViewHandler>> hashMap = (HashMap<String, Class<ARViewHandler>>)new HashMap<String, Class>();
        hashMap.put("arview", ARViewHandler.class);
        return (Map<String, Class>)hashMap;
    }
    
    @Override
    public Class[] getWebAppApiClassList() {
        return new Class[] { AR.class };
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
        if (ARCheck.isFrameworkPresent()) {
            ARUtils.isSupported(ClientProperties.getApplicationContext());
        }
        return true;
    }
    
    @Override
    public boolean resetState(final Configuration configuration) {
        return true;
    }
}
