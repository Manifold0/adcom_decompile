package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

public final class ag {
    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static void m7158a(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
