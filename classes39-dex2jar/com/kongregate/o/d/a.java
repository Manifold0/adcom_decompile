// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.d;

import com.kongregate.android.internal.util.j;

public class a implements c
{
    private final c a;
    
    public a() {
        this.a = this.f();
    }
    
    private c f() {
        try {
            final c c = (c)Class.forName("com.kongregate.android.internal.config.CustomBuildFeatureSet").newInstance();
            j.b("Using custom build feature set");
            return c;
        }
        catch (ClassNotFoundException ex3) {
            j.b("Using default build feature set");
        }
        catch (InstantiationException ex) {
            j.b("Using default build feature set due to error: ", ex);
            goto Label_0025;
        }
        catch (IllegalAccessException ex2) {
            j.b("Using default build feature set due to error: ", ex2);
            goto Label_0025;
        }
    }
    
    @Override
    public String a() {
        if (this.a == null) {
            return "default";
        }
        return this.a.a();
    }
    
    @Override
    public boolean b() {
        return this.a == null || this.a.b();
    }
    
    @Override
    public boolean c() {
        return this.b() && (this.a == null || this.a.b());
    }
    
    @Override
    public boolean d() {
        return this.c() && (this.a == null || this.a.d());
    }
    
    public boolean e() {
        return this.d();
    }
}
