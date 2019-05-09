package com.moat.analytics.mobile.tjy;

import android.os.Build.VERSION;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.tjy.u */
public class C2766u {
    /* renamed from: a */
    private boolean f6736a = false;
    /* renamed from: b */
    private boolean f6737b = false;
    /* renamed from: c */
    private int f6738c = 200;

    public C2766u(String str) {
        m6952a(str);
    }

    /* renamed from: a */
    private boolean m6951a(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: a */
    public void m6952a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("sa");
            boolean equals = string.equals("8ace5ca5da6b9adb3c0f055aad4a98c2aedf4bd7");
            if ((string.equals("on") || equals) && !m6951a(jSONObject)) {
                this.f6736a = true;
                this.f6737b = equals;
            }
            if (jSONObject.has("in")) {
                int i = jSONObject.getInt("in");
                if (i >= 100 && i <= 1000) {
                    this.f6738c = i;
                }
            }
        } catch (Exception e) {
            this.f6736a = false;
            this.f6737b = false;
            this.f6738c = 200;
        }
    }

    /* renamed from: a */
    public boolean m6953a() {
        return this.f6737b;
    }

    /* renamed from: b */
    public boolean m6954b() {
        return this.f6736a;
    }

    /* renamed from: c */
    public int m6955c() {
        return this.f6738c;
    }
}
