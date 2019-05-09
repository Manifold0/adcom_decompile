// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

public class InternalSettings
{
    private static volatile String mCustomUserAgent;
    
    public static String getCustomUserAgent() {
        return InternalSettings.mCustomUserAgent;
    }
    
    public static void setCustomUserAgent(final String mCustomUserAgent) {
        InternalSettings.mCustomUserAgent = mCustomUserAgent;
    }
}
