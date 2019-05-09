// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters.b;

import org.json.JSONObject;
import java.io.Serializable;

public class c implements Serializable
{
    private static final long serialVersionUID = -1165646029762217510L;
    private final int a;
    private final int b;
    private final int c;
    private final boolean d;
    
    private c(final int a, final int b, final int c, final boolean d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public static c a(final JSONObject jsonObject) {
        return new c(jsonObject.optInt("countdown_time_ms", 6000), jsonObject.optInt("pulse_animation_duration_ms", 600), jsonObject.optInt("default_ad_index"), jsonObject.optBoolean("should_show_rating", false));
    }
    
    public int a() {
        return this.a;
    }
    
    public int b() {
        return this.c;
    }
    
    public int c() {
        return this.b;
    }
    
    public boolean d() {
        return this.d;
    }
}
