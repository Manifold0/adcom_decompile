// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.listeners;

import org.json.JSONObject;

public interface OnOfferWallListener
{
    void onGetOWCreditsFailed(final String p0);
    
    void onOWAdClosed();
    
    boolean onOWAdCredited(final int p0, final int p1, final boolean p2);
    
    void onOWGeneric(final String p0, final String p1);
    
    void onOWShowFail(final String p0);
    
    void onOWShowSuccess(final String p0);
    
    void onOfferwallEventNotificationReceived(final String p0, final JSONObject p1);
    
    void onOfferwallInitFail(final String p0);
    
    void onOfferwallInitSuccess();
}
