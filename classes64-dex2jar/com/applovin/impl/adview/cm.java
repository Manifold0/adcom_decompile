// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.impl.sdk.bu;
import com.applovin.sdk.AppLovinSdk;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinLogger;

public class cm
{
    private final AppLovinLogger a;
    private int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private int g;
    private int h;
    private int i;
    private float j;
    private float k;
    
    public cm(final JSONObject jsonObject, final AppLovinSdk appLovinSdk) {
        (this.a = appLovinSdk.getLogger()).i("VideoButtonProperties", "Updating video button properties with JSON = " + jsonObject);
        this.b = bu.a(jsonObject, "width", 64, appLovinSdk);
        this.c = bu.a(jsonObject, "height", 7, appLovinSdk);
        this.d = bu.a(jsonObject, "margin", 20, appLovinSdk);
        this.e = bu.a(jsonObject, "gravity", 85, appLovinSdk);
        this.f = bu.a(jsonObject, "tap_to_fade", false, appLovinSdk);
        this.g = bu.a(jsonObject, "tap_to_fade_duration_milliseconds", 500, appLovinSdk);
        this.h = bu.a(jsonObject, "fade_in_duration_milliseconds", 500, appLovinSdk);
        this.i = bu.a(jsonObject, "fade_out_duration_milliseconds", 500, appLovinSdk);
        this.j = bu.a(jsonObject, "fade_in_delay_seconds", 1.0f, appLovinSdk);
        this.k = bu.a(jsonObject, "fade_out_delay_seconds", 6.0f, appLovinSdk);
    }
    
    public int a() {
        return this.b;
    }
    
    public int b() {
        return this.c;
    }
    
    public int c() {
        return this.d;
    }
    
    public int d() {
        return this.e;
    }
    
    public boolean e() {
        return this.f;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        final boolean b2 = false;
        boolean b3;
        if (this == o) {
            b3 = true;
        }
        else {
            b3 = b2;
            if (o != null) {
                b3 = b2;
                if (this.getClass() == o.getClass()) {
                    final cm cm = (cm)o;
                    b3 = b2;
                    if (this.b == cm.b) {
                        b3 = b2;
                        if (this.c == cm.c) {
                            b3 = b2;
                            if (this.d == cm.d) {
                                b3 = b2;
                                if (this.e == cm.e) {
                                    b3 = b2;
                                    if (this.f == cm.f) {
                                        b3 = b2;
                                        if (this.g == cm.g) {
                                            b3 = b2;
                                            if (this.h == cm.h) {
                                                b3 = b2;
                                                if (this.i == cm.i) {
                                                    b3 = b2;
                                                    if (Float.compare(cm.j, this.j) == 0) {
                                                        return Float.compare(cm.k, this.k) == 0 && b;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b3;
    }
    
    public long f() {
        return this.g;
    }
    
    public long g() {
        return this.h;
    }
    
    public long h() {
        return this.i;
    }
    
    @Override
    public int hashCode() {
        int floatToIntBits = 0;
        final int b = this.b;
        final int c = this.c;
        final int d = this.d;
        final int e = this.e;
        int n;
        if (this.f) {
            n = 1;
        }
        else {
            n = 0;
        }
        final int g = this.g;
        final int h = this.h;
        final int i = this.i;
        int floatToIntBits2;
        if (this.j != 0.0f) {
            floatToIntBits2 = Float.floatToIntBits(this.j);
        }
        else {
            floatToIntBits2 = 0;
        }
        if (this.k != 0.0f) {
            floatToIntBits = Float.floatToIntBits(this.k);
        }
        return (floatToIntBits2 + ((((n + (((b * 31 + c) * 31 + d) * 31 + e) * 31) * 31 + g) * 31 + h) * 31 + i) * 31) * 31 + floatToIntBits;
    }
    
    public float i() {
        return this.j;
    }
    
    public float j() {
        return this.k;
    }
    
    @Override
    public String toString() {
        return "VideoButtonProperties{widthPercentOfScreen=" + this.b + ", heightPercentOfScreen=" + this.c + ", margin=" + this.d + ", gravity=" + this.e + ", tapToFade=" + this.f + ", tapToFadeDurationMillis=" + this.g + ", fadeInDurationMillis=" + this.h + ", fadeOutDurationMillis=" + this.i + ", fadeInDelay=" + this.j + ", fadeOutDelay=" + this.k + '}';
    }
}
