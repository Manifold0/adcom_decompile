// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import org.json.JSONObject;
import java.io.Serializable;

public abstract class a implements Serializable
{
    private static final long serialVersionUID = -5352540727250859603L;
    private int a;
    private String b;
    private String c;
    
    public a() {
        this.a = 200;
    }
    
    public static a a(final boolean b, final JSONObject jsonObject) {
        if (b) {
            return f.a(jsonObject);
        }
        return q.a(jsonObject);
    }
    
    public abstract String a();
    
    public void a(final int a) {
        this.a = a;
    }
    
    public void a(final String b) {
        this.b = b;
    }
    
    public int b() {
        return this.a;
    }
    
    public void b(final String c) {
        this.c = c;
    }
    
    public String c() {
        return this.b;
    }
    
    public String d() {
        return this.c;
    }
}
