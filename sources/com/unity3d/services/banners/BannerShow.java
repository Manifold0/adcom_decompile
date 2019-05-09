package com.unity3d.services.banners;

import android.os.ConditionVariable;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import java.lang.reflect.Method;

public class BannerShow {
    private static ConditionVariable _waitShowStatus;

    public static synchronized boolean show(String placementId) throws NoSuchMethodException {
        boolean success;
        synchronized (BannerShow.class) {
            Method showCallback = BannerShow.class.getMethod("showCallback", new Class[]{CallbackStatus.class});
            _waitShowStatus = new ConditionVariable();
            WebViewApp.getCurrentApp().invokeMethod(ParametersKeys.WEB_VIEW, "showBanner", showCallback, placementId);
            success = _waitShowStatus.block((long) AdsProperties.getShowTimeout());
            _waitShowStatus = null;
        }
        return success;
    }

    public static void showCallback(CallbackStatus status) {
        if (_waitShowStatus != null && status.equals(CallbackStatus.OK)) {
            _waitShowStatus.open();
        }
    }
}
