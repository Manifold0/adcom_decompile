// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.common.wrappers.Wrappers;
import android.os.Bundle;
import android.content.pm.PackageInfo;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ClientLibraryUtils
{
    private ClientLibraryUtils() {
    }
    
    @KeepForSdk
    public static int getClientVersion(final Context context, final String s) {
        final PackageInfo zzb = zzb(context, s);
        if (zzb != null && zzb.applicationInfo != null) {
            final Bundle metaData = zzb.applicationInfo.metaData;
            if (metaData != null) {
                return metaData.getInt("com.google.android.gms.version", -1);
            }
        }
        return -1;
    }
    
    @KeepForSdk
    public static boolean isPackageSide() {
        return false;
    }
    
    @Nullable
    private static PackageInfo zzb(final Context context, final String s) {
        try {
            return Wrappers.packageManager(context).getPackageInfo(s, 128);
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static boolean zzc(final Context context, final String s) {
        boolean b = false;
        "com.google.android.gms".equals(s);
        try {
            if ((Wrappers.packageManager(context).getApplicationInfo(s, 0).flags & 0x200000) != 0x0) {
                b = true;
            }
            return b;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
}
