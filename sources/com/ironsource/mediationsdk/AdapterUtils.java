package com.ironsource.mediationsdk;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class AdapterUtils {
    public static boolean isLargeScreen(Activity activity) {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        return ((float) displayMetrics.heightPixels) / displayMetrics.density > 720.0f && ((float) displayMetrics.widthPixels) / displayMetrics.density >= 728.0f;
    }

    public static int dpToPixels(Activity activity, int dpSize) {
        return (int) TypedValue.applyDimension(1, (float) dpSize, activity.getResources().getDisplayMetrics());
    }
}
