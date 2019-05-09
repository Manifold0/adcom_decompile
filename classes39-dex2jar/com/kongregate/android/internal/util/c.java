// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import android.net.wifi.WifiInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.provider.Settings$Secure;
import java.util.Iterator;
import java.io.File;
import android.os.Build$VERSION;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import java.util.LinkedHashSet;
import java.util.Set;
import android.view.Display;
import android.content.res.Configuration;
import android.content.Context;

public class c
{
    public static final int a = 4;
    private static Context b;
    private static a c;
    private static String d;
    private static Configuration e;
    private static Display f;
    private static final Set<String> g;
    
    static {
        g = new LinkedHashSet<String>();
    }
    
    public static int a() {
        return Math.abs(com.kongregate.android.internal.util.c.d.hashCode()) % 100;
    }
    
    public static void a(final Context b) {
        com.kongregate.android.internal.util.c.b = b;
        com.kongregate.android.internal.util.c.c = k();
        com.kongregate.android.internal.util.c.d = l();
        com.kongregate.android.internal.util.c.e = b.getResources().getConfiguration();
        com.kongregate.android.internal.util.c.f = ((WindowManager)com.kongregate.android.internal.util.c.b.getSystemService("window")).getDefaultDisplay();
        final boolean i = i();
        final boolean b2 = b();
        final boolean b3 = com.kongregate.android.internal.util.c.c == com.kongregate.android.internal.util.c.a.d;
        com.kongregate.android.internal.util.c.g.clear();
        if (i) {
            com.kongregate.android.internal.util.c.g.add("kb");
        }
        if (b2) {
            com.kongregate.android.internal.util.c.g.add("tab");
        }
        if (b3) {
            com.kongregate.android.internal.util.c.g.add("gpad");
        }
        j.b("DeviceInfo: " + String.valueOf(com.kongregate.android.internal.util.c.d) + ", layout=" + com.kongregate.android.internal.util.c.e.screenLayout + ", gamepad=" + b3 + ", tablet=" + b2 + ", caps=" + j());
        if (com.kongregate.android.internal.util.c.d != null) {
            j.a("Device hash code: " + a());
        }
    }
    
    public static boolean a(final String s) {
        if (!StringUtils.a((CharSequence)s)) {
            final String[] split = s.split("\\|");
            for (int length = split.length, i = 0; i < length; ++i) {
                if (!com.kongregate.android.internal.util.c.g.contains(split[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static String b(final Context context) {
        if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0) {
            return ((TelephonyManager)context.getSystemService("phone")).getDeviceId();
        }
        return null;
    }
    
    public static boolean b() {
        return com.kongregate.android.internal.util.c.f.getWidth() >= 1000 || com.kongregate.android.internal.util.c.f.getHeight() >= 1000 || (com.kongregate.android.internal.util.c.e.screenLayout & 0x4) == 0x4 || (com.kongregate.android.internal.util.c.e.screenLayout & 0x3) == 0x3;
    }
    
    public static String c() {
        return "build_id=" + StringUtils.a(Build.DISPLAY) + "&product=" + StringUtils.a(Build.PRODUCT) + "&device=" + StringUtils.a(Build.DEVICE) + "&manufacturer=" + StringUtils.a(Build.MANUFACTURER) + "&brand=" + StringUtils.a(Build.BRAND) + "&model=" + StringUtils.a(Build.MODEL) + "&hardware=" + StringUtils.a(Build.HARDWARE) + "&app_version=" + StringUtils.a(k.b(com.kongregate.android.internal.util.c.b)) + "&app_version_code=" + StringUtils.a(Integer.toString(k.d(com.kongregate.android.internal.util.c.b))) + "&android_version=" + StringUtils.a(Build$VERSION.RELEASE);
    }
    
    public static a d() {
        return com.kongregate.android.internal.util.c.c;
    }
    
    public static String e() {
        return com.kongregate.android.internal.util.c.d;
    }
    
    public static boolean f() {
        return d() == com.kongregate.android.internal.util.c.a.c;
    }
    
    public static boolean g() {
        final String tags = Build.TAGS;
        return (tags != null && tags.contains("test-keys")) || new File("/system/app/Superuser.apk").exists();
    }
    
    public static boolean h() {
        return !com.kongregate.android.internal.util.a.c();
    }
    
    private static boolean i() {
        return com.kongregate.android.internal.util.c.e.keyboard != 1;
    }
    
    private static String j() {
        final Iterator<String> iterator = com.kongregate.android.internal.util.c.g.iterator();
        String string = "";
        while (iterator.hasNext()) {
            string = string + iterator.next() + " ";
        }
        return string;
    }
    
    private static a k() {
        if (com.kongregate.android.internal.util.c.b == null) {
            throw new IllegalStateException("DeviceInfo.initialize must be called before DeviceInfo.getDevice");
        }
        final String product = Build.PRODUCT;
        final String device = Build.DEVICE;
        final String model = Build.MODEL;
        j.b("product: " + product + ", device: " + device + ", model: " + model);
        if ("1".equals(System.getProperties().getProperty("ro.kernel.qemu", "0")) || "sdk".equals(product) || "google_sdk".equals(product) || ("generic".equalsIgnoreCase(product) && "generic".equalsIgnoreCase(device))) {
            return com.kongregate.android.internal.util.c.a.c;
        }
        if (product.equalsIgnoreCase("passion") || device.equalsIgnoreCase("nexus one")) {
            return com.kongregate.android.internal.util.c.a.a;
        }
        if (device.equalsIgnoreCase("milestone") || model.equalsIgnoreCase("droid")) {
            return com.kongregate.android.internal.util.c.a.b;
        }
        if ("R800x".equals(device)) {
            return com.kongregate.android.internal.util.c.a.d;
        }
        return com.kongregate.android.internal.util.c.a.e;
    }
    
    private static String l() {
        while (true) {
            String s = "";
            while (true) {
                Label_0134: {
                    try {
                        final PackageManager packageManager = com.kongregate.android.internal.util.c.b.getPackageManager();
                        final String string = Settings$Secure.getString(com.kongregate.android.internal.util.c.b.getContentResolver(), "android_id");
                        if (string != null) {
                            s = "" + string;
                        }
                        if (packageManager == null || !packageManager.hasSystemFeature("android.hardware.wifi")) {
                            break Label_0134;
                        }
                        final WifiManager wifiManager = (WifiManager)com.kongregate.android.internal.util.c.b.getSystemService("wifi");
                        if (wifiManager == null) {
                            break Label_0134;
                        }
                        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                        if (connectionInfo == null) {
                            break Label_0134;
                        }
                        s += connectionInfo.getMacAddress();
                        if (StringUtils.d((CharSequence)s)) {
                            return StringUtils.d(s);
                        }
                    }
                    catch (RuntimeException ex) {
                        j.c("RuntimeException getting package info");
                        return null;
                    }
                    break;
                }
                continue;
            }
        }
        return null;
    }
    
    public enum a
    {
        a, 
        b, 
        c, 
        d, 
        e;
    }
}
