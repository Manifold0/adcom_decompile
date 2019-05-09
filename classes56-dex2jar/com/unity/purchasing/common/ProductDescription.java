// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

public class ProductDescription
{
    public final ProductMetadata metadata;
    public final String receipt;
    public final String storeSpecificId;
    public final String transactionId;
    
    public ProductDescription(final String storeSpecificId, final ProductMetadata metadata, final String receipt, final String transactionId) {
        this.storeSpecificId = storeSpecificId;
        this.metadata = metadata;
        this.receipt = receipt;
        this.transactionId = transactionId;
    }
    
    @Override
    public String toString() {
        return "{ProductDescription: storeSpecificId = " + this.storeSpecificId + ", metadata = " + this.metadata + ", receipt = " + this.receipt + ", transactionId = " + this.transactionId + ", }";
    }
}
