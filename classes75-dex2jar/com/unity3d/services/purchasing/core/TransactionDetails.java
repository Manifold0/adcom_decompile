// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core;

import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Map;

public class TransactionDetails
{
    private String currency;
    private Map<String, Object> extras;
    private BigDecimal price;
    private String productId;
    private String receipt;
    private String transactionId;
    
    private TransactionDetails(final Builder builder) {
        this.productId = builder.productId;
        this.transactionId = builder.transactionId;
        this.receipt = builder.receipt;
        this.extras = builder.extras;
        this.price = builder.price;
        this.currency = builder.currency;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public String getCurrency() {
        return this.currency;
    }
    
    public Map<String, Object> getExtras() {
        return this.extras;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public String getProductId() {
        return this.productId;
    }
    
    public String getReceipt() {
        return this.receipt;
    }
    
    public String getTransactionId() {
        return this.transactionId;
    }
    
    public static final class Builder
    {
        private String currency;
        private Map<String, Object> extras;
        private BigDecimal price;
        private String productId;
        private String receipt;
        private String transactionId;
        
        private Builder() {
            this.extras = new HashMap<String, Object>();
        }
        
        public TransactionDetails build() {
            return new TransactionDetails(this, null);
        }
        
        public Builder putExtra(final String s, final Object o) {
            this.extras.put(s, o);
            return this;
        }
        
        public Builder withCurrency(final String currency) {
            this.currency = currency;
            return this;
        }
        
        public Builder withPrice(final double n) {
            this.price = new BigDecimal(n);
            return this;
        }
        
        public Builder withPrice(final BigDecimal price) {
            this.price = price;
            return this;
        }
        
        public Builder withProductId(final String productId) {
            this.productId = productId;
            return this;
        }
        
        public Builder withReceipt(final String receipt) {
            this.receipt = receipt;
            return this;
        }
        
        public Builder withTransactionId(final String transactionId) {
            this.transactionId = transactionId;
            return this;
        }
    }
}
