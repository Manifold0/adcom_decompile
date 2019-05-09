package com.moat.analytics.mobile.tjy;

import android.view.View;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class bd extends C2743f implements ReactiveVideoTracker {
    /* renamed from: j */
    private Integer f6686j;

    public bd(String str, C2742a c2742a, ap apVar) {
        super(str, c2742a, apVar);
    }

    /* renamed from: a */
    protected Map mo6086a() {
        Object valueOf;
        Object valueOf2;
        Map hashMap = new HashMap();
        View view = (View) this.g.get();
        Integer valueOf3 = Integer.valueOf(0);
        Integer valueOf4 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        } else {
            Integer num = valueOf4;
            valueOf4 = valueOf3;
        }
        hashMap.put("duration", this.f6686j);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    /* renamed from: a */
    protected JSONObject mo6084a(MoatAdEvent moatAdEvent) {
        if (moatAdEvent.eventType == MoatAdEventType.AD_EVT_COMPLETE && !m6820a(moatAdEvent.adPlayhead, this.f6686j)) {
            moatAdEvent.eventType = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo6084a(moatAdEvent);
    }

    /* renamed from: a */
    public /* bridge */ /* synthetic */ boolean mo6087a(Map map, Object obj, View view) {
        return super.mo6087a(map, obj, view);
    }

    public /* bridge */ /* synthetic */ void changeTargetView(View view) {
        super.changeTargetView(view);
    }

    public /* bridge */ /* synthetic */ void setDebug(boolean z) {
        super.setDebug(z);
    }

    public boolean trackVideoAd(Map map, Integer num, View view) {
        if (num.intValue() < 1000) {
            m6819a(String.format("Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{num}));
            return false;
        }
        this.f6686j = num;
        return super.mo6087a(map, new Object(), view);
    }
}
