// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import java.lang.reflect.Method;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import org.json.JSONObject;
import android.os.ConditionVariable;

public class AdUnitOpen
{
    private static ConditionVariable _waitShowStatus;
    
    public static boolean open(final String s, final JSONObject jsonObject) throws NoSuchMethodException {
        synchronized (AdUnitOpen.class) {
            final Method method = AdUnitOpen.class.getMethod("showCallback", CallbackStatus.class);
            AdUnitOpen._waitShowStatus = new ConditionVariable();
            WebViewApp.getCurrentApp().invokeMethod("webview", "show", method, s, jsonObject);
            final boolean block = AdUnitOpen._waitShowStatus.block((long)AdsProperties.getShowTimeout());
            AdUnitOpen._waitShowStatus = null;
            return block;
        }
    }
    
    public static void showCallback(final CallbackStatus callbackStatus) {
        if (AdUnitOpen._waitShowStatus != null && callbackStatus.equals(CallbackStatus.OK)) {
            AdUnitOpen._waitShowStatus.open();
        }
    }
}
