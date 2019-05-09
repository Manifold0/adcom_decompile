package com.moat.analytics.mobile.iro;

import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.iro.w */
final class C0805w extends C0759d implements ReactiveVideoTracker {
    /* renamed from: ˏॱ */
    private Integer f1308;

    public C0805w(String str) {
        super(str);
        C0756b.m1234(3, "ReactiveVideoTracker", this, "Initializing.");
        C0756b.m1232("[SUCCESS] ", "ReactiveVideoTracker created");
    }

    /* renamed from: ˊ */
    final String mo1647() {
        return "ReactiveVideoTracker";
    }

    /* renamed from: ˊॱ */
    final Map<String, Object> mo1648() throws C0785o {
        Object valueOf;
        Object valueOf2;
        Map<String, Object> hashMap = new HashMap();
        View view = (View) this.ʼ.get();
        Integer valueOf3 = Integer.valueOf(0);
        Integer valueOf4 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        } else {
            Integer num = valueOf4;
            valueOf4 = valueOf3;
        }
        hashMap.put("duration", this.f1308);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            m1243();
            m1246();
            this.f1308 = num;
            return super.mo1644((Map) map, view);
        } catch (Exception e) {
            m1248("trackVideoAd", e);
            return false;
        }
    }

    /* renamed from: ˋ */
    final JSONObject mo1643(MoatAdEvent moatAdEvent) {
        if (!(moatAdEvent.f1113 != MoatAdEventType.AD_EVT_COMPLETE || moatAdEvent.f1111.equals(MoatAdEvent.f1108) || C0759d.m1251(moatAdEvent.f1111, this.f1308))) {
            moatAdEvent.f1113 = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo1643(moatAdEvent);
    }

    /* renamed from: ˏ */
    final void mo1641(List<String> list) throws C0785o {
        if (this.f1308.intValue() < 1000) {
            throw new C0785o(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{this.f1308}));
        } else {
            super.mo1641(list);
        }
    }
}
