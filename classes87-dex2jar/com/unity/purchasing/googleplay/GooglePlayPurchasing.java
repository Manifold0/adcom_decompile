// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import java.io.Serializable;
import com.unity.purchasing.common.InitializationFailureReason;
import com.google.vr.ndk.base.DaydreamApi;
import android.os.Handler;
import com.unity3d.player.UnityPlayerActivity;
import com.unity.purchasing.common.ProductType;
import android.content.IntentFilter;
import android.os.RemoteException;
import com.android.vending.billing.IInAppBillingService;
import android.util.Log;
import com.unity.purchasing.common.UnityPurchasing;
import com.unity3d.player.UnityPlayer;
import com.unity.purchasing.common.IUnityCallback;
import android.content.Intent;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import com.unity.purchasing.common.ProductMetadata;
import java.math.BigDecimal;
import java.util.Map;
import org.json.JSONObject;
import com.unity.purchasing.common.ProductDescription;
import java.util.ArrayList;
import android.app.Activity;
import com.unity.purchasing.common.PurchaseFailureDescription;
import com.unity.purchasing.common.PurchaseFailureReason;
import com.unity.purchasing.common.IStoreCallback;
import java.util.HashSet;
import android.content.BroadcastReceiver;
import com.unity.purchasing.common.ProductDefinition;
import android.content.Context;
import com.unity.purchasing.common.StoreDeserializer;

public class GooglePlayPurchasing extends StoreDeserializer
{
    public static final int ACTIVITY_REQUEST_CODE = 999;
    protected static final String TAG = "UnityIAP";
    private static GooglePlayPurchasing instance;
    private static final boolean isDaydreamApiAvailable;
    public IabHelper.OnIabPurchaseFinishedListener PurchaseListener;
    public boolean activityPending;
    private Context context;
    Features features;
    public IabHelper helper;
    private Inventory inventory;
    private boolean isUnityVrEnabled;
    private IActivityLauncher launcher;
    private IBillingServiceManager manager;
    private int offlineBackOffTime;
    public String productJSON;
    private ProductDefinition productUnderPurchase;
    private volatile boolean purchaseInProgress;
    private BroadcastReceiver purchasesUpdatedReceiver;
    private volatile boolean subscriptionUpdateInProgress;
    private HashSet<String> suspectFailedConsumableSkus;
    private IStoreCallback unityPurchasing;
    
    static {
        boolean isDaydreamApiAvailable2 = true;
        while (true) {
            try {
                Class.forName("com.google.vr.ndk.base.DaydreamApi");
                isDaydreamApiAvailable = isDaydreamApiAvailable2;
            }
            catch (Throwable t) {
                isDaydreamApiAvailable2 = false;
                continue;
            }
            break;
        }
    }
    
    public GooglePlayPurchasing(final IStoreCallback unityPurchasing, final IabHelper helper, final IBillingServiceManager manager, final Context context, final IActivityLauncher launcher) {
        this.suspectFailedConsumableSkus = new HashSet<String>();
        this.features = new Features();
        this.PurchaseListener = new IabHelper.OnIabPurchaseFinishedListener() {
            @Override
            public void onIabPurchaseFinished(final IabResult iabResult, final Purchase purchase) {
                if (!GooglePlayPurchasing.this.purchaseInProgress) {
                    return;
                }
                log("onIabPurchaseFinished: %s", Boolean.toString(iabResult.isSuccess()));
                log(iabResult.mMessage);
                GooglePlayPurchasing.this.purchaseInProgress = false;
                if (iabResult.isSuccess()) {
                    log("Product purchased successfully!");
                    GooglePlayPurchasing.this.NotifyUnityOfPurchase(purchase);
                    return;
                }
                final int response = iabResult.getResponse();
                log("Purchase response code:%s", Integer.toString(response));
                PurchaseFailureReason purchaseFailureReason = PurchaseFailureReason.Unknown;
                GooglePlayPurchasing.this.suspectFailedConsumableSkus.add(GooglePlayPurchasing.this.productUnderPurchase.storeSpecificId);
                switch (response) {
                    case -1005:
                    case 1: {
                        purchaseFailureReason = PurchaseFailureReason.UserCancelled;
                        break;
                    }
                    case 4: {
                        purchaseFailureReason = PurchaseFailureReason.ItemUnavailable;
                        break;
                    }
                    case 2:
                    case 3: {
                        purchaseFailureReason = PurchaseFailureReason.BillingUnavailable;
                        break;
                    }
                    case 7: {
                        if (GooglePlayPurchasing.this.features.supportsPurchaseFailureReasonDuplicateTransaction) {
                            purchaseFailureReason = PurchaseFailureReason.DuplicateTransaction;
                            break;
                        }
                        break;
                    }
                }
                final PurchaseFailureDescription purchaseFailureDescription = new PurchaseFailureDescription(GooglePlayPurchasing.this.productUnderPurchase.storeSpecificId, purchaseFailureReason, "GOOGLEPLAY_" + iabResult.mMessage, (String)IabHelper.billingResponseCodeNames.get(response));
                if (iabResult.getResponse() == -1002) {
                    log("Received bad response, polling for successful purchases to investigate failure more deeply");
                    GooglePlayPurchasing.this.reconcileFailedPurchaseWithInventory(purchaseFailureDescription);
                    return;
                }
                GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(purchaseFailureDescription);
            }
        };
        this.purchasesUpdatedReceiver = null;
        this.offlineBackOffTime = 5000;
        this.purchaseInProgress = false;
        this.subscriptionUpdateInProgress = false;
        this.unityPurchasing = unityPurchasing;
        (this.helper = helper).enableDaydreamApi(GooglePlayPurchasing.isDaydreamApiAvailable);
        this.manager = manager;
        this.context = context;
        this.launcher = launcher;
        (GooglePlayPurchasing.instance = this).registerPurchasesUpdatedReceiver();
    }
    
    public static boolean ContinuePurchase(final Activity activity, final String s, final String s2) {
        if (GooglePlayPurchasing.instance != null) {
            GooglePlayPurchasing.instance.StartPurchase(activity, s, s2);
            return true;
        }
        return false;
    }
    
    public static boolean ContinueSubscriptionUpdate(final Activity activity, final String s, final String s2) {
        if (GooglePlayPurchasing.instance != null) {
            GooglePlayPurchasing.instance.StartSubscriptionUpdate(activity, s, s2);
            return true;
        }
        return false;
    }
    
    private void NotifyUnityOfProducts(final Inventory inventory) {
        final ArrayList<ProductDescription> list = new ArrayList<ProductDescription>();
        final JSONObject jsonObject = new JSONObject();
    Label_0087_Outer:
        for (Object o : inventory.mSkuMap.entrySet()) {
            Object encodeReceipt = ((Map.Entry<String, SkuDetails>)o).getValue();
            while (true) {
                try {
                    jsonObject.put((String)((Map.Entry<String, SkuDetails>)o).getKey(), (Object)((SkuDetails)encodeReceipt).getOriginalJSON());
                    final ProductMetadata productMetadata = new ProductMetadata(((SkuDetails)encodeReceipt).getPrice(), ((SkuDetails)encodeReceipt).getTitle(), ((SkuDetails)encodeReceipt).getDescription(), ((SkuDetails)encodeReceipt).getISOCurrencyCode(), new BigDecimal(((SkuDetails)encodeReceipt).getPriceInMicros()).divide(new BigDecimal(1000000)));
                    encodeReceipt = null;
                    String orderIdOrPurchaseToken = null;
                    o = ((Map.Entry<String, SkuDetails>)o).getKey();
                    if (inventory.hasPurchase((String)o)) {
                        final Purchase purchase = inventory.getPurchase((String)o);
                        encodeReceipt = this.encodeReceipt(purchase, inventory.getSkuDetails((String)o));
                        orderIdOrPurchaseToken = purchase.getOrderIdOrPurchaseToken();
                    }
                    list.add(new ProductDescription((String)o, productMetadata, (String)encodeReceipt, orderIdOrPurchaseToken));
                    continue Label_0087_Outer;
                }
                catch (JSONException ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
            break;
        }
        this.productJSON = jsonObject.toString();
        this.unityPurchasing.OnProductsRetrieved((List)list);
    }
    
    private void NotifyUnityOfPurchase(final Purchase purchase) {
        log("NotifyUnityOfPurchase");
        this.inventory.addPurchase(purchase);
        final SkuDetails skuDetails = this.inventory.getSkuDetails(purchase.getSku());
        if (purchase.getItemType().equals("subs")) {
            this.inventory.addPurchaseToSubscriptionPurchaseHistory(purchase.getSku());
        }
        this.unityPurchasing.OnPurchaseSucceeded(purchase.getSku(), this.encodeReceipt(purchase, skuDetails), purchase.getOrderIdOrPurchaseToken());
    }
    
    public static void ProcessActivityResult(final int n, final int n2, final Intent intent) {
        if (GooglePlayPurchasing.instance != null) {
            GooglePlayPurchasing.instance.onActivityResult(n, n2, intent);
        }
    }
    
    private void QueryInventory(final List<String> list, final long n) {
        log("QueryInventory: %s", Integer.toString(list.size()));
        this.helper.queryInventoryAsync(true, list, (IabHelper.QueryInventoryFinishedListener)new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(final IabResult iabResult, final Inventory inventory) throws Exception {
                log("onQueryInventoryFinished: %s", Boolean.toString(iabResult.isSuccess()));
                log(iabResult.mMessage);
                if (iabResult.isFailure()) {
                    log("Failed to Query inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    GooglePlayPurchasing.this.QueryInventory(list, GooglePlayPurchasing.this.offlineBackOffTime);
                    GooglePlayPurchasing.this.offlineBackOffTime = Math.min(300000, GooglePlayPurchasing.this.offlineBackOffTime * 2);
                    return;
                }
                GooglePlayPurchasing.this.inventory = inventory;
                for (final String s : list) {
                    if (GooglePlayPurchasing.this.inventory.hasConsumablePurchaseHistory(s) && !GooglePlayPurchasing.this.inventory.hasPurchase(s)) {
                        GooglePlayPurchasing.this.helper.consumeAsync(GooglePlayPurchasing.this.inventory.getHistoryPurchase(s), null, true);
                    }
                }
                GooglePlayPurchasing.this.NotifyUnityOfProducts(inventory);
            }
        }, n);
    }
    
    private String addFreeTrialAndIntroPriceFlagToDeveloperPayload(final String p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/unity/purchasing/googleplay/GooglePlayPurchasing.inventory:Lcom/unity/purchasing/googleplay/Inventory;
        //     4: aload_2        
        //     5: invokevirtual   com/unity/purchasing/googleplay/Inventory.hasDetails:(Ljava/lang/String;)Z
        //     8: ifeq            130
        //    11: aload_0        
        //    12: getfield        com/unity/purchasing/googleplay/GooglePlayPurchasing.inventory:Lcom/unity/purchasing/googleplay/Inventory;
        //    15: aload_2        
        //    16: invokevirtual   com/unity/purchasing/googleplay/Inventory.getSkuDetails:(Ljava/lang/String;)Lcom/unity/purchasing/googleplay/SkuDetails;
        //    19: astore          10
        //    21: new             Lorg/json/JSONObject;
        //    24: dup            
        //    25: invokespecial   org/json/JSONObject.<init>:()V
        //    28: astore          13
        //    30: new             Ljava/lang/String;
        //    33: dup            
        //    34: invokespecial   java/lang/String.<init>:()V
        //    37: astore          11
        //    39: aload_1        
        //    40: ifnonnull       136
        //    43: ldc_w           ""
        //    46: astore_1       
        //    47: aload_1        
        //    48: invokevirtual   java/lang/String.getBytes:()[B
        //    51: iconst_0       
        //    52: invokestatic    android/util/Base64.encodeToString:([BI)Ljava/lang/String;
        //    55: astore          12
        //    57: aload           10
        //    59: ifnonnull       159
        //    62: aload           13
        //    64: ldc_w           "developerPayload"
        //    67: aload           12
        //    69: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //    72: pop            
        //    73: aload           13
        //    75: ldc_w           "is_free_trial"
        //    78: iconst_0       
        //    79: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //    82: pop            
        //    83: aload           13
        //    85: ldc_w           "has_introductory_price_trial"
        //    88: iconst_0       
        //    89: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //    92: pop            
        //    93: aload           13
        //    95: ldc_w           "is_updated"
        //    98: iconst_0       
        //    99: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   102: pop            
        //   103: aload           13
        //   105: ldc_w           "update_subscription_metadata"
        //   108: aconst_null    
        //   109: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   112: pop            
        //   113: aload           13
        //   115: ldc_w           "accountId"
        //   118: aload           11
        //   120: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   123: pop            
        //   124: aload           13
        //   126: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   129: areturn        
        //   130: aconst_null    
        //   131: astore          10
        //   133: goto            21
        //   136: new             Lorg/json/JSONObject;
        //   139: dup            
        //   140: aload_1        
        //   141: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   144: ldc_w           "accountId"
        //   147: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   150: astore          12
        //   152: aload           12
        //   154: astore          11
        //   156: goto            47
        //   159: iconst_0       
        //   160: istore          6
        //   162: aconst_null    
        //   163: astore_1       
        //   164: aload_3        
        //   165: ifnull          425
        //   168: ldc_w           "oldSkuMetadata is NOT null"
        //   171: invokestatic    com/unity/purchasing/googleplay/GooglePlayPurchasing.log:(Ljava/lang/String;)V
        //   174: iconst_1       
        //   175: istore          6
        //   177: aload_0        
        //   178: aload_3        
        //   179: aload           10
        //   181: invokespecial   com/unity/purchasing/googleplay/GooglePlayPurchasing.getUpdateMetadata:(Ljava/lang/String;Lcom/unity/purchasing/googleplay/SkuDetails;)Ljava/lang/String;
        //   184: astore_1       
        //   185: iconst_0       
        //   186: istore          5
        //   188: aload           10
        //   190: invokevirtual   com/unity/purchasing/googleplay/SkuDetails.getType:()Ljava/lang/String;
        //   193: astore_3       
        //   194: aload           10
        //   196: invokevirtual   com/unity/purchasing/googleplay/SkuDetails.getIntroductoryPricePeriod:()Ljava/lang/String;
        //   199: astore          14
        //   201: aload           10
        //   203: invokevirtual   com/unity/purchasing/googleplay/SkuDetails.getFreeTrialPeriod:()Ljava/lang/String;
        //   206: astore          10
        //   208: aload_0        
        //   209: getfield        com/unity/purchasing/googleplay/GooglePlayPurchasing.inventory:Lcom/unity/purchasing/googleplay/Inventory;
        //   212: aload_2        
        //   213: invokevirtual   com/unity/purchasing/googleplay/Inventory.hasPurchaseHistory:(Ljava/lang/String;)Z
        //   216: istore          9
        //   218: aload_0        
        //   219: getfield        com/unity/purchasing/googleplay/GooglePlayPurchasing.inventory:Lcom/unity/purchasing/googleplay/Inventory;
        //   222: ldc_w           "subs"
        //   225: invokevirtual   com/unity/purchasing/googleplay/Inventory.getAllSkus:(Ljava/lang/String;)Ljava/util/List;
        //   228: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   233: astore_2       
        //   234: iload           5
        //   236: istore          4
        //   238: aload_2        
        //   239: invokeinterface java/util/Iterator.hasNext:()Z
        //   244: ifeq            291
        //   247: aload_2        
        //   248: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   253: checkcast       Ljava/lang/String;
        //   256: astore          15
        //   258: aload_0        
        //   259: getfield        com/unity/purchasing/googleplay/GooglePlayPurchasing.inventory:Lcom/unity/purchasing/googleplay/Inventory;
        //   262: aload           15
        //   264: invokevirtual   com/unity/purchasing/googleplay/Inventory.getSkuDetails:(Ljava/lang/String;)Lcom/unity/purchasing/googleplay/SkuDetails;
        //   267: invokevirtual   com/unity/purchasing/googleplay/SkuDetails.getFreeTrialPeriod:()Ljava/lang/String;
        //   270: invokevirtual   java/lang/String.isEmpty:()Z
        //   273: ifne            234
        //   276: aload_0        
        //   277: getfield        com/unity/purchasing/googleplay/GooglePlayPurchasing.inventory:Lcom/unity/purchasing/googleplay/Inventory;
        //   280: aload           15
        //   282: invokevirtual   com/unity/purchasing/googleplay/Inventory.hasPurchaseHistory:(Ljava/lang/String;)Z
        //   285: ifeq            234
        //   288: iconst_1       
        //   289: istore          4
        //   291: iconst_1       
        //   292: istore          7
        //   294: iconst_1       
        //   295: istore          8
        //   297: aload_3        
        //   298: ldc_w           "inapp"
        //   301: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   304: ifne            325
        //   307: aload           10
        //   309: invokevirtual   java/lang/String.isEmpty:()Z
        //   312: ifne            325
        //   315: iload           9
        //   317: ifne            325
        //   320: iload           4
        //   322: ifeq            328
        //   325: iconst_0       
        //   326: istore          7
        //   328: aload_3        
        //   329: ldc_w           "inapp"
        //   332: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   335: ifne            351
        //   338: aload           14
        //   340: invokevirtual   java/lang/String.isEmpty:()Z
        //   343: ifne            351
        //   346: iload           9
        //   348: ifeq            354
        //   351: iconst_0       
        //   352: istore          8
        //   354: aload           13
        //   356: ldc_w           "developerPayload"
        //   359: aload           12
        //   361: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   364: pop            
        //   365: aload           13
        //   367: ldc_w           "is_free_trial"
        //   370: iload           7
        //   372: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   375: pop            
        //   376: aload           13
        //   378: ldc_w           "has_introductory_price_trial"
        //   381: iload           8
        //   383: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   386: pop            
        //   387: aload           13
        //   389: ldc_w           "is_updated"
        //   392: iload           6
        //   394: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Z)Lorg/json/JSONObject;
        //   397: pop            
        //   398: aload           13
        //   400: ldc_w           "update_subscription_metadata"
        //   403: aload_1        
        //   404: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   407: pop            
        //   408: aload           13
        //   410: ldc_w           "accountId"
        //   413: aload           11
        //   415: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   418: pop            
        //   419: aload           13
        //   421: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   424: areturn        
        //   425: ldc_w           "oldSkuMetadata is null"
        //   428: invokestatic    com/unity/purchasing/googleplay/GooglePlayPurchasing.log:(Ljava/lang/String;)V
        //   431: goto            185
        //   434: astore_1       
        //   435: goto            419
        //   438: astore_1       
        //   439: goto            124
        //   442: astore          12
        //   444: goto            47
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  62     124    438    442    Lorg/json/JSONException;
        //  136    152    442    447    Lorg/json/JSONException;
        //  354    419    434    438    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0136:
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
    
    private void consumeSuspectFailedPurchase(final ProductDefinition productDefinition, final String s) {
        final String storeSpecificId = productDefinition.storeSpecificId;
        this.suspectFailedConsumableSkus.remove(storeSpecificId);
        this.helper.queryInventoryAsync(false, this.inventory.getAllSkus("inapp"), (IabHelper.QueryInventoryFinishedListener)new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(final IabResult iabResult, final Inventory inventory) throws Exception {
                if (iabResult.isFailure()) {
                    log("Failed to Query inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    GooglePlayPurchasing.this.QueryInventory(GooglePlayPurchasing.this.inventory.getAllSkus("inapp"), GooglePlayPurchasing.this.offlineBackOffTime);
                    GooglePlayPurchasing.this.offlineBackOffTime = Math.min(300000, GooglePlayPurchasing.this.offlineBackOffTime * 2);
                    return;
                }
                GooglePlayPurchasing.this.inventory = inventory;
                if (GooglePlayPurchasing.this.inventory.hasConsumablePurchaseHistory(storeSpecificId)) {
                    GooglePlayPurchasing.this.helper.consumeAsync(GooglePlayPurchasing.this.inventory.getHistoryPurchase(storeSpecificId), (IabHelper.OnConsumeFinishedListener)new OnConsumeFinishedListener() {
                        @Override
                        public void onConsumeFinished(final Purchase purchase, final IabResult iabResult) throws JSONException {
                            GooglePlayPurchasing.this.Purchase(productDefinition, s);
                        }
                    }, true);
                    return;
                }
                GooglePlayPurchasing.this.Purchase(productDefinition, s);
            }
        }, 0L);
    }
    
    private String encodeReceipt(final Purchase purchase, final SkuDetails skuDetails) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("json", (Object)purchase.getOriginalJson());
            jsonObject.put("signature", (Object)purchase.getSignature());
            jsonObject.put("skuDetails", (Object)skuDetails.getOriginalJSON());
            jsonObject.put("isPurchaseHistorySupported", this.helper.subscriptionPurchaseHistorySupported());
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject.toString();
        }
    }
    
    private Purchase findPurchaseByOrderId(final String s) {
        for (final Purchase purchase : this.inventory.getAllPurchases()) {
            if (purchase.getOrderIdOrPurchaseToken().equals(s)) {
                return purchase;
            }
        }
        log("No consumable with order %s", s);
        return null;
    }
    
    private String getUpdateMetadata(String s, SkuDetails skuDetails) {
        final String s2 = null;
        final int n = 0;
        final int n2 = 0;
        final long priceInMicros = skuDetails.getPriceInMicros();
        long n3 = 0L;
        int boolean1 = n;
        int n4 = n2;
        String s3 = s2;
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject(s);
                boolean1 = n;
                n4 = n2;
                s3 = s2;
                s = jsonObject.getString("productId");
                boolean1 = n;
                n4 = n2;
                s3 = s;
                final boolean b = (boolean1 = (jsonObject.getBoolean("is_free_trial") ? 1 : 0)) != 0;
                n4 = n2;
                s3 = s;
                final boolean boolean2 = jsonObject.getBoolean("is_introductory_price_period");
                boolean1 = (b ? 1 : 0);
                n4 = (boolean2 ? 1 : 0);
                s3 = s;
                n3 = (long)jsonObject.getDouble("remaining_time_in_seconds");
                n4 = (boolean2 ? 1 : 0);
                boolean1 = (b ? 1 : 0);
                if (s == null || !this.inventory.hasDetails(s)) {
                    return null;
                }
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                s = s3;
                continue;
            }
            break;
        }
        final SkuDetails skuDetails2 = this.inventory.getSkuDetails(s);
        if (boolean1 != 0) {
            n3 = 0L;
        }
        Label_0257: {
            if (n4 == 0 || skuDetails.getPriceInMicros() == 0L) {
                break Label_0257;
            }
            long n5 = skuDetails2.getIntroductoryPriceInMicros();
            s = skuDetails2.getIntroductoryPricePeriod();
            while (true) {
                skuDetails = (SkuDetails)new JSONObject();
                try {
                    ((JSONObject)skuDetails).put("old_sku_remaining_seconds", n3);
                    ((JSONObject)skuDetails).put("old_sku_price_in_micros", n5);
                    ((JSONObject)skuDetails).put("old_sku_period_string", (Object)s);
                    ((JSONObject)skuDetails).put("new_sku_price_in_micros", priceInMicros);
                    return ((JSONObject)skuDetails).toString();
                    n5 = skuDetails2.getPriceInMicros();
                    s = skuDetails2.getSubscriptionPeriod();
                }
                catch (JSONException ex2) {
                    ex2.printStackTrace();
                    return ((JSONObject)skuDetails).toString();
                }
            }
        }
    }
    
    public static GooglePlayPurchasing instance(final IUnityCallback unityCallback) {
        if (GooglePlayPurchasing.instance == null) {
            final BillingServiceManager billingServiceManager = new BillingServiceManager((Context)UnityPlayer.currentActivity);
            GooglePlayPurchasing.instance = new GooglePlayPurchasing((IStoreCallback)new UnityPurchasing(unityCallback), new IabHelper((Context)UnityPlayer.currentActivity, billingServiceManager, new ActivityLauncher()), billingServiceManager, (Context)UnityPlayer.currentActivity, new ActivityLauncher());
        }
        return GooglePlayPurchasing.instance;
    }
    
    private static void log(final String s) {
        Log.i("UnityIAP", s);
    }
    
    private static void log(final String s, final String s2) {
        log(String.format(s, s2));
    }
    
    private void pollForNewPurchases() {
        this.reconcileFailedPurchaseWithInventory(null);
    }
    
    private void reconcileFailedPurchaseWithInventory(final PurchaseFailureDescription purchaseFailureDescription) {
        this.manager.workWith(new BillingServiceProcessor() {
            @Override
            public void workWith(IInAppBillingService purchase) {
                while (true) {
                    boolean hasPurchase = false;
                    final boolean b = false;
                    final int n = 0;
                Label_0213_Outer:
                    while (true) {
                        Label_0381: {
                            while (true) {
                            Block_12_Outer:
                                while (true) {
                                    int hasPurchase2 = 0;
                                    Label_0367: {
                                        try {
                                            if (purchaseFailureDescription == null) {
                                                break Label_0381;
                                            }
                                            hasPurchase2 = (GooglePlayPurchasing.this.inventory.hasPurchase(purchaseFailureDescription.productId) ? 1 : 0);
                                            if (GooglePlayPurchasing.this.helper.queryPurchases(GooglePlayPurchasing.this.inventory, "inapp", (IInAppBillingService)purchase) != 0) {
                                                log("Received bad response from queryPurchases");
                                            }
                                            if (purchaseFailureDescription != null) {
                                                hasPurchase = GooglePlayPurchasing.this.inventory.hasPurchase(purchaseFailureDescription.productId);
                                            }
                                            int n2 = n;
                                            if (purchaseFailureDescription != null) {
                                                if (hasPurchase2 != 0) {
                                                    break Label_0367;
                                                }
                                                if (hasPurchase) {
                                                    break Label_0367;
                                                }
                                                GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(purchaseFailureDescription);
                                                n2 = 1;
                                            }
                                            // iftrue(Label_0189:, !hasPurchase)
                                            while (true) {
                                                while (true) {
                                                    if (n2 == 0) {
                                                        GooglePlayPurchasing.this.NotifyUnityOfProducts(GooglePlayPurchasing.this.inventory);
                                                        return;
                                                    }
                                                    break;
                                                    n2 = n;
                                                    purchase = (JSONException)GooglePlayPurchasing.this.inventory.getPurchase(purchaseFailureDescription.productId);
                                                    GooglePlayPurchasing.this.unityPurchasing.OnPurchaseSucceeded(((Purchase)purchase).getSku(), GooglePlayPurchasing.this.encodeReceipt((Purchase)purchase, GooglePlayPurchasing.this.inventory.getSkuDetails(purchaseFailureDescription.productId)), ((Purchase)purchase).getOrderIdOrPurchaseToken());
                                                    n2 = 1;
                                                    continue Block_12_Outer;
                                                }
                                                n2 = n;
                                                continue Label_0213_Outer;
                                            }
                                        }
                                        // iftrue(Label_0189:, hasPurchase2 != 0)
                                        catch (RemoteException ex) {}
                                        catch (JSONException purchase) {
                                            goto Label_0323;
                                        }
                                    }
                                    if (hasPurchase2 != 0 && hasPurchase) {
                                        continue Label_0213_Outer;
                                    }
                                    break;
                                }
                                continue;
                            }
                        }
                        int hasPurchase2 = 0;
                        continue;
                    }
                }
            }
        });
    }
    
    private void registerPurchasesUpdatedReceiver() {
        if (this.purchasesUpdatedReceiver == null) {
            this.purchasesUpdatedReceiver = new BroadcastReceiver() {
                public void onReceive(final Context context, final Intent intent) {
                    GooglePlayPurchasing.this.pollForNewPurchases();
                }
            };
            this.context.registerReceiver(this.purchasesUpdatedReceiver, new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED"));
        }
    }
    
    public void FinishAdditionalTransaction(final String s, final String s2) {
        log("Finish transaction:%s", s2);
        final Purchase purchaseByOrderId = this.findPurchaseByOrderId(s2);
        if (purchaseByOrderId != null) {
            log("Consuming %s", purchaseByOrderId.getSku());
            this.inventory.erasePurchase(purchaseByOrderId.getSku());
            this.helper.consumeAsync(purchaseByOrderId, (IabHelper.OnConsumeFinishedListener)new IabHelper.OnConsumeFinishedListener() {
                @Override
                public void onConsumeFinished(final Purchase purchase, final IabResult iabResult) throws JSONException {
                    log("onConsumeFinished:%s", Boolean.toString(iabResult.isSuccess()));
                    log(iabResult.mMessage);
                    log(String.valueOf(iabResult.getResponse()));
                }
            }, false);
        }
    }
    
    public void FinishTransaction(final ProductDefinition productDefinition, final String s) {
        log("Finish transaction:%s", s);
        if (productDefinition == null) {
            log("Received FinishTransaction for unknown product with transaction %s. Not consuming.", s);
        }
        else if (productDefinition.type == ProductType.Consumable) {
            final Purchase purchaseByOrderId = this.findPurchaseByOrderId(s);
            if (purchaseByOrderId != null) {
                log("Consuming %s", purchaseByOrderId.getSku());
                this.inventory.erasePurchase(purchaseByOrderId.getSku());
                this.helper.consumeAsync(purchaseByOrderId, (IabHelper.OnConsumeFinishedListener)new IabHelper.OnConsumeFinishedListener() {
                    @Override
                    public void onConsumeFinished(final Purchase purchase, final IabResult iabResult) throws JSONException {
                        log("onConsumeFinished:%s", Boolean.toString(iabResult.isSuccess()));
                        log(iabResult.mMessage);
                        log(String.valueOf(iabResult.getResponse()));
                    }
                }, false);
            }
        }
    }
    
    public void Purchase(final ProductDefinition productDefinition) {
        this.Purchase(productDefinition, null);
    }
    
    public void Purchase(final ProductDefinition productUnderPurchase, final String s) {
        if (this.purchaseInProgress) {
            this.unityPurchasing.OnPurchaseFailed(new PurchaseFailureDescription(productUnderPurchase.storeSpecificId, PurchaseFailureReason.ExistingPurchasePending));
            return;
        }
        if (productUnderPurchase.type == ProductType.Consumable && this.suspectFailedConsumableSkus.contains(productUnderPurchase.storeSpecificId) && !this.inventory.hasPurchase(productUnderPurchase.storeSpecificId)) {
            this.consumeSuspectFailedPurchase(productUnderPurchase, s);
            return;
        }
        final String storeSpecificId = productUnderPurchase.storeSpecificId;
        this.productUnderPurchase = productUnderPurchase;
        log("onPurchaseProduct: %s", storeSpecificId);
        final SkuDetails skuDetails = this.inventory.getSkuDetails(storeSpecificId);
        log("ITEM TYPE:%s", skuDetails.getType());
        final boolean b = this.context instanceof UnityPlayerActivity && this.isUnityVrEnabled && GooglePlayPurchasing.isDaydreamApiAvailable;
        final Intent purchaseIntent = this.createPurchaseIntent(b);
        purchaseIntent.putExtra("productId", storeSpecificId);
        purchaseIntent.putExtra("itemType", skuDetails.getType());
        purchaseIntent.putExtra("developerPayload", s);
        this.purchaseInProgress = true;
        this.activityPending = true;
        if (b) {
            new Handler(this.context.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    purchaseIntent.putExtra("vr", true);
                    final DaydreamApi create = DaydreamApi.create(GooglePlayPurchasing.this.context);
                    create.launchInVr(purchaseIntent);
                    create.close();
                }
            });
            return;
        }
        this.launcher.startActivity(this.context, purchaseIntent);
    }
    
    public void RestoreTransactions(final IGooglePlayStoreCallback googlePlayStoreCallback) {
        this.helper.queryInventoryAsync(true, new ArrayList<String>(), (IabHelper.QueryInventoryFinishedListener)new IabHelper.QueryInventoryFinishedListener() {
            @Override
            public void onQueryInventoryFinished(final IabResult iabResult, final Inventory inventory) throws Exception {
                log("RestoreInventoryFinished: %s", Boolean.toString(iabResult.isSuccess()));
                log(iabResult.mMessage);
                if (iabResult.isFailure()) {
                    log("Failed to Restore inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    googlePlayStoreCallback.OnTransactionsRestored(false);
                    return;
                }
                GooglePlayPurchasing.this.inventory = inventory;
                GooglePlayPurchasing.this.NotifyUnityOfProducts(inventory);
                googlePlayStoreCallback.OnTransactionsRestored(true);
            }
        }, 0L);
    }
    
    public void RetrieveProducts(final List<ProductDefinition> list) {
        final ArrayList<String> list2 = new ArrayList<String>();
        final Iterator<ProductDefinition> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().storeSpecificId);
        }
        final IabHelper.OnIabSetupFinishedListener onIabSetupFinishedListener = new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(final IabResult iabResult) {
                log("onIabSetupFinished: %s", Integer.toString(iabResult.mResponse));
                if (iabResult.isFailure()) {
                    log("Failed to setup IAB. Notifying Unity...");
                    GooglePlayPurchasing.this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
                    return;
                }
                log("Requesting %s products", Integer.toString(list2.size()));
                GooglePlayPurchasing.this.QueryInventory(list2, 0L);
            }
        };
        if (!this.helper.mSetupDone) {
            try {
                this.manager.initialise();
                this.helper.startSetup((IabHelper.OnIabSetupFinishedListener)onIabSetupFinishedListener);
                return;
            }
            catch (GooglePlayBillingUnAvailableException ex) {
                this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
                return;
            }
        }
        log("Requesting %s products", Integer.toString(list2.size()));
        this.QueryInventory(list2, 0L);
    }
    
    public void SetFeatures(final String s) {
        final String[] split = s.split(",");
        for (int length = split.length, i = 0; i < length; ++i) {
            if (split[i].equals("supportsPurchaseFailureReasonDuplicateTransaction")) {
                this.features.supportsPurchaseFailureReasonDuplicateTransaction = true;
            }
        }
    }
    
    public void SetUnityVrEnabled(final boolean isUnityVrEnabled) {
        this.isUnityVrEnabled = isUnityVrEnabled;
        log("isUnityVrEnabled = %s", String.valueOf(this.isUnityVrEnabled));
    }
    
    public void StartPurchase(final Activity activity, final String s, String addFreeTrialAndIntroPriceFlagToDeveloperPayload) {
        this.helper.enableUnityVr(this.isUnityVrEnabled);
        addFreeTrialAndIntroPriceFlagToDeveloperPayload = this.addFreeTrialAndIntroPriceFlagToDeveloperPayload(addFreeTrialAndIntroPriceFlagToDeveloperPayload, s, null);
        if (this.inventory.getSkuDetails(s).mItemType == "inapp") {
            this.helper.launchPurchaseFlow(activity, s, 999, this.PurchaseListener, addFreeTrialAndIntroPriceFlagToDeveloperPayload);
            return;
        }
        this.helper.launchSubscriptionPurchaseFlow(activity, s, 999, this.PurchaseListener, addFreeTrialAndIntroPriceFlagToDeveloperPayload);
    }
    
    public void StartSubscriptionUpdate(final Activity activity, String addFreeTrialAndIntroPriceFlagToDeveloperPayload, final String s) {
        String string = null;
    Label_0066:
        while (true) {
            try {
                final JSONObject jsonObject = new JSONObject(addFreeTrialAndIntroPriceFlagToDeveloperPayload);
                if (jsonObject.has("productId")) {
                    string = jsonObject.getString("productId");
                }
                else {
                    string = null;
                }
                if (string == null) {
                    log("Error: the product that is going to be updated does not have a valid product id");
                    return;
                }
                break Label_0066;
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            continue;
        }
        if (!this.inventory.hasDetails(string)) {
            log("Error: the product that is going to be updated is not in the current inventory");
            return;
        }
        if (!this.inventory.hasPurchase(string)) {
            log("Error: the product that is going to be updated has not been purchased yet.");
            return;
        }
        if (!this.inventory.hasDetails(s)) {
            log("Error: the product that is going to be updated to is not in the current inventory");
            return;
        }
        this.productUnderPurchase = new ProductDefinition(s, s, ProductType.Subscription);
        final ArrayList<String> list = new ArrayList<String>();
        list.add(string);
        addFreeTrialAndIntroPriceFlagToDeveloperPayload = this.addFreeTrialAndIntroPriceFlagToDeveloperPayload(null, s, addFreeTrialAndIntroPriceFlagToDeveloperPayload);
        this.helper.launchSubscriptionUpdateFlow(activity, s, list, 999, this.PurchaseListener, addFreeTrialAndIntroPriceFlagToDeveloperPayload);
    }
    
    public void UpgradeDowngradeSubscription(final String s, final String s2) {
        if (this.subscriptionUpdateInProgress) {
            log("Subscription update is in progress");
            return;
        }
        if (!this.helper.subscriptionUpgradeDowngradeSupported()) {
            log("UpgradeDowngradeSubscription is not supported, this service needs v5 and higher android in app billing api");
            return;
        }
        if (s == null || s.length() == 0 || s2 == null || s2.length() == 0) {
            log("Cannot update subscription. Subscription product identifiers(SKUs) must not be empty");
            return;
        }
        final boolean b = this.context instanceof UnityPlayerActivity && this.isUnityVrEnabled && GooglePlayPurchasing.isDaydreamApiAvailable;
        final Intent purchaseIntent = this.createPurchaseIntent(b);
        purchaseIntent.putExtra("oldSkuMetadata", s);
        purchaseIntent.putExtra("newSku", s2);
        purchaseIntent.putExtra("type", "subscription_update");
        this.subscriptionUpdateInProgress = true;
        this.purchaseInProgress = true;
        this.activityPending = true;
        if (b) {
            new Handler(this.context.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    purchaseIntent.putExtra("vr", true);
                    final DaydreamApi create = DaydreamApi.create(GooglePlayPurchasing.this.context);
                    create.launchInVr(purchaseIntent);
                    create.close();
                }
            });
            return;
        }
        this.launcher.startActivity(this.context, purchaseIntent);
    }
    
    protected Intent createPurchaseIntent(final boolean b) {
        Serializable s;
        if (b) {
            s = VRPurchaseActivity.class;
        }
        else {
            s = PurchaseActivity.class;
        }
        return new Intent(this.context, (Class)s);
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        if (this.helper != null) {
            log("onActivityResult");
            this.helper.handleActivityResult(n, n2, intent);
            this.purchaseInProgress = false;
            this.subscriptionUpdateInProgress = false;
        }
    }
    
    class Features
    {
        public boolean supportsPurchaseFailureReasonDuplicateTransaction;
    }
}
