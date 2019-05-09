// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core;

import java.util.Map;

public interface IPurchasingAdapter
{
    void onPurchase(final String p0, final ITransactionListener p1, final Map<String, Object> p2);
    
    void retrieveProducts(final IRetrieveProductsListener p0);
}
