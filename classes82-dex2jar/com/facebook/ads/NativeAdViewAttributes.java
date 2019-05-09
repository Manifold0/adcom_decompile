// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.graphics.Typeface;
import com.facebook.ads.internal.o.b;
import com.facebook.ads.internal.o.a;
import org.json.JSONObject;
import com.facebook.ads.internal.t.j;

public class NativeAdViewAttributes
{
    private final j a;
    
    public NativeAdViewAttributes() {
        this.a = new j();
    }
    
    NativeAdViewAttributes(final j a) {
        this.a = a;
    }
    
    public NativeAdViewAttributes(final JSONObject jsonObject) {
        while (true) {
            try {
                final j a = new j(jsonObject);
                this.a = a;
            }
            catch (Exception ex) {
                final j a = new j();
                b.a(com.facebook.ads.internal.o.a.a(ex, "Error retrieving native ui configuration data"));
                continue;
            }
            break;
        }
    }
    
    j a() {
        return this.a;
    }
    
    public boolean getAutoplay() {
        return this.a.j();
    }
    
    public boolean getAutoplayOnMobile() {
        return this.a.k();
    }
    
    public int getBackgroundColor() {
        return this.a.b();
    }
    
    public int getButtonBorderColor() {
        return this.a.g();
    }
    
    public int getButtonColor() {
        return this.a.e();
    }
    
    public int getButtonTextColor() {
        return this.a.f();
    }
    
    public int getDescriptionTextColor() {
        return this.a.d();
    }
    
    public int getDescriptionTextSize() {
        return this.a.i();
    }
    
    public int getTitleTextColor() {
        return this.a.c();
    }
    
    public int getTitleTextSize() {
        return this.a.h();
    }
    
    public Typeface getTypeface() {
        return this.a.a();
    }
    
    public NativeAdViewAttributes setAutoplay(final boolean b) {
        this.a.b(b);
        return this;
    }
    
    public NativeAdViewAttributes setAutoplayOnMobile(final boolean b) {
        this.a.a(b);
        return this;
    }
    
    public NativeAdViewAttributes setBackgroundColor(final int n) {
        this.a.a(n);
        return this;
    }
    
    public NativeAdViewAttributes setButtonBorderColor(final int n) {
        this.a.f(n);
        return this;
    }
    
    public NativeAdViewAttributes setButtonColor(final int n) {
        this.a.d(n);
        return this;
    }
    
    public NativeAdViewAttributes setButtonTextColor(final int n) {
        this.a.e(n);
        return this;
    }
    
    public NativeAdViewAttributes setDescriptionTextColor(final int n) {
        this.a.c(n);
        return this;
    }
    
    public NativeAdViewAttributes setTitleTextColor(final int n) {
        this.a.b(n);
        return this;
    }
    
    public NativeAdViewAttributes setTypeface(final Typeface typeface) {
        this.a.a(typeface);
        return this;
    }
}
