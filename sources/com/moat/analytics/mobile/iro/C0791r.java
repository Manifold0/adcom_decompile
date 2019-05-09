package com.moat.analytics.mobile.iro;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.iro.NativeDisplayTracker.MoatUserInteractionType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.iro.r */
final class C0791r extends C0757c implements NativeDisplayTracker {
    /* renamed from: ʻ */
    private final Set<MoatUserInteractionType> f1265 = new HashSet();
    /* renamed from: ʼ */
    private final Map<String, String> f1266;

    C0791r(View view, Map<String, String> map) {
        super(view, true, false);
        C0756b.m1234(3, "NativeDisplayTracker", this, "Initializing.");
        this.f1266 = map;
        String str;
        String str2;
        if (view == null) {
            str = "Target view is null";
            str2 = "NativeDisplayTracker initialization not successful, " + str;
            C0756b.m1234(3, "NativeDisplayTracker", this, str2);
            C0756b.m1232("[ERROR] ", str2);
            this.ˋ = new C0785o(str);
        } else if (map == null || map.isEmpty()) {
            str = "NativeDisplayTracker initialization not successful, " + "AdIds is null or empty";
            C0756b.m1234(3, "NativeDisplayTracker", this, str);
            C0756b.m1232("[ERROR] ", str);
            this.ˋ = new C0785o("AdIds is null or empty");
        } else {
            C0756b c0756b = ((C0774j) MoatAnalytics.getInstance()).f1219;
            if (c0756b == null) {
                str = "prepareNativeDisplayTracking was not called successfully";
                str2 = "NativeDisplayTracker initialization not successful, " + str;
                C0756b.m1234(3, "NativeDisplayTracker", this, str2);
                C0756b.m1232("[ERROR] ", str2);
                this.ˋ = new C0785o(str);
                return;
            }
            this.f1143 = c0756b.f1130;
            try {
                super.m1244(c0756b.f1132);
                if (this.ˏ != null) {
                    this.ˏ.m1284(m1376());
                }
                C0756b.m1232("[SUCCESS] ", "NativeDisplayTracker created for " + m1241() + ", with adIds:" + map.toString());
            } catch (C0785o e) {
                this.ˋ = e;
            }
        }
    }

    /* renamed from: ˊ */
    final String mo1647() {
        return "NativeDisplayTracker";
    }

    public final void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType) {
        try {
            C0756b.m1234(3, "NativeDisplayTracker", this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (!this.f1265.contains(moatUserInteractionType)) {
                this.f1265.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.ˎ);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.ˏ != null) {
                    this.ˏ.m1286(jSONObject.toString());
                }
            }
        } catch (Exception e) {
            C0756b.m1235("NativeDisplayTracker", this, "Got JSON exception");
            C0785o.m1351(e);
        } catch (Exception e2) {
            C0785o.m1351(e2);
        }
    }

    /* renamed from: ᐝ */
    private String m1376() {
        int i = 0;
        String str = "";
        try {
            Map map = this.f1266;
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
            C0756b.m1234(3, "NativeDisplayTracker", this, "Parsed ad ids = " + str42);
            return "{\"adIds\":" + str42 + ", \"adKey\":\"" + this.ˎ + "\", \"adSize\":" + m1375() + "}";
        } catch (Exception e) {
            C0785o.m1351(e);
            return str;
        }
    }

    /* renamed from: ˊॱ */
    private String m1375() {
        try {
            Rect ˏ = C0810y.m1414(super.m1239());
            int width = ˏ.width();
            int height = ˏ.height();
            Map hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            C0785o.m1351(e);
            return null;
        }
    }
}
