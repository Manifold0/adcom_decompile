package com.tapjoy.internal;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: com.tapjoy.internal.c */
public final class C2850c {
    /* renamed from: a */
    public static Activity m7313a(Context context) {
        for (Context context2 = context; context2 instanceof ContextWrapper; context2 = ((ContextWrapper) context2).getBaseContext()) {
            if (context2 instanceof Activity) {
                return (Activity) context2;
            }
        }
        return null;
    }
}
