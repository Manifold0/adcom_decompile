package com.chartboost.sdk.impl;

import com.chartboost.sdk.C1410i;
import com.chartboost.sdk.Libraries.C1373c;
import com.chartboost.sdk.Libraries.C1375d.C1374a;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.C1377e.C1376a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.C1385a;
import com.chartboost.sdk.Tracking.C1391a;
import com.facebook.share.internal.ShareConstants;
import com.kongregate.p000o.p002g.C0640a;
import com.kongregate.p000o.p003a.C0578a;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyConstants;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class aj extends ad<JSONObject> {
    /* renamed from: a */
    public final JSONObject f3008a = new JSONObject();
    /* renamed from: k */
    public final C1406a f3009k;
    /* renamed from: l */
    public boolean f3010l = false;
    /* renamed from: m */
    protected final ap f3011m;
    /* renamed from: n */
    private final String f3012n;
    /* renamed from: o */
    private String f3013o;
    /* renamed from: p */
    private final C1391a f3014p;

    /* renamed from: com.chartboost.sdk.impl.aj$a */
    public interface C1406a {
        /* renamed from: a */
        void mo4277a(aj ajVar, CBError cBError);

        /* renamed from: a */
        void mo4278a(aj ajVar, JSONObject jSONObject);
    }

    public aj(String str, ap apVar, C1391a c1391a, int i, C1406a c1406a) {
        super("POST", m3380a(str), i, null);
        this.f3012n = str;
        this.f3011m = apVar;
        this.f3014p = c1391a;
        this.f3009k = c1406a;
    }

    /* renamed from: a */
    public static String m3380a(String str) {
        String str2 = "%s%s%s";
        Object[] objArr = new Object[3];
        objArr[0] = "https://live.chartboost.com";
        String str3 = (str == null || !str.startsWith("/")) ? "/" : "";
        objArr[1] = str3;
        if (str == null) {
            str = "";
        }
        objArr[2] = str;
        return String.format(str2, objArr);
    }

    /* renamed from: a */
    public void m3386a(String str, Object obj) {
        C1377e.m3131a(this.f3008a, str, obj);
    }

    /* renamed from: c */
    protected void mo4284c() {
        boolean z = true;
        m3386a(TapjoyConstants.TJC_APP_PLACEMENT, this.f3011m.f3058s);
        m3386a("model", this.f3011m.f3045f);
        m3386a(TapjoyConstants.TJC_DEVICE_TYPE_NAME, this.f3011m.f3059t);
        m3386a("actual_device_type", this.f3011m.f3060u);
        m3386a("os", this.f3011m.f3046g);
        m3386a("country", this.f3011m.f3047h);
        m3386a("language", this.f3011m.f3048i);
        m3386a("sdk", this.f3011m.f3051l);
        m3386a("user_agent", C1410i.f2946w);
        m3386a("timestamp", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(this.f3011m.f3044e.m3158a())));
        m3386a(C0578a.f789b, Integer.valueOf(this.f3011m.f3043d.getInt("cbPrefSessionCount", 0)));
        m3386a("reachability", Integer.valueOf(this.f3011m.f3041b.m3373a()));
        m3386a("scale", this.f3011m.f3057r);
        m3386a("is_portrait", Boolean.valueOf(CBUtility.m3111a(CBUtility.m3107a())));
        m3386a(String.BUNDLE, this.f3011m.f3049j);
        m3386a("bundle_id", this.f3011m.f3050k);
        m3386a("carrier", this.f3011m.f3061v);
        m3386a("custom_id", C1410i.f2924a);
        m3386a("mediation", C1410i.f2931h);
        if (C1410i.f2927d != null) {
            m3386a("framework_version", C1410i.f2929f);
            m3386a("wrapper_version", C1410i.f2925b);
        }
        m3386a("rooted_device", Boolean.valueOf(this.f3011m.f3062w));
        m3386a(TapjoyConstants.TJC_DEVICE_TIMEZONE, this.f3011m.f3063x);
        m3386a("mobile_network", this.f3011m.f3064y);
        m3386a("dw", this.f3011m.f3054o);
        m3386a("dh", this.f3011m.f3055p);
        m3386a("dpi", this.f3011m.f3056q);
        m3386a("w", this.f3011m.f3052m);
        m3386a("h", this.f3011m.f3053n);
        m3386a("commit_hash", (Object) "d7ce69ccc5a09544389d65501ba55f9bcd5a5b05");
        C1374a a = this.f3011m.f3040a.m3127a();
        m3386a("identity", a.f2682b);
        if (a.f2681a != -1) {
            if (a.f2681a != 1) {
                z = false;
            }
            m3386a("limit_ad_tracking", Boolean.valueOf(z));
        }
        m3386a("pidatauseconsent", Integer.valueOf(C1410i.f2947x.getValue()));
        Object obj = ((C1390e) this.f3011m.f3042c.get()).f2793a;
        if (!C1454s.m3627a().m3631a((CharSequence) obj)) {
            m3386a("config_variant", obj);
        }
        m3386a("certification_providers", C1450o.m3617e());
    }

    /* renamed from: d */
    public String m3390d() {
        return m3391e();
    }

    /* renamed from: e */
    public String m3391e() {
        if (this.f3012n == null) {
            return "/";
        }
        return (this.f3012n.startsWith("/") ? "" : "/") + this.f3012n;
    }

    /* renamed from: a */
    private void m3381a(ag agVar, CBError cBError) {
        Object obj;
        C1376a[] c1376aArr = new C1376a[5];
        c1376aArr[0] = C1377e.m3128a("endpoint", m3391e());
        String str = "statuscode";
        if (agVar == null) {
            obj = "None";
        } else {
            obj = Integer.valueOf(agVar.f2995a);
        }
        c1376aArr[1] = C1377e.m3128a(str, obj);
        str = "error";
        if (cBError == null) {
            obj = "None";
        } else {
            obj = cBError.m3161a().toString();
        }
        c1376aArr[2] = C1377e.m3128a(str, obj);
        str = "errorDescription";
        if (cBError == null) {
            obj = "None";
        } else {
            obj = cBError.m3162b();
        }
        c1376aArr[3] = C1377e.m3128a(str, obj);
        c1376aArr[4] = C1377e.m3128a("retryCount", Integer.valueOf(0));
        this.f3014p.m3218a("request_manager", ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, cBError == null ? "success" : "failure", null, null, null, C1377e.m3130a(c1376aArr));
    }

    /* renamed from: b */
    public void m3388b(String str) {
        this.f3013o = str;
    }

    /* renamed from: a */
    public ae mo4280a() {
        mo4284c();
        String jSONObject = this.f3008a.toString();
        String str = C1410i.f2934k;
        String str2 = C1410i.f2935l;
        str2 = C1373c.m3123b(C1373c.m3122a(String.format(Locale.US, "%s %s\n%s\n%s", new Object[]{this.b, m3390d(), str2, jSONObject}).getBytes()));
        Map hashMap = new HashMap();
        hashMap.put("Accept", C0640a.f1003a);
        hashMap.put("X-Chartboost-Client", CBUtility.m3114b());
        hashMap.put("X-Chartboost-API", "7.3.0");
        hashMap.put("X-Chartboost-App", str);
        hashMap.put("X-Chartboost-Signature", str2);
        return new ae(hashMap, jSONObject.getBytes(), C0640a.f1003a);
    }

    /* renamed from: a */
    public af<JSONObject> mo4281a(ag agVar) {
        try {
            if (agVar.f2996b == null) {
                return af.m3369a(new CBError(C1385a.INVALID_RESPONSE, "Response is not a valid json object"));
            }
            Object jSONObject = new JSONObject(new String(agVar.f2996b));
            CBLogging.m3101c("CBRequest", "Request " + m3391e() + " succeeded. Response code: " + agVar.f2995a + ", body: " + jSONObject.toString(4));
            if (this.f3010l) {
                int optInt = jSONObject.optInt("status");
                if (optInt == 404) {
                    return af.m3369a(new CBError(C1385a.HTTP_NOT_FOUND, "404 error from server"));
                }
                if (optInt < 200 || optInt > 299) {
                    String str = "Request failed due to status code " + optInt + " in message";
                    CBLogging.m3099b("CBRequest", str);
                    return af.m3369a(new CBError(C1385a.UNEXPECTED_RESPONSE, str));
                }
            }
            return af.m3370a(jSONObject);
        } catch (Exception e) {
            C1391a.m3206a(getClass(), "parseServerResponse", e);
            return af.m3369a(new CBError(C1385a.MISCELLANEOUS, e.getLocalizedMessage()));
        }
    }

    /* renamed from: a */
    public void m3387a(JSONObject jSONObject, ag agVar) {
        if (!(this.f3009k == null || jSONObject == null)) {
            this.f3009k.mo4278a(this, jSONObject);
        }
        if (this.f3014p != null) {
            m3381a(agVar, null);
        }
    }

    /* renamed from: a */
    public void mo4282a(CBError cBError, ag agVar) {
        if (cBError != null) {
            if (this.f3009k != null) {
                this.f3009k.mo4277a(this, cBError);
            }
            if (this.f3014p != null) {
                m3381a(agVar, cBError);
            }
        }
    }
}
