// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import java.util.List;

public interface IStore
{
    void FinishTransaction(final ProductDefinition p0, final String p1);
    
    void Purchase(final ProductDefinition p0);
    
    void Purchase(final ProductDefinition p0, final String p1);
    
    void RetrieveProducts(final List<ProductDefinition> p0);
}
