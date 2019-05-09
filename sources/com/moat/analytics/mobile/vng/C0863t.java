package com.moat.analytics.mobile.vng;

import android.graphics.Rect;
import android.view.View;
import com.moat.analytics.mobile.vng.NativeDisplayTracker.MoatUserInteractionType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.t */
class C0863t extends C0822b implements NativeDisplayTracker {
    /* renamed from: f */
    private final Map<String, String> f1458f;
    /* renamed from: g */
    private final Set<MoatUserInteractionType> f1459g = new HashSet();

    C0863t(View view, Map<String, String> map) {
        super(view, true, false);
        C0858p.m1577a(3, "NativeDisplayTracker", (Object) this, "Initializing.");
        this.f1458f = map;
        C0834g c0834g = ((C0847k) MoatAnalytics.getInstance()).f1426d;
        if (c0834g != null) {
            super.m1449a(c0834g.f1375b);
            super.m1448a(c0834g.f1374a);
        }
        m1594g();
        C0858p.m1579a("[SUCCESS] ", mo1662a() + " created for " + m1453e() + ", with adIds:" + map.toString());
    }

    /* renamed from: a */
    private static String m1593a(Map<String, String> map) {
        int i = 0;
        Map linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < 8; i2++) {
            String str = "moatClientLevel" + i2;
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        while (i < 8) {
            String str2 = "moatClientSlicer" + i;
            if (map.containsKey(str2)) {
                linkedHashMap.put(str2, map.get(str2));
            }
            i++;
        }
        for (String str3 : map.keySet()) {
            if (!linkedHashMap.containsKey(str3)) {
                linkedHashMap.put(str3, (String) map.get(str3));
            }
        }
        return new JSONObject(linkedHashMap).toString();
    }

    /* renamed from: g */
    private void m1594g() {
        if (this.a != null) {
            this.a.m1521a(m1595h());
        }
    }

    /* renamed from: h */
    private String m1595h() {
        String str = "";
        try {
            String a = C0863t.m1593a(this.f1458f);
            C0858p.m1577a(3, "NativeDisplayTracker", (Object) this, "Parsed ad ids = " + a);
            str = "{\"adIds\":" + a + ", \"adKey\":\"" + this.b + "\", \"adSize\":" + m1596i() + "}";
        } catch (Exception e) {
            C0849m.m1543a(e);
        }
        return str;
    }

    /* renamed from: i */
    private String m1596i() {
        try {
            Rect a = C0886z.m1642a(super.m1452d());
            int width = a.width();
            int height = a.height();
            Map hashMap = new HashMap();
            hashMap.put("width", Integer.toString(width));
            hashMap.put("height", Integer.toString(height));
            return new JSONObject(hashMap).toString();
        } catch (Exception e) {
            C0849m.m1543a(e);
            return null;
        }
    }

    /* renamed from: a */
    String mo1662a() {
        return "NativeDisplayTracker";
    }

    public void reportUserInteractionEvent(MoatUserInteractionType moatUserInteractionType) {
        try {
            C0858p.m1577a(3, "NativeDisplayTracker", (Object) this, "reportUserInteractionEvent:" + moatUserInteractionType.name());
            if (!this.f1459g.contains(moatUserInteractionType)) {
                this.f1459g.add(moatUserInteractionType);
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("adKey", this.b);
                jSONObject.accumulate("event", moatUserInteractionType.name().toLowerCase());
                if (this.a != null) {
                    this.a.m1523b(jSONObject.toString());
                }
            }
        } catch (Exception e) {
            C0858p.m1580b(2, "NativeDisplayTracker", this, "Got JSON exception");
            C0849m.m1543a(e);
        } catch (Exception e2) {
            C0849m.m1543a(e2);
        }
    }
}
