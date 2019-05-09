// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.misc;

import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import com.unity3d.services.core.log.DeviceLog;
import android.view.ViewGroup;
import android.view.View;

public class ViewUtilities
{
    public static void removeViewFromParent(final View view) {
        if (view == null || view.getParent() == null) {
            return;
        }
        try {
            ((ViewGroup)view.getParent()).removeView(view);
        }
        catch (Exception ex) {
            DeviceLog.exception("Error while removing view from it's parent", ex);
        }
    }
    
    public static void setBackground(final View view, final Drawable drawable) {
        String s = "setBackground";
        if (Build$VERSION.SDK_INT < 16) {
            s = "setBackgroundDrawable";
        }
        try {
            View.class.getMethod(s, Drawable.class).invoke(view, drawable);
        }
        catch (Exception ex) {
            DeviceLog.exception("Couldn't run" + s, ex);
        }
    }
}
