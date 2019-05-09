// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

public interface IBillingServiceManager
{
    void dispose();
    
    void initialise() throws GooglePlayBillingUnAvailableException;
    
    void workWith(final BillingServiceProcessor p0);
}
