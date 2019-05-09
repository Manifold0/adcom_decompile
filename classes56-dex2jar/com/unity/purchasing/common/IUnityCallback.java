// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

public interface IUnityCallback
{
    void OnProductsRetrieved(final String p0);
    
    void OnPurchaseFailed(final String p0);
    
    void OnPurchaseSucceeded(final String p0, final String p1, final String p2);
    
    void OnSetupFailed(final String p0);
}
