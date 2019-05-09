package com.unity3d.services.analytics;

import android.support.v4.app.NotificationCompat;
import com.applovin.sdk.AppLovinEventParameters;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class UnityAnalytics {
    protected static JSONArray eventQueue = new JSONArray();

    private enum AnalyticsEventType {
        POSTEVENT
    }

    private static JSONObject createItemAcquired(String transactionContext, Float amount, String itemId, Float balance, String itemType, String level, String transactionId, AcquisitionType acquisitionType) {
        HashMap<String, Object> customParams = new HashMap();
        customParams.put("currency_type", acquisitionType.toString());
        customParams.put("transaction_context", transactionContext);
        customParams.put("amount", amount);
        customParams.put("item_id", itemId);
        customParams.put("balance", balance);
        customParams.put("item_type", itemType);
        customParams.put("level", level);
        customParams.put(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER, transactionId);
        HashMap<String, Object> msg = new HashMap();
        msg.put("custom_params", customParams);
        msg.put("ts", Long.valueOf(1533594423477L));
        msg.put("name", "item_acquired");
        HashMap<String, Object> map = new HashMap();
        map.put("type", "analytics.custom.v1");
        map.put(NotificationCompat.CATEGORY_MESSAGE, msg);
        return new JSONObject(map);
    }

    private static JSONObject createItemSpent(String transactionContext, Float amount, String itemId, Float balance, String itemType, String level, String transactionId, AcquisitionType acquisitionType) {
        HashMap<String, Object> customParams = new HashMap();
        customParams.put("currency_type", acquisitionType.toString());
        customParams.put("transaction_context", transactionContext);
        customParams.put("amount", amount);
        customParams.put("item_id", itemId);
        customParams.put("balance", balance);
        customParams.put("item_type", itemType);
        customParams.put("level", level);
        customParams.put(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER, transactionId);
        HashMap<String, Object> msg = new HashMap();
        msg.put("custom_params", customParams);
        msg.put("ts", Long.valueOf(new Date().getTime()));
        msg.put("name", "item_spent");
        HashMap<String, Object> map = new HashMap();
        map.put("type", "analytics.custom.v1");
        map.put(NotificationCompat.CATEGORY_MESSAGE, msg);
        return new JSONObject(map);
    }

    private static JSONObject createLevelFail(Integer levelIndex) {
        HashMap<String, Object> customParams = new HashMap();
        customParams.put("level_index", levelIndex);
        HashMap<String, Object> msg = new HashMap();
        msg.put("custom_params", customParams);
        msg.put("ts", Long.valueOf(new Date().getTime()));
        msg.put("name", "level_fail");
        HashMap<String, Object> map = new HashMap();
        map.put("type", "analytics.custom.v1");
        map.put(NotificationCompat.CATEGORY_MESSAGE, msg);
        return new JSONObject(map);
    }

    private static JSONObject createLevelUp(Integer newLevelIndex) {
        HashMap<String, Object> customParams = new HashMap();
        customParams.put("new_level_index", newLevelIndex);
        HashMap<String, Object> msg = new HashMap();
        msg.put("custom_params", customParams);
        msg.put("ts", Long.valueOf(new Date().getTime()));
        msg.put("name", "level_up");
        HashMap<String, Object> map = new HashMap();
        map.put("type", "analytics.custom.v1");
        map.put(NotificationCompat.CATEGORY_MESSAGE, msg);
        return new JSONObject(map);
    }

    private static JSONObject createAdComplete(String network, String placementId, Boolean rewarded) {
        HashMap<String, Object> customParams = new HashMap();
        customParams.put("rewarded", rewarded);
        customParams.put("network", network);
        customParams.put("placement_id", placementId);
        HashMap<String, Object> msg = new HashMap();
        msg.put("custom_params", customParams);
        msg.put("ts", Long.valueOf(new Date().getTime()));
        msg.put("name", "ad_complete");
        HashMap<String, Object> map = new HashMap();
        map.put("type", "analytics.custom.v1");
        map.put(NotificationCompat.CATEGORY_MESSAGE, msg);
        return new JSONObject(map);
    }

    private static JSONObject createIapTransaction(String productId, Float amount, String currency, Boolean isPromo, String receipt) {
        HashMap<String, Object> msg = new HashMap();
        msg.put("ts", Long.valueOf(new Date().getTime()));
        msg.put("name", "ad_complete");
        msg.put("productid", productId);
        msg.put("amount", amount);
        msg.put("currency", currency);
        msg.put(NotificationCompat.CATEGORY_PROMO, isPromo);
        msg.put(TransactionDetailsUtilities.RECEIPT, receipt);
        HashMap<String, Object> map = new HashMap();
        map.put("type", "analytics.transaction.v1");
        map.put(NotificationCompat.CATEGORY_MESSAGE, msg);
        return new JSONObject(map);
    }

    public static void onItemAcquired(String transactionContext, Float amount, String itemId, Float balance, String itemType, String level, String transactionId, AcquisitionType acquisitionType) {
        postEvent(createItemAcquired(transactionContext, amount, itemId, balance, itemType, level, transactionId, acquisitionType));
    }

    public static void onItemSpent(String transactionContext, Float amount, String itemId, Float balance, String itemType, String level, String transactionId, AcquisitionType acquisitionType) {
        postEvent(createItemSpent(transactionContext, amount, itemId, balance, itemType, level, transactionId, acquisitionType));
    }

    public static void onLevelFail(Integer levelIndex) {
        postEvent(createLevelFail(levelIndex));
    }

    public static void onLevelUp(Integer newLevelIndex) {
        postEvent(createLevelUp(newLevelIndex));
    }

    public static void onAdComplete(String network, String placementId, Boolean rewarded) {
        postEvent(createAdComplete(network, placementId, rewarded));
    }

    public static void onIapTransaction(String productId, Float amount, String currency, Boolean isPromo, String receipt) {
        postEvent(createIapTransaction(productId, amount, currency, isPromo, receipt));
    }

    public static void onEvent(JSONObject jsonEvent) {
        postEvent(jsonEvent);
    }

    private static synchronized void postEvent(JSONObject event) {
        synchronized (UnityAnalytics.class) {
            if (eventQueue.length() < 200) {
                eventQueue.put(event);
            }
            WebViewApp currentApp = WebViewApp.getCurrentApp();
            if (currentApp != null) {
                if (Boolean.valueOf(currentApp.sendEvent(WebViewEventCategory.ANALYTICS, AnalyticsEventType.POSTEVENT, eventQueue.toString())).booleanValue()) {
                    eventQueue = new JSONArray();
                }
            }
        }
    }
}
