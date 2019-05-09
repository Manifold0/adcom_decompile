package com.unity3d.services.purchasing.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TransactionErrorDetails {
    private String exceptionMessage;
    private Map<String, Object> extras;
    private Store store;
    private String storeSpecificErrorCode;
    private TransactionError transactionError;

    public static final class Builder {
        private String exceptionMessage;
        private Map<String, Object> extras;
        private Store store;
        private String storeSpecificErrorCode;
        private TransactionError transactionError;

        private Builder() {
            this.extras = new HashMap();
        }

        public Builder withTransactionError(TransactionError val) {
            this.transactionError = val;
            return this;
        }

        public Builder withExceptionMessage(String val) {
            this.exceptionMessage = val;
            return this;
        }

        public Builder withStore(Store val) {
            this.store = val;
            return this;
        }

        public Builder withStoreSpecificErrorCode(String val) {
            this.storeSpecificErrorCode = val;
            return this;
        }

        public Builder putExtras(Map<String, Object> val) {
            for (Entry<String, Object> entry : val.entrySet()) {
                this.extras.put(entry.getKey(), entry.getValue());
            }
            return this;
        }

        public Builder putExtra(String key, Object val) {
            this.extras.put(key, val);
            return this;
        }

        public TransactionErrorDetails build() {
            return new TransactionErrorDetails();
        }
    }

    private TransactionErrorDetails(Builder builder) {
        this.transactionError = builder.transactionError;
        this.exceptionMessage = builder.exceptionMessage;
        this.store = builder.store;
        this.storeSpecificErrorCode = builder.storeSpecificErrorCode;
        this.extras = builder.extras;
    }

    public TransactionError getTransactionError() {
        return this.transactionError;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    public Store getStore() {
        return this.store;
    }

    public String getStoreSpecificErrorCode() {
        return this.storeSpecificErrorCode;
    }

    public Map<String, Object> getExtras() {
        return this.extras;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}
