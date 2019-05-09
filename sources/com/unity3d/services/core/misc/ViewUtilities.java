package com.unity3d.services.core.misc;

import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import com.unity3d.services.core.log.DeviceLog;

public class ViewUtilities {
    public static void removeViewFromParent(View view) {
        if (view != null && view.getParent() != null) {
            try {
                ((ViewGroup) view.getParent()).removeView(view);
            } catch (Exception e) {
                DeviceLog.exception("Error while removing view from it's parent", e);
            }
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        Class<View> cl = View.class;
        String methodName = "setBackground";
        if (VERSION.SDK_INT < 16) {
            methodName = "setBackgroundDrawable";
        }
        try {
            cl.getMethod(methodName, new Class[]{Drawable.class}).invoke(view, new Object[]{drawable});
        } catch (Exception e) {
            DeviceLog.exception("Couldn't run" + methodName, e);
        }
    }
}
