// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.m;

import com.facebook.ads.internal.w.b.v;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import java.util.List;

public class c
{
    private List<a> a;
    private int b;
    private d c;
    @Nullable
    private String d;
    @Nullable
    private String e;
    
    public c(final d c, @Nullable final String d, @Nullable final String e) {
        this.b = 0;
        this.a = new ArrayList<a>();
        this.c = c;
        this.d = d;
        this.e = e;
    }
    
    public d a() {
        return this.c;
    }
    
    public void a(final a a) {
        this.a.add(a);
    }
    
    @Nullable
    public String b() {
        return this.d;
    }
    
    @Nullable
    public String c() {
        return this.e;
    }
    
    public int d() {
        return this.a.size();
    }
    
    public a e() {
        if (this.b < this.a.size()) {
            ++this.b;
            return this.a.get(this.b - 1);
        }
        return null;
    }
    
    @Nullable
    public String f() {
        if (this.b > 0 && this.b <= this.a.size()) {
            return this.a.get(this.b - 1).c().optString("ct");
        }
        return null;
    }
    
    public boolean g() {
        return this.c == null || v.a() > this.c.a() + this.c.l();
    }
    
    public long h() {
        if (this.c != null) {
            return this.c.a() + this.c.l();
        }
        return -1L;
    }
}
