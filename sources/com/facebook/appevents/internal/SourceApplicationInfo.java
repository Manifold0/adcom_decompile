package com.facebook.appevents.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import bolts.AppLinks;
import com.facebook.FacebookSdk;
import com.kongregate.android.internal.sdk.C0498e;

class SourceApplicationInfo {
    private static final String CALL_APPLICATION_PACKAGE_KEY = "com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage";
    private static final String OPENED_BY_APP_LINK_KEY = "com.facebook.appevents.SourceApplicationInfo.openedByApplink";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private String callingApplicationPackage;
    private boolean openedByAppLink;

    public static class Factory {
        public static SourceApplicationInfo create(Activity activity) {
            boolean openedByAppLink = false;
            String callingApplicationPackage = "";
            ComponentName callingApplication = activity.getCallingActivity();
            if (callingApplication != null) {
                callingApplicationPackage = callingApplication.getPackageName();
                if (callingApplicationPackage.equals(activity.getPackageName())) {
                    return null;
                }
            }
            Intent openIntent = activity.getIntent();
            if (!(openIntent == null || openIntent.getBooleanExtra(SourceApplicationInfo.SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, false))) {
                openIntent.putExtra(SourceApplicationInfo.SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
                Bundle appLinkData = AppLinks.getAppLinkData(openIntent);
                if (appLinkData != null) {
                    openedByAppLink = true;
                    Bundle appLinkReferrerData = appLinkData.getBundle("referer_app_link");
                    if (appLinkReferrerData != null) {
                        callingApplicationPackage = appLinkReferrerData.getString(C0498e.f503t);
                    }
                }
            }
            openIntent.putExtra(SourceApplicationInfo.SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT, true);
            return new SourceApplicationInfo(callingApplicationPackage, openedByAppLink);
        }
    }

    private SourceApplicationInfo(String callingApplicationPackage, boolean openedByAppLink) {
        this.callingApplicationPackage = callingApplicationPackage;
        this.openedByAppLink = openedByAppLink;
    }

    public static SourceApplicationInfo getStoredSourceApplicatioInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        if (sharedPreferences.contains(CALL_APPLICATION_PACKAGE_KEY)) {
            return new SourceApplicationInfo(sharedPreferences.getString(CALL_APPLICATION_PACKAGE_KEY, null), sharedPreferences.getBoolean(OPENED_BY_APP_LINK_KEY, false));
        }
        return null;
    }

    public static void clearSavedSourceApplicationInfoFromDisk() {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        editor.remove(CALL_APPLICATION_PACKAGE_KEY);
        editor.remove(OPENED_BY_APP_LINK_KEY);
        editor.apply();
    }

    public String getCallingApplicationPackage() {
        return this.callingApplicationPackage;
    }

    public boolean isOpenedByAppLink() {
        return this.openedByAppLink;
    }

    public String toString() {
        String openType = "Unclassified";
        if (this.openedByAppLink) {
            openType = "Applink";
        }
        if (this.callingApplicationPackage != null) {
            return openType + "(" + this.callingApplicationPackage + ")";
        }
        return openType;
    }

    public void writeSourceApplicationInfoToDisk() {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        editor.putString(CALL_APPLICATION_PACKAGE_KEY, this.callingApplicationPackage);
        editor.putBoolean(OPENED_BY_APP_LINK_KEY, this.openedByAppLink);
        editor.apply();
    }
}
