// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.content.Intent;
import java.util.HashMap;
import org.json.JSONArray;
import com.facebook.appevents.internal.Constants;
import android.content.SharedPreferences;
import org.json.JSONException;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import android.text.TextUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;
import android.content.Context;
import org.json.JSONObject;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;

public final class FetchedAppSettingsManager
{
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_ANDROID_SDK_ERROR_CATEGORIES = "android_sdk_error_categories";
    private static final String APP_SETTING_APP_EVENTS_FEATURE_BITMASK = "app_events_feature_bitmask";
    private static final String APP_SETTING_APP_EVENTS_SESSION_TIMEOUT = "app_events_session_timeout";
    private static final String APP_SETTING_CUSTOM_TABS_ENABLED = "gdpv4_chrome_custom_tabs_enabled";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String[] APP_SETTING_FIELDS;
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_SMART_LOGIN_OPTIONS = "seamless_login";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    private static final int AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 8;
    private static final int IAP_AUTOMATIC_LOGGING_ENABLED_BITMASK_FIELD = 16;
    private static final String SDK_UPDATE_MESSAGE = "sdk_update_message";
    private static final String SMART_LOGIN_BOOKMARK_ICON_URL = "smart_login_bookmark_icon_url";
    private static final String SMART_LOGIN_MENU_ICON_URL = "smart_login_menu_icon_url";
    private static final String TAG;
    private static Map<String, FetchedAppSettings> fetchedAppSettings;
    private static AtomicBoolean loadingSettings;
    private static boolean printedSDKUpdatedMessage;
    
    static {
        TAG = FetchedAppSettingsManager.class.getCanonicalName();
        APP_SETTING_FIELDS = new String[] { "supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "gdpv4_chrome_custom_tabs_enabled", "android_dialog_configs", "android_sdk_error_categories", "app_events_session_timeout", "app_events_feature_bitmask", "seamless_login", "smart_login_bookmark_icon_url", "smart_login_menu_icon_url" };
        FetchedAppSettingsManager.fetchedAppSettings = new ConcurrentHashMap<String, FetchedAppSettings>();
        FetchedAppSettingsManager.loadingSettings = new AtomicBoolean(false);
        FetchedAppSettingsManager.printedSDKUpdatedMessage = false;
    }
    
    private static JSONObject getAppSettingsQueryResponse(final String s) {
        final Bundle parameters = new Bundle();
        parameters.putString("fields", TextUtils.join((CharSequence)",", (Iterable)new ArrayList(Arrays.asList(FetchedAppSettingsManager.APP_SETTING_FIELDS))));
        final GraphRequest graphPathRequest = GraphRequest.newGraphPathRequest(null, s, null);
        graphPathRequest.setSkipClientToken(true);
        graphPathRequest.setParameters(parameters);
        return graphPathRequest.executeAndWait().getJSONObject();
    }
    
    public static FetchedAppSettings getAppSettingsWithoutQuery(final String s) {
        if (s != null) {
            return FetchedAppSettingsManager.fetchedAppSettings.get(s);
        }
        return null;
    }
    
    public static void loadAppSettingsAsync() {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        final boolean compareAndSet = FetchedAppSettingsManager.loadingSettings.compareAndSet(false, true);
        if (Utility.isNullOrEmpty(applicationId) || FetchedAppSettingsManager.fetchedAppSettings.containsKey(applicationId) || !compareAndSet) {
            return;
        }
        FacebookSdk.getExecutor().execute(new Runnable() {
            final /* synthetic */ String val$settingsKey = String.format("com.facebook.internal.APP_SETTINGS.%s", applicationId);
            
            @Override
            public void run() {
                final SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
                final String string = sharedPreferences.getString(this.val$settingsKey, (String)null);
                FetchedAppSettings access$000;
                final FetchedAppSettings fetchedAppSettings = access$000 = null;
                Label_0067: {
                    if (Utility.isNullOrEmpty(string)) {
                        break Label_0067;
                    }
                    JSONObject access$2 = null;
                    while (true) {
                        try {
                            access$2 = new JSONObject(string);
                            access$000 = fetchedAppSettings;
                            if (access$2 != null) {
                                access$000 = parseAppSettingsFromJSON(applicationId, access$2);
                            }
                            access$2 = getAppSettingsQueryResponse(applicationId);
                            if (access$2 != null) {
                                parseAppSettingsFromJSON(applicationId, access$2);
                                sharedPreferences.edit().putString(this.val$settingsKey, access$2.toString()).apply();
                            }
                            if (access$000 != null) {
                                final String sdkUpdateMessage = access$000.getSdkUpdateMessage();
                                if (!FetchedAppSettingsManager.printedSDKUpdatedMessage && sdkUpdateMessage != null && sdkUpdateMessage.length() > 0) {
                                    FetchedAppSettingsManager.printedSDKUpdatedMessage = true;
                                    Log.w(FetchedAppSettingsManager.TAG, sdkUpdateMessage);
                                }
                            }
                            AutomaticAnalyticsLogger.logActivateAppEvent();
                            startInAppPurchaseAutoLogging(applicationContext);
                            FetchedAppSettingsManager.loadingSettings.set(false);
                        }
                        catch (JSONException ex) {
                            Utility.logd("FacebookSDK", (Exception)ex);
                            continue;
                        }
                        break;
                    }
                }
            }
        });
    }
    
    private static FetchedAppSettings parseAppSettingsFromJSON(final String s, final JSONObject jsonObject) {
        final JSONArray optJSONArray = jsonObject.optJSONArray("android_sdk_error_categories");
        FacebookRequestErrorClassification facebookRequestErrorClassification;
        if (optJSONArray == null) {
            facebookRequestErrorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
        }
        else {
            facebookRequestErrorClassification = FacebookRequestErrorClassification.createFromJSON(optJSONArray);
        }
        final int optInt = jsonObject.optInt("app_events_feature_bitmask", 0);
        final FetchedAppSettings fetchedAppSettings = new FetchedAppSettings(jsonObject.optBoolean("supports_implicit_sdk_logging", false), jsonObject.optString("gdpv4_nux_content", ""), jsonObject.optBoolean("gdpv4_nux_enabled", false), jsonObject.optBoolean("gdpv4_chrome_custom_tabs_enabled", false), jsonObject.optInt("app_events_session_timeout", Constants.getDefaultAppEventsSessionTimeoutInSeconds()), SmartLoginOption.parseOptions(jsonObject.optLong("seamless_login")), parseDialogConfigurations(jsonObject.optJSONObject("android_dialog_configs")), (optInt & 0x8) != 0x0, facebookRequestErrorClassification, jsonObject.optString("smart_login_bookmark_icon_url"), jsonObject.optString("smart_login_menu_icon_url"), (optInt & 0x10) != 0x0, jsonObject.optString("sdk_update_message"));
        FetchedAppSettingsManager.fetchedAppSettings.put(s, fetchedAppSettings);
        return fetchedAppSettings;
    }
    
    private static Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>> parseDialogConfigurations(final JSONObject jsonObject) {
        final HashMap<Object, Map<String, FetchedAppSettings.DialogFeatureConfig>> hashMap = new HashMap<Object, Map<String, FetchedAppSettings.DialogFeatureConfig>>();
        if (jsonObject != null) {
            final JSONArray optJSONArray = jsonObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final FetchedAppSettings.DialogFeatureConfig dialogConfig = FetchedAppSettings.DialogFeatureConfig.parseDialogConfig(optJSONArray.optJSONObject(i));
                    if (dialogConfig != null) {
                        final String dialogName = dialogConfig.getDialogName();
                        Map<String, FetchedAppSettings.DialogFeatureConfig> map;
                        if ((map = hashMap.get(dialogName)) == null) {
                            map = new HashMap<String, FetchedAppSettings.DialogFeatureConfig>();
                            hashMap.put(dialogName, map);
                        }
                        map.put(dialogConfig.getFeatureName(), dialogConfig);
                    }
                }
            }
        }
        return (Map<String, Map<String, FetchedAppSettings.DialogFeatureConfig>>)hashMap;
    }
    
    public static FetchedAppSettings queryAppSettings(final String s, final boolean b) {
        if (!b && FetchedAppSettingsManager.fetchedAppSettings.containsKey(s)) {
            return FetchedAppSettingsManager.fetchedAppSettings.get(s);
        }
        final JSONObject appSettingsQueryResponse = getAppSettingsQueryResponse(s);
        if (appSettingsQueryResponse == null) {
            return null;
        }
        return parseAppSettingsFromJSON(s, appSettingsQueryResponse);
    }
    
    private static void startInAppPurchaseAutoLogging(final Context context) {
        CallbackManagerImpl.registerStaticCallback(CallbackManagerImpl.RequestCodeOffset.InAppPurchase.toRequestCode(), (CallbackManagerImpl.Callback)new CallbackManagerImpl.Callback() {
            @Override
            public boolean onActivityResult(final int n, final Intent intent) {
                FacebookSdk.getExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        AutomaticAnalyticsLogger.logInAppPurchaseEvent(context, n, intent);
                    }
                });
                return true;
            }
        });
    }
}
