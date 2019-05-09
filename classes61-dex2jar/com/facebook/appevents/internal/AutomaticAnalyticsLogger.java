// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents.internal;

import com.facebook.internal.Utility;
import org.json.JSONException;
import java.util.Currency;
import java.math.BigDecimal;
import org.json.JSONObject;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import com.facebook.appevents.AppEventsLogger;
import android.util.Log;
import android.app.Application;
import com.facebook.internal.Validate;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.FacebookSdk;
import android.support.annotation.Nullable;

public class AutomaticAnalyticsLogger
{
    private static final String INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    private static final String TAG;
    @Nullable
    private static Object inAppBillingObj;
    
    static {
        TAG = AutomaticAnalyticsLogger.class.getCanonicalName();
    }
    
    public static boolean isImplicitPurchaseLoggingEnabled() {
        final FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
        return appSettingsWithoutQuery != null && FacebookSdk.getAutoLogAppEventsEnabled() && appSettingsWithoutQuery.getIAPAutomaticLoggingEnabled();
    }
    
    public static void logActivateAppEvent() {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        final boolean autoLogAppEventsEnabled = FacebookSdk.getAutoLogAppEventsEnabled();
        Validate.notNull(applicationContext, "context");
        if (autoLogAppEventsEnabled) {
            if (!(applicationContext instanceof Application)) {
                Log.w(AutomaticAnalyticsLogger.TAG, "Automatic logging of basic events will not happen, because FacebookSdk.getApplicationContext() returns object that is not instance of android.app.Application. Make sure you call FacebookSdk.sdkInitialize() from Application class and pass application context.");
                return;
            }
            AppEventsLogger.activateApp((Application)applicationContext, applicationId);
        }
    }
    
    public static void logActivityTimeSpentEvent(final String s, final long n) {
        final Context applicationContext = FacebookSdk.getApplicationContext();
        final String applicationId = FacebookSdk.getApplicationId();
        Validate.notNull(applicationContext, "context");
        final FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(applicationId, false);
        if (queryAppSettings != null && queryAppSettings.getAutomaticLoggingEnabled() && n > 0L) {
            final AppEventsLogger logger = AppEventsLogger.newLogger(applicationContext);
            final Bundle bundle = new Bundle(1);
            bundle.putCharSequence("fb_aa_time_spent_view_name", (CharSequence)s);
            logger.logEvent("fb_aa_time_spent_on_view", (double)n, bundle);
        }
    }
    
    public static boolean logInAppPurchaseEvent(final Context context, final int n, final Intent intent) {
        boolean b = true;
        if (intent == null || !isImplicitPurchaseLoggingEnabled()) {
            b = false;
        }
        else {
            final String stringExtra = intent.getStringExtra("INAPP_PURCHASE_DATA");
            if (n == -1) {
                final ServiceConnection serviceConnection = (ServiceConnection)new ServiceConnection() {
                    public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                        AutomaticAnalyticsLogger.inAppBillingObj = InAppPurchaseEventManager.getServiceInterface(context, binder);
                        try {
                            final JSONObject jsonObject = new JSONObject(stringExtra);
                            final String string = jsonObject.getString("productId");
                            final String purchaseDetails = InAppPurchaseEventManager.getPurchaseDetails(context, string, AutomaticAnalyticsLogger.inAppBillingObj, jsonObject.has("autoRenewing"));
                            if (purchaseDetails.equals("")) {
                                return;
                            }
                            final JSONObject jsonObject2 = new JSONObject(purchaseDetails);
                            final AppEventsLogger logger = AppEventsLogger.newLogger(context);
                            final Bundle bundle = new Bundle(1);
                            bundle.putCharSequence("fb_iap_product_id", (CharSequence)string);
                            bundle.putCharSequence("fb_iap_purchase_time", (CharSequence)jsonObject.getString("purchaseTime"));
                            bundle.putCharSequence("fb_iap_purchase_state", (CharSequence)jsonObject.getString("purchaseState"));
                            bundle.putCharSequence("fb_iap_purchase_token", (CharSequence)jsonObject.getString("purchaseToken"));
                            bundle.putCharSequence("fb_iap_package_name", (CharSequence)jsonObject.getString("packageName"));
                            bundle.putCharSequence("fb_iap_product_type", (CharSequence)jsonObject2.getString("type"));
                            bundle.putCharSequence("fb_iap_product_title", (CharSequence)jsonObject2.getString("title"));
                            bundle.putCharSequence("fb_iap_product_description", (CharSequence)jsonObject2.getString("description"));
                            bundle.putCharSequence("fb_iap_subs_auto_renewing", (CharSequence)Boolean.toString(jsonObject.optBoolean("autoRenewing", false)));
                            bundle.putCharSequence("fb_iap_subs_period", (CharSequence)jsonObject2.optString("subscriptionPeriod"));
                            bundle.putCharSequence("fb_free_trial_period", (CharSequence)jsonObject2.optString("freeTrialPeriod"));
                            bundle.putCharSequence("fb_intro_price_amount_micros", (CharSequence)jsonObject2.optString("introductoryPriceAmountMicros"));
                            bundle.putCharSequence("fb_intro_price_cycles", (CharSequence)jsonObject2.optString("introductoryPriceCycles"));
                            logger.logPurchaseImplicitly(new BigDecimal(jsonObject2.getLong("price_amount_micros") / 1000000.0), Currency.getInstance(jsonObject2.getString("price_currency_code")), bundle);
                        }
                        catch (JSONException ex) {
                            Log.e(AutomaticAnalyticsLogger.TAG, "Error parsing in-app purchase data.", (Throwable)ex);
                        }
                        finally {
                            context.unbindService((ServiceConnection)this);
                        }
                    }
                    
                    public void onServiceDisconnected(final ComponentName componentName) {
                        AutomaticAnalyticsLogger.inAppBillingObj = null;
                        Utility.logd(AutomaticAnalyticsLogger.TAG, "In-app billing service disconnected");
                    }
                };
                final Intent intent2 = new Intent("com.android.vending.billing.InAppBillingService.BIND");
                intent2.setPackage("com.android.vending");
                context.bindService(intent2, (ServiceConnection)serviceConnection, 1);
                return true;
            }
        }
        return b;
    }
}
