// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import java.util.List;

public interface IStoreCallback
{
    void OnProductsRetrieved(final List<ProductDescription> p0);
    
    void OnPurchaseFailed(final PurchaseFailureDescription p0);
    
    void OnPurchaseSucceeded(final String p0, final String p1, final String p2);
    
    void OnSetupFailed(final InitializationFailureReason p0);
}
