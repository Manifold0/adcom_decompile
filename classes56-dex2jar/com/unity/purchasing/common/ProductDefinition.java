// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

public class ProductDefinition
{
    public String id;
    public String storeSpecificId;
    public ProductType type;
    
    public ProductDefinition(final String s, final ProductType productType) {
        this(s, s, productType);
    }
    
    public ProductDefinition(final String id, final String storeSpecificId, final ProductType type) {
        this.id = id;
        this.storeSpecificId = storeSpecificId;
        this.type = type;
    }
}
