package com.moat.analytics.mobile.tjy;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;

public abstract class MoatFactory {
    public static MoatFactory create(Activity activity) {
        try {
            return new C2767v(activity);
        } catch (Exception e) {
            C2747a.m6882a(e);
            return new ak();
        }
    }

    public abstract Object createCustomTracker(ac acVar);

    public abstract NativeDisplayTracker createNativeDisplayTracker(View view, String str);

    public abstract NativeVideoTracker createNativeVideoTracker(String str);

    public abstract WebAdTracker createWebAdTracker(ViewGroup viewGroup);

    public abstract WebAdTracker createWebAdTracker(WebView webView);

    @Deprecated
    public abstract WebAdTracker createWebDisplayTracker(ViewGroup viewGroup);

    @Deprecated
    public abstract WebAdTracker createWebDisplayTracker(WebView webView);
}
