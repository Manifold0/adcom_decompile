// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk.plugin;

import android.provider.Settings$Secure;
import android.content.Context;

public class AndroidIdUtil
{
    public static String getAndroidId(final Context context) {
        return Settings$Secure.getString(context.getContentResolver(), "android_id");
    }
}
