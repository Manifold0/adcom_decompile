package com.facebook.ads.internal.p037f;

import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* renamed from: com.facebook.ads.internal.f.b */
public class C1995b {
    /* renamed from: a */
    private final List<String> f4410a = new ArrayList();
    /* renamed from: b */
    private final List<String> f4411b = new ArrayList();

    /* renamed from: com.facebook.ads.internal.f.b$a */
    public enum C1994a {
        REPORT("report"),
        HIDE(MessengerShareContentUtility.SHARE_BUTTON_HIDE),
        NONE(ParametersKeys.ORIENTATION_NONE);
        
        /* renamed from: d */
        private final String f4409d;

        private C1994a(String str) {
            this.f4409d = str;
        }

        /* renamed from: a */
        String m4803a() {
            return this.f4409d;
        }
    }

    /* renamed from: a */
    public void m4804a() {
        this.f4410a.add(String.VIDEO_START);
    }

    /* renamed from: a */
    public void m4805a(int i) {
        this.f4411b.add(String.valueOf(i));
    }

    /* renamed from: a */
    public void m4806a(C1994a c1994a) {
        this.f4410a.add(c1994a.m4803a() + "_end");
    }

    /* renamed from: a */
    public void m4807a(C1994a c1994a, int i) {
        this.f4410a.add(c1994a.m4803a() + "_" + i);
    }

    /* renamed from: b */
    public void m4808b() {
        this.f4410a.add("why_am_i_seeing_this");
    }

    /* renamed from: c */
    public void m4809c() {
        this.f4410a.add("manage_ad_preferences");
    }

    /* renamed from: d */
    public Map<String, String> m4810d() {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("user_journey", new JSONArray(this.f4410a).toString());
        hashMap.put("options_selected", new JSONArray(this.f4411b).toString());
        return hashMap;
    }

    /* renamed from: e */
    public boolean m4811e() {
        return (this.f4410a.isEmpty() && this.f4411b.isEmpty()) ? false : true;
    }

    /* renamed from: f */
    public void m4812f() {
        this.f4410a.clear();
        this.f4411b.clear();
    }
}
