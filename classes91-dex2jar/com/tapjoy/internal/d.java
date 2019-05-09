// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import android.app.Activity;
import java.util.Collections;
import java.util.Set;
import android.app.Application;

public final class d
{
    private static Application a;
    private static int b;
    private static final cd c;
    private static final Set d;
    private static final cd e;
    
    static {
        c = new cd();
        d = Collections.synchronizedSet((Set<Object>)new bd());
        e = new cd();
    }
    
    public static Activity a() {
        Activity c;
        if ((c = (Activity)com.tapjoy.internal.d.e.a()) == null) {
            c = c();
        }
        return c;
    }
    
    public static void a(final Activity activity) {
        com.tapjoy.internal.d.c.a(activity);
    }
    
    public static void a(final Application a) {
        synchronized (d.class) {
            if (com.tapjoy.internal.d.a != a) {
                com.tapjoy.internal.d.a = a;
            }
        }
    }
    
    public static void b(final Activity activity) {
        ++com.tapjoy.internal.d.b;
        com.tapjoy.internal.d.c.a(activity);
        com.tapjoy.internal.d.d.add(activity);
    }
    
    public static boolean b() {
        return com.tapjoy.internal.d.b > 0;
    }
    
    public static Activity c() {
        final Activity activity = (Activity)com.tapjoy.internal.d.c.a();
        if (activity != null) {
            return activity;
        }
        return (Activity)cw.a(com.tapjoy.internal.d.d.iterator());
    }
    
    public static void c(final Activity activity) {
        --com.tapjoy.internal.d.b;
        com.tapjoy.internal.d.c.a = null;
        com.tapjoy.internal.d.d.remove(activity);
        if (com.tapjoy.internal.d.b < 0) {
            com.tapjoy.internal.d.b = 0;
        }
    }
}
