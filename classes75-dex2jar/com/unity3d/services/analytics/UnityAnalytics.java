// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.analytics;

import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONArray;

public class UnityAnalytics
{
    protected static JSONArray eventQueue;
    
    static {
        UnityAnalytics.eventQueue = new JSONArray();
    }
    
    private static JSONObject createAdComplete(final String s, final String s2, final Boolean b) {
        final HashMap hashMap = new HashMap<String, Boolean>();
        hashMap.put("rewarded", b);
        hashMap.put("network", (Boolean)s);
        hashMap.put("placement_id", (Boolean)s2);
        final HashMap<String, HashMap<String, Boolean>> hashMap2 = new HashMap<String, HashMap<String, Boolean>>();
        hashMap2.put("custom_params", (HashMap<String, Boolean>)hashMap);
        hashMap2.put("ts", (HashMap<String, Boolean>)new Date().getTime());
        hashMap2.put("name", (HashMap<String, Boolean>)"ad_complete");
        final HashMap<String, HashMap<String, HashMap<String, Boolean>>> hashMap3 = new HashMap<String, HashMap<String, HashMap<String, Boolean>>>();
        hashMap3.put("type", (HashMap<String, HashMap<String, Boolean>>)"analytics.custom.v1");
        hashMap3.put("msg", hashMap2);
        return new JSONObject((Map)hashMap3);
    }
    
    private static JSONObject createIapTransaction(final String s, final Float n, final String s2, final Boolean b, final String s3) {
        final HashMap<String, Long> hashMap = new HashMap<String, Long>();
        hashMap.put("ts", new Date().getTime());
        hashMap.put("name", (Long)"ad_complete");
        hashMap.put("productid", (Long)s);
        hashMap.put("amount", (Long)(Object)n);
        hashMap.put("currency", (Long)s2);
        hashMap.put("promo", (Long)(Object)b);
        hashMap.put("receipt", (Long)s3);
        final HashMap<String, HashMap<String, Long>> hashMap2 = new HashMap<String, HashMap<String, Long>>();
        hashMap2.put("type", (HashMap<String, Long>)"analytics.transaction.v1");
        hashMap2.put("msg", hashMap);
        return new JSONObject((Map)hashMap2);
    }
    
    private static JSONObject createItemAcquired(final String s, final Float n, final String s2, final Float n2, final String s3, final String s4, final String s5, final AcquisitionType acquisitionType) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("currency_type", acquisitionType.toString());
        hashMap.put("transaction_context", s);
        hashMap.put("amount", (String)n);
        hashMap.put("item_id", s2);
        hashMap.put("balance", (String)n2);
        hashMap.put("item_type", s3);
        hashMap.put("level", s4);
        hashMap.put("transaction_id", s5);
        final HashMap<String, HashMap<String, String>> hashMap2 = new HashMap<String, HashMap<String, String>>();
        hashMap2.put("custom_params", hashMap);
        hashMap2.put("ts", (HashMap<String, String>)1533594423477L);
        hashMap2.put("name", (HashMap<String, String>)"item_acquired");
        final HashMap<String, HashMap<String, HashMap<String, String>>> hashMap3 = new HashMap<String, HashMap<String, HashMap<String, String>>>();
        hashMap3.put("type", (HashMap<String, HashMap<String, String>>)"analytics.custom.v1");
        hashMap3.put("msg", hashMap2);
        return new JSONObject((Map)hashMap3);
    }
    
    private static JSONObject createItemSpent(final String s, final Float n, final String s2, final Float n2, final String s3, final String s4, final String s5, final AcquisitionType acquisitionType) {
        final HashMap hashMap = new HashMap<String, String>();
        hashMap.put("currency_type", acquisitionType.toString());
        hashMap.put("transaction_context", s);
        hashMap.put("amount", (String)n);
        hashMap.put("item_id", s2);
        hashMap.put("balance", (String)n2);
        hashMap.put("item_type", s3);
        hashMap.put("level", s4);
        hashMap.put("transaction_id", s5);
        final HashMap<String, HashMap<String, String>> hashMap2 = new HashMap<String, HashMap<String, String>>();
        hashMap2.put("custom_params", (HashMap<String, String>)hashMap);
        hashMap2.put("ts", (HashMap<String, String>)new Date().getTime());
        hashMap2.put("name", (HashMap<String, String>)"item_spent");
        final HashMap<String, HashMap<String, HashMap<String, String>>> hashMap3 = new HashMap<String, HashMap<String, HashMap<String, String>>>();
        hashMap3.put("type", (HashMap<String, HashMap<String, String>>)"analytics.custom.v1");
        hashMap3.put("msg", hashMap2);
        return new JSONObject((Map)hashMap3);
    }
    
    private static JSONObject createLevelFail(final Integer n) {
        final HashMap hashMap = new HashMap<String, Integer>();
        hashMap.put("level_index", n);
        final HashMap<String, HashMap<String, Integer>> hashMap2 = new HashMap<String, HashMap<String, Integer>>();
        hashMap2.put("custom_params", (HashMap<String, Integer>)hashMap);
        hashMap2.put("ts", (HashMap<String, Integer>)new Date().getTime());
        hashMap2.put("name", (HashMap<String, Integer>)"level_fail");
        final HashMap<String, HashMap<String, HashMap<String, Integer>>> hashMap3 = new HashMap<String, HashMap<String, HashMap<String, Integer>>>();
        hashMap3.put("type", (HashMap<String, HashMap<String, Integer>>)"analytics.custom.v1");
        hashMap3.put("msg", hashMap2);
        return new JSONObject((Map)hashMap3);
    }
    
    private static JSONObject createLevelUp(final Integer n) {
        final HashMap hashMap = new HashMap<String, Integer>();
        hashMap.put("new_level_index", n);
        final HashMap<String, HashMap<String, Integer>> hashMap2 = new HashMap<String, HashMap<String, Integer>>();
        hashMap2.put("custom_params", (HashMap<String, Integer>)hashMap);
        hashMap2.put("ts", (HashMap<String, Integer>)new Date().getTime());
        hashMap2.put("name", (HashMap<String, Integer>)"level_up");
        final HashMap<String, HashMap<String, HashMap<String, Integer>>> hashMap3 = new HashMap<String, HashMap<String, HashMap<String, Integer>>>();
        hashMap3.put("type", (HashMap<String, HashMap<String, Integer>>)"analytics.custom.v1");
        hashMap3.put("msg", hashMap2);
        return new JSONObject((Map)hashMap3);
    }
    
    public static void onAdComplete(final String s, final String s2, final Boolean b) {
        postEvent(createAdComplete(s, s2, b));
    }
    
    public static void onEvent(final JSONObject jsonObject) {
        postEvent(jsonObject);
    }
    
    public static void onIapTransaction(final String s, final Float n, final String s2, final Boolean b, final String s3) {
        postEvent(createIapTransaction(s, n, s2, b, s3));
    }
    
    public static void onItemAcquired(final String s, final Float n, final String s2, final Float n2, final String s3, final String s4, final String s5, final AcquisitionType acquisitionType) {
        postEvent(createItemAcquired(s, n, s2, n2, s3, s4, s5, acquisitionType));
    }
    
    public static void onItemSpent(final String s, final Float n, final String s2, final Float n2, final String s3, final String s4, final String s5, final AcquisitionType acquisitionType) {
        postEvent(createItemSpent(s, n, s2, n2, s3, s4, s5, acquisitionType));
    }
    
    public static void onLevelFail(final Integer n) {
        postEvent(createLevelFail(n));
    }
    
    public static void onLevelUp(final Integer n) {
        postEvent(createLevelUp(n));
    }
    
    private static void postEvent(final JSONObject jsonObject) {
        synchronized (UnityAnalytics.class) {
            if (UnityAnalytics.eventQueue.length() < 200) {
                UnityAnalytics.eventQueue.put((Object)jsonObject);
            }
            final WebViewApp currentApp = WebViewApp.getCurrentApp();
            if (currentApp != null && (boolean)currentApp.sendEvent(WebViewEventCategory.ANALYTICS, AnalyticsEventType.POSTEVENT, UnityAnalytics.eventQueue.toString())) {
                UnityAnalytics.eventQueue = new JSONArray();
            }
        }
    }
    
    private enum AnalyticsEventType
    {
        POSTEVENT;
    }
}
