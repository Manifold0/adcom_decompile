package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.google.vr.ndk.base.DaydreamApi;
import com.unity.purchasing.common.SaneJSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class IabHelper {
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    public static final int BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
    public static final int IABHELPER_BAD_RESPONSE = -1002;
    public static final int IABHELPER_ERROR_BASE = -1000;
    public static final int IABHELPER_INVALID_CONSUMPTION = -1010;
    public static final int IABHELPER_MISSING_TOKEN = -1007;
    public static final int IABHELPER_REMOTE_EXCEPTION = -1001;
    public static final int IABHELPER_SEND_INTENT_FAILED = -1004;
    public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -1009;
    public static final int IABHELPER_UNKNOWN_ERROR = -1008;
    public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -1006;
    public static final int IABHELPER_USER_CANCELLED = -1005;
    public static final int IABHELPER_VERIFICATION_FAILED = -1003;
    public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    private static final int IN_APP_BILLING_API_VERSION_3 = 3;
    private static final int IN_APP_BILLING_API_VERSION_5 = 5;
    private static final int IN_APP_BILLING_API_VERSION_6 = 6;
    private static final int IN_APP_BILLING_API_VERSION_7 = 7;
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
    public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
    public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    public static HashMap<Integer, String> billingResponseCodeNames = new HashMap();
    private Inventory inv;
    private IActivityLauncher launcher;
    String mAsyncOperation = "";
    Context mContext;
    private boolean mDaydreamApiAvailable = false;
    boolean mDebugLog = false;
    String mDebugTag = "IabHelper";
    volatile boolean mDisposed = false;
    OnIabPurchaseFinishedListener mPurchaseListener;
    String mPurchasingItemType;
    int mRequestCode;
    volatile boolean mSetupDone = false;
    boolean mSubscriptionPurchaseHistorySupported = false;
    boolean mSubscriptionUpgradeDowngradeSupported = false;
    boolean mSubscriptionsSupported = false;
    private boolean mUnityVrEnabled = false;
    private boolean mVrSupported = false;
    private IBillingServiceManager serviceManager;

    public interface OnConsumeFinishedListener {
        void onConsumeFinished(Purchase purchase, IabResult iabResult) throws JSONException;
    }

    public interface QueryInventoryFinishedListener {
        void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) throws Exception;
    }

    public interface OnIabPurchaseFinishedListener {
        void onIabPurchaseFinished(IabResult iabResult, Purchase purchase);
    }

    public interface OnIabSetupFinishedListener {
        void onIabSetupFinished(IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2);
    }

    static {
        billingResponseCodeNames.put(Integer.valueOf(0), "BILLING_RESPONSE_RESULT_OK");
        billingResponseCodeNames.put(Integer.valueOf(1), "BILLING_RESPONSE_RESULT_USER_CANCELED");
        billingResponseCodeNames.put(Integer.valueOf(2), "BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE");
        billingResponseCodeNames.put(Integer.valueOf(3), "BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE");
        billingResponseCodeNames.put(Integer.valueOf(4), "BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE");
        billingResponseCodeNames.put(Integer.valueOf(5), "BILLING_RESPONSE_RESULT_DEVELOPER_ERROR");
        billingResponseCodeNames.put(Integer.valueOf(6), "BILLING_RESPONSE_RESULT_ERROR");
        billingResponseCodeNames.put(Integer.valueOf(7), "BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED");
        billingResponseCodeNames.put(Integer.valueOf(8), "BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED");
        billingResponseCodeNames.put(Integer.valueOf(-1000), "IABHELPER_ERROR_BASE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_REMOTE_EXCEPTION), "IABHELPER_REMOTE_EXCEPTION");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_BAD_RESPONSE), "IABHELPER_BAD_RESPONSE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_VERIFICATION_FAILED), "IABHELPER_VERIFICATION_FAILED");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_SEND_INTENT_FAILED), "IABHELPER_SEND_INTENT_FAILED");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_USER_CANCELLED), "IABHELPER_USER_CANCELLED");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_UNKNOWN_PURCHASE_RESPONSE), "IABHELPER_UNKNOWN_PURCHASE_RESPONSE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_MISSING_TOKEN), "IABHELPER_MISSING_TOKEN");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_UNKNOWN_ERROR), "IABHELPER_UNKNOWN_ERROR");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE), "IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE");
        billingResponseCodeNames.put(Integer.valueOf(IABHELPER_INVALID_CONSUMPTION), "IABHELPER_INVALID_CONSUMPTION");
    }

    public IabHelper(Context ctx, IBillingServiceManager manager, IActivityLauncher launcher) {
        this.mContext = ctx.getApplicationContext();
        this.serviceManager = manager;
        this.launcher = launcher;
        this.inv = new Inventory();
        logDebug("IAB helper created.");
    }

    public void enableDebugLogging(boolean enable, String tag) {
        this.mDebugLog = enable;
        this.mDebugTag = tag;
    }

    public void enableDebugLogging(boolean enable) {
        this.mDebugLog = enable;
    }

    public void enableUnityVr(boolean enabled) {
        this.mUnityVrEnabled = enabled;
    }

    public void enableDaydreamApi(boolean enabled) {
        this.mDaydreamApiAvailable = enabled;
    }

    public void startSetup(final OnIabSetupFinishedListener listener) {
        if (this.mSetupDone) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        logDebug("Starting in-app billing setup.");
        this.serviceManager.workWith(new BillingServiceProcessor() {
            public void workWith(IInAppBillingService service) {
                IabHelper.this.finishSetup(listener, service);
            }
        });
    }

    private void finishSetup(OnIabSetupFinishedListener listener, IInAppBillingService service) {
        String packageName = this.mContext.getPackageName();
        try {
            logDebug("Checking for in-app billing 3 support.");
            int response = service.isBillingSupported(3, packageName, "inapp");
            if (response != 0) {
                listener.onIabSetupFinished(new IabResult(response, "Billing V3 not supported."));
                this.mSubscriptionsSupported = false;
                return;
            }
            logDebug("In-app billing version 3 supported for " + packageName);
            response = service.isBillingSupported(3, packageName, "subs");
            if (response == 0) {
                logDebug("Subscriptions AVAILABLE.");
                this.mSubscriptionsSupported = true;
                if (service.isBillingSupported(5, packageName, "subs") == 0) {
                    this.mSubscriptionUpgradeDowngradeSupported = true;
                    logDebug("Subscription upgrade and downgrade are AVAILABLE.");
                } else {
                    logDebug("Subscription upgrade and downgrade are NOT AVAILABLE.");
                }
                if (service.isBillingSupported(6, packageName, "subs") == 0) {
                    this.mSubscriptionPurchaseHistorySupported = true;
                    logDebug("Subscriptions information parse AVAILABLE.");
                } else {
                    logDebug("Subscriptions information parse NOT AVAILABLE.");
                }
            } else {
                logDebug("Subscriptions NOT AVAILABLE. Response: " + response);
            }
            if (service.isBillingSupported(7, packageName, "inapp") == 0) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("vr", true);
                response = service.isBillingSupportedExtraParams(7, this.mContext.getPackageName(), "inapp", bundle);
                if (response == 0) {
                    logDebug("VR supported.");
                    this.mVrSupported = true;
                } else {
                    logDebug("VR purchases  NOT AVAILABLE. Response: " + response);
                }
            } else {
                logDebug("In app billing version 7 is not supported");
            }
            this.mSetupDone = true;
            listener.onIabSetupFinished(new IabResult(0, "Setup successful."));
        } catch (RemoteException e) {
            listener.onIabSetupFinished(new IabResult(IABHELPER_REMOTE_EXCEPTION, "RemoteException while setting up in-app billing."));
        }
    }

    public void dispose() {
        logDebug("Disposing.");
        this.mSetupDone = false;
        this.serviceManager.dispose();
        this.mDisposed = true;
    }

    public boolean subscriptionsSupported() {
        return this.mSubscriptionsSupported;
    }

    public boolean subscriptionPurchaseHistorySupported() {
        return this.mSubscriptionPurchaseHistorySupported;
    }

    public boolean subscriptionUpgradeDowngradeSupported() {
        return this.mSubscriptionUpgradeDowngradeSupported;
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener) {
        launchPurchaseFlow(act, sku, requestCode, listener, "");
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) {
        launchPurchaseFlow(act, sku, "inapp", requestCode, listener, extraData);
    }

    public void launchSubscriptionPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener) {
        launchSubscriptionPurchaseFlow(act, sku, requestCode, listener, "");
    }

    public void launchSubscriptionPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) {
        launchPurchaseFlow(act, sku, "subs", requestCode, listener, extraData);
    }

    public void launchSubscriptionUpdateFlow(Activity act, String sku, List<String> oldSkus, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) {
        checkSetupDone("launchSubscriptionUpdateFlow");
        if (this.mSubscriptionsSupported && this.mSubscriptionUpgradeDowngradeSupported) {
            final String str = sku;
            final String str2 = extraData;
            final List<String> list = oldSkus;
            final Activity activity = act;
            final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener = listener;
            final int i = requestCode;
            this.serviceManager.workWith(new BillingServiceProcessor() {
                public void workWith(IInAppBillingService service) {
                    IabResult iabResult;
                    try {
                        Bundle buyIntentBundle;
                        IabHelper.this.logDebug("Constructing buy intent for " + str + ", item type: " + "subs");
                        String accountId = IabHelper.this.getAccountId(str2);
                        if (IabHelper.this.mVrSupported) {
                            Bundle bundle = new Bundle();
                            if (IabHelper.this.mUnityVrEnabled) {
                                bundle.putBoolean("vr", true);
                                IabHelper.this.logDebug("Initiating VR purchase intent");
                            }
                            if (accountId != null) {
                                bundle.putString("accountId", accountId);
                                IabHelper.this.logDebug("pass accountId to GooglePlay for fraud detection " + accountId);
                            }
                            bundle.putStringArrayList("skusToReplace", new ArrayList(list));
                            bundle.putBoolean("replaceSkusProration", true);
                            buyIntentBundle = service.getBuyIntentExtraParams(7, IabHelper.this.mContext.getPackageName(), str, "subs", str2, bundle);
                        } else {
                            buyIntentBundle = service.getBuyIntentToReplaceSkus(5, IabHelper.this.mContext.getPackageName(), list, str, "subs", str2);
                        }
                        int response = IabHelper.this.getResponseCodeFromBundle(buyIntentBundle);
                        if (response != 0) {
                            Purchase purchase;
                            IabHelper.this.logError("Unable to update subscription, Error response: " + IabHelper.getResponseDesc(response));
                            iabResult = new IabResult(response, "Unable to update subscription item");
                            SaneJSONObject o = new SaneJSONObject();
                            SaneJSONObject saneJSONObject = o;
                            saneJSONObject.put("productId", str);
                            if (response == 7) {
                                try {
                                    if (IabHelper.this.inv.hasPurchase(str)) {
                                        purchase = IabHelper.this.inv.getPurchase(str);
                                        activity.finish();
                                        if (onIabPurchaseFinishedListener != null) {
                                            onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, purchase);
                                            return;
                                        }
                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            Purchase purchase2 = new Purchase("subs", o.toString(), "");
                            activity.finish();
                            if (onIabPurchaseFinishedListener != null) {
                                onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, purchase);
                                return;
                            }
                            return;
                        }
                        final PendingIntent pendingIntent = (PendingIntent) buyIntentBundle.getParcelable(IabHelper.RESPONSE_BUY_INTENT);
                        IabHelper.this.logDebug("Launching buy intent for " + str + ". Request code: " + i);
                        IabHelper.this.mRequestCode = i;
                        IabHelper.this.mPurchaseListener = onIabPurchaseFinishedListener;
                        IabHelper.this.mPurchasingItemType = "subs";
                        if (IabHelper.this.mVrSupported && IabHelper.this.mUnityVrEnabled && IabHelper.this.mDaydreamApiAvailable) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    DaydreamApi api = DaydreamApi.create(activity);
                                    api.launchInVrForResult(activity, pendingIntent, i);
                                    api.close();
                                }
                            });
                            return;
                        }
                        IntentSender sender = pendingIntent.getIntentSender();
                        IabHelper.this.launcher.startIntentSenderForResult(activity, pendingIntent, i, new Intent(), str);
                    } catch (SendIntentException e2) {
                        IabHelper.this.logError("SendIntentException while launching purchase flow for sku " + str);
                        e2.printStackTrace();
                        iabResult = new IabResult(IabHelper.IABHELPER_SEND_INTENT_FAILED, "Failed to send intent.");
                        if (onIabPurchaseFinishedListener != null) {
                            onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
                        }
                    } catch (RemoteException e3) {
                        IabHelper.this.logError("RemoteException while launching purchase flow for sku " + str);
                        e3.printStackTrace();
                        iabResult = new IabResult(IabHelper.IABHELPER_REMOTE_EXCEPTION, "Remote exception while starting purchase flow");
                        if (onIabPurchaseFinishedListener != null) {
                            onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
                        }
                    }
                }
            });
            return;
        }
        IabResult r = new IabResult(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE, "Subscription upgrade/downgrade is not available.");
        if (listener != null) {
            listener.onIabPurchaseFinished(r, null);
        }
    }

    public void launchPurchaseFlow(Activity act, String sku, String itemType, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) {
        checkSetupDone("launchPurchaseFlow");
        if (!itemType.equals("subs") || this.mSubscriptionsSupported) {
            final String str = sku;
            final String str2 = itemType;
            final String str3 = extraData;
            final Activity activity = act;
            final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener = listener;
            final int i = requestCode;
            this.serviceManager.workWith(new BillingServiceProcessor() {
                public void workWith(IInAppBillingService service) {
                    IabResult result;
                    try {
                        Bundle buyIntentBundle;
                        IabHelper.this.logDebug("Constructing buy intent for " + str + ", item type: " + str2);
                        String accountId = IabHelper.this.getAccountId(str3);
                        if (IabHelper.this.mVrSupported) {
                            Bundle bundle = new Bundle();
                            if (IabHelper.this.mUnityVrEnabled) {
                                bundle.putBoolean("vr", true);
                                IabHelper.this.logDebug("Initiating VR purchase intent");
                            }
                            if (accountId != null) {
                                bundle.putString("accountId", accountId);
                                IabHelper.this.logDebug("pass accountId to GooglePlay for fraud detection, and accountId is: " + accountId);
                            }
                            buyIntentBundle = service.getBuyIntentExtraParams(7, IabHelper.this.mContext.getPackageName(), str, str2, str3, bundle);
                        } else {
                            buyIntentBundle = service.getBuyIntent(3, IabHelper.this.mContext.getPackageName(), str, str2, str3);
                        }
                        int response = IabHelper.this.getResponseCodeFromBundle(buyIntentBundle);
                        if (response != 0) {
                            Purchase purchase;
                            IabHelper.this.logError("Unable to buy item, Error response: " + IabHelper.getResponseDesc(response));
                            result = new IabResult(response, "Unable to buy item");
                            SaneJSONObject o = new SaneJSONObject();
                            o.put("productId", str);
                            if (response == 7) {
                                try {
                                    if (IabHelper.this.inv.hasPurchase(str)) {
                                        purchase = IabHelper.this.inv.getPurchase(str);
                                        activity.finish();
                                        if (onIabPurchaseFinishedListener != null) {
                                            onIabPurchaseFinishedListener.onIabPurchaseFinished(result, purchase);
                                            return;
                                        }
                                        return;
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    return;
                                }
                            }
                            purchase = new Purchase(str2, o.toString(), "");
                            activity.finish();
                            if (onIabPurchaseFinishedListener != null) {
                                onIabPurchaseFinishedListener.onIabPurchaseFinished(result, purchase);
                                return;
                            }
                            return;
                        }
                        final PendingIntent pendingIntent = (PendingIntent) buyIntentBundle.getParcelable(IabHelper.RESPONSE_BUY_INTENT);
                        IabHelper.this.logDebug("Launching buy intent for " + str + ". Request code: " + i);
                        IabHelper.this.mRequestCode = i;
                        IabHelper.this.mPurchaseListener = onIabPurchaseFinishedListener;
                        IabHelper.this.mPurchasingItemType = str2;
                        if (IabHelper.this.mVrSupported && IabHelper.this.mUnityVrEnabled && IabHelper.this.mDaydreamApiAvailable) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    DaydreamApi api = DaydreamApi.create(activity);
                                    api.launchInVrForResult(activity, pendingIntent, i);
                                    api.close();
                                }
                            });
                            return;
                        }
                        IntentSender sender = pendingIntent.getIntentSender();
                        IabHelper.this.launcher.startIntentSenderForResult(activity, pendingIntent, i, new Intent(), str);
                    } catch (SendIntentException e2) {
                        IabHelper.this.logError("SendIntentException while launching purchase flow for sku " + str);
                        e2.printStackTrace();
                        result = new IabResult(IabHelper.IABHELPER_SEND_INTENT_FAILED, "Failed to send intent.");
                        if (onIabPurchaseFinishedListener != null) {
                            onIabPurchaseFinishedListener.onIabPurchaseFinished(result, null);
                        }
                    } catch (RemoteException e3) {
                        IabHelper.this.logError("RemoteException while launching purchase flow for sku " + str);
                        e3.printStackTrace();
                        result = new IabResult(IabHelper.IABHELPER_REMOTE_EXCEPTION, "Remote exception while starting purchase flow");
                        if (onIabPurchaseFinishedListener != null) {
                            onIabPurchaseFinishedListener.onIabPurchaseFinished(result, null);
                        }
                    }
                }
            });
            return;
        }
        IabResult r = new IabResult(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE, "Subscriptions are not available.");
        if (listener != null) {
            listener.onIabPurchaseFinished(r, null);
        }
    }

    public boolean handleActivityResult(int requestCode, int resultCode, Intent data) {
        IabResult result;
        JSONException e;
        if (requestCode != this.mRequestCode) {
            return false;
        }
        checkSetupDone("handleActivityResult");
        if (data == null) {
            logError("Null data in IAB activity result.");
            result = new IabResult(IABHELPER_BAD_RESPONSE, "Null data in IAB result");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(result, null);
            }
            return true;
        }
        int responseCode = getResponseCodeFromIntent(data);
        String purchaseData = data.getStringExtra(RESPONSE_INAPP_PURCHASE_DATA);
        String dataSignature = data.getStringExtra(RESPONSE_INAPP_SIGNATURE);
        logDebug("Purchase data: " + purchaseData);
        logDebug("Data signature: " + dataSignature);
        if (resultCode == -1 && responseCode == 0) {
            logDebug("Successful resultcode from purchase activity.");
            logDebug("Purchase data: " + purchaseData);
            logDebug("Data signature: " + dataSignature);
            logDebug("Extras: " + data.getExtras());
            logDebug("Expected item type: " + this.mPurchasingItemType);
            if (purchaseData == null || dataSignature == null) {
                logError("BUG: either purchaseData or dataSignature is null.");
                logDebug("Extras: " + data.getExtras().toString());
                result = new IabResult(IABHELPER_UNKNOWN_ERROR, "IAB returned null purchaseData or dataSignature");
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(result, null);
                }
                return true;
            }
            try {
                Purchase purchase = new Purchase(this.mPurchasingItemType, purchaseData, dataSignature);
                try {
                    purchase.getSku();
                    if (this.mPurchaseListener != null) {
                        this.mPurchaseListener.onIabPurchaseFinished(new IabResult(0, "Success"), purchase);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    Purchase purchase2 = purchase;
                    logError("Failed to parse purchase data.");
                    e.printStackTrace();
                    result = new IabResult(IABHELPER_BAD_RESPONSE, "Failed to parse purchase data.");
                    if (this.mPurchaseListener != null) {
                        this.mPurchaseListener.onIabPurchaseFinished(result, null);
                    }
                    return true;
                }
            } catch (JSONException e3) {
                e = e3;
                logError("Failed to parse purchase data.");
                e.printStackTrace();
                result = new IabResult(IABHELPER_BAD_RESPONSE, "Failed to parse purchase data.");
                if (this.mPurchaseListener != null) {
                    this.mPurchaseListener.onIabPurchaseFinished(result, null);
                }
                return true;
            }
        } else if (resultCode == -1) {
            logDebug("Result code was OK but in-app billing response was not OK: " + getResponseDesc(responseCode));
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(new IabResult(responseCode, "Problem purchasing item."), null);
            }
        } else if (resultCode == 0) {
            logDebug("Purchase canceled - Response: " + getResponseDesc(responseCode));
            result = new IabResult(responseCode, getResponseDesc(responseCode));
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(result, null);
            }
        } else {
            logError("Purchase failed. Result code: " + Integer.toString(resultCode) + ". Response: " + getResponseDesc(responseCode));
            result = new IabResult(IABHELPER_UNKNOWN_PURCHASE_RESPONSE, "Unknown purchase response.");
            if (this.mPurchaseListener != null) {
                this.mPurchaseListener.onIabPurchaseFinished(result, null);
            }
        }
        return true;
    }

    public Inventory queryInventory(boolean querySkuDetails, List<String> moreSkus, IInAppBillingService service) throws IabException {
        return queryInventory(querySkuDetails, moreSkus, null, service);
    }

    public Inventory queryInventory(boolean querySkuDetails, List<String> moreItemSkus, List<String> list, IInAppBillingService service) throws IabException {
        checkSetupDone("queryInventory");
        try {
            int r = queryPurchases(this.inv, "inapp", service);
            if (r != 0) {
                throw new IabException(r, "Error refreshing inventory (querying owned items).");
            }
            if (querySkuDetails) {
                r = querySkuDetails("inapp", this.inv, moreItemSkus, service);
                if (r != 0) {
                    throw new IabException(r, "Error refreshing inventory (querying prices of items).");
                }
            }
            if (this.mSubscriptionsSupported) {
                r = queryPurchases(this.inv, "subs", service);
                if (r != 0) {
                    throw new IabException(r, "Error refreshing inventory (querying owned subscriptions).");
                } else if (querySkuDetails) {
                    r = querySkuDetails("subs", this.inv, moreItemSkus, service);
                    if (r != 0) {
                        throw new IabException(r, "Error refreshing inventory (querying prices of subscriptions).");
                    }
                }
            }
            if (this.mSubscriptionPurchaseHistorySupported) {
                r = queryPurchaseHistory(this.inv, "subs", service);
                r = queryPurchaseHistory(this.inv, "inapp", service);
                if (r != 0) {
                    throw new IabException(r, "Error query Purchase History");
                }
            }
            return this.inv;
        } catch (RemoteException e) {
            throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while refreshing inventory.", e);
        } catch (JSONException e2) {
            throw new IabException(IABHELPER_BAD_RESPONSE, "Error parsing JSON response while refreshing inventory.", e2);
        } catch (SecurityException e3) {
            throw new IabException(IABHELPER_UNKNOWN_ERROR, "SecurityException querying inventory, update Google Play - https://github.com/googlesamples/android-play-billing/issues/26", e3);
        }
    }

    public void queryInventoryAsync(boolean querySkuDetails, List<String> moreSkus, QueryInventoryFinishedListener listener, long delay) {
        checkSetupDone("queryInventory");
        final long j = delay;
        final boolean z = querySkuDetails;
        final List<String> list = moreSkus;
        final QueryInventoryFinishedListener queryInventoryFinishedListener = listener;
        this.serviceManager.workWith(new BillingServiceProcessor() {
            public void workWith(IInAppBillingService service) {
                try {
                    Thread.sleep(j);
                } catch (InterruptedException e) {
                }
                IabResult result = new IabResult(0, "Inventory refresh successful.");
                Inventory inv = null;
                try {
                    inv = IabHelper.this.queryInventory(z, list, service);
                } catch (IabException ex) {
                    result = ex.getResult();
                }
                IabResult result_f = result;
                Inventory inv_f = inv;
                if (!IabHelper.this.mDisposed && queryInventoryFinishedListener != null) {
                    try {
                        queryInventoryFinishedListener.onQueryInventoryFinished(result_f, inv_f);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    public void queryInventoryAsync(QueryInventoryFinishedListener listener) {
        queryInventoryAsync(true, null, listener, 0);
    }

    public void queryInventoryAsync(boolean querySkuDetails, QueryInventoryFinishedListener listener) {
        queryInventoryAsync(querySkuDetails, null, listener, 0);
    }

    void consume(Purchase itemInfo, IInAppBillingService service) throws IabException {
        if (itemInfo.mItemType.equals("inapp")) {
            try {
                String token = itemInfo.getToken();
                String sku = itemInfo.getSku();
                if (token == null || token.equals("")) {
                    logError("Can't consume " + sku + ". No token.");
                    throw new IabException((int) IABHELPER_MISSING_TOKEN, "PurchaseInfo is missing token for sku: " + sku + " " + itemInfo);
                }
                logDebug("Consuming sku: " + sku + ", token: " + token);
                int response = service.consumePurchase(3, this.mContext.getPackageName(), token);
                if (response == 0) {
                    logDebug("Successfully consumed sku: " + sku);
                    return;
                } else {
                    logDebug("Error consuming consuming sku " + sku + ". " + getResponseDesc(response));
                    throw new IabException(response, "Error consuming sku " + sku);
                }
            } catch (RemoteException e) {
                throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while consuming. PurchaseInfo: " + itemInfo, e);
            }
        }
        throw new IabException((int) IABHELPER_INVALID_CONSUMPTION, "Items of type '" + itemInfo.mItemType + "' can't be consumed.");
    }

    void consumeSilently(Purchase itemInfo, IInAppBillingService service) throws IabException {
        if (itemInfo.mItemType.equals("inapp")) {
            try {
                String token = itemInfo.getToken();
                String sku = itemInfo.getSku();
                if (token != null && !token.equals("")) {
                    service.consumePurchase(3, this.mContext.getPackageName(), token);
                }
            } catch (RemoteException e) {
            }
        }
    }

    public void consumeAsync(Purchase purchase, OnConsumeFinishedListener listener, boolean isSilent) {
        checkSetupDone("consume");
        List<Purchase> purchases = new ArrayList();
        purchases.add(purchase);
        consumeAsyncInternal(purchases, listener, null, isSilent);
    }

    public void consumeAsync(List<Purchase> purchases, OnConsumeMultiFinishedListener listener, boolean isSilent) {
        checkSetupDone("consume");
        consumeAsyncInternal(purchases, null, listener, isSilent);
    }

    public static String getResponseDesc(int code) {
        String[] iab_msgs = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        String[] iabhelper_msgs = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (code <= -1000) {
            int index = -1000 - code;
            if (index < 0 || index >= iabhelper_msgs.length) {
                return String.valueOf(code) + ":Unknown IAB Helper Error";
            }
            return iabhelper_msgs[index];
        } else if (code < 0 || code >= iab_msgs.length) {
            return String.valueOf(code) + ":Unknown";
        } else {
            return iab_msgs[code];
        }
    }

    void checkSetupDone(String operation) {
        if (!this.mSetupDone) {
            logError("Illegal state for operation (" + operation + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + operation);
        }
    }

    int getResponseCodeFromBundle(Bundle b) {
        Object o = b.get("RESPONSE_CODE");
        if (o == null) {
            logDebug("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            logError("Unexpected type for bundle response code.");
            logError(o.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + o.getClass().getName());
        }
    }

    int getResponseCodeFromIntent(Intent i) {
        Object o = i.getExtras().get("RESPONSE_CODE");
        if (o == null) {
            logError("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            logError("Unexpected type for intent response code.");
            logError(o.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + o.getClass().getName());
        }
    }

    public int queryPurchaseHistory(Inventory inv, String itemType, IInAppBillingService service) throws JSONException, RemoteException {
        if (this.mDisposed) {
            logDebug("queryPurchase History - Biller disposed. Returning...");
            return 0;
        }
        logDebug("Querying owned items' purchase history, item type: " + itemType);
        logDebug("Package name: " + this.mContext.getPackageName());
        String continueToken = null;
        do {
            logDebug("Calling getPurchaseHistory with continuation token: " + continueToken);
            Bundle ownedItems = service.getPurchaseHistory(6, this.mContext.getPackageName(), itemType, continueToken, new Bundle());
            if (ownedItems == null) {
                return 0;
            }
            int response = getResponseCodeFromBundle(ownedItems);
            logDebug("Purchase history response: " + String.valueOf(response));
            if (response != 0) {
                logDebug("getPurchaseHistory() failed: " + getResponseDesc(response));
                return response;
            } else if (ownedItems.containsKey(RESPONSE_INAPP_ITEM_LIST) && ownedItems.containsKey(RESPONSE_INAPP_PURCHASE_DATA_LIST) && ownedItems.containsKey(RESPONSE_INAPP_SIGNATURE_LIST)) {
                int i;
                ArrayList<String> ownedSkus = ownedItems.getStringArrayList(RESPONSE_INAPP_ITEM_LIST);
                if (itemType.equals("inapp")) {
                    ArrayList<String> purchaseDataList = ownedItems.getStringArrayList(RESPONSE_INAPP_PURCHASE_DATA_LIST);
                    ArrayList<String> signatureList = ownedItems.getStringArrayList(RESPONSE_INAPP_SIGNATURE_LIST);
                    for (i = 0; i < purchaseDataList.size(); i++) {
                        String purchaseData = (String) purchaseDataList.get(i);
                        String sku = (String) ownedSkus.get(i);
                        Purchase purchase = new Purchase(itemType, purchaseData, (String) signatureList.get(i));
                        if (TextUtils.isEmpty(purchase.getToken())) {
                            logWarn("BUG: empty/null token!");
                            logDebug("Purchase data: " + purchaseData);
                        }
                        inv.addPurchaseToConsumablePurchaseHistory(sku, purchase);
                    }
                }
                if (itemType.equals("subs")) {
                    for (i = 0; i < ownedSkus.size(); i++) {
                        inv.addPurchaseToSubscriptionPurchaseHistory((String) ownedSkus.get(i));
                    }
                }
                continueToken = ownedItems.getString(INAPP_CONTINUATION_TOKEN);
                logDebug("Continuation token: " + continueToken);
            } else {
                logError("Bundle returned from getPurchaseHistory() doesn't contain required fields.");
                return IABHELPER_BAD_RESPONSE;
            }
        } while (!TextUtils.isEmpty(continueToken));
        return false ? IABHELPER_VERIFICATION_FAILED : 0;
    }

    int queryPurchases(Inventory inv, String itemType, IInAppBillingService service) throws JSONException, RemoteException {
        if (this.mDisposed) {
            logDebug("queryPurchases - Biller disposed. Returning...");
            return 0;
        }
        logDebug("Querying owned items, item type: " + itemType);
        logDebug("Package name: " + this.mContext.getPackageName());
        String continueToken = null;
        do {
            logDebug("Calling getPurchases with continuation token: " + continueToken);
            Bundle ownedItems = service.getPurchases(3, this.mContext.getPackageName(), itemType, continueToken);
            int response = getResponseCodeFromBundle(ownedItems);
            logDebug("Owned items response: " + String.valueOf(response));
            if (response != 0) {
                logDebug("getPurchases() failed: " + getResponseDesc(response));
                return response;
            } else if (ownedItems.containsKey(RESPONSE_INAPP_ITEM_LIST) && ownedItems.containsKey(RESPONSE_INAPP_PURCHASE_DATA_LIST) && ownedItems.containsKey(RESPONSE_INAPP_SIGNATURE_LIST)) {
                ArrayList<String> ownedSkus = ownedItems.getStringArrayList(RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> purchaseDataList = ownedItems.getStringArrayList(RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> signatureList = ownedItems.getStringArrayList(RESPONSE_INAPP_SIGNATURE_LIST);
                for (int i = 0; i < purchaseDataList.size(); i++) {
                    String purchaseData = (String) purchaseDataList.get(i);
                    String signature = (String) signatureList.get(i);
                    logDebug("Sku is owned: " + ((String) ownedSkus.get(i)));
                    Purchase purchase = new Purchase(itemType, purchaseData, signature);
                    if (TextUtils.isEmpty(purchase.getToken())) {
                        logWarn("BUG: empty/null token!");
                        logDebug("Purchase data: " + purchaseData);
                    }
                    inv.addPurchase(purchase);
                }
                continueToken = ownedItems.getString(INAPP_CONTINUATION_TOKEN);
                logDebug("Continuation token: " + continueToken);
            } else {
                logError("Bundle returned from getPurchases() doesn't contain required fields.");
                return IABHELPER_BAD_RESPONSE;
            }
        } while (!TextUtils.isEmpty(continueToken));
        return false ? IABHELPER_VERIFICATION_FAILED : 0;
    }

    int querySkuDetails(String itemType, Inventory inv, List<String> moreSkus, IInAppBillingService service) throws RemoteException, JSONException {
        logDebug("Querying SKU details.");
        ArrayList<String> skuList = new ArrayList();
        skuList.addAll(inv.getAllOwnedSkus(itemType));
        if (moreSkus != null) {
            skuList.addAll(moreSkus);
        }
        if (skuList.size() == 0) {
            logDebug("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        while (skuList.size() > 0) {
            int endIndex = Math.min(20, skuList.size());
            ArrayList<String> chunk = new ArrayList();
            for (int t = 0; t < endIndex; t++) {
                chunk.add(skuList.get(t));
            }
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList(GET_SKU_DETAILS_ITEM_LIST, chunk);
            Bundle skuDetails = service.getSkuDetails(3, this.mContext.getPackageName(), itemType, querySkus);
            skuList.removeAll(chunk);
            if (skuDetails.containsKey(RESPONSE_GET_SKU_DETAILS_LIST)) {
                Iterator it = skuDetails.getStringArrayList(RESPONSE_GET_SKU_DETAILS_LIST).iterator();
                while (it.hasNext()) {
                    inv.addSkuDetails(new SkuDetails(itemType, (String) it.next()));
                }
            } else {
                int response = getResponseCodeFromBundle(skuDetails);
                if (response != 0) {
                    logDebug("getSkuDetails() failed: " + getResponseDesc(response));
                    return response;
                }
                logError("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                return IABHELPER_BAD_RESPONSE;
            }
        }
        return 0;
    }

    void consumeAsyncInternal(List<Purchase> purchases, OnConsumeFinishedListener singleListener, OnConsumeMultiFinishedListener multiListener, boolean isSilent) {
        final List<Purchase> list = purchases;
        final boolean z = isSilent;
        final OnConsumeFinishedListener onConsumeFinishedListener = singleListener;
        final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener = multiListener;
        this.serviceManager.workWith(new BillingServiceProcessor() {
            public void workWith(IInAppBillingService service) {
                List<IabResult> results = new ArrayList();
                for (Purchase purchase : list) {
                    try {
                        if (z) {
                            IabHelper.this.consumeSilently(purchase, service);
                        } else {
                            IabHelper.this.consume(purchase, service);
                        }
                        results.add(new IabResult(0, "Successful consume of sku " + purchase.getSku()));
                    } catch (IabException ex) {
                        results.add(ex.getResult());
                    }
                }
                if (!(IabHelper.this.mDisposed || onConsumeFinishedListener == null)) {
                    try {
                        onConsumeFinishedListener.onConsumeFinished((Purchase) list.get(0), (IabResult) results.get(0));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (!IabHelper.this.mDisposed && onConsumeMultiFinishedListener != null) {
                    onConsumeMultiFinishedListener.onConsumeMultiFinished(list, results);
                }
            }
        });
    }

    public static String byteArrayToHexString(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        int length = a.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x", new Object[]{Integer.valueOf(a[i] & 255)}));
        }
        return sb.toString();
    }

    void logDebug(String msg) {
        Log.i("UnityIAP", msg);
    }

    void logError(String msg) {
        Log.e(this.mDebugTag, "In-app billing error: " + msg);
    }

    void logWarn(String msg) {
        Log.w(this.mDebugTag, "In-app billing warning: " + msg);
    }

    String getAccountId(String developerPayload) {
        try {
            JSONObject json = new JSONObject(developerPayload);
            if (!json.has("accountId") || json.getString("accountId") == null || json.getString("accountId").isEmpty()) {
                return null;
            }
            String accountId = json.getString("accountId");
            logDebug("accountId is: " + accountId);
            return accountId;
        } catch (JSONException e) {
            return null;
        }
    }
}
