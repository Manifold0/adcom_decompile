// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.config.GservicesValue;
import com.google.android.gms.common.internal.GmsLogger;

public final class zzh
{
    private static final GmsLogger zzik;
    private static final GservicesValue<Boolean> zzil;
    
    static {
        zzik = new GmsLogger("Games");
        zzil = GservicesValue.value("games.play_games_dogfood", false);
    }
    
    public static void e(final String s, final String s2) {
        zzh.zzik.e(s, s2);
    }
    
    public static void e(final String s, final String s2, final Throwable t) {
        zzh.zzik.e(s, s2, t);
    }
    
    public static void i(final String s, final String s2, final Throwable t) {
        zzh.zzik.i(s, s2, t);
    }
    
    public static void w(final String s, final String s2) {
        zzh.zzik.w(s, s2);
    }
    
    public static void w(final String s, final String s2, final Throwable t) {
        zzh.zzik.w(s, s2, t);
    }
}
