// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Context;
import javax.annotation.concurrent.GuardedBy;
import android.os.DropBoxManager;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class CrashUtils
{
    private static final String[] zzgg;
    private static DropBoxManager zzgh;
    private static boolean zzgi;
    private static int zzgj;
    @GuardedBy("CrashUtils.class")
    private static int zzgk;
    @GuardedBy("CrashUtils.class")
    private static int zzgl;
    
    static {
        zzgg = new String[] { "android.", "com.android.", "dalvik.", "java.", "javax." };
        CrashUtils.zzgh = null;
        CrashUtils.zzgi = false;
        CrashUtils.zzgj = -1;
        CrashUtils.zzgk = 0;
        CrashUtils.zzgl = 0;
    }
    
    @KeepForSdk
    public static boolean addDynamiteErrorToDropBox(final Context context, final Throwable t) {
        return zza(context, t, 536870912);
    }
    
    private static boolean zza(final Context context, final Throwable t, final int n) {
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(t);
            return false;
        }
        catch (Exception ex) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", (Throwable)ex);
            return false;
        }
    }
}
