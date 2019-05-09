// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.wrappers.Wrappers;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;

public final class zzp
{
    private static Object sLock;
    @GuardedBy("sLock")
    private static boolean zzeo;
    private static String zzep;
    private static int zzeq;
    
    static {
        zzp.sLock = new Object();
    }
    
    public static String zzc(final Context context) {
        zze(context);
        return zzp.zzep;
    }
    
    public static int zzd(final Context context) {
        zze(context);
        return zzp.zzeq;
    }
    
    private static void zze(final Context context) {
        final Object sLock = zzp.sLock;
        // monitorenter(sLock)
        Label_0053: {
            String packageName;
            PackageManagerWrapper packageManagerWrapper;
            Bundle bundle;
            try {
                if (zzp.zzeo) {
                    return;
                }
                zzp.zzeo = true;
                packageName = context.getPackageName();
                final PackageManagerWrapper packageManager;
                packageManagerWrapper = (packageManager = Wrappers.packageManager(context));
                final String s = packageName;
                final int n = 128;
                final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(s, n);
                final Bundle metaData = applicationInfo.metaData;
                final Bundle metaData2 = metaData;
                if (metaData2 == null) {
                    return;
                }
                break Label_0053;
            }
            finally {
                final Throwable t;
                bundle = (Bundle)t;
            }
            // monitorexit(sLock)
            while (true) {
                try {
                    final PackageManagerWrapper packageManager = packageManagerWrapper;
                    final String s = packageName;
                    final int n = 128;
                    final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(s, n);
                    final Bundle metaData2;
                    final Bundle metaData = metaData2 = applicationInfo.metaData;
                    if (metaData2 == null) {
                        return;
                    }
                    zzp.zzep = bundle.getString("com.google.app.id");
                    zzp.zzeq = bundle.getInt("com.google.android.gms.version");
                }
                // monitorexit(sLock)
                catch (PackageManager$NameNotFoundException ex) {
                    Log.wtf("MetadataValueReader", "This should never happen.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
    }
}
