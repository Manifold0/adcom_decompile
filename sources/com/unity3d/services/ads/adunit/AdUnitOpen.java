package com.unity3d.services.ads.adunit;

import android.os.ConditionVariable;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.services.ads.properties.AdsProperties;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.bridge.CallbackStatus;
import java.lang.reflect.Method;
import org.json.JSONObject;

public class AdUnitOpen {
    private static ConditionVariable _waitShowStatus;

    public static synchronized boolean open(String placementId, JSONObject options) throws NoSuchMethodException {
        boolean success;
        synchronized (AdUnitOpen.class) {
            Method showCallback = AdUnitOpen.class.getMethod("showCallback", new Class[]{CallbackStatus.class});
            _waitShowStatus = new ConditionVariable();
            WebViewApp.getCurrentApp().invokeMethod(ParametersKeys.WEB_VIEW, "show", showCallback, placementId, options);
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
