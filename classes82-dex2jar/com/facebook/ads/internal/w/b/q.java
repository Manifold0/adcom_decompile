// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.w.b;

import java.util.Random;

public class q
{
    public static String a(final int n) {
        if (n > 0 && new Random().nextFloat() < 1.0f / n) {
            return s.a(Thread.currentThread().getStackTrace());
        }
        return null;
    }
}
