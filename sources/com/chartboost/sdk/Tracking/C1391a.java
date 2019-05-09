package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import android.util.Log;
import com.chartboost.sdk.C1392b;
import com.chartboost.sdk.Libraries.C1372b;
import com.chartboost.sdk.Libraries.C1377e;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1390e;
import com.facebook.places.model.PlaceFields;
import com.kongregate.p000o.p003a.C0578a;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.chartboost.sdk.Tracking.a */
public class C1391a implements C1372b {
    /* renamed from: d */
    private static C1391a f2823d;
    /* renamed from: g */
    private static final Long f2824g = Long.valueOf(TimeUnit.MINUTES.toMillis(5));
    /* renamed from: e */
    private final AtomicReference<C1390e> f2825e;
    /* renamed from: f */
    private boolean f2826f = false;
    /* renamed from: h */
    private long f2827h = (System.currentTimeMillis() - f2824g.longValue());

    public C1391a(AtomicReference<C1390e> atomicReference) {
        f2823d = this;
        this.f2825e = atomicReference;
    }

    /* renamed from: a */
    public void m3211a() {
        m3207a(String.VIDEO_START);
        m3207a("did-become-active");
    }

    /* renamed from: a */
    private void m3207a(String str) {
        if (((C1390e) this.f2825e.get()).f2806n) {
            m3208a(C0578a.f789b, str, null, null, null, false);
        }
    }

    /* renamed from: b */
    public void m3222b() {
        m3210c();
    }

    /* renamed from: c */
    private void m3210c() {
        if (((C1390e) this.f2825e.get()).f2806n) {
            m3219a(C0578a.f789b, "end", null, null, null, null, null, false);
            m3207a("did-become-active");
        }
    }

    /* renamed from: a */
    public void m3216a(String str, String str2, String str3, String str4) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3219a("webview-track", str, str2, str3, str4, null, null, false);
        }
    }

    /* renamed from: a */
    public void m3221a(JSONObject jSONObject) {
        C1390e c1390e = (C1390e) this.f2825e.get();
        if (c1390e.f2807o) {
            m3219a("folder", C1392b.m3232a(c1390e), null, null, null, null, jSONObject, false);
        }
    }

    /* renamed from: a */
    public void m3215a(String str, String str2, String str3) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("load", str, str2, str3, null, false);
        }
    }

    /* renamed from: b */
    public void m3224b(String str, String str2, String str3) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("ad-show", str, str2, str3, null, false);
        }
    }

    /* renamed from: c */
    public void m3227c(String str, String str2, String str3) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("ad-click", str, str2, str3, null, false);
        }
    }

    /* renamed from: d */
    public void m3229d(String str, String str2, String str3) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("ad-close", str, str2, str3, null, false);
        }
    }

    /* renamed from: e */
    public void m3231e(String str, String str2, String str3) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("ad-dismiss", str, str2, str3, null, false);
        }
    }

    /* renamed from: a */
    public void m3220a(String str, String str2, String str3, String str4, boolean z) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            String str5;
            String str6 = "ad-error";
            if (TextUtils.isEmpty(str3)) {
                str5 = "empty-adid";
            } else {
                str5 = str3;
            }
            m3208a(str6, str, str2, str5, str4, z);
        }
    }

    /* renamed from: a */
    public void m3217a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        if (((C1390e) this.f2825e.get()).f2804l) {
            m3219a("ad-unit-error", str, str2, str3, str4, null, C1377e.m3130a(C1377e.m3128a("adId", (Object) str5), C1377e.m3128a(PlaceFields.LOCATION, (Object) str6), C1377e.m3128a("state", (Object) str7)), true);
        }
    }

    /* renamed from: b */
    public void m3225b(String str, String str2, String str3, String str4) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            String str5;
            String str6 = "ad-warning";
            if (TextUtils.isEmpty(str3)) {
                str5 = "empty-adid";
            } else {
                str5 = str3;
            }
            m3208a(str6, str, str2, str5, str4, false);
        }
    }

    /* renamed from: a */
    public void m3213a(String str, String str2) {
        C1390e c1390e = (C1390e) this.f2825e.get();
        if (c1390e.f2807o) {
            m3219a("download-asset-start", C1392b.m3232a(c1390e), str, str2, null, null, null, false);
        }
    }

    /* renamed from: a */
    public void m3214a(String str, String str2, long j, long j2, long j3) {
        if (((C1390e) this.f2825e.get()).f2808p) {
            m3219a("download-asset-failure", str, str2, null, null, null, C1377e.m3130a(C1377e.m3128a("processingMs", Long.valueOf(j)), C1377e.m3128a("getResponseCodeMs", Long.valueOf(j2)), C1377e.m3128a("readDataMs", Long.valueOf(j3))), false);
        }
    }

    /* renamed from: a */
    public void m3212a(String str, long j, long j2, long j3) {
        if (((C1390e) this.f2825e.get()).f2808p) {
            m3219a("download-asset-success", str, null, null, null, null, C1377e.m3130a(C1377e.m3128a("processingMs", Long.valueOf(j)), C1377e.m3128a("getResposeCodeMs", Long.valueOf(j2)), C1377e.m3128a("readDataMs", Long.valueOf(j3))), false);
        }
    }

    /* renamed from: b */
    public void m3223b(String str, String str2) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("playback-complete", str, str2, null, null, false);
        }
    }

    /* renamed from: c */
    public void m3226c(String str, String str2) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("replay", str, str2, null, null, false);
        }
    }

    /* renamed from: d */
    public void m3228d(String str, String str2) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("playback-start", str, str2, null, null, false);
        }
    }

    /* renamed from: e */
    public void m3230e(String str, String str2) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3208a("playback-stop", str, str2, null, null, false);
        }
    }

    /* renamed from: a */
    public static void m3206a(Class cls, String str, Exception exception) {
        exception.printStackTrace();
        C1391a c1391a = f2823d;
        if (c1391a != null) {
            c1391a.m3209b(cls, str, exception);
        }
    }

    /* renamed from: b */
    private synchronized void m3209b(Class cls, String str, Exception exception) {
        String str2 = null;
        synchronized (this) {
            if (this.f2825e != null) {
                C1390e c1390e = (C1390e) this.f2825e.get();
                if (!(c1390e == null || !c1390e.f2803k || this.f2826f)) {
                    this.f2826f = true;
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.f2827h >= f2824g.longValue()) {
                            if (c1390e.f2810r) {
                                str2 = Log.getStackTraceString(exception);
                            }
                            m3219a("exception", cls.getName(), str, exception.getClass().getName(), exception.getMessage(), str2, null, true);
                            this.f2827h = currentTimeMillis;
                        }
                        this.f2826f = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.f2826f = false;
                    } catch (Throwable th) {
                        this.f2826f = false;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void m3218a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            m3219a(str, str2, str3, str4, str5, str6, jSONObject, false);
        }
    }

    /* renamed from: a */
    private void m3208a(String str, String str2, String str3, String str4, String str5, boolean z) {
        m3219a(str, str2, str3, str4, str5, null, new JSONObject(), z);
    }

    /* renamed from: a */
    public void m3219a(String str, String str2, String str3, String str4, String str5, String str6, JSONObject jSONObject, boolean z) {
        if (((C1390e) this.f2825e.get()).f2807o) {
            String str7 = "CBTrack";
            if (str == null) {
                str = "unknown event";
            }
            CBLogging.m3097a(str7, str);
        }
    }
}
