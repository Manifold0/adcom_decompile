package com.moat.analytics.mobile.vng;

import android.view.View;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.y */
class C0883y extends C0827c<Object> implements ReactiveVideoTracker {
    /* renamed from: l */
    private Integer f1500l;

    public C0883y(String str) {
        super(str);
        C0858p.m1579a("[SUCCESS] ", mo1662a() + " created");
    }

    /* renamed from: a */
    String mo1662a() {
        return "ReactiveVideoTracker";
    }

    /* renamed from: a */
    protected JSONObject mo1666a(MoatAdEvent moatAdEvent) {
        if (!(moatAdEvent.f1330d != MoatAdEventType.AD_EVT_COMPLETE || moatAdEvent.f1328b.equals(MoatAdEvent.f1326a) || m1463a(moatAdEvent.f1328b, this.f1500l))) {
            moatAdEvent.f1330d = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo1666a(moatAdEvent);
    }

    /* renamed from: g */
    protected Map<String, Object> mo1664g() {
        Object valueOf;
        Object valueOf2;
        Map<String, Object> hashMap = new HashMap();
        View view = (View) this.k.get();
        Integer valueOf3 = Integer.valueOf(0);
        Integer valueOf4 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        } else {
            Integer num = valueOf4;
            valueOf4 = valueOf3;
        }
        hashMap.put("duration", this.f1500l);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        if (this.e) {
            C0858p.m1577a(3, "ReactiveVideoTracker", (Object) this, "trackVideoAd already called");
            C0858p.m1579a("[ERROR] ", mo1662a() + " trackVideoAd can't be called twice");
            return false;
        } else if (num.intValue() < 1000) {
            C0858p.m1577a(3, "ReactiveVideoTracker", (Object) this, String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{num}));
            return false;
        } else {
            this.f1500l = num;
            return super.mo1667a(map, new Object(), view);
        }
    }
}
