package com.ironsource.mediationsdk.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.ironsource.mediationsdk.AbstractSmash;
import com.ironsource.mediationsdk.BannerSmash;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.logger.ThreadExceptionHandler;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.json.JSONException;
import org.json.JSONObject;

public class IronSourceUtils {
    private static final String ADAPTER_VERSION_KEY = "providerAdapterVersion";
    private static final String DEFAULT_IS_EVENTS_FORMATTER_TYPE = "default_is_events_formatter_type";
    private static final String DEFAULT_IS_EVENTS_URL = "default_is_events_url";
    private static final String DEFAULT_IS_OPT_OUT_EVENTS = "default_is_opt_out_events";
    private static final String DEFAULT_RV_EVENTS_FORMATTER_TYPE = "default_rv_events_formatter_type";
    private static final String DEFAULT_RV_EVENTS_URL = "default_rv_events_url";
    private static final String DEFAULT_RV_OPT_OUT_EVENTS = "default_rv_opt_out_events";
    private static final String GENERAL_PROPERTIES = "general_properties";
    public static final String KEY = "C38FB23A402222A0C17D34A92F971D1F";
    private static final String LAST_RESPONSE = "last_response";
    private static final String NETWORK_INSTANCE_KEY = "networkInstance";
    private static final String PROVIDER_KEY = "provider";
    private static final String PROVIDER_PRIORITY = "providerPriority";
    private static final String SDK_VERSION = "6.8.0";
    private static final String SDK_VERSION_KEY = "providerSDKVersion";
    private static final String SHARED_PREFERENCES_NAME = "Mediation_Shared_Preferences";
    private static final String SUB_PROVIDER_ID_KEY = "spId";
    private static String mAbt = "";
    private static int serr = 1;

    private static void setSerr(int value) {
        serr = value;
    }

    public static int getSerr() {
        return serr;
    }

    static void setABT(String abt) {
        mAbt = abt;
    }

    public static String getAbt() {
        return mAbt;
    }

    public static String getMD5(String input) {
        try {
            String bigInteger = new BigInteger(1, MessageDigest.getInstance(Constants.MD5).digest(input.getBytes())).toString(16);
            while (bigInteger.length() < 32) {
                bigInteger = AppEventsConstants.EVENT_PARAM_VALUE_NO + bigInteger;
            }
            return bigInteger;
        } catch (Throwable e) {
            if (input == null) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "getMD5(input:null)", e);
            } else {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "getMD5(input:" + input + ")", e);
            }
            return "";
        }
    }

    private static String getSHA256(String input) {
        try {
            return String.format("%064x", new Object[]{new BigInteger(1, MessageDigest.getInstance(Constants.SHA256).digest(input.getBytes()))});
        } catch (NoSuchAlgorithmException e) {
            if (input == null) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "getSHA256(input:null)", e);
            } else {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "getSHA256(input:" + input + ")", e);
            }
            return "";
        }
    }

    public static String getTransId(String strToTransId) {
        return getSHA256(strToTransId);
    }

    public static int getCurrentTimestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static String getConnectionType(Context c) {
        String CONNECTION_WIFI = "WIFI";
        String CONNECTION_MOBILE = "MOBILE";
        String noneConnectionType = ParametersKeys.ORIENTATION_NONE;
        String wifiConnectionType = "wifi";
        String cellularConnectionType = "cellular";
        if (c == null) {
            return ParametersKeys.ORIENTATION_NONE;
        }
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService("connectivity");
        if (cm == null) {
            return ParametersKeys.ORIENTATION_NONE;
        }
        if (VERSION.SDK_INT >= 23) {
            NetworkCapabilities mNetworkCapabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
            if (mNetworkCapabilities == null) {
                return ParametersKeys.ORIENTATION_NONE;
            }
            if (mNetworkCapabilities.hasTransport(1)) {
                return "wifi";
            }
            if (mNetworkCapabilities.hasTransport(0)) {
                return "cellular";
            }
            return ParametersKeys.ORIENTATION_NONE;
        }
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isConnected()) {
            return ParametersKeys.ORIENTATION_NONE;
        }
        if (info.getTypeName().equalsIgnoreCase("WIFI")) {
            return "wifi";
        }
        if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
            return "cellular";
        }
        return ParametersKeys.ORIENTATION_NONE;
    }

    public static String getSDKVersion() {
        return "6.8.0";
    }

    public static void createAndStartWorker(Runnable runnable, String threadName) {
        Thread worker = new Thread(runnable, threadName);
        worker.setUncaughtExceptionHandler(new ThreadExceptionHandler());
        worker.start();
    }

    public static String getBase64Auth(String loginUsername, String loginPass) {
        return "Basic " + Base64.encodeToString((loginUsername + ":" + loginPass).getBytes(), 10);
    }

    private static String getDefaultEventsUrlByEventType(String eventType) {
        if (IronSourceConstants.INTERSTITIAL_EVENT_TYPE.equals(eventType)) {
            return DEFAULT_IS_EVENTS_URL;
        }
        if (IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE.equals(eventType)) {
            return DEFAULT_RV_EVENTS_URL;
        }
        return "";
    }

    private static String getDefaultOptOutEventsByEventType(String eventType) {
        if (IronSourceConstants.INTERSTITIAL_EVENT_TYPE.equals(eventType)) {
            return DEFAULT_IS_OPT_OUT_EVENTS;
        }
        if (IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE.equals(eventType)) {
            return DEFAULT_RV_OPT_OUT_EVENTS;
        }
        return "";
    }

    private static String getDefaultFormatterTypeByEventType(String eventType) {
        if (IronSourceConstants.INTERSTITIAL_EVENT_TYPE.equals(eventType)) {
            return DEFAULT_IS_EVENTS_FORMATTER_TYPE;
        }
        if (IronSourceConstants.REWARDED_VIDEO_EVENT_TYPE.equals(eventType)) {
            return DEFAULT_RV_EVENTS_FORMATTER_TYPE;
        }
        return "";
    }

    public static synchronized void saveDefaultEventsURL(Context context, String eventType, String eventsUrl) {
        synchronized (IronSourceUtils.class) {
            try {
                Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
                editor.putString(getDefaultEventsUrlByEventType(eventType), eventsUrl);
                editor.commit();
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultEventsURL(eventType: " + eventType + ", eventsUrl:" + eventsUrl + ")", e);
            }
        }
    }

    public static synchronized void saveDefaultOptOutEvents(Context context, String eventType, int[] optOutEvents) {
        synchronized (IronSourceUtils.class) {
            try {
                Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
                String optOutEventsString = null;
                if (optOutEvents != null) {
                    StringBuilder str = new StringBuilder();
                    for (int append : optOutEvents) {
                        str.append(append).append(",");
                    }
                    optOutEventsString = str.toString();
                }
                editor.putString(getDefaultOptOutEventsByEventType(eventType), optOutEventsString);
                editor.commit();
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultOptOutEvents(eventType: " + eventType + ", optOutEvents:" + optOutEvents + ")", e);
            }
        }
    }

    public static synchronized void saveDefaultEventsFormatterType(Context context, String eventType, String formatterType) {
        synchronized (IronSourceUtils.class) {
            try {
                Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
                editor.putString(getDefaultFormatterTypeByEventType(eventType), formatterType);
                editor.commit();
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:saveDefaultEventsFormatterType(eventType: " + eventType + ", formatterType:" + formatterType + ")", e);
            }
        }
    }

    public static synchronized String getDefaultEventsFormatterType(Context context, String eventType, String defaultFormatterType) {
        String formatterType;
        synchronized (IronSourceUtils.class) {
            formatterType = defaultFormatterType;
            try {
                formatterType = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(getDefaultFormatterTypeByEventType(eventType), defaultFormatterType);
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:getDefaultEventsFormatterType(eventType: " + eventType + ", defaultFormatterType:" + defaultFormatterType + ")", e);
            }
        }
        return formatterType;
    }

    public static synchronized String getDefaultEventsURL(Context context, String eventType, String defaultEventsURL) {
        String serverUrl;
        synchronized (IronSourceUtils.class) {
            serverUrl = defaultEventsURL;
            try {
                serverUrl = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(getDefaultEventsUrlByEventType(eventType), defaultEventsURL);
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:getDefaultEventsURL(eventType: " + eventType + ", defaultEventsURL:" + defaultEventsURL + ")", e);
            }
        }
        return serverUrl;
    }

    public static synchronized int[] getDefaultOptOutEvents(Context context, String eventType) {
        int[] optOutEvents;
        synchronized (IronSourceUtils.class) {
            optOutEvents = null;
            try {
                String optOutEventsString = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(getDefaultOptOutEventsByEventType(eventType), null);
                if (!TextUtils.isEmpty(optOutEventsString)) {
                    StringTokenizer stringTokenizer = new StringTokenizer(optOutEventsString, ",");
                    ArrayList<Integer> result = new ArrayList();
                    while (stringTokenizer.hasMoreTokens()) {
                        result.add(Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken())));
                    }
                    optOutEvents = new int[result.size()];
                    for (int i = 0; i < optOutEvents.length; i++) {
                        optOutEvents[i] = ((Integer) result.get(i)).intValue();
                    }
                }
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:getDefaultOptOutEvents(eventType: " + eventType + ")", e);
            }
        }
        return optOutEvents;
    }

    public static synchronized void saveLastResponse(Context context, String response) {
        synchronized (IronSourceUtils.class) {
            Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
            editor.putString(LAST_RESPONSE, response);
            editor.apply();
        }
    }

    public static String getLastResponse(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(LAST_RESPONSE, "");
    }

    static synchronized void saveGeneralProperties(Context context, JSONObject properties) {
        synchronized (IronSourceUtils.class) {
            if (!(context == null || properties == null)) {
                Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
                editor.putString(GENERAL_PROPERTIES, properties.toString());
                editor.apply();
            }
        }
    }

    public static synchronized JSONObject getGeneralProperties(Context context) {
        Object result;
        synchronized (IronSourceUtils.class) {
            JSONObject result2 = new JSONObject();
            if (context == null) {
                result = result2;
            } else {
                try {
                    result2 = new JSONObject(context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(GENERAL_PROPERTIES, result2.toString()));
                } catch (JSONException e) {
                }
                JSONObject result3 = result2;
            }
        }
        return result;
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo activeNetwork = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetwork == null) {
            return false;
        }
        return activeNetwork.isConnected();
    }

    public static long getTimeStamp() {
        return System.currentTimeMillis();
    }

    public static JSONObject getProviderAdditionalData(AbstractSmash smash, boolean isDemandOnlyMode) {
        JSONObject data = new JSONObject();
        if (isDemandOnlyMode) {
            try {
                data.put(NETWORK_INSTANCE_KEY, "true");
            } catch (Exception e) {
                IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:getProviderAdditionalData(adapter: " + smash.getName() + ")", e);
            }
        }
        data.put(SUB_PROVIDER_ID_KEY, smash.getSubProviderId());
        data.put("provider", smash.getAdSourceNameForEvents());
        data.put(SDK_VERSION_KEY, smash.getAdapter().getCoreSDKVersion());
        data.put(ADAPTER_VERSION_KEY, smash.getAdapter().getVersion());
        data.put(PROVIDER_PRIORITY, smash.getProviderPriority());
        return data;
    }

    public static JSONObject getProviderAdditionalData(BannerSmash smash) {
        JSONObject data = new JSONObject();
        try {
            data.put(SUB_PROVIDER_ID_KEY, smash.getSubProviderId());
            data.put("provider", smash.getAdSourceNameForEvents());
            data.put(SDK_VERSION_KEY, smash.getAdapter().getCoreSDKVersion());
            data.put(ADAPTER_VERSION_KEY, smash.getAdapter().getVersion());
            data.put(PROVIDER_PRIORITY, smash.getProviderPriority());
        } catch (Exception e) {
            IronSourceLoggerManager.getLogger().logException(IronSourceTag.NATIVE, "IronSourceUtils:getProviderAdditionalData(adapter: " + smash.getName() + ")", e);
        }
        return data;
    }

    public static JSONObject getMediationAdditionalData(boolean isDemandOnlyMode) {
        JSONObject data = new JSONObject();
        if (isDemandOnlyMode) {
            try {
                data.put(NETWORK_INSTANCE_KEY, "true");
            } catch (JSONException e) {
            }
        }
        data.put("provider", "Mediation");
        return data;
    }

    static void saveStringToSharedPrefs(Context context, String key, String value) {
        Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editor.putString(key, value);
        editor.apply();
    }

    static String getStringFromSharedPrefs(Context context, String key, String defaultValue) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getString(key, defaultValue);
    }

    static void saveBooleanToSharedPrefs(Context context, String key, boolean value) {
        Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    static boolean getBooleanFromSharedPrefs(Context context, String key, boolean defaultValue) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getBoolean(key, defaultValue);
    }

    static void saveIntToSharedPrefs(Context context, String key, int value) {
        Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    static int getIntFromSharedPrefs(Context context, String key, int defaultValue) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getInt(key, defaultValue);
    }

    static void saveLongToSharedPrefs(Context context, String key, long value) {
        Editor editor = context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).edit();
        editor.putLong(key, value);
        editor.apply();
    }

    static long getLongFromSharedPrefs(Context context, String key, long defaultValue) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, 0).getLong(key, defaultValue);
    }

    public static JSONObject mergeJsons(JSONObject mainJson, JSONObject secondaryJson) {
        if (mainJson == null && secondaryJson == null) {
            try {
                return new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return mainJson;
            }
        } else if (mainJson == null) {
            return secondaryJson;
        } else {
            if (secondaryJson == null) {
                return mainJson;
            }
            Iterator it = secondaryJson.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                if (!mainJson.has(key)) {
                    mainJson.put(key, secondaryJson.get(key));
                }
            }
            return mainJson;
        }
    }

    public static synchronized void sendAutomationLog(String text) {
        synchronized (IronSourceUtils.class) {
            IronSourceLoggerManager.getLogger().log(IronSourceTag.INTERNAL, "automation_log:" + Long.toString(System.currentTimeMillis()) + "text:" + text, 1);
        }
    }
}
