package com.unity3d.services.purchasing.core;

public class Product {
    private String isoCurrencyCode;
    private String localizedDescription;
    private double localizedPrice;
    private String localizedPriceString;
    private String localizedTitle;
    private String productId;
    private String productType;

    public static final class Builder {
        private String isoCurrencyCode;
        private String localizedDescription;
        private double localizedPrice;
        private String localizedPriceString;
        private String localizedTitle;
        private String productId;
        private String productType;

        private Builder() {
        }

        public Builder withProductId(String val) {
            this.productId = val;
            return this;
        }

        public Builder withLocalizedPriceString(String val) {
            this.localizedPriceString = val;
            return this;
        }

        public Builder withLocalizedTitle(String val) {
            this.localizedTitle = val;
            return this;
        }

        public Builder withIsoCurrencyCode(String val) {
            this.isoCurrencyCode = val;
            return this;
        }

        public Builder withLocalizedPrice(double val) {
            this.localizedPrice = val;
            return this;
        }

        public Builder withLocalizedDescription(String val) {
            this.localizedDescription = val;
            return this;
        }

        public Builder withProductType(String val) {
            this.productType = val;
            return this;
        }

        public Product build() {
            return new Product();
        }
    }

    private Product(Builder builder) {
        this.productId = builder.productId;
        this.localizedPriceString = builder.localizedPriceString;
        this.localizedTitle = builder.localizedTitle;
        this.isoCurrencyCode = builder.isoCurrencyCode;
        this.localizedPrice = builder.localizedPrice;
        this.localizedDescription = builder.localizedDescription;
        this.productType = builder.productType;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getLocalizedPriceString() {
        return this.localizedPriceString;
    }

    public String getLocalizedTitle() {
        return this.localizedTitle;
    }

    public String getIsoCurrencyCode() {
        return this.isoCurrencyCode;
    }

    public double getLocalizedPrice() {
        return this.localizedPrice;
    }

    public String getLocalizedDescription() {
        return this.localizedDescription;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getProductType() {
        return this.productType;
    }
}
