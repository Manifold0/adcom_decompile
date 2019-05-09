package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class DeviceProperties {
    private static Boolean zzgn;
    private static Boolean zzgo;
    private static Boolean zzgp;
    private static Boolean zzgq;
    private static Boolean zzgr;
    private static Boolean zzgs;
    private static Boolean zzgt;
    private static Boolean zzgu;

    private DeviceProperties() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @com.google.android.gms.common.annotation.KeepForSdk
    public static boolean isTablet(android.content.res.Resources r5) {
        /*
        r4 = 3;
        r1 = 1;
        r2 = 0;
        if (r5 != 0) goto L_0x0006;
    L_0x0005:
        return r2;
    L_0x0006:
        r0 = zzgn;
        if (r0 != 0) goto L_0x0041;
    L_0x000a:
        r0 = r5.getConfiguration();
        r0 = r0.screenLayout;
        r0 = r0 & 15;
        if (r0 <= r4) goto L_0x0048;
    L_0x0014:
        r0 = r1;
    L_0x0015:
        if (r0 != 0) goto L_0x003a;
    L_0x0017:
        r0 = zzgo;
        if (r0 != 0) goto L_0x0032;
    L_0x001b:
        r0 = r5.getConfiguration();
        r3 = r0.screenLayout;
        r3 = r3 & 15;
        if (r3 > r4) goto L_0x004a;
    L_0x0025:
        r0 = r0.smallestScreenWidthDp;
        r3 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        if (r0 < r3) goto L_0x004a;
    L_0x002b:
        r0 = r1;
    L_0x002c:
        r0 = java.lang.Boolean.valueOf(r0);
        zzgo = r0;
    L_0x0032:
        r0 = zzgo;
        r0 = r0.booleanValue();
        if (r0 == 0) goto L_0x003b;
    L_0x003a:
        r2 = r1;
    L_0x003b:
        r0 = java.lang.Boolean.valueOf(r2);
        zzgn = r0;
    L_0x0041:
        r0 = zzgn;
        r2 = r0.booleanValue();
        goto L_0x0005;
    L_0x0048:
        r0 = r2;
        goto L_0x0015;
    L_0x004a:
        r0 = r2;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.DeviceProperties.isTablet(android.content.res.Resources):boolean");
    }

    @TargetApi(20)
    @KeepForSdk
    public static boolean isWearable(Context context) {
        if (zzgp == null) {
            boolean z = PlatformVersion.isAtLeastKitKatWatch() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
            zzgp = Boolean.valueOf(z);
        }
        return zzgp.booleanValue();
    }

    @TargetApi(26)
    @KeepForSdk
    public static boolean isWearableWithoutPlayStore(Context context) {
        return isWearable(context) && (!PlatformVersion.isAtLeastN() || (isSidewinder(context) && !PlatformVersion.isAtLeastO()));
    }

    @TargetApi(21)
    @KeepForSdk
    public static boolean isSidewinder(Context context) {
        if (zzgq == null) {
            boolean z = PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google");
            zzgq = Boolean.valueOf(z);
        }
        return zzgq.booleanValue();
    }

    @KeepForSdk
    public static boolean isLatchsky(Context context) {
        if (zzgr == null) {
            PackageManager packageManager = context.getPackageManager();
            boolean z = packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services");
            zzgr = Boolean.valueOf(z);
        }
        return zzgr.booleanValue();
    }

    public static boolean zzf(Context context) {
        if (zzgs == null) {
            boolean z = context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded");
            zzgs = Boolean.valueOf(z);
        }
        return zzgs.booleanValue();
    }

    @KeepForSdk
    public static boolean isAuto(Context context) {
        if (zzgt == null) {
            boolean z = PlatformVersion.isAtLeastO() && context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
            zzgt = Boolean.valueOf(z);
        }
        return zzgt.booleanValue();
    }

    @KeepForSdk
    public static boolean isTv(Context context) {
        if (zzgu == null) {
            PackageManager packageManager = context.getPackageManager();
            boolean z = packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback");
            zzgu = Boolean.valueOf(z);
        }
        return zzgu.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }
}
