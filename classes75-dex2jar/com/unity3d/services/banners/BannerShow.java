// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners;

import java.lang.reflect.Method;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import android.os.ConditionVariable;

public class BannerShow
{
    private static ConditionVariable _waitShowStatus;
    
    public static boolean show(final String s) throws NoSuchMethodException {
        synchronized (BannerShow.class) {
            final Method method = BannerShow.class.getMethod("showCallback", CallbackStatus.class);
            BannerShow._waitShowStatus = new ConditionVariable();
            WebViewApp.getCurrentApp().invokeMethod("webview", "showBanner", method, s);
            final boolean block = BannerShow._waitShowStatus.block((long)AdsProperties.getShowTimeout());
            BannerShow._waitShowStatus = null;
            return block;
        }
    }
    
    public static void showCallback(final CallbackStatus callbackStatus) {
        if (BannerShow._waitShowStatus != null && callbackStatus.equals(CallbackStatus.OK)) {
            BannerShow._waitShowStatus.open();
        }
    }
}
