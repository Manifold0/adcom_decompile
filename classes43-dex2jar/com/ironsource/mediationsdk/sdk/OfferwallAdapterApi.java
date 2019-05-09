// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import org.json.JSONObject;
import android.app.Activity;

public interface OfferwallAdapterApi
{
    void getOfferwallCredits();
    
    void initOfferwall(final Activity p0, final String p1, final String p2, final JSONObject p3);
    
    boolean isOfferwallAvailable();
    
    void setInternalOfferwallListener(final InternalOfferwallListener p0);
    
    void showOfferwall(final String p0, final JSONObject p1);
}
