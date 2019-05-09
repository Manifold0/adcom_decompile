// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.common;

import android.os.Build$VERSION;
import android.support.annotation.RequiresApi;
import android.annotation.TargetApi;
import android.content.Context;

public final class zzg
{
    private static volatile boolean zziy;
    
    static {
        zzg.zziy = !zzam();
    }
    
    @TargetApi(24)
    @RequiresApi(24)
    public static Context getDeviceProtectedStorageContext(final Context context) {
        if (context.isDeviceProtectedStorage()) {
            return context;
        }
        return context.createDeviceProtectedStorageContext();
    }
    
    public static boolean zzam() {
        return Build$VERSION.SDK_INT >= 24;
    }
}
