// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.wrappers;

import com.google.android.gms.common.util.PlatformVersion;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class InstantApps
{
    private static Context zzhv;
    private static Boolean zzhw;
    
    @KeepForSdk
    public static boolean isInstantApp(final Context context) {
        synchronized (InstantApps.class) {
            final Context applicationContext = context.getApplicationContext();
            boolean b;
            if (InstantApps.zzhv != null && InstantApps.zzhw != null && InstantApps.zzhv == applicationContext) {
                b = InstantApps.zzhw;
            }
            else {
                InstantApps.zzhw = null;
                if (PlatformVersion.isAtLeastO()) {
                    InstantApps.zzhw = applicationContext.getPackageManager().isInstantApp();
                }
                else {
                    try {
                        context.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
                        InstantApps.zzhw = true;
                    }
                    catch (ClassNotFoundException ex) {
                        InstantApps.zzhw = false;
                    }
                }
                InstantApps.zzhv = applicationContext;
                b = InstantApps.zzhw;
            }
            return b;
        }
    }
}
