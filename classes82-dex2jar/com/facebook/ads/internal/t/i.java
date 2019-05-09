// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

import android.support.annotation.Nullable;
import org.json.JSONObject;

public class i
{
    private final double a;
    private final double b;
    
    public i(final double a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    @Nullable
    public static i a(final JSONObject jsonObject) {
        if (jsonObject != null) {
            final double optDouble = jsonObject.optDouble("value", 0.0);
            final double optDouble2 = jsonObject.optDouble("scale", 0.0);
            if (optDouble != 0.0 && optDouble2 != 0.0) {
                return new i(optDouble, optDouble2);
            }
        }
        return null;
    }
    
    public double a() {
        return this.a;
    }
    
    public double b() {
        return this.b;
    }
}
