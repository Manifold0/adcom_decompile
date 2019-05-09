// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.ContextWrapper;
import android.app.Activity;
import android.content.Context;

public final class c
{
    public static Activity a(Context baseContext) {
        while (baseContext instanceof ContextWrapper) {
            if (baseContext instanceof Activity) {
                return (Activity)baseContext;
            }
            baseContext = ((ContextWrapper)baseContext).getBaseContext();
        }
        return null;
    }
}
