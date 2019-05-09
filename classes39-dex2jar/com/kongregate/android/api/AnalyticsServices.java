// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

import com.kongregate.android.internal.util.j;
import java.util.Locale;
import android.app.Activity;
import com.kongregate.o.a.d;
import java.util.Set;
import java.util.Map;

public interface AnalyticsServices
{
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
    
    void addEvent(final String p0, final String p1, final String p2);
    
    void addEvent(final String p0, final Map<String, Object> p1);
    
    void addFilterType(final String p0);
    
    void finishPromoAward(final String p0);
    
    void finishPurchase(final IabResult p0, final String p1, final Map<String, Object> p2);
    
    void finishPurchase(final IabResult p0, final String p1, final Map<String, Object> p2, final String p3);
    
    void finishPurchase(final String p0, final String p1, final String p2);
    
    void finishPurchase(final String p0, final String p1, final String p2, final String p3);
    
    void gameUserUpdate(final String p0);
    
    void gameUserUpdate(final Map<String, String> p0);
    
    boolean getAutoBoolProperty(final String p0);
    
    double getAutoDoubleProperty(final String p0);
    
    int getAutoIntProperty(final String p0);
    
    long getAutoLongProperty(final String p0);
    
    String getAutoPropertiesJSON();
    
    String getAutoStringProperty(final String p0);
    
    String getAutoUTCProperty(final String p0);
    
    String getInstallReferrer();
    
    String getKongPropertiesJSON();
    
    Set<String> getResouceNames();
    
    boolean getResourceAsBool(final String p0, final String p1, final boolean p2);
    
    float getResourceAsFloat(final String p0, final String p1, final float p2);
    
    int getResourceAsInt(final String p0, final String p1, final int p2);
    
    String getResourceAsString(final String p0, final String p1, final String p2);
    
    String getResourceNamesAsJson();
    
    Map<String, Object> getResources();
    
    void setAutoEventListener(final AutoEventListener p0);
    
    void setCommonProperties(final String p0);
    
    void setCommonProperties(final Map<String, Object> p0);
    
    void setCommonPropertiesEvaluator(final CommonPropertiesEvaluator p0);
    
    void setDeltaCustomButtonListener(final d.b p0);
    
    void setDeltaCustomParamListener(final d.c p0);
    
    @Deprecated
    void setFilterType(final String p0);
    
    void setSwrveCustomButtonListener(final d.b p0);
    
    void start(final Activity p0);
    
    void startPurchase(final String p0, final String p1);
    
    void startPurchase(final String p0, final Map<String, Object> p1);
    
    @Deprecated
    void trackPurchase(final String p0);
    
    @Deprecated
    void trackPurchase(final String p0, final String p1);
    
    @Deprecated
    void trackPurchase(final String p0, final Map<String, Object> p1);
    
    public interface AutoEventListener
    {
        void onAutoEvent(final String p0, final Map<String, Object> p1);
    }
    
    public enum Events
    {
        PLAYER_INFO;
        
        public static Events fromEventName(final String s) {
            try {
                return valueOf(s.toUpperCase(Locale.US));
            }
            catch (IllegalArgumentException ex) {
                return null;
            }
        }
        
        public String eventName() {
            return this.toString().toLowerCase(Locale.US);
        }
    }
    
    public enum Fields
    {
        AD_TRACKING, 
        BUNDLE_ID, 
        CLIENT_OS_TYPE, 
        CLIENT_OS_VERSION, 
        CLIENT_VERSION, 
        COUNTRY_CODE, 
        DATA_CONNECTION_TYPE, 
        DAYS_RETAINED, 
        DEVICE_EVENT_ID, 
        DEVICE_TYPE, 
        DEV_CLIENT_VERSION, 
        EMAIL, 
        EVENT_TIME, 
        EVENT_TIME_OFFSET, 
        EXTERNAL_IP_ADDRESS, 
        FB_EMAIL, 
        FB_USERNAME, 
        FB_USER_ID, 
        FILTER_TYPE, 
        FIRST_CLIENT_VERSION, 
        FIRST_PLAY_TIME, 
        FIRST_PLAY_TIME_OFFSET, 
        FIRST_SDK_VERSION, 
        FIRST_SERVER_VERSION, 
        GAMECENTER_ALIAS, 
        GAMECENTER_ID, 
        GOOGLE_AD_ID, 
        GOOGLE_PLAY_SERVICES_ALIAS, 
        GOOGLE_PLAY_SERVICES_ID, 
        IDFA, 
        IP_ADDRESS, 
        IS_FROM_BACKGROUND, 
        KONG_JOIN_DATE, 
        KONG_PLUS, 
        KONG_USERNAME, 
        KONG_USER_ID, 
        LANG_CODE, 
        NUM_PURCHASES, 
        NUM_SESSIONS, 
        PKG_SRC, 
        PLATFORM, 
        PLAYER_ID, 
        PRIVACY_POLICY_ACCEPTED_AT, 
        PRIVACY_POLICY_VERSION, 
        PUR_LINK_DATE, 
        PUR_TIER, 
        PUSH_NOTIFICATIONS, 
        SDK_VERSION, 
        SESSION_ID, 
        SITE_VISITOR_ID, 
        TIME_SKEW, 
        TOTAL_SPENT_IN_USD, 
        TWITTER_ID, 
        USD_SPENT_ON_KREDS;
        
        public static Fields fromFieldName(final String s) {
            try {
                return valueOf(s.toUpperCase(Locale.US));
            }
            catch (IllegalArgumentException ex) {
                j.c("Unrecognized field name: " + s);
                return null;
            }
        }
        
        public String fieldName() {
            return this.toString().toLowerCase(Locale.US);
        }
    }
    
    public enum IabResult
    {
        FAIL, 
        RECEIPT_FAIL, 
        SUCCESS;
    }
}
