package com.facebook.ads.internal.p037f;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p071f.C2616a;
import com.facebook.ads.internal.p050r.C2078a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.f.a */
public class C1993a {
    /* renamed from: a */
    private static final String[] f4402a = new String[]{"hide_ad", "hide_ad_description", "hide_ad_follow_up_heading", "hide_ad_options", "report_ad", "report_ad_description", "report_ad_follow_up_heading", "report_ad_options", "manage_ad_preferences", "finished_hide_ad", "finished_report_ad", "finished_description", "why_am_i_seeing_this", "ad_choices_uri", "manage_ad_preferences_uri"};
    /* renamed from: b */
    private static C1993a f4403b;
    /* renamed from: c */
    private final SharedPreferences f4404c;

    private C1993a(Context context) {
        this.f4404c = context.getApplicationContext().getSharedPreferences(C2616a.m6730a("com.facebook.ads.AD_REPORTING_CONFIG", context), 0);
    }

    /* renamed from: a */
    public static long m4782a(Context context) {
        return C1993a.m4802o(context).f4404c.getLong("last_updated_timestamp", 0);
    }

    /* renamed from: a */
    private String m4783a(String str, String str2) {
        String string = this.f4404c.getString(str, str2);
        return (string == null || string.equals("null")) ? str2 : string;
    }

    /* renamed from: a */
    private static List<C1996c> m4784a(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
            return new ArrayList();
        }
        JSONArray jSONArray = new JSONArray(str);
        List<C1996c> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            C1996c c1996c = new C1996c(jSONObject.getInt("option_value"), jSONObject.getString("option_text"), jSONObject.optString("children_heading"));
            for (C1996c a : C1993a.m4784a(jSONObject.optString("children_options"))) {
                c1996c.m4814a(a);
            }
            arrayList.add(c1996c);
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m4785a() {
        Editor edit = this.f4404c.edit();
        edit.putLong("last_updated_timestamp", 0);
        edit.apply();
    }

    /* renamed from: a */
    public static void m4786a(Context context, @Nullable String str) {
        Map b = C1993a.m4789b(context, str);
        if (b != null && b.size() == f4402a.length) {
            Editor edit = C1993a.m4802o(context).f4404c.edit();
            for (String str2 : f4402a) {
                edit.putString(str2, (String) b.get(str2));
            }
            edit.putLong("last_updated_timestamp", System.currentTimeMillis());
            edit.apply();
        }
    }

    /* renamed from: a */
    public static boolean m4787a(Context context, boolean z) {
        return (z ? C2078a.m5076O(context) : C2078a.m5077P(context)) && C1993a.m4782a(context) > 0;
    }

    /* renamed from: b */
    public static String m4788b(Context context) {
        return C1993a.m4802o(context).m4783a("hide_ad", "Hide Ad");
    }

    @Nullable
    /* renamed from: b */
    private static Map<String, String> m4789b(Context context, @Nullable String str) {
        if (str == null || str.isEmpty() || str.equals("[]")) {
            return null;
        }
        Map<String, String> hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            for (String str2 : f4402a) {
                if (!jSONObject.has(str2)) {
                    return null;
                }
                hashMap.put(str2, jSONObject.getString(str2));
            }
            if (C1993a.m4784a(jSONObject.getString("report_ad_options")).size() == 0) {
                C2625a.m6741b(context, "reporting", C2626b.f6522M, new Exception("No report ad options"));
                return null;
            } else if (C1993a.m4784a(jSONObject.getString("hide_ad_options")).size() != 0) {
                return hashMap;
            } else {
                C2625a.m6741b(context, "reporting", C2626b.f6524O, new Exception("No hide ad options"));
                return null;
            }
        } catch (Exception e) {
            C2625a.m6741b(context, "reporting", C2626b.f6521L, e);
            return null;
        }
    }

    /* renamed from: c */
    public static String m4790c(Context context) {
        return C1993a.m4802o(context).m4783a("hide_ad_description", "See fewer ads like this");
    }

    /* renamed from: d */
    public static C1996c m4791d(Context context) {
        C1996c c1996c = new C1996c(C1993a.m4802o(context).m4783a("hide_ad_follow_up_heading", "Help us understand what is happening. Why don't you want to see this?"));
        try {
            for (C1996c a : C1993a.m4784a(C1993a.m4802o(context).m4783a("hide_ad_options", ""))) {
                c1996c.m4814a(a);
            }
        } catch (Exception e) {
            C1993a.m4802o(context).m4785a();
            C2625a.m6741b(context, "reporting", C2626b.f6525P, e);
        }
        return c1996c;
    }

    /* renamed from: e */
    public static String m4792e(Context context) {
        return C1993a.m4802o(context).m4783a("report_ad", "Report Ad");
    }

    /* renamed from: f */
    public static String m4793f(Context context) {
        return C1993a.m4802o(context).m4783a("report_ad_description", " Mark ad as offensive or inappropriate");
    }

    /* renamed from: g */
    public static C1996c m4794g(Context context) {
        C1996c c1996c = new C1996c(C1993a.m4802o(context).m4783a("report_ad_follow_up_heading", "Help us understand what is happening. Why is this inappropriate?"));
        try {
            for (C1996c a : C1993a.m4784a(C1993a.m4802o(context).m4783a("report_ad_options", ""))) {
                c1996c.m4814a(a);
            }
        } catch (Exception e) {
            C1993a.m4802o(context).m4785a();
            C2625a.m6741b(context, "reporting", C2626b.f6523N, e);
        }
        return c1996c;
    }

    /* renamed from: h */
    public static String m4795h(Context context) {
        return C1993a.m4802o(context).m4783a("manage_ad_preferences", "Manage ad preferences");
    }

    /* renamed from: i */
    public static String m4796i(Context context) {
        return C1993a.m4802o(context).m4783a("finished_hide_ad", "Ad hidden.");
    }

    /* renamed from: j */
    public static String m4797j(Context context) {
        return C1993a.m4802o(context).m4783a("finished_report_ad", "Ad reported.");
    }

    /* renamed from: k */
    public static String m4798k(Context context) {
        return C1993a.m4802o(context).m4783a("finished_description", "Your submission is now being reviewed.");
    }

    /* renamed from: l */
    public static String m4799l(Context context) {
        return C1993a.m4802o(context).m4783a("why_am_i_seeing_this", "Why am I seeing this?");
    }

    /* renamed from: m */
    public static String m4800m(Context context) {
        return C1993a.m4802o(context).m4783a("ad_choices_uri", "");
    }

    /* renamed from: n */
    public static String m4801n(Context context) {
        return C1993a.m4802o(context).m4783a("manage_ad_preferences_uri", "");
    }

    /* renamed from: o */
    private static C1993a m4802o(Context context) {
        if (f4403b == null) {
            synchronized (C1993a.class) {
                if (f4403b == null) {
                    f4403b = new C1993a(context);
                }
            }
        }
        return f4403b;
    }
}
