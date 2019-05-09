// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.content.pm.PackageItemInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.Signature;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageInfo;
import android.content.Context;

public class k
{
    public static PackageInfo a(final Context context) {
        try {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageInfo(context.getPackageName(), 0);
            }
        }
        catch (PackageManager$NameNotFoundException ex) {
            j.d("package not found: " + context.getPackageName(), (Throwable)ex);
        }
        return null;
    }
    
    public static boolean a(final Context context, final int n) {
    Label_0036_Outer:
        while (true) {
            final boolean b = false;
            final Signature[] array = null;
            while (true) {
                int n2 = 0;
            Label_0090:
                while (true) {
                    try {
                        final Signature[] signatures = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
                        boolean b2 = b;
                        if (signatures != null) {
                            final int length = signatures.length;
                            n2 = 0;
                            b2 = b;
                            if (n2 < length) {
                                if (signatures[n2].hashCode() != n) {
                                    break Label_0090;
                                }
                                b2 = true;
                            }
                        }
                        return b2;
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        j.c("unable to retrieve package name: " + ex);
                        final Signature[] signatures = array;
                        continue Label_0036_Outer;
                    }
                    break;
                }
                ++n2;
                continue;
            }
        }
    }
    
    public static boolean a(final Context context, final String s, final boolean b) {
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            boolean boolean1 = b;
            if (applicationInfo != null) {
                boolean1 = b;
                if (((PackageItemInfo)applicationInfo).metaData != null) {
                    boolean1 = ((PackageItemInfo)applicationInfo).metaData.getBoolean(s, b);
                }
            }
            return boolean1;
        }
        catch (PackageManager$NameNotFoundException ex) {
            j.c("package not found", (Throwable)ex);
            return b;
        }
    }
    
    public static String b(final Context context) {
        final PackageInfo a = a(context);
        if (a != null) {
            return a.versionName;
        }
        return "";
    }
    
    public static String c(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            j.c("Unable to retrieve installer package: Package manager not found.");
            return null;
        }
        final String packageName = context.getPackageName();
        if (packageName == null) {
            j.c("Unable to retrieve installer package: Package name no found.");
        }
        return packageManager.getInstallerPackageName(packageName);
    }
    
    public static int d(final Context context) {
        final PackageInfo a = a(context);
        if (a != null) {
            return a.versionCode;
        }
        return 0;
    }
}
