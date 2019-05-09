package com.moat.analytics.mobile.cha;

import android.os.Handler;
import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.view.View;
import com.moat.analytics.mobile.cha.C1487a.C1486d;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.cha.b */
abstract class C1490b extends C1489d {
    /* renamed from: ʻ */
    static final MoatAdEventType[] f3447 = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    /* renamed from: ʼ */
    final Map<MoatAdEventType, Integer> f3448;
    /* renamed from: ʼॱ */
    private final String f3449;
    /* renamed from: ˊॱ */
    WeakReference<View> f3450;
    /* renamed from: ˋॱ */
    private boolean f3451;
    /* renamed from: ˏॱ */
    private Map<String, String> f3452;
    /* renamed from: ͺ */
    private Double f3453;
    /* renamed from: ॱˊ */
    private VideoTrackerListener f3454;
    /* renamed from: ॱˋ */
    private final Set<MoatAdEventType> f3455;
    /* renamed from: ॱˎ */
    private final C1487a f3456 = new C1487a(C1492c.m3748(), C1486d.f3428);
    /* renamed from: ᐝ */
    final Handler f3457;

    /* renamed from: com.moat.analytics.mobile.cha.b$5 */
    class C14885 implements Runnable {
        /* renamed from: ˊ */
        private /* synthetic */ C1490b f3435;

        C14885(C1490b c1490b) {
            this.f3435 = c1490b;
        }

        public final void run() {
            try {
                C1487a.m3715(3, "BaseVideoTracker", this, "Shutting down.");
                C1487a ˊ = this.f3435.f3456;
                C1487a.m3715(3, "GlobalWebView", ˊ, "Cleaning up");
                ˊ.f3433.m3804();
                ˊ.f3433 = null;
                ˊ.f3431.destroy();
                ˊ.f3431 = null;
                this.f3435.f3454 = null;
            } catch (Exception e) {
                C1518o.m3840(e);
            }
        }
    }

    /* renamed from: ᐝ */
    abstract Map<String, Object> mo4379() throws C1518o;

    C1490b(String str) {
        super(null, false, true);
        C1487a.m3715(3, "BaseVideoTracker", this, "Initializing.");
        this.f3449 = str;
        try {
            super.m3730(this.f3456.f3431);
        } catch (C1518o e) {
            this.ॱ = e;
        }
        this.f3448 = new HashMap();
        this.f3455 = new HashSet();
        this.f3457 = new Handler();
        this.f3451 = false;
        this.f3453 = Double.valueOf(1.0d);
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.f3454 = videoTrackerListener;
    }

    public void removeVideoListener() {
        this.f3454 = null;
    }

    @CallSuper
    /* renamed from: ॱ */
    public boolean mo4373(Map<String, String> map, View view) {
        try {
            m3727();
            m3729();
            if (view == null) {
                C1487a.m3715(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.f3452 = map;
            this.f3450 = new WeakReference(view);
            mo4369();
            String format = String.format("trackVideoAd tracking ids: %s | view: %s", new Object[]{new JSONObject(map).toString(), C1487a.m3714(view)});
            C1487a.m3715(3, "BaseVideoTracker", this, format);
            C1487a.m3712("[SUCCESS] ", mo4374() + " " + format);
            if (this.ˊ == null) {
                return true;
            }
            this.ˊ.onTrackingStarted(m3721());
            return true;
        } catch (Exception e) {
            m3731("trackVideoAd", e);
            return false;
        }
    }

    public void changeTargetView(View view) {
        C1487a.m3715(3, "BaseVideoTracker", this, "changing view to " + C1487a.m3714(view));
        this.f3450 = new WeakReference(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* renamed from: ˋ */
    void mo4368(List<String> list) throws C1518o {
        if (this.f3452 == null) {
            list.add("Null adIds object");
        }
        if (list.isEmpty()) {
            super.mo4368(list);
            return;
        }
        throw new C1518o(TextUtils.join(" and ", list));
    }

    public void stopTracking() {
        try {
            super.stopTracking();
            m3740();
            if (this.f3454 != null) {
                this.f3454 = null;
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        Object obj = null;
        try {
            C1487a.m3715(3, "BaseVideoTracker", this, String.format("Received event: %s", new Object[]{mo4372(moatAdEvent).toString()}));
            C1487a.m3712("[SUCCESS] ", mo4374() + String.format(" Received event: %s", new Object[]{r2.toString()}));
            if (m3724() && this.ˎ != null) {
                this.ˎ.m3812(this.f3456.f3430, r2);
                if (!this.f3455.contains(moatAdEvent.f3418)) {
                    this.f3455.add(moatAdEvent.f3418);
                    if (this.f3454 != null) {
                        this.f3454.onVideoEventReported(moatAdEvent.f3418);
                    }
                }
            }
            MoatAdEventType moatAdEventType = moatAdEvent.f3418;
            if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
                obj = 1;
            }
            if (obj != null) {
                this.f3448.put(moatAdEventType, Integer.valueOf(1));
                if (this.ˎ != null) {
                    this.ˎ.m3806((C1489d) this);
                }
                m3740();
            }
        } catch (Exception e) {
            C1518o.m3840(e);
        }
    }

    /* renamed from: ॱˊ */
    final Double m3742() {
        return this.f3453;
    }

    /* renamed from: ˏ */
    final void mo4369() throws C1518o {
        super.changeTargetView((View) this.f3450.get());
        super.mo4369();
        Map ᐝ = mo4379();
        Integer num = (Integer) ᐝ.get("height");
        C1487a.m3715(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) ᐝ.get("width"), (Integer) ᐝ.get("duration")}));
        this.f3456.m3720(this.f3449, this.f3452, r3, num, r5);
    }

    /* renamed from: ˎ */
    JSONObject mo4372(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f3416.doubleValue())) {
            moatAdEvent.f3416 = this.f3453;
        }
        return new JSONObject(moatAdEvent.m3708());
    }

    /* renamed from: ˏॱ */
    final void m3740() {
        if (!this.f3451) {
            this.f3451 = true;
            this.f3457.postDelayed(new C14885(this), 500);
        }
    }

    /* renamed from: ˋॱ */
    final boolean m3737() {
        return this.f3448.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f3448.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f3448.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    /* renamed from: ˋ */
    static boolean m3733(Integer num, Integer num2) {
        return ((double) Math.abs(num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    public void setPlayerVolume(Double d) {
        Double valueOf = Double.valueOf(this.f3453.doubleValue() * C1526r.m3867());
        if (!d.equals(this.f3453)) {
            C1487a.m3715(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.f3453 = d;
            if (!valueOf.equals(Double.valueOf(this.f3453.doubleValue() * C1526r.m3867()))) {
                dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f3413, this.f3453));
            }
        }
    }

    /* renamed from: ˊॱ */
    final Double m3735() {
        return Double.valueOf(this.f3453.doubleValue() * C1526r.m3867());
    }
}
