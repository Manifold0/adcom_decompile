// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public final class m extends o
{
    private final int c;
    
    public m(final SharedPreferences sharedPreferences, final String s, final int c) {
        super(sharedPreferences, s);
        this.c = c;
    }
    
    public final SharedPreferences$Editor a(final SharedPreferences$Editor sharedPreferences$Editor, final int n) {
        return sharedPreferences$Editor.putInt(this.b, n);
    }
    
    public final Integer a() {
        return this.b();
    }
    
    public final void a(final int n) {
        this.a.edit().putInt(this.b, n).commit();
    }
    
    public final void a(final Integer n) {
        if (n != null) {
            this.a((int)n);
            return;
        }
        this.c();
    }
    
    public final int b() {
        return this.a.getInt(this.b, this.c);
    }
}
