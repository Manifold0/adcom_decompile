// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.util.UUID;
import android.content.Context;
import android.content.ContextWrapper;
import android.app.Activity;
import android.view.View;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import android.util.DisplayMetrics;
import android.os.Looper;

public final class zzdg
{
    private static final char[] zzsw;
    
    static {
        zzsw = "0123456789abcdef".toCharArray();
    }
    
    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
    
    public static long zza(final double n, final DisplayMetrics displayMetrics) {
        return Math.round(n / displayMetrics.density);
    }
    
    public static String zza(final Throwable t) {
        final StringWriter stringWriter = new StringWriter();
        zzazr.zza(t, new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
    
    public static boolean zza(final DisplayMetrics displayMetrics) {
        return displayMetrics != null && displayMetrics.density != 0.0f;
    }
    
    public static Activity zzc(View view) {
        int n = 0;
        final View rootView = view.getRootView();
        if (rootView != null) {
            view = rootView;
        }
        for (Context context = view.getContext(); context instanceof ContextWrapper && n < 10; context = ((ContextWrapper)context).getBaseContext(), ++n) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
        }
        return null;
    }
    
    public static String zzn(final String s) {
        String zza = s;
        if (s != null) {
            zza = s;
            if (s.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$")) {
                final UUID fromString = UUID.fromString(s);
                final byte[] array = new byte[16];
                final ByteBuffer wrap = ByteBuffer.wrap(array);
                wrap.putLong(fromString.getMostSignificantBits());
                wrap.putLong(fromString.getLeastSignificantBits());
                zza = zzbi.zza(array, true);
            }
        }
        return zza;
    }
    
    public static boolean zzo(final String s) {
        return s == null || s.isEmpty();
    }
}
