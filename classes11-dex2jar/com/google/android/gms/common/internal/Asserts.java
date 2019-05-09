// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.util.Log;
import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Asserts
{
    private Asserts() {
        throw new AssertionError((Object)"Uninstantiable");
    }
    
    @KeepForSdk
    public static void checkMainThread(final String s) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            final String value = String.valueOf(Thread.currentThread());
            final String value2 = String.valueOf(Looper.getMainLooper().getThread());
            Log.e("Asserts", new StringBuilder(String.valueOf(value).length() + 57 + String.valueOf(value2).length()).append("checkMainThread: current thread ").append(value).append(" IS NOT the main thread ").append(value2).append("!").toString());
            throw new IllegalStateException(s);
        }
    }
    
    @KeepForSdk
    public static void checkNotMainThread(final String s) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            final String value = String.valueOf(Thread.currentThread());
            final String value2 = String.valueOf(Looper.getMainLooper().getThread());
            Log.e("Asserts", new StringBuilder(String.valueOf(value).length() + 56 + String.valueOf(value2).length()).append("checkNotMainThread: current thread ").append(value).append(" IS the main thread ").append(value2).append("!").toString());
            throw new IllegalStateException(s);
        }
    }
    
    @KeepForSdk
    public static void checkNotNull(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException("null reference");
        }
    }
    
    @KeepForSdk
    public static void checkNotNull(final Object o, final Object o2) {
        if (o == null) {
            throw new IllegalArgumentException(String.valueOf(o2));
        }
    }
    
    @KeepForSdk
    public static void checkNull(final Object o) {
        if (o != null) {
            throw new IllegalArgumentException("non-null reference");
        }
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
}
