package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;

public final class zza {
    private static final IntentFilter filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long zzgv;
    private static float zzgw = Float.NaN;

    @TargetApi(20)
    public static int zzg(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        int i2;
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, filter);
        if (((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        boolean isInteractive;
        int i3;
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            isInteractive = powerManager.isInteractive();
        } else {
            isInteractive = powerManager.isScreenOn();
        }
        if (isInteractive) {
            i3 = 2;
        } else {
            i3 = 0;
        }
        if (i2 == 0) {
            i = 0;
        }
        return i3 | i;
    }

    public static synchronized float zzh(Context context) {
        float f;
        synchronized (zza.class) {
            if (SystemClock.elapsedRealtime() - zzgv >= 60000 || Float.isNaN(zzgw)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver(null, filter);
                if (registerReceiver != null) {
                    zzgw = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                zzgv = SystemClock.elapsedRealtime();
                f = zzgw;
            } else {
                f = zzgw;
            }
        }
        return f;
    }
}
