package com.chartboost.sdk.impl;

import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.C1375d.C1374a;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.aj.C1406a;
import com.facebook.places.model.PlaceFields;
import com.kongregate.p000o.p003a.C0578a;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public final class am extends aj {
    /* renamed from: n */
    private final JSONObject f3028n = new JSONObject();
    /* renamed from: o */
    private final JSONObject f3029o = new JSONObject();
    /* renamed from: p */
    private final JSONObject f3030p = new JSONObject();
    /* renamed from: q */
    private final JSONObject f3031q = new JSONObject();

    public am(String str, ap apVar, C1391a c1391a, int i, C1406a c1406a) {
        super(str, apVar, c1391a, i, c1406a);
    }

    /* renamed from: c */
    protected void mo4284c() {
        boolean z = true;
        C1377e.m3131a(this.f3029o, TapjoyConstants.TJC_APP_PLACEMENT, this.m.f3058s);
        C1377e.m3131a(this.f3029o, String.BUNDLE, this.m.f3049j);
        C1377e.m3131a(this.f3029o, "bundle_id", this.m.f3050k);
        C1377e.m3131a(this.f3029o, "custom_id", C1410i.f2924a);
        C1377e.m3131a(this.f3029o, TapjoyConstants.TJC_SESSION_ID, "");
        C1377e.m3131a(this.f3029o, "ui", Integer.valueOf(-1));
        C1377e.m3131a(this.f3029o, "test_mode", Boolean.valueOf(false));
        C1377e.m3131a(this.f3029o, "certification_providers", C1450o.m3618f());
        m3386a(TapjoyConstants.TJC_APP_PLACEMENT, (Object) this.f3029o);
        C1377e.m3131a(this.f3030p, "carrier", C1377e.m3130a(C1377e.m3128a(TapjoyConstants.TJC_CARRIER_NAME, this.m.f3061v.optString("carrier-name")), C1377e.m3128a(TapjoyConstants.TJC_MOBILE_COUNTRY_CODE, this.m.f3061v.optString("mobile-country-code")), C1377e.m3128a(TapjoyConstants.TJC_MOBILE_NETWORK_CODE, this.m.f3061v.optString("mobile-network-code")), C1377e.m3128a("iso_country_code", this.m.f3061v.optString("iso-country-code")), C1377e.m3128a("phone_type", Integer.valueOf(this.m.f3061v.optInt("phone-type")))));
        C1377e.m3131a(this.f3030p, "model", this.m.f3045f);
        C1377e.m3131a(this.f3030p, TapjoyConstants.TJC_DEVICE_TYPE_NAME, this.m.f3059t);
        C1377e.m3131a(this.f3030p, "actual_device_type", this.m.f3060u);
        C1377e.m3131a(this.f3030p, "os", this.m.f3046g);
        C1377e.m3131a(this.f3030p, "country", this.m.f3047h);
        C1377e.m3131a(this.f3030p, "language", this.m.f3048i);
        C1377e.m3131a(this.f3030p, "timestamp", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.m.f3044e.m3158a())));
        C1377e.m3131a(this.f3030p, "reachability", Integer.valueOf(this.m.f3041b.m3373a()));
        C1377e.m3131a(this.f3030p, "scale", this.m.f3057r);
        C1377e.m3131a(this.f3030p, "is_portrait", Boolean.valueOf(CBUtility.m3111a(CBUtility.m3107a())));
        C1377e.m3131a(this.f3030p, "rooted_device", Boolean.valueOf(this.m.f3062w));
        C1377e.m3131a(this.f3030p, TapjoyConstants.TJC_DEVICE_TIMEZONE, this.m.f3063x);
        C1377e.m3131a(this.f3030p, "mobile_network", this.m.f3064y);
        C1377e.m3131a(this.f3030p, "dw", this.m.f3054o);
        C1377e.m3131a(this.f3030p, "dh", this.m.f3055p);
        C1377e.m3131a(this.f3030p, "dpi", this.m.f3056q);
        C1377e.m3131a(this.f3030p, "w", this.m.f3052m);
        C1377e.m3131a(this.f3030p, "h", this.m.f3053n);
        C1377e.m3131a(this.f3030p, "user_agent", C1410i.f2946w);
        C1377e.m3131a(this.f3030p, "device_family", "");
        C1377e.m3131a(this.f3030p, "retina", Boolean.valueOf(false));
        C1374a a = this.m.f3040a.m3127a();
        C1377e.m3131a(this.f3030p, "identity", a.f2682b);
        if (a.f2681a != -1) {
            if (a.f2681a != 1) {
                z = false;
            }
            C1377e.m3131a(this.f3030p, "limit_ad_tracking", Boolean.valueOf(z));
        }
        C1377e.m3131a(this.f3030p, "pidatauseconsent", Integer.valueOf(C1410i.f2947x.getValue()));
        m3386a("device", (Object) this.f3030p);
        C1377e.m3131a(this.f3028n, "framework", "");
        C1377e.m3131a(this.f3028n, "sdk", this.m.f3051l);
        if (C1410i.f2927d != null) {
            C1377e.m3131a(this.f3028n, "framework_version", C1410i.f2929f);
            C1377e.m3131a(this.f3028n, "wrapper_version", C1410i.f2925b);
        }
        C1377e.m3131a(this.f3028n, "mediation", C1410i.f2931h);
        C1377e.m3131a(this.f3028n, "commit_hash", "d7ce69ccc5a09544389d65501ba55f9bcd5a5b05");
        CharSequence charSequence = ((C1390e) this.m.f3042c.get()).f2793a;
        if (!C1454s.m3627a().m3631a(charSequence)) {
            C1377e.m3131a(this.f3028n, "config_variant", charSequence);
        }
        m3386a("sdk", (Object) this.f3028n);
        C1377e.m3131a(this.f3031q, C0578a.f789b, Integer.valueOf(this.m.f3043d.getInt("cbPrefSessionCount", 0)));
        if (this.f3031q.isNull("cache")) {
            C1377e.m3131a(this.f3031q, "cache", Boolean.valueOf(false));
        }
        if (this.f3031q.isNull("amount")) {
            C1377e.m3131a(this.f3031q, "amount", Integer.valueOf(0));
        }
        if (this.f3031q.isNull("retry_count")) {
            C1377e.m3131a(this.f3031q, "retry_count", Integer.valueOf(0));
        }
        if (this.f3031q.isNull(PlaceFields.LOCATION)) {
            C1377e.m3131a(this.f3031q, PlaceFields.LOCATION, "");
        }
        m3386a("ad", (Object) this.f3031q);
    }

    /* renamed from: a */
    public void m3400a(String str, Object obj, int i) {
        if (i == 0) {
            C1377e.m3131a(this.f3031q, str, obj);
            m3386a("ad", (Object) this.f3031q);
        }
    }
}
