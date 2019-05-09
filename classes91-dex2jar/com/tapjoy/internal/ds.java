// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Collections;
import java.util.List;

public final class ds
{
    public static IllegalStateException a(final Object... array) {
        final StringBuilder sb = new StringBuilder();
        final int length = array.length;
        String s = "";
        String s2;
        for (int i = 0; i < length; i += 2, s = s2) {
            s2 = s;
            if (array[i] == null) {
                if (sb.length() > 0) {
                    s = "s";
                }
                sb.append("\n  ");
                sb.append(array[i + 1]);
                s2 = s;
            }
        }
        throw new IllegalStateException("Required field" + s + " not set:" + (Object)sb);
    }
    
    public static List a() {
        return new dt(Collections.emptyList());
    }
    
    public static List a(final String s, final List list) {
        if (list == null) {
            throw new NullPointerException(s + " == null");
        }
        List a = list;
        if (list instanceof dt) {
            a = ((dt)list).a;
        }
        if (a == Collections.emptyList() || a instanceof dr) {
            return a;
        }
        final dr dr = new dr(a);
        if (dr.contains(null)) {
            throw new IllegalArgumentException(s + ".contains(null)");
        }
        return dr;
    }
    
    public static boolean a(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
}
