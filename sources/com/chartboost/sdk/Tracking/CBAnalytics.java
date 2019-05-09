package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import android.util.Base64;
import com.chartboost.sdk.C1409h;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.ap;
import com.unity3d.services.purchasing.core.TransactionDetailsUtilities;
import java.util.EnumMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class CBAnalytics {

    public enum CBIAPPurchaseInfo {
        PRODUCT_ID,
        PRODUCT_TITLE,
        PRODUCT_DESCRIPTION,
        PRODUCT_PRICE,
        PRODUCT_CURRENCY_CODE,
        GOOGLE_PURCHASE_DATA,
        GOOGLE_PURCHASE_SIGNATURE,
        AMAZON_PURCHASE_TOKEN,
        AMAZON_USER_ID
    }

    public enum CBIAPType {
        GOOGLE_PLAY,
        AMAZON
    }

    public enum CBLevelType {
        HIGHEST_LEVEL_REACHED(1),
        CURRENT_AREA(2),
        CHARACTER_LEVEL(3),
        OTHER_SEQUENTIAL(4),
        OTHER_NONSEQUENTIAL(5);
        
        /* renamed from: a */
        private final int f2822a;

        private CBLevelType(int value) {
            this.f2822a = value;
        }

        public int getLevelType() {
            return this.f2822a;
        }
    }

    private CBAnalytics() {
    }

    public synchronized void trackInAppPurchaseEvent(EnumMap<CBIAPPurchaseInfo, String> map, CBIAPType iapType) {
        if (!(map == null || iapType == null)) {
            if (!(TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_ID)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_TITLE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE)))) {
                m3205a((String) map.get(CBIAPPurchaseInfo.PRODUCT_ID), (String) map.get(CBIAPPurchaseInfo.PRODUCT_TITLE), (String) map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION), (String) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE), (String) map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE), (String) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA), (String) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE), (String) map.get(CBIAPPurchaseInfo.AMAZON_USER_ID), (String) map.get(CBIAPPurchaseInfo.AMAZON_PURCHASE_TOKEN), iapType);
            }
        }
        CBLogging.m3099b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
    }

    /* renamed from: a */
    private static synchronized void m3205a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, CBIAPType cBIAPType) {
        synchronized (CBAnalytics.class) {
            if (C1410i.f2936m == null) {
                CBLogging.m3099b("CBPostInstallTracker", "You need call Chartboost.init() before calling any public API's");
            } else {
                C1409h a = C1409h.m3324a();
                if (a == null) {
                    CBLogging.m3099b("CBPostInstallTracker", "You need call Chartboost.startWithAppId() before tracking in-app purchases");
                } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
                    CBLogging.m3099b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
                } else {
                    try {
                        Matcher matcher = Pattern.compile("(\\d+\\.\\d+)|(\\d+)").matcher(str4);
                        matcher.find();
                        Object group = matcher.group();
                        if (TextUtils.isEmpty(group)) {
                            CBLogging.m3099b("CBPostInstallTracker", "Invalid price object");
                        } else {
                            float parseFloat = Float.parseFloat(group);
                            JSONObject jSONObject = null;
                            if (cBIAPType == CBIAPType.GOOGLE_PLAY) {
                                if (TextUtils.isEmpty(str6) || TextUtils.isEmpty(str7)) {
                                    CBLogging.m3099b("CBPostInstallTracker", "Null object is passed for for purchase data or purchase signature");
                                } else {
                                    jSONObject = C1377e.m3130a(C1377e.m3128a("purchaseData", (Object) str6), C1377e.m3128a("purchaseSignature", (Object) str7), C1377e.m3128a("type", Integer.valueOf(CBIAPType.GOOGLE_PLAY.ordinal())));
                                }
                            } else if (cBIAPType == CBIAPType.AMAZON) {
                                if (TextUtils.isEmpty(str8) || TextUtils.isEmpty(str9)) {
                                    CBLogging.m3099b("CBPostInstallTracker", "Null object is passed for for amazon user id or amazon purchase token");
                                } else {
                                    jSONObject = C1377e.m3130a(C1377e.m3128a("userID", (Object) str8), C1377e.m3128a("purchaseToken", (Object) str9), C1377e.m3128a("type", Integer.valueOf(CBIAPType.AMAZON.ordinal())));
                                }
                            }
                            if (jSONObject == null) {
                                CBLogging.m3099b("CBPostInstallTracker", "Error while parsing the receipt to a JSON Object, ");
                            } else {
                                group = Base64.encodeToString(jSONObject.toString().getBytes(), 2);
                                m3204a(a.f2909h, a.f2911j, a.f2916o, C1377e.m3130a(C1377e.m3128a("localized-title", (Object) str2), C1377e.m3128a("localized-description", (Object) str3), C1377e.m3128a("price", Float.valueOf(parseFloat)), C1377e.m3128a("currency", (Object) str5), C1377e.m3128a("productID", (Object) str), C1377e.m3128a(TransactionDetailsUtilities.RECEIPT, group)), "iap", cBIAPType);
                            }
                        }
                    } catch (IllegalStateException e) {
                        CBLogging.m3099b("CBPostInstallTracker", "Invalid price object");
                    }
                }
            }
        }
    }

    public static synchronized void trackInAppGooglePlayPurchaseEvent(String title, String description, String price, String currency, String productID, String purchaseData, String purchaseSignature) {
        synchronized (CBAnalytics.class) {
            m3205a(productID, title, description, price, currency, purchaseData, purchaseSignature, null, null, CBIAPType.GOOGLE_PLAY);
        }
    }

    public static synchronized void trackInAppAmazonStorePurchaseEvent(String title, String description, String price, String currency, String productID, String userID, String purchaseToken) {
        synchronized (CBAnalytics.class) {
            m3205a(productID, title, description, price, currency, null, null, userID, purchaseToken, CBIAPType.AMAZON);
        }
    }

    public static synchronized void trackLevelInfo(String eventLabel, CBLevelType type, int mainLevel, String description) {
        synchronized (CBAnalytics.class) {
            trackLevelInfo(eventLabel, type, mainLevel, 0, description);
        }
    }

    public static synchronized void trackLevelInfo(String eventLabel, CBLevelType type, int mainLevel, int subLevel, String description) {
        synchronized (CBAnalytics.class) {
            C1409h a = C1409h.m3324a();
            if (a == null) {
                CBLogging.m3099b("CBPostInstallTracker", "trackLevelInfo: SDK is not initialized");
            } else if (TextUtils.isEmpty(eventLabel)) {
                CBLogging.m3099b("CBPostInstallTracker", "Invalid value: event label cannot be empty or null");
            } else if (type == null) {
                CBLogging.m3099b("CBPostInstallTracker", "Invalid value: level type cannot be empty or null, please choose from one of the CBLevelType enum values");
            } else if (mainLevel < 0 || subLevel < 0) {
                CBLogging.m3099b("CBPostInstallTracker", "Invalid value: Level number should be > 0");
            } else if (description.isEmpty()) {
                CBLogging.m3099b("CBPostInstallTracker", "Invalid value: description cannot be empty or null");
            } else {
                JSONObject a2 = C1377e.m3130a(C1377e.m3128a("event_label", (Object) eventLabel), C1377e.m3128a("event_field", Integer.valueOf(type.getLevelType())), C1377e.m3128a("main_level", Integer.valueOf(mainLevel)), C1377e.m3128a("sub_level", Integer.valueOf(subLevel)), C1377e.m3128a("description", (Object) description), C1377e.m3128a("timestamp", Long.valueOf(System.currentTimeMillis())), C1377e.m3128a("data_type", (Object) "level_info"));
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(a2);
                m3203a(a.f2909h, a.f2911j, a.f2916o, jSONArray, "tracking");
            }
        }
    }

    /* renamed from: a */
    private static synchronized void m3204a(ah ahVar, ap apVar, C1391a c1391a, JSONObject jSONObject, String str, CBIAPType cBIAPType) {
        synchronized (CBAnalytics.class) {
            ad ajVar = new aj(String.format(Locale.US, "%s%s", new Object[]{"/post-install-event/", str}), apVar, c1391a, 2, null);
            ajVar.m3386a(str, (Object) jSONObject);
            ajVar.f3010l = true;
            ajVar.m3388b(str);
            ahVar.m3371a(ajVar);
        }
    }

    /* renamed from: a */
    private static synchronized void m3203a(ah ahVar, ap apVar, C1391a c1391a, JSONArray jSONArray, String str) {
        synchronized (CBAnalytics.class) {
            ad ajVar = new aj("/post-install-event/".concat("tracking"), apVar, c1391a, 2, null);
            ajVar.m3386a("track_info", (Object) jSONArray);
            ajVar.f3010l = true;
            ajVar.m3388b(str);
            ahVar.m3371a(ajVar);
        }
    }
}
