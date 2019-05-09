package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Build.VERSION;

public final class ae {
    /* renamed from: a */
    public static String m7148a(PackageManager packageManager, String str) {
        try {
            return packageManager.getPackageInfo(str, 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static int m7147a(Context context) {
        return m7149b(context.getPackageManager(), context.getPackageName());
    }

    /* renamed from: b */
    public static int m7149b(PackageManager packageManager, String str) {
        int i = 0;
        try {
            return packageManager.getPackageInfo(str, 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: c */
    public static long m7150c(PackageManager packageManager, String str) {
        if (VERSION.SDK_INT >= 9) {
            try {
                long j = packageManager.getPackageInfo(str, 0).firstInstallTime;
                if (j > 0) {
                    return j;
                }
            } catch (NameNotFoundException e) {
            }
        }
        return 0;
    }

    /* renamed from: d */
    public static String m7151d(PackageManager packageManager, String str) {
        try {
            return ct.m7338b(packageManager.getApplicationInfo(str, 0).sourceDir);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /* renamed from: e */
    public static Signature[] m7152e(PackageManager packageManager, String str) {
        try {
            return packageManager.getPackageInfo(str, 64).signatures;
        } catch (NameNotFoundException e) {
            return null;
        }
    }
}
