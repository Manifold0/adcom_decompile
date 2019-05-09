package net.hockeyapp.android.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.v4.view.ViewCompat;

public class ViewHelper {
    public static Drawable getGradient() {
        return new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{ViewCompat.MEASURED_STATE_MASK, 0});
    }
}
