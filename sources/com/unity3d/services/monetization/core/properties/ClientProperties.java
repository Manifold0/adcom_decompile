package com.unity3d.services.monetization.core.properties;

import com.unity3d.services.monetization.IUnityMonetizationListener;

public class ClientProperties {
    private static IUnityMonetizationListener listener;
    private static boolean monetizationEnabled;

    public static IUnityMonetizationListener getListener() {
        return listener;
    }

    public static void setListener(IUnityMonetizationListener listener) {
        listener = listener;
    }

    public static void setMonetizationEnabled(boolean monetizationEnabled) {
        monetizationEnabled = monetizationEnabled;
    }

    public static boolean isMonetizationEnabled() {
        return monetizationEnabled;
    }
}
