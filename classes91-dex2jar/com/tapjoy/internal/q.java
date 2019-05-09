// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public final class q extends o
{
    private final String c;
    
    public q(final SharedPreferences sharedPreferences, final String s) {
        super(sharedPreferences, s);
        this.c = null;
    }
    
    public final SharedPreferences$Editor a(final SharedPreferences$Editor sharedPreferences$Editor, final String s) {
        if (s != null) {
            return sharedPreferences$Editor.putString(this.b, s);
        }
        return sharedPreferences$Editor.remove(this.b);
    }
    
    public final String a() {
        return this.a.getString(this.b, this.c);
    }
    
    public final void a(final String s) {
        this.a.edit().putString(this.b, s).commit();
    }
}
