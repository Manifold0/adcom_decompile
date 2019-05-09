// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.configuration;

import java.util.Map;
import com.unity3d.services.core.configuration.IModuleConfiguration;

public interface IAdsModuleConfiguration extends IModuleConfiguration
{
    Map<String, Class> getAdUnitViewHandlers();
}
