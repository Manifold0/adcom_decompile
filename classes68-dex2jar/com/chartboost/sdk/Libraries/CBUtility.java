// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import android.os.Build;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.Serializable;
import com.chartboost.sdk.Chartboost;
import android.os.Build$VERSION;
import com.chartboost.sdk.Model.e;
import android.app.Activity;
import java.util.Collection;
import java.util.ArrayList;
import java.io.File;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.i;
import android.content.Context;

public final class CBUtility
{
    private CBUtility() {
    }
    
    public static float a(final float n, final Context context) {
        return a(context) * n;
    }
    
    public static float a(final Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
    
    public static int a() {
        final int n = 1;
        final Context m = i.m;
        final Display defaultDisplay = ((WindowManager)m.getSystemService("window")).getDefaultDisplay();
        final int rotation = defaultDisplay.getRotation();
        boolean b;
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            if (m.getResources().getConfiguration().orientation != 2) {
                b = true;
            }
            else {
                b = false;
            }
        }
        else if (defaultDisplay.getWidth() < defaultDisplay.getHeight()) {
            b = true;
        }
        else {
            b = false;
        }
        int n2 = b ? 1 : 0;
        if (rotation != 0) {
            if (rotation == 2) {
                n2 = (b ? 1 : 0);
            }
            else if (!b) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
        }
        if (n2 != 0) {
            int n3 = n;
            switch (rotation) {
                default: {
                    n3 = 0;
                    return n3;
                }
                case 1: {
                    return n3;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    return 3;
                }
            }
        }
        else {
            switch (rotation) {
                default: {
                    return 1;
                }
                case 1: {
                    return 2;
                }
                case 2: {
                    return 3;
                }
                case 3: {
                    return 0;
                }
            }
        }
    }
    
    public static int a(final int n, final Context context) {
        return Math.round(n * a(context));
    }
    
    public static ArrayList<File> a(File file, final boolean b) {
        ArrayList<File> list;
        if (file == null) {
            list = null;
        }
        else {
            final ArrayList<File> list2 = new ArrayList<File>();
            final File[] listFiles = file.listFiles();
            list = list2;
            if (listFiles != null) {
                final int length = listFiles.length;
                int n = 0;
                while (true) {
                    list = list2;
                    if (n >= length) {
                        break;
                    }
                    file = listFiles[n];
                    if (file.isFile() && !file.getName().equals(".nomedia")) {
                        list2.add(file);
                    }
                    else if (file.isDirectory() && b) {
                        list2.addAll(a(file, b));
                    }
                    ++n;
                }
            }
        }
        return list;
    }
    
    public static void a(final Activity activity, int a, final e e) {
        if (activity == null || a(activity) || ((a != 1 || e.y || !e.C) && (a != 0 || !e.e || !e.h))) {
            return;
        }
        a = a();
        if (a == 0) {
            activity.setRequestedOrientation(1);
            return;
        }
        if (a == 2) {
            activity.setRequestedOrientation(9);
            return;
        }
        if (a == 1) {
            activity.setRequestedOrientation(0);
            return;
        }
        activity.setRequestedOrientation(8);
    }
    
    public static boolean a(final int n) {
        return n == 0 || n == 2;
    }
    
    public static boolean a(final Activity activity) {
        return activity == null || activity.getWindow() == null || activity.getWindow().getDecorView() == null || activity.getWindow().getDecorView().getBackground() == null || (Build$VERSION.SDK_INT == 26 && activity.getApplicationInfo().targetSdkVersion > 26 && activity.getWindow().getDecorView().getBackground().getAlpha() != 255);
    }
    
    public static boolean a(final Chartboost.CBFramework cbFramework) {
        return i.d != null && i.d == cbFramework;
    }
    
    public static String b() {
        Serializable d;
        if (i.d == null) {
            d = "";
        }
        else {
            d = i.d;
        }
        return String.format("%s %s %s", "Chartboost-Android-SDK", d, "7.3.0");
    }
    
    public static void b(final Activity activity, final int n, final e e) {
        if (activity != null && !a(activity) && ((n == 1 && e.y && e.C) || (n == 0 && e.e && e.h))) {
            activity.setRequestedOrientation(-1);
        }
    }
    
    public static boolean b(final int n) {
        return n == 1 || n == 3;
    }
    
    public static boolean c() {
        return e() || f() || g();
    }
    
    public static String d() {
        SimpleDateFormat simpleDateFormat;
        if (Build$VERSION.SDK_INT >= 18) {
            simpleDateFormat = new SimpleDateFormat("ZZZZ", Locale.US);
        }
        else {
            simpleDateFormat = new SimpleDateFormat("'GMT'ZZZZ", Locale.US);
        }
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date());
    }
    
    private static boolean e() {
        final String tags = Build.TAGS;
        return tags != null && tags.contains("test-keys");
    }
    
    private static boolean f() {
        return new File("/system/app/Superuser.apk").exists();
    }
    
    private static boolean g() {
        final String[] array = { "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su" };
        for (int length = array.length, i = 0; i < length; ++i) {
            if (new File(array[i]).exists()) {
                return true;
            }
        }
        return false;
    }
    
    public static void throwProguardError(final Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            CBLogging.b("CBUtility", "Chartboost library error! Have you used proguard on your application? Make sure to add the line '-keep class com.chartboost.sdk.** { *; }' to your proguard config file.");
            return;
        }
        if (ex != null && ex.getMessage() != null) {
            CBLogging.b("CBUtility", ex.getMessage());
            return;
        }
        CBLogging.b("CBUtility", "Unknown Proguard error");
    }
}
