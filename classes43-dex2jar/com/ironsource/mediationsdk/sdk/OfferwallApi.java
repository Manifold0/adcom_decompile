// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import android.app.Activity;

public interface OfferwallApi
{
    void getOfferwallCredits();
    
    void initOfferwall(final Activity p0, final String p1, final String p2);
    
    boolean isOfferwallAvailable();
    
    void setOfferwallListener(final OfferwallListener p0);
    
    void showOfferwall();
    
    void showOfferwall(final String p0);
}
