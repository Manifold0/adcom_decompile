// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.h;

import org.json.JSONObject;

public class c
{
    public final long a;
    public final String b;
    public final String c;
    public final int d;
    
    public c(final JSONObject jsonObject) {
        this.a = jsonObject.optInt("id");
        this.b = jsonObject.optString("identifier");
        this.c = jsonObject.optString("data");
        this.d = jsonObject.optInt("remaining_uses", -1);
    }
}
