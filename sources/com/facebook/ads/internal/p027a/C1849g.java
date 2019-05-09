package com.facebook.ads.internal.p027a;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.kongregate.android.internal.sdk.C0498e;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.a.g */
public class C1849g {
    /* renamed from: a */
    private final String f3849a;
    /* renamed from: b */
    private final String f3850b;
    /* renamed from: c */
    private final String f3851c;
    /* renamed from: d */
    private final List<String> f3852d;
    /* renamed from: e */
    private final String f3853e;
    /* renamed from: f */
    private final String f3854f;

    private C1849g(String str, String str2, String str3, List<String> list, String str4, String str5) {
        this.f3849a = str;
        this.f3850b = str2;
        this.f3851c = str3;
        this.f3852d = list;
        this.f3853e = str4;
        this.f3854f = str5;
    }

    /* renamed from: a */
    public static C1849g m4162a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString(C0498e.f503t);
        String optString2 = jSONObject.optString("appsite");
        String optString3 = jSONObject.optString("appsite_url");
        JSONArray optJSONArray = jSONObject.optJSONArray("key_hashes");
        List arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.optString(i));
            }
        }
        return new C1849g(optString, optString2, optString3, arrayList, jSONObject.optString("market_uri"), jSONObject.optString(MessengerShareContentUtility.FALLBACK_URL));
    }

    /* renamed from: a */
    public String m4163a() {
        return this.f3849a;
    }

    /* renamed from: b */
    public String m4164b() {
        return this.f3850b;
    }

    /* renamed from: c */
    public String m4165c() {
        return this.f3851c;
    }
}
