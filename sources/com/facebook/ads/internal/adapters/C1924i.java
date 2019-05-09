package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2114e.C1825c;
import com.facebook.ads.internal.p017t.C2115g;
import com.facebook.ads.internal.p017t.C2116i;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.p017t.C2119l;
import com.facebook.ads.internal.p025w.p026b.C2567c;
import com.facebook.ads.internal.p025w.p026b.C2581k;
import com.facebook.ads.internal.p025w.p026b.C2601z;
import com.facebook.ads.internal.p027a.C1842b;
import com.facebook.ads.internal.p027a.C1843c;
import com.facebook.ads.internal.p027a.C1844d;
import com.facebook.ads.internal.p027a.C1846e;
import com.facebook.ads.internal.p027a.C1846e.C1845a;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.i */
public class C1924i implements C1845a, AdAdapter {
    /* renamed from: a */
    private static final String f4133a = C1924i.class.getSimpleName();
    /* renamed from: A */
    private int f4134A;
    /* renamed from: B */
    private String f4135B;
    /* renamed from: C */
    private boolean f4136C;
    /* renamed from: D */
    private boolean f4137D;
    /* renamed from: E */
    private boolean f4138E;
    /* renamed from: F */
    private boolean f4139F;
    /* renamed from: G */
    private boolean f4140G;
    @Nullable
    /* renamed from: H */
    private C2085c f4141H;
    /* renamed from: I */
    private C1825c f4142I;
    /* renamed from: b */
    private Context f4143b;
    /* renamed from: c */
    private C1837q f4144c;
    /* renamed from: d */
    private Uri f4145d;
    /* renamed from: e */
    private HashMap<String, String> f4146e = new HashMap();
    /* renamed from: f */
    private C2115g f4147f;
    /* renamed from: g */
    private C2115g f4148g;
    /* renamed from: h */
    private C2116i f4149h;
    /* renamed from: i */
    private String f4150i;
    /* renamed from: j */
    private C1844d f4151j;
    /* renamed from: k */
    private Collection<String> f4152k;
    /* renamed from: l */
    private boolean f4153l;
    /* renamed from: m */
    private boolean f4154m;
    /* renamed from: n */
    private int f4155n;
    /* renamed from: o */
    private int f4156o;
    /* renamed from: p */
    private int f4157p;
    /* renamed from: q */
    private int f4158q;
    /* renamed from: r */
    private String f4159r;
    /* renamed from: s */
    private String f4160s;
    /* renamed from: t */
    private C2119l f4161t;
    /* renamed from: u */
    private int f4162u = 200;
    /* renamed from: v */
    private C2115g f4163v;
    /* renamed from: w */
    private String f4164w;
    /* renamed from: x */
    private C2117j f4165x;
    /* renamed from: y */
    private List<C2114e> f4166y;
    /* renamed from: z */
    private int f4167z = -1;

    /* renamed from: C */
    private void m4485C() {
        if (!this.f4140G) {
            if (this.f4141H != null) {
                this.f4141H.mo5469a(this.f4150i);
            }
            this.f4140G = true;
        }
    }

    /* renamed from: a */
    private void m4487a(JSONObject jSONObject, String str) {
        JSONArray jSONArray = null;
        if (this.f4137D) {
            throw new IllegalStateException("Adapter already loaded data");
        } else if (jSONObject != null) {
            int i;
            C2117j c2117j;
            int length;
            List arrayList;
            C1924i c1924i;
            Context context;
            JSONObject jSONObject2;
            C2085c c2085c;
            boolean z;
            C2567c.m6621a(this.f4143b, "Audience Network Loaded");
            this.f4135B = str;
            Object a = C2581k.m6646a(jSONObject, "fbad_command");
            this.f4145d = TextUtils.isEmpty(a) ? null : Uri.parse(a);
            for (String str2 : new String[]{"advertiser_name", "title", MessengerShareContentUtility.SUBTITLE, "headline", "body", "social_context", "link_description", "sponsored_translation", "ad_translation", "promoted_translation"}) {
                this.f4146e.put(str2, C2581k.m6646a(jSONObject, str2));
            }
            CharSequence a2 = C2581k.m6646a(jSONObject, "call_to_action");
            if (!TextUtils.isEmpty(a2)) {
                this.f4146e.put("call_to_action", a2);
            }
            this.f4147f = C2115g.m5351a(jSONObject.optJSONObject("icon"));
            this.f4148g = C2115g.m5351a(jSONObject.optJSONObject(MessengerShareContentUtility.MEDIA_IMAGE));
            this.f4149h = C2116i.m5355a(jSONObject.optJSONObject("star_rating"));
            this.f4150i = C2581k.m6646a(jSONObject, "used_report_url");
            this.f4153l = jSONObject.optBoolean("enable_view_log");
            this.f4154m = jSONObject.optBoolean("enable_snapshot_log");
            this.f4155n = jSONObject.optInt("snapshot_log_delay_second", 4);
            this.f4156o = jSONObject.optInt("snapshot_compress_quality", 0);
            this.f4157p = jSONObject.optInt("viewability_check_initial_delay", 0);
            this.f4158q = jSONObject.optInt("viewability_check_interval", 1000);
            JSONObject optJSONObject = jSONObject.optJSONObject("ad_choices_icon");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("native_ui_config");
            if (optJSONObject2 != null) {
                try {
                    if (optJSONObject2.length() != 0) {
                        c2117j = new C2117j(optJSONObject2);
                        this.f4165x = c2117j;
                        if (optJSONObject != null) {
                            this.f4163v = C2115g.m5351a(optJSONObject);
                        }
                        this.f4164w = C2581k.m6646a(jSONObject, "ad_choices_link_url");
                        this.f4151j = C1844d.m4145a(jSONObject.optString("invalidation_behavior"));
                        jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
                        this.f4152k = C1846e.m4148a(jSONArray);
                        this.f4159r = C2581k.m6646a(jSONObject, TapjoyConstants.TJC_VIDEO_URL);
                        this.f4160s = C2581k.m6646a(jSONObject, "video_mpd");
                        if (jSONObject.has("video_autoplay_enabled")) {
                            this.f4161t = C2119l.f4896a;
                        } else {
                            this.f4161t = jSONObject.optBoolean("video_autoplay_enabled") ? C2119l.ON : C2119l.OFF;
                        }
                        jSONArray = jSONObject.optJSONArray("carousel");
                        if (jSONArray != null && jSONArray.length() > 0) {
                            length = jSONArray.length();
                            arrayList = new ArrayList(length);
                            for (i = 0; i < length; i++) {
                                c1924i = new C1924i();
                                context = this.f4143b;
                                jSONObject2 = jSONArray.getJSONObject(i);
                                c2085c = this.f4141H;
                                c1924i.f4136C = true;
                                c1924i.f4143b = context;
                                c1924i.f4141H = c2085c;
                                c1924i.f4167z = i;
                                c1924i.f4134A = length;
                                c1924i.m4487a(jSONObject2, str);
                                arrayList.add(new C2114e(this.f4143b, c1924i, null, this.f4142I));
                            }
                            this.f4166y = arrayList;
                        }
                        this.f4137D = true;
                        z = ((this.f4136C && !TextUtils.isEmpty((CharSequence) this.f4146e.get("advertiser_name"))) || (!TextUtils.isEmpty((CharSequence) this.f4146e.get("title")) && this.f4136C)) && ((this.f4147f != null || this.f4136C) && (this.f4148g != null || getPlacementType() == AdPlacementType.NATIVE_BANNER));
                        this.f4138E = z;
                    }
                } catch (JSONException e) {
                    this.f4165x = null;
                }
            }
            c2117j = null;
            this.f4165x = c2117j;
            if (optJSONObject != null) {
                this.f4163v = C2115g.m5351a(optJSONObject);
            }
            this.f4164w = C2581k.m6646a(jSONObject, "ad_choices_link_url");
            this.f4151j = C1844d.m4145a(jSONObject.optString("invalidation_behavior"));
            try {
                jSONArray = new JSONArray(jSONObject.optString("detection_strings"));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.f4152k = C1846e.m4148a(jSONArray);
            this.f4159r = C2581k.m6646a(jSONObject, TapjoyConstants.TJC_VIDEO_URL);
            this.f4160s = C2581k.m6646a(jSONObject, "video_mpd");
            if (jSONObject.has("video_autoplay_enabled")) {
                if (jSONObject.optBoolean("video_autoplay_enabled")) {
                }
                this.f4161t = jSONObject.optBoolean("video_autoplay_enabled") ? C2119l.ON : C2119l.OFF;
            } else {
                this.f4161t = C2119l.f4896a;
            }
            try {
                jSONArray = jSONObject.optJSONArray("carousel");
                length = jSONArray.length();
                arrayList = new ArrayList(length);
                for (i = 0; i < length; i++) {
                    c1924i = new C1924i();
                    context = this.f4143b;
                    jSONObject2 = jSONArray.getJSONObject(i);
                    c2085c = this.f4141H;
                    c1924i.f4136C = true;
                    c1924i.f4143b = context;
                    c1924i.f4141H = c2085c;
                    c1924i.f4167z = i;
                    c1924i.f4134A = length;
                    c1924i.m4487a(jSONObject2, str);
                    arrayList.add(new C2114e(this.f4143b, c1924i, null, this.f4142I));
                }
                this.f4166y = arrayList;
            } catch (Throwable e3) {
                Log.e(f4133a, "Unable to parse carousel data.", e3);
            }
            this.f4137D = true;
            if (this.f4136C) {
            }
            this.f4138E = z;
        }
    }

    /* renamed from: A */
    public boolean m4489A() {
        return this.f4137D && this.f4138E;
    }

    /* renamed from: B */
    public boolean m4490B() {
        return this.f4136C;
    }

    /* renamed from: a */
    public C1844d mo5409a() {
        return this.f4151j;
    }

    @Nullable
    /* renamed from: a */
    public String m4492a(String str) {
        if (!m4489A()) {
            return null;
        }
        m4485C();
        return (String) this.f4146e.get(str);
    }

    /* renamed from: a */
    public void m4493a(int i) {
    }

    /* renamed from: a */
    public void m4494a(Context context, C1837q c1837q, C2085c c2085c, Map<String, Object> map, C1825c c1825c) {
        this.f4143b = context;
        this.f4144c = c1837q;
        this.f4141H = c2085c;
        this.f4142I = c1825c;
        JSONObject jSONObject = (JSONObject) map.get("data");
        C2047d c2047d = (C2047d) map.get("definition");
        this.f4162u = c2047d != null ? c2047d.m4990k() : 200;
        m4487a(jSONObject, C2581k.m6646a(jSONObject, "ct"));
        if (C1846e.m4149a(context, this, c2085c)) {
            c1837q.mo5373a(this, new C2065a(AdErrorType.NO_FILL, "No Fill"));
        } else if (c1837q != null) {
            c1837q.mo5372a(this);
        }
    }

    /* renamed from: a */
    public void m4495a(View view, List<View> list) {
    }

    /* renamed from: a */
    public void m4496a(C1837q c1837q) {
        this.f4144c = c1837q;
    }

    /* renamed from: a */
    public void m4497a(Map<String, String> map) {
        if (m4489A() && !this.f4139F) {
            if (this.f4144c != null) {
                this.f4144c.mo5374b(this);
            }
            final Map hashMap = new HashMap();
            if (map != null) {
                hashMap.putAll(map);
            }
            if (this.f4136C) {
                hashMap.put("cardind", String.valueOf(this.f4167z));
                hashMap.put("cardcnt", String.valueOf(this.f4134A));
            }
            if (!(TextUtils.isEmpty(getClientToken()) || this.f4141H == null)) {
                this.f4141H.mo5470a(getClientToken(), hashMap);
            }
            if (m4503d() || m4508h()) {
                try {
                    final Map hashMap2 = new HashMap();
                    if (map.containsKey(ParametersKeys.VIEW)) {
                        hashMap2.put(ParametersKeys.VIEW, map.get(ParametersKeys.VIEW));
                    }
                    if (map.containsKey("snapshot")) {
                        hashMap2.put("snapshot", map.get("snapshot"));
                    }
                    new Handler().postDelayed(new Runnable(this) {
                        /* renamed from: c */
                        final /* synthetic */ C1924i f4132c;

                        public void run() {
                            if (!TextUtils.isEmpty(this.f4132c.f4135B)) {
                                Map hashMap = new HashMap();
                                hashMap.putAll(hashMap);
                                hashMap.putAll(hashMap2);
                                if (this.f4132c.f4141H != null) {
                                    this.f4132c.f4141H.mo5477f(this.f4132c.f4135B, hashMap);
                                }
                            }
                        }
                    }, (long) (this.f4155n * 1000));
                } catch (Exception e) {
                }
            }
            this.f4139F = true;
        }
    }

    /* renamed from: b */
    public Collection<String> mo5410b() {
        return this.f4152k;
    }

    /* renamed from: b */
    public void m4499b(Map<String, String> map) {
        if (this.f4141H != null) {
            this.f4141H.mo5482k(this.f4135B, map);
        }
    }

    /* renamed from: c */
    public void m4500c() {
        if (this.f4166y != null && !this.f4166y.isEmpty()) {
            for (C2114e z : this.f4166y) {
                z.m5350z();
            }
        }
    }

    /* renamed from: c */
    public void m4501c(Map<String, String> map) {
        if (this.f4141H != null) {
            this.f4141H.mo5481j(this.f4135B, map);
        }
    }

    /* renamed from: d */
    public void m4502d(Map<String, String> map) {
        if (this.f4141H != null) {
            this.f4141H.mo5480i(this.f4135B, map);
        }
    }

    /* renamed from: d */
    public boolean m4503d() {
        return m4489A() && this.f4153l;
    }

    /* renamed from: e */
    public void m4504e(Map<String, String> map) {
        if (!m4489A()) {
            return;
        }
        if (C2078a.m5100j(this.f4143b) && C2601z.m6692a((Map) map)) {
            Log.e(f4133a, "Click happened on lockscreen ad");
            return;
        }
        Map hashMap = new HashMap();
        if (map != null) {
            hashMap.putAll(map);
        }
        C2567c.m6621a(this.f4143b, "Click logged");
        if (this.f4144c != null) {
            this.f4144c.mo5375c(this);
        }
        if (this.f4136C) {
            hashMap.put("cardind", String.valueOf(this.f4167z));
            hashMap.put("cardcnt", String.valueOf(this.f4134A));
        }
        C1842b a = C1843c.m4142a(this.f4143b, this.f4141H, this.f4135B, this.f4145d, hashMap);
        if (a != null) {
            try {
                a.mo5376a();
            } catch (Throwable e) {
                Log.e(f4133a, "Error executing action", e);
            }
        }
    }

    /* renamed from: e */
    public boolean m4505e() {
        return m4489A() && this.f4165x != null;
    }

    /* renamed from: f */
    public boolean m4506f() {
        return m4489A() && this.f4145d != null;
    }

    /* renamed from: g */
    public boolean m4507g() {
        return true;
    }

    public String getClientToken() {
        return this.f4135B;
    }

    public AdPlacementType getPlacementType() {
        return AdPlacementType.NATIVE;
    }

    /* renamed from: h */
    public boolean m4508h() {
        return m4489A() && this.f4154m;
    }

    /* renamed from: i */
    public int m4509i() {
        return (this.f4156o < 0 || this.f4156o > 100) ? 0 : this.f4156o;
    }

    /* renamed from: j */
    public int m4510j() {
        return this.f4157p;
    }

    /* renamed from: k */
    public int m4511k() {
        return this.f4158q;
    }

    /* renamed from: l */
    public C2115g m4512l() {
        return !m4489A() ? null : this.f4147f;
    }

    /* renamed from: m */
    public C2115g m4513m() {
        return !m4489A() ? null : this.f4148g;
    }

    /* renamed from: n */
    public C2117j m4514n() {
        return !m4489A() ? null : this.f4165x;
    }

    /* renamed from: o */
    public String m4515o() {
        if (!m4489A()) {
            return null;
        }
        m4485C();
        String str = (String) this.f4146e.get("body");
        if (str == null) {
            return str;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ", true);
        if (str.length() <= 90) {
            return str;
        }
        if (str.length() <= 93 && str.endsWith("...")) {
            return str;
        }
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            int length = stringTokenizer.nextToken().length();
            if (i + length < 90) {
                i += length;
            }
        }
        return i == 0 ? str.substring(0, 90) + "..." : str.substring(0, i) + "...";
    }

    public void onDestroy() {
    }

    /* renamed from: p */
    public C2116i m4516p() {
        if (!m4489A()) {
            return null;
        }
        m4485C();
        return this.f4149h;
    }

    /* renamed from: q */
    public C2115g m4517q() {
        return !m4489A() ? null : this.f4163v;
    }

    /* renamed from: r */
    public String m4518r() {
        return !m4489A() ? null : this.f4164w;
    }

    /* renamed from: s */
    public String m4519s() {
        return !m4489A() ? null : "AdChoices";
    }

    /* renamed from: t */
    public String m4520t() {
        return !m4489A() ? null : this.f4159r;
    }

    /* renamed from: u */
    public String m4521u() {
        return !m4489A() ? null : this.f4160s;
    }

    /* renamed from: v */
    public C2119l m4522v() {
        return !m4489A() ? C2119l.f4896a : this.f4161t;
    }

    /* renamed from: w */
    public int m4523w() {
        return this.f4162u;
    }

    /* renamed from: x */
    public List<C2114e> m4524x() {
        return !m4489A() ? null : this.f4166y;
    }

    /* renamed from: y */
    public int m4525y() {
        return this.f4167z;
    }

    /* renamed from: z */
    public int m4526z() {
        return this.f4134A;
    }
}
