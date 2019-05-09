package com.moat.analytics.mobile.cha;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.moat.analytics.mobile.cha.C1507j.C1506e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.moat.analytics.mobile.cha.d */
abstract class C1489d {
    /* renamed from: ʻ */
    private WeakReference<View> f3436;
    /* renamed from: ʼ */
    private final boolean f3437;
    /* renamed from: ʽ */
    final boolean f3438;
    /* renamed from: ˊ */
    TrackerListener f3439;
    /* renamed from: ˊॱ */
    private boolean f3440;
    /* renamed from: ˋ */
    final String f3441;
    /* renamed from: ˎ */
    C1507j f3442 = this.f3456.f3433;
    /* renamed from: ˏ */
    WeakReference<WebView> f3443;
    /* renamed from: ͺ */
    private boolean f3444;
    /* renamed from: ॱ */
    C1518o f3445 = null;
    /* renamed from: ᐝ */
    private final C1541u f3446;

    /* renamed from: ˋ */
    abstract String mo4374();

    C1489d(@Nullable View view, boolean z, boolean z2) {
        C1487a.m3715(3, "BaseTracker", this, "Initializing.");
        this.f3441 = z ? "m" + hashCode() : "";
        this.f3436 = new WeakReference(view);
        this.f3437 = z;
        this.f3438 = z2;
        this.f3440 = false;
        this.f3444 = false;
        this.f3446 = new C1541u();
    }

    public void setListener(TrackerListener trackerListener) {
        this.f3439 = trackerListener;
    }

    public void removeListener() {
        this.f3439 = null;
    }

    public void startTracking() {
        try {
            C1487a.m3715(3, "BaseTracker", this, "In startTracking method.");
            mo4369();
            if (this.f3439 != null) {
                this.f3439.onTrackingStarted("Tracking started on " + C1487a.m3714((View) this.f3436.get()));
            }
            String str = "startTracking succeeded for " + C1487a.m3714((View) this.f3436.get());
            C1487a.m3715(3, "BaseTracker", this, str);
            C1487a.m3712("[SUCCESS] ", mo4374() + " " + str);
        } catch (Exception e) {
            m3731("startTracking", e);
        }
    }

    @CallSuper
    public void stopTracking() {
        String str;
        StringBuilder append;
        String str2;
        Object obj = 1;
        try {
            C1487a.m3715(3, "BaseTracker", this, "In stopTracking method.");
            this.f3444 = true;
            if (this.f3442 != null) {
                this.f3442.m3806(this);
                C1487a.m3715(3, "BaseTracker", this, "Attempt to stop tracking ad impression was " + (obj == null ? "" : "un") + "successful.");
                str = obj == null ? "[SUCCESS] " : "[ERROR] ";
                append = new StringBuilder().append(mo4374()).append(" stopTracking ");
                if (obj == null) {
                    str2 = AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED;
                } else {
                    str2 = ParametersKeys.FAILED;
                }
                C1487a.m3712(str, append.append(str2).append(" for ").append(C1487a.m3714((View) this.f3436.get())).toString());
                if (this.f3439 != null) {
                    this.f3439.onTrackingStopped("");
                    this.f3439 = null;
                }
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
        obj = null;
        if (obj == null) {
        }
        C1487a.m3715(3, "BaseTracker", this, "Attempt to stop tracking ad impression was " + (obj == null ? "" : "un") + "successful.");
        if (obj == null) {
        }
        append = new StringBuilder().append(mo4374()).append(" stopTracking ");
        if (obj == null) {
            str2 = ParametersKeys.FAILED;
        } else {
            str2 = AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED;
        }
        C1487a.m3712(str, append.append(str2).append(" for ").append(C1487a.m3714((View) this.f3436.get())).toString());
        if (this.f3439 != null) {
            this.f3439.onTrackingStopped("");
            this.f3439 = null;
        }
    }

    @CallSuper
    public void changeTargetView(View view) {
        C1487a.m3715(3, "BaseTracker", this, "changing view to " + C1487a.m3714(view));
        this.f3436 = new WeakReference(view);
    }

    @Deprecated
    public void setActivity(Activity activity) {
    }

    @CallSuper
    /* renamed from: ˏ */
    void mo4369() throws C1518o {
        C1487a.m3715(3, "BaseTracker", this, "Attempting to start impression.");
        m3727();
        if (this.f3440) {
            throw new C1518o("Tracker already started");
        } else if (this.f3444) {
            throw new C1518o("Tracker already stopped");
        } else {
            mo4368(new ArrayList());
            if (this.f3442 != null) {
                this.f3442.m3807(this);
                this.f3440 = true;
                C1487a.m3715(3, "BaseTracker", this, "Impression started.");
                return;
            }
            C1487a.m3715(3, "BaseTracker", this, "Bridge is null, won't start tracking");
            throw new C1518o("Bridge is null");
        }
    }

    /* renamed from: ॱ */
    final void m3730(WebView webView) throws C1518o {
        if (webView != null) {
            this.f3443 = new WeakReference(webView);
            if (this.f3442 == null) {
                Object obj;
                if (this.f3437 || this.f3438) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    C1487a.m3715(3, "BaseTracker", this, "Attempting bridge installation.");
                    if (this.f3443.get() != null) {
                        this.f3442 = new C1507j((WebView) this.f3443.get(), C1506e.f3513);
                        C1487a.m3715(3, "BaseTracker", this, "Bridge installed.");
                    } else {
                        this.f3442 = null;
                        C1487a.m3715(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
                    }
                }
            }
            if (this.f3442 != null) {
                this.f3442.m3805(this);
            }
        }
    }

    /* renamed from: ˎ */
    final void m3727() throws C1518o {
        if (this.f3445 != null) {
            throw new C1518o("Tracker initialization failed: " + this.f3445.getMessage());
        }
    }

    /* renamed from: ˊ */
    final boolean m3724() {
        return this.f3440 && !this.f3444;
    }

    /* renamed from: ʼ */
    final View m3722() {
        return (View) this.f3436.get();
    }

    /* renamed from: ʽ */
    final String m3723() {
        this.f3446.m3904(this.f3441, (View) this.f3436.get());
        return this.f3446.f3634;
    }

    /* renamed from: ॱ */
    final void m3731(String str, Exception exception) {
        try {
            C1518o.m3840(exception);
            String ˎ = C1518o.m3839(str, exception);
            if (this.f3439 != null) {
                this.f3439.onTrackingFailedToStart(ˎ);
            }
            C1487a.m3715(3, "BaseTracker", this, ˎ);
            C1487a.m3712("[ERROR] ", mo4374() + " " + ˎ);
        } catch (Exception e) {
        }
    }

    /* renamed from: ॱ */
    final void m3729() throws C1518o {
        if (this.f3440) {
            throw new C1518o("Tracker already started");
        } else if (this.f3444) {
            throw new C1518o("Tracker already stopped");
        }
    }

    @CallSuper
    /* renamed from: ˋ */
    void mo4368(List<String> list) throws C1518o {
        if (((View) this.f3436.get()) == null && !this.f3438) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new C1518o(TextUtils.join(" and ", list));
        }
    }

    /* renamed from: ʻ */
    final String m3721() {
        return C1487a.m3714((View) this.f3436.get());
    }
}
