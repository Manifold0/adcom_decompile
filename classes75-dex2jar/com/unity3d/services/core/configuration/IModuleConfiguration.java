// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.configuration;

public interface IModuleConfiguration
{
    Class[] getWebAppApiClassList();
    
    boolean initCompleteState(final Configuration p0);
    
    boolean initErrorState(final Configuration p0, final String p1, final String p2);
    
    boolean initModuleState(final Configuration p0);
    
    boolean resetState(final Configuration p0);
}
