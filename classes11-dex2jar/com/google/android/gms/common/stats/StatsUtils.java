// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.stats;

import android.support.annotation.Nullable;
import java.util.List;
import android.text.TextUtils;
import android.os.Process;
import android.os.PowerManager$WakeLock;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class StatsUtils
{
    @KeepForSdk
    public static String getEventKey(final Context context, final Intent intent) {
        return String.valueOf((long)System.identityHashCode(context) << 32 | (long)System.identityHashCode(intent));
    }
    
    @KeepForSdk
    public static String getEventKey(final PowerManager$WakeLock powerManager$WakeLock, final String s) {
        final String value = String.valueOf(String.valueOf((long)Process.myPid() << 32 | (long)System.identityHashCode(powerManager$WakeLock)));
        String s2 = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            s2 = "";
        }
        final String value2 = String.valueOf(s2);
        if (value2.length() != 0) {
            return value.concat(value2);
        }
        return new String(value);
    }
    
    @Nullable
    static List<String> zza(@Nullable final List<String> list) {
        List<String> list2 = list;
        if (list != null) {
            list2 = list;
            if (list.size() == 1) {
                list2 = list;
                if ("com.google.android.gms".equals(list.get(0))) {
                    list2 = null;
                }
            }
        }
        return list2;
    }
    
    @Nullable
    static String zzi(final String s) {
        String s2 = s;
        if ("com.google.android.gms".equals(s)) {
            s2 = null;
        }
        return s2;
    }
}
