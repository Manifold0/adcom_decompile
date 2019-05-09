package com.applovin.impl.adview;

import com.applovin.impl.sdk.bu;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.tapjoy.TJAdUnitConstants;
import org.json.JSONObject;

public class cm {
    /* renamed from: a */
    private final AppLovinLogger f1864a;
    /* renamed from: b */
    private int f1865b;
    /* renamed from: c */
    private int f1866c;
    /* renamed from: d */
    private int f1867d;
    /* renamed from: e */
    private int f1868e;
    /* renamed from: f */
    private boolean f1869f;
    /* renamed from: g */
    private int f1870g;
    /* renamed from: h */
    private int f1871h;
    /* renamed from: i */
    private int f1872i;
    /* renamed from: j */
    private float f1873j;
    /* renamed from: k */
    private float f1874k;

    public cm(JSONObject jSONObject, AppLovinSdk appLovinSdk) {
        this.f1864a = appLovinSdk.getLogger();
        this.f1864a.mo4175i("VideoButtonProperties", "Updating video button properties with JSON = " + jSONObject);
        this.f1865b = bu.m2385a(jSONObject, "width", 64, appLovinSdk);
        this.f1866c = bu.m2385a(jSONObject, "height", 7, appLovinSdk);
        this.f1867d = bu.m2385a(jSONObject, "margin", 20, appLovinSdk);
        this.f1868e = bu.m2385a(jSONObject, "gravity", 85, appLovinSdk);
        this.f1869f = bu.m2387a(jSONObject, "tap_to_fade", Boolean.valueOf(false), appLovinSdk).booleanValue();
        this.f1870g = bu.m2385a(jSONObject, "tap_to_fade_duration_milliseconds", (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL, appLovinSdk);
        this.f1871h = bu.m2385a(jSONObject, "fade_in_duration_milliseconds", (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL, appLovinSdk);
        this.f1872i = bu.m2385a(jSONObject, "fade_out_duration_milliseconds", (int) TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL, appLovinSdk);
        this.f1873j = bu.m2384a(jSONObject, "fade_in_delay_seconds", 1.0f, appLovinSdk);
        this.f1874k = bu.m2384a(jSONObject, "fade_out_delay_seconds", 6.0f, appLovinSdk);
    }

    /* renamed from: a */
    public int m2083a() {
        return this.f1865b;
    }

    /* renamed from: b */
    public int m2084b() {
        return this.f1866c;
    }

    /* renamed from: c */
    public int m2085c() {
        return this.f1867d;
    }

    /* renamed from: d */
    public int m2086d() {
        return this.f1868e;
    }

    /* renamed from: e */
    public boolean m2087e() {
        return this.f1869f;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        cm cmVar = (cm) obj;
        if (this.f1865b != cmVar.f1865b || this.f1866c != cmVar.f1866c || this.f1867d != cmVar.f1867d || this.f1868e != cmVar.f1868e || this.f1869f != cmVar.f1869f || this.f1870g != cmVar.f1870g || this.f1871h != cmVar.f1871h || this.f1872i != cmVar.f1872i || Float.compare(cmVar.f1873j, this.f1873j) != 0) {
            return false;
        }
        if (Float.compare(cmVar.f1874k, this.f1874k) != 0) {
            z = false;
        }
        return z;
    }

    /* renamed from: f */
    public long m2088f() {
        return (long) this.f1870g;
    }

    /* renamed from: g */
    public long m2089g() {
        return (long) this.f1871h;
    }

    /* renamed from: h */
    public long m2090h() {
        return (long) this.f1872i;
    }

    public int hashCode() {
        int i = 0;
        int floatToIntBits = ((this.f1873j != 0.0f ? Float.floatToIntBits(this.f1873j) : 0) + (((((((((this.f1869f ? 1 : 0) + (((((((this.f1865b * 31) + this.f1866c) * 31) + this.f1867d) * 31) + this.f1868e) * 31)) * 31) + this.f1870g) * 31) + this.f1871h) * 31) + this.f1872i) * 31)) * 31;
        if (this.f1874k != 0.0f) {
            i = Float.floatToIntBits(this.f1874k);
        }
        return floatToIntBits + i;
    }

    /* renamed from: i */
    public float m2091i() {
        return this.f1873j;
    }

    /* renamed from: j */
    public float m2092j() {
        return this.f1874k;
    }

    public String toString() {
        return "VideoButtonProperties{widthPercentOfScreen=" + this.f1865b + ", heightPercentOfScreen=" + this.f1866c + ", margin=" + this.f1867d + ", gravity=" + this.f1868e + ", tapToFade=" + this.f1869f + ", tapToFadeDurationMillis=" + this.f1870g + ", fadeInDurationMillis=" + this.f1871h + ", fadeOutDurationMillis=" + this.f1872i + ", fadeInDelay=" + this.f1873j + ", fadeOutDelay=" + this.f1874k + '}';
    }
}
