package com.moat.analytics.mobile.iro;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.iro.C0794s.C0747a;
import com.moat.analytics.mobile.iro.NoOp.C0744a;
import com.moat.analytics.mobile.iro.NoOp.C0745b;
import com.moat.analytics.mobile.iro.NoOp.C0746c;
import com.moat.analytics.mobile.iro.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.iro.n */
final class C0784n extends MoatFactory {
    C0784n() throws C0785o {
        if (!((C0774j) MoatAnalytics.getInstance()).m1320()) {
            String str = "Failed to initialize MoatFactory";
            String str2 = str + ", SDK was not started";
            C0756b.m1234(3, "Factory", this, str2);
            C0756b.m1232("[ERROR] ", str2);
            throw new C0785o(str);
        }
    }

    public final WebAdTracker createWebAdTracker(@NonNull WebView webView) {
        try {
            final WeakReference weakReference = new WeakReference(webView);
            return (WebAdTracker) C0794s.m1382(new C0747a<WebAdTracker>(this) {
                /* renamed from: ˊ */
                private /* synthetic */ C0784n f1239;

                /* renamed from: ˏ */
                public final Optional<WebAdTracker> mo1625() {
                    WebView webView = (WebView) weakReference.get();
                    String str = "Attempting to create WebAdTracker for " + C0756b.m1236(webView);
                    C0756b.m1234(3, "Factory", this, str);
                    C0756b.m1232("[INFO] ", str);
                    return Optional.of(new C0806x(webView));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            C0785o.m1351(e);
            return new C0745b();
        }
    }

    public final WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
        try {
            final WeakReference weakReference = new WeakReference(viewGroup);
            return (WebAdTracker) C0794s.m1382(new C0747a<WebAdTracker>(this) {
                /* renamed from: ˋ */
                private /* synthetic */ C0784n f1242;

                /* renamed from: ˏ */
                public final Optional<WebAdTracker> mo1625() throws C0785o {
                    ViewGroup viewGroup = (ViewGroup) weakReference.get();
                    String str = "Attempting to create WebAdTracker for adContainer " + C0756b.m1236(viewGroup);
                    C0756b.m1234(3, "Factory", this, str);
                    C0756b.m1232("[INFO] ", str);
                    return Optional.of(new C0806x(viewGroup));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            C0785o.m1351(e);
            return new C0745b();
        }
    }

    public final NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull final Map<String, String> map) {
        try {
            final WeakReference weakReference = new WeakReference(view);
            return (NativeDisplayTracker) C0794s.m1382(new C0747a<NativeDisplayTracker>(this) {
                /* renamed from: ˎ */
                private /* synthetic */ C0784n f1245;

                /* renamed from: ˏ */
                public final Optional<NativeDisplayTracker> mo1625() {
                    View view = (View) weakReference.get();
                    String str = "Attempting to create NativeDisplayTracker for " + C0756b.m1236(view);
                    C0756b.m1234(3, "Factory", this, str);
                    C0756b.m1232("[INFO] ", str);
                    return Optional.of(new C0791r(view, map));
                }
            }, NativeDisplayTracker.class);
        } catch (Exception e) {
            C0785o.m1351(e);
            return new C0744a();
        }
    }

    public final NativeVideoTracker createNativeVideoTracker(final String str) {
        try {
            return (NativeVideoTracker) C0794s.m1382(new C0747a<NativeVideoTracker>(this) {
                /* renamed from: ˊ */
                private /* synthetic */ C0784n f1237;

                /* renamed from: ˏ */
                public final Optional<NativeVideoTracker> mo1625() {
                    String str = "Attempting to create NativeVideoTracker";
                    C0756b.m1234(3, "Factory", this, str);
                    C0756b.m1232("[INFO] ", str);
                    return Optional.of(new C0790q(str));
                }
            }, NativeVideoTracker.class);
        } catch (Exception e) {
            C0785o.m1351(e);
            return new C0746c();
        }
    }

    public final <T> T createCustomTracker(C0750m<T> c0750m) {
        try {
            return c0750m.create();
        } catch (Exception e) {
            C0785o.m1351(e);
            return c0750m.createNoOp();
        }
    }
}
