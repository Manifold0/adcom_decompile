// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.monetization.core.properties;

import com.unity3d.services.monetization.IUnityMonetizationListener;

public class ClientProperties
{
    private static IUnityMonetizationListener listener;
    private static boolean monetizationEnabled;
    
    public static IUnityMonetizationListener getListener() {
        return ClientProperties.listener;
    }
    
    public static boolean isMonetizationEnabled() {
        return ClientProperties.monetizationEnabled;
    }
    
    public static void setListener(final IUnityMonetizationListener listener) {
        ClientProperties.listener = listener;
    }
    
    public static void setMonetizationEnabled(final boolean monetizationEnabled) {
        ClientProperties.monetizationEnabled = monetizationEnabled;
    }
}
