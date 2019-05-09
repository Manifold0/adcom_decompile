// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.Looper;
import android.os.Handler;

public final class x
{
    private static Handler a;
    
    public static Handler a() {
        synchronized (x.class) {
            if (x.a == null) {
                x.a = new Handler(Looper.getMainLooper());
            }
            return x.a;
        }
    }
    
    public static bf a(final Handler handler) {
        return new bf() {
            @Override
            public final boolean a(final Runnable runnable) {
                return handler.post(runnable);
            }
        };
    }
}
