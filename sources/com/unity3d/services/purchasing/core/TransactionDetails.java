package com.unity3d.services.purchasing.core;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TransactionDetails {
    private String currency;
    private Map<String, Object> extras;
    private BigDecimal price;
    private String productId;
    private String receipt;
    private String transactionId;

    public static final class Builder {
        private String currency;
        private Map<String, Object> extras;
        private BigDecimal price;
        private String productId;
        private String receipt;
        private String transactionId;

        private Builder() {
            this.extras = new HashMap();
        }

        public Builder withProductId(String val) {
            this.productId = val;
            return this;
        }

        public Builder withTransactionId(String val) {
            this.transactionId = val;
            return this;
        }

        public Builder withReceipt(String val) {
            this.receipt = val;
            return this;
        }

        public Builder withPrice(BigDecimal val) {
            this.price = val;
            return this;
        }

        public Builder withPrice(double val) {
            this.price = new BigDecimal(val);
            return this;
        }

        public Builder withCurrency(String val) {
            this.currency = val;
            return this;
        }

        public Builder putExtra(String key, Object value) {
            this.extras.put(key, value);
            return this;
        }

        public TransactionDetails build() {
            return new TransactionDetails();
        }
    }

    private TransactionDetails(Builder builder) {
        this.productId = builder.productId;
        this.transactionId = builder.transactionId;
        this.receipt = builder.receipt;
        this.extras = builder.extras;
        this.price = builder.price;
        this.currency = builder.currency;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public String getReceipt() {
        return this.receipt;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Map<String, Object> getExtras() {
        return this.extras;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
