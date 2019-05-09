// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.app.Activity;

public class AdapterUtils
{
    public static int dpToPixels(final Activity activity, final int n) {
        return (int)TypedValue.applyDimension(1, (float)n, activity.getResources().getDisplayMetrics());
    }
    
    public static boolean isLargeScreen(final Activity activity) {
        final DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        final float n = displayMetrics.heightPixels / displayMetrics.density;
        final float n2 = displayMetrics.widthPixels / displayMetrics.density;
        return n > 720.0f && n2 >= 728.0f;
    }
}
