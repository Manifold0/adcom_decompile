package com.moat.analytics.mobile.vng;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.C0870v.C0866b;
import java.util.Map;

public abstract class MoatFactory {
    public static MoatFactory create() {
        try {
            return new C0854n();
        } catch (Exception e) {
            C0849m.m1543a(e);
            return new C0866b();
        }
    }

    public abstract <T> T createCustomTracker(MoatPlugin<T> moatPlugin);

    public abstract NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map);

    public abstract NativeVideoTracker createNativeVideoTracker(String str);

    public abstract WebAdTracker createWebAdTracker(ViewGroup viewGroup);

    public abstract WebAdTracker createWebAdTracker(WebView webView);
}
