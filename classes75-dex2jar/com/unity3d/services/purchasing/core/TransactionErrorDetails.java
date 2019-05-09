// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.purchasing.core;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class TransactionErrorDetails
{
    private String exceptionMessage;
    private Map<String, Object> extras;
    private Store store;
    private String storeSpecificErrorCode;
    private TransactionError transactionError;
    
    private TransactionErrorDetails(final Builder builder) {
        this.transactionError = builder.transactionError;
        this.exceptionMessage = builder.exceptionMessage;
        this.store = builder.store;
        this.storeSpecificErrorCode = builder.storeSpecificErrorCode;
        this.extras = builder.extras;
    }
    
    public static Builder newBuilder() {
        return new Builder();
    }
    
    public String getExceptionMessage() {
        return this.exceptionMessage;
    }
    
    public Map<String, Object> getExtras() {
        return this.extras;
    }
    
    public Store getStore() {
        return this.store;
    }
    
    public String getStoreSpecificErrorCode() {
        return this.storeSpecificErrorCode;
    }
    
    public TransactionError getTransactionError() {
        return this.transactionError;
    }
    
    public static final class Builder
    {
        private String exceptionMessage;
        private Map<String, Object> extras;
        private Store store;
        private String storeSpecificErrorCode;
        private TransactionError transactionError;
        
        private Builder() {
            this.extras = new HashMap<String, Object>();
        }
        
        public TransactionErrorDetails build() {
            return new TransactionErrorDetails(this, null);
        }
        
        public Builder putExtra(final String s, final Object o) {
            this.extras.put(s, o);
            return this;
        }
        
        public Builder putExtras(final Map<String, Object> map) {
            for (final Map.Entry<String, Object> entry : map.entrySet()) {
                this.extras.put(entry.getKey(), entry.getValue());
            }
            return this;
        }
        
        public Builder withExceptionMessage(final String exceptionMessage) {
            this.exceptionMessage = exceptionMessage;
            return this;
        }
        
        public Builder withStore(final Store store) {
            this.store = store;
            return this;
        }
        
        public Builder withStoreSpecificErrorCode(final String storeSpecificErrorCode) {
            this.storeSpecificErrorCode = storeSpecificErrorCode;
            return this;
        }
        
        public Builder withTransactionError(final TransactionError transactionError) {
            this.transactionError = transactionError;
            return this;
        }
    }
}
