// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.os.SystemClock;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.PowerManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

public final class zza
{
    private static final IntentFilter filter;
    private static long zzgv;
    private static float zzgw;
    
    static {
        filter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        zza.zzgw = Float.NaN;
    }
    
    @TargetApi(20)
    public static int zzg(final Context context) {
        final int n = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, zza.filter);
        int intExtra;
        if (registerReceiver == null) {
            intExtra = 0;
        }
        else {
            intExtra = registerReceiver.getIntExtra("plugged", 0);
        }
        final boolean b = (intExtra & 0x7) != 0x0;
        final PowerManager powerManager = (PowerManager)context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        boolean b2;
        if (PlatformVersion.isAtLeastKitKatWatch()) {
            b2 = powerManager.isInteractive();
        }
        else {
            b2 = powerManager.isScreenOn();
        }
        int n2;
        if (b2) {
            n2 = 2;
        }
        else {
            n2 = 0;
        }
        int n3;
        if (b) {
            n3 = n;
        }
        else {
            n3 = 0;
        }
        return n2 | n3;
    }
    
    public static float zzh(final Context context) {
        synchronized (zza.class) {
            float n;
            if (SystemClock.elapsedRealtime() - zza.zzgv < 60000L && !Float.isNaN(zza.zzgw)) {
                n = zza.zzgw;
            }
            else {
                final Intent registerReceiver = context.getApplicationContext().registerReceiver((BroadcastReceiver)null, zza.filter);
                if (registerReceiver != null) {
                    zza.zzgw = registerReceiver.getIntExtra("level", -1) / (float)registerReceiver.getIntExtra("scale", -1);
                }
                zza.zzgv = SystemClock.elapsedRealtime();
                n = zza.zzgw;
            }
            return n;
        }
    }
}
