// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageManager$NameNotFoundException;
import java.security.MessageDigest;
import android.content.pm.PackageInfo;
import com.google.android.gms.common.wrappers.Wrappers;
import android.annotation.TargetApi;
import com.google.android.gms.internal.common.zzg;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class AndroidUtilsLight
{
    private static volatile int zzgf;
    
    static {
        AndroidUtilsLight.zzgf = -1;
    }
    
    @Deprecated
    @TargetApi(24)
    @KeepForSdk
    public static Context getDeviceProtectedStorageContext(final Context context) {
        Context deviceProtectedStorageContext = context;
        if (zzg.zzam()) {
            deviceProtectedStorageContext = zzg.getDeviceProtectedStorageContext(context);
        }
        return deviceProtectedStorageContext;
    }
    
    @KeepForSdk
    public static byte[] getPackageCertificateHashBytes(final Context context, final String s) throws PackageManager$NameNotFoundException {
        final PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(s, 64);
        if (packageInfo.signatures != null && packageInfo.signatures.length == 1) {
            final MessageDigest zzj = zzj("SHA1");
            if (zzj != null) {
                return zzj.digest(packageInfo.signatures[0].toByteArray());
            }
        }
        return null;
    }
    
    public static MessageDigest zzj(final String s) {
        for (int i = 0; i < 2; ++i) {
            try {
                final MessageDigest instance = MessageDigest.getInstance(s);
                if (instance != null) {
                    return instance;
                }
            }
            catch (NoSuchAlgorithmException ex) {}
        }
        return null;
    }
}
