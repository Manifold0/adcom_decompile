package com.tapjoy;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import com.tapjoy.internal.du;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public final class Tapjoy {
    public static final String INTENT_EXTRA_PUSH_PAYLOAD = "com.tapjoy.PUSH_PAYLOAD";

    public static String getVersion() {
        return du.m7479a().mo6245b();
    }

    public static void setDebugEnabled(boolean enable) {
        du.m7479a().mo6242a(enable);
    }

    public static boolean connect(Context context, String sdkKey) {
        return du.m7479a().mo6243a(context, sdkKey);
    }

    public static boolean connect(Context context, String sdkKey, Hashtable flags) {
        return du.m7479a().mo6244a(context, sdkKey, flags, null);
    }

    public static synchronized boolean connect(Context applicationContext, String sdkKey, Hashtable flags, TJConnectListener publisherListener) {
        boolean a;
        synchronized (Tapjoy.class) {
            a = du.m7479a().mo6244a(applicationContext, sdkKey, flags, publisherListener);
        }
        return a;
    }

    public static TJPlacement getPlacement(String placementName, TJPlacementListener listener) {
        return du.m7479a().mo6218a(placementName, listener);
    }

    public static void setActivity(Activity activity) {
        du.m7479a().mo6224a(activity);
    }

    public static void getCurrencyBalance(TJGetCurrencyBalanceListener listener) {
        du.m7479a().mo6227a(listener);
    }

    public static void spendCurrency(int amount, TJSpendCurrencyListener listener) {
        du.m7479a().mo6222a(amount, listener);
    }

    public static void awardCurrency(int amount, TJAwardCurrencyListener listener) {
        du.m7479a().mo6221a(amount, listener);
    }

    public static void setEarnedCurrencyListener(TJEarnedCurrencyListener listener) {
        du.m7479a().mo6226a(listener);
    }

    @Deprecated
    public static void setCurrencyMultiplier(float multiplier) {
        du.m7479a().mo6219a(multiplier);
    }

    @Deprecated
    public static float getCurrencyMultiplier() {
        return du.m7479a().mo6251c();
    }

    public static void trackPurchase(String productId, String currencyCode, double price, String campaignId) {
        du.m7479a().mo6233a(productId, currencyCode, price, campaignId);
    }

    public static void trackPurchase(String skuDetails, String purchaseData, String dataSignature, String campaignId) {
        du.m7479a().mo6235a(skuDetails, purchaseData, dataSignature, campaignId);
    }

    @Deprecated
    public static void trackPurchase(String skuDetails, String campaignId) {
        du.m7479a().mo6232a(skuDetails, campaignId);
    }

    public static void trackEvent(String name) {
        du.m7479a().mo6229a(name);
    }

    public static void trackEvent(String name, long value) {
        du.m7479a().mo6230a(name, value);
    }

    public static void trackEvent(String category, String name, long value) {
        du.m7479a().mo6234a(category, name, value);
    }

    public static void trackEvent(String category, String name, String parameter1, String parameter2) {
        du.m7479a().mo6249b(category, name, parameter1, parameter2);
    }

    public static void trackEvent(String category, String name, String parameter1, String parameter2, long value) {
        du.m7479a().mo6236a(category, name, parameter1, parameter2, value);
    }

    public static void trackEvent(String category, String name, String parameter1, String parameter2, String valueName, long value) {
        du.m7479a().mo6237a(category, name, parameter1, parameter2, valueName, value);
    }

    public static void trackEvent(String category, String name, String parameter1, String parameter2, String value1Name, long value1, String value2Name, long value2) {
        du.m7479a().mo6238a(category, name, parameter1, parameter2, value1Name, value1, value2Name, value2);
    }

    public static void trackEvent(String category, String name, String parameter1, String parameter2, String value1Name, long value1, String value2Name, long value2, String value3Name, long value3) {
        du.m7479a().mo6239a(category, name, parameter1, parameter2, value1Name, value1, value2Name, value2, value3Name, value3);
    }

    public static void trackEvent(String category, String name, String parameter1, String parameter2, Map values) {
        du.m7479a().mo6240a(category, name, parameter1, parameter2, values);
    }

    public static void startSession() {
        du.m7479a().mo6255d();
    }

    public static void endSession() {
        du.m7479a().mo6257e();
    }

    public static void onActivityStart(Activity activity) {
        du.m7479a().mo6247b(activity);
    }

    public static void onActivityStop(Activity activity) {
        du.m7479a().mo6252c(activity);
    }

    public static void setUserID(String userID) {
        setUserID(userID, null);
    }

    public static void setUserID(String userID, TJSetUserIDListener listener) {
        du.m7479a().mo6231a(userID, listener);
    }

    public static void setUserLevel(int userLevel) {
        du.m7479a().mo6220a(userLevel);
    }

    public static void setUserFriendCount(int friendCount) {
        du.m7479a().mo6246b(friendCount);
    }

    public static void setAppDataVersion(String dataVersion) {
        du.m7479a().mo6248b(dataVersion);
    }

    public static void setUserCohortVariable(int variableIndex, String value) {
        du.m7479a().mo6223a(variableIndex, value);
    }

    public static Set getUserTags() {
        return du.m7479a().mo6259f();
    }

    public static void setUserTags(Set tags) {
        du.m7479a().mo6241a(tags);
    }

    public static void clearUserTags() {
        du.m7479a().mo6262g();
    }

    public static void addUserTag(String tag) {
        du.m7479a().mo6253c(tag);
    }

    public static void removeUserTag(String tag) {
        du.m7479a().mo6256d(tag);
    }

    public static void setVideoListener(TJVideoListener listener) {
        du.m7479a().mo6228a(listener);
    }

    public static void actionComplete(String actionID) {
        du.m7479a().mo6258e(actionID);
    }

    public static void setGcmSender(String senderId) {
        du.m7479a().mo6260f(senderId);
    }

    public static boolean isPushNotificationDisabled() {
        return du.m7479a().mo6264h();
    }

    public static void setPushNotificationDisabled(boolean disabled) {
        du.m7479a().mo6250b(disabled);
    }

    public static void loadSharedLibrary() {
        try {
            System.loadLibrary("tapjoy");
        } catch (UnsatisfiedLinkError e) {
        }
    }

    public static void setGLSurfaceView(GLSurfaceView glSurfaceView) {
        du.m7479a().mo6225a(glSurfaceView);
    }

    public static String getSupportURL() {
        return du.m7479a().mo6261g(null);
    }

    public static String getSupportURL(String currencyId) {
        return du.m7479a().mo6261g(currencyId);
    }

    public static boolean isConnected() {
        return du.m7479a().mo6265i();
    }

    public static void subjectToGDPR(boolean gdprApplicable) {
        du.m7479a().mo6254c(gdprApplicable);
    }

    public static void setUserConsent(String value) {
        du.m7479a().mo6263h(value);
    }
}
