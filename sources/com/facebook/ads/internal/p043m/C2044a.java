package com.facebook.ads.internal.p043m;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.m.a */
public class C2044a {
    /* renamed from: a */
    private final String f4563a;
    /* renamed from: b */
    private final String f4564b;
    /* renamed from: c */
    private final JSONObject f4565c;
    /* renamed from: d */
    private final Map<C2048e, List<String>> f4566d = new HashMap();

    public C2044a(String str, String str2, JSONObject jSONObject, @Nullable JSONArray jSONArray) {
        this.f4563a = str;
        this.f4564b = str2;
        this.f4565c = jSONObject;
        if (jSONArray != null && jSONArray.length() != 0) {
            int i;
            for (Object put : C2048e.values()) {
                this.f4566d.put(put, new LinkedList());
            }
            for (i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("type");
                    CharSequence string2 = jSONObject2.getString("url");
                    C2048e valueOf = C2048e.valueOf(string.toUpperCase(Locale.US));
                    if (!(valueOf == null || TextUtils.isEmpty(string2))) {
                        ((List) this.f4566d.get(valueOf)).add(string2);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    /* renamed from: a */
    public String m4963a() {
        return this.f4563a;
    }

    /* renamed from: a */
    public List<String> m4964a(C2048e c2048e) {
        return (List) this.f4566d.get(c2048e);
    }

    /* renamed from: b */
    public String m4965b() {
        return this.f4564b;
    }

    /* renamed from: c */
    public JSONObject m4966c() {
        return this.f4565c;
    }
}
