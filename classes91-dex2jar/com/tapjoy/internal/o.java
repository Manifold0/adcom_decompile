// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences;

public abstract class o
{
    protected SharedPreferences a;
    protected String b;
    
    public o(final SharedPreferences a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public final void c() {
        this.a.edit().remove(this.b).commit();
    }
}
