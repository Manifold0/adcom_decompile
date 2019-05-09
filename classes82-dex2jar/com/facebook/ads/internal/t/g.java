// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

import android.support.annotation.Nullable;
import org.json.JSONObject;

public class g
{
    private final String a;
    private final int b;
    private final int c;
    
    public g(final String a, final int b, final int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Nullable
    public static g a(final JSONObject jsonObject) {
        if (jsonObject != null) {
            final String optString = jsonObject.optString("url");
            if (optString != null) {
                return new g(optString, jsonObject.optInt("width", 0), jsonObject.optInt("height", 0));
            }
        }
        return null;
    }
    
    public String a() {
        return this.a;
    }
    
    public int b() {
        return this.b;
    }
    
    public int c() {
        return this.c;
    }
}
