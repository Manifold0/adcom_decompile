package com.moat.analytics.mobile.iro;

import android.os.Handler;
import android.support.annotation.CallSuper;
import android.text.TextUtils;
import android.view.View;
import com.moat.analytics.mobile.iro.C0756b.C0755a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.iro.d */
abstract class C0759d extends C0757c {
    /* renamed from: ʽ */
    static final MoatAdEventType[] f1148 = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    /* renamed from: ʻ */
    final Map<MoatAdEventType, Integer> f1149;
    /* renamed from: ʼ */
    WeakReference<View> f1150;
    /* renamed from: ˊॱ */
    final Handler f1151;
    /* renamed from: ˋॱ */
    private Double f1152;
    /* renamed from: ˏॱ */
    private Map<String, String> f1153;
    /* renamed from: ͺ */
    private boolean f1154;
    /* renamed from: ॱˊ */
    private VideoTrackerListener f1155;
    /* renamed from: ॱˋ */
    private final Set<MoatAdEventType> f1156;
    /* renamed from: ॱˎ */
    private final String f1157;
    /* renamed from: ᐝॱ */
    private final C0756b f1158 = new C0756b(C0752a.m1226(), C0755a.f1127);

    /* renamed from: com.moat.analytics.mobile.iro.d$4 */
    class C07584 implements Runnable {
        /* renamed from: ˊ */
        private /* synthetic */ C0759d f1147;

        C07584(C0759d c0759d) {
            this.f1147 = c0759d;
        }

        public final void run() {
            try {
                C0756b.m1234(3, "BaseVideoTracker", this, "Shutting down.");
                C0756b ˎ = this.f1147.f1158;
                C0756b.m1234(3, "GlobalWebView", ˎ, "Cleaning up");
                ˎ.f1130.m1283();
                ˎ.f1130 = null;
                ˎ.f1132.destroy();
                ˎ.f1132 = null;
                this.f1147.f1155 = null;
            } catch (Exception e) {
                C0785o.m1351(e);
            }
        }
    }

    /* renamed from: ˊॱ */
    abstract Map<String, Object> mo1648() throws C0785o;

    C0759d(String str) {
        super(null, false, true);
        C0756b.m1234(3, "BaseVideoTracker", this, "Initializing.");
        this.f1157 = str;
        try {
            super.m1244(this.f1158.f1132);
        } catch (C0785o e) {
            this.ˋ = e;
        }
        this.f1149 = new HashMap();
        this.f1156 = new HashSet();
        this.f1151 = new Handler();
        this.f1154 = false;
        this.f1152 = Double.valueOf(1.0d);
    }

    public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        this.f1155 = videoTrackerListener;
    }

    public void removeVideoListener() {
        this.f1155 = null;
    }

    @CallSuper
    /* renamed from: ˋ */
    public boolean mo1644(Map<String, String> map, View view) {
        try {
            m1243();
            m1246();
            if (view == null) {
                C0756b.m1234(3, "BaseVideoTracker", this, "trackVideoAd received null video view instance");
            }
            this.f1153 = map;
            this.f1150 = new WeakReference(view);
            mo1640();
            String format = String.format("trackVideoAd tracking ids: %s | view: %s", new Object[]{new JSONObject(map).toString(), C0756b.m1236(view)});
            C0756b.m1234(3, "BaseVideoTracker", this, format);
            C0756b.m1232("[SUCCESS] ", mo1647() + " " + format);
            if (this.ˊ == null) {
                return true;
            }
            this.ˊ.onTrackingStarted(m1241());
            return true;
        } catch (Exception e) {
            m1248("trackVideoAd", e);
            return false;
        }
    }

    public void changeTargetView(View view) {
        C0756b.m1234(3, "BaseVideoTracker", this, "changing view to " + C0756b.m1236(view));
        this.f1150 = new WeakReference(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* renamed from: ˏ */
    void mo1641(List<String> list) throws C0785o {
        if (this.f1153 == null) {
            list.add("Null adIds object");
        }
        if (list.isEmpty()) {
            super.mo1641(list);
            return;
        }
        throw new C0785o(TextUtils.join(" and ", list));
    }

    public void stopTracking() {
        try {
            super.stopTracking();
            m1260();
            if (this.f1155 != null) {
                this.f1155 = null;
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        Object obj = null;
        try {
            C0756b.m1234(3, "BaseVideoTracker", this, String.format("Received event: %s", new Object[]{mo1643(moatAdEvent).toString()}));
            C0756b.m1232("[SUCCESS] ", mo1647() + String.format(" Received event: %s", new Object[]{r2.toString()}));
            if (m1249() && this.ˏ != null) {
                this.ˏ.m1285(this.f1158.f1129, r2);
                if (!this.f1156.contains(moatAdEvent.f1113)) {
                    this.f1156.add(moatAdEvent.f1113);
                    if (this.f1155 != null) {
                        this.f1155.onVideoEventReported(moatAdEvent.f1113);
                    }
                }
            }
            MoatAdEventType moatAdEventType = moatAdEvent.f1113;
            if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
                obj = 1;
            }
            if (obj != null) {
                this.f1149.put(moatAdEventType, Integer.valueOf(1));
                if (this.ˏ != null) {
                    this.ˏ.m1288((C0757c) this);
                }
                m1260();
            }
        } catch (Exception e) {
            C0785o.m1351(e);
        }
    }

    /* renamed from: ˏॱ */
    final Double m1258() {
        return this.f1152;
    }

    /* renamed from: ˎ */
    final void mo1640() throws C0785o {
        super.changeTargetView((View) this.f1150.get());
        super.mo1640();
        Map ˊॱ = mo1648();
        Integer num = (Integer) ˊॱ.get("height");
        C0756b.m1234(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) ˊॱ.get("width"), (Integer) ˊॱ.get("duration")}));
        this.f1158.m1238(this.f1157, this.f1153, r3, num, r5);
    }

    /* renamed from: ˋ */
    JSONObject mo1643(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f1112.doubleValue())) {
            moatAdEvent.f1112 = this.f1152;
        }
        return new JSONObject(moatAdEvent.m1218());
    }

    /* renamed from: ॱˋ */
    final void m1260() {
        if (!this.f1154) {
            this.f1154 = true;
            this.f1151.postDelayed(new C07584(this), 500);
        }
    }

    /* renamed from: ͺ */
    final boolean m1259() {
        return this.f1149.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f1149.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f1149.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    /* renamed from: ˋ */
    static boolean m1251(Integer num, Integer num2) {
        return ((double) Math.abs(num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    public void setPlayerVolume(Double d) {
        Double valueOf = Double.valueOf(this.f1152.doubleValue() * C0789p.m1366());
        if (!d.equals(this.f1152)) {
            C0756b.m1234(3, "BaseVideoTracker", this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.f1152 = d;
            if (!valueOf.equals(Double.valueOf(this.f1152.doubleValue() * C0789p.m1366()))) {
                dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f1108, this.f1152));
            }
        }
    }

    /* renamed from: ᐝ */
    final Double m1261() {
        return Double.valueOf(this.f1152.doubleValue() * C0789p.m1366());
    }
}
