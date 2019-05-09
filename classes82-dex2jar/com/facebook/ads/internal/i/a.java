// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.i;

import android.support.annotation.Nullable;
import android.content.Context;

public final class a
{
    @Nullable
    private static Context a;
    
    @Nullable
    public static Context a() {
        return com.facebook.ads.internal.i.a.a;
    }
    
    public static void a(final Context context) {
        if (context == null) {
            return;
        }
        com.facebook.ads.internal.i.a.a = context.getApplicationContext();
    }
}
