package com.facebook.ads;

import android.graphics.Typeface;
import com.facebook.ads.internal.p017t.C2117j;
import com.facebook.ads.internal.p021o.C2058a;
import com.facebook.ads.internal.p021o.C2059b;
import org.json.JSONObject;

public class NativeAdViewAttributes {
    /* renamed from: a */
    private final C2117j f3801a;

    public NativeAdViewAttributes() {
        this.f3801a = new C2117j();
    }

    NativeAdViewAttributes(C2117j c2117j) {
        this.f3801a = c2117j;
    }

    public NativeAdViewAttributes(JSONObject jSONObject) {
        C2117j c2117j;
        try {
            c2117j = new C2117j(jSONObject);
        } catch (Throwable e) {
            Throwable th = e;
            c2117j = new C2117j();
            C2059b.m5023a(C2058a.m5020a(th, "Error retrieving native ui configuration data"));
        }
        this.f3801a = c2117j;
    }

    /* renamed from: a */
    C2117j m4109a() {
        return this.f3801a;
    }

    public boolean getAutoplay() {
        return this.f3801a.m5379j();
    }

    public boolean getAutoplayOnMobile() {
        return this.f3801a.m5380k();
    }

    public int getBackgroundColor() {
        return this.f3801a.m5363b();
    }

    public int getButtonBorderColor() {
        return this.f3801a.m5376g();
    }

    public int getButtonColor() {
        return this.f3801a.m5372e();
    }

    public int getButtonTextColor() {
        return this.f3801a.m5374f();
    }

    public int getDescriptionTextColor() {
        return this.f3801a.m5370d();
    }

    public int getDescriptionTextSize() {
        return this.f3801a.m5378i();
    }

    public int getTitleTextColor() {
        return this.f3801a.m5367c();
    }

    public int getTitleTextSize() {
        return this.f3801a.m5377h();
    }

    public Typeface getTypeface() {
        return this.f3801a.m5358a();
    }

    public NativeAdViewAttributes setAutoplay(boolean z) {
        this.f3801a.m5366b(z);
        return this;
    }

    public NativeAdViewAttributes setAutoplayOnMobile(boolean z) {
        this.f3801a.m5362a(z);
        return this;
    }

    public NativeAdViewAttributes setBackgroundColor(int i) {
        this.f3801a.m5359a(i);
        return this;
    }

    public NativeAdViewAttributes setButtonBorderColor(int i) {
        this.f3801a.m5375f(i);
        return this;
    }

    public NativeAdViewAttributes setButtonColor(int i) {
        this.f3801a.m5371d(i);
        return this;
    }

    public NativeAdViewAttributes setButtonTextColor(int i) {
        this.f3801a.m5373e(i);
        return this;
    }

    public NativeAdViewAttributes setDescriptionTextColor(int i) {
        this.f3801a.m5368c(i);
        return this;
    }

    public NativeAdViewAttributes setTitleTextColor(int i) {
        this.f3801a.m5364b(i);
        return this;
    }

    public NativeAdViewAttributes setTypeface(Typeface typeface) {
        this.f3801a.m5360a(typeface);
        return this;
    }
}
