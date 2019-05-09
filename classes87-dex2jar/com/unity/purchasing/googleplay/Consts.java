// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

public class Consts
{
    public static final String ACTION_CONFIRM_NOTIFICATION = "com.example.subscriptions.CONFIRM_NOTIFICATION";
    public static final String ACTION_GET_PURCHASE_INFORMATION = "com.example.subscriptions.GET_PURCHASE_INFORMATION";
    public static final String ACTION_NOTIFY = "com.android.vending.billing.IN_APP_NOTIFY";
    public static final String ACTION_PURCHASE_STATE_CHANGED = "com.android.vending.billing.PURCHASE_STATE_CHANGED";
    public static final String ACTION_RESPONSE_CODE = "com.android.vending.billing.RESPONSE_CODE";
    public static final String ACTION_RESTORE_TRANSACTIONS = "com.example.subscriptions.RESTORE_TRANSACTIONS";
    public static final String BILLING_REQUEST_API_VERSION = "API_VERSION";
    public static final String BILLING_REQUEST_DEVELOPER_PAYLOAD = "DEVELOPER_PAYLOAD";
    public static final String BILLING_REQUEST_ITEM_ID = "ITEM_ID";
    public static final String BILLING_REQUEST_ITEM_TYPE = "ITEM_TYPE";
    public static final String BILLING_REQUEST_METHOD = "BILLING_REQUEST";
    public static final String BILLING_REQUEST_NONCE = "NONCE";
    public static final String BILLING_REQUEST_NOTIFY_IDS = "NOTIFY_IDS";
    public static final String BILLING_REQUEST_PACKAGE_NAME = "PACKAGE_NAME";
    public static long BILLING_RESPONSE_INVALID_REQUEST_ID = 0L;
    public static final String BILLING_RESPONSE_PURCHASE_INTENT = "PURCHASE_INTENT";
    public static final String BILLING_RESPONSE_REQUEST_ID = "REQUEST_ID";
    public static final String BILLING_RESPONSE_RESPONSE_CODE = "RESPONSE_CODE";
    public static final boolean DEBUG = false;
    public static final String INAPP_REQUEST_ID = "request_id";
    public static final String INAPP_RESPONSE_CODE = "response_code";
    public static final String INAPP_SIGNATURE = "inapp_signature";
    public static final String INAPP_SIGNED_DATA = "inapp_signed_data";
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBSCRIPTION = "subs";
    public static final String MARKET_BILLING_SERVICE_ACTION = "com.android.vending.billing.MarketBillingService.BIND";
    public static final String NOTIFICATION_ID = "notification_id";
    
    static {
        Consts.BILLING_RESPONSE_INVALID_REQUEST_ID = -1L;
    }
    
    public enum PurchaseState
    {
        CANCELED, 
        PURCHASED, 
        REFUNDED;
        
        public static PurchaseState valueOf(final int n) {
            final PurchaseState[] values = values();
            if (n < 0 || n >= values.length) {
                return PurchaseState.CANCELED;
            }
            return values[n];
        }
    }
    
    public enum ResponseCode
    {
        RESULT_BILLING_UNAVAILABLE, 
        RESULT_DEVELOPER_ERROR, 
        RESULT_ERROR, 
        RESULT_ITEM_UNAVAILABLE, 
        RESULT_OK, 
        RESULT_SERVICE_UNAVAILABLE, 
        RESULT_USER_CANCELED;
        
        public static ResponseCode valueOf(final int n) {
            final ResponseCode[] values = values();
            if (n < 0 || n >= values.length) {
                return ResponseCode.RESULT_ERROR;
            }
            return values[n];
        }
    }
}
