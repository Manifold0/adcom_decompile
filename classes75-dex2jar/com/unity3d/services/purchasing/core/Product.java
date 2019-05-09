// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core;

public class Product
{
    private String isoCurrencyCode;
    private String localizedDescription;
    private double localizedPrice;
    private String localizedPriceString;
    private String localizedTitle;
    private String productId;
    private String productType;
    
    private Product(final Builder builder) {
        this.productId = builder.productId;
        this.localizedPriceString = builder.localizedPriceString;
        this.localizedTitle = builder.localizedTitle;
        this.isoCurrencyCode = builder.isoCurrencyCode;
        this.localizedPrice = builder.localizedPrice;
        this.localizedDescription = builder.localizedDescription;
        this.productType = builder.productType;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public String getIsoCurrencyCode() {
        return this.isoCurrencyCode;
    }
    
    public String getLocalizedDescription() {
        return this.localizedDescription;
    }
    
    public double getLocalizedPrice() {
        return this.localizedPrice;
    }
    
    public String getLocalizedPriceString() {
        return this.localizedPriceString;
    }
    
    public String getLocalizedTitle() {
        return this.localizedTitle;
    }
    
    public String getProductId() {
        return this.productId;
    }
    
    public String getProductType() {
        return this.productType;
    }
    
    public static final class Builder
    {
        private String isoCurrencyCode;
        private String localizedDescription;
        private double localizedPrice;
        private String localizedPriceString;
        private String localizedTitle;
        private String productId;
        private String productType;
        
        private Builder() {
        }
        
        public Product build() {
            return new Product(this, null);
        }
        
        public Builder withIsoCurrencyCode(final String isoCurrencyCode) {
            this.isoCurrencyCode = isoCurrencyCode;
            return this;
        }
        
        public Builder withLocalizedDescription(final String localizedDescription) {
            this.localizedDescription = localizedDescription;
            return this;
        }
        
        public Builder withLocalizedPrice(final double localizedPrice) {
            this.localizedPrice = localizedPrice;
            return this;
        }
        
        public Builder withLocalizedPriceString(final String localizedPriceString) {
            this.localizedPriceString = localizedPriceString;
            return this;
        }
        
        public Builder withLocalizedTitle(final String localizedTitle) {
            this.localizedTitle = localizedTitle;
            return this;
        }
        
        public Builder withProductId(final String productId) {
            this.productId = productId;
            return this;
        }
        
        public Builder withProductType(final String productType) {
            this.productType = productType;
            return this;
        }
    }
}
