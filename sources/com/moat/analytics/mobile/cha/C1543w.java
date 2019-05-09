package com.moat.analytics.mobile.cha;

import android.view.View;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.cha.w */
final class C1543w extends C1490b implements ReactiveVideoTracker {
    /* renamed from: ˋॱ */
    private Integer f3636;

    public C1543w(String str) {
        super(str);
        C1487a.m3715(3, "ReactiveVideoTracker", this, "Initializing.");
        C1487a.m3712("[SUCCESS] ", "ReactiveVideoTracker created");
    }

    /* renamed from: ˋ */
    final String mo4374() {
        return "ReactiveVideoTracker";
    }

    /* renamed from: ᐝ */
    final Map<String, Object> mo4379() throws C1518o {
        Object valueOf;
        Object valueOf2;
        Map<String, Object> hashMap = new HashMap();
        View view = (View) this.ˊॱ.get();
        Integer valueOf3 = Integer.valueOf(0);
        Integer valueOf4 = Integer.valueOf(0);
        if (view != null) {
            valueOf = Integer.valueOf(view.getWidth());
            valueOf2 = Integer.valueOf(view.getHeight());
        } else {
            Integer num = valueOf4;
            valueOf4 = valueOf3;
        }
        hashMap.put("duration", this.f3636);
        hashMap.put("width", valueOf);
        hashMap.put("height", valueOf2);
        return hashMap;
    }

    public final boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
        try {
            m3727();
            m3729();
            this.f3636 = num;
            return super.mo4373(map, view);
        } catch (Exception e) {
            m3731("trackVideoAd", e);
            return false;
        }
    }

    /* renamed from: ˎ */
    final JSONObject mo4372(MoatAdEvent moatAdEvent) {
        if (!(moatAdEvent.f3418 != MoatAdEventType.AD_EVT_COMPLETE || moatAdEvent.f3417.equals(MoatAdEvent.f3413) || C1490b.m3733(moatAdEvent.f3417, this.f3636))) {
            moatAdEvent.f3418 = MoatAdEventType.AD_EVT_STOPPED;
        }
        return super.mo4372(moatAdEvent);
    }

    /* renamed from: ˋ */
    final void mo4368(List<String> list) throws C1518o {
        if (this.f3636.intValue() < 1000) {
            throw new C1518o(String.format(Locale.ROOT, "Invalid duration = %d. Please make sure duration is in milliseconds.", new Object[]{this.f3636}));
        } else {
            super.mo4368(list);
        }
    }
}
