// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import java.util.UUID;

public class o
{
    private static final String a;
    private static volatile boolean b;
    private static double c;
    private static String d;
    
    static {
        a = o.class.getSimpleName();
        o.b = false;
    }
    
    public static void a() {
        if (!o.b) {
            synchronized (o.a) {
                if (!o.b) {
                    o.b = true;
                    o.c = System.currentTimeMillis() / 1000.0;
                    o.d = UUID.randomUUID().toString();
                }
            }
        }
    }
    
    public static double b() {
        return o.c;
    }
    
    public static String c() {
        return o.d;
    }
}
