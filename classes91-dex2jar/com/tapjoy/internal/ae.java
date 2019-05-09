// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.pm.Signature;
import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.Context;

public final class ae
{
    public static int a(final Context context) {
        return b(context.getPackageManager(), context.getPackageName());
    }
    
    public static String a(final PackageManager packageManager, final String s) {
        try {
            return packageManager.getPackageInfo(s, 0).versionName;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static int b(final PackageManager packageManager, final String s) {
        try {
            return packageManager.getPackageInfo(s, 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return 0;
        }
    }
    
    @SuppressLint({ "NewApi" })
    public static long c(final PackageManager packageManager, final String s) {
        if (Build$VERSION.SDK_INT >= 9) {
            try {
                final long firstInstallTime = packageManager.getPackageInfo(s, 0).firstInstallTime;
                if (firstInstallTime > 0L) {
                    return firstInstallTime;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
        return 0L;
    }
    
    public static String d(final PackageManager packageManager, final String s) {
        try {
            return ct.b(packageManager.getApplicationInfo(s, 0).sourceDir);
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static Signature[] e(final PackageManager packageManager, final String s) {
        try {
            return packageManager.getPackageInfo(s, 64).signatures;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
}
