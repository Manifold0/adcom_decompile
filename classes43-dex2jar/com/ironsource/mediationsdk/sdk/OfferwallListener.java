// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.sdk;

import com.ironsource.mediationsdk.logger.IronSourceError;

public interface OfferwallListener
{
    void onGetOfferwallCreditsFailed(final IronSourceError p0);
    
    boolean onOfferwallAdCredited(final int p0, final int p1, final boolean p2);
    
    void onOfferwallAvailable(final boolean p0);
    
    void onOfferwallClosed();
    
    void onOfferwallOpened();
    
    void onOfferwallShowFailed(final IronSourceError p0);
}
