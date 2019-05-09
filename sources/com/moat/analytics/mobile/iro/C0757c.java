package com.moat.analytics.mobile.iro;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.moat.analytics.mobile.iro.C0765f.C0764b;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.moat.analytics.mobile.iro.c */
abstract class C0757c {
    /* renamed from: ʻ */
    private WeakReference<View> f1136;
    /* renamed from: ʼ */
    private final boolean f1137;
    /* renamed from: ʽ */
    private boolean f1138;
    /* renamed from: ˊ */
    TrackerListener f1139;
    /* renamed from: ˊॱ */
    private final C0810y f1140;
    /* renamed from: ˋ */
    C0785o f1141 = null;
    /* renamed from: ˎ */
    final String f1142;
    /* renamed from: ˏ */
    C0765f f1143 = this.f1158.f1130;
    /* renamed from: ˏॱ */
    private boolean f1144;
    /* renamed from: ॱ */
    WeakReference<WebView> f1145;
    /* renamed from: ᐝ */
    final boolean f1146;

    /* renamed from: ˊ */
    abstract String mo1647();

    C0757c(@Nullable View view, boolean z, boolean z2) {
        C0756b.m1234(3, "BaseTracker", this, "Initializing.");
        this.f1142 = z ? "m" + hashCode() : "";
        this.f1136 = new WeakReference(view);
        this.f1137 = z;
        this.f1146 = z2;
        this.f1138 = false;
        this.f1144 = false;
        this.f1140 = new C0810y();
    }

    public void setListener(TrackerListener trackerListener) {
        this.f1139 = trackerListener;
    }

    public void removeListener() {
        this.f1139 = null;
    }

    public void startTracking() {
        try {
            C0756b.m1234(3, "BaseTracker", this, "In startTracking method.");
            mo1640();
            if (this.f1139 != null) {
                this.f1139.onTrackingStarted("Tracking started on " + C0756b.m1236((View) this.f1136.get()));
            }
            String str = "startTracking succeeded for " + C0756b.m1236((View) this.f1136.get());
            C0756b.m1234(3, "BaseTracker", this, str);
            C0756b.m1232("[SUCCESS] ", mo1647() + " " + str);
        } catch (Exception e) {
            m1248("startTracking", e);
        }
    }

    @CallSuper
    public void stopTracking() {
        String str;
        StringBuilder append;
        String str2;
        Object obj = 1;
        try {
            C0756b.m1234(3, "BaseTracker", this, "In stopTracking method.");
            this.f1144 = true;
            if (this.f1143 != null) {
                this.f1143.m1288(this);
                C0756b.m1234(3, "BaseTracker", this, "Attempt to stop tracking ad impression was " + (obj == null ? "" : "un") + "successful.");
                str = obj == null ? "[SUCCESS] " : "[ERROR] ";
                append = new StringBuilder().append(mo1647()).append(" stopTracking ");
                if (obj == null) {
                    str2 = AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED;
                } else {
                    str2 = ParametersKeys.FAILED;
                }
                C0756b.m1232(str, append.append(str2).append(" for ").append(C0756b.m1236((View) this.f1136.get())).toString());
                if (this.f1139 != null) {
                    this.f1139.onTrackingStopped("");
                    this.f1139 = null;
                }
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
        obj = null;
        if (obj == null) {
        }
        C0756b.m1234(3, "BaseTracker", this, "Attempt to stop tracking ad impression was " + (obj == null ? "" : "un") + "successful.");
        if (obj == null) {
        }
        append = new StringBuilder().append(mo1647()).append(" stopTracking ");
        if (obj == null) {
            str2 = ParametersKeys.FAILED;
        } else {
            str2 = AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED;
        }
        C0756b.m1232(str, append.append(str2).append(" for ").append(C0756b.m1236((View) this.f1136.get())).toString());
        if (this.f1139 != null) {
            this.f1139.onTrackingStopped("");
            this.f1139 = null;
        }
    }

    @CallSuper
    public void changeTargetView(View view) {
        C0756b.m1234(3, "BaseTracker", this, "changing view to " + C0756b.m1236(view));
        this.f1136 = new WeakReference(view);
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    @CallSuper
    /* renamed from: ˎ */
    void mo1640() throws C0785o {
        C0756b.m1234(3, "BaseTracker", this, "Attempting to start impression.");
        m1243();
        if (this.f1138) {
            throw new C0785o("Tracker already started");
        } else if (this.f1144) {
            throw new C0785o("Tracker already stopped");
        } else {
            mo1641(new ArrayList());
            if (this.f1143 != null) {
                this.f1143.m1290(this);
                this.f1138 = true;
                C0756b.m1234(3, "BaseTracker", this, "Impression started.");
                return;
            }
            C0756b.m1234(3, "BaseTracker", this, "Bridge is null, won't start tracking");
            throw new C0785o("Bridge is null");
        }
    }

    /* renamed from: ˋ */
    final void m1244(WebView webView) throws C0785o {
        if (webView != null) {
            this.f1145 = new WeakReference(webView);
            if (this.f1143 == null) {
                Object obj;
                if (this.f1137 || this.f1146) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    C0756b.m1234(3, "BaseTracker", this, "Attempting bridge installation.");
                    if (this.f1145.get() != null) {
                        this.f1143 = new C0765f((WebView) this.f1145.get(), C0764b.f1166);
                        C0756b.m1234(3, "BaseTracker", this, "Bridge installed.");
                    } else {
                        this.f1143 = null;
                        C0756b.m1234(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
                    }
                }
            }
            if (this.f1143 != null) {
                this.f1143.m1287(this);
            }
        }
    }

    /* renamed from: ˋ */
    final void m1243() throws C0785o {
        if (this.f1141 != null) {
            throw new C0785o("Tracker initialization failed: " + this.f1141.getMessage());
        }
    }

    /* renamed from: ॱ */
    final boolean m1249() {
        return this.f1138 && !this.f1144;
    }

    /* renamed from: ʻ */
    final View m1239() {
        return (View) this.f1136.get();
    }

    /* renamed from: ʼ */
    final String m1240() {
        this.f1140.m1419(this.f1142, (View) this.f1136.get());
        return this.f1140.f1322;
    }

    /* renamed from: ॱ */
    final void m1248(String str, Exception exception) {
        try {
            C0785o.m1351(exception);
            String ॱ = C0785o.m1350(str, exception);
            if (this.f1139 != null) {
                this.f1139.onTrackingFailedToStart(ॱ);
            }
            C0756b.m1234(3, "BaseTracker", this, ॱ);
            C0756b.m1232("[ERROR] ", mo1647() + " " + ॱ);
        } catch (Exception e) {
        }
    }

    /* renamed from: ˏ */
    final void m1246() throws C0785o {
        if (this.f1138) {
            throw new C0785o("Tracker already started");
        } else if (this.f1144) {
            throw new C0785o("Tracker already stopped");
        }
    }

    @CallSuper
    /* renamed from: ˏ */
    void mo1641(List<String> list) throws C0785o {
        if (((View) this.f1136.get()) == null && !this.f1146) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new C0785o(TextUtils.join(" and ", list));
        }
    }

    /* renamed from: ʽ */
    final String m1241() {
        return C0756b.m1236((View) this.f1136.get());
    }
}
