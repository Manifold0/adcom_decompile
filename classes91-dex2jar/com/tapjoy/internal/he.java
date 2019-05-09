// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class he
{
    public float a;
    public int b;
    
    public static he a(final String s) {
        if (ct.c(s)) {
            return null;
        }
        try {
            final he he = new he();
            final int length = s.length();
            final char char1 = s.charAt(length - 1);
            if (char1 == 'w') {
                he.a = Float.valueOf(s.substring(0, length - 1));
                he.b = 1;
            }
            else if (char1 == 'h') {
                he.a = Float.valueOf(s.substring(0, length - 1));
                he.b = 2;
            }
            else {
                he.a = Float.valueOf(s);
                he.b = 0;
            }
            return he;
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    
    public final float a(final float n, final float n2) {
        if (this.b == 1) {
            return this.a * n / 100.0f;
        }
        if (this.b == 2) {
            return this.a * n2 / 100.0f;
        }
        return this.a;
    }
}
