// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import android.util.Log;
import android.app.ActivityManager;
import android.app.ActivityManager$RunningTaskInfo;
import android.content.Context;

public class h
{
    public static boolean a(final Context context) {
        while (true) {
            while (true) {
                try {
                    if (!((ActivityManager)context.getSystemService("activity")).getRunningTasks(2).get(0).topActivity.getClassName().equals("com.unity3d.player.UnityPlayerActivity")) {
                        if (!a("com.unity3d.player.UnityPlayerActivity")) {
                            final boolean b = false;
                            final Boolean value = b;
                            Log.d("IS_UNITY", Boolean.toString(value));
                            return value;
                        }
                    }
                }
                catch (Throwable t) {
                    return false;
                }
                final boolean b = true;
                continue;
            }
        }
    }
    
    public static boolean a(final String s) {
        try {
            Class.forName(s);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
}
