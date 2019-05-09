package com.chartboost.sdk.Model;

import android.content.SharedPreferences;
import android.os.Handler;
import com.chartboost.sdk.C1397c;
import com.chartboost.sdk.C1399d;
import com.chartboost.sdk.C1403e;
import com.chartboost.sdk.C1403e.C1402a;
import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.C1378f;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1434c;
import com.chartboost.sdk.impl.C1434c.C1433a;
import com.chartboost.sdk.impl.C1454s;
import com.chartboost.sdk.impl.C1461u;
import com.chartboost.sdk.impl.C1469v;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.ak;
import com.chartboost.sdk.impl.al;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.bf;
import com.facebook.places.model.PlaceFields;
import java.util.Locale;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Model.c */
public class C1388c {
    /* renamed from: A */
    private boolean f2751A;
    /* renamed from: B */
    private Boolean f2752B = null;
    /* renamed from: C */
    private C1403e f2753C;
    /* renamed from: D */
    private Runnable f2754D;
    /* renamed from: a */
    public final C1434c f2755a;
    /* renamed from: b */
    public final C1378f f2756b;
    /* renamed from: c */
    public final ah f2757c;
    /* renamed from: d */
    public final ap f2758d;
    /* renamed from: e */
    public final C1391a f2759e;
    /* renamed from: f */
    public final Handler f2760f;
    /* renamed from: g */
    public final C1397c f2761g;
    /* renamed from: h */
    public final ak f2762h;
    /* renamed from: i */
    public final C1399d f2763i;
    /* renamed from: j */
    public final al f2764j;
    /* renamed from: k */
    public final C1389d f2765k;
    /* renamed from: l */
    public int f2766l;
    /* renamed from: m */
    public final String f2767m;
    /* renamed from: n */
    public int f2768n;
    /* renamed from: o */
    public final String f2769o;
    /* renamed from: p */
    public final C1386a f2770p;
    /* renamed from: q */
    public final SharedPreferences f2771q;
    /* renamed from: r */
    public boolean f2772r;
    /* renamed from: s */
    public bc f2773s;
    /* renamed from: t */
    public boolean f2774t = false;
    /* renamed from: u */
    public boolean f2775u = false;
    /* renamed from: v */
    public boolean f2776v = false;
    /* renamed from: w */
    public aj f2777w;
    /* renamed from: x */
    public boolean f2778x;
    /* renamed from: y */
    public boolean f2779y = false;
    /* renamed from: z */
    public boolean f2780z = false;

    public C1388c(C1386a c1386a, C1389d c1389d, C1378f c1378f, ah ahVar, ap apVar, SharedPreferences sharedPreferences, C1391a c1391a, Handler handler, C1397c c1397c, ak akVar, C1399d c1399d, al alVar, C1434c c1434c, String str, String str2) {
        this.f2770p = c1386a;
        this.f2755a = c1434c;
        this.f2756b = c1378f;
        this.f2757c = ahVar;
        this.f2758d = apVar;
        this.f2759e = c1391a;
        this.f2760f = handler;
        this.f2761g = c1397c;
        this.f2762h = akVar;
        this.f2763i = c1399d;
        this.f2764j = alVar;
        this.f2765k = c1389d;
        this.f2766l = 0;
        this.f2772r = false;
        this.f2778x = false;
        this.f2780z = true;
        this.f2768n = 3;
        this.f2767m = str;
        this.f2769o = str2;
        this.f2751A = true;
        this.f2771q = sharedPreferences;
    }

    /* renamed from: a */
    public boolean m3174a() {
        this.f2766l = 0;
        if (this.f2770p.f2730b == 0) {
            switch (this.f2755a.f3209a) {
                case 0:
                    if (!this.f2770p.f2744p.equals("video")) {
                        this.f2768n = 0;
                        this.f2753C = new C1461u(this, this.f2760f, this.f2761g);
                        break;
                    }
                    this.f2768n = 1;
                    this.f2753C = new C1469v(this, this.f2756b, this.f2760f, this.f2761g);
                    this.f2751A = false;
                    break;
                case 1:
                    this.f2768n = 2;
                    this.f2753C = new C1469v(this, this.f2756b, this.f2760f, this.f2761g);
                    this.f2751A = false;
                    break;
            }
        }
        switch (this.f2755a.f3209a) {
            case 0:
                if (!this.f2770p.f2744p.equals("video")) {
                    this.f2768n = 0;
                    break;
                }
                this.f2768n = 1;
                this.f2751A = false;
                break;
            case 1:
                this.f2768n = 2;
                this.f2751A = false;
                break;
        }
        this.f2753C = new bf(this, this.f2756b, this.f2757c, this.f2771q, this.f2759e, this.f2760f, this.f2761g, this.f2763i);
        return this.f2753C.mo4297a(this.f2770p.f2729a);
    }

    /* renamed from: b */
    public boolean m3176b() {
        return this.f2751A;
    }

    /* renamed from: c */
    public void m3177c() {
        this.f2780z = true;
        this.f2761g.m3258b(this);
        this.f2765k.mo4309b(this);
    }

    /* renamed from: d */
    public void m3178d() {
        this.f2765k.mo4307a(this);
    }

    /* renamed from: a */
    public boolean m3175a(JSONObject jSONObject) {
        Exception e;
        if (this.f2766l != 2 || this.f2774t) {
            return false;
        }
        String str = this.f2770p.f2738j;
        String str2 = this.f2770p.f2737i;
        if (!str2.isEmpty()) {
            try {
                if (this.f2762h.m3397a(str2)) {
                    try {
                        this.f2752B = Boolean.TRUE;
                    } catch (Exception e2) {
                        e = e2;
                        C1391a.m3206a(getClass(), "onClick", e);
                        if (!this.f2778x) {
                            return false;
                        }
                        this.f2778x = true;
                        this.f2780z = false;
                        m3173a(str2, jSONObject);
                        return true;
                    }
                    if (!this.f2778x) {
                        return false;
                    }
                    this.f2778x = true;
                    this.f2780z = false;
                    m3173a(str2, jSONObject);
                    return true;
                }
                this.f2752B = Boolean.FALSE;
            } catch (Exception e3) {
                Exception exception = e3;
                str2 = str;
                e = exception;
                C1391a.m3206a(getClass(), "onClick", e);
                if (!this.f2778x) {
                    return false;
                }
                this.f2778x = true;
                this.f2780z = false;
                m3173a(str2, jSONObject);
                return true;
            }
        }
        str2 = str;
        if (!this.f2778x) {
            return false;
        }
        this.f2778x = true;
        this.f2780z = false;
        m3173a(str2, jSONObject);
        return true;
    }

    /* renamed from: x */
    private boolean m3169x() {
        return this.f2752B != null;
    }

    /* renamed from: y */
    private boolean m3170y() {
        return this.f2752B.booleanValue();
    }

    /* renamed from: a */
    public void m3171a(CBImpressionError cBImpressionError) {
        this.f2765k.mo4308a(this, cBImpressionError);
    }

    /* renamed from: e */
    public void m3179e() {
        this.f2775u = true;
        this.f2751A = true;
        if (this.f2755a.f3209a == 1 && C1410i.f2926c != null) {
            C1410i.f2926c.didCompleteRewardedVideo(this.f2767m, this.f2770p.f2739k);
        }
        m3197w();
    }

    /* renamed from: f */
    public void m3180f() {
        this.f2776v = true;
    }

    /* renamed from: g */
    public boolean m3181g() {
        if (this.f2753C != null) {
            this.f2753C.m3303b();
            if (this.f2753C.mo4300e() != null) {
                return true;
            }
        }
        CBLogging.m3099b("CBImpression", "reinitializing -- no view protocol exists!!");
        CBLogging.m3104e("CBImpression", "reinitializing -- view not yet created");
        return false;
    }

    /* renamed from: h */
    public void m3182h() {
        m3183i();
        if (this.f2772r) {
            if (this.f2753C != null) {
                this.f2753C.mo4299d();
            }
            this.f2753C = null;
            CBLogging.m3104e("CBImpression", "Destroying the view and view data");
        }
    }

    /* renamed from: i */
    public void m3183i() {
        if (this.f2773s != null) {
            this.f2773s.m3479b();
            try {
                if (!(this.f2753C == null || this.f2753C.mo4300e() == null || this.f2753C.mo4300e().getParent() == null)) {
                    this.f2773s.removeView(this.f2753C.mo4300e());
                }
            } catch (Exception e) {
                CBLogging.m3098a("CBImpression", "Exception raised while cleaning up views", e);
                C1391a.m3206a(getClass(), "cleanUpViews", e);
            }
            this.f2773s = null;
        }
        if (this.f2753C != null) {
            this.f2753C.m3308f();
        }
        CBLogging.m3104e("CBImpression", "Destroying the view");
    }

    /* renamed from: j */
    public CBImpressionError m3184j() {
        try {
            if (this.f2753C != null) {
                return this.f2753C.m3305c();
            }
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "tryCreatingView", e);
        }
        return CBImpressionError.ERROR_CREATING_VIEW;
    }

    /* renamed from: k */
    public C1402a m3185k() {
        if (this.f2753C != null) {
            return this.f2753C.mo4300e();
        }
        return null;
    }

    /* renamed from: l */
    public void m3186l() {
        if (this.f2753C != null && this.f2753C.mo4300e() != null) {
            this.f2753C.mo4300e().setVisibility(8);
        }
    }

    /* renamed from: a */
    public void m3172a(Runnable runnable) {
        this.f2754D = runnable;
    }

    /* renamed from: m */
    public void m3187m() {
        this.f2774t = true;
    }

    /* renamed from: n */
    public void m3188n() {
        if (this.f2754D != null) {
            this.f2754D.run();
            this.f2754D = null;
        }
        this.f2774t = false;
    }

    /* renamed from: o */
    public String m3189o() {
        return this.f2770p.f2734f;
    }

    /* renamed from: p */
    public void m3190p() {
        this.f2765k.mo4310c(this);
    }

    /* renamed from: q */
    public boolean m3191q() {
        if (this.f2753C != null) {
            return this.f2753C.mo4304l();
        }
        return false;
    }

    /* renamed from: r */
    public void m3192r() {
        this.f2778x = false;
        if (this.f2753C != null && this.f2779y) {
            this.f2779y = false;
            this.f2753C.mo4305m();
        }
    }

    /* renamed from: s */
    public void m3193s() {
        this.f2778x = false;
    }

    /* renamed from: t */
    public void m3194t() {
        if (this.f2753C != null && !this.f2779y) {
            this.f2779y = true;
            this.f2753C.mo4306n();
        }
    }

    /* renamed from: u */
    public C1403e m3195u() {
        return this.f2753C;
    }

    /* renamed from: v */
    public boolean m3196v() {
        return this.f2780z;
    }

    /* renamed from: w */
    public void m3197w() {
        C1403e c1403e = null;
        ad ajVar = new aj("/api/video-complete", this.f2758d, this.f2759e, 2, null);
        ajVar.m3386a(PlaceFields.LOCATION, this.f2767m);
        ajVar.m3386a("reward", Integer.valueOf(this.f2770p.f2739k));
        ajVar.m3386a("currency-name", this.f2770p.f2740l);
        ajVar.m3386a("ad_id", m3189o());
        ajVar.m3386a("force_close", Boolean.valueOf(false));
        if (!this.f2770p.f2735g.isEmpty()) {
            ajVar.m3386a("cgn", this.f2770p.f2735g);
        }
        if (m3185k() != null) {
            c1403e = m3195u();
        }
        if (c1403e != null) {
            float k = c1403e.mo4303k();
            float j = c1403e.mo4302j();
            CBLogging.m3097a(getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", new Object[]{Float.valueOf(j), Float.valueOf(k)}));
            ajVar.m3386a("total_time", Float.valueOf(j / 1000.0f));
            if (k <= 0.0f) {
                ajVar.m3386a("playback_time", Float.valueOf(j / 1000.0f));
            } else {
                ajVar.m3386a("playback_time", Float.valueOf(k / 1000.0f));
            }
        }
        this.f2757c.m3371a(ajVar);
        this.f2759e.m3223b(this.f2755a.m3538a(this.f2770p.f2730b), m3189o());
    }

    /* renamed from: a */
    void m3173a(String str, JSONObject jSONObject) {
        Handler handler = this.f2760f;
        C1434c c1434c = this.f2755a;
        c1434c.getClass();
        handler.post(new C1433a(c1434c, 1, this.f2767m, null));
        if (m3176b() && this.f2766l == 2) {
            C1399d c = this.f2761g.m3260c();
            if (c != null) {
                c.m3284b(this);
            }
        }
        if ((!C1454s.m3627a().m3631a((CharSequence) str) ? 1 : 0) != 0) {
            aj ajVar = new aj("/api/click", this.f2758d, this.f2759e, 2, null);
            if (!this.f2770p.f2734f.isEmpty()) {
                ajVar.m3386a("ad_id", this.f2770p.f2734f);
            }
            if (!this.f2770p.f2741m.isEmpty()) {
                ajVar.m3386a("to", this.f2770p.f2741m);
            }
            if (!this.f2770p.f2735g.isEmpty()) {
                ajVar.m3386a("cgn", this.f2770p.f2735g);
            }
            if (!this.f2770p.f2736h.isEmpty()) {
                ajVar.m3386a("creative", this.f2770p.f2736h);
            }
            if (this.f2768n == 1 || this.f2768n == 2) {
                C1403e c1403e;
                if (this.f2770p.f2730b == 0 && m3185k() != null) {
                    c1403e = (C1469v) m3195u();
                } else if (this.f2770p.f2730b != 1 || m3185k() == null) {
                    c1403e = null;
                } else {
                    bf bfVar = (bf) m3195u();
                }
                if (c1403e != null) {
                    float k = c1403e.mo4303k();
                    float j = c1403e.mo4302j();
                    CBLogging.m3097a(getClass().getSimpleName(), String.format(Locale.US, "TotalDuration: %f PlaybackTime: %f", new Object[]{Float.valueOf(j), Float.valueOf(k)}));
                    ajVar.m3386a("total_time", Float.valueOf(j / 1000.0f));
                    if (k <= 0.0f) {
                        ajVar.m3386a("playback_time", Float.valueOf(j / 1000.0f));
                    } else {
                        ajVar.m3386a("playback_time", Float.valueOf(k / 1000.0f));
                    }
                }
            }
            if (jSONObject != null) {
                ajVar.m3386a("click_coordinates", (Object) jSONObject);
            }
            ajVar.m3386a(PlaceFields.LOCATION, this.f2767m);
            if (m3169x()) {
                ajVar.m3386a("retarget_reinstall", Boolean.valueOf(m3170y()));
            }
            this.f2777w = ajVar;
            this.f2762h.m3395a(this, str, null);
        } else {
            this.f2762h.m3396a(this, false, str, CBClickError.URI_INVALID, null);
        }
        this.f2759e.m3227c(this.f2755a.m3538a(this.f2770p.f2730b), this.f2767m, m3189o());
    }
}
