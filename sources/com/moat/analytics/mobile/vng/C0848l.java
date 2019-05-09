package com.moat.analytics.mobile.vng;

import android.os.Build.VERSION;
import com.moat.analytics.mobile.vng.C0862s.C0861a;
import com.moat.analytics.mobile.vng.C0879w.C0878d;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.vng.l */
class C0848l {
    /* renamed from: a */
    private boolean f1430a = false;
    /* renamed from: b */
    private boolean f1431b = false;
    /* renamed from: c */
    private boolean f1432c = false;
    /* renamed from: d */
    private int f1433d = 200;

    C0848l(String str) {
        m1535a(str);
    }

    /* renamed from: a */
    private void m1535a(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("sa");
                boolean equals = string.equals("3f2ae9c1894282b5e0222f0d06bbf457191f816f");
                boolean equals2 = string.equals("8f1d08a2d6496191a5ebae8f0590f513e2619489");
                if (!((!string.equals("on") && !equals && !equals2) || m1536a(jSONObject) || m1537b(jSONObject))) {
                    this.f1430a = true;
                    this.f1431b = equals;
                    this.f1432c = equals2;
                    if (this.f1432c) {
                        this.f1431b = true;
                    }
                }
                if (jSONObject.has("in")) {
                    int i = jSONObject.getInt("in");
                    if (i >= 100 && i <= 1000) {
                        this.f1433d = i;
                    }
                }
                if (m1538c(jSONObject)) {
                    ((C0847k) MoatAnalytics.getInstance()).f1425c = true;
                }
            } catch (Exception e) {
                this.f1430a = false;
                this.f1431b = false;
                this.f1433d = 200;
                C0849m.m1543a(e);
            }
        }
    }

    /* renamed from: a */
    private boolean m1536a(JSONObject jSONObject) {
        try {
            if (16 > VERSION.SDK_INT) {
                return true;
            }
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

    /* renamed from: b */
    private boolean m1537b(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("ap")) {
                return false;
            }
            CharSequence b = new C0861a().m1585b();
            JSONArray jSONArray = jSONObject.getJSONArray("ap");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                if (jSONArray.getString(i).contentEquals(b)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            C0849m.m1543a(e);
            return false;
        }
    }

    /* renamed from: c */
    private boolean m1538c(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("al")) {
                return false;
            }
            CharSequence b = new C0861a().m1585b();
            JSONArray jSONArray = jSONObject.getJSONArray("al");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                if (jSONArray.getString(i).contentEquals(b)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            C0849m.m1543a(e);
            return false;
        }
    }

    /* renamed from: a */
    boolean m1539a() {
        return this.f1431b;
    }

    /* renamed from: b */
    boolean m1540b() {
        return this.f1432c;
    }

    /* renamed from: c */
    int m1541c() {
        return this.f1433d;
    }

    /* renamed from: d */
    C0878d m1542d() {
        return this.f1430a ? C0878d.ON : C0878d.OFF;
    }
}
