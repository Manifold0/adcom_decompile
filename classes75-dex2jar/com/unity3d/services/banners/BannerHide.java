// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.banners;

import java.lang.reflect.Method;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import android.os.ConditionVariable;

public class BannerHide
{
    private static ConditionVariable _waitHideStatus;
    
    public static boolean hide() throws NoSuchMethodException {
        synchronized (BannerHide.class) {
            final Method method = BannerHide.class.getMethod("showCallback", CallbackStatus.class);
            BannerHide._waitHideStatus = new ConditionVariable();
            WebViewApp.getCurrentApp().invokeMethod("webview", "hideBanner", method, new Object[0]);
            final boolean block = BannerHide._waitHideStatus.block((long)AdsProperties.getShowTimeout());
            BannerHide._waitHideStatus = null;
            return block;
        }
    }
    
    public static void showCallback(final CallbackStatus callbackStatus) {
        if (BannerHide._waitHideStatus != null && callbackStatus.equals(CallbackStatus.OK)) {
            BannerHide._waitHideStatus.open();
        }
    }
}
