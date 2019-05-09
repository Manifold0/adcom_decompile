package com.kongregate.android.internal.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.TapjoyConstants;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: com.kongregate.android.internal.util.c */
public class C0545c {
    /* renamed from: a */
    public static final int f712a = 4;
    /* renamed from: b */
    private static Context f713b;
    /* renamed from: c */
    private static C0544a f714c;
    /* renamed from: d */
    private static String f715d;
    /* renamed from: e */
    private static Configuration f716e;
    /* renamed from: f */
    private static Display f717f;
    /* renamed from: g */
    private static final Set<String> f718g = new LinkedHashSet();

    /* renamed from: com.kongregate.android.internal.util.c$a */
    public enum C0544a {
        NEXUS_ONE,
        DROID,
        EMULATOR,
        XPERIA_PLAY,
        UNKNOWN
    }

    /* renamed from: a */
    public static void m619a(Context context) {
        f713b = context;
        f714c = C0545c.m631k();
        f715d = C0545c.m632l();
        f716e = context.getResources().getConfiguration();
        f717f = ((WindowManager) f713b.getSystemService("window")).getDefaultDisplay();
        boolean i = C0545c.m629i();
        boolean b = C0545c.m622b();
        boolean z = f714c == C0544a.XPERIA_PLAY;
        f718g.clear();
        if (i) {
            f718g.add("kb");
        }
        if (b) {
            f718g.add("tab");
        }
        if (z) {
            f718g.add("gpad");
        }
        C0562j.m756b("DeviceInfo: " + String.valueOf(f715d) + ", layout=" + f716e.screenLayout + ", gamepad=" + z + ", tablet=" + b + ", caps=" + C0545c.m630j());
        if (f715d != null) {
            C0562j.m753a("Device hash code: " + C0545c.m618a());
        }
    }

    /* renamed from: a */
    public static boolean m620a(String str) {
        if (StringUtils.m580a((CharSequence) str)) {
            return true;
        }
        for (Object contains : str.split("\\|")) {
            if (!f718g.contains(contains)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static int m618a() {
        return Math.abs(f715d.hashCode()) % 100;
    }

    /* renamed from: i */
    private static boolean m629i() {
        return f716e.keyboard != 1;
    }

    /* renamed from: j */
    private static String m630j() {
        String str = "";
        for (String str2 : f718g) {
            str = str + str2 + " ";
        }
        return str;
    }

    /* renamed from: b */
    public static boolean m622b() {
        return f717f.getWidth() >= 1000 || f717f.getHeight() >= 1000 || (f716e.screenLayout & 4) == 4 || (f716e.screenLayout & 3) == 3;
    }

    /* renamed from: k */
    private static C0544a m631k() {
        if (f713b == null) {
            throw new IllegalStateException("DeviceInfo.initialize must be called before DeviceInfo.getDevice");
        }
        String str = Build.PRODUCT;
        String str2 = Build.DEVICE;
        String str3 = Build.MODEL;
        C0562j.m756b("product: " + str + ", device: " + str2 + ", model: " + str3);
        if ("1".equals(System.getProperties().getProperty("ro.kernel.qemu", AppEventsConstants.EVENT_PARAM_VALUE_NO)) || "sdk".equals(str) || "google_sdk".equals(str) || (MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE.equalsIgnoreCase(str) && MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE.equalsIgnoreCase(str2))) {
            return C0544a.EMULATOR;
        }
        if (str.equalsIgnoreCase("passion") || str2.equalsIgnoreCase("nexus one")) {
            return C0544a.NEXUS_ONE;
        }
        if (str2.equalsIgnoreCase("milestone") || str3.equalsIgnoreCase("droid")) {
            return C0544a.DROID;
        }
        if ("R800x".equals(str2)) {
            return C0544a.XPERIA_PLAY;
        }
        return C0544a.UNKNOWN;
    }

    /* renamed from: l */
    private static String m632l() {
        String str = "";
        try {
            String str2;
            PackageManager packageManager = f713b.getPackageManager();
            String string = Secure.getString(f713b.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
            if (string != null) {
                str = str + string;
            }
            if (packageManager != null && packageManager.hasSystemFeature("android.hardware.wifi")) {
                WifiManager wifiManager = (WifiManager) f713b.getSystemService("wifi");
                if (wifiManager != null) {
                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                    if (connectionInfo != null) {
                        str2 = str + connectionInfo.getMacAddress();
                        if (StringUtils.m591d((CharSequence) str2)) {
                            return null;
                        }
                        return StringUtils.m589d(str2);
                    }
                }
            }
            str2 = str;
            if (StringUtils.m591d((CharSequence) str2)) {
                return null;
            }
            return StringUtils.m589d(str2);
        } catch (RuntimeException e) {
            C0562j.m759c("RuntimeException getting package info");
            return null;
        }
    }

    /* renamed from: c */
    public static String m623c() {
        return "build_id=" + StringUtils.m563a(Build.DISPLAY) + "&product=" + StringUtils.m563a(Build.PRODUCT) + "&device=" + StringUtils.m563a(Build.DEVICE) + "&manufacturer=" + StringUtils.m563a(Build.MANUFACTURER) + "&brand=" + StringUtils.m563a(Build.BRAND) + "&model=" + StringUtils.m563a(Build.MODEL) + "&hardware=" + StringUtils.m563a(Build.HARDWARE) + "&app_version=" + StringUtils.m563a(C0563k.m769b(f713b)) + "&app_version_code=" + StringUtils.m563a(Integer.toString(C0563k.m771d(f713b))) + "&android_version=" + StringUtils.m563a(VERSION.RELEASE);
    }

    /* renamed from: b */
    public static String m621b(Context context) {
        if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
            return ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getDeviceId();
        }
        return null;
    }

    /* renamed from: d */
    public static C0544a m624d() {
        return f714c;
    }

    /* renamed from: e */
    public static String m625e() {
        return f715d;
    }

    /* renamed from: f */
    public static boolean m626f() {
        return C0545c.m624d() == C0544a.EMULATOR;
    }

    /* renamed from: g */
    public static boolean m627g() {
        String str = Build.TAGS;
        if ((str == null || !str.contains("test-keys")) && !new File("/system/app/Superuser.apk").exists()) {
            return false;
        }
        return true;
    }

    /* renamed from: h */
    public static boolean m628h() {
        return !C0542a.m610c();
    }
}
