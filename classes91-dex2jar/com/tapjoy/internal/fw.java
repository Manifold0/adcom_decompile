// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.app.Activity;

public abstract class fw
{
    protected static fw a;
    
    public static void b(final Activity activity) {
        if (fw.a != null) {
            fw.a.a(activity);
        }
    }
    
    public abstract void a(final Activity p0);
}
