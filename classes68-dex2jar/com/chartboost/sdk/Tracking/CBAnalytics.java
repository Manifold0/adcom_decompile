// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Tracking;

import java.util.regex.Matcher;
import java.util.EnumMap;
import android.util.Base64;
import com.chartboost.sdk.Libraries.e;
import java.util.regex.Pattern;
import android.text.TextUtils;
import com.chartboost.sdk.h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.i;
import java.util.Locale;
import org.json.JSONObject;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.aj;
import org.json.JSONArray;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.ah;

public class CBAnalytics
{
    private CBAnalytics() {
    }
    
    private static void a(final ah ah, final ap ap, final a a, final JSONArray jsonArray, final String s) {
        synchronized (CBAnalytics.class) {
            final aj aj = new aj("/post-install-event/".concat("tracking"), ap, a, 2, null);
            aj.a("track_info", jsonArray);
            aj.l = true;
            aj.b(s);
            ah.a((ad<Object>)aj);
        }
    }
    
    private static void a(final ah ah, final ap ap, final a a, final JSONObject jsonObject, final String s, final CBIAPType cbiapType) {
        synchronized (CBAnalytics.class) {
            final aj aj = new aj(String.format(Locale.US, "%s%s", "/post-install-event/", s), ap, a, 2, null);
            aj.a(s, jsonObject);
            aj.l = true;
            aj.b(s);
            ah.a((ad<Object>)aj);
        }
    }
    
    private static void a(final String s, final String s2, final String s3, String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final CBIAPType cbiapType) {
        while (true) {
            h a = null;
            Label_0046: {
                synchronized (CBAnalytics.class) {
                    if (i.m == null) {
                        CBLogging.b("CBPostInstallTracker", "You need call Chartboost.init() before calling any public API's");
                    }
                    else {
                        a = h.a();
                        if (a != null) {
                            break Label_0046;
                        }
                        CBLogging.b("CBPostInstallTracker", "You need call Chartboost.startWithAppId() before tracking in-app purchases");
                    }
                    return;
                }
            }
            final CharSequence charSequence;
            if (TextUtils.isEmpty(charSequence) || TextUtils.isEmpty((CharSequence)s2) || TextUtils.isEmpty((CharSequence)s3) || TextUtils.isEmpty((CharSequence)s4) || TextUtils.isEmpty((CharSequence)s5)) {
                CBLogging.b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
                return;
            }
            Label_0140: {
                try {
                    s4 = (String)Pattern.compile("(\\d+\\.\\d+)|(\\d+)").matcher(s4);
                    ((Matcher)s4).find();
                    s4 = ((Matcher)s4).group();
                    if (TextUtils.isEmpty((CharSequence)s4)) {
                        CBLogging.b("CBPostInstallTracker", "Invalid price object");
                        return;
                    }
                    break Label_0140;
                }
                catch (IllegalStateException ex) {
                    CBLogging.b("CBPostInstallTracker", "Invalid price object");
                    return;
                }
                return;
            }
            final float float1 = Float.parseFloat(s4);
            s4 = null;
            if (cbiapType == CBIAPType.GOOGLE_PLAY) {
                if (TextUtils.isEmpty((CharSequence)s6) || TextUtils.isEmpty((CharSequence)s7)) {
                    CBLogging.b("CBPostInstallTracker", "Null object is passed for for purchase data or purchase signature");
                    return;
                }
                s4 = (String)e.a(e.a("purchaseData", s6), e.a("purchaseSignature", s7), e.a("type", CBIAPType.GOOGLE_PLAY.ordinal()));
            }
            else if (cbiapType == CBIAPType.AMAZON) {
                if (TextUtils.isEmpty((CharSequence)s8) || TextUtils.isEmpty((CharSequence)s9)) {
                    CBLogging.b("CBPostInstallTracker", "Null object is passed for for amazon user id or amazon purchase token");
                    return;
                }
                s4 = (String)e.a(e.a("userID", s8), e.a("purchaseToken", s9), e.a("type", CBIAPType.AMAZON.ordinal()));
            }
            if (s4 == null) {
                CBLogging.b("CBPostInstallTracker", "Error while parsing the receipt to a JSON Object, ");
                return;
            }
            s4 = Base64.encodeToString(((JSONObject)s4).toString().getBytes(), 2);
            a(a.h, a.j, a.o, e.a(e.a("localized-title", s2), e.a("localized-description", s3), e.a("price", float1), e.a("currency", s5), e.a("productID", charSequence), e.a("receipt", s4)), "iap", cbiapType);
        }
    }
    
    public static void trackInAppAmazonStorePurchaseEvent(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        synchronized (CBAnalytics.class) {
            a(s5, s, s2, s3, s4, null, null, s6, s7, CBIAPType.AMAZON);
        }
    }
    
    public static void trackInAppGooglePlayPurchaseEvent(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        synchronized (CBAnalytics.class) {
            a(s5, s, s2, s3, s4, s6, s7, null, null, CBIAPType.GOOGLE_PLAY);
        }
    }
    
    public static void trackLevelInfo(final String s, CBLevelType cbLevelType, final int n, final int n2, final String s2) {
        while (true) {
            final h a;
            Label_0047: {
                synchronized (CBAnalytics.class) {
                    a = h.a();
                    if (a == null) {
                        CBLogging.b("CBPostInstallTracker", "trackLevelInfo: SDK is not initialized");
                    }
                    else {
                        if (!TextUtils.isEmpty((CharSequence)s)) {
                            break Label_0047;
                        }
                        CBLogging.b("CBPostInstallTracker", "Invalid value: event label cannot be empty or null");
                    }
                    return;
                }
            }
            if (cbLevelType == null) {
                CBLogging.b("CBPostInstallTracker", "Invalid value: level type cannot be empty or null, please choose from one of the CBLevelType enum values");
                return;
            }
            if (n < 0 || n2 < 0) {
                CBLogging.b("CBPostInstallTracker", "Invalid value: Level number should be > 0");
                return;
            }
            if (s2.isEmpty()) {
                CBLogging.b("CBPostInstallTracker", "Invalid value: description cannot be empty or null");
                return;
            }
            final Throwable t;
            final JSONObject a2 = e.a(e.a("event_label", t), e.a("event_field", cbLevelType.getLevelType()), e.a("main_level", n), e.a("sub_level", n2), e.a("description", s2), e.a("timestamp", System.currentTimeMillis()), e.a("data_type", "level_info"));
            cbLevelType = (CBLevelType)new JSONArray();
            ((JSONArray)cbLevelType).put((Object)a2);
            a(a.h, a.j, a.o, (JSONArray)cbLevelType, "tracking");
        }
    }
    
    public static void trackLevelInfo(final String s, final CBLevelType cbLevelType, final int n, final String s2) {
        synchronized (CBAnalytics.class) {
            trackLevelInfo(s, cbLevelType, n, 0, s2);
        }
    }
    
    public void trackInAppPurchaseEvent(final EnumMap<CBIAPPurchaseInfo, String> enumMap, final CBIAPType cbiapType) {
        // monitorenter(this)
        Label_0090: {
            if (enumMap == null || cbiapType == null) {
                break Label_0090;
            }
            try {
                if (TextUtils.isEmpty((CharSequence)enumMap.get(CBIAPPurchaseInfo.PRODUCT_ID)) || TextUtils.isEmpty((CharSequence)enumMap.get(CBIAPPurchaseInfo.PRODUCT_TITLE)) || TextUtils.isEmpty((CharSequence)enumMap.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION)) || TextUtils.isEmpty((CharSequence)enumMap.get(CBIAPPurchaseInfo.PRODUCT_PRICE)) || TextUtils.isEmpty((CharSequence)enumMap.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE))) {
                    CBLogging.b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
                }
                else {
                    a(enumMap.get(CBIAPPurchaseInfo.PRODUCT_ID), enumMap.get(CBIAPPurchaseInfo.PRODUCT_TITLE), enumMap.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION), enumMap.get(CBIAPPurchaseInfo.PRODUCT_PRICE), enumMap.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE), enumMap.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA), enumMap.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE), enumMap.get(CBIAPPurchaseInfo.AMAZON_USER_ID), enumMap.get(CBIAPPurchaseInfo.AMAZON_PURCHASE_TOKEN), cbiapType);
                }
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public enum CBIAPPurchaseInfo
    {
        AMAZON_PURCHASE_TOKEN, 
        AMAZON_USER_ID, 
        GOOGLE_PURCHASE_DATA, 
        GOOGLE_PURCHASE_SIGNATURE, 
        PRODUCT_CURRENCY_CODE, 
        PRODUCT_DESCRIPTION, 
        PRODUCT_ID, 
        PRODUCT_PRICE, 
        PRODUCT_TITLE;
    }
    
    public enum CBIAPType
    {
        AMAZON, 
        GOOGLE_PLAY;
    }
    
    public enum CBLevelType
    {
        CHARACTER_LEVEL(3), 
        CURRENT_AREA(2), 
        HIGHEST_LEVEL_REACHED(1), 
        OTHER_NONSEQUENTIAL(5), 
        OTHER_SEQUENTIAL(4);
        
        private final int a;
        
        private CBLevelType(final int a) {
            this.a = a;
        }
        
        public int getLevelType() {
            return this.a;
        }
    }
}
