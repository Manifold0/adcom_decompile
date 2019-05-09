// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.widget.TextView;
import android.graphics.Color;
import org.json.JSONObject;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.graphics.Typeface;

public class j
{
    private Typeface a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    private boolean i;
    
    public j() {
        this.a = Typeface.create(Typeface.SANS_SERIF, 0);
        this.b = -1;
        this.c = -16777216;
        this.d = -11643291;
        this.e = 0;
        this.f = -12420889;
        this.g = -12420889;
        this.h = AdInternalSettings.isVideoAutoplay();
        this.i = AdInternalSettings.isVideoAutoplayOnMobile();
    }
    
    public j(final JSONObject jsonObject) {
        this.a = Typeface.create(Typeface.SANS_SERIF, 0);
        this.b = -1;
        this.c = -16777216;
        this.d = -11643291;
        this.e = 0;
        this.f = -12420889;
        this.g = -12420889;
        this.h = AdInternalSettings.isVideoAutoplay();
        this.i = AdInternalSettings.isVideoAutoplayOnMobile();
        int color;
        if (jsonObject.getBoolean("background_transparent")) {
            color = 0;
        }
        else {
            color = Color.parseColor(jsonObject.getString("background_color"));
        }
        this.b = color;
        this.c = Color.parseColor(jsonObject.getString("title_text_color"));
        this.d = Color.parseColor(jsonObject.getString("description_text_color"));
        int color2;
        if (jsonObject.getBoolean("button_transparent")) {
            color2 = 0;
        }
        else {
            color2 = Color.parseColor(jsonObject.getString("button_color"));
        }
        this.e = color2;
        int color3;
        if (jsonObject.getBoolean("button_border_transparent")) {
            color3 = 0;
        }
        else {
            color3 = Color.parseColor(jsonObject.getString("button_border_color"));
        }
        this.g = color3;
        this.f = Color.parseColor(jsonObject.getString("button_text_color"));
        this.a = Typeface.create(jsonObject.getString("android_typeface"), 0);
    }
    
    public Typeface a() {
        return this.a;
    }
    
    public void a(final int b) {
        this.b = b;
    }
    
    public void a(final Typeface a) {
        this.a = a;
    }
    
    public void a(final TextView textView) {
        textView.setTextColor(this.c);
        textView.setTextSize(16.0f);
        textView.setTypeface(this.a, 1);
    }
    
    public void a(final boolean i) {
        this.i = i;
    }
    
    public int b() {
        return this.b;
    }
    
    public void b(final int c) {
        this.c = c;
    }
    
    public void b(final TextView textView) {
        textView.setTextColor(this.d);
        textView.setTextSize(10.0f);
        textView.setTypeface(this.a);
    }
    
    public void b(final boolean h) {
        this.h = h;
    }
    
    public int c() {
        return this.c;
    }
    
    public void c(final int d) {
        this.d = d;
    }
    
    public void c(final TextView textView) {
        x.a((View)textView, this.e);
        textView.setTextColor(this.f);
        textView.setTextSize(2, 14.0f);
        textView.setTypeface(this.a, 1);
    }
    
    public int d() {
        return this.d;
    }
    
    public void d(final int e) {
        this.e = e;
    }
    
    public int e() {
        return this.e;
    }
    
    public void e(final int f) {
        this.f = f;
    }
    
    public int f() {
        return this.f;
    }
    
    public void f(final int g) {
        this.g = g;
    }
    
    public int g() {
        return this.g;
    }
    
    public int h() {
        return 16;
    }
    
    public int i() {
        return 10;
    }
    
    public boolean j() {
        return this.h;
    }
    
    public boolean k() {
        return this.i;
    }
}
