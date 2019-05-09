// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.stats;

import android.content.ComponentName;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class LoggingConstants
{
    @KeepForSdk
    public static final String EXTRA_WAKE_LOCK_KEY = "WAKE_LOCK_KEY";
    private static int LOG_LEVEL_OFF;
    public static final ComponentName zzfg;
    private static int zzfh;
    private static int zzfi;
    private static int zzfj;
    private static int zzfk;
    private static int zzfl;
    private static int zzfm;
    private static int zzfn;
    
    static {
        zzfg = new ComponentName("com.google.android.gms", "com.google.android.gms.common.stats.GmsCoreStatsService");
        LoggingConstants.LOG_LEVEL_OFF = 0;
        LoggingConstants.zzfh = 1;
        LoggingConstants.zzfi = 2;
        LoggingConstants.zzfj = 4;
        LoggingConstants.zzfk = 8;
        LoggingConstants.zzfl = 16;
        LoggingConstants.zzfm = 32;
        LoggingConstants.zzfn = 1;
    }
    
    private LoggingConstants() {
    }
}
