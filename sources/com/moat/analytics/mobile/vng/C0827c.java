package com.moat.analytics.mobile.vng;

import android.os.Handler;
import android.view.View;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.moat.analytics.mobile.vng.C0834g.C0833a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.c */
abstract class C0827c<PlayerOrIMAAd> extends C0822b {
    /* renamed from: f */
    static final MoatAdEventType[] f1357f = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    /* renamed from: g */
    final Map<MoatAdEventType, Integer> f1358g;
    /* renamed from: h */
    final Handler f1359h;
    /* renamed from: i */
    Map<String, String> f1360i;
    /* renamed from: j */
    WeakReference<PlayerOrIMAAd> f1361j;
    /* renamed from: k */
    WeakReference<View> f1362k;
    /* renamed from: l */
    private boolean f1363l;
    /* renamed from: m */
    private Double f1364m;
    /* renamed from: n */
    private final C0834g f1365n = new C0834g(C0821a.m1439a(), C0833a.f1372b);
    /* renamed from: o */
    private final String f1366o;

    /* renamed from: com.moat.analytics.mobile.vng.c$1 */
    class C08261 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0827c f1356a;

        C08261(C0827c c0827c) {
            this.f1356a = c0827c;
        }

        public void run() {
            try {
                C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "Shutting down.");
                this.f1356a.f1365n.m1476a();
            } catch (Exception e) {
                C0849m.m1543a(e);
            }
        }
    }

    C0827c(String str) {
        super(null, false, true);
        C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "Initializing.");
        this.f1366o = str;
        this.f1365n.m1477a(str);
        super.m1449a(this.f1365n.f1375b);
        super.m1448a(this.f1365n.f1374a);
        this.f1358g = new HashMap();
        this.f1359h = new Handler();
        this.f1363l = false;
        this.f1364m = Double.valueOf(1.0d);
    }

    /* renamed from: b */
    private void m1460b(MoatAdEvent moatAdEvent) {
        C0858p.m1577a(3, "BaseVideoTracker", (Object) this, String.format("Received event: %s", new Object[]{mo1666a(moatAdEvent).toString()}));
        C0858p.m1579a("[SUCCESS] ", mo1662a() + String.format(" Received event: %s", new Object[]{r0.toString()}));
        this.a.m1522a(this.f1365n.f1376c, r0);
        MoatAdEventType moatAdEventType = moatAdEvent.f1330d;
        if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
            this.f1358g.put(moatAdEventType, Integer.valueOf(1));
            m1466h();
        }
    }

    /* renamed from: j */
    private void mo1668j() {
        Map g = mo1664g();
        Integer num = (Integer) g.get("height");
        C0858p.m1577a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) g.get("width"), (Integer) g.get("duration")}));
        this.f1365n.m1478a(this.f1366o, this.f1360i, r3, num, r5);
        super.changeTargetView((View) this.f1362k.get());
        super.m1450b();
    }

    /* renamed from: a */
    JSONObject mo1666a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.f1329c.doubleValue())) {
            try {
                moatAdEvent.f1329c = Double.valueOf(C0862s.m1586a());
            } catch (Exception e) {
                moatAdEvent.f1329c = Double.valueOf(1.0d);
            }
        }
        C0858p.m1577a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "adVolume before adjusting for player volume %f", new Object[]{moatAdEvent.f1329c}));
        moatAdEvent.f1329c = Double.valueOf(moatAdEvent.f1329c.doubleValue() * this.f1364m.doubleValue());
        C0858p.m1577a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "adVolume after adjusting for player volume %f", new Object[]{moatAdEvent.f1329c}));
        return new JSONObject(moatAdEvent.m1420a());
    }

    /* renamed from: a */
    boolean m1463a(Integer num, Integer num2) {
        return ((double) Math.abs(num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    /* renamed from: a */
    public boolean mo1667a(Map<String, String> map, PlayerOrIMAAd playerOrIMAAd, View view) {
        boolean z = true;
        try {
            if (this.e) {
                C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "trackVideoAd already called");
                C0858p.m1579a("[ERROR] ", mo1662a() + " trackVideoAd can't be called twice");
                z = false;
            }
            if (map == null) {
                C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null adIds object. Not tracking.");
                C0858p.m1579a("[ERROR] ", mo1662a() + " trackVideoAd failed, received null adIds object");
                z = false;
            }
            if (view == null) {
                C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null video view instance");
            }
            if (playerOrIMAAd == null) {
                C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "trackVideoAd received null ad instance. Not tracking.");
                C0858p.m1579a("[ERROR] ", mo1662a() + " trackVideoAd failed, received null ad instance");
                z = false;
            }
            if (z) {
                String str = "BaseVideoTracker";
                String str2 = "trackVideoAd tracking ids: %s | ad: %s | view: %s";
                Object[] objArr = new Object[3];
                objArr[0] = new JSONObject(map).toString();
                objArr[1] = playerOrIMAAd.toString();
                objArr[2] = view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null";
                C0858p.m1577a(3, str, (Object) this, String.format(str2, objArr));
                String str3 = "[SUCCESS] ";
                StringBuilder append = new StringBuilder().append(mo1662a());
                str2 = " trackVideoAd succeeded with ids: %s | ad: %s | view: %s";
                objArr = new Object[3];
                objArr[0] = new JSONObject(map).toString();
                objArr[1] = playerOrIMAAd.toString();
                objArr[2] = view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null";
                C0858p.m1579a(str3, append.append(String.format(str2, objArr)).toString());
                this.f1360i = map;
                this.f1361j = new WeakReference(playerOrIMAAd);
                this.f1362k = new WeakReference(view);
                mo1668j();
            }
        } catch (Exception e) {
            C0849m.m1543a(e);
            z = false;
        }
        C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "Attempt to start tracking ad was " + (z ? "" : "un") + "successful.");
        return z;
    }

    public void changeTargetView(View view) {
        C0858p.m1577a(3, "BaseVideoTracker", (Object) this, "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        this.f1362k = new WeakReference(view);
        try {
            super.changeTargetView(view);
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            m1460b(moatAdEvent);
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }

    /* renamed from: g */
    protected abstract Map<String, Object> mo1664g();

    /* renamed from: h */
    void m1466h() {
        if (!this.f1363l) {
            this.f1359h.postDelayed(new C08261(this), 500);
            this.f1363l = true;
        }
    }

    /* renamed from: i */
    boolean m1467i() {
        return this.f1358g.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f1358g.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f1358g.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void setPlayerVolume(Double d) {
        if (!d.equals(this.f1364m)) {
            C0858p.m1577a(3, "BaseVideoTracker", (Object) this, String.format(Locale.ROOT, "player volume changed to %f ", new Object[]{d}));
            this.f1364m = d;
            dispatchEvent(new MoatAdEvent(MoatAdEventType.AD_EVT_VOLUME_CHANGE, MoatAdEvent.f1326a));
        }
    }

    public void stopTracking() {
        try {
            boolean c = super.m1451c();
            C0858p.m1579a(c ? "[SUCCESS] " : "[ERROR] ", mo1662a() + " stopTracking " + (c ? AnalyticsEvents.PARAMETER_SHARE_OUTCOME_SUCCEEDED : ParametersKeys.FAILED) + " for " + m1453e());
            m1466h();
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
    }
}
