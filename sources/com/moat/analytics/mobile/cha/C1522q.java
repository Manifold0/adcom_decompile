package com.moat.analytics.mobile.cha;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.cha.NativeDisplayTracker.MoatUserInteractionType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.cha.q */
final class C1522q extends C1489d implements NativeDisplayTracker {
    /* renamed from: ˊॱ */
    private final Map<String, String> f3568;
    /* renamed from: ᐝ */
    private final Set<MoatUserInteractionType> f3569 = new HashSet();

    C1522q(View view, Map<String, String> map) {
        super(view, true, false);
        C1487a.m3715(3, "NativeDisplayTracker", this, "Initializing.");
        this.f3568 = map;
        String str;
        String str2;
        if (view == null) {
            str = "Target view is null";
            str2 = "NativeDisplayTracker initialization not successful, " + str;
            C1487a.m3715(3, "NativeDisplayTracker", this, str2);
            C1487a.m3712("[ERROR] ", str2);
            this.ॱ = new C1518o(str);
        } else if (map == null || map.isEmpty()) {
            str = "NativeDisplayTracker initialization not successful, " + "AdIds is null or empty";
            C1487a.m3715(3, "NativeDisplayTracker", this, str);
            C1487a.m3712("[ERROR] ", str);
            this.ॱ = new C1518o("AdIds is null or empty");
        } else {
            C1487a c1487a = ((C1495f) MoatAnalytics.getInstance()).f3474;
            if (c1487a == null) {
                str = "prepareNativeDisplayTracking was not called successfully";
                str2 = "NativeDisplayTracker initialization not successful, " + str;
                C1487a.m3715(3, "NativeDisplayTracker", this, str2);
                C1487a.m3712("[ERROR] ", str2);
                this.ॱ = new C1518o(str);
                return;
            }
            this.f3442 = c1487a.f3433;
            try {
                super.m3730(c1487a.f3431);
                if (this.ˎ != null) {
                    this.ˎ.m3810(m3850());
                }
                C1487a.m3712("[SUCCESS] ", "NativeDisplayTracker created for " + m3721() + ", with adIds:" + map.toString());
            } catch (C1518o e) {
                this.ॱ = e;
            }
        }
    }

    /* renamed from: ˋ */
    final String mo4374() {
        return "NativeDisplayTracker";
    }

    public final void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType) {
        try {
            C1487a.m3715(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (!this.f3569.contains(moatUserInteractionType)) {
                this.f3569.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.ˋ);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.ˎ != null) {
                    this.ˎ.m3811(jSONObject.toString());
                }
            }
        } catch (Exception e) {
            C1487a.m3716("NativeDisplayTracker", this, "Got JSON exception");
            C1518o.m3840(e);
        } catch (Exception e2) {
            C1518o.m3840(e2);
        }
    }

    /* renamed from: ˊॱ */
    private String m3850() {
        int i = 0;
        String str = "";
        try {
            Map map = this.f3568;
            Map linkedHashMap = new LinkedHashMap();
            for (int i2 = 0; i2 < 8; i2++) {
                String str2 = "moatClientLevel" + i2;
                if (map.containsKey(str2)) {
                    linkedHashMap.put(str2, map.get(str2));
                }
            }
            while (i < 8) {
                String str3 = "moatClientSlicer" + i;
                if (map.containsKey(str3)) {
                    linkedHashMap.put(str3, map.get(str3));
                }
                i++;
            }
            for (String str4 : map.keySet()) {
                if (!linkedHashMap.containsKey(str4)) {
                    linkedHashMap.put(str4, (String) map.get(str4));
                }
            }
            String str42 = new JSONObject(linkedHashMap).toString();
            C1487a.m3715(3, "NativeDisplayTracker", this, "Parsed ad ids = " + str42);
            return "{\"adIds\":" + str42 + ", \"adKey\":\"" + this.ˋ + "\", \"adSize\":" + m3851() + "}";
        } catch (Exception e) {
            C1518o.m3840(e);
            return str;
        }
    }

    /* renamed from: ᐝ */
    private String m3851() {
        try {
            Rect ˋ = C1541u.m3897(super.m3722());
            int width = ˋ.width();
            int height = ˋ.height();
            Map hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            C1518o.m3840(e);
            return null;
        }
    }
}
