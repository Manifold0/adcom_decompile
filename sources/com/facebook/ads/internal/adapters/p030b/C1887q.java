package com.facebook.ads.internal.adapters.p030b;

import android.text.TextUtils;
import com.facebook.ads.internal.adapters.p030b.C1869d.C1868a;
import com.facebook.ads.internal.adapters.p030b.C1873e.C1872b;
import com.facebook.ads.internal.adapters.p030b.C1883m.C1882a;
import com.facebook.ads.internal.p021o.C2060c;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.tapjoy.TapjoyConstants;
import com.unity.purchasing.googleplay.Consts;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.b.q */
public class C1887q extends C1864a {
    /* renamed from: a */
    private final String f4014a;
    /* renamed from: b */
    private final C1883m f4015b;
    /* renamed from: c */
    private final C1873e f4016c;
    /* renamed from: d */
    private final C1877i f4017d;
    /* renamed from: e */
    private final C1865b f4018e;
    /* renamed from: f */
    private final C1869d f4019f;
    /* renamed from: g */
    private final C1878j f4020g;
    /* renamed from: h */
    private final String f4021h;
    /* renamed from: i */
    private boolean f4022i;

    private C1887q(String str, C1883m c1883m, C1873e c1873e, C1877i c1877i, C1865b c1865b, C1869d c1869d, C1878j c1878j, String str2) {
        this.f4014a = str;
        this.f4015b = c1883m;
        this.f4016c = c1873e;
        this.f4017d = c1877i;
        this.f4018e = c1865b;
        this.f4019f = c1869d;
        this.f4020g = c1878j;
        this.f4021h = str2;
    }

    /* renamed from: a */
    public static C1887q m4355a(JSONObject jSONObject) {
        JSONObject jSONObject2 = null;
        C1882a c = new C1882a().m4323a(jSONObject.optString("advertiser_name")).m4325b(jSONObject.optJSONObject("icon") != null ? jSONObject.optJSONObject("icon").optString("url") : "").m4326c(jSONObject.optString("ad_choices_link_url"));
        JSONObject optJSONObject = jSONObject.optJSONObject("generic_text");
        C1883m a = c.m4327d(optJSONObject == null ? "Sponsored" : optJSONObject.optString("sponsored", "Sponsored")).m4324a();
        C1873e a2 = new C1872b().m4257a(jSONObject.optString("title")).m4259b(jSONObject.optString(MessengerShareContentUtility.SUBTITLE)).m4260c(jSONObject.optString("body")).m4261d(jSONObject.optString("rating_value")).m4262e(jSONObject.optString("category")).m4263f(jSONObject.optString("destination_title")).m4264g(jSONObject.optString("ad_creative_type")).m4258a();
        C1877i c1877i = new C1877i(jSONObject.optString("fbad_command"), jSONObject.optString("call_to_action"));
        JSONObject optJSONObject2 = jSONObject.optJSONObject("layout");
        C1876h a3 = C1876h.m4285a(optJSONObject2 != null ? optJSONObject2.optJSONObject("portrait") : null);
        if (optJSONObject2 != null) {
            jSONObject2 = optJSONObject2.optJSONObject("landscape");
        }
        return new C1887q(jSONObject.optString(Consts.INAPP_REQUEST_ID), a, a2, c1877i, new C1865b(a3, C1876h.m4285a(jSONObject2)), new C1868a().m4230a(jSONObject.optString(TapjoyConstants.TJC_VIDEO_URL)).m4234b(jSONObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE) != null ? jSONObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE).optString("url") : "").m4228a(jSONObject.optInt("skippable_seconds")).m4233b(jSONObject.optInt("video_duration_sec")).m4229a(C1884n.m4332a(jSONObject)).m4232a(), new C1878j(C2060c.m5026a(jSONObject.optString("end_card_markup")), jSONObject.optString("activation_command"), C1887q.m4356a(jSONObject.optJSONArray("end_card_images"))), jSONObject.optString("ct"));
    }

    /* renamed from: a */
    private static List<String> m4356a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return new ArrayList();
        }
        List<String> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            CharSequence optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public String mo5384a() {
        return this.f4021h;
    }

    /* renamed from: a */
    public void mo5386a(String str) {
        super.mo5386a(str);
        this.f4020g.m4298a(str);
    }

    /* renamed from: a */
    public void m4359a(boolean z) {
        this.f4022i = z;
    }

    /* renamed from: c */
    public void m4360c(String str) {
        this.f4019f.m4239a(str);
    }

    /* renamed from: e */
    public String m4361e() {
        return this.f4014a;
    }

    /* renamed from: f */
    public C1883m m4362f() {
        return this.f4015b;
    }

    /* renamed from: g */
    public C1873e m4363g() {
        return this.f4016c;
    }

    /* renamed from: h */
    public C1877i m4364h() {
        return this.f4017d;
    }

    /* renamed from: i */
    public C1865b m4365i() {
        return this.f4018e;
    }

    /* renamed from: j */
    public C1869d m4366j() {
        return this.f4019f;
    }

    /* renamed from: k */
    public C1878j m4367k() {
        return this.f4020g;
    }

    /* renamed from: l */
    public boolean m4368l() {
        return this.f4022i;
    }
}
