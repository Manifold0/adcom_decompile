// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads.purchasing;

public interface IPurchasing
{
    void onGetProductCatalog();
    
    void onGetPurchasingVersion();
    
    void onInitializePurchasing();
    
    void onPurchasingCommand(final String p0);
}
