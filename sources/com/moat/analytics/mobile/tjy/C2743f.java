package com.moat.analytics.mobile.tjy;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.moat.analytics.mobile.tjy.base.exception.C2747a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.tjy.f */
abstract class C2743f {
    /* renamed from: b */
    protected static final MoatAdEventType[] f6630b = new MoatAdEventType[]{MoatAdEventType.AD_EVT_FIRST_QUARTILE, MoatAdEventType.AD_EVT_MID_POINT, MoatAdEventType.AD_EVT_THIRD_QUARTILE};
    /* renamed from: a */
    protected boolean f6631a;
    /* renamed from: c */
    protected final Map f6632c = new HashMap();
    /* renamed from: d */
    protected final Handler f6633d = new Handler();
    /* renamed from: e */
    protected Map f6634e;
    /* renamed from: f */
    protected WeakReference f6635f;
    /* renamed from: g */
    protected WeakReference f6636g;
    /* renamed from: h */
    protected final C2742a f6637h;
    /* renamed from: i */
    protected final ap f6638i;
    /* renamed from: j */
    private boolean f6639j;
    /* renamed from: k */
    private WeakReference f6640k;
    /* renamed from: l */
    private ad f6641l;

    public C2743f(String str, C2742a c2742a, ap apVar) {
        this.f6638i = apVar;
        this.f6637h = c2742a;
        m6819a("Initializing.");
        this.f6641l = new ad(str, apVar, c2742a);
        this.f6640k = new WeakReference(c2742a.mo6127c());
        this.f6639j = false;
        this.f6631a = false;
    }

    /* renamed from: a */
    private int m6812a(AudioManager audioManager) {
        return audioManager.getStreamVolume(3);
    }

    /* renamed from: a */
    private MoatAdEvent m6813a(Map map) {
        return new MoatAdEvent(MoatAdEventType.fromString((String) map.get("type")), map.containsKey("playHead") ? (Integer) map.get("playHead") : MoatAdEvent.TIME_UNAVAILABLE, map.containsKey(MoatAdEvent.EVENT_AD_VOLUME) ? (Double) map.get(MoatAdEvent.EVENT_AD_VOLUME) : MoatAdEvent.VOLUME_UNAVAILABLE);
    }

    /* renamed from: b */
    private void m6816b(MoatAdEvent moatAdEvent) {
        m6819a(String.format("Received event: %s", new Object[]{mo6084a(moatAdEvent).toString()}));
        this.f6641l.m6804a(r0);
        MoatAdEventType moatAdEventType = moatAdEvent.eventType;
        if (moatAdEventType == MoatAdEventType.AD_EVT_COMPLETE || moatAdEventType == MoatAdEventType.AD_EVT_STOPPED || moatAdEventType == MoatAdEventType.AD_EVT_SKIPPED) {
            this.f6632c.put(moatAdEventType, Integer.valueOf(1));
            m6823c();
        }
    }

    /* renamed from: a */
    protected abstract Map mo6086a();

    /* renamed from: a */
    protected JSONObject mo6084a(MoatAdEvent moatAdEvent) {
        if (Double.isNaN(moatAdEvent.adVolume.doubleValue())) {
            try {
                moatAdEvent.adVolume = Double.valueOf(m6824d());
            } catch (Exception e) {
                moatAdEvent.adVolume = Double.valueOf(1.0d);
            }
        }
        return new JSONObject(moatAdEvent.toMap());
    }

    /* renamed from: a */
    protected void m6819a(String str) {
        if (this.f6638i.mo6105b() || this.f6631a) {
            Log.d("MoatVideoTracker", str);
        }
    }

    /* renamed from: a */
    protected boolean m6820a(Integer num, Integer num2) {
        return ((double) (num2.intValue() - num.intValue())) <= Math.min(750.0d, ((double) num2.intValue()) * 0.05d);
    }

    /* renamed from: a */
    public boolean mo6087a(Map map, Object obj, View view) {
        boolean z = true;
        boolean z2 = false;
        if (map == null) {
            try {
                m6819a("trackVideoAd received null adIds object. Not tracking.");
                z = false;
            } catch (Exception e) {
                C2747a.m6882a(e);
            }
        }
        if (view == null) {
            m6819a("trackVideoAd received null video view instance");
        }
        if (obj == null) {
            m6819a("trackVideoAd received null ad instance. Not tracking.");
            z = false;
        }
        if (z) {
            String str = "trackVideoAd tracking ids: %s | ad: %s | view: %s";
            Object[] objArr = new Object[3];
            objArr[0] = new JSONObject(map).toString();
            objArr[1] = obj.toString();
            objArr[2] = view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null";
            m6819a(String.format(str, objArr));
            this.f6634e = map;
            this.f6635f = new WeakReference(obj);
            this.f6636g = new WeakReference(view);
            mo6085b();
        }
        z2 = z;
        m6819a("Attempt to start tracking ad was " + (z2 ? "" : "un") + "successful.");
        return z2;
    }

    /* renamed from: b */
    protected void mo6085b() {
        Map a = mo6086a();
        Integer num = (Integer) a.get("height");
        m6819a(String.format("Player metadata: height = %d, width = %d, duration = %d", new Object[]{num, (Integer) a.get("width"), (Integer) a.get("duration")}));
        this.f6641l.m6802a((View) this.f6636g.get(), this.f6634e, r3, num, r5);
    }

    /* renamed from: c */
    protected void m6823c() {
        if (!this.f6639j) {
            this.f6633d.postDelayed(new C2754g(this), 500);
            this.f6639j = true;
        }
    }

    public void changeTargetView(View view) {
        if (this.f6638i.mo6105b()) {
            Log.d("MoatVideoTracker", "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        }
        this.f6636g = new WeakReference(view);
        this.f6641l.m6801a(view);
    }

    /* renamed from: d */
    protected double m6824d() {
        AudioManager audioManager = (AudioManager) ((Context) this.f6640k.get()).getSystemService("audio");
        return ((double) m6812a(audioManager)) / ((double) audioManager.getStreamMaxVolume(3));
    }

    public void dispatchEvent(MoatAdEvent moatAdEvent) {
        try {
            m6816b(moatAdEvent);
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }

    public void dispatchEvent(Map map) {
        try {
            m6816b(m6813a(map));
        } catch (Exception e) {
            C2747a.m6882a(e);
        }
    }

    /* renamed from: e */
    protected boolean m6825e() {
        return this.f6632c.containsKey(MoatAdEventType.AD_EVT_COMPLETE) || this.f6632c.containsKey(MoatAdEventType.AD_EVT_STOPPED) || this.f6632c.containsKey(MoatAdEventType.AD_EVT_SKIPPED);
    }

    public void setDebug(boolean z) {
        this.f6631a = z;
    }
}
