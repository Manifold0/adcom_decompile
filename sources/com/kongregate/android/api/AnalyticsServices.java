package com.kongregate.android.api;

import android.app.Activity;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p003a.C0582d.C0532b;
import com.kongregate.p000o.p003a.C0582d.C0534c;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public interface AnalyticsServices {
    public static final String ADJUST_EVENT_PREFIX = "adjust.";
    public static final String DELTA_EVENT_PREFIX = "delta.";
    public static final String SWRVE_EVENT_PREFIX = "swrve.";
    public static final String SWRVE_VIRTUAL_ECONOMY_EVENT_GIFT = "swrve.ve.gift";
    public static final String SWRVE_VIRTUAL_ECONOMY_EVENT_PURCHASE = "swrve.ve.purchase";
    public static final String SWRVE_VIRTUAL_ECONOMY_PARAM_AMOUNT = "amount";
    public static final String SWRVE_VIRTUAL_ECONOMY_PARAM_COST = "cost";
    public static final String SWRVE_VIRTUAL_ECONOMY_PARAM_CURRENCY = "currency";
    public static final String SWRVE_VIRTUAL_ECONOMY_PARAM_ITEM = "item";
    public static final String SWRVE_VIRTUAL_ECONOMY_PARAM_QUANTITY = "quantity";

    public interface AutoEventListener {
        void onAutoEvent(String str, Map<String, Object> map);
    }

    public enum Events {
        PLAYER_INFO;

        public String eventName() {
            return toString().toLowerCase(Locale.US);
        }

        public static Events fromEventName(String str) {
            try {
                return valueOf(str.toUpperCase(Locale.US));
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

    public enum Fields {
        AD_TRACKING,
        BUNDLE_ID,
        CLIENT_OS_TYPE,
        CLIENT_OS_VERSION,
        CLIENT_VERSION,
        COUNTRY_CODE,
        DATA_CONNECTION_TYPE,
        DAYS_RETAINED,
        DEV_CLIENT_VERSION,
        DEVICE_EVENT_ID,
        DEVICE_TYPE,
        EVENT_TIME,
        EVENT_TIME_OFFSET,
        EXTERNAL_IP_ADDRESS,
        FILTER_TYPE,
        FIRST_CLIENT_VERSION,
        FIRST_PLAY_TIME,
        FIRST_PLAY_TIME_OFFSET,
        FIRST_SDK_VERSION,
        FIRST_SERVER_VERSION,
        GOOGLE_AD_ID,
        GOOGLE_PLAY_SERVICES_ID,
        GOOGLE_PLAY_SERVICES_ALIAS,
        PKG_SRC,
        PRIVACY_POLICY_VERSION,
        PRIVACY_POLICY_ACCEPTED_AT,
        PUR_TIER,
        IP_ADDRESS,
        IS_FROM_BACKGROUND,
        KONG_PLUS,
        KONG_USER_ID,
        KONG_USERNAME,
        LANG_CODE,
        NUM_PURCHASES,
        NUM_SESSIONS,
        PLAYER_ID,
        PUSH_NOTIFICATIONS,
        SITE_VISITOR_ID,
        SDK_VERSION,
        SESSION_ID,
        TIME_SKEW,
        IDFA,
        GAMECENTER_ALIAS,
        GAMECENTER_ID,
        TOTAL_SPENT_IN_USD,
        USD_SPENT_ON_KREDS,
        PLATFORM,
        EMAIL,
        FB_EMAIL,
        FB_USER_ID,
        FB_USERNAME,
        KONG_JOIN_DATE,
        PUR_LINK_DATE,
        TWITTER_ID;

        public String fieldName() {
            return toString().toLowerCase(Locale.US);
        }

        public static Fields fromFieldName(String str) {
            try {
                return valueOf(str.toUpperCase(Locale.US));
            } catch (IllegalArgumentException e) {
                C0562j.m759c("Unrecognized field name: " + str);
                return null;
            }
        }
    }

    public enum IabResult {
        SUCCESS,
        FAIL,
        RECEIPT_FAIL
    }

    void addEvent(String str, String str2, String str3);

    void addEvent(String str, Map<String, Object> map);

    void addFilterType(String str);

    void finishPromoAward(String str);

    void finishPurchase(IabResult iabResult, String str, Map<String, Object> map);

    void finishPurchase(IabResult iabResult, String str, Map<String, Object> map, String str2);

    void finishPurchase(String str, String str2, String str3);

    void finishPurchase(String str, String str2, String str3, String str4);

    void gameUserUpdate(String str);

    void gameUserUpdate(Map<String, String> map);

    boolean getAutoBoolProperty(String str);

    double getAutoDoubleProperty(String str);

    int getAutoIntProperty(String str);

    long getAutoLongProperty(String str);

    String getAutoPropertiesJSON();

    String getAutoStringProperty(String str);

    String getAutoUTCProperty(String str);

    String getInstallReferrer();

    String getKongPropertiesJSON();

    Set<String> getResouceNames();

    boolean getResourceAsBool(String str, String str2, boolean z);

    float getResourceAsFloat(String str, String str2, float f);

    int getResourceAsInt(String str, String str2, int i);

    String getResourceAsString(String str, String str2, String str3);

    String getResourceNamesAsJson();

    Map<String, Object> getResources();

    void setAutoEventListener(AutoEventListener autoEventListener);

    void setCommonProperties(String str);

    void setCommonProperties(Map<String, Object> map);

    void setCommonPropertiesEvaluator(CommonPropertiesEvaluator commonPropertiesEvaluator);

    void setDeltaCustomButtonListener(C0532b c0532b);

    void setDeltaCustomParamListener(C0534c c0534c);

    @Deprecated
    void setFilterType(String str);

    void setSwrveCustomButtonListener(C0532b c0532b);

    void start(Activity activity);

    void startPurchase(String str, String str2);

    void startPurchase(String str, Map<String, Object> map);

    @Deprecated
    void trackPurchase(String str);

    @Deprecated
    void trackPurchase(String str, String str2);

    @Deprecated
    void trackPurchase(String str, Map<String, Object> map);
}
