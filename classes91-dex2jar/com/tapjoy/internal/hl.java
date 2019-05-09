// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;

public abstract class hl extends ce
{
    protected Object a(final bs bs) {
        bs.s();
        return null;
    }
    
    @Override
    public final String b() {
        return "POST";
    }
    
    @Override
    public final String d() {
        return "application/json";
    }
    
    @Override
    public Map e() {
        final Map e = super.e();
        final gc a = gc.a();
        e.put("sdk_ver", a.m + "/Android");
        e.put("api_key", a.l);
        if (fz.a) {
            e.put("debug", true);
        }
        return e;
    }
    
    protected Object f() {
        try {
            return super.f();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
}
