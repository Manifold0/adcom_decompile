package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AdError;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.p021o.C1809f;
import com.facebook.ads.internal.p024h.C1832a;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C1910r;
import com.facebook.ads.internal.p025w.p026b.C2595u;
import com.facebook.ads.internal.p025w.p026b.C2595u.C2594a;
import com.facebook.ads.internal.p032d.C1907b;
import com.facebook.ads.internal.p032d.C1989c;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.C2422c;
import com.facebook.ads.internal.view.p022i.C2423b;
import com.facebook.ads.internal.view.p022i.p023b.C2405b;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2407e;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p065a.C2390b;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;
import com.facebook.ads.internal.view.p022i.p067c.C2434a;
import com.facebook.ads.internal.view.p022i.p067c.C2440b;
import com.facebook.ads.internal.view.p022i.p067c.C2442c;
import com.facebook.ads.internal.view.p022i.p067c.C2451d;
import com.facebook.ads.internal.view.p022i.p067c.C2451d.C2450a;
import com.facebook.ads.internal.view.p022i.p067c.C2453e;
import com.facebook.ads.internal.view.p022i.p067c.C2468i;
import com.facebook.ads.internal.view.p022i.p067c.C2475k;
import com.facebook.ads.internal.view.p022i.p067c.C2481l;
import com.facebook.ads.p018a.C1800a;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.adapters.f */
public class C1912f extends C1911n implements C1910r<Bundle> {
    /* renamed from: e */
    static final /* synthetic */ boolean f4062e = (!C1912f.class.desiredAssertionStatus());
    @Nullable
    /* renamed from: a */
    protected C2085c f4063a;
    @Nullable
    /* renamed from: b */
    protected C2394a f4064b;
    @Nullable
    /* renamed from: c */
    protected JSONObject f4065c;
    @Nullable
    /* renamed from: d */
    protected Context f4066d;
    /* renamed from: f */
    private final C1809f<C2406c> f4067f = new C19031(this);
    /* renamed from: g */
    private final C1809f<C2412m> f4068g = new C19042(this);
    /* renamed from: h */
    private final C1809f<C2407e> f4069h = new C19053(this);
    /* renamed from: i */
    private final C1809f<C2405b> f4070i = new C19064(this);
    @Nullable
    /* renamed from: j */
    private C1800a f4071j;
    @Nullable
    /* renamed from: k */
    private String f4072k;
    /* renamed from: l */
    private boolean f4073l = false;
    @Nullable
    /* renamed from: m */
    private C2422c f4074m;
    @Nullable
    /* renamed from: n */
    private String f4075n;
    /* renamed from: o */
    private boolean f4076o = false;
    /* renamed from: p */
    private C2011b f4077p;

    /* renamed from: com.facebook.ads.internal.adapters.f$1 */
    class C19031 extends C1809f<C2406c> {
        /* renamed from: a */
        final /* synthetic */ C1912f f4052a;

        C19031(C1912f c1912f) {
            this.f4052a = c1912f;
        }

        /* renamed from: a */
        public Class<C2406c> mo5359a() {
            return C2406c.class;
        }

        /* renamed from: a */
        public void m4405a(C2406c c2406c) {
            if (this.f4052a.f4071j != null) {
                this.f4052a.f4071j.mo5348d(this.f4052a);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.f$2 */
    class C19042 extends C1809f<C2412m> {
        /* renamed from: a */
        final /* synthetic */ C1912f f4053a;

        C19042(C1912f c1912f) {
            this.f4053a = c1912f;
        }

        /* renamed from: a */
        public Class<C2412m> mo5359a() {
            return C2412m.class;
        }

        /* renamed from: a */
        public void m4408a(C2412m c2412m) {
            this.f4053a.f4073l = true;
            if (this.f4053a.f4071j != null) {
                this.f4053a.f4071j.mo5343a(this.f4053a);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.f$3 */
    class C19053 extends C1809f<C2407e> {
        /* renamed from: a */
        final /* synthetic */ C1912f f4054a;

        C19053(C1912f c1912f) {
            this.f4054a = c1912f;
        }

        /* renamed from: a */
        public Class<C2407e> mo5359a() {
            return C2407e.class;
        }

        /* renamed from: a */
        public void m4411a(C2407e c2407e) {
            if (this.f4054a.f4071j != null) {
                this.f4054a.f4071j.mo5345a(this.f4054a, AdError.internalError(AdError.INTERNAL_ERROR_2003));
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.f$4 */
    class C19064 extends C1809f<C2405b> {
        /* renamed from: a */
        final /* synthetic */ C1912f f4055a;

        C19064(C1912f c1912f) {
            this.f4055a = c1912f;
        }

        /* renamed from: a */
        public Class<C2405b> mo5359a() {
            return C2405b.class;
        }

        /* renamed from: a */
        public void m4414a(C2405b c2405b) {
            if (this.f4055a.f4071j != null) {
                this.f4055a.f4071j.mo5346b(this.f4055a);
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.adapters.f$6 */
    class C19096 implements C1832a {
        /* renamed from: a */
        final /* synthetic */ C1912f f4061a;

        C19096(C1912f c1912f) {
            this.f4061a = c1912f;
        }

        /* renamed from: a */
        public void mo5368a() {
            this.f4061a.f4064b.setVideoURI(this.f4061a.mo5407h());
        }

        /* renamed from: b */
        public void mo5369b() {
            this.f4061a.f4064b.setVideoURI(this.f4061a.mo5407h());
        }
    }

    /* renamed from: a */
    private void m4422a(Context context, C1800a c1800a, JSONObject jSONObject, C2085c c2085c, @Nullable Bundle bundle, EnumSet<CacheFlag> enumSet, int i) {
        this.f4066d = context;
        this.f4071j = c1800a;
        this.f4063a = c2085c;
        this.f4065c = jSONObject;
        this.f4073l = false;
        JSONObject jSONObject2 = jSONObject.getJSONObject("video");
        this.f4075n = jSONObject.optString("ct");
        this.f4064b = new C2394a(context);
        this.f4064b.setVideoProgressReportIntervalMs(i);
        mo5402a();
        this.f4064b.getEventBus().m5029a(this.f4067f, this.f4068g, this.f4069h, this.f4070i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new C1907b(this, 1.0E-7d, -1.0d, 0.001d, false) {
            /* renamed from: a */
            final /* synthetic */ C1912f f4060a;

            /* renamed from: a */
            protected void mo5396a(boolean z, boolean z2, C1989c c1989c) {
                this.f4060a.m4433f();
            }
        });
        if (bundle != null) {
            this.f4074m = new C2423b(context, c2085c, this.f4064b, arrayList, this.f4075n, bundle.getBundle("logger"), null);
        } else {
            this.f4074m = new C2423b(context, c2085c, this.f4064b, (List) arrayList, this.f4075n);
        }
        this.f4071j.mo5344a((C1911n) this, this.f4064b);
        if (C2595u.m6664a(context) == C2594a.MOBILE_INTERNET && jSONObject2.has("videoHDURL") && !jSONObject2.isNull("videoHDURL")) {
            this.f4072k = jSONObject2.getString("videoHDURL");
        } else {
            this.f4072k = jSONObject2.getString(AudienceNetworkActivity.VIDEO_URL);
        }
        if (enumSet.contains(CacheFlag.VIDEO)) {
            this.f4077p = new C2011b(context);
            this.f4077p.m4843a(this.f4072k);
            this.f4077p.m4842a(new C19096(this));
            return;
        }
        this.f4064b.setVideoURI(mo5407h());
    }

    /* renamed from: h */
    private String mo5407h() {
        Object obj = "";
        if (!(this.f4077p == null || this.f4072k == null)) {
            obj = this.f4077p.m4846c(this.f4072k);
        }
        return TextUtils.isEmpty(obj) ? this.f4072k : obj;
    }

    /* renamed from: a */
    protected void mo5402a() {
        if (!f4062e && this.f4066d == null) {
            throw new AssertionError();
        } else if (f4062e || this.f4065c != null) {
            C2390b c2442c;
            LayoutParams layoutParams;
            JSONObject optJSONObject = this.f4065c.optJSONObject("text");
            JSONObject jSONObject = optJSONObject == null ? new JSONObject() : optJSONObject;
            this.f4064b.m6158a(new C2475k(this.f4066d));
            C2390b c2481l = new C2481l(this.f4066d);
            this.f4064b.m6158a(c2481l);
            this.f4064b.m6158a(new C2451d(c2481l, C2450a.INVSIBLE));
            this.f4064b.m6158a(new C2440b(this.f4066d));
            String b = m4429b();
            if (b != null) {
                c2442c = new C2442c(this.f4066d, b);
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(9);
                c2442c.setLayoutParams(layoutParams);
                c2442c.setCountdownTextColor(-1);
                this.f4064b.m6158a(c2442c);
            }
            if (this.f4065c.has("cta") && !this.f4065c.isNull("cta")) {
                JSONObject jSONObject2 = this.f4065c.getJSONObject("cta");
                c2481l = new C2453e(this.f4066d, jSONObject2.getString("url"), this.f4063a, this.f4075n, jSONObject2.getString("text"));
                LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(10);
                layoutParams2.addRule(11);
                c2481l.setLayoutParams(layoutParams2);
                this.f4064b.m6158a(c2481l);
            }
            Object d = m4431d();
            if (!TextUtils.isEmpty(d)) {
                this.f4064b.m6158a(new C2434a(this.f4066d, d, this.f4075n, new float[]{0.0f, 0.0f, 8.0f, 0.0f}));
            }
            int c = m4430c();
            if (c > 0) {
                c2442c = new C2468i(this.f4066d, c, jSONObject.optString("skipAdIn", "Skip Ad in"), jSONObject.optString("skipAd", "Skip Ad"));
                layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(12);
                layoutParams.addRule(11);
                c2442c.setLayoutParams(layoutParams);
                c2442c.setPadding(0, 0, 0, 30);
                this.f4064b.m6158a(c2442c);
            }
        } else {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public void m4427a(Context context, C1800a c1800a, C2085c c2085c, Bundle bundle, EnumSet<CacheFlag> enumSet) {
        try {
            JSONObject jSONObject = new JSONObject(bundle.getString("ad_response"));
            m4422a(context, c1800a, jSONObject, c2085c, bundle, enumSet, jSONObject.optInt("video_time_polling_interval", 200));
        } catch (JSONException e) {
            c1800a.mo5345a((C1911n) this, AdError.INTERNAL_ERROR);
        }
    }

    /* renamed from: a */
    public void m4428a(Context context, C1800a c1800a, Map<String, Object> map, C2085c c2085c, EnumSet<CacheFlag> enumSet) {
        try {
            C2047d c2047d = (C2047d) map.get("definition");
            m4422a(context, c1800a, (JSONObject) map.get("data"), c2085c, null, enumSet, c2047d == null ? 200 : c2047d.m4990k());
        } catch (JSONException e) {
            c1800a.mo5345a((C1911n) this, AdError.INTERNAL_ERROR);
        }
    }

    /* renamed from: b */
    protected String m4429b() {
        String str = null;
        if (f4062e || this.f4065c != null) {
            try {
                JSONObject jSONObject = this.f4065c.getJSONObject("capabilities");
                if (jSONObject.has("countdown") && !jSONObject.isNull("countdown")) {
                    jSONObject = jSONObject.getJSONObject("countdown");
                    if (jSONObject.has("format")) {
                        str = jSONObject.optString("format");
                    }
                }
            } catch (Throwable e) {
                Log.w(String.valueOf(C1912f.class), "Invalid JSON", e);
            }
            return str;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    protected int m4430c() {
        int i = -1;
        if (f4062e || this.f4065c != null) {
            try {
                JSONObject jSONObject = this.f4065c.getJSONObject("capabilities");
                if (jSONObject.has("skipButton") && !jSONObject.isNull("skipButton")) {
                    jSONObject = jSONObject.getJSONObject("skipButton");
                    if (jSONObject.has("skippableSeconds")) {
                        i = jSONObject.getInt("skippableSeconds");
                    }
                }
            } catch (Throwable e) {
                Log.w(String.valueOf(C1912f.class), "Invalid JSON", e);
            }
            return i;
        }
        throw new AssertionError();
    }

    @Nullable
    /* renamed from: d */
    protected String m4431d() {
        String str = null;
        if (f4062e || this.f4065c != null) {
            try {
                JSONObject jSONObject = this.f4065c.getJSONObject("capabilities");
                if (jSONObject.has("adChoices") && !jSONObject.isNull("adChoices")) {
                    jSONObject = jSONObject.getJSONObject("adChoices");
                    if (jSONObject.has("url")) {
                        str = jSONObject.getString("url");
                    }
                }
            } catch (Throwable e) {
                Log.w(String.valueOf(C1912f.class), "Invalid JSON", e);
            }
            return str;
        }
        throw new AssertionError();
    }

    /* renamed from: e */
    public boolean mo5397e() {
        if (!this.f4073l || this.f4064b == null) {
            return false;
        }
        if (this.f4074m.m6229j() > 0) {
            this.f4064b.m6155a(this.f4074m.m6229j());
        }
        this.f4064b.m6157a(C2389a.AUTO_STARTED);
        return true;
    }

    /* renamed from: f */
    protected void m4433f() {
        if (this.f4063a != null && !this.f4076o) {
            this.f4076o = true;
            this.f4063a.mo5470a(this.f4075n, new HashMap());
            if (this.f4071j != null) {
                this.f4071j.mo5347c(this);
            }
        }
    }

    /* renamed from: g */
    public Bundle mo5398g() {
        if (this.f4074m == null || this.f4065c == null || this.f4064b == null || this.f4064b.getState() == C2501d.IDLE) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("logger", this.f4074m.mo5398g());
        bundle.putString("ad_response", this.f4065c.toString());
        return bundle;
    }

    public String getClientToken() {
        return this.f4075n;
    }

    public void onDestroy() {
        if (this.f4064b != null) {
            this.f4064b.m6165g();
            this.f4064b.m6170l();
        }
        this.f4071j = null;
        this.f4063a = null;
        this.f4072k = null;
        this.f4073l = false;
        this.f4075n = null;
        this.f4064b = null;
        this.f4074m = null;
        this.f4065c = null;
        this.f4066d = null;
        this.f4076o = false;
    }
}
