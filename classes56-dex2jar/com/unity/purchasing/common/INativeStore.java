// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

public interface INativeStore
{
    void FinishTransaction(final String p0, final String p1);
    
    void Purchase(final String p0, final String p1);
    
    void RetrieveProducts(final String p0);
}
