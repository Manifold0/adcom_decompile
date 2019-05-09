// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class cr
{
    public static boolean a(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static Object b(final Object o, final Object o2) {
        if (o != null) {
            return o;
        }
        return cs.a(o2);
    }
}
