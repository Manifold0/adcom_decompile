package com.kongregate.android.internal.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

/* renamed from: com.kongregate.android.internal.util.k */
public class C0563k {
    /* renamed from: a */
    public static PackageInfo m766a(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageInfo(context.getPackageName(), 0);
            }
        } catch (Throwable e) {
            C0562j.m762d("package not found: " + context.getPackageName(), e);
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m767a(Context context, int i) {
        Signature[] signatureArr = null;
        try {
            signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
        } catch (NameNotFoundException e) {
            C0562j.m759c("unable to retrieve package name: " + e);
        }
        if (signatureArr == null) {
            return false;
        }
        for (Signature hashCode : signatureArr) {
            if (hashCode.hashCode() == i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m768a(Context context, String str, boolean z) {
        try {
            PackageItemInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                z = applicationInfo.metaData.getBoolean(str, z);
            }
        } catch (Throwable e) {
            C0562j.m760c("package not found", e);
        }
        return z;
    }

    /* renamed from: b */
    public static String m769b(Context context) {
        PackageInfo a = C0563k.m766a(context);
        return a != null ? a.versionName : "";
    }

    /* renamed from: c */
    public static String m770c(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            C0562j.m759c("Unable to retrieve installer package: Package manager not found.");
            return null;
        }
        String packageName = context.getPackageName();
        if (packageName == null) {
            C0562j.m759c("Unable to retrieve installer package: Package name no found.");
        }
        return packageManager.getInstallerPackageName(packageName);
    }

    /* renamed from: d */
    public static int m771d(Context context) {
        PackageInfo a = C0563k.m766a(context);
        return a != null ? a.versionCode : 0;
    }
}
