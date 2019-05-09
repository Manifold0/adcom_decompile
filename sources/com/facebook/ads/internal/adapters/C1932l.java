package com.facebook.ads.internal.adapters;

import android.content.Intent;
import android.os.Bundle;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p021o.C2060c;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p027a.C1844d;
import com.facebook.ads.internal.p027a.C1846e;
import com.facebook.ads.internal.p027a.C1846e.C1845a;
import com.unity.purchasing.googleplay.Consts;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.l */
public class C1932l implements C1845a {
    /* renamed from: a */
    private final String f4195a;
    /* renamed from: b */
    private final String f4196b;
    /* renamed from: c */
    private final C1844d f4197c;
    /* renamed from: d */
    private final Collection<String> f4198d;
    /* renamed from: e */
    private final Map<String, String> f4199e;
    /* renamed from: f */
    private final String f4200f;
    /* renamed from: g */
    private final int f4201g;
    /* renamed from: h */
    private final int f4202h;
    /* renamed from: i */
    private final int f4203i;
    /* renamed from: j */
    private final String f4204j;

    private C1932l(String str, String str2, C1844d c1844d, Collection<String> collection, Map<String, String> map, String str3, int i, int i2, int i3, String str4) {
        this.f4195a = str;
        this.f4196b = str2;
        this.f4197c = c1844d;
        this.f4198d = collection;
        this.f4199e = map;
        this.f4200f = str3;
        this.f4201g = i;
        this.f4202h = i2;
        this.f4203i = i3;
        this.f4204j = str4;
    }

    /* renamed from: a */
    public static C1932l m4548a(Bundle bundle) {
        return new C1932l(C2060c.m5025a(bundle.getByteArray("markup")), null, C1844d.NONE, null, null, bundle.getString(Consts.INAPP_REQUEST_ID), bundle.getInt("viewability_check_initial_delay"), bundle.getInt("viewability_check_interval"), bundle.getInt("skip_after_seconds", 0), bundle.getString("ct"));
    }

    /* renamed from: a */
    public static C1932l m4549a(JSONObject jSONObject) {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        JSONArray jSONArray;
        String optString = jSONObject.optString("markup");
        String optString2 = jSONObject.optString("activation_command");
        String optString3 = jSONObject.optString(Consts.INAPP_REQUEST_ID);
        String a = C2581k.m6646a(jSONObject, "ct");
        C1844d a2 = C1844d.m4145a(jSONObject.optString("invalidation_behavior"));
        try {
            jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        Collection a3 = C1846e.m4148a(jSONArray);
        JSONObject optJSONObject = jSONObject.optJSONObject("metadata");
        Map hashMap = new HashMap();
        if (optJSONObject != null) {
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                hashMap.put(str, optJSONObject.optString(str));
            }
        }
        int i2 = 1000;
        int parseInt = hashMap.containsKey("viewability_check_initial_delay") ? Integer.parseInt((String) hashMap.get("viewability_check_initial_delay")) : 0;
        if (hashMap.containsKey("viewability_check_interval")) {
            i2 = Integer.parseInt((String) hashMap.get("viewability_check_interval"));
        }
        if (hashMap.containsKey("skip_after_seconds")) {
            i = Integer.parseInt((String) hashMap.get("skip_after_seconds"));
        }
        return new C1932l(optString, optString2, a2, a3, hashMap, optString3, parseInt, i2, i, a);
    }

    /* renamed from: b */
    public static C1932l m4550b(Intent intent) {
        return new C1932l(C2060c.m5025a(intent.getByteArrayExtra("markup")), intent.getStringExtra("activation_command"), C1844d.NONE, null, null, intent.getStringExtra(Consts.INAPP_REQUEST_ID), intent.getIntExtra("viewability_check_initial_delay", 0), intent.getIntExtra("viewability_check_interval", 1000), intent.getIntExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, 0), intent.getStringExtra("ct"));
    }

    /* renamed from: a */
    public C1844d mo5409a() {
        return this.f4197c;
    }

    /* renamed from: a */
    public void m4552a(Intent intent) {
        intent.putExtra("markup", C2060c.m5026a(this.f4195a));
        intent.putExtra("activation_command", this.f4196b);
        intent.putExtra(Consts.INAPP_REQUEST_ID, this.f4200f);
        intent.putExtra("viewability_check_initial_delay", this.f4201g);
        intent.putExtra("viewability_check_interval", this.f4202h);
        intent.putExtra(AudienceNetworkActivity.SKIP_DELAY_SECONDS_KEY, this.f4203i);
        intent.putExtra("ct", this.f4204j);
    }

    /* renamed from: b */
    public Collection<String> mo5410b() {
        return this.f4198d;
    }

    /* renamed from: c */
    public String m4554c() {
        return this.f4195a;
    }

    /* renamed from: d */
    public String m4555d() {
        return this.f4196b;
    }

    /* renamed from: e */
    public Map<String, String> m4556e() {
        return this.f4199e;
    }

    /* renamed from: f */
    public int m4557f() {
        return this.f4201g;
    }

    /* renamed from: g */
    public int m4558g() {
        return this.f4202h;
    }

    public String getClientToken() {
        return this.f4204j;
    }

    /* renamed from: h */
    public Bundle m4559h() {
        Bundle bundle = new Bundle();
        bundle.putByteArray("markup", C2060c.m5026a(this.f4195a));
        bundle.putString(Consts.INAPP_REQUEST_ID, this.f4200f);
        bundle.putInt("viewability_check_initial_delay", this.f4201g);
        bundle.putInt("viewability_check_interval", this.f4202h);
        bundle.putInt("skip_after_seconds", this.f4203i);
        bundle.putString("ct", this.f4204j);
        return bundle;
    }
}
