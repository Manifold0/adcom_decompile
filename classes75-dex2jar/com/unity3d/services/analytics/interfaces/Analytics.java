// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.analytics.interfaces;

public class Analytics
{
    public static void initialize(final IAnalytics analyticsInterface) {
        com.unity3d.services.analytics.core.api.Analytics.setAnalyticsInterface(analyticsInterface);
    }
}
