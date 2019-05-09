// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.os.Looper;

public final class zzc
{
    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
