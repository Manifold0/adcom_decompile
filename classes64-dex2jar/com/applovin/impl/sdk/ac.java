// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import android.content.Context;

public class ac
{
    public static Boolean a(final Context context) {
        return a(ef.g, context);
    }
    
    private static Boolean a(final ef<Boolean> ef, final Context context) {
        return eg.b(ef, null, context);
    }
    
    private static boolean a(final ef<Boolean> ef, final Boolean b, final Context context) {
        final Boolean a = a(ef, context);
        eg.a(ef, b, context);
        return a == null || a != b;
    }
    
    public static boolean a(final boolean b, final Context context) {
        return a(ef.g, b, context);
    }
    
    public static Boolean b(final Context context) {
        return a(ef.h, context);
    }
    
    public static boolean b(final boolean b, final Context context) {
        return a(ef.h, b, context);
    }
}
