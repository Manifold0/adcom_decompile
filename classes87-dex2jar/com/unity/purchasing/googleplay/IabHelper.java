// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import android.text.TextUtils;
import android.util.Log;
import java.util.Collection;
import android.app.PendingIntent;
import android.content.IntentSender$SendIntentException;
import com.unity.purchasing.common.SaneJSONObject;
import android.app.Activity;
import android.content.Intent;
import org.json.JSONObject;
import java.util.Iterator;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;
import android.os.RemoteException;
import android.os.Bundle;
import com.android.vending.billing.IInAppBillingService;
import android.content.Context;
import java.util.HashMap;

public class IabHelper
{
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
    public static HashMap<Integer, String> billingResponseCodeNames;
    private Inventory inv;
    private IActivityLauncher launcher;
    String mAsyncOperation;
    Context mContext;
    private boolean mDaydreamApiAvailable;
    boolean mDebugLog;
    String mDebugTag;
    volatile boolean mDisposed;
    OnIabPurchaseFinishedListener mPurchaseListener;
    String mPurchasingItemType;
    int mRequestCode;
    volatile boolean mSetupDone;
    boolean mSubscriptionPurchaseHistorySupported;
    boolean mSubscriptionUpgradeDowngradeSupported;
    boolean mSubscriptionsSupported;
    private boolean mUnityVrEnabled;
    private boolean mVrSupported;
    private IBillingServiceManager serviceManager;
    
    static {
        (IabHelper.billingResponseCodeNames = new HashMap<Integer, String>()).put(0, "BILLING_RESPONSE_RESULT_OK");
        IabHelper.billingResponseCodeNames.put(1, "BILLING_RESPONSE_RESULT_USER_CANCELED");
        IabHelper.billingResponseCodeNames.put(2, "BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE");
        IabHelper.billingResponseCodeNames.put(3, "BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE");
        IabHelper.billingResponseCodeNames.put(4, "BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE");
        IabHelper.billingResponseCodeNames.put(5, "BILLING_RESPONSE_RESULT_DEVELOPER_ERROR");
        IabHelper.billingResponseCodeNames.put(6, "BILLING_RESPONSE_RESULT_ERROR");
        IabHelper.billingResponseCodeNames.put(7, "BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED");
        IabHelper.billingResponseCodeNames.put(8, "BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED");
        IabHelper.billingResponseCodeNames.put(-1000, "IABHELPER_ERROR_BASE");
        IabHelper.billingResponseCodeNames.put(-1001, "IABHELPER_REMOTE_EXCEPTION");
        IabHelper.billingResponseCodeNames.put(-1002, "IABHELPER_BAD_RESPONSE");
        IabHelper.billingResponseCodeNames.put(-1003, "IABHELPER_VERIFICATION_FAILED");
        IabHelper.billingResponseCodeNames.put(-1004, "IABHELPER_SEND_INTENT_FAILED");
        IabHelper.billingResponseCodeNames.put(-1005, "IABHELPER_USER_CANCELLED");
        IabHelper.billingResponseCodeNames.put(-1006, "IABHELPER_UNKNOWN_PURCHASE_RESPONSE");
        IabHelper.billingResponseCodeNames.put(-1007, "IABHELPER_MISSING_TOKEN");
        IabHelper.billingResponseCodeNames.put(-1008, "IABHELPER_UNKNOWN_ERROR");
        IabHelper.billingResponseCodeNames.put(-1009, "IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE");
        IabHelper.billingResponseCodeNames.put(-1010, "IABHELPER_INVALID_CONSUMPTION");
    }
    
    public IabHelper(final Context context, final IBillingServiceManager serviceManager, final IActivityLauncher launcher) {
        this.mDebugLog = false;
        this.mDebugTag = "IabHelper";
        this.mSetupDone = false;
        this.mDisposed = false;
        this.mSubscriptionsSupported = false;
        this.mSubscriptionUpgradeDowngradeSupported = false;
        this.mSubscriptionPurchaseHistorySupported = false;
        this.mVrSupported = false;
        this.mUnityVrEnabled = false;
        this.mDaydreamApiAvailable = false;
        this.mAsyncOperation = "";
        this.mContext = context.getApplicationContext();
        this.serviceManager = serviceManager;
        this.launcher = launcher;
        this.inv = new Inventory();
        this.logDebug("IAB helper created.");
    }
    
    public static String byteArrayToHexString(final byte[] array) {
        final StringBuilder sb = new StringBuilder(array.length * 2);
        for (int length = array.length, i = 0; i < length; ++i) {
            sb.append(String.format("%02x", array[i] & 0xFF));
        }
        return sb.toString();
    }
    
    private void finishSetup(final OnIabSetupFinishedListener onIabSetupFinishedListener, final IInAppBillingService inAppBillingService) {
    Label_0230_Outer:
        while (true) {
            final String packageName = this.mContext.getPackageName();
            while (true) {
                Label_0348: {
                    int billingSupportedExtraParams = 0;
                Label_0321:
                    while (true) {
                        int billingSupported2 = 0;
                        Label_0294: {
                            try {
                                this.logDebug("Checking for in-app billing 3 support.");
                                final int billingSupported = inAppBillingService.isBillingSupported(3, packageName, "inapp");
                                if (billingSupported != 0) {
                                    onIabSetupFinishedListener.onIabSetupFinished(new IabResult(billingSupported, "Billing V3 not supported."));
                                    this.mSubscriptionsSupported = false;
                                    return;
                                }
                                this.logDebug("In-app billing version 3 supported for " + packageName);
                                billingSupported2 = inAppBillingService.isBillingSupported(3, packageName, "subs");
                                if (billingSupported2 != 0) {
                                    break Label_0294;
                                }
                                this.logDebug("Subscriptions AVAILABLE.");
                                this.mSubscriptionsSupported = true;
                                if (inAppBillingService.isBillingSupported(5, packageName, "subs") == 0) {
                                    this.mSubscriptionUpgradeDowngradeSupported = true;
                                    this.logDebug("Subscription upgrade and downgrade are AVAILABLE.");
                                }
                                else {
                                    this.logDebug("Subscription upgrade and downgrade are NOT AVAILABLE.");
                                }
                                if (inAppBillingService.isBillingSupported(6, packageName, "subs") == 0) {
                                    this.mSubscriptionPurchaseHistorySupported = true;
                                    this.logDebug("Subscriptions information parse AVAILABLE.");
                                    if (inAppBillingService.isBillingSupported(7, packageName, "inapp") != 0) {
                                        break Label_0348;
                                    }
                                    final Bundle bundle = new Bundle();
                                    bundle.putBoolean("vr", true);
                                    billingSupportedExtraParams = inAppBillingService.isBillingSupportedExtraParams(7, this.mContext.getPackageName(), "inapp", bundle);
                                    if (billingSupportedExtraParams == 0) {
                                        this.logDebug("VR supported.");
                                        this.mVrSupported = true;
                                        this.mSetupDone = true;
                                        onIabSetupFinishedListener.onIabSetupFinished(new IabResult(0, "Setup successful."));
                                        return;
                                    }
                                    break Label_0321;
                                }
                            }
                            catch (RemoteException ex) {
                                onIabSetupFinishedListener.onIabSetupFinished(new IabResult(-1001, "RemoteException while setting up in-app billing."));
                                return;
                            }
                            this.logDebug("Subscriptions information parse NOT AVAILABLE.");
                            continue Label_0230_Outer;
                        }
                        this.logDebug("Subscriptions NOT AVAILABLE. Response: " + billingSupported2);
                        continue Label_0230_Outer;
                    }
                    this.logDebug("VR purchases  NOT AVAILABLE. Response: " + billingSupportedExtraParams);
                    continue;
                }
                this.logDebug("In app billing version 7 is not supported");
                continue;
            }
        }
    }
    
    public static String getResponseDesc(final int n) {
        final String[] split = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split("/");
        final String[] split2 = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split("/");
        if (n <= -1000) {
            final int n2 = -1000 - n;
            if (n2 >= 0 && n2 < split2.length) {
                return split2[n2];
            }
            return String.valueOf(n) + ":Unknown IAB Helper Error";
        }
        else {
            if (n < 0 || n >= split.length) {
                return String.valueOf(n) + ":Unknown";
            }
            return split[n];
        }
    }
    
    void checkSetupDone(final String s) {
        if (!this.mSetupDone) {
            this.logError("Illegal state for operation (" + s + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + s);
        }
    }
    
    void consume(final Purchase purchase, final IInAppBillingService inAppBillingService) throws IabException {
        if (!purchase.mItemType.equals("inapp")) {
            throw new IabException(-1010, "Items of type '" + purchase.mItemType + "' can't be consumed.");
        }
        String token;
        String sku;
        try {
            token = purchase.getToken();
            sku = purchase.getSku();
            if (token == null || token.equals("")) {
                this.logError("Can't consume " + sku + ". No token.");
                throw new IabException(-1007, "PurchaseInfo is missing token for sku: " + sku + " " + purchase);
            }
        }
        catch (RemoteException ex) {
            throw new IabException(-1001, "Remote exception while consuming. PurchaseInfo: " + purchase, (Exception)ex);
        }
        this.logDebug("Consuming sku: " + sku + ", token: " + token);
        final int consumePurchase = inAppBillingService.consumePurchase(3, this.mContext.getPackageName(), token);
        if (consumePurchase == 0) {
            this.logDebug("Successfully consumed sku: " + sku);
            return;
        }
        this.logDebug("Error consuming consuming sku " + sku + ". " + getResponseDesc(consumePurchase));
        throw new IabException(consumePurchase, "Error consuming sku " + sku);
    }
    
    public void consumeAsync(final Purchase purchase, final OnConsumeFinishedListener onConsumeFinishedListener, final boolean b) {
        this.checkSetupDone("consume");
        final ArrayList<Purchase> list = new ArrayList<Purchase>();
        list.add(purchase);
        this.consumeAsyncInternal(list, onConsumeFinishedListener, null, b);
    }
    
    public void consumeAsync(final List<Purchase> list, final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener, final boolean b) {
        this.checkSetupDone("consume");
        this.consumeAsyncInternal(list, null, onConsumeMultiFinishedListener, b);
    }
    
    void consumeAsyncInternal(final List<Purchase> list, final OnConsumeFinishedListener onConsumeFinishedListener, final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener, final boolean b) {
        this.serviceManager.workWith(new BillingServiceProcessor() {
            @Override
            public void workWith(final IInAppBillingService inAppBillingService) {
                final ArrayList<IabResult> list = new ArrayList<IabResult>();
                final Iterator<Purchase> iterator = list.iterator();
                while (iterator.hasNext()) {
                    while (true) {
                        final Purchase purchase = iterator.next();
                        while (true) {
                            Label_0113: {
                                try {
                                    if (!b) {
                                        break Label_0113;
                                    }
                                    IabHelper.this.consumeSilently(purchase, inAppBillingService);
                                    list.add(new IabResult(0, "Successful consume of sku " + purchase.getSku()));
                                }
                                catch (IabException ex) {
                                    list.add(ex.getResult());
                                }
                                break;
                            }
                            IabHelper.this.consume(purchase, inAppBillingService);
                            continue;
                        }
                    }
                }
                while (true) {
                    if (IabHelper.this.mDisposed || onConsumeFinishedListener == null) {
                        break Label_0175;
                    }
                    try {
                        onConsumeFinishedListener.onConsumeFinished(list.get(0), (IabResult)list.get(0));
                        if (!IabHelper.this.mDisposed && onConsumeMultiFinishedListener != null) {
                            onConsumeMultiFinishedListener.onConsumeMultiFinished(list, list);
                        }
                    }
                    catch (JSONException ex2) {
                        ex2.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        });
    }
    
    void consumeSilently(final Purchase purchase, final IInAppBillingService inAppBillingService) throws IabException {
        if (purchase.mItemType.equals("inapp")) {
            try {
                final String token = purchase.getToken();
                purchase.getSku();
                if (token != null && !token.equals("")) {
                    inAppBillingService.consumePurchase(3, this.mContext.getPackageName(), token);
                }
            }
            catch (RemoteException ex) {}
        }
    }
    
    public void dispose() {
        this.logDebug("Disposing.");
        this.mSetupDone = false;
        this.serviceManager.dispose();
        this.mDisposed = true;
    }
    
    public void enableDaydreamApi(final boolean mDaydreamApiAvailable) {
        this.mDaydreamApiAvailable = mDaydreamApiAvailable;
    }
    
    public void enableDebugLogging(final boolean mDebugLog) {
        this.mDebugLog = mDebugLog;
    }
    
    public void enableDebugLogging(final boolean mDebugLog, final String mDebugTag) {
        this.mDebugLog = mDebugLog;
        this.mDebugTag = mDebugTag;
    }
    
    public void enableUnityVr(final boolean mUnityVrEnabled) {
        this.mUnityVrEnabled = mUnityVrEnabled;
    }
    
    String getAccountId(String s) {
        final String s2 = null;
        final String s3 = null;
        String string = s2;
        try {
            final JSONObject jsonObject = new JSONObject(s);
            s = s3;
            string = s2;
            if (jsonObject.has("accountId")) {
                s = s3;
                string = s2;
                if (jsonObject.getString("accountId") != null) {
                    s = s3;
                    string = s2;
                    if (!jsonObject.getString("accountId").isEmpty()) {
                        string = s2;
                        s = (string = jsonObject.getString("accountId"));
                        this.logDebug("accountId is: " + s);
                    }
                }
            }
            return s;
        }
        catch (JSONException ex) {
            return string;
        }
    }
    
    int getResponseCodeFromBundle(final Bundle bundle) {
        final Object value = bundle.get("RESPONSE_CODE");
        if (value == null) {
            this.logDebug("Bundle with null response code, assuming OK (known issue)");
            return 0;
        }
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof Long) {
            return (int)(long)value;
        }
        this.logError("Unexpected type for bundle response code.");
        this.logError(((Long)value).getClass().getName());
        throw new RuntimeException("Unexpected type for bundle response code: " + ((Long)value).getClass().getName());
    }
    
    int getResponseCodeFromIntent(final Intent intent) {
        final Object value = intent.getExtras().get("RESPONSE_CODE");
        if (value == null) {
            this.logError("Intent with no response code, assuming OK (known issue)");
            return 0;
        }
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof Long) {
            return (int)(long)value;
        }
        this.logError("Unexpected type for intent response code.");
        this.logError(((Long)value).getClass().getName());
        throw new RuntimeException("Unexpected type for intent response code: " + ((Long)value).getClass().getName());
    }
    
    public boolean handleActivityResult(final int p0, final int p1, final Intent p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_0        
        //     2: getfield        com/unity/purchasing/googleplay/IabHelper.mRequestCode:I
        //     5: if_icmpeq       10
        //     8: iconst_0       
        //     9: ireturn        
        //    10: aload_0        
        //    11: ldc_w           "handleActivityResult"
        //    14: invokevirtual   com/unity/purchasing/googleplay/IabHelper.checkSetupDone:(Ljava/lang/String;)V
        //    17: aload_3        
        //    18: ifnonnull       62
        //    21: aload_0        
        //    22: ldc_w           "Null data in IAB activity result."
        //    25: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logError:(Ljava/lang/String;)V
        //    28: new             Lcom/unity/purchasing/googleplay/IabResult;
        //    31: dup            
        //    32: sipush          -1002
        //    35: ldc_w           "Null data in IAB result"
        //    38: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
        //    41: astore_3       
        //    42: aload_0        
        //    43: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //    46: ifnull          60
        //    49: aload_0        
        //    50: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //    53: aload_3        
        //    54: aconst_null    
        //    55: invokeinterface com/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener.onIabPurchaseFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Purchase;)V
        //    60: iconst_1       
        //    61: ireturn        
        //    62: aload_0        
        //    63: aload_3        
        //    64: invokevirtual   com/unity/purchasing/googleplay/IabHelper.getResponseCodeFromIntent:(Landroid/content/Intent;)I
        //    67: istore_1       
        //    68: aload_3        
        //    69: ldc             "INAPP_PURCHASE_DATA"
        //    71: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    74: astore          4
        //    76: aload_3        
        //    77: ldc             "INAPP_DATA_SIGNATURE"
        //    79: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    82: astore          5
        //    84: aload_0        
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: ldc_w           "Purchase data: "
        //    95: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    98: aload           4
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   106: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   109: aload_0        
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: invokespecial   java/lang/StringBuilder.<init>:()V
        //   117: ldc_w           "Data signature: "
        //   120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   123: aload           5
        //   125: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   128: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   131: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   134: iload_2        
        //   135: iconst_m1      
        //   136: if_icmpne       432
        //   139: iload_1        
        //   140: ifne            432
        //   143: aload_0        
        //   144: ldc_w           "Successful resultcode from purchase activity."
        //   147: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   150: aload_0        
        //   151: new             Ljava/lang/StringBuilder;
        //   154: dup            
        //   155: invokespecial   java/lang/StringBuilder.<init>:()V
        //   158: ldc_w           "Purchase data: "
        //   161: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   164: aload           4
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   172: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   175: aload_0        
        //   176: new             Ljava/lang/StringBuilder;
        //   179: dup            
        //   180: invokespecial   java/lang/StringBuilder.<init>:()V
        //   183: ldc_w           "Data signature: "
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: aload           5
        //   191: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   194: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   197: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   200: aload_0        
        //   201: new             Ljava/lang/StringBuilder;
        //   204: dup            
        //   205: invokespecial   java/lang/StringBuilder.<init>:()V
        //   208: ldc_w           "Extras: "
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: aload_3        
        //   215: invokevirtual   android/content/Intent.getExtras:()Landroid/os/Bundle;
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   221: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   224: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   227: aload_0        
        //   228: new             Ljava/lang/StringBuilder;
        //   231: dup            
        //   232: invokespecial   java/lang/StringBuilder.<init>:()V
        //   235: ldc_w           "Expected item type: "
        //   238: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: aload_0        
        //   242: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchasingItemType:Ljava/lang/String;
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   251: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   254: aload           4
        //   256: ifnull          264
        //   259: aload           5
        //   261: ifnonnull       335
        //   264: aload_0        
        //   265: ldc_w           "BUG: either purchaseData or dataSignature is null."
        //   268: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logError:(Ljava/lang/String;)V
        //   271: aload_0        
        //   272: new             Ljava/lang/StringBuilder;
        //   275: dup            
        //   276: invokespecial   java/lang/StringBuilder.<init>:()V
        //   279: ldc_w           "Extras: "
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: aload_3        
        //   286: invokevirtual   android/content/Intent.getExtras:()Landroid/os/Bundle;
        //   289: invokevirtual   android/os/Bundle.toString:()Ljava/lang/String;
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   298: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   301: new             Lcom/unity/purchasing/googleplay/IabResult;
        //   304: dup            
        //   305: sipush          -1008
        //   308: ldc_w           "IAB returned null purchaseData or dataSignature"
        //   311: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
        //   314: astore_3       
        //   315: aload_0        
        //   316: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   319: ifnull          333
        //   322: aload_0        
        //   323: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   326: aload_3        
        //   327: aconst_null    
        //   328: invokeinterface com/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener.onIabPurchaseFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Purchase;)V
        //   333: iconst_1       
        //   334: ireturn        
        //   335: new             Lcom/unity/purchasing/googleplay/Purchase;
        //   338: dup            
        //   339: aload_0        
        //   340: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchasingItemType:Ljava/lang/String;
        //   343: aload           4
        //   345: aload           5
        //   347: invokespecial   com/unity/purchasing/googleplay/Purchase.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   350: astore_3       
        //   351: aload_3        
        //   352: invokevirtual   com/unity/purchasing/googleplay/Purchase.getSku:()Ljava/lang/String;
        //   355: pop            
        //   356: aload_0        
        //   357: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   360: ifnull          384
        //   363: aload_0        
        //   364: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   367: new             Lcom/unity/purchasing/googleplay/IabResult;
        //   370: dup            
        //   371: iconst_0       
        //   372: ldc_w           "Success"
        //   375: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
        //   378: aload_3        
        //   379: invokeinterface com/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener.onIabPurchaseFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Purchase;)V
        //   384: iconst_1       
        //   385: ireturn        
        //   386: astore_3       
        //   387: aload_0        
        //   388: ldc_w           "Failed to parse purchase data."
        //   391: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logError:(Ljava/lang/String;)V
        //   394: aload_3        
        //   395: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   398: new             Lcom/unity/purchasing/googleplay/IabResult;
        //   401: dup            
        //   402: sipush          -1002
        //   405: ldc_w           "Failed to parse purchase data."
        //   408: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
        //   411: astore_3       
        //   412: aload_0        
        //   413: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   416: ifnull          430
        //   419: aload_0        
        //   420: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   423: aload_3        
        //   424: aconst_null    
        //   425: invokeinterface com/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener.onIabPurchaseFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Purchase;)V
        //   430: iconst_1       
        //   431: ireturn        
        //   432: iload_2        
        //   433: iconst_m1      
        //   434: if_icmpne       497
        //   437: aload_0        
        //   438: new             Ljava/lang/StringBuilder;
        //   441: dup            
        //   442: invokespecial   java/lang/StringBuilder.<init>:()V
        //   445: ldc_w           "Result code was OK but in-app billing response was not OK: "
        //   448: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   451: iload_1        
        //   452: invokestatic    com/unity/purchasing/googleplay/IabHelper.getResponseDesc:(I)Ljava/lang/String;
        //   455: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   458: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   461: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   464: aload_0        
        //   465: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   468: ifnull          384
        //   471: new             Lcom/unity/purchasing/googleplay/IabResult;
        //   474: dup            
        //   475: iload_1        
        //   476: ldc_w           "Problem purchasing item."
        //   479: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
        //   482: astore_3       
        //   483: aload_0        
        //   484: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   487: aload_3        
        //   488: aconst_null    
        //   489: invokeinterface com/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener.onIabPurchaseFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Purchase;)V
        //   494: goto            384
        //   497: iload_2        
        //   498: ifne            562
        //   501: aload_0        
        //   502: new             Ljava/lang/StringBuilder;
        //   505: dup            
        //   506: invokespecial   java/lang/StringBuilder.<init>:()V
        //   509: ldc_w           "Purchase canceled - Response: "
        //   512: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   515: iload_1        
        //   516: invokestatic    com/unity/purchasing/googleplay/IabHelper.getResponseDesc:(I)Ljava/lang/String;
        //   519: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   522: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   525: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logDebug:(Ljava/lang/String;)V
        //   528: new             Lcom/unity/purchasing/googleplay/IabResult;
        //   531: dup            
        //   532: iload_1        
        //   533: iload_1        
        //   534: invokestatic    com/unity/purchasing/googleplay/IabHelper.getResponseDesc:(I)Ljava/lang/String;
        //   537: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
        //   540: astore_3       
        //   541: aload_0        
        //   542: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   545: ifnull          384
        //   548: aload_0        
        //   549: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   552: aload_3        
        //   553: aconst_null    
        //   554: invokeinterface com/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener.onIabPurchaseFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Purchase;)V
        //   559: goto            384
        //   562: aload_0        
        //   563: new             Ljava/lang/StringBuilder;
        //   566: dup            
        //   567: invokespecial   java/lang/StringBuilder.<init>:()V
        //   570: ldc_w           "Purchase failed. Result code: "
        //   573: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   576: iload_2        
        //   577: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   580: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   583: ldc_w           ". Response: "
        //   586: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   589: iload_1        
        //   590: invokestatic    com/unity/purchasing/googleplay/IabHelper.getResponseDesc:(I)Ljava/lang/String;
        //   593: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   596: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   599: invokevirtual   com/unity/purchasing/googleplay/IabHelper.logError:(Ljava/lang/String;)V
        //   602: new             Lcom/unity/purchasing/googleplay/IabResult;
        //   605: dup            
        //   606: sipush          -1006
        //   609: ldc_w           "Unknown purchase response."
        //   612: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
        //   615: astore_3       
        //   616: aload_0        
        //   617: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   620: ifnull          384
        //   623: aload_0        
        //   624: getfield        com/unity/purchasing/googleplay/IabHelper.mPurchaseListener:Lcom/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener;
        //   627: aload_3        
        //   628: aconst_null    
        //   629: invokeinterface com/unity/purchasing/googleplay/IabHelper$OnIabPurchaseFinishedListener.onIabPurchaseFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Purchase;)V
        //   634: goto            384
        //   637: astore_3       
        //   638: goto            387
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  335    351    386    387    Lorg/json/JSONException;
        //  351    356    637    641    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0384:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void launchPurchaseFlow(final Activity activity, final String s, final int n, final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) {
        this.launchPurchaseFlow(activity, s, n, onIabPurchaseFinishedListener, "");
    }
    
    public void launchPurchaseFlow(final Activity activity, final String s, final int n, final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, final String s2) {
        this.launchPurchaseFlow(activity, s, "inapp", n, onIabPurchaseFinishedListener, s2);
    }
    
    public void launchPurchaseFlow(final Activity activity, final String s, final String s2, final int n, final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, final String s3) {
        this.checkSetupDone("launchPurchaseFlow");
        if (s2.equals("subs") && !this.mSubscriptionsSupported) {
            final IabResult iabResult = new IabResult(-1009, "Subscriptions are not available.");
            if (onIabPurchaseFinishedListener != null) {
                onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
            }
            return;
        }
        this.serviceManager.workWith(new BillingServiceProcessor() {
            @Override
            public void workWith(final IInAppBillingService inAppBillingService) {
                try {
                    IabHelper.this.logDebug("Constructing buy intent for " + s + ", item type: " + s2);
                    final String accountId = IabHelper.this.getAccountId(s3);
                    Label_0306: {
                        if (!IabHelper.this.mVrSupported) {
                            break Label_0306;
                        }
                        final Bundle bundle = new Bundle();
                        if (IabHelper.this.mUnityVrEnabled) {
                            bundle.putBoolean("vr", true);
                            IabHelper.this.logDebug("Initiating VR purchase intent");
                        }
                        if (accountId != null) {
                            bundle.putString("accountId", accountId);
                            IabHelper.this.logDebug("pass accountId to GooglePlay for fraud detection, and accountId is: " + accountId);
                        }
                        Bundle bundle2 = inAppBillingService.getBuyIntentExtraParams(7, IabHelper.this.mContext.getPackageName(), s, s2, s3, bundle);
                        final int responseCodeFromBundle = IabHelper.this.getResponseCodeFromBundle(bundle2);
                        if (responseCodeFromBundle == 0) {
                            goto Label_0432;
                        }
                        IabHelper.this.logError("Unable to buy item, Error response: " + IabHelper.getResponseDesc(responseCodeFromBundle));
                        final IabResult iabResult = new IabResult(responseCodeFromBundle, "Unable to buy item");
                        final SaneJSONObject saneJSONObject = new SaneJSONObject();
                        saneJSONObject.put("productId", (Object)s);
                        Label_0339: {
                            if (responseCodeFromBundle != 7) {
                                break Label_0339;
                            }
                            try {
                                Purchase purchase;
                                if (IabHelper.this.inv.hasPurchase(s)) {
                                    purchase = IabHelper.this.inv.getPurchase(s);
                                }
                                else {
                                    purchase = new Purchase(s2, saneJSONObject.toString(), "");
                                }
                                activity.finish();
                                if (onIabPurchaseFinishedListener != null) {
                                    onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, purchase);
                                }
                                return;
                                bundle2 = inAppBillingService.getBuyIntent(3, IabHelper.this.mContext.getPackageName(), s, s2, s3);
                            }
                            catch (JSONException ex) {
                                ex.printStackTrace();
                                return;
                            }
                        }
                    }
                }
                catch (IntentSender$SendIntentException ex2) {
                    IabHelper.this.logError("SendIntentException while launching purchase flow for sku " + s);
                    ex2.printStackTrace();
                    final IabResult iabResult2 = new IabResult(-1004, "Failed to send intent.");
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult2, null);
                    }
                    return;
                }
                catch (RemoteException ex3) {
                    IabHelper.this.logError("RemoteException while launching purchase flow for sku " + s);
                    ex3.printStackTrace();
                    final IabResult iabResult3 = new IabResult(-1001, "Remote exception while starting purchase flow");
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult3, null);
                    }
                    return;
                }
                final PendingIntent pendingIntent;
                pendingIntent.getIntentSender();
                IabHelper.this.launcher.startIntentSenderForResult(activity, pendingIntent, n, new Intent(), s);
            }
        });
    }
    
    public void launchSubscriptionPurchaseFlow(final Activity activity, final String s, final int n, final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener) {
        this.launchSubscriptionPurchaseFlow(activity, s, n, onIabPurchaseFinishedListener, "");
    }
    
    public void launchSubscriptionPurchaseFlow(final Activity activity, final String s, final int n, final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, final String s2) {
        this.launchPurchaseFlow(activity, s, "subs", n, onIabPurchaseFinishedListener, s2);
    }
    
    public void launchSubscriptionUpdateFlow(final Activity activity, final String s, final List<String> list, final int n, final OnIabPurchaseFinishedListener onIabPurchaseFinishedListener, final String s2) {
        this.checkSetupDone("launchSubscriptionUpdateFlow");
        if (!this.mSubscriptionsSupported || !this.mSubscriptionUpgradeDowngradeSupported) {
            final IabResult iabResult = new IabResult(-1009, "Subscription upgrade/downgrade is not available.");
            if (onIabPurchaseFinishedListener != null) {
                onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, null);
            }
            return;
        }
        this.serviceManager.workWith(new BillingServiceProcessor() {
            @Override
            public void workWith(final IInAppBillingService inAppBillingService) {
                try {
                    IabHelper.this.logDebug("Constructing buy intent for " + s + ", item type: " + "subs");
                    final String accountId = IabHelper.this.getAccountId(s2);
                    Label_0328: {
                        if (!IabHelper.this.mVrSupported) {
                            break Label_0328;
                        }
                        final Bundle bundle = new Bundle();
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
                        Bundle bundle2 = inAppBillingService.getBuyIntentExtraParams(7, IabHelper.this.mContext.getPackageName(), s, "subs", s2, bundle);
                        final int responseCodeFromBundle = IabHelper.this.getResponseCodeFromBundle(bundle2);
                        if (responseCodeFromBundle == 0) {
                            goto Label_0454;
                        }
                        IabHelper.this.logError("Unable to update subscription, Error response: " + IabHelper.getResponseDesc(responseCodeFromBundle));
                        final IabResult iabResult = new IabResult(responseCodeFromBundle, "Unable to update subscription item");
                        final SaneJSONObject saneJSONObject = new SaneJSONObject();
                        saneJSONObject.put("productId", (Object)s);
                        Label_0363: {
                            if (responseCodeFromBundle != 7) {
                                break Label_0363;
                            }
                            try {
                                Purchase purchase;
                                if (IabHelper.this.inv.hasPurchase(s)) {
                                    purchase = IabHelper.this.inv.getPurchase(s);
                                }
                                else {
                                    purchase = new Purchase("subs", saneJSONObject.toString(), "");
                                }
                                activity.finish();
                                if (onIabPurchaseFinishedListener != null) {
                                    onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult, purchase);
                                }
                                return;
                                bundle2 = inAppBillingService.getBuyIntentToReplaceSkus(5, IabHelper.this.mContext.getPackageName(), list, s, "subs", s2);
                            }
                            catch (JSONException ex) {
                                ex.printStackTrace();
                                return;
                            }
                        }
                    }
                }
                catch (IntentSender$SendIntentException ex2) {
                    IabHelper.this.logError("SendIntentException while launching purchase flow for sku " + s);
                    ex2.printStackTrace();
                    final IabResult iabResult2 = new IabResult(-1004, "Failed to send intent.");
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult2, null);
                    }
                    return;
                }
                catch (RemoteException ex3) {
                    IabHelper.this.logError("RemoteException while launching purchase flow for sku " + s);
                    ex3.printStackTrace();
                    final IabResult iabResult3 = new IabResult(-1001, "Remote exception while starting purchase flow");
                    if (onIabPurchaseFinishedListener != null) {
                        onIabPurchaseFinishedListener.onIabPurchaseFinished(iabResult3, null);
                    }
                    return;
                }
                final PendingIntent pendingIntent;
                pendingIntent.getIntentSender();
                IabHelper.this.launcher.startIntentSenderForResult(activity, pendingIntent, n, new Intent(), s);
            }
        });
    }
    
    void logDebug(final String s) {
        Log.i("UnityIAP", s);
    }
    
    void logError(final String s) {
        Log.e(this.mDebugTag, "In-app billing error: " + s);
    }
    
    void logWarn(final String s) {
        Log.w(this.mDebugTag, "In-app billing warning: " + s);
    }
    
    public Inventory queryInventory(final boolean b, final List<String> list, final IInAppBillingService inAppBillingService) throws IabException {
        return this.queryInventory(b, list, null, inAppBillingService);
    }
    
    public Inventory queryInventory(final boolean b, final List<String> list, final List<String> list2, final IInAppBillingService inAppBillingService) throws IabException {
        this.checkSetupDone("queryInventory");
        try {
            final int queryPurchases = this.queryPurchases(this.inv, "inapp", inAppBillingService);
            if (queryPurchases != 0) {
                throw new IabException(queryPurchases, "Error refreshing inventory (querying owned items).");
            }
            goto Label_0055;
        }
        catch (RemoteException ex) {
            throw new IabException(-1001, "Remote exception while refreshing inventory.", (Exception)ex);
        }
        catch (JSONException ex2) {
            throw new IabException(-1002, "Error parsing JSON response while refreshing inventory.", (Exception)ex2);
        }
        catch (SecurityException ex3) {
            throw new IabException(-1008, "SecurityException querying inventory, update Google Play - https://github.com/googlesamples/android-play-billing/issues/26", ex3);
        }
        if (b) {
            final int querySkuDetails = this.querySkuDetails("subs", this.inv, list, inAppBillingService);
            if (querySkuDetails != 0) {
                throw new IabException(querySkuDetails, "Error refreshing inventory (querying prices of subscriptions).");
            }
        }
        if (this.mSubscriptionPurchaseHistorySupported) {
            this.queryPurchaseHistory(this.inv, "subs", inAppBillingService);
            final int queryPurchaseHistory = this.queryPurchaseHistory(this.inv, "inapp", inAppBillingService);
            if (queryPurchaseHistory != 0) {
                throw new IabException(queryPurchaseHistory, "Error query Purchase History");
            }
        }
        return this.inv;
    }
    
    public void queryInventoryAsync(final QueryInventoryFinishedListener queryInventoryFinishedListener) {
        this.queryInventoryAsync(true, null, queryInventoryFinishedListener, 0L);
    }
    
    public void queryInventoryAsync(final boolean b, final QueryInventoryFinishedListener queryInventoryFinishedListener) {
        this.queryInventoryAsync(b, null, queryInventoryFinishedListener, 0L);
    }
    
    public void queryInventoryAsync(final boolean b, final List<String> list, final QueryInventoryFinishedListener queryInventoryFinishedListener, final long n) {
        this.checkSetupDone("queryInventory");
        this.serviceManager.workWith(new BillingServiceProcessor() {
            @Override
            public void workWith(final IInAppBillingService p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        com/unity/purchasing/googleplay/IabHelper$4.val$delay:J
                //     4: invokestatic    java/lang/Thread.sleep:(J)V
                //     7: new             Lcom/unity/purchasing/googleplay/IabResult;
                //    10: dup            
                //    11: iconst_0       
                //    12: ldc             "Inventory refresh successful."
                //    14: invokespecial   com/unity/purchasing/googleplay/IabResult.<init>:(ILjava/lang/String;)V
                //    17: astore_3       
                //    18: aconst_null    
                //    19: astore_2       
                //    20: aload_0        
                //    21: getfield        com/unity/purchasing/googleplay/IabHelper$4.this$0:Lcom/unity/purchasing/googleplay/IabHelper;
                //    24: aload_0        
                //    25: getfield        com/unity/purchasing/googleplay/IabHelper$4.val$querySkuDetails:Z
                //    28: aload_0        
                //    29: getfield        com/unity/purchasing/googleplay/IabHelper$4.val$moreSkus:Ljava/util/List;
                //    32: aload_1        
                //    33: invokevirtual   com/unity/purchasing/googleplay/IabHelper.queryInventory:(ZLjava/util/List;Lcom/android/vending/billing/IInAppBillingService;)Lcom/unity/purchasing/googleplay/Inventory;
                //    36: astore_1       
                //    37: aload_3        
                //    38: astore_2       
                //    39: aload_0        
                //    40: getfield        com/unity/purchasing/googleplay/IabHelper$4.this$0:Lcom/unity/purchasing/googleplay/IabHelper;
                //    43: getfield        com/unity/purchasing/googleplay/IabHelper.mDisposed:Z
                //    46: ifne            67
                //    49: aload_0        
                //    50: getfield        com/unity/purchasing/googleplay/IabHelper$4.val$listener:Lcom/unity/purchasing/googleplay/IabHelper$QueryInventoryFinishedListener;
                //    53: ifnull          67
                //    56: aload_0        
                //    57: getfield        com/unity/purchasing/googleplay/IabHelper$4.val$listener:Lcom/unity/purchasing/googleplay/IabHelper$QueryInventoryFinishedListener;
                //    60: aload_2        
                //    61: aload_1        
                //    62: invokeinterface com/unity/purchasing/googleplay/IabHelper$QueryInventoryFinishedListener.onQueryInventoryFinished:(Lcom/unity/purchasing/googleplay/IabResult;Lcom/unity/purchasing/googleplay/Inventory;)V
                //    67: return         
                //    68: astore_1       
                //    69: aload_1        
                //    70: invokevirtual   com/unity/purchasing/googleplay/IabException.getResult:()Lcom/unity/purchasing/googleplay/IabResult;
                //    73: astore_3       
                //    74: aload_2        
                //    75: astore_1       
                //    76: aload_3        
                //    77: astore_2       
                //    78: goto            39
                //    81: astore_1       
                //    82: aload_1        
                //    83: invokevirtual   java/lang/Exception.printStackTrace:()V
                //    86: return         
                //    87: astore_2       
                //    88: goto            7
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                          
                //  -----  -----  -----  -----  ----------------------------------------------
                //  0      7      87     91     Ljava/lang/InterruptedException;
                //  20     37     68     81     Lcom/unity/purchasing/googleplay/IabException;
                //  56     67     81     87     Ljava/lang/Exception;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0067:
                //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
                //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
                //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
    }
    
    public int queryPurchaseHistory(final Inventory inventory, final String s, final IInAppBillingService inAppBillingService) throws JSONException, RemoteException {
        if (this.mDisposed) {
            this.logDebug("queryPurchase History - Biller disposed. Returning...");
            return 0;
        }
        this.logDebug("Querying owned items' purchase history, item type: " + s);
        this.logDebug("Package name: " + this.mContext.getPackageName());
        String s2 = null;
        String string;
        do {
            this.logDebug("Calling getPurchaseHistory with continuation token: " + s2);
            final Bundle purchaseHistory = inAppBillingService.getPurchaseHistory(6, this.mContext.getPackageName(), s, s2, new Bundle());
            if (purchaseHistory == null) {
                return 0;
            }
            final int responseCodeFromBundle = this.getResponseCodeFromBundle(purchaseHistory);
            this.logDebug("Purchase history response: " + String.valueOf(responseCodeFromBundle));
            if (responseCodeFromBundle != 0) {
                this.logDebug("getPurchaseHistory() failed: " + getResponseDesc(responseCodeFromBundle));
                return responseCodeFromBundle;
            }
            if (!purchaseHistory.containsKey("INAPP_PURCHASE_ITEM_LIST") || !purchaseHistory.containsKey("INAPP_PURCHASE_DATA_LIST") || !purchaseHistory.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
                this.logError("Bundle returned from getPurchaseHistory() doesn't contain required fields.");
                return -1002;
            }
            final ArrayList stringArrayList = purchaseHistory.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            if (s.equals("inapp")) {
                final ArrayList stringArrayList2 = purchaseHistory.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                final ArrayList stringArrayList3 = purchaseHistory.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                for (int i = 0; i < stringArrayList2.size(); ++i) {
                    final String s3 = stringArrayList2.get(i);
                    final String s4 = stringArrayList3.get(i);
                    final String s5 = stringArrayList.get(i);
                    final Purchase purchase = new Purchase(s, s3, s4);
                    if (TextUtils.isEmpty((CharSequence)purchase.getToken())) {
                        this.logWarn("BUG: empty/null token!");
                        this.logDebug("Purchase data: " + s3);
                    }
                    inventory.addPurchaseToConsumablePurchaseHistory(s5, purchase);
                }
            }
            if (s.equals("subs")) {
                for (int j = 0; j < stringArrayList.size(); ++j) {
                    inventory.addPurchaseToSubscriptionPurchaseHistory(stringArrayList.get(j));
                }
            }
            string = purchaseHistory.getString("INAPP_CONTINUATION_TOKEN");
            this.logDebug("Continuation token: " + string);
            s2 = string;
        } while (!TextUtils.isEmpty((CharSequence)string));
        int n;
        if (false) {
            n = -1003;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    int queryPurchases(final Inventory inventory, final String s, final IInAppBillingService inAppBillingService) throws JSONException, RemoteException {
        if (this.mDisposed) {
            this.logDebug("queryPurchases - Biller disposed. Returning...");
            return 0;
        }
        this.logDebug("Querying owned items, item type: " + s);
        this.logDebug("Package name: " + this.mContext.getPackageName());
        String s2 = null;
        String string;
        do {
            this.logDebug("Calling getPurchases with continuation token: " + s2);
            final Bundle purchases = inAppBillingService.getPurchases(3, this.mContext.getPackageName(), s, s2);
            final int responseCodeFromBundle = this.getResponseCodeFromBundle(purchases);
            this.logDebug("Owned items response: " + String.valueOf(responseCodeFromBundle));
            if (responseCodeFromBundle != 0) {
                this.logDebug("getPurchases() failed: " + getResponseDesc(responseCodeFromBundle));
                return responseCodeFromBundle;
            }
            if (!purchases.containsKey("INAPP_PURCHASE_ITEM_LIST") || !purchases.containsKey("INAPP_PURCHASE_DATA_LIST") || !purchases.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
                this.logError("Bundle returned from getPurchases() doesn't contain required fields.");
                return -1002;
            }
            final ArrayList stringArrayList = purchases.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
            final ArrayList stringArrayList2 = purchases.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
            final ArrayList stringArrayList3 = purchases.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
            for (int i = 0; i < stringArrayList2.size(); ++i) {
                final String s3 = stringArrayList2.get(i);
                final String s4 = stringArrayList3.get(i);
                this.logDebug("Sku is owned: " + stringArrayList.get(i));
                final Purchase purchase = new Purchase(s, s3, s4);
                if (TextUtils.isEmpty((CharSequence)purchase.getToken())) {
                    this.logWarn("BUG: empty/null token!");
                    this.logDebug("Purchase data: " + s3);
                }
                inventory.addPurchase(purchase);
            }
            string = purchases.getString("INAPP_CONTINUATION_TOKEN");
            this.logDebug("Continuation token: " + string);
            s2 = string;
        } while (!TextUtils.isEmpty((CharSequence)string));
        int n;
        if (false) {
            n = -1003;
        }
        else {
            n = 0;
        }
        return n;
    }
    
    int querySkuDetails(final String s, final Inventory inventory, final List<String> list, final IInAppBillingService inAppBillingService) throws RemoteException, JSONException {
        this.logDebug("Querying SKU details.");
        final ArrayList<Object> list2 = new ArrayList<Object>();
        list2.addAll(inventory.getAllOwnedSkus(s));
        if (list != null) {
            list2.addAll(list);
        }
        if (list2.size() == 0) {
            this.logDebug("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        while (list2.size() > 0) {
            final int min = Math.min(20, list2.size());
            final ArrayList<Object> list3 = new ArrayList<Object>();
            for (int i = 0; i < min; ++i) {
                list3.add(list2.get(i));
            }
            final Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", (ArrayList)list3);
            final Bundle skuDetails = inAppBillingService.getSkuDetails(3, this.mContext.getPackageName(), s, bundle);
            list2.removeAll(list3);
            if (!skuDetails.containsKey("DETAILS_LIST")) {
                final int responseCodeFromBundle = this.getResponseCodeFromBundle(skuDetails);
                if (responseCodeFromBundle != 0) {
                    this.logDebug("getSkuDetails() failed: " + getResponseDesc(responseCodeFromBundle));
                    return responseCodeFromBundle;
                }
                this.logError("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                return -1002;
            }
            else {
                final Iterator iterator = skuDetails.getStringArrayList("DETAILS_LIST").iterator();
                while (iterator.hasNext()) {
                    inventory.addSkuDetails(new SkuDetails(s, iterator.next()));
                }
            }
        }
        return 0;
    }
    
    public void startSetup(final OnIabSetupFinishedListener onIabSetupFinishedListener) {
        if (this.mSetupDone) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        this.logDebug("Starting in-app billing setup.");
        this.serviceManager.workWith(new BillingServiceProcessor() {
            @Override
            public void workWith(final IInAppBillingService inAppBillingService) {
                IabHelper.this.finishSetup(onIabSetupFinishedListener, inAppBillingService);
            }
        });
    }
    
    public boolean subscriptionPurchaseHistorySupported() {
        return this.mSubscriptionPurchaseHistorySupported;
    }
    
    public boolean subscriptionUpgradeDowngradeSupported() {
        return this.mSubscriptionUpgradeDowngradeSupported;
    }
    
    public boolean subscriptionsSupported() {
        return this.mSubscriptionsSupported;
    }
    
    public interface OnConsumeFinishedListener
    {
        void onConsumeFinished(final Purchase p0, final IabResult p1) throws JSONException;
    }
    
    public interface OnConsumeMultiFinishedListener
    {
        void onConsumeMultiFinished(final List<Purchase> p0, final List<IabResult> p1);
    }
    
    public interface OnIabPurchaseFinishedListener
    {
        void onIabPurchaseFinished(final IabResult p0, final Purchase p1);
    }
    
    public interface OnIabSetupFinishedListener
    {
        void onIabSetupFinished(final IabResult p0);
    }
    
    public interface QueryInventoryFinishedListener
    {
        void onQueryInventoryFinished(final IabResult p0, final Inventory p1) throws Exception;
    }
}
