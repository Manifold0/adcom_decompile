// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.zzc;
import android.os.Looper;
import android.os.Handler;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Preconditions
{
    private Preconditions() {
        throw new AssertionError((Object)"Uninstantiable");
    }
    
    @KeepForSdk
    public static void checkArgument(final boolean b) {
        if (!b) {
            throw new IllegalArgumentException();
        }
    }
    
    @KeepForSdk
    public static void checkArgument(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
    }
    
    @KeepForSdk
    public static void checkArgument(final boolean b, final String s, final Object... array) {
        if (!b) {
            throw new IllegalArgumentException(String.format(s, array));
        }
    }
    
    @KeepForSdk
    public static void checkHandlerThread(final Handler handler) {
        checkHandlerThread(handler, "Must be called on the handler thread");
    }
    
    @KeepForSdk
    public static void checkHandlerThread(final Handler handler, final String s) {
        if (Looper.myLooper() != handler.getLooper()) {
            throw new IllegalStateException(s);
        }
    }
    
    @KeepForSdk
    public static void checkMainThread(final String s) {
        if (!zzc.isMainThread()) {
            throw new IllegalStateException(s);
        }
    }
    
    @KeepForSdk
    public static String checkNotEmpty(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        return s;
    }
    
    @KeepForSdk
    public static String checkNotEmpty(final String s, final Object o) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return s;
    }
    
    @KeepForSdk
    public static void checkNotMainThread() {
        checkNotMainThread("Must not be called on the main application thread");
    }
    
    @KeepForSdk
    public static void checkNotMainThread(final String s) {
        if (zzc.isMainThread()) {
            throw new IllegalStateException(s);
        }
    }
    
    @NonNull
    @KeepForSdk
    public static <T> T checkNotNull(@Nullable final T t) {
        if (t == null) {
            throw new NullPointerException("null reference");
        }
        return t;
    }
    
    @NonNull
    @KeepForSdk
    public static <T> T checkNotNull(final T t, final Object o) {
        if (t == null) {
            throw new NullPointerException(String.valueOf(o));
        }
        return t;
    }
    
    @KeepForSdk
    public static int checkNotZero(final int n) {
        if (n == 0) {
            throw new IllegalArgumentException("Given Integer is zero");
        }
        return n;
    }
    
    @KeepForSdk
    public static int checkNotZero(final int n, final Object o) {
        if (n == 0) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return n;
    }
    
    @KeepForSdk
    public static long checkNotZero(final long n) {
        if (n == 0L) {
            throw new IllegalArgumentException("Given Long is zero");
        }
        return n;
    }
    
    @KeepForSdk
    public static long checkNotZero(final long n, final Object o) {
        if (n == 0L) {
            throw new IllegalArgumentException(String.valueOf(o));
        }
        return n;
    }
    
    @KeepForSdk
    public static void checkState(final boolean b) {
        if (!b) {
            throw new IllegalStateException();
        }
    }
    
    @KeepForSdk
    public static void checkState(final boolean b, final Object o) {
        if (!b) {
            throw new IllegalStateException(String.valueOf(o));
        }
    }
    
    @KeepForSdk
    public static void checkState(final boolean b, final String s, final Object... array) {
        if (!b) {
            throw new IllegalStateException(String.format(s, array));
        }
    }
}
