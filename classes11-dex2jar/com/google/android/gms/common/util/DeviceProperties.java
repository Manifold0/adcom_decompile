// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.os.Build;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class DeviceProperties
{
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
    
    @KeepForSdk
    public static boolean isAuto(final Context context) {
        if (DeviceProperties.zzgt == null) {
            DeviceProperties.zzgt = (PlatformVersion.isAtLeastO() && context.getPackageManager().hasSystemFeature("android.hardware.type.automotive"));
        }
        return DeviceProperties.zzgt;
    }
    
    @KeepForSdk
    public static boolean isLatchsky(final Context context) {
        if (DeviceProperties.zzgr == null) {
            final PackageManager packageManager = context.getPackageManager();
            DeviceProperties.zzgr = (packageManager.hasSystemFeature("com.google.android.feature.services_updater") && packageManager.hasSystemFeature("cn.google.services"));
        }
        return DeviceProperties.zzgr;
    }
    
    @TargetApi(21)
    @KeepForSdk
    public static boolean isSidewinder(final Context context) {
        if (DeviceProperties.zzgq == null) {
            DeviceProperties.zzgq = (PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google"));
        }
        return DeviceProperties.zzgq;
    }
    
    @KeepForSdk
    public static boolean isTablet(final Resources resources) {
        final boolean b = false;
        if (resources == null) {
            return false;
        }
        if (DeviceProperties.zzgn == null) {
            int n;
            if ((resources.getConfiguration().screenLayout & 0xF) > 3) {
                n = 1;
            }
            else {
                n = 0;
            }
            boolean b2 = false;
            Label_0088: {
                if (n == 0) {
                    if (DeviceProperties.zzgo == null) {
                        final Configuration configuration = resources.getConfiguration();
                        DeviceProperties.zzgo = ((configuration.screenLayout & 0xF) <= 3 && configuration.smallestScreenWidthDp >= 600);
                    }
                    b2 = b;
                    if (!DeviceProperties.zzgo) {
                        break Label_0088;
                    }
                }
                b2 = true;
            }
            DeviceProperties.zzgn = b2;
        }
        return DeviceProperties.zzgn;
    }
    
    @KeepForSdk
    public static boolean isTv(final Context context) {
        if (DeviceProperties.zzgu == null) {
            final PackageManager packageManager = context.getPackageManager();
            DeviceProperties.zzgu = (packageManager.hasSystemFeature("com.google.android.tv") || packageManager.hasSystemFeature("android.hardware.type.television") || packageManager.hasSystemFeature("android.software.leanback"));
        }
        return DeviceProperties.zzgu;
    }
    
    @KeepForSdk
    public static boolean isUserBuild() {
        return "user".equals(Build.TYPE);
    }
    
    @TargetApi(20)
    @KeepForSdk
    public static boolean isWearable(final Context context) {
        if (DeviceProperties.zzgp == null) {
            DeviceProperties.zzgp = (PlatformVersion.isAtLeastKitKatWatch() && context.getPackageManager().hasSystemFeature("android.hardware.type.watch"));
        }
        return DeviceProperties.zzgp;
    }
    
    @TargetApi(26)
    @KeepForSdk
    public static boolean isWearableWithoutPlayStore(final Context context) {
        return isWearable(context) && (!PlatformVersion.isAtLeastN() || (isSidewinder(context) && !PlatformVersion.isAtLeastO()));
    }
    
    public static boolean zzf(final Context context) {
        if (DeviceProperties.zzgs == null) {
            DeviceProperties.zzgs = (context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded"));
        }
        return DeviceProperties.zzgs;
    }
}
