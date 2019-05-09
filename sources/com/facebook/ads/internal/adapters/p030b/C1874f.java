package com.facebook.ads.internal.adapters.p030b;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.f */
public class C1874f extends C1864a {
    /* renamed from: a */
    private C1875g f3942a;
    /* renamed from: b */
    private C1866c f3943b;
    /* renamed from: c */
    private List<C1887q> f3944c;
    /* renamed from: d */
    private String f3945d;
    /* renamed from: e */
    private String f3946e;
    /* renamed from: f */
    private String f3947f;

    private C1874f(C1875g c1875g, Map<String, String> map, C1866c c1866c, List<C1887q> list) {
        this.f3942a = c1875g;
        this.f3943b = c1866c;
        this.f3944c = list;
        this.f3945d = map.containsKey("background_color") ? (String) map.get("background_color") : "#FF23272F";
        this.f3947f = map.containsKey("timer_text_color") ? (String) map.get("timer_text_color") : "#FFFFFF";
        this.f3946e = map.containsKey("title_text_color") ? (String) map.get("title_text_color") : "#FFFFFF";
    }

    /* renamed from: a */
    public static C1874f m4272a(JSONObject jSONObject) {
        C1875g a = C1875g.m4282a(jSONObject.optJSONObject("translations"));
        JSONObject optJSONObject = jSONObject.optJSONObject("layout");
        Map hashMap = new HashMap();
        if (optJSONObject != null) {
            C1874f.m4273a(optJSONObject, hashMap, "background_color");
            C1874f.m4273a(optJSONObject, hashMap, "timer_text_color");
            C1874f.m4273a(optJSONObject, hashMap, "title_text_color");
        }
        C1866c a2 = C1866c.m4214a(jSONObject.optJSONObject("ad_config"));
        JSONArray optJSONArray = jSONObject.optJSONArray("choosable_ads");
        List arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                C1887q a3 = C1887q.m4355a(optJSONArray.optJSONObject(i));
                a3.m4359a(true);
                arrayList.add(a3);
            }
        }
        return new C1874f(a, hashMap, a2, arrayList);
    }

    /* renamed from: a */
    private static void m4273a(JSONObject jSONObject, HashMap<String, String> hashMap, String str) {
        CharSequence optString = jSONObject.optString(str);
        if (!TextUtils.isEmpty(optString)) {
            hashMap.put(str, optString);
        }
    }

    @Nullable
    /* renamed from: a */
    public String mo5384a() {
        return this.f3944c.isEmpty() ? null : ((C1887q) this.f3944c.get(0)).mo5384a();
    }

    /* renamed from: b */
    public void mo5385b(String str) {
        super.mo5385b(str);
        for (C1887q b : this.f3944c) {
            b.mo5385b(str);
        }
    }

    /* renamed from: e */
    public C1875g m4276e() {
        return this.f3942a;
    }

    /* renamed from: f */
    public String m4277f() {
        return this.f3945d;
    }

    /* renamed from: g */
    public String m4278g() {
        return this.f3947f;
    }

    /* renamed from: h */
    public String m4279h() {
        return this.f3946e;
    }

    /* renamed from: i */
    public C1866c m4280i() {
        return this.f3943b;
    }

    /* renamed from: j */
    public List<C1887q> m4281j() {
        return this.f3944c;
    }
}
