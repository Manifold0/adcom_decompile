// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences;

public final class j extends o
{
    private final boolean c;
    
    public j(final SharedPreferences sharedPreferences, final String s) {
        super(sharedPreferences, s);
        this.c = false;
    }
    
    public final Boolean a() {
        return this.a.getBoolean(this.b, this.c);
    }
    
    public final void a(final boolean b) {
        this.a.edit().putBoolean(this.b, b).commit();
    }
}
