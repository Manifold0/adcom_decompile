// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.common;

import java.math.BigDecimal;

public class ProductMetadata
{
    public final String isoCurrencyCode;
    public final String localizedDescription;
    public final BigDecimal localizedPrice;
    public final String localizedPriceString;
    public final String localizedTitle;
    
    public ProductMetadata(final String s, final String localizedTitle, final String localizedDescription, final String isoCurrencyCode, final BigDecimal localizedPrice) {
        String localizedPriceString = s;
        if (s == null) {
            localizedPriceString = "";
        }
        this.localizedPriceString = localizedPriceString;
        this.localizedTitle = localizedTitle;
        this.localizedDescription = localizedDescription;
        this.isoCurrencyCode = isoCurrencyCode;
        this.localizedPrice = localizedPrice;
    }
    
    @Override
    public String toString() {
        return "{ProductMetadata: localizedPriceString = " + this.localizedPriceString + ", localizedTitle = " + this.localizedTitle + ", localizedDescription = " + this.localizedDescription + ", isoCurrencyCode = " + this.isoCurrencyCode + ", localizedPrice = " + this.localizedPrice + ", }";
    }
}
