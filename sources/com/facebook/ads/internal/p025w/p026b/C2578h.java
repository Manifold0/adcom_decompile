package com.facebook.ads.internal.p025w.p026b;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.util.Log;

/* renamed from: com.facebook.ads.internal.w.b.h */
public class C2578h {
    /* renamed from: a */
    public static boolean m6638a(Context context) {
        try {
            boolean z = ((RunningTaskInfo) ((ActivityManager) context.getSystemService("activity")).getRunningTasks(2).get(0)).topActivity.getClassName().equals("com.unity3d.player.UnityPlayerActivity") || C2578h.m6639a("com.unity3d.player.UnityPlayerActivity");
            Boolean valueOf = Boolean.valueOf(z);
            Log.d("IS_UNITY", Boolean.toString(valueOf.booleanValue()));
            return valueOf.booleanValue();
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m6639a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }
}
