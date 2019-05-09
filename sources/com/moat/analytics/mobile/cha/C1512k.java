package com.moat.analytics.mobile.cha;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.cha.C1521p.C1480c;
import com.moat.analytics.mobile.cha.NoOp.C1477b;
import com.moat.analytics.mobile.cha.NoOp.C1478c;
import com.moat.analytics.mobile.cha.NoOp.C1479e;
import com.moat.analytics.mobile.cha.base.functional.Optional;
import java.lang.ref.WeakReference;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.cha.k */
final class C1512k extends MoatFactory {
    C1512k() throws C1518o {
        if (!((C1495f) MoatAnalytics.getInstance()).m3755()) {
            String str = "Failed to initialize MoatFactory";
            String str2 = str + ", SDK was not started";
            C1487a.m3715(3, "Factory", this, str2);
            C1487a.m3712("[ERROR] ", str2);
            throw new C1518o(str);
        }
    }

    public final WebAdTracker createWebAdTracker(@NonNull WebView webView) {
        try {
            final WeakReference weakReference = new WeakReference(webView);
            return (WebAdTracker) C1521p.m3846(new C1480c<WebAdTracker>(this) {
                /* renamed from: ˊ */
                private /* synthetic */ C1512k f3541;

                /* renamed from: ˋ */
                public final Optional<WebAdTracker> mo4353() {
                    WebView webView = (WebView) weakReference.get();
                    String str = "Attempting to create WebAdTracker for " + C1487a.m3714(webView);
                    C1487a.m3715(3, "Factory", this, str);
                    C1487a.m3712("[INFO] ", str);
                    return Optional.of(new C1542v(webView));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            C1518o.m3840(e);
            return new C1479e();
        }
    }

    public final WebAdTracker createWebAdTracker(@NonNull ViewGroup viewGroup) {
        try {
            final WeakReference weakReference = new WeakReference(viewGroup);
            return (WebAdTracker) C1521p.m3846(new C1480c<WebAdTracker>(this) {
                /* renamed from: ˏ */
                private /* synthetic */ C1512k f3540;

                /* renamed from: ˋ */
                public final Optional<WebAdTracker> mo4353() throws C1518o {
                    ViewGroup viewGroup = (ViewGroup) weakReference.get();
                    String str = "Attempting to create WebAdTracker for adContainer " + C1487a.m3714(viewGroup);
                    C1487a.m3715(3, "Factory", this, str);
                    C1487a.m3712("[INFO] ", str);
                    return Optional.of(new C1542v(viewGroup));
                }
            }, WebAdTracker.class);
        } catch (Exception e) {
            C1518o.m3840(e);
            return new C1479e();
        }
    }

    public final NativeDisplayTracker createNativeDisplayTracker(@NonNull View view, @NonNull final Map<String, String> map) {
        try {
            final WeakReference weakReference = new WeakReference(view);
            return (NativeDisplayTracker) C1521p.m3846(new C1480c<NativeDisplayTracker>(this) {
                /* renamed from: ॱ */
                private /* synthetic */ C1512k f3536;

                /* renamed from: ˋ */
                public final Optional<NativeDisplayTracker> mo4353() {
                    View view = (View) weakReference.get();
                    String str = "Attempting to create NativeDisplayTracker for " + C1487a.m3714(view);
                    C1487a.m3715(3, "Factory", this, str);
                    C1487a.m3712("[INFO] ", str);
                    return Optional.of(new C1522q(view, map));
                }
            }, NativeDisplayTracker.class);
        } catch (Exception e) {
            C1518o.m3840(e);
            return new C1478c();
        }
    }

    public final NativeVideoTracker createNativeVideoTracker(final String str) {
        try {
            return (NativeVideoTracker) C1521p.m3846(new C1480c<NativeVideoTracker>(this) {
                /* renamed from: ॱ */
                private /* synthetic */ C1512k f3538;

                /* renamed from: ˋ */
                public final Optional<NativeVideoTracker> mo4353() {
                    String str = "Attempting to create NativeVideoTracker";
                    C1487a.m3715(3, "Factory", this, str);
                    C1487a.m3712("[INFO] ", str);
                    return Optional.of(new C1527s(str));
                }
            }, NativeVideoTracker.class);
        } catch (Exception e) {
            C1518o.m3840(e);
            return new C1477b();
        }
    }

    public final <T> T createCustomTracker(C1483l<T> c1483l) {
        try {
            return c1483l.create();
        } catch (Exception e) {
            C1518o.m3840(e);
            return c1483l.createNoOp();
        }
    }
}
