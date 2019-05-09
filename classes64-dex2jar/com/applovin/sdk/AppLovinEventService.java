// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.sdk;

import android.content.Intent;
import java.util.Map;

public interface AppLovinEventService
{
    void trackCheckout(final String p0, final Map<String, String> p1);
    
    void trackEvent(final String p0);
    
    void trackEvent(final String p0, final Map<String, String> p1);
    
    void trackInAppPurchase(final Intent p0, final Map<String, String> p1);
}
