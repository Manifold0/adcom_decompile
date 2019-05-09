package com.facebook.ads.internal.p017t;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.TextView;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.settings.AdInternalSettings;
import org.json.JSONObject;

/* renamed from: com.facebook.ads.internal.t.j */
public class C2117j {
    /* renamed from: a */
    private Typeface f4878a = Typeface.create(Typeface.SANS_SERIF, 0);
    /* renamed from: b */
    private int f4879b = -1;
    /* renamed from: c */
    private int f4880c = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: d */
    private int f4881d = -11643291;
    /* renamed from: e */
    private int f4882e = 0;
    /* renamed from: f */
    private int f4883f = -12420889;
    /* renamed from: g */
    private int f4884g = -12420889;
    /* renamed from: h */
    private boolean f4885h = AdInternalSettings.isVideoAutoplay();
    /* renamed from: i */
    private boolean f4886i = AdInternalSettings.isVideoAutoplayOnMobile();

    public C2117j(JSONObject jSONObject) {
        this.f4879b = jSONObject.getBoolean("background_transparent") ? 0 : Color.parseColor(jSONObject.getString("background_color"));
        this.f4880c = Color.parseColor(jSONObject.getString("title_text_color"));
        this.f4881d = Color.parseColor(jSONObject.getString("description_text_color"));
        this.f4882e = jSONObject.getBoolean("button_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_color"));
        this.f4884g = jSONObject.getBoolean("button_border_transparent") ? 0 : Color.parseColor(jSONObject.getString("button_border_color"));
        this.f4883f = Color.parseColor(jSONObject.getString("button_text_color"));
        this.f4878a = Typeface.create(jSONObject.getString("android_typeface"), 0);
    }

    /* renamed from: a */
    public Typeface m5358a() {
        return this.f4878a;
    }

    /* renamed from: a */
    public void m5359a(int i) {
        this.f4879b = i;
    }

    /* renamed from: a */
    public void m5360a(Typeface typeface) {
        this.f4878a = typeface;
    }

    /* renamed from: a */
    public void m5361a(TextView textView) {
        textView.setTextColor(this.f4880c);
        textView.setTextSize(16.0f);
        textView.setTypeface(this.f4878a, 1);
    }

    /* renamed from: a */
    public void m5362a(boolean z) {
        this.f4886i = z;
    }

    /* renamed from: b */
    public int m5363b() {
        return this.f4879b;
    }

    /* renamed from: b */
    public void m5364b(int i) {
        this.f4880c = i;
    }

    /* renamed from: b */
    public void m5365b(TextView textView) {
        textView.setTextColor(this.f4881d);
        textView.setTextSize(10.0f);
        textView.setTypeface(this.f4878a);
    }

    /* renamed from: b */
    public void m5366b(boolean z) {
        this.f4885h = z;
    }

    /* renamed from: c */
    public int m5367c() {
        return this.f4880c;
    }

    /* renamed from: c */
    public void m5368c(int i) {
        this.f4881d = i;
    }

    /* renamed from: c */
    public void m5369c(TextView textView) {
        C2600x.m6680a((View) textView, this.f4882e);
        textView.setTextColor(this.f4883f);
        textView.setTextSize(2, 14.0f);
        textView.setTypeface(this.f4878a, 1);
    }

    /* renamed from: d */
    public int m5370d() {
        return this.f4881d;
    }

    /* renamed from: d */
    public void m5371d(int i) {
        this.f4882e = i;
    }

    /* renamed from: e */
    public int m5372e() {
        return this.f4882e;
    }

    /* renamed from: e */
    public void m5373e(int i) {
        this.f4883f = i;
    }

    /* renamed from: f */
    public int m5374f() {
        return this.f4883f;
    }

    /* renamed from: f */
    public void m5375f(int i) {
        this.f4884g = i;
    }

    /* renamed from: g */
    public int m5376g() {
        return this.f4884g;
    }

    /* renamed from: h */
    public int m5377h() {
        return 16;
    }

    /* renamed from: i */
    public int m5378i() {
        return 10;
    }

    /* renamed from: j */
    public boolean m5379j() {
        return this.f4885h;
    }

    /* renamed from: k */
    public boolean m5380k() {
        return this.f4886i;
    }
}
