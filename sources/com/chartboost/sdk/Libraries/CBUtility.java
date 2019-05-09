package com.chartboost.sdk.Libraries;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Model.C1390e;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class CBUtility {
    private CBUtility() {
    }

    /* renamed from: a */
    public static float m3106a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /* renamed from: a */
    public static int m3108a(int i, Context context) {
        return Math.round(((float) i) * m3106a(context));
    }

    /* renamed from: a */
    public static float m3105a(float f, Context context) {
        return m3106a(context) * f;
    }

    /* renamed from: a */
    public static int m3107a() {
        Context context = C1410i.f2936m;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        int rotation = defaultDisplay.getRotation();
        int i = defaultDisplay.getWidth() == defaultDisplay.getHeight() ? context.getResources().getConfiguration().orientation != 2 ? 1 : 0 : defaultDisplay.getWidth() < defaultDisplay.getHeight() ? 1 : 0;
        if (!(rotation == 0 || rotation == 2)) {
            i = i == 0 ? 1 : 0;
        }
        if (i != 0) {
            switch (rotation) {
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                default:
                    return 0;
            }
        }
        switch (rotation) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 0;
            default:
                return 1;
        }
    }

    public static void throwProguardError(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            CBLogging.m3099b("CBUtility", "Chartboost library error! Have you used proguard on your application? Make sure to add the line '-keep class com.chartboost.sdk.** { *; }' to your proguard config file.");
        } else if (ex == null || ex.getMessage() == null) {
            CBLogging.m3099b("CBUtility", "Unknown Proguard error");
        } else {
            CBLogging.m3099b("CBUtility", ex.getMessage());
        }
    }

    /* renamed from: b */
    public static String m3114b() {
        String str = "%s %s %s";
        Object[] objArr = new Object[3];
        objArr[0] = "Chartboost-Android-SDK";
        objArr[1] = C1410i.f2927d == null ? "" : C1410i.f2927d;
        objArr[2] = "7.3.0";
        return String.format(str, objArr);
    }

    /* renamed from: c */
    public static boolean m3117c() {
        return m3119e() || m3120f() || m3121g();
    }

    /* renamed from: d */
    public static String m3118d() {
        SimpleDateFormat simpleDateFormat;
        if (VERSION.SDK_INT >= 18) {
            simpleDateFormat = new SimpleDateFormat("ZZZZ", Locale.US);
        } else {
            simpleDateFormat = new SimpleDateFormat("'GMT'ZZZZ", Locale.US);
        }
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date());
    }

    /* renamed from: e */
    private static boolean m3119e() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    /* renamed from: f */
    private static boolean m3120f() {
        return new File("/system/app/Superuser.apk").exists();
    }

    /* renamed from: g */
    private static boolean m3121g() {
        for (String file : new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static void m3110a(Activity activity, int i, C1390e c1390e) {
        if (activity != null && !m3112a(activity)) {
            if ((i == 1 && c1390e.f2817y && c1390e.f2783C) || (i == 0 && c1390e.f2797e && c1390e.f2800h)) {
                int a = m3107a();
                if (a == 0) {
                    activity.setRequestedOrientation(1);
                } else if (a == 2) {
                    activity.setRequestedOrientation(9);
                } else if (a == 1) {
                    activity.setRequestedOrientation(0);
                } else {
                    activity.setRequestedOrientation(8);
                }
            }
        }
    }

    /* renamed from: b */
    public static void m3115b(Activity activity, int i, C1390e c1390e) {
        if (activity != null && !m3112a(activity)) {
            if ((i == 1 && c1390e.f2817y && c1390e.f2783C) || (i == 0 && c1390e.f2797e && c1390e.f2800h)) {
                activity.setRequestedOrientation(-1);
            }
        }
    }

    /* renamed from: a */
    public static boolean m3113a(CBFramework cBFramework) {
        return C1410i.f2927d != null && C1410i.f2927d == cBFramework;
    }

    /* renamed from: a */
    public static boolean m3111a(int i) {
        return i == 0 || i == 2;
    }

    /* renamed from: b */
    public static boolean m3116b(int i) {
        return i == 1 || i == 3;
    }

    /* renamed from: a */
    public static ArrayList<File> m3109a(File file, boolean z) {
        if (file == null) {
            return null;
        }
        ArrayList<File> arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return arrayList;
        }
        for (File file2 : listFiles) {
            if (file2.isFile() && !file2.getName().equals(".nomedia")) {
                arrayList.add(file2);
            } else if (file2.isDirectory() && z) {
                arrayList.addAll(m3109a(file2, z));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static boolean m3112a(Activity activity) {
        if (activity == null || activity.getWindow() == null || activity.getWindow().getDecorView() == null || activity.getWindow().getDecorView().getBackground() == null) {
            return true;
        }
        if (VERSION.SDK_INT != 26 || activity.getApplicationInfo().targetSdkVersion <= 26 || activity.getWindow().getDecorView().getBackground().getAlpha() == 255) {
            return false;
        }
        return true;
    }
}
