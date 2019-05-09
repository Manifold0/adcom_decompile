package com.unity3d.services.banners;

import android.os.ConditionVariable;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import java.lang.reflect.Method;

public class BannerHide {
    private static ConditionVariable _waitHideStatus;

    public static synchronized boolean hide() throws NoSuchMethodException {
        boolean success;
        synchronized (BannerHide.class) {
            Method showCallback = BannerHide.class.getMethod("showCallback", new Class[]{CallbackStatus.class});
            _waitHideStatus = new ConditionVariable();
            WebViewApp.getCurrentApp().invokeMethod(ParametersKeys.WEB_VIEW, "hideBanner", showCallback, new Object[0]);
            success = _waitHideStatus.block((long) AdsProperties.getShowTimeout());
            _waitHideStatus = null;
        }
        return success;
    }

    public static void showCallback(CallbackStatus status) {
        if (_waitHideStatus != null && status.equals(CallbackStatus.OK)) {
            _waitHideStatus.open();
        }
    }
}
