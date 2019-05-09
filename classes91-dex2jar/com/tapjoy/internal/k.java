// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;

public final class k extends o
{
    private final double c;
    
    public k(final SharedPreferences sharedPreferences, final String s) {
        super(sharedPreferences, s);
        this.c = 0.0;
    }
    
    public final double a() {
        final String string = this.a.getString(this.b, (String)null);
        if (string != null) {
            try {
                return Double.parseDouble(string);
            }
            catch (NumberFormatException ex) {}
        }
        return this.c;
    }
    
    public final SharedPreferences$Editor a(final SharedPreferences$Editor sharedPreferences$Editor) {
        return sharedPreferences$Editor.remove(this.b);
    }
    
    public final SharedPreferences$Editor a(final SharedPreferences$Editor sharedPreferences$Editor, final double n) {
        return sharedPreferences$Editor.putString(this.b, Double.toString(n));
    }
}
