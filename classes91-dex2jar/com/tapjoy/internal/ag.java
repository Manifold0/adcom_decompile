// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.view.View;

public final class ag
{
    @SuppressLint({ "NewApi" })
    public static void a(final View view, final Drawable drawable) {
        if (Build$VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
            return;
        }
        view.setBackgroundDrawable(drawable);
    }
}
