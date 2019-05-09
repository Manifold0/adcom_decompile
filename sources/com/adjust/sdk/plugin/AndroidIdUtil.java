package com.adjust.sdk.plugin;

import android.content.Context;
import android.provider.Settings.Secure;
import com.tapjoy.TapjoyConstants;

public class AndroidIdUtil {
    public static String getAndroidId(Context context) {
        return Secure.getString(context.getContentResolver(), TapjoyConstants.TJC_ANDROID_ID);
    }
}
