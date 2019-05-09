// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import android.text.TextUtils;
import org.json.JSONObject;
import android.graphics.Color;
import java.io.Serializable;

public class h implements Serializable
{
    public static final int a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    public static final int f;
    private static final long serialVersionUID = 8946536326456653736L;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    
    static {
        a = Color.parseColor("#90949c");
        b = Color.parseColor("#4b4f56");
        c = Color.parseColor("#f6f7f9");
        d = Color.parseColor("#ff4080ff");
        e = Color.parseColor("#23272F");
        f = Color.parseColor("#ff4080ff");
    }
    
    public h() {
        this.g = com.facebook.ads.internal.adapters.b.h.a;
        this.h = com.facebook.ads.internal.adapters.b.h.b;
        this.i = -16777216;
        this.j = com.facebook.ads.internal.adapters.b.h.c;
        this.k = com.facebook.ads.internal.adapters.b.h.d;
        this.l = -1;
        this.m = -16777216;
    }
    
    public static h a(final JSONObject jsonObject) {
        final h h = new h();
        if (jsonObject != null) {
            final String optString = jsonObject.optString("accent_color");
            final String optString2 = jsonObject.optString("body_color");
            final String optString3 = jsonObject.optString("subtitle_color");
            final String optString4 = jsonObject.optString("bg_color");
            final String optString5 = jsonObject.optString("cta_color");
            final String optString6 = jsonObject.optString("cta_text_color");
            final String optString7 = jsonObject.optString("title_color");
            if (!TextUtils.isEmpty((CharSequence)optString)) {
                h.g = Color.parseColor(optString);
            }
            if (!TextUtils.isEmpty((CharSequence)optString2)) {
                h.h = Color.parseColor(optString2);
            }
            if (!TextUtils.isEmpty((CharSequence)optString3)) {
                h.i = Color.parseColor(optString3);
            }
            if (!TextUtils.isEmpty((CharSequence)optString4)) {
                h.j = Color.parseColor(optString4);
            }
            if (!TextUtils.isEmpty((CharSequence)optString5)) {
                h.k = Color.parseColor(optString5);
            }
            if (!TextUtils.isEmpty((CharSequence)optString6)) {
                h.l = Color.parseColor(optString6);
            }
            if (!TextUtils.isEmpty((CharSequence)optString7)) {
                h.m = Color.parseColor(optString7);
            }
        }
        return h;
    }
    
    public int a(final boolean b) {
        if (b) {
            return -1;
        }
        return this.g;
    }
    
    public void a(final int k) {
        this.k = k;
    }
    
    public int b(final boolean b) {
        if (b) {
            return -1;
        }
        return this.h;
    }
    
    public int c(final boolean b) {
        if (b) {
            return -1;
        }
        return this.i;
    }
    
    public int d(final boolean b) {
        if (b) {
            return com.facebook.ads.internal.adapters.b.h.e;
        }
        return this.j;
    }
    
    public int e(final boolean b) {
        if (b) {
            return -1;
        }
        return this.k;
    }
    
    public int f(final boolean b) {
        if (b) {
            return com.facebook.ads.internal.adapters.b.h.f;
        }
        return this.l;
    }
    
    public int g(final boolean b) {
        if (b) {
            return -1;
        }
        return this.m;
    }
}
