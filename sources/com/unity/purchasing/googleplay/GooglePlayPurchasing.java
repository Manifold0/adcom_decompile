package com.unity.purchasing.googleplay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.google.vr.ndk.base.DaydreamApi;
import com.ironsource.sdk.precache.DownloadManager;
import com.unity.purchasing.common.IStoreCallback;
import com.unity.purchasing.common.IUnityCallback;
import com.unity.purchasing.common.InitializationFailureReason;
import com.unity.purchasing.common.ProductDefinition;
import com.unity.purchasing.common.ProductDescription;
import com.unity.purchasing.common.ProductMetadata;
import com.unity.purchasing.common.ProductType;
import com.unity.purchasing.common.PurchaseFailureDescription;
import com.unity.purchasing.common.PurchaseFailureReason;
import com.unity.purchasing.common.StoreDeserializer;
import com.unity.purchasing.common.UnityPurchasing;
import com.unity.purchasing.googleplay.IabHelper.OnConsumeFinishedListener;
import com.unity.purchasing.googleplay.IabHelper.OnIabPurchaseFinishedListener;
import com.unity.purchasing.googleplay.IabHelper.OnIabSetupFinishedListener;
import com.unity.purchasing.googleplay.IabHelper.QueryInventoryFinishedListener;
import com.unity3d.ads.metadata.InAppPurchaseMetaData;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import net.hockeyapp.android.UpdateActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlayPurchasing extends StoreDeserializer {
    public static final int ACTIVITY_REQUEST_CODE = 999;
    protected static final String TAG = "UnityIAP";
    private static GooglePlayPurchasing instance;
    private static final boolean isDaydreamApiAvailable;
    public OnIabPurchaseFinishedListener PurchaseListener = new C27033();
    public boolean activityPending;
    private Context context;
    Features features = new Features();
    public IabHelper helper;
    private Inventory inventory;
    private boolean isUnityVrEnabled;
    private IActivityLauncher launcher;
    private IBillingServiceManager manager;
    private int offlineBackOffTime = DownloadManager.OPERATION_TIMEOUT;
    public String productJSON;
    private ProductDefinition productUnderPurchase;
    private volatile boolean purchaseInProgress = false;
    private BroadcastReceiver purchasesUpdatedReceiver = null;
    private volatile boolean subscriptionUpdateInProgress = false;
    private HashSet<String> suspectFailedConsumableSkus = new HashSet();
    private IStoreCallback unityPurchasing;

    /* renamed from: com.unity.purchasing.googleplay.GooglePlayPurchasing$3 */
    class C27033 implements OnIabPurchaseFinishedListener {
        C27033() {
        }

        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            if (GooglePlayPurchasing.this.purchaseInProgress) {
                GooglePlayPurchasing.log("onIabPurchaseFinished: %s", Boolean.toString(result.isSuccess()));
                GooglePlayPurchasing.log(result.mMessage);
                GooglePlayPurchasing.this.purchaseInProgress = false;
                if (result.isSuccess()) {
                    GooglePlayPurchasing.log("Product purchased successfully!");
                    GooglePlayPurchasing.this.NotifyUnityOfPurchase(info);
                    return;
                }
                int responseCode = result.getResponse();
                GooglePlayPurchasing.log("Purchase response code:%s", Integer.toString(responseCode));
                PurchaseFailureReason reason = PurchaseFailureReason.Unknown;
                GooglePlayPurchasing.this.suspectFailedConsumableSkus.add(GooglePlayPurchasing.this.productUnderPurchase.storeSpecificId);
                switch (responseCode) {
                    case IabHelper.IABHELPER_USER_CANCELLED /*-1005*/:
                    case 1:
                        reason = PurchaseFailureReason.UserCancelled;
                        break;
                    case 2:
                    case 3:
                        reason = PurchaseFailureReason.BillingUnavailable;
                        break;
                    case 4:
                        reason = PurchaseFailureReason.ItemUnavailable;
                        break;
                    case 7:
                        if (GooglePlayPurchasing.this.features.supportsPurchaseFailureReasonDuplicateTransaction) {
                            reason = PurchaseFailureReason.DuplicateTransaction;
                            break;
                        }
                        break;
                }
                PurchaseFailureDescription description = new PurchaseFailureDescription(GooglePlayPurchasing.this.productUnderPurchase.storeSpecificId, reason, "GOOGLEPLAY_" + result.mMessage, (String) IabHelper.billingResponseCodeNames.get(Integer.valueOf(responseCode)));
                if (result.getResponse() == IabHelper.IABHELPER_BAD_RESPONSE) {
                    GooglePlayPurchasing.log("Received bad response, polling for successful purchases to investigate failure more deeply");
                    GooglePlayPurchasing.this.reconcileFailedPurchaseWithInventory(description);
                    return;
                }
                GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(description);
            }
        }
    }

    /* renamed from: com.unity.purchasing.googleplay.GooglePlayPurchasing$5 */
    class C27055 extends BroadcastReceiver {
        C27055() {
        }

        public void onReceive(Context context, Intent intent) {
            GooglePlayPurchasing.this.pollForNewPurchases();
        }
    }

    class Features {
        public boolean supportsPurchaseFailureReasonDuplicateTransaction;

        Features() {
        }
    }

    static {
        boolean result = true;
        try {
            Class.forName("com.google.vr.ndk.base.DaydreamApi");
        } catch (Throwable th) {
            result = false;
        }
        isDaydreamApiAvailable = result;
    }

    public static GooglePlayPurchasing instance(IUnityCallback bridge) {
        if (instance == null) {
            BillingServiceManager serviceManager = new BillingServiceManager(UnityPlayer.currentActivity);
            instance = new GooglePlayPurchasing(new UnityPurchasing(bridge), new IabHelper(UnityPlayer.currentActivity, serviceManager, new ActivityLauncher()), serviceManager, UnityPlayer.currentActivity, new ActivityLauncher());
        }
        return instance;
    }

    public static boolean ContinuePurchase(Activity activity, String productID, String developerPayload) {
        if (instance == null) {
            return false;
        }
        instance.StartPurchase(activity, productID, developerPayload);
        return true;
    }

    public static boolean ContinueSubscriptionUpdate(Activity activity, String oldSkuMetadata, String newSku) {
        if (instance == null) {
            return false;
        }
        instance.StartSubscriptionUpdate(activity, oldSkuMetadata, newSku);
        return true;
    }

    public static void ProcessActivityResult(int requestCode, int resultCode, Intent data) {
        if (instance != null) {
            instance.onActivityResult(requestCode, resultCode, data);
        }
    }

    public GooglePlayPurchasing(IStoreCallback callback, IabHelper helper, IBillingServiceManager manager, Context context, IActivityLauncher launcher) {
        this.unityPurchasing = callback;
        this.helper = helper;
        this.helper.enableDaydreamApi(isDaydreamApiAvailable);
        this.manager = manager;
        this.context = context;
        this.launcher = launcher;
        instance = this;
        registerPurchasesUpdatedReceiver();
    }

    public void SetUnityVrEnabled(boolean vrEnabled) {
        this.isUnityVrEnabled = vrEnabled;
        log("isUnityVrEnabled = %s", String.valueOf(this.isUnityVrEnabled));
    }

    public void RestoreTransactions(final IGooglePlayStoreCallback callback) {
        this.helper.queryInventoryAsync(true, new ArrayList(), new QueryInventoryFinishedListener() {
            public void onQueryInventoryFinished(IabResult result, Inventory inv) throws Exception {
                GooglePlayPurchasing.log("RestoreInventoryFinished: %s", Boolean.toString(result.isSuccess()));
                GooglePlayPurchasing.log(result.mMessage);
                if (result.isFailure()) {
                    GooglePlayPurchasing.log("Failed to Restore inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    callback.OnTransactionsRestored(false);
                    return;
                }
                GooglePlayPurchasing.this.inventory = inv;
                GooglePlayPurchasing.this.NotifyUnityOfProducts(inv);
                callback.OnTransactionsRestored(true);
            }
        }, 0);
    }

    public void UpgradeDowngradeSubscription(String oldSkuMetadata, String newSku) {
        if (this.subscriptionUpdateInProgress) {
            log("Subscription update is in progress");
        } else if (!this.helper.subscriptionUpgradeDowngradeSupported()) {
            log("UpgradeDowngradeSubscription is not supported, this service needs v5 and higher android in app billing api");
        } else if (oldSkuMetadata == null || oldSkuMetadata.length() == 0 || newSku == null || newSku.length() == 0) {
            log("Cannot update subscription. Subscription product identifiers(SKUs) must not be empty");
        } else {
            boolean useVRIntent = (this.context instanceof UnityPlayerActivity) && this.isUnityVrEnabled && isDaydreamApiAvailable;
            final Intent subscriptionUpdateIntent = createPurchaseIntent(useVRIntent);
            subscriptionUpdateIntent.putExtra("oldSkuMetadata", oldSkuMetadata);
            subscriptionUpdateIntent.putExtra("newSku", newSku);
            subscriptionUpdateIntent.putExtra("type", "subscription_update");
            this.subscriptionUpdateInProgress = true;
            this.purchaseInProgress = true;
            this.activityPending = true;
            if (useVRIntent) {
                new Handler(this.context.getMainLooper()).post(new Runnable() {
                    public void run() {
                        subscriptionUpdateIntent.putExtra("vr", true);
                        DaydreamApi api = DaydreamApi.create(GooglePlayPurchasing.this.context);
                        api.launchInVr(subscriptionUpdateIntent);
                        api.close();
                    }
                });
            } else {
                this.launcher.startActivity(this.context, subscriptionUpdateIntent);
            }
        }
    }

    public void StartSubscriptionUpdate(Activity activity, String oldSkuMetadata, String newSku) {
        String oldSku = null;
        try {
            JSONObject metadata = new JSONObject(oldSkuMetadata);
            if (metadata.has("productId")) {
                oldSku = metadata.getString("productId");
            } else {
                oldSku = null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (oldSku == null) {
            log("Error: the product that is going to be updated does not have a valid product id");
        } else if (!this.inventory.hasDetails(oldSku)) {
            log("Error: the product that is going to be updated is not in the current inventory");
        } else if (!this.inventory.hasPurchase(oldSku)) {
            log("Error: the product that is going to be updated has not been purchased yet.");
        } else if (this.inventory.hasDetails(newSku)) {
            this.productUnderPurchase = new ProductDefinition(newSku, newSku, ProductType.Subscription);
            List<String> oldSkus = new ArrayList();
            oldSkus.add(oldSku);
            Activity activity2 = activity;
            String str = newSku;
            this.helper.launchSubscriptionUpdateFlow(activity2, str, oldSkus, ACTIVITY_REQUEST_CODE, this.PurchaseListener, addFreeTrialAndIntroPriceFlagToDeveloperPayload(null, newSku, oldSkuMetadata));
        } else {
            log("Error: the product that is going to be updated to is not in the current inventory");
        }
    }

    public void StartPurchase(Activity activity, String productId, String developerPayload) {
        this.helper.enableUnityVr(this.isUnityVrEnabled);
        developerPayload = addFreeTrialAndIntroPriceFlagToDeveloperPayload(developerPayload, productId, null);
        if (this.inventory.getSkuDetails(productId).mItemType == "inapp") {
            this.helper.launchPurchaseFlow(activity, productId, ACTIVITY_REQUEST_CODE, this.PurchaseListener, developerPayload);
            return;
        }
        this.helper.launchSubscriptionPurchaseFlow(activity, productId, ACTIVITY_REQUEST_CODE, this.PurchaseListener, developerPayload);
    }

    private String addFreeTrialAndIntroPriceFlagToDeveloperPayload(String developerPayload, String newSku, String oldSkuMetadata) {
        SkuDetails newSkuDetails = this.inventory.hasDetails(newSku) ? this.inventory.getSkuDetails(newSku) : null;
        JSONObject json = new JSONObject();
        String accountId = new String();
        if (developerPayload == null) {
            developerPayload = "";
        } else {
            try {
                accountId = new JSONObject(developerPayload).getString("accountId");
            } catch (JSONException e) {
            }
        }
        String developerPayloadBase64 = Base64.encodeToString(developerPayload.getBytes(), 0);
        if (newSkuDetails == null) {
            try {
                json.put("developerPayload", developerPayloadBase64);
                json.put("is_free_trial", false);
                json.put("has_introductory_price_trial", false);
                json.put("is_updated", false);
                json.put("update_subscription_metadata", null);
                json.put("accountId", accountId);
            } catch (JSONException e2) {
            }
            return json.toString();
        }
        boolean is_updated = false;
        String updateMetadata = null;
        if (oldSkuMetadata != null) {
            log("oldSkuMetadata is NOT null");
            is_updated = true;
            updateMetadata = getUpdateMetadata(oldSkuMetadata, newSkuDetails);
        } else {
            log("oldSkuMetadata is null");
        }
        boolean has_used_free_trial = false;
        String type = newSkuDetails.getType();
        String introductory_price_period = newSkuDetails.getIntroductoryPricePeriod();
        String free_trial_period = newSkuDetails.getFreeTrialPeriod();
        boolean has_purchase_history = this.inventory.hasPurchaseHistory(newSku);
        for (String sku : this.inventory.getAllSkus("subs")) {
            if (!this.inventory.getSkuDetails(sku).getFreeTrialPeriod().isEmpty() && this.inventory.hasPurchaseHistory(sku)) {
                has_used_free_trial = true;
                break;
            }
        }
        boolean is_free_trial = true;
        boolean has_introductory_price_trial = true;
        if (type.equals("inapp") || free_trial_period.isEmpty() || has_purchase_history || has_used_free_trial) {
            is_free_trial = false;
        }
        if (type.equals("inapp") || introductory_price_period.isEmpty() || has_purchase_history) {
            has_introductory_price_trial = false;
        }
        try {
            json.put("developerPayload", developerPayloadBase64);
            json.put("is_free_trial", is_free_trial);
            json.put("has_introductory_price_trial", has_introductory_price_trial);
            json.put("is_updated", is_updated);
            json.put("update_subscription_metadata", updateMetadata);
            json.put("accountId", accountId);
        } catch (JSONException e3) {
        }
        return json.toString();
    }

    private String getUpdateMetadata(String oldSkuMetadata, SkuDetails newSkuDetails) {
        String old_sku_product_id = null;
        boolean old_sku_is_free_trial = false;
        boolean old_sku_is_introductory_price = false;
        long new_sku_price_in_micros = newSkuDetails.getPriceInMicros();
        long old_sku_remaining_seconds = 0;
        try {
            JSONObject metadata = new JSONObject(oldSkuMetadata);
            old_sku_product_id = metadata.getString("productId");
            old_sku_is_free_trial = metadata.getBoolean("is_free_trial");
            old_sku_is_introductory_price = metadata.getBoolean("is_introductory_price_period");
            old_sku_remaining_seconds = (long) metadata.getDouble("remaining_time_in_seconds");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (old_sku_product_id == null || !this.inventory.hasDetails(old_sku_product_id)) {
            return null;
        }
        long old_sku_price_in_micros;
        String old_sku_period_string;
        SkuDetails oldSkuDetails = this.inventory.getSkuDetails(old_sku_product_id);
        if (old_sku_is_free_trial) {
            old_sku_remaining_seconds = 0;
        }
        if (!old_sku_is_introductory_price || newSkuDetails.getPriceInMicros() == 0) {
            old_sku_price_in_micros = oldSkuDetails.getPriceInMicros();
            old_sku_period_string = oldSkuDetails.getSubscriptionPeriod();
        } else {
            old_sku_price_in_micros = oldSkuDetails.getIntroductoryPriceInMicros();
            old_sku_period_string = oldSkuDetails.getIntroductoryPricePeriod();
        }
        JSONObject json = new JSONObject();
        try {
            json.put("old_sku_remaining_seconds", old_sku_remaining_seconds);
            json.put("old_sku_price_in_micros", old_sku_price_in_micros);
            json.put("old_sku_period_string", old_sku_period_string);
            json.put("new_sku_price_in_micros", new_sku_price_in_micros);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return json.toString();
    }

    private void pollForNewPurchases() {
        reconcileFailedPurchaseWithInventory(null);
    }

    private void reconcileFailedPurchaseWithInventory(final PurchaseFailureDescription suspectBadPurchase) {
        this.manager.workWith(new BillingServiceProcessor() {
            public void workWith(IInAppBillingService service) {
                Exception e;
                boolean hasPurchase = false;
                boolean notified = false;
                try {
                    boolean hadPurchase = suspectBadPurchase != null ? GooglePlayPurchasing.this.inventory.hasPurchase(suspectBadPurchase.productId) : false;
                    if (GooglePlayPurchasing.this.helper.queryPurchases(GooglePlayPurchasing.this.inventory, "inapp", service) != 0) {
                        GooglePlayPurchasing.log("Received bad response from queryPurchases");
                    }
                    if (suspectBadPurchase != null) {
                        hasPurchase = GooglePlayPurchasing.this.inventory.hasPurchase(suspectBadPurchase.productId);
                    }
                    if (suspectBadPurchase != null) {
                        if ((!hadPurchase && !hasPurchase) || (hadPurchase && hasPurchase)) {
                            GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(suspectBadPurchase);
                            notified = true;
                        } else if (!hadPurchase && hasPurchase) {
                            Purchase purchase = GooglePlayPurchasing.this.inventory.getPurchase(suspectBadPurchase.productId);
                            GooglePlayPurchasing.this.unityPurchasing.OnPurchaseSucceeded(purchase.getSku(), GooglePlayPurchasing.this.encodeReceipt(purchase, GooglePlayPurchasing.this.inventory.getSkuDetails(suspectBadPurchase.productId)), purchase.getOrderIdOrPurchaseToken());
                            notified = true;
                        }
                    }
                    if (!notified) {
                        GooglePlayPurchasing.this.NotifyUnityOfProducts(GooglePlayPurchasing.this.inventory);
                    }
                } catch (JSONException e2) {
                    e = e2;
                    Log.e(GooglePlayPurchasing.TAG, "exception", e);
                    if (suspectBadPurchase != null && null == null) {
                        GooglePlayPurchasing.this.unityPurchasing.OnPurchaseFailed(suspectBadPurchase);
                    }
                } catch (RemoteException e3) {
                    e = e3;
                    Log.e(GooglePlayPurchasing.TAG, "exception", e);
                    if (suspectBadPurchase != null) {
                    }
                }
            }
        });
    }

    private void registerPurchasesUpdatedReceiver() {
        if (this.purchasesUpdatedReceiver == null) {
            this.purchasesUpdatedReceiver = new C27055();
            this.context.registerReceiver(this.purchasesUpdatedReceiver, new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED"));
        }
    }

    private void QueryInventory(final List<String> skus, long delay) {
        log("QueryInventory: %s", Integer.toString(skus.size()));
        this.helper.queryInventoryAsync(true, skus, new QueryInventoryFinishedListener() {
            public void onQueryInventoryFinished(IabResult result, Inventory inv) throws Exception {
                GooglePlayPurchasing.log("onQueryInventoryFinished: %s", Boolean.toString(result.isSuccess()));
                GooglePlayPurchasing.log(result.mMessage);
                if (result.isFailure()) {
                    GooglePlayPurchasing.log("Failed to Query inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    GooglePlayPurchasing.this.QueryInventory(skus, (long) GooglePlayPurchasing.this.offlineBackOffTime);
                    GooglePlayPurchasing.this.offlineBackOffTime = Math.min(300000, GooglePlayPurchasing.this.offlineBackOffTime * 2);
                    return;
                }
                GooglePlayPurchasing.this.inventory = inv;
                for (String sku : skus) {
                    if (GooglePlayPurchasing.this.inventory.hasConsumablePurchaseHistory(sku) && !GooglePlayPurchasing.this.inventory.hasPurchase(sku)) {
                        GooglePlayPurchasing.this.helper.consumeAsync(GooglePlayPurchasing.this.inventory.getHistoryPurchase(sku), null, true);
                    }
                }
                GooglePlayPurchasing.this.NotifyUnityOfProducts(inv);
            }
        }, delay);
    }

    private void NotifyUnityOfProducts(Inventory inv) {
        List<ProductDescription> products = new ArrayList();
        JSONObject products_json = new JSONObject();
        for (Entry<String, SkuDetails> entry : inv.mSkuMap.entrySet()) {
            SkuDetails details = (SkuDetails) entry.getValue();
            try {
                products_json.put((String) entry.getKey(), details.getOriginalJSON());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ProductMetadata metadata = new ProductMetadata(details.getPrice(), details.getTitle(), details.getDescription(), details.getISOCurrencyCode(), new BigDecimal(details.getPriceInMicros()).divide(new BigDecimal(1000000)));
            String receipt = null;
            String transactionId = null;
            String key = (String) entry.getKey();
            if (inv.hasPurchase(key)) {
                Purchase purchase = inv.getPurchase(key);
                receipt = encodeReceipt(purchase, inv.getSkuDetails(key));
                transactionId = purchase.getOrderIdOrPurchaseToken();
            }
            products.add(new ProductDescription(key, metadata, receipt, transactionId));
        }
        this.productJSON = products_json.toString();
        this.unityPurchasing.OnProductsRetrieved(products);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.helper != null) {
            log("onActivityResult");
            this.helper.handleActivityResult(requestCode, resultCode, data);
            this.purchaseInProgress = false;
            this.subscriptionUpdateInProgress = false;
        }
    }

    private Purchase findPurchaseByOrderId(String orderId) {
        for (Purchase p : this.inventory.getAllPurchases()) {
            if (p.getOrderIdOrPurchaseToken().equals(orderId)) {
                return p;
            }
        }
        log("No consumable with order %s", orderId);
        return null;
    }

    private void NotifyUnityOfPurchase(Purchase purchase) {
        log("NotifyUnityOfPurchase");
        this.inventory.addPurchase(purchase);
        SkuDetails skuDetails = this.inventory.getSkuDetails(purchase.getSku());
        if (purchase.getItemType().equals("subs")) {
            this.inventory.addPurchaseToSubscriptionPurchaseHistory(purchase.getSku());
        }
        this.unityPurchasing.OnPurchaseSucceeded(purchase.getSku(), encodeReceipt(purchase, skuDetails), purchase.getOrderIdOrPurchaseToken());
    }

    private String encodeReceipt(Purchase purchase, SkuDetails skuDetails) {
        JSONObject signature = new JSONObject();
        try {
            signature.put(UpdateActivity.EXTRA_JSON, purchase.getOriginalJson());
            signature.put(InAppPurchaseMetaData.KEY_SIGNATURE, purchase.getSignature());
            signature.put("skuDetails", skuDetails.getOriginalJSON());
            signature.put("isPurchaseHistorySupported", this.helper.subscriptionPurchaseHistorySupported());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return signature.toString();
    }

    private static void log(String message) {
        Log.i(TAG, message);
    }

    private static void log(String message, String arg) {
        log(String.format(message, new Object[]{arg}));
    }

    public void RetrieveProducts(List<ProductDefinition> products) {
        final List<String> skus = new ArrayList();
        for (ProductDefinition product : products) {
            skus.add(product.storeSpecificId);
        }
        OnIabSetupFinishedListener listener = new OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                GooglePlayPurchasing.log("onIabSetupFinished: %s", Integer.toString(result.mResponse));
                if (result.isFailure()) {
                    GooglePlayPurchasing.log("Failed to setup IAB. Notifying Unity...");
                    GooglePlayPurchasing.this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
                    return;
                }
                GooglePlayPurchasing.log("Requesting %s products", Integer.toString(skus.size()));
                GooglePlayPurchasing.this.QueryInventory(skus, 0);
            }
        };
        if (this.helper.mSetupDone) {
            log("Requesting %s products", Integer.toString(skus.size()));
            QueryInventory(skus, 0);
            return;
        }
        try {
            this.manager.initialise();
            this.helper.startSetup(listener);
        } catch (GooglePlayBillingUnAvailableException e) {
            this.unityPurchasing.OnSetupFailed(InitializationFailureReason.PurchasingUnavailable);
        }
    }

    private void consumeSuspectFailedPurchase(ProductDefinition product, String developerPayload) {
        final String sku = product.storeSpecificId;
        final ProductDefinition p = product;
        final String payload = developerPayload;
        this.suspectFailedConsumableSkus.remove(sku);
        this.helper.queryInventoryAsync(false, this.inventory.getAllSkus("inapp"), new QueryInventoryFinishedListener() {

            /* renamed from: com.unity.purchasing.googleplay.GooglePlayPurchasing$8$1 */
            class C27081 implements OnConsumeFinishedListener {
                C27081() {
                }

                public void onConsumeFinished(Purchase purchase, IabResult result) throws JSONException {
                    GooglePlayPurchasing.this.Purchase(p, payload);
                }
            }

            public void onQueryInventoryFinished(IabResult result, Inventory inv) throws Exception {
                if (result.isFailure()) {
                    GooglePlayPurchasing.log("Failed to Query inventory. UnityIAP will automatically retry in " + GooglePlayPurchasing.this.offlineBackOffTime + "ms");
                    GooglePlayPurchasing.this.QueryInventory(GooglePlayPurchasing.this.inventory.getAllSkus("inapp"), (long) GooglePlayPurchasing.this.offlineBackOffTime);
                    GooglePlayPurchasing.this.offlineBackOffTime = Math.min(300000, GooglePlayPurchasing.this.offlineBackOffTime * 2);
                    return;
                }
                GooglePlayPurchasing.this.inventory = inv;
                if (GooglePlayPurchasing.this.inventory.hasConsumablePurchaseHistory(sku)) {
                    GooglePlayPurchasing.this.helper.consumeAsync(GooglePlayPurchasing.this.inventory.getHistoryPurchase(sku), new C27081(), true);
                    return;
                }
                GooglePlayPurchasing.this.Purchase(p, payload);
            }
        }, 0);
    }

    public void Purchase(ProductDefinition product) {
        Purchase(product, null);
    }

    public void Purchase(ProductDefinition product, String developerPayload) {
        if (this.purchaseInProgress) {
            this.unityPurchasing.OnPurchaseFailed(new PurchaseFailureDescription(product.storeSpecificId, PurchaseFailureReason.ExistingPurchasePending));
        } else if (product.type == ProductType.Consumable && this.suspectFailedConsumableSkus.contains(product.storeSpecificId) && !this.inventory.hasPurchase(product.storeSpecificId)) {
            consumeSuspectFailedPurchase(product, developerPayload);
        } else {
            String productId = product.storeSpecificId;
            this.productUnderPurchase = product;
            log("onPurchaseProduct: %s", productId);
            SkuDetails details = this.inventory.getSkuDetails(productId);
            log("ITEM TYPE:%s", details.getType());
            boolean useVRIntent = (this.context instanceof UnityPlayerActivity) && this.isUnityVrEnabled && isDaydreamApiAvailable;
            final Intent purchaseIntent = createPurchaseIntent(useVRIntent);
            purchaseIntent.putExtra("productId", productId);
            purchaseIntent.putExtra("itemType", details.getType());
            purchaseIntent.putExtra("developerPayload", developerPayload);
            this.purchaseInProgress = true;
            this.activityPending = true;
            if (useVRIntent) {
                new Handler(this.context.getMainLooper()).post(new Runnable() {
                    public void run() {
                        purchaseIntent.putExtra("vr", true);
                        DaydreamApi api = DaydreamApi.create(GooglePlayPurchasing.this.context);
                        api.launchInVr(purchaseIntent);
                        api.close();
                    }
                });
            } else {
                this.launcher.startActivity(this.context, purchaseIntent);
            }
        }
    }

    protected Intent createPurchaseIntent(boolean useVRIntent) {
        return new Intent(this.context, useVRIntent ? VRPurchaseActivity.class : PurchaseActivity.class);
    }

    public void FinishTransaction(ProductDefinition product, String transactionId) {
        log("Finish transaction:%s", transactionId);
        if (product == null) {
            log("Received FinishTransaction for unknown product with transaction %s. Not consuming.", transactionId);
        } else if (product.type == ProductType.Consumable) {
            Purchase purchase = findPurchaseByOrderId(transactionId);
            if (purchase != null) {
                log("Consuming %s", purchase.getSku());
                this.inventory.erasePurchase(purchase.getSku());
                this.helper.consumeAsync(purchase, new OnConsumeFinishedListener() {
                    public void onConsumeFinished(Purchase purchase, IabResult result) throws JSONException {
                        GooglePlayPurchasing.log("onConsumeFinished:%s", Boolean.toString(result.isSuccess()));
                        GooglePlayPurchasing.log(result.mMessage);
                        GooglePlayPurchasing.log(String.valueOf(result.getResponse()));
                    }
                }, false);
            }
        }
    }

    public void FinishAdditionalTransaction(String product, String transactionId) {
        log("Finish transaction:%s", transactionId);
        Purchase purchase = findPurchaseByOrderId(transactionId);
        if (purchase != null) {
            log("Consuming %s", purchase.getSku());
            this.inventory.erasePurchase(purchase.getSku());
            this.helper.consumeAsync(purchase, new OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase, IabResult result) throws JSONException {
                    GooglePlayPurchasing.log("onConsumeFinished:%s", Boolean.toString(result.isSuccess()));
                    GooglePlayPurchasing.log(result.mMessage);
                    GooglePlayPurchasing.log(String.valueOf(result.getResponse()));
                }
            }, false);
        }
    }

    public void SetFeatures(String featuresCSV) {
        for (String part : featuresCSV.split(",")) {
            if (part.equals("supportsPurchaseFailureReasonDuplicateTransaction")) {
                this.features.supportsPurchaseFailureReasonDuplicateTransaction = true;
            }
        }
    }
}
