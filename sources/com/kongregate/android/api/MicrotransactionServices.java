package com.kongregate.android.api;

public interface MicrotransactionServices {

    public interface ReceiptVerificationListener {
        void receiptVerificationComplete(boolean z);
    }

    public enum ReceiptVerificationStatus {
        PROCESSING,
        UNKNOWN,
        VALID,
        INVALID
    }

    boolean hasItem(String str);

    ReceiptVerificationStatus receiptVerificationStatus(String str);

    void requestUserItemList();

    void verifyTransaction(String str, String str2);

    void verifyTransaction(String str, String str2, ReceiptVerificationListener receiptVerificationListener);
}
