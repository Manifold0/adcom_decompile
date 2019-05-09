// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public final class n extends o
{
    private final long c;
    
    public n(final SharedPreferences sharedPreferences, final String s) {
        super(sharedPreferences, s);
        this.c = 0L;
    }
    
    public final long a() {
        return this.a.getLong(this.b, this.c);
    }
    
    public final SharedPreferences$Editor a(final SharedPreferences$Editor sharedPreferences$Editor) {
        return sharedPreferences$Editor.remove(this.b);
    }
    
    public final SharedPreferences$Editor a(final SharedPreferences$Editor sharedPreferences$Editor, final long n) {
        return sharedPreferences$Editor.putLong(this.b, n);
    }
    
    public final void a(final long n) {
        this.a.edit().putLong(this.b, n).commit();
    }
}
