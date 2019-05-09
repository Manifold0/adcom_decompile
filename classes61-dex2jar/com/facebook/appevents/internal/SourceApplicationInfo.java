// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.appevents.internal;

import android.os.Bundle;
import android.content.Intent;
import android.content.ComponentName;
import bolts.AppLinks;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences$Editor;
import android.preference.PreferenceManager;
import com.facebook.FacebookSdk;

class SourceApplicationInfo
{
    private static final String CALL_APPLICATION_PACKAGE_KEY = "com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage";
    private static final String OPENED_BY_APP_LINK_KEY = "com.facebook.appevents.SourceApplicationInfo.openedByApplink";
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private String callingApplicationPackage;
    private boolean openedByAppLink;
    
    private SourceApplicationInfo(final String callingApplicationPackage, final boolean openedByAppLink) {
        this.callingApplicationPackage = callingApplicationPackage;
        this.openedByAppLink = openedByAppLink;
    }
    
    public static void clearSavedSourceApplicationInfoFromDisk() {
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        edit.remove("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage");
        edit.remove("com.facebook.appevents.SourceApplicationInfo.openedByApplink");
        edit.apply();
    }
    
    public static SourceApplicationInfo getStoredSourceApplicatioInfo() {
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        if (!defaultSharedPreferences.contains("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage")) {
            return null;
        }
        return new SourceApplicationInfo(defaultSharedPreferences.getString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", (String)null), defaultSharedPreferences.getBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", false));
    }
    
    public String getCallingApplicationPackage() {
        return this.callingApplicationPackage;
    }
    
    public boolean isOpenedByAppLink() {
        return this.openedByAppLink;
    }
    
    @Override
    public String toString() {
        String s = "Unclassified";
        if (this.openedByAppLink) {
            s = "Applink";
        }
        String string = s;
        if (this.callingApplicationPackage != null) {
            string = s + "(" + this.callingApplicationPackage + ")";
        }
        return string;
    }
    
    public void writeSourceApplicationInfoToDisk() {
        final SharedPreferences$Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        edit.putString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", this.callingApplicationPackage);
        edit.putBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", this.openedByAppLink);
        edit.apply();
    }
    
    public static class Factory
    {
        public static SourceApplicationInfo create(final Activity activity) {
            final boolean b = false;
            String packageName = "";
            final ComponentName callingActivity = activity.getCallingActivity();
            if (callingActivity != null && (packageName = callingActivity.getPackageName()).equals(activity.getPackageName())) {
                return null;
            }
            final Intent intent = activity.getIntent();
            String string = packageName;
            boolean b2 = b;
            if (intent != null) {
                string = packageName;
                b2 = b;
                if (!intent.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)) {
                    intent.putExtra("_fbSourceApplicationHasBeenSet", true);
                    final Bundle appLinkData = AppLinks.getAppLinkData(intent);
                    string = packageName;
                    b2 = b;
                    if (appLinkData != null) {
                        final boolean b3 = true;
                        final Bundle bundle = appLinkData.getBundle("referer_app_link");
                        string = packageName;
                        b2 = b3;
                        if (bundle != null) {
                            string = bundle.getString("package");
                            b2 = b3;
                        }
                    }
                }
            }
            intent.putExtra("_fbSourceApplicationHasBeenSet", true);
            return new SourceApplicationInfo(string, b2, null);
        }
    }
}
