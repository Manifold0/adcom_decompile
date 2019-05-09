// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

public interface MicrotransactionServices
{
    boolean hasItem(final String p0);
    
    ReceiptVerificationStatus receiptVerificationStatus(final String p0);
    
    void requestUserItemList();
    
    void verifyTransaction(final String p0, final String p1);
    
    void verifyTransaction(final String p0, final String p1, final ReceiptVerificationListener p2);
    
    public interface ReceiptVerificationListener
    {
        void receiptVerificationComplete(final boolean p0);
    }
    
    public enum ReceiptVerificationStatus
    {
        INVALID, 
        PROCESSING, 
        UNKNOWN, 
        VALID;
    }
}
