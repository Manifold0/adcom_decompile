// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import com.google.android.gms.common.wrappers.Wrappers;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.GoogleSignatureVerifier;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class UidVerifier
{
    private UidVerifier() {
    }
    
    @KeepForSdk
    public static boolean isGooglePlayServicesUid(final Context context, final int n) {
        if (uidHasPackageName(context, n, "com.google.android.gms")) {
            final PackageManager packageManager = context.getPackageManager();
            try {
                return GoogleSignatureVerifier.getInstance(context).isGooglePublicSignedPackage(packageManager.getPackageInfo("com.google.android.gms", 64));
            }
            catch (PackageManager$NameNotFoundException ex) {
                if (Log.isLoggable("UidVerifier", 3)) {
                    Log.d("UidVerifier", "Package manager can't find google play services package, defaulting to false");
                    return false;
                }
            }
        }
        return false;
    }
    
    @TargetApi(19)
    @KeepForSdk
    public static boolean uidHasPackageName(final Context context, final int n, final String s) {
        return Wrappers.packageManager(context).zzb(n, s);
    }
}
