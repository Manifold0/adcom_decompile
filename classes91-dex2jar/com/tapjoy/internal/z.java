// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public final class z
{
    private static final ThreadLocal a;
    private static final ThreadLocal b;
    
    static {
        a = new ThreadLocal() {};
        b = new ThreadLocal() {};
    }
    
    public static String a(final Date date) {
        return z.a.get().format(date);
    }
}
