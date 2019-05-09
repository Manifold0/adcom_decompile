// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import com.facebook.FacebookSdkNotInitializedException;
import com.facebook.FacebookException;
import android.os.Looper;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import java.util.List;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.facebook.FacebookSdk;
import java.util.Iterator;
import java.util.Collection;
import android.util.Log;
import android.content.Context;

public final class Validate
{
    private static final String CONTENT_PROVIDER_BASE = "com.facebook.app.FacebookContentProvider";
    private static final String CONTENT_PROVIDER_NOT_FOUND_REASON = "A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.";
    private static final String CUSTOM_TAB_REDIRECT_ACTIVITY_NOT_FOUND_REASON = "FacebookActivity is declared incorrectly in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
    private static final String FACEBOOK_ACTIVITY_NOT_FOUND_REASON = "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.";
    private static final String NO_INTERNET_PERMISSION_REASON = "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.";
    private static final String TAG;
    
    static {
        TAG = Validate.class.getName();
    }
    
    public static void checkCustomTabRedirectActivity(final Context context) {
        checkCustomTabRedirectActivity(context, true);
    }
    
    public static void checkCustomTabRedirectActivity(final Context context, final boolean b) {
        if (!hasCustomTabRedirectActivity(context)) {
            if (b) {
                throw new IllegalStateException("FacebookActivity is declared incorrectly in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
            }
            Log.w(Validate.TAG, "FacebookActivity is declared incorrectly in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
        }
    }
    
    public static void containsNoNullOrEmpty(final Collection<String> collection, final String s) {
        notNull(collection, s);
        for (final String s2 : collection) {
            if (s2 == null) {
                throw new NullPointerException("Container '" + s + "' cannot contain null values");
            }
            if (s2.length() == 0) {
                throw new IllegalArgumentException("Container '" + s + "' cannot contain empty values");
            }
        }
    }
    
    public static <T> void containsNoNulls(final Collection<T> collection, final String s) {
        notNull(collection, s);
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == null) {
                throw new NullPointerException("Container '" + s + "' cannot contain null values");
            }
        }
    }
    
    public static String hasAppID() {
        final String applicationId = FacebookSdk.getApplicationId();
        if (applicationId == null) {
            throw new IllegalStateException("No App ID found, please set the App ID.");
        }
        return applicationId;
    }
    
    public static boolean hasBluetoothPermission(final Context context) {
        return hasPermission(context, "android.permission.BLUETOOTH") && hasPermission(context, "android.permission.BLUETOOTH_ADMIN");
    }
    
    public static boolean hasChangeWifiStatePermission(final Context context) {
        return hasPermission(context, "android.permission.CHANGE_WIFI_STATE");
    }
    
    public static String hasClientToken() {
        final String clientToken = FacebookSdk.getClientToken();
        if (clientToken == null) {
            throw new IllegalStateException("No Client Token found, please set the Client Token.");
        }
        return clientToken;
    }
    
    public static void hasContentProvider(final Context context) {
        notNull(context, "context");
        final String hasAppID = hasAppID();
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            final String string = "com.facebook.app.FacebookContentProvider" + hasAppID;
            if (packageManager.resolveContentProvider(string, 0) == null) {
                throw new IllegalStateException(String.format("A ContentProvider for this app was not set up in the AndroidManifest.xml, please add %s as a provider to your AndroidManifest.xml file. See https://developers.facebook.com/docs/sharing/android for more info.", string));
            }
        }
    }
    
    public static boolean hasCustomTabRedirectActivity(final Context context) {
        notNull(context, "context");
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentActivities = null;
        if (packageManager != null) {
            final Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addCategory("android.intent.category.BROWSABLE");
            intent.setData(Uri.parse("fb" + FacebookSdk.getApplicationId() + "://authorize"));
            queryIntentActivities = (List<ResolveInfo>)packageManager.queryIntentActivities(intent, 64);
        }
        boolean b = false;
        final boolean b2 = false;
        if (queryIntentActivities != null) {
            final Iterator<ResolveInfo> iterator = queryIntentActivities.iterator();
            b = b2;
            while (iterator.hasNext()) {
                final ActivityInfo activityInfo = iterator.next().activityInfo;
                if (!activityInfo.name.equals("com.facebook.CustomTabActivity") || !activityInfo.packageName.equals(context.getPackageName())) {
                    return false;
                }
                b = true;
            }
        }
        return b;
    }
    
    public static void hasFacebookActivity(final Context context) {
        hasFacebookActivity(context, true);
    }
    
    public static void hasFacebookActivity(final Context context, final boolean b) {
        notNull(context, "context");
        final PackageManager packageManager = context.getPackageManager();
        ActivityInfo activityInfo = null;
        while (true) {
            if (packageManager == null) {
                break Label_0040;
            }
            final ComponentName componentName = new ComponentName(context, "com.facebook.FacebookActivity");
            try {
                activityInfo = packageManager.getActivityInfo(componentName, 1);
                if (activityInfo == null) {
                    if (b) {
                        throw new IllegalStateException("FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
                    }
                    Log.w(Validate.TAG, "FacebookActivity is not declared in the AndroidManifest.xml, please add com.facebook.FacebookActivity to your AndroidManifest.xml file. See https://developers.facebook.com/docs/android/getting-started for more info.");
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                activityInfo = activityInfo;
                continue;
            }
            break;
        }
    }
    
    public static void hasInternetPermissions(final Context context) {
        hasInternetPermissions(context, true);
    }
    
    public static void hasInternetPermissions(final Context context, final boolean b) {
        notNull(context, "context");
        if (context.checkCallingOrSelfPermission("android.permission.INTERNET") == -1) {
            if (b) {
                throw new IllegalStateException("No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
            }
            Log.w(Validate.TAG, "No internet permissions granted for the app, please add <uses-permission android:name=\"android.permission.INTERNET\" /> to your AndroidManifest.xml.");
        }
    }
    
    public static boolean hasLocationPermission(final Context context) {
        return hasPermission(context, "android.permission.ACCESS_COARSE_LOCATION") || hasPermission(context, "android.permission.ACCESS_FINE_LOCATION");
    }
    
    public static boolean hasPermission(final Context context, final String s) {
        return context.checkCallingOrSelfPermission(s) == 0;
    }
    
    public static boolean hasWiFiPermission(final Context context) {
        return hasPermission(context, "android.permission.ACCESS_WIFI_STATE");
    }
    
    public static <T> void notEmpty(final Collection<T> collection, final String s) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Container '" + s + "' cannot be empty");
        }
    }
    
    public static <T> void notEmptyAndContainsNoNulls(final Collection<T> collection, final String s) {
        containsNoNulls((Collection<Object>)collection, s);
        notEmpty((Collection<Object>)collection, s);
    }
    
    public static void notNull(final Object o, final String s) {
        if (o == null) {
            throw new NullPointerException("Argument '" + s + "' cannot be null");
        }
    }
    
    public static void notNullOrEmpty(final String s, final String s2) {
        if (Utility.isNullOrEmpty(s)) {
            throw new IllegalArgumentException("Argument '" + s2 + "' cannot be null or empty");
        }
    }
    
    public static void oneOf(final Object o, final String s, final Object... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final Object o2 = array[i];
            if (o2 != null) {
                if (o2.equals(o)) {
                    return;
                }
            }
            else if (o == null) {
                return;
            }
        }
        throw new IllegalArgumentException("Argument '" + s + "' was not one of the allowed values");
    }
    
    public static void runningOnUiThread() {
        if (!Looper.getMainLooper().equals(Looper.myLooper())) {
            throw new FacebookException("This method should be called from the UI thread");
        }
    }
    
    public static void sdkInitialized() {
        if (!FacebookSdk.isInitialized()) {
            throw new FacebookSdkNotInitializedException("The SDK has not been initialized, make sure to call FacebookSdk.sdkInitialize() first.");
        }
    }
}
