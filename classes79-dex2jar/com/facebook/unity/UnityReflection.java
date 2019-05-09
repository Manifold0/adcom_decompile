// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.unity;

import android.util.Log;
import android.app.Activity;

public class UnityReflection
{
    private static Class<?> unityPlayer;
    
    public static Activity GetUnityActivity() {
        try {
            if (UnityReflection.unityPlayer == null) {
                UnityReflection.unityPlayer = Class.forName("com.unity3d.player.UnityPlayer");
            }
            final Activity activity = (Activity)UnityReflection.unityPlayer.getField("currentActivity").get(null);
            if (activity == null) {
                Log.d(FB.TAG, "Current unity activity is null");
            }
            return activity;
        }
        catch (Exception ex) {
            Log.d(FB.TAG, ex.toString());
            return null;
        }
    }
    
    public static void SendMessage(final String s, final String s2, final String s3) {
        try {
            if (UnityReflection.unityPlayer == null) {
                UnityReflection.unityPlayer = Class.forName("com.unity3d.player.UnityPlayer");
            }
            UnityReflection.unityPlayer.getMethod("UnitySendMessage", String.class, String.class, String.class).invoke(UnityReflection.unityPlayer, s, s2, s3);
        }
        catch (Exception ex) {
            Log.d("TODO", ex.toString());
        }
    }
}
