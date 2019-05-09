// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core;

public interface ITransactionListener
{
    void onTransactionComplete(final TransactionDetails p0);
    
    void onTransactionError(final TransactionErrorDetails p0);
}
