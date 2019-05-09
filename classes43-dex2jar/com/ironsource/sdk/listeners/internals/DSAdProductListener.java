// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.listeners.internals;

import com.ironsource.sdk.data.AdUnitsReady;
import org.json.JSONObject;
import com.ironsource.sdk.data.SSAEnums;

public interface DSAdProductListener
{
    void onAdProductClick(final SSAEnums.ProductType p0, final String p1);
    
    void onAdProductClose(final SSAEnums.ProductType p0, final String p1);
    
    void onAdProductEventNotificationReceived(final SSAEnums.ProductType p0, final String p1, final String p2, final JSONObject p3);
    
    void onAdProductInitFailed(final SSAEnums.ProductType p0, final String p1, final String p2);
    
    void onAdProductInitSuccess(final SSAEnums.ProductType p0, final String p1, final AdUnitsReady p2);
    
    void onAdProductOpen(final SSAEnums.ProductType p0, final String p1);
}
