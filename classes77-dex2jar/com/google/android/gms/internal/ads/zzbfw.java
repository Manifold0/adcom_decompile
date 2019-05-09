// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.IntentFilter;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;

public final class zzbfw
{
    private static String zzedy;
    
    public static String zzbn(final Context context) {
        if (zzbfw.zzedy != null) {
            return zzbfw.zzedy;
        }
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        final ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        String packageName;
        if (resolveActivity != null) {
            packageName = resolveActivity.activityInfo.packageName;
        }
        else {
            packageName = null;
        }
        final List queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        final ArrayList<String> list = new ArrayList<String>();
        for (final ResolveInfo resolveInfo : queryIntentActivities) {
            final Intent intent2 = new Intent();
            intent2.setAction("android.support.customtabs.action.CustomTabsService");
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                list.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (list.isEmpty()) {
            zzbfw.zzedy = null;
        }
        else if (list.size() == 1) {
            zzbfw.zzedy = (String)list.get(0);
        }
        else if (!TextUtils.isEmpty((CharSequence)packageName) && !zzd(context, intent) && list.contains(packageName)) {
            zzbfw.zzedy = packageName;
        }
        else if (list.contains("com.android.chrome")) {
            zzbfw.zzedy = "com.android.chrome";
        }
        else if (list.contains("com.chrome.beta")) {
            zzbfw.zzedy = "com.chrome.beta";
        }
        else if (list.contains("com.chrome.dev")) {
            zzbfw.zzedy = "com.chrome.dev";
        }
        else if (list.contains("com.google.android.apps.chrome")) {
            zzbfw.zzedy = "com.google.android.apps.chrome";
        }
        return zzbfw.zzedy;
    }
    
    private static boolean zzd(final Context context, final Intent intent) {
        try {
            final List queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities != null && queryIntentActivities.size() != 0) {
                for (final ResolveInfo resolveInfo : queryIntentActivities) {
                    final IntentFilter filter = resolveInfo.filter;
                    if (filter != null && filter.countDataAuthorities() != 0 && filter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        catch (RuntimeException ex) {
            Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        }
        return false;
    }
}
