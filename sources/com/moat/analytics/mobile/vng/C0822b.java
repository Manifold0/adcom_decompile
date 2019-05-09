package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.moat.analytics.mobile.vng.C0845j.C0844a;
import com.moat.analytics.mobile.vng.p013a.p014a.C0819a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.vng.b */
abstract class C0822b {
    /* renamed from: a */
    C0845j f1344a;
    /* renamed from: b */
    final String f1345b;
    /* renamed from: c */
    final boolean f1346c;
    /* renamed from: d */
    boolean f1347d;
    /* renamed from: e */
    boolean f1348e;
    /* renamed from: f */
    private WeakReference<View> f1349f;
    /* renamed from: g */
    private WeakReference<WebView> f1350g;
    /* renamed from: h */
    private final C0886z f1351h;
    /* renamed from: i */
    private final boolean f1352i;

    C0822b(@Nullable View view, boolean z, boolean z2) {
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Initializing.");
        if (z) {
            this.f1345b = "m" + hashCode();
        } else {
            this.f1345b = "";
        }
        this.f1349f = new WeakReference(view);
        this.f1352i = z;
        this.f1346c = z2;
        this.f1347d = false;
        this.f1348e = false;
        this.f1351h = new C0886z();
    }

    /* renamed from: g */
    private void mo1664g() {
        C0819a.m1431a(this.f1350g);
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Attempting bridge installation.");
        if (this.f1350g.get() != null) {
            if (!(this.f1352i || this.f1346c)) {
                this.f1344a = new C0845j((WebView) this.f1350g.get(), C0844a.WEBVIEW);
            }
            C0858p.m1577a(3, "BaseTracker", (Object) this, "Bridge " + (this.f1344a.f1409a ? "" : "not ") + "installed.");
            return;
        }
        this.f1344a = null;
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Bridge not installed, WebView is null.");
    }

    /* renamed from: a */
    abstract String mo1662a();

    /* renamed from: a */
    void m1448a(WebView webView) {
        if (webView != null) {
            this.f1350g = new WeakReference(webView);
            if (this.f1344a == null) {
                mo1664g();
            }
            if (this.f1344a != null && this.f1344a.f1409a) {
                this.f1344a.m1520a(this);
            }
        }
    }

    /* renamed from: a */
    void m1449a(C0845j c0845j) {
        this.f1344a = c0845j;
    }

    /* renamed from: b */
    boolean m1450b() {
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Attempting to start impression.");
        if (this.f1348e) {
            C0858p.m1577a(3, "BaseTracker", (Object) this, "startTracking failed, tracker already started");
            C0858p.m1579a("[INFO] ", mo1662a() + " already started");
            return false;
        }
        boolean b = this.f1344a.m1524b(this);
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Impression " + (b ? "" : "not ") + "started.");
        if (!b) {
            return b;
        }
        this.f1347d = true;
        this.f1348e = true;
        return b;
    }

    /* renamed from: c */
    boolean m1451c() {
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Attempting to stop impression.");
        this.f1347d = false;
        boolean c = this.f1344a.m1526c(this);
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Impression tracking " + (c ? "" : "not ") + "stopped.");
        return c;
    }

    @CallSuper
    public void changeTargetView(View view) {
        C0858p.m1577a(3, "BaseTracker", (Object) this, "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        this.f1349f = new WeakReference(view);
    }

    /* renamed from: d */
    View m1452d() {
        return (View) this.f1349f.get();
    }

    /* renamed from: e */
    String m1453e() {
        return m1452d() != null ? m1452d().getClass().getSimpleName() + "@" + m1452d().hashCode() : "";
    }

    /* renamed from: f */
    String m1454f() {
        this.f1351h.m1659a(this.f1345b, m1452d());
        return this.f1351h.f1504a;
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    public void startTracking() {
        boolean z = false;
        try {
            C0858p.m1577a(3, "BaseTracker", (Object) this, "In startTracking method.");
            z = m1450b();
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Attempt to start tracking ad impression was " + (z ? "" : "un") + "successful.");
        C0858p.m1579a(z ? "[SUCCESS] " : "[ERROR] ", mo1662a() + " startTracking " + (z ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : ParametersKeys.FAILED) + " for " + m1453e());
    }

    public void stopTracking() {
        boolean z = false;
        try {
            C0858p.m1577a(3, "BaseTracker", (Object) this, "In stopTracking method.");
            z = m1451c();
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
        C0858p.m1577a(3, "BaseTracker", (Object) this, "Attempt to stop tracking ad impression was " + (z ? "" : "un") + "successful.");
        C0858p.m1579a(z ? "[SUCCESS] " : "[ERROR] ", mo1662a() + " stopTracking " + (z ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : ParametersKeys.FAILED) + " for " + m1453e());
    }
}
