// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.text.TextUtils;
import com.google.android.gms.common.util.zza;
import java.util.List;
import java.util.Arrays;
import android.util.Log;
import android.os.Parcelable;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class WakeLockTracker
{
    private static WakeLockTracker zzgc;
    private static Boolean zzgd;
    @VisibleForTesting
    private static boolean zzge;
    
    static {
        WakeLockTracker.zzgc = new WakeLockTracker();
        WakeLockTracker.zzge = false;
    }
    
    @KeepForSdk
    public static WakeLockTracker getInstance() {
        return WakeLockTracker.zzgc;
    }
    
    private static void zza(final Context context, final WakeLockEvent wakeLockEvent) {
        try {
            context.startService(new Intent().setComponent(LoggingConstants.zzfg).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", (Parcelable)wakeLockEvent));
        }
        catch (Exception ex) {
            Log.wtf("WakeLockTracker", (Throwable)ex);
        }
    }
    
    private static boolean zzw() {
        if (WakeLockTracker.zzgd == null) {
            WakeLockTracker.zzgd = false;
        }
        return WakeLockTracker.zzgd;
    }
    
    @KeepForSdk
    public void registerAcquireEvent(final Context context, final Intent intent, final String s, final String s2, final String s3, final int n, final String s4) {
        this.registerEvent(context, intent.getStringExtra("WAKE_LOCK_KEY"), 7, s, s2, s3, n, Arrays.asList(s4));
    }
    
    @KeepForSdk
    public void registerDeadlineEvent(final Context context, final String s, final String s2, final String s3, final int n, final List<String> list, final boolean b, final long n2) {
        if (!zzw()) {
            return;
        }
        zza(context, new WakeLockEvent(System.currentTimeMillis(), 16, s, n, StatsUtils.zza(list), null, n2, zza.zzg(context), s2, StatsUtils.zzi(context.getPackageName()), zza.zzh(context), 0L, s3, b));
    }
    
    @KeepForSdk
    public void registerEvent(final Context context, final String s, final int n, final String s2, final String s3, final String s4, final int n2, final List<String> list) {
        this.registerEvent(context, s, n, s2, s3, s4, n2, list, 0L);
    }
    
    @KeepForSdk
    public void registerEvent(final Context context, final String s, final int n, final String s2, final String s3, final String s4, final int n2, final List<String> list, final long n3) {
        if (zzw()) {
            if (TextUtils.isEmpty((CharSequence)s)) {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "missing wakeLock key. ".concat(value);
                }
                else {
                    concat = new String("missing wakeLock key. ");
                }
                Log.e("WakeLockTracker", concat);
                return;
            }
            if (7 == n || 8 == n || 10 == n || 11 == n) {
                zza(context, new WakeLockEvent(System.currentTimeMillis(), n, s2, n2, StatsUtils.zza(list), s, SystemClock.elapsedRealtime(), zza.zzg(context), s3, StatsUtils.zzi(context.getPackageName()), zza.zzh(context), n3, s4, false));
            }
        }
    }
    
    @KeepForSdk
    public void registerReleaseEvent(final Context context, final Intent intent) {
        this.registerEvent(context, intent.getStringExtra("WAKE_LOCK_KEY"), 8, null, null, null, 0, null);
    }
}
