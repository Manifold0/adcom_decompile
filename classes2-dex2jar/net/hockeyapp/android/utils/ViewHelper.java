// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.graphics.drawable.Drawable;

public class ViewHelper
{
    public static Drawable getGradient() {
        return (Drawable)new GradientDrawable(GradientDrawable$Orientation.TOP_BOTTOM, new int[] { -16777216, 0 });
    }
}
